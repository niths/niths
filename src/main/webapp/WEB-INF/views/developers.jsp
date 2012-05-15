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
<script>
  <%@include file="assets/javascripts/jquery-1.7.2.min.js" %>
</script>
<script>
  <%@include file="assets/javascripts/developers.js" %>
</script>
</head>
<body>
	<section id="container">
		<h1>Manage developers</h1>
		<section id="content">
			<br /> <br />
			<c:forEach items="${allDevelopers}" var="developer"
				varStatus="loopStatus">
				<div class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">

					<form method="post">
						<input type="hidden" value="${developer.id}" id=developerId
							name="developerId">
						<div class="developer">
							Name: ${developer.name} email: ${developer.email} <input
								type="checkbox" name=devs
								<c:if test="${developer.enabled}">checked="checked"</c:if>
								id="devs" value="${developer.id}">
						</div>
						<c:forEach items="${developer.apps}" var="application">

							<div class="application">
								Application: ${application.title} <input type="checkbox"
									name=apps
									<c:if test="${application.enabled}">checked="checked"</c:if>
									id="apps" value="${application.id}">
							</div>
						</c:forEach>

						<div class="right">
							<input type="submit" value="Update developer" />
						</div>
					</form>
				</div>
				<form method="post" action="developer/reset">
				<input type="hidden" value="${developer.id}" id=developerId
							name="developerId">
					<div class="right">
							<button class="reset-developer">
							  Reset developer key + token
							</button>
						</div>
				</form>
			</c:forEach>

		</section>
		<br style="clear: both" />
		<div id="footer">&copy; NITHs 2012</div>
	</section>
</body>
</html>

