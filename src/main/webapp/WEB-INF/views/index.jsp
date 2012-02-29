<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head><title>Admin panel</title></head>
<body>
	<h1>list of students</h1>
	
	
	<form method="get" >
		<input name="query" id="query" />
		<input type="submit" value="søk" />
	</form>

	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
		</tr>
		<c:forEach items="${studentList}" var="student">
			<tr>
				<td><c:out value="${student.firstName}" /></td>
				<td><c:out value="${student.lastName}" /></td>
				<td><c:out value="${student.email}" />)</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
