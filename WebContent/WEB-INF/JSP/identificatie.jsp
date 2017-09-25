<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Identificatie' />
</c:import>
</head>
<body>
	<c:import url='/WEB-INF/JSP/menu.jsp' />
	<h1>Identificatie</h1>
	<form method='post'>
		<label>Naam<input name='gebruikersnaam'
			value='${gebruikersnaam}' autofocus></label> <input type='submit'
			value='Onthoud me'>
	</form>
</body>
</html>