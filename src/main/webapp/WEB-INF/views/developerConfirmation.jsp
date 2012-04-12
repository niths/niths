<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enable developer confirmation</title>
<style type="text/css">
<%@ include file ='assets/stylesheets/admin.css'%>
</style>
<script type="text/javascript">
	
<%@include file='assets/javascripts/admin.js'%>
	
</script>
</head>
<body>
	<section id="container">
		<h1>Developer confirmation</h1>
		<section id="content">
			<br />
			<br />
			<c:choose>
				<c:when test="${error == null}">
					<p><b><c:out value="${msg}" /></b></p>
					<br />
					<br />
					<p>In every request from any of your apps, place:</p>
					<br />
					<p><b><c:out value="${key}" /></b></p>
					<br />
					<p><b><c:out value="${token}" /></b></p>
					<br />
					<p>in the header.</p>
					
				</c:when>
				<c:otherwise>
					<p>Very sorry, but an error occured</p>
					<br />
					<p>Error report: <b>${error}</b></p>
					<br />
					<p>You sure you sendt in the correct token?</p>
				</c:otherwise>
			</c:choose>



		</section>
		<br style="clear: both" />
		<div id="footer">&copy; NITHs 2012</div>
	</section>
</body>
</html>

