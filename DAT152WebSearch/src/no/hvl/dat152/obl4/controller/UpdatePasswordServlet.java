package no.hvl.dat152.obl4.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.hvl.dat152.obl4.database.AppUser;
import no.hvl.dat152.obl4.database.AppUserDAO;
import no.hvl.dat152.obl4.util.Validator;

@WebServlet("/updatepassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// check that the user has a valid session
		if(RequestHelper.isLoggedIn(request))
			request.getRequestDispatcher("updatepassword.jsp").forward(request, response);
		else {
			request.setAttribute("message", "Session has expired. Login again!");
			request.getRequestDispatcher("login").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.removeAttribute("message");

		boolean successfulPasswordUpdate = false;
		
		String passwordnew = Validator.validString(request
				.getParameter("passwordnew"));
		String confirmedPasswordnew = Validator.validString(request
				.getParameter("confirm_passwordnew"));
		
		
		if (RequestHelper.isLoggedIn(request)) {
			
			AppUser user = (AppUser) request.getSession().getAttribute("user");
			
			AppUserDAO userDAO = new AppUserDAO();
			
			if (passwordnew.equals(confirmedPasswordnew)){
				String passwordValidationMessage = getPasswordValidationMessage(passwordnew, user.getUsername());
				if(passwordValidationMessage == null){
				successfulPasswordUpdate = userDAO.updateUserPassword(user.getUsername(), passwordnew);
				}
				if (successfulPasswordUpdate) {
					request.getSession().invalidate(); // invalidate current session and force user to login again
					request.setAttribute("message", "Password successfully updated. Please login again!");
					response.sendRedirect("login");

				} else {
					request.setAttribute("message", "Password update failed!");
					request.getRequestDispatcher("updatepassword.jsp").forward(request,
							response);
				}
			} else {
				request.setAttribute("message", "Password fields do not match. Try again!");
				request.getRequestDispatcher("updatepassword.jsp").forward(request,
						response);
			}
			
		} else {
			request.getSession().invalidate();
			request.getRequestDispatcher("index.html").forward(request,
					response);
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
