/**
 * 
 */
package no.hvl.dat152.obl4.blog;


public class Constants {

	// IdP parameters
	public static String CLIENT_ID = "oblig4";									// this is assigned to the client by the Identity Provider
	public static String CLIENT_SECRET = "qV2EpPNRW8IpMOYAq1I0dRoCxMOj5lSU";	// this is issued to the client by the identity Provider during registration
	
	public static final String STATE = "abcdef";		// this should be a secure random number (not used in this example)
	
	public static final int IDP_PORT = 9095;
	public static final String IDP_AUTH_ENDPOINT = "http://localhost:"+IDP_PORT+"/realms/DAT152Oblig4/protocol/openid-connect/auth";
	public static final String IDP_LOGOUT_ENDPOINT = "http://localhost:"+IDP_PORT+"/realms/DAT152Oblig4/protocol/openid-connect/logout";
	public static final String IDP_TOKEN_ENDPOINT = "http://localhost:"+IDP_PORT+"/realms/DAT152Oblig4/protocol/openid-connect/token";
	public static final String IDP_USERCLAIMS_ENDPOINT = "http://localhost:"+IDP_PORT+"/realms/DAT152Oblig4/protocol/openid-connect/userinfo";
	public static final String IDP_REGISTER_ENDPOINT = "http://localhost:"+IDP_PORT+"/realms/DAT152Oblig4/protocol/openid-connect/register";
	
	public static final String IDP_PATH = "/realms/DAT152Oblig4";
	
	// SP parameters
	public static final int SP_CALLBACK_PORT = 9091;
	public static final String SP_ADDRESS = "http://localhost:"+SP_CALLBACK_PORT+"/DAT152BlogApp";
	public static final String SP_CALLBACK_ADDRESS = "http://localhost:"+SP_CALLBACK_PORT+"/DAT152BlogApp/callback";
	public static final String SP_LOGOUT_ADDRESS = "http://localhost:"+SP_CALLBACK_PORT+"/DAT152BlogApp/logout";
	
}
