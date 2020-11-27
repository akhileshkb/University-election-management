<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>administrator login</title>
</head>
<body>
<div align = "center">
	<h1>Admin login page</h1>
	<br><br>
	<button onclick = "location.href = 'home.html' ">home</button>
	<br><br>
	<form action= "ValidateAdmin" method = "post">
		<table style = "border-spacing : 15px">
			<tr>
				<td>ID:</td>
				<td><input type = "text" name = "id" title= "enter your student id" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type = "password" name="password" /></td>
			</tr>
			
		</table>
		<input type= "submit" value = "login"/>
	</form>
	
</div>
</body>
</html>