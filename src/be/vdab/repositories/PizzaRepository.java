package be.vdab.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import be.vdab.entities.Pizza;

public class PizzaRepository {
	private static final Map<Long, Pizza> PIZZAS = new ConcurrentHashMap<>();
	static {
		PIZZAS.put(12L, new Pizza(12, "Prosciutto", BigDecimal.valueOf(4), true));
		PIZZAS.put(14L, new Pizza(14, "Margehrita", BigDecimal.valueOf(5), false));
		PIZZAS.put(17L, new Pizza(17, "Calzone", BigDecimal.valueOf(4), false));
		PIZZAS.put(23L, new Pizza(23, "Fungi & Olive", BigDecimal.valueOf(5), false));
	}

	public List<Pizza> findAll() {
		return new ArrayList<>(PIZZAS.values());
	}

	public Optional<Pizza> read(long id) {
		Pizza pizza = PIZZAS.get(id);
		return Optional.ofNullable(pizza);
	}

	public List<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot) {
		return PIZZAS.values().stream().filter(pizza -> pizza.getPrijs().compareTo(van) >= 0 && pizza.getPrijs().compareTo(tot) <= 0).collect(Collectors.toList());
	}

	public void create(Pizza pizza) {
		pizza.setId(Collections.max(PIZZAS.keySet()) + 1);
		PIZZAS.put(pizza.getId(), pizza);
	}
}
