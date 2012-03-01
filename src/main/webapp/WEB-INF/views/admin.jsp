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

	<form method="get">
		<input name="query" id="query" /> <input type="submit" value="Søk" />
	</form>
	<%-- 	<form method="post"> --%>
	<!-- 			<table> -->
	<!-- 			<tr> -->
	<!-- 				<th>Fornavn</th> -->
	<!-- 				<th>Etternavn</th> -->
	<!-- 				<th>email</th> -->

	<%-- 				<c:forEach items="${listOfRoles}" var="roles"> --%>
	<%-- 					<th><c:out value="${roles.trimedRoleName} " /></th> --%>
	<%-- 				</c:forEach> --%>
	<!-- 			</tr> -->
	<!-- 				</table> -->
	<form method="post">
		<c:forEach items="${studentList}" var="student">
			<table>
				<tr>
					<td><c:out value="${student.id}" /></td>
					<td><c:out value="${student.firstName}" /></td>
					<td><c:out value="${student.lastName}" /></td>
					<td><c:out value="${student.email}" /></td>

					<c:choose>
						<c:when test="${!student.roles.isEmpty()}">
							<c:forEach items="${listOfRoles}" var="roles">
								<%
									int counter = 0;
								%>
								<c:forEach items="${student.roles}" var="studRoles">
									<%
										if (counter == 1) {

																break;
															}
									%>
									<c:if test="${studRoles.id == roles.id}">
										<td><input type="checkbox" name=${student.email}Roles
											checked="checked"> <c:out
												value="${roles.trimedRoleName}" /></td>
										<%
											counter++;
										%>
									</c:if>
									
								</c:forEach>
									<%
										if (counter == 0) {

									out.print("<td><input type='checkbox' name=${student.email}Roles>" +
																		"<c:out value='${roles.trimedRoleName} ' /></td>");
															}
									%>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${listOfRoles}" var="roles">
								<td><input type="checkbox" name=${student.email}Roles>
									<c:out value="${roles.trimedRoleName} " /></td>
							</c:forEach>
						</c:otherwise>
					</c:choose>


				</tr>

			</table>
			<input type="button" value="Oppdater Roller" id="${student.email}" />
		</c:forEach>
	</form>


	<%-- 	</form> --%>


</body>
</html>

