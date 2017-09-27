<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Pagina niet gevonden' />
</c:import>
</head>
<body>
	<c:import url='/WEB-INF/JSP/menu.jsp' />
	<h1>Pagina niet gevonden</h1>
	<img src='<c:url value="/images/fout.jpg"/>' alt='fout'>
	<p>De pagina die u zocht bestaat niet op onze website.</p>
</body>
</html>
