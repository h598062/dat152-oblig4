package no.hvl.dat152.obl4.blog.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.hvl.dat152.obl4.blog.Constants;

/**
 * @author tdoy
 */

/**
 * Servlet implementation class SSOForwarder
 */
@WebServlet("/sso")
public class SSOForwarder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SSOForwarder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientId = request.getParameter("client_id");
		
		String scope = request.getParameter("scope");
		String response_type = request.getParameter("response_type");
		String state = request.getParameter("state");
		String redirect_uri = request.getParameter("redirect_uri");
		
		String ssourl = Constants.IDP_AUTH_ENDPOINT+"?client_id="+clientId+"&scope="+scope+"&response_type="+response_type+"&state="+state+
				"&redirect_uri="+redirect_uri;

		response.sendRedirect(ssourl);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
