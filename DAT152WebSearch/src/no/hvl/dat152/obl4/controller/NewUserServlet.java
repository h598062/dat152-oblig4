package no.hvl.dat152.obl4.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.hvl.dat152.obl4.database.AppUser;
import no.hvl.dat152.obl4.database.AppUserDAO;
import no.hvl.dat152.obl4.util.Crypto;
import no.hvl.dat152.obl4.util.Role;
import no.hvl.dat152.obl4.util.Validator;

@WebServlet("/newuser")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {

		int len = request.getRequestURL().length();
		String dicturi = request.getRequestURL().substring(0, len-7)+"v003/";

		request.setAttribute("dictconfig", dicturi);
		request.getRequestDispatcher("newuser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {

		boolean successfulRegistration = false;

		String username = Validator.validString(request.getParameter("username"));
		String password = Validator.validString(request.getParameter("password"));
		String confirmedPassword = Validator.validString(request.getParameter("confirm_password"));
		String firstName = Validator.validString(request.getParameter("first_name"));
		String lastName = Validator.validString(request.getParameter("last_name"));
		String mobilePhone = Validator.validString(request.getParameter("mobile_phone"));
		String preferredDict = Validator.validString(request.getParameter("dicturl"));

		AppUser user = null;
		if (password.equals(confirmedPassword)) {
			String passwordValidationMessage = getPasswordValidationMessage(password, username);
			if (passwordValidationMessage == null) {
				AppUserDAO userDAO = new AppUserDAO();
				user = new AppUser(username, Crypto.generateMD5Hash(password), firstName, lastName, mobilePhone, Role.USER.toString());
				successfulRegistration = userDAO.saveUser(user);
			} else {
				request.setAttribute("message", passwordValidationMessage);
			}
		} else {
			request.setAttribute("message", "Passwords do not match!");
		}

		if (successfulRegistration) {
			request.getSession().setAttribute("user", user);
			Cookie dicturlCookie = new Cookie("dicturl", preferredDict);
			dicturlCookie.setMaxAge(60 * 10);
			response.addCookie(dicturlCookie);
			response.sendRedirect("searchpage");
		} else {
			request.getRequestDispatcher("newuser.jsp").forward(request, response);
		}
	}

	private String getPasswordValidationMessage(String password, String username) {
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