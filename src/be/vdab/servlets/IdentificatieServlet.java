package be.vdab.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IdentificatieServlet
 */
@WebServlet("/identificatie.htm")
public class IdentificatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/identificatie.jsp";
	private static final int COOKIE_MAXIMUM_LEEFTIJD = 60 /* seconden */ * 30 /* minuten */;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Cookie cookie = new Cookie("gebruikersnaam",
				URLEncoder.encode(request.getParameter("gebruikersnaam"), "UTF-8"));
		cookie.setMaxAge(COOKIE_MAXIMUM_LEEFTIJD);
		response.addCookie(cookie);
		response.sendRedirect(request.getRequestURI());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if ("gebruikersnaam".equals(cookie.getName())) {
					request.setAttribute("gebruikersnaam", URLDecoder.decode(cookie.getValue(), "UTF-8"));
					break;
				}
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
