/**
 * 
 */
package no.hvl.dat152.obl4.blog.database;



import com.google.gson.JsonObject;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * @author tdoy
 */

@XmlRootElement
@XmlType(propOrder = { "userId", "hashedPassword", "salt", "role", "phone", "clientId"})
public class User {
	
	public static final String ADMIN = "ADMIN";
	public static final String USER = "USER";
	private String userId;
	private String hashedPassword;
	private String salt;
	private String role;
	private String phone;
	private String clientId;			// this is retrieved from the IdP server
	
	public User() {
		
	}
	
	public User(String userId, String hashedPassword) {
		
		this.userId = userId;
		this.hashedPassword = hashedPassword;
	}
	
	public User(String userId, String hashedPassword, String salt, String role, String phone) {
		
		this.userId = userId;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
		this.role = role;
		this.phone = phone;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	@XmlElement(name = "userid")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the hashedPassword
	 */
	public String getHashedPassword() {
		return hashedPassword;
	}

	/**
	 * @param hashedPassword the hashedPassword to set
	 */
	@XmlElement(name = "hashpwd")
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt the salt to set
	 */
	@XmlElement(name = "salt")
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	@XmlElement(name = "role")
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	@XmlElement(name = "phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	@XmlElement(name = "clientId")
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		JsonObject jobj = new JsonObject();
		jobj.addProperty("userId", userId);
		jobj.addProperty("hashedPassword",hashedPassword);
		jobj.addProperty("salt", salt);
		jobj.addProperty("role", role);
		jobj.addProperty("phone", phone);
		jobj.addProperty("clientId", clientId);
		
		return jobj.getAsString();
		
	}

}
