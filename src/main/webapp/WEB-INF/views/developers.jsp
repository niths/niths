<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage developers</title>
<style type="text/css">
<%@include file='assets/stylesheets/admin.css'%>
</style>
<script type="text/javascript">
	
<%@include file='assets/javascripts/admin.js'%>
	
</script>
</head>
<body>
	<section id="container">
		<h1>Manage developers</h1>
		<section id="content">
			<br /> <br />
				<c:forEach items="${allDevelopers}" var="developer" varStatus="loopStatus">
				<div class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
					<p>
					Name: ${developer.name} email: ${developer.email} <br />
					</p>
					
					<c:forEach items="${developer.apps}" var="application">
								Application: ${application.title} <br />
					</c:forEach> 
						
				</div>
				</c:forEach>



		</section>
		<br style="clear: both" />
		<div id="footer">&copy; NITHs 2012</div>
	</section>
</body>
</html>

