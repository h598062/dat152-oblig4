package no.hvl.dat152.obl4.blog.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.hvl.dat152.obl4.blog.database.User;
import no.hvl.dat152.obl4.blog.database.UserXMLDbLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		if(username != null && pwd != null) {
			/**
			 * use xml file located on the server to store users - weak
			 */
			String dbpath = getServletContext().getRealPath("WEB-INF/usersdb.xml");
			UserXMLDbLogic xmldb = new UserXMLDbLogic(dbpath);
			User user = xmldb.authenticateWithSaltUser(username, pwd);
			
			if(user != null) {
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("loggeduser", user.getUserId());
				request.getSession().setAttribute("role", user.getRole());
				
				// forward to blog controller
				request.getRequestDispatcher("blogservlet").forward(request, response);

			} else {
				request.setAttribute("message", "Error logging into the app. Register as a new user...");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}  else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
