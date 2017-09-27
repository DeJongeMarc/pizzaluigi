<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%> 
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title="Bestellen"/>
</head>
<body>
	<vdab:menu />
	<h1>Pizza's bestellen</h1>
	<c:if test='${not empty allePizzas}'>
		<h2>Assortiment</h2>
		<form method='post' id='toevoegform'>
			<ul class='zonderbolletjes'>
				<c:forEach var='pizza' items='${allePizzas}'>
					<li><label><input type='checkbox' name='id'
							value='${pizza.id}'> <c:out value='${pizza.naam}' /></label></li>
				</c:forEach>
			</ul>
			<input type='submit' value='Toevoegen aan mandje' id='toevoegknop'>
		</form>
	</c:if>
	<c:if test='${not empty pizzasInMandje}'>
		<h2>Uw mandje</h2>
		<ul>
			<c:forEach var='pizza' items='${pizzasInMandje}'>
				<li><c:out value='${pizza.naam}' /></li>
			</c:forEach>
		</ul>
	</c:if>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			if (! navigator.cookieEnabled) {
				alert("Dit werkt enkel als cookies aanstaan");
				return false;
			}
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>
