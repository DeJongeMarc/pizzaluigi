package be.vdab.servlets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Pizza;
import be.vdab.repositories.PizzaRepository;

@WebServlet("/pizzas.htm")
public class PizzasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzas.jsp";
	private static final String PIZZAS_REQUESTS = "pizzasRequests";
	private final transient PizzaRepository pizzaRepository = new PizzaRepository();
	private String pizzaFotosPad;

	@Resource(name = PizzaRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		pizzaRepository.setDataSource(dataSource);
	}

	@Override
	public void init() throws ServletException {
		this.getServletContext().setAttribute(PIZZAS_REQUESTS, new AtomicInteger());
		pizzaFotosPad = this.getServletContext().getRealPath("/pizzafotos");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((AtomicInteger) this.getServletContext().getAttribute(PIZZAS_REQUESTS)).incrementAndGet();
		List<Pizza> pizzas = pizzaRepository.findAll();
		request.setAttribute("pizzas", pizzas);
		request.setAttribute("pizzaIdsMetFoto",
				pizzas.stream().filter(pizza -> Files.exists(Paths.get(pizzaFotosPad, pizza.getId() + ".jpg")))
						.map(pizza -> pizza.getId()).collect(Collectors.toList()));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
