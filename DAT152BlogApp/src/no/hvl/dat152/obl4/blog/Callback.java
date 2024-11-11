package no.hvl.dat152.obl4.blog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.hvl.dat152.obl4.blog.tokens.KeyCloakJwt;
import no.hvl.dat152.obl4.blog.tokens.KeyCloakTokenHandler;

import java.io.IOException;

/**
 * @author tdoy
 * Servlet implementation class Callback
 */
@WebServlet("/callback")
public class Callback extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Callback() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
			ServletException,
			IOException {

		String idp_response = "";
		try {

			String code = request.getParameter("code");
			// String state = request.getParameter("state");		// not used here!

			// use the authorization_code to request for authentication token (JWT)
			// Authorization header contains the client_id and the client_secret (optional)

			String token_endpoint_url_data = "grant_type=authorization_code&code=" + code + "&redirect_uri=" +
			                                 Constants.SP_CALLBACK_ADDRESS;

			// we will use a direct back channel to submit the request to the IdP			
			HttpClient httpChannel = new HttpClient(Constants.IDP_TOKEN_ENDPOINT);

			idp_response = httpChannel.requestToken(Constants.CLIENT_ID + ":" + Constants.CLIENT_SECRET,
					token_endpoint_url_data);

			// keycloak
			KeyCloakTokenHandler keycloakHandler = new KeyCloakTokenHandler(idp_response.trim());
			KeyCloakJwt          keyCloakToken   = keycloakHandler.getKeyCloakjwt();

			System.out.println("access_token = " + keyCloakToken.getAccess_token());

			request.getSession().setAttribute("id_token", keyCloakToken.getAccess_token());

			// redirect the user to the blog controller upon successful checks.
			request.getRequestDispatcher("blogservlet?newtoken=yes").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("message", "\nSSO login failed!\n" + idp_response);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
			ServletException,
			IOException {

		doGet(request, response);
	}


}
