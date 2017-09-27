package be.vdab.repositories;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.vdab.entities.Pizza;

public class PizzaRepository extends AbstractRepository {
	private static final String BEGIN_SELECT = "select id, naam, prijs, pikant from pizzas ";
	private static final String FIND_ALL = BEGIN_SELECT + "order by naam";
	private static final String READ = BEGIN_SELECT + "where id=?";
	private static final String FIND_BY_PRIJS_BETWEEN = BEGIN_SELECT + "where prijs between ? and ? order by prijs";
	private static final String CREATE = "insert into pizzas(naam, prijs, pikant) values (?, ?, ?)";

	public List<Pizza> findAll() {
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
			List<Pizza> pizzas = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
				while (resultSet.next()) {
					pizzas.add(resultSetRijNaarPizza(resultSet));
				}
			}
			connection.commit();
			return pizzas;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	private Pizza resultSetRijNaarPizza(ResultSet resultSet) throws SQLException {
		return new Pizza(resultSet.getLong("id"), resultSet.getString("naam"), resultSet.getBigDecimal("prijs"),
				resultSet.getBoolean("pikant"));
	}

	public Optional<Pizza> read(long id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(READ)) {
			Optional<Pizza> pizza;
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					pizza = Optional.of(resultSetRijNaarPizza(resultSet));
				} else {
					pizza = Optional.empty();
				}
			}
			connection.commit();
			return pizza;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	public List<Pizza> findByPrijsBetween(BigDecimal van, BigDecimal tot) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_PRIJS_BETWEEN)) {
			List<Pizza> pizzas = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setBigDecimal(1, van);
			statement.setBigDecimal(2, tot);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					pizzas.add(resultSetRijNaarPizza(resultSet));
				}
			}
			connection.commit();
			return pizzas;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	public void create(Pizza pizza) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement( CREATE, Statement.RETURN_GENERATED_KEYS)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); connection.setAutoCommit(false);
			      statement.setString(1, pizza.getNaam());
			      statement.setBigDecimal(2, pizza.getPrijs());
			      statement.setBoolean(3, pizza.isPikant());
			      statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()) { resultSet.next();
			pizza.setId(resultSet.getLong(1));
			}
			      connection.commit();
			    }
			catch (SQLException ex) {
			throw new RepositoryException(ex);
			}
	}
}
