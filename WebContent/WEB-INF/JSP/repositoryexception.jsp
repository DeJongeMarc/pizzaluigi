<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Problemen bij ophalen data' />
</c:import>
</head>
<body>
	<c:import url='/WEB-INF/JSP/menu.jsp' />
	<h1>Problemen bij het ophalen van data</h1>
	<img src='<c:url value="/images/datafout.jpg"/>' alt='data fout'>
	<p>
		We kunnen de gevraagde data niet ophalen wegens een technische
		storing.<br> Gelieve de helpdesk te contacteren.
	</p>
</body>
</html>