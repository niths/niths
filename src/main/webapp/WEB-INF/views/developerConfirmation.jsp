<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enable developer confirmation</title>
<style type="text/css">
<%@ include file='assets/stylesheets/admin.css'%>
</style>
<script type="text/javascript">
	
<%@include file='assets/javascripts/jquery.js'%>
	
<%@include file='assets/javascripts/admin.js'%>
	
</script>
</head>
<body>
	<section id="container">
		<h1>Developer confirmation</h1>
		<section id="content">
		<br />
		<p>Your </p>
		<c:out value="${token}" />
		
		</section>
		<br style="clear: both" />
		<div id="footer">&copy; NITHs 2012</div>
	</section>
</body>
</html>

