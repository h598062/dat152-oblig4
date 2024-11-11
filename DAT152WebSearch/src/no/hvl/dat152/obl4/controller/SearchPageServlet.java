package no.hvl.dat152.obl4.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.hvl.dat152.obl4.database.AppUser;
import no.hvl.dat152.obl4.database.SearchItem;
import no.hvl.dat152.obl4.database.SearchItemDAO;
import no.hvl.dat152.obl4.util.Role;

@WebServlet("/searchpage")
public class SearchPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (RequestHelper.isLoggedIn(request)) {
			
			AppUser authUser = (AppUser) request.getSession().getAttribute("user");
			
			List<SearchItem> top5history = new ArrayList<>();
			
			if(authUser.getRole().equals(Role.ADMIN.toString())) {
				
				SearchItemDAO searchItemDAO = new SearchItemDAO();
				top5history = searchItemDAO.getSearchHistoryLastFive();
			}
			
			request.setAttribute("top5history", top5history);

			request.getRequestDispatcher("searchpage.jsp").forward(request,
					response);

		} else {
			request.getSession().invalidate();
			request.getRequestDispatcher("index.jsp").forward(request,
					response);
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
