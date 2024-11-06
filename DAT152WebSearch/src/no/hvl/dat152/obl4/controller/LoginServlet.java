package no.hvl.dat152.obl4.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.hvl.dat152.obl4.database.AppUser;
import no.hvl.dat152.obl4.database.AppUserDAO;
import no.hvl.dat152.obl4.util.Role;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean successfulLogin = login(request, response);

		if (successfulLogin) {
			
			// forward user to searchpage
			request.getRequestDispatcher("searchpage").forward(request, response);
			
		} else {
			request.setAttribute("message", " Login failed!...");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	
	private boolean login(HttpServletRequest request,
			HttpServletResponse response) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean successfulLogin = false;

		if (username != null && password != null) {

			AppUserDAO userDAO = new AppUserDAO();
			AppUser authUser = userDAO.getAuthenticatedUser(username, password);

			if (authUser != null) {
				successfulLogin = true;
				request.getSession().setAttribute("user", authUser);
				request.getSession().setAttribute("updaterole", "");
				
				// admin issues
				if(authUser.getRole().equals(Role.ADMIN.name())) {
					// set dictionary url in a cookie
					int len = request.getRequestURL().length();
					String dicturi = request.getRequestURL().substring(0, len-5)+"v003/";
					
					Cookie dicturl = new Cookie("dicturl",dicturi);
					dicturl.setMaxAge(60*10);
					response.addCookie(dicturl);
					List<String> usernames = userDAO.getUsernames();
					request.getSession().setAttribute("usernames", usernames);
					request.getSession().setAttribute("updaterole", "<a href=\"updaterole\">Update Role</a>");
				}
			}
		}
		
		return successfulLogin;
	}
}
