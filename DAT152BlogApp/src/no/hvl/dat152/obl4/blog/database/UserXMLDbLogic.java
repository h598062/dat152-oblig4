/**
 * 
 */
package no.hvl.dat152.obl4.blog.database;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import no.hvl.dat152.obl4.blog.util.PasswordHash;


public class UserXMLDbLogic {

	private String pathtoxmldb;
	
	public UserXMLDbLogic(String pathxmldb) {
		
		this.pathtoxmldb = pathxmldb;
	}
	
	public User authenticateWithSaltUser(String userid, String password) {
		
		try {
			User user = getUser(userid);
			PasswordHash ph = new PasswordHash(PasswordHash.SHA256);
		
			boolean auth = ph.validatePasswordWithSalt(password, user.getSalt(), user.getHashedPassword());
			
			if(auth)
				return user;
			else
				return null;
		
		} catch(JAXBException | NoSuchAlgorithmException | NullPointerException e) {
			return null;
		}
	}
	
	private User getUser(String userid) throws JAXBException {
		
		File f = new File(pathtoxmldb);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Users.class); 
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Users users = (Users) jaxbUnmarshaller.unmarshal(f);
        
        for (User user : users.getUsers()) {
        	if(user.getUserId().equals(userid))
        		return user;
        }
        
		return null;
	}
	
	// this is not thread safe
	public boolean registerNewUserWithSalt(String userid, String password, String role, String phone) {
		// userid must be unique
		try {
			if(getUser(userid)!= null)
				return false;
		} catch (JAXBException e1) {
//			e1.printStackTrace();
		}
		
		boolean succeed = false;
		
		try {
			PasswordHash ph = new PasswordHash(PasswordHash.SHA256);
			byte[] salt = ph.getSalt();
			
			String hashpwd = ph.generateHashWithSalt(password, salt);
			String salthex = ph.getPasswordSalt();
		
			File f = new File(pathtoxmldb);
			Users users = new Users();
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class); 
			
			try {
				if(f.exists()) {
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					users = (Users) jaxbUnmarshaller.unmarshal(f);
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}

	        User user = new User(userid, hashpwd, salthex, role, phone);
	        users.add(user);
	        
	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        jaxbMarshaller.marshal(users,f);
	        
	        succeed = true;
		} catch (JAXBException | NoSuchAlgorithmException e) {
			succeed = false;
		}
		
		return succeed;
	}

}
