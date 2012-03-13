<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin panel</title>
<!-- <link rel="stylesheet" href="../stylesheet/main.css" type="text/css"/> -->
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	font-family: verdana;
	font-size: 10pt;
}

body {

	background-repeat: repeat-x;
	background-position: top;
	background-attachment: fixed;
	background-color: #CCCCCC;
}

#container {
	margin-left: auto;
	margin-right: auto;
	margin-top: 20px;
	margin-bottom: 20px;
	width: 900px;
	display: block;
	background-color: #FFF;
	padding: 20px;
	-webkit-border-radius: 20px;
	-moz-border-radius: 20px;
	border-radius: 20px;
}

#content a,#content visited {
	text-decoration: none;
}

#content a:hover {
	text-decoration: underline;
}

h1 {
	font-size: 20pt;
 	color: #292929;
	font-weight: bold;
	margin: 0 0 10px 0;
}

h2 {
	margin-top: 15px;
	font-size: 13pt;
	color: #292929;
}

.tagline {
	font-style: italic;
}

#menu {
	margin: 0 0 10px 0;
	display: block;
	clear: both;
	height: auto;
	padding: 2px 2px;
	background-color: #9FCBE3;
}

#menu a,#menu visited {
	text-decoration: none;
	padding: 2px 9px;
	margin: 1px 2px 1px 2px;
	color: #292929;
	font-weight: bold;
	font-size: 9pt;
}

#menu a:hover {
	background-color: #CADDE8;
}

#menu form {
	float: right;
}

#searchFormErrorList,#searchFormErrorList li {
	list-style-type: none;
}

#searchFormErrorList {
	display: block;
	clear: both;
	width: 100%;
	background-color: #D98B93;
}

#searcher input {
	background-color: #FFF;
	padding: 3px;
	border: 1px solid #c0c0c0;
	margin-bottom: 2px;
	margin-right: 5px;
}

#searcher input[type="radio"] {
	margin-right: 1px;
}

#searcher label {
	margin-right: 5px;
}

#technologyForm label {
    width: 75px;
    float: left;
}
</style>
</head>
<body>
	<div id="container">
		<h1>NITHs Admin Panel</h1>
		<div id="content">
			<div id="searcher">
				<form method="get">
					<input type="radio" value="firstName" name="columnName"
						checked="checked"> Fornavn <input type="radio"
						value="lastName" name="columnName"> Etternavn <input
						type="radio" value="email" name="columnName">Email <input
						name="query" id="query" /> <input type="submit" value="Søk" />
				</form>
			</div>
			<hr />
			<c:forEach items="${studentList}" var="student">
				<form method="post">
					<input type="hidden" value="${student.id}" id=studentId
						name="studentId">
					<div>
						<c:out value="${student.id}" />
						<c:out value="${student.firstName}" />
						<c:out value="${student.lastName}" />
						<c:out value="${student.email}" />
						<c:choose>
							<c:when test="${!student.roles.isEmpty()}">
								<c:forEach items="${listOfRoles}" var="roles">
									<c:set value="false" var="temp" />
									<c:forEach items="${student.roles}" var="studRoles">
										<c:choose>
											<c:when test="${temp == 'true'}">
												<!-- do nothing -->
											</c:when>
											<c:otherwise>
												<c:if test="${studRoles.id == roles.id}">
													<input type="checkbox" name=checkedRoles checked="checked"
														id="checkedRoles" value="${roles.id}">
													<c:out value="${roles.trimedRoleName}" />
													<c:set value="true" var="temp" />
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${temp == 'false'}">
										<input type="checkbox" name=checkedRoles id="checkedRoles"
											value="${roles.id}">
										<c:out value="${roles.trimedRoleName}" />
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach items="${listOfRoles}" var="roles">
									<input type="checkbox" name=checkedRoles id="checkedRoles"
										value="${roles.id}">
									<c:out value="${roles.trimedRoleName}" />
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<input type="submit" value="Oppdater Roller" />
					</div>
				</form>
				<form action="delete" method="post">
					<input type="hidden" value="${student.id}" name="studId" /> <input
						type="submit" value="Slett bruker">
				</form>
			</c:forEach>
		</div>
		<br style="clear: both" />
		<div id="footer">&copy; NITHs 2012</div>
	</div>
</body>
</html>

