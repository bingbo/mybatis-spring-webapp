<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - Employee List</title>
<%@ include file="../../../header.jsp"%>
</head>
<body>
	<center>
		<table>
			<tr>
				<th>id</th>
				<th>username</th>
				<th>password</th>
				<th>email</th>
			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
				    <td><c:out value="${user.id}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.password}" /></td>
					<td><c:out value="${user.email}" /></td>
				</tr>
			</c:forEach>
		</table>
		

	</center>
</body>
<%@ include file="../../../footer.jsp"%>
</html>