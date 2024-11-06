/**
 * 
 */
package no.hvl.dat152.obl4.blog.database;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;




@XmlRootElement(name = "users")
public class Users {
	
	private ArrayList<User> users;
	/**
	 * 
	 */
	public Users() {
		users = new ArrayList<User>();
	}
	
	public void add(User user) {
		users.add(user);
	}
	
	public void remove(User user) {	
		users.remove(user);
	}
	
	public User getUser(User user) {
		
		for(int i=0; i<users.size(); i++) {
			User cuser = users.get(i);
			if(user.getUserId().equals(cuser.getUserId())) {
				return cuser;
			}
		}
		
		return null;
	}
	
	public User getUser(String userId) {
		
		for(int i=0; i<users.size(); i++) {
			User cuser = users.get(i);
			if(userId.equals(cuser.getUserId())) {
				return cuser;
			}
		}
		
		return null;
	}

	/**
	 * @return the users
	 */
	@XmlElement(name = "user")
	public ArrayList<User> getUsers() {
		return users;
	}
	
}
