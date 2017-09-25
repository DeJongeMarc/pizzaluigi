package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import be.vdab.entities.Pizza;
import be.vdab.repositories.PizzaRepository;
import be.vdab.util.StringUtils;

/**
 * Servlet implementation class PizzaToevoegenServlet
 */
@MultipartConfig
@WebServlet("/pizzas/toevoegen.htm")
public class PizzaToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzatoevoegen.jsp";
	private static final String REDIRECT_URL = "/pizzas.htm";
	private final PizzaRepository pizzaRepository = new PizzaRepository();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!Pizza.isNaamValid(naam)) {
			fouten.put("naam", "verplicht");
		}
		String prijsString = request.getParameter("prijs");
		BigDecimal prijs = null;
		if (StringUtils.isBigDecimal(prijsString)) {
			prijs = new BigDecimal(prijsString);
			if (!Pizza.isPrijsValid(prijs)) {
				fouten.put("prijs", "tik een positief getal");
			}
		} else {
			fouten.put("prijs", "tik een getal");
		}
		Part fotoPart = request.getPart("foto");
		boolean fotoIsOpgeladen = fotoPart != null && fotoPart.getSize() != 0;
		if (fotoIsOpgeladen && !fotoPart.getContentType().contains("jpeg")) {
			fouten.put("foto", "geen JPEG foto");
		}
		if (fouten.isEmpty()) {
			boolean pikant = "pikant".equals(request.getParameter("pikant"));
			Pizza pizza = new Pizza(naam, prijs, pikant);
			pizzaRepository.create(pizza);
			if (fotoIsOpgeladen) {
				String pizzaFotosPad = this.getServletContext().getRealPath("/pizzafotos");
				fotoPart.write(pizzaFotosPad + '/' + pizza.getId() + ".jpg");
			}
			response.sendRedirect(request.getContextPath() + REDIRECT_URL);
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
