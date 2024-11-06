package no.hvl.dat152.obl4.database;

public class AppUser {

	private String username; 
	private String passhash; 
	private String firstname;
	private String lastname;
	private String mobilephone;
	private String role;
	
	public AppUser(String username, String passhash, String firstname, 
			String lastname, String mobilephone, String role) {
		this.username = username;
		this.passhash = passhash;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobilephone = mobilephone;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public String getPasshash() {
		return passhash;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getMobilephone() {
		return mobilephone;
	}
	
	public String getRole() {
		return role;
	}
	
}

