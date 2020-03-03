<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>

<title>Create a Ticket</title>
<script>
	function valid() {
		if (document.getElementById("ticketDesc").value == "") {
			document.getElementById("error1").innerHTML = "<center>Description required</center>";
			return false;
		} else {
			return true;

		}

	}
</script>
<style>
#error1 {
	color: red;
}
</style>
</head>
<body onload='document.loginForm.j_username.focus();'>
	<h3>Create a Ticket</h3>
	<form:form action="tickets" method="POST" modelAttribute="ticket">

		<table>
			<tr>
				<td>Ticket Description :</td>
				<td><input type='text' name='ticketDesc' id="ticketDesc" /></td>
				<td><p id="error1"></p></td>
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
				<td><input name="submit" type="submit" onclick="return valid()"
					value="Add Ticket" /></td>
			</tr>
		</table>
	</form:form>
	<c:if test="${listTickets.size()!=0}">
		<table class="table" border=1>

			<tr>
				<th scope="col">Ticket Id</th>
				<th scope="col">Ticket Description</th>
				<th scope="col">Ticket Status</th>
				<th scope="col">Ticket Priority</th>
				<th scope="col">Ticket Created By</th>
				<th scope="col">Ticket Created On</th>
				<th scope="col">Edit</th>
			</tr>
			<c:forEach items="${listTickets}" var="ticket">
				<tr>
					<td>${ticket.ticketId}</td>
					<td>${ticket.ticketDesc}</td>
					<td>${ticket.ticketStatus}</td>
					<td>${ticket.priority}</td>
					<td>${ticket.createdBy}</td>
					<td>${ticket.createdDate}</td>
					<td><a href="/ticket/${ticket.ticketId}">Edit Ticket</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:out value="${message}" />

	<br />
	<a href="/logout">Logout</a>
</body>
</html>