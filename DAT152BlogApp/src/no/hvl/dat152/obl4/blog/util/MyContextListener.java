package no.hvl.dat152.obl4.blog.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import no.hvl.dat152.obl4.blog.database.UserXMLDbLogic;


@WebListener
public class MyContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event)  { 
    System.out.println("Application is started. The database will be created if it does not exist.");
    setupDB(event);
    
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0)  { 
    System.out.println("Application was stopped.");    
  }
  
  private void setupDB(ServletContextEvent event) {
	  String dbpath = event.getServletContext().getRealPath("WEB-INF/usersdb.xml");
	  
	  // delete the db file
	  File f = new File(dbpath);
	  f.delete();
	  
	  // create a new file
	  String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
	  try {
		PrintWriter writer = new PrintWriter(new File(dbpath));
		writer.write(header);
		writer.flush();
		writer.close();
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	  }	  
	  new UserXMLDbLogic(dbpath);
	  
	  System.out.println("XML User Database path: "+dbpath);
  }
}
