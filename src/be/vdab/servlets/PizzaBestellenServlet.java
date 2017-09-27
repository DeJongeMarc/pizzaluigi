package be.vdab.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.repositories.PizzaRepository;

/**
 * Servlet implementation class PizzaBestellenServlet
 */
@WebServlet("/pizzas/bestellen.htm")
public class PizzaBestellenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzabestellen.jsp";
	private static final String MANDJE = "mandje";
	private final transient PizzaRepository pizzaRepository = new PizzaRepository();
	
	@Resource(name = PizzaRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		pizzaRepository.setDataSource(dataSource);
		}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("allePizzas", pizzaRepository.findAll());
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE);
			if (mandje != null) {
				request.setAttribute("pizzasInMandje",
						mandje.stream().map(id -> pizzaRepository.read(id))
								.filter(optionalPizza -> optionalPizza.isPresent())
								.map(optionalPizza -> optionalPizza.get()).collect(Collectors.toList()));
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterValues("id") != null) {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE);
			if (mandje == null) {
				mandje = new LinkedHashSet<>();
			}
			mandje.addAll(Arrays.stream(request.getParameterValues("id")).map(id -> Long.parseLong(id))
					.collect(Collectors.toSet()));
			session.setAttribute(MANDJE, mandje);
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
}
