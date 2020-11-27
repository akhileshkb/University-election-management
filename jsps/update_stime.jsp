<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>change start time</title>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if(session.getAttribute("user_id")== null)
		response.sendRedirect("adminLogin.jsp");
%>
<form action = "Updatestime" method = "post">
current start time is: ${stime}
	<table>
		<tr>
			<td>enter new start time(yy-mm-dd hour:min:sec)</td>
			<td><input type = "text" name = "newt"/></td>
		</tr>
	</table>
	<input type = "submit" value ="save change">
</form>
</body>
</html>