<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin panel</title>
</head>
<body>

<h1>Admin Panel</h1>
	<form method="get">
	<input type="radio" value="firstName" name="columName" checked="checked"> Fornavn
	<input type="radio" value="lastName" name="columName"> Etternavn
	<input type="radio" value="email" name="columName">Email
		<input name="query" id="query" /> <input type="submit" value="Søk" />
	</form>
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
							<%
								boolean temp = false;
												pageContext.setAttribute("temp", temp);
							%>
							<c:forEach items="${student.roles}" var="studRoles">
								<%
									if (temp) {
															break;
														}
								%>
								<c:if test="${studRoles.id == roles.id}">
									<input type="checkbox" name=checkedRoles checked="checked"
										id="checkedRoles" value="${roles.id}">
									<c:out value="${roles.trimedRoleName}" />
									<%
										temp = true;
																pageContext.setAttribute("temp", temp);
									%>
								</c:if>

							</c:forEach>

							<c:if test="${!temp}">
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
		<form action="/niths/admin/delete" method="post">
			<input type="hidden" value="${student.id}" name="studId"/>
			 <input type="submit" value="SlettBruker">
		</form>

	</c:forEach>
</body>
</html>

