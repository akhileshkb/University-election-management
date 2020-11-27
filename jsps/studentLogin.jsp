<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>student login</title>
</head>
<body>
	<form action= "validate-student" method = "post">
		<table>
			<tr>
				<td>ID:</td>
				<td><input type = "text" name = "id" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type = "password" name="password" /></td>
			</tr>
		</table>
	</form>
</body>
</html>