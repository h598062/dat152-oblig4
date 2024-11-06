package no.hvl.dat152.obl4.util;

import java.sql.Connection;
import java.sql.Statement;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import no.hvl.dat152.obl4.database.DatabaseHelper;


@WebListener
public class MyContextListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent arg0)  { 
    System.out.println("Application is started. The database will be created if it does not exist.");
    setupDB();
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0)  { 
    System.out.println("Application was stopped.");    
  }
  
  private void setupDB() {
    try (
        Connection conn = DatabaseHelper.getConnection();
        Statement st = conn.createStatement();
        ){
      try {
        st.executeUpdate("create schema SecOblig");
        System.out.println("Schema SecOblig was created.");
      } catch (Exception e) {
        System.out.println(e.toString());
      }

      try {
       st.executeUpdate("create table SecOblig.AppUser ("
           + "username VARCHAR(50) UNIQUE,"
           + "passhash VARCHAR(50),"
           + "firstname VARCHAR(50),"
           + "lastname VARCHAR(50),"
           + "mobilephone VARCHAR(20),"
           + "role VARCHAR(10),"
           + "PRIMARY KEY (username, passhash))");
        System.out.println("Table SecOblig.AppUser was created.");
      } catch (Exception e) {
        System.out.println(e.toString());
      }
      
      try {
        st.executeUpdate("CREATE TABLE SecOblig.History (datetime TIMESTAMP,"
            + "username VARCHAR(50),"
            + "searchkey VARCHAR(50),"
            + "PRIMARY KEY (datetime, username),"
            + "FOREIGN KEY (username) REFERENCES SecOblig.AppUser(username))");
     

        System.out.println("Table SecOblig.History was created.");
      } catch (Exception e) {
        System.out.println(e.toString());
      }
      
      // insert default admin user (username=admin (password=md5("password")))
      try {
          st.executeUpdate("INSERT INTO SecOblig.AppUser VALUES "
          		+ "('admin', '5f4dcc3b5aa765d61d8327deb882cf99','Admin', 'SuperUser', '120389734562',"
        		  +"'"+Role.ADMIN.toString()+"')");

          System.out.println("Default admin user \"admin\" created...");

        } catch (Exception e) {
          System.out.println(e.toString());
        }

    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}
