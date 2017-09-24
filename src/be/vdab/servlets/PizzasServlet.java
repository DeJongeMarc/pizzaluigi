package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Pizza;
import be.vdab.repositories.PizzaRepository;

@WebServlet("/pizzas.htm")
public class PizzasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzas.jsp";
	private static final String PIZZAS_REQUESTS = "pizzasRequests";
	private final PizzaRepository pizzaRepository = new PizzaRepository();

	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute(PIZZAS_REQUESTS, new AtomicInteger());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((AtomicInteger) this.getServletContext().getAttribute(PIZZAS_REQUESTS)).incrementAndGet();
		request.setAttribute("pizzas", pizzaRepository.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
