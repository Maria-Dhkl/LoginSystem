<%
    //Authenticate the user
    	if(session.getAttribute("username")!= null){
    		response.sendRedirect("home.jsp");
    	}
    %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="Login" method="POST">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username" required></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Login"><a href="registrationForm.jsp">  Register Now!</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
	