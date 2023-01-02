<%
    //Authenticate the user
    	if(session.getAttribute("authenticationCode")== null){
    		response.sendRedirect("registrationForm.jsp");
    	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Please enter the verification code here.</p>
	<form action="VerifyEmail" method="post">
		<input type="text" name = "authenticationCode">
		<input type="submit" value="Submit">
	</form>
</body>
</html>