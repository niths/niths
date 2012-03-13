<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

#content input[type="submit"]{
	padding: 3px;
	margin-bottom: 2px;
	margin-right: 5px;
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

.list{
	list-style: none;
	padding: 5px;
	margin: 3px;
	
}

</style>
</head>
<body>


	<div id="container">
		<h1>NITHs Admin Panel</h1>
		<div id="content">
			<div id="searcher">
				<form method="get" >
					<input type="radio" value="firstName" name="columnName" checked="checked" id="r1"><label for="r1">Fornavn</label>
					<input type="radio" value="lastName" name="columnName"  id="r2"> <label for="r2">Etternavn</label>
					<input type="radio" value="email" name="columnName"  id="r3"> <label for="r3">Email</label>
					<input name="query" id= "query" /> 
					<input type="submit" value="Søk" />
				</form>
			</div>
			<hr />
			
			<c:if test="${exception != null}" >
				is null yeah
			</c:if>
				<c:forEach items="${studentList}" var="student">
					<form method="post">
						<input type="hidden" value="${student.id}" id=studentId
							name="studentId"> <b> <c:out value="${student.id}" />
							<c:out value="${student.firstName}" /> <c:out
								value="${student.lastName}" /> <c:out value="${student.email}" />
						</b> <br />
						
						<c:choose>
							<c:when test="${!student.roles.isEmpty()}">
							
							<ul class="list">
								<c:forEach items="${listOfRoles}" var="roles">
								<li>
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
								</li>
								</c:forEach>
							</ul>
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
					</form>
					<form action="delete" method="post">
						<input type="hidden" value="${student.id}" name="studId" /> <input
							type="submit" value="Slett bruker">
					</form>
					<br />
				</c:forEach>
			</div>
		
		<br style="clear: both" />
		<div id="footer">&copy; NITHs 2012</div>
	</div>
</body>
</html>

