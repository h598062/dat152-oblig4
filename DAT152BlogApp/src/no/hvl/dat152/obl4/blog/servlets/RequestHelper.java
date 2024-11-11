package no.hvl.dat152.obl4.blog.servlets;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat152.obl4.blog.Constants;
import no.hvl.dat152.obl4.blog.tokens.JWTHandler;

public class RequestHelper {

	public static String getCookieValue(HttpServletRequest request,
			String cookieName) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(cookieName.trim())) {
					return c.getValue();
				}
			}
		}
		return null;
	}
	
	public static Cookie getCookie(HttpServletRequest request,
			String cookieName) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(cookieName.trim())) {
					return c;
				}
			}
		}
		return null;
	}
	
	public static boolean isLoggedIn(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null;
	}
	
	public static boolean isLoggedInSSO(HttpServletRequest request, String keypath) {
		String id_token = RequestHelper.getCookieValue(request, "id_token");
		doJWT(request, id_token, keypath);
		
		return JWTHandler.verifyJWS(id_token, keypath);
		
	}
	
	public static void doJWT(HttpServletRequest request, String id_token, String pubkeypath) {

		Claims claims = JWTHandler.getJwt(id_token, pubkeypath);
		
		if(claims != null) {
			Map resourceAccess = (HashMap) claims.get("resource_access");
			Map clientApp = (HashMap) resourceAccess.get("oblig4");
			List<String> roles = (ArrayList<String>) clientApp.get("roles");
			
			String role = roles.get(0);
			String user = (String) claims.get("preferred_username");
			
			request.getSession().setAttribute("loggeduser", user);
			request.getSession().setAttribute("role", role);
			request.getSession().setAttribute("logoutep", Constants.IDP_LOGOUT_ENDPOINT);
			request.getSession().setAttribute("clientid", Constants.CLIENT_ID);
			request.getSession().setAttribute("redirectep", Constants.SP_LOGOUT_ADDRESS);
		} 

	}
}
