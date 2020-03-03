<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>

<head>
<title>Create a Ticket</title>

</head>
<body onload='document.loginForm.j_username.focus();'>
	<h3>Create a Ticket</h3>
	<form:form action="success" method="GET" modelAttribute="ticket">

		<table>
			<tr>
				<td><input type='text' name='ticketId'
					value="${ticket.ticketId}" hidden /></td>
			</tr>
			<tr>
				<td>Ticket Description :</td>
				<td><input type='text' name='ticketDesc'
					value="${ticket.ticketDesc}" /><font color="red"> <form:errors
							path="ticketDesc" />
				</font></td>
			</tr>
			<tr>
				<td>Ticket Priority :</td>
				<td><select name="priority">
						<option value="low">Low</option>
						<option value="medium">Medium</option>
						<option value="high">High</option>
				</select></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="Update Ticket" /></td>
			</tr>
		</table>
	</form:form>
	<c:out value="${message}" />

	<br />
	<a href="/logout">Logout</a>
</body>
</html>