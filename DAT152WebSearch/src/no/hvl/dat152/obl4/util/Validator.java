package no.hvl.dat152.obl4.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class Validator {

	public static String validString(String parameter) {
		return parameter != null ? parameter : "null";
	}
	
	public static String getCookieValue(HttpServletRequest request,
			String cookieName) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(cookieName)) {
					return c.getValue();
				}
			}
		}
		return null;
	}

	public static String getPasswordValidationMessage(String password, String username) {
		String[] weakPasswords = {"password", "123456", "123456789", "12345678", "12345", "1234567", "qwerty", "abc123", "password1"};

		for (String weakPassword : weakPasswords) {
			if (password.equalsIgnoreCase(weakPassword)) {
				return "Password is too weak!";
			}
		}
		if (password.toLowerCase().contains(username.toLowerCase())) {
			return "Password should not contain the username!";
		}
		if (!password.matches(".*[a-z].*")) {
			return "Password must contain at least one lowercase letter!";
		}
		if (!password.matches(".*[A-Z].*")) {
			return "Password must contain at least one uppercase letter!";
		}
		if (!password.matches(".*\\d.*")) {
			return "Password must contain at least one digit!";
		}
		if (!password.matches(".*[@#$%^&+=].*")) {
			return "Password must contain at least one special character!";
		}
		if (password.length() < 8 || password.length() > 20) {
			return "Password must be between 8 and 20 characters long!";
		}
		return null;
	}

}
