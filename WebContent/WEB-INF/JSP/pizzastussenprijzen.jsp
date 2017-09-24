<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Pizzas tussen prijzen' />
</c:import>
</head>
<body>
	<c:import url='/WEB-INF/JSP/menu.jsp' />
	<h1>Pizza's tussen prijzen</h1>
	<form>
		<label>Van prijs<span>${fouten.van}</span> <input name='van' value="${param.van}"
			autofocus type="number" min="0" required>
		</label> 
		<label>Tot prijs<span>${fouten.tot}</span> 
		<input name='tot' value="${param.tot}" type="number" min="0" required>
		</label> 
		<input type='submit' value='Zoeken'>
	</form>
	<c:if test='${not empty pizzas}'>
		<ul class='zebra'>
			<c:forEach var='pizza' items='${pizzas}'>
				<li><c:out value='${pizza.naam}' /> ${pizza.prijs}&euro;</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test='${not empty param and empty fouten and empty pizzas}'>
		<div class='fout'>Geen pizza’s gevonden</div>
	</c:if>
</body>
</html>