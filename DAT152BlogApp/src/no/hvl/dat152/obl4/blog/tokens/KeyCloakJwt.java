/**
 * 
 */
package no.hvl.dat152.obl4.blog.tokens;


/**
 * @author tdoy
 */
public class KeyCloakJwt {

//	json = {"access_token":"value","expires_in":"value","refresh_expires_in":"value","refresh_token":"value"}
	private String access_token;
	private int expires_in;			//sec
	private int refresh_expires_in;		//sec
	private String refresh_token;
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/**
	 * @return the expires_in
	 */
	public int getExpires_in() {
		return expires_in;
	}
	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	/**
	 * @return the refresh_expires_in
	 */
	public int getRefresh_expires_in() {
		return refresh_expires_in;
	}
	/**
	 * @param refresh_expires_in the refresh_expires_in to set
	 */
	public void setRefresh_expires_in(int refresh_expires_in) {
		this.refresh_expires_in = refresh_expires_in;
	}
	/**
	 * @return the refresh_token
	 */
	public String getRefresh_token() {
		return refresh_token;
	}
	/**
	 * @param refresh_token the refresh_token to set
	 */
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
	
}
