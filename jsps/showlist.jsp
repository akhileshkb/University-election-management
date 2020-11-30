<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if(session.getAttribute("user_id")== null)
		response.sendRedirect("studentLogin.jsp");
%>
<div align = "center">
	<div id = "clubdet"><h2>candidate list for - ${voteforclub }</h2><br><br><h3>Choose one Candidate you want to vote</h3></div>
 	<script type="text/javascript">
 		var candlist = ${candlist}
		var n= candlist.length;
		var s = "<form action='MakeVote' method = 'post'>";
		for(var i=0;i<n;i+=2){
			s +="<input type='radio' name = 'voteto' value = \""+candlist[i]+"\" id= \""+candlist[i]+"\" />";
			s +="<label for = \""+candlist[i]+"\" >" +candlist[i+1]+ "(id= " + candlist[i] +")"+ "</label><br>";
		}
		s+="<br><br><input type='submit' value = 'save vote'/></form>";
		//console.log(s);
		document.getElementById("clubdet").innerHTML += s;
	</script>
</div>
</body>
</html>