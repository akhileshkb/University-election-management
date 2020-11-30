<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Dashboard</title>
<script type="text/javascript">
	function setclub(c_id){
		document.getElementById("club"+c_id).value= String(c_id);
		var form = document.getElementById("myform"+c_id);
        form.submit();
	}
	function setclub1(c_id){
		document.getElementById("club1"+c_id).value= String(c_id);
		var form1 = document.getElementById("myform1"+c_id);
        form1.submit();
	}
</script>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if(session.getAttribute("user_id")== null)
		response.sendRedirect("studentLogin.jsp");
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
 	</table><br><br><br>
 	<div id = "clubdet"><h3>Clubs in which you are registered</h3></div>
 	<script type="text/javascript">
		var s = "";
		s+= "<table border = '1' width = 80%><tr><th>club id</th><th>club name</th></tr>";
		var club_data = ${clubdata}
		var n= club_data.length;
		for(var i=0;i<n;i+=2){
			s+="<tr>";
			var c_id = String(club_data[i]);
			s+=("<td>"+c_id+"</td>");
			var c_name= String(club_data[i+1]);
			s+=("<td>"+c_name+"</td>");
			var vote = "<form action='ShowList' id = 'myform"+c_id+"' method = 'post'>";
			vote +=" <input type='hidden' id= 'club"+c_id+"' name = 'standfor1'/><input type= 'submit' value='votenow' onclick='setclub(\""+c_id+"\")'/></form>";
			var stand = "<form action='RegisterCandidate' id = 'myform1"+c_id+"' method = 'post'>";
			stand +=" <input type='hidden' id= 'club1"+c_id+"' name = 'standfor'/><input type= 'submit' value='stand as candidate' onclick='setclub1(\""+c_id+"\")'/></form>";
			s+=("<td>"+vote+"</td><td>"+stand+"</td></tr>");
		}
		s+="</table>";
		//console.log(s);
		document.getElementById("clubdet").innerHTML += s;
	</script>
 </div>
 <br>
</body>
</html>