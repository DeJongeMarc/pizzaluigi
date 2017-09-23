package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StatistiekServlet
 */
@WebServlet("/statistiek.htm")
public class StatistiekServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/statistiek.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
