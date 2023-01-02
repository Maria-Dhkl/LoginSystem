<%
    //Authenticate the user
    	if(session.getAttribute("username")== null){
    		response.sendRedirect("index.jsp");
    	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.users.StartConnection" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User</title>
</head>
<body>
<%!ResultSet rs; %>
	<% 
		int id = Integer.parseInt(request.getParameter("id"));
		try{
			Connection conn = new StartConnection().getConnection();
			PreparedStatement stmt=conn.prepareStatement("SELECT * FROM tbl_user WHERE id=?");  
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
	%>
	<form action="Edit" method="POST">
		<table>
			<tr>
				<td><input type="hidden" name="id" value="<%=rs.getInt("id")%>"></td>
			</tr>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username" value="<%=rs.getString("username")%>"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="email" name="email" value="<%=rs.getString("email")%>"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" value="<%=rs.getString("password")%>"></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" value="<%=rs.getString("address")%>"></td>
			</tr>
			<tr>
				<td>Contact Number:</td>
				<td><input type="number" name="phoneNumber" value="<%=rs.getString("phoneNumber")%>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form>
</body>
</html>