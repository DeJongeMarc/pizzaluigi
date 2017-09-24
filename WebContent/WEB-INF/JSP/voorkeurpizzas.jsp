<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value="Voorkeurpizza’s" />
</c:import>
</head>
<body>
	<c:import url='/WEB-INF/JSP/menu.jsp' />
	<h1>Voorkeurpizza's</h1>
	<form>
		<ul class='zonderbolletjes'>
			<c:forEach var='pizza' items='${pizzas}'>
				<li><label><input type='checkbox' name='id'
						value='${pizza.id}'> <c:out value='${pizza.naam}' /></label></li>
			</c:forEach>
		</ul>
		<input type='submit' value='Toon mijn keuzes'>
	</form>
	<c:if test='${not empty voorkeurPizzas}'>
		<h1>Je voorkeurpizza's</h1>
		<ul class='zebra'>
			<c:forEach var='pizza' items='${voorkeurPizzas}'>
				<li><c:out value='${pizza.naam}' /></li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>