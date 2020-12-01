<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Results</title>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if(session.getAttribute("user_id")== null)
		response.sendRedirect("studentLogin.jsp");
%>
<div id = "clubdet"><h3>Winners List</h3></div>
 	<script type="text/javascript">
		var s = "";
		s+= "<table border = '1' width = 80%><tr><th>Student Id</th><th>Student Name</th><th>club name</th><th>Margin</th></tr>";
		var winner_data = ${winnerdata}
		var n= winner_data.length;
		for(var i=0;i<n;i+=4){
			s+="<tr>";
			var c1_id = String(winner_data[i]);
			s+=("<td>"+c1_id+"</td>");
			var c_id = String(winner_data[i+1]);
			s+=("<td>"+c_id+"</td>");
			var c_name= String(winner_data[i+2]);
			s+=("<td>"+c_name+"</td>");
			var d_name= String(winner_data[i+3]);
			s+=("<td>"+d_name+"</td>");
			s+="<tr>";
			/* var vote = "<form action='ShowList' id = 'myform"+c_id+"' method = 'post'>";
			vote +=" <input type='hidden' id= 'club"+c_id+"' name = 'standfor1'/><input type= 'submit' value='votenow' onclick='setclub(\""+c_id+"\")'/></form>";
			var stand = "<form action='RegisterCandidate' id = 'myform1"+c_id+"' method = 'post'>";
			stand +=" <input type='hidden' id= 'club1"+c_id+"' name = 'standfor'/><input type= 'submit' value='stand as candidate' onclick='setclub1(\""+c_id+"\")'/></form>";
			s+=("<td>"+vote+"</td><td>"+stand+"</td></tr>"); */
		}
		//s+="</table>";
		//console.log(s);
		document.getElementById("clubdet").innerHTML += s;
	</script>	
</body>
</html>