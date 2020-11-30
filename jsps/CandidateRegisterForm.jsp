<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>candidate registration form</title>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if(session.getAttribute("user_id")== null)
		response.sendRedirect("studentLogin.jsp");
%>
	<div align = "center">
		<h2> Candidate registration form</h2>
		<table>
			<tr>
				<td>Student id:</td>
				<td>${user_id }</td>
			</tr>
			<tr>
				<td>Student name:</td>
				<td>${username }</td>
			</tr>
			<tr>
				<td>Standing for:</td>
				<td>${standforclub }</td>
			</tr>
		</table>
		<h3>Enter your achievements in the text-box</h3>
		<form action='Registration' method = 'post'>
			<textarea rows="10" cols="40" name = "ahievement"></textarea>
			<br><br>
			<input type = "submit" value = "submit form"/>
		</form>
	</div>
</body>
</html>