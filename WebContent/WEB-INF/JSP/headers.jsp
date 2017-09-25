<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value="Pizza's" />
</c:import>
</head>
<body>
	<c:import url='/WEB-INF/JSP/menu.jsp' />
	<dl>
		<c:forEach var='h' items='${headers}'>
			<dt>${h.key}</dt>
			<dd>${h.value}</dd>
		</c:forEach>
	</dl>
</body>
</html>