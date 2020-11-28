<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Dashboard</title>
</head>
<script>
function ToCandidate() {
  location.replace("StandCandidate.jsp")
}
</script>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if(session.getAttribute("user_id")== null)
		response.sendRedirect("adminLogin.jsp");
%>
 <div align = "center">
 	<h1>Hello Student: ${ username }</h1>
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
 	</table>
 </div>
 <br>
 <div align = "center">
 	<button onclick="ToCandidate()">stand as candidate</button>
 </div>
</body>
</html>