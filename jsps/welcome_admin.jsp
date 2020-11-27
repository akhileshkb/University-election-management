<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>admin_dashboard</title>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if(session.getAttribute("user_id")== null)
		response.sendRedirect("adminLogin.jsp");
%>
 <div align = "center">
 	<h1>Hello Administrator: ${ username }</h1>
 	<form action = "Logout" method = "post">
 		<input type = "submit" title = "click hear to end the current session" value = "LogOut"/>
 	</form>
 	<h3>Here are current election details</h3>
 	<table border = "1" width = 75%>
 		<tr >
 			<th> start time</th>
 			<th> end time</th>
 			<th> guideline</th>
 		</tr>
 		<tr>
 			<td> ${stime }</td>
 			<td> ${etime }</td>
 			<td> ${guideline}</td>		
 		</tr>
 		<tr>
 			<td><a href = "update_stime.jsp">update start date</a>
 			<td><a href = "update_etime.jsp">update end date</a>
 			<td><a href = "update_guid.jsp">update guideline</a>
 		</tr>
 	</table>
 </div>
</body>
</html>