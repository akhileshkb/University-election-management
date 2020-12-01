<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stand as Candidate</title>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if(session.getAttribute("user_id")== null)
		response.sendRedirect("studentLogin.jsp");
%>
<div align = "center">
<br><br>
	<button onclick = "location.href = 'dashboard.jsp' ">dashboard</button>
<br><br>
</div>
<div align = "center"> 
<form action = "RegisterCandidate" method = "post">
	<table style = "border-spacing : 15px">
			<tr>
				<td>Club ID:</td>
				<td><input type = "text" id = "club_id" name = "club_id" /></td>
			</tr>

			<tr>
				<td>Achievement:</td>
				<td><textarea rows = "8" cols = "30" name = "achievement" id = "achievement">
				</textarea>
				</td>
			</tr>
	</table>
			
	
	<input type = "submit" value ="Register as Candidate">
</form>
</div>
</body>
</html>