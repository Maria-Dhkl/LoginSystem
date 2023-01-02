<%
    //Authenticate the user
    	if(session.getAttribute("username")== null){
    		response.sendRedirect("index.jsp");
    	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.users.StartConnection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User | Home</title>
</head>
<body>
	<%!ResultSet rs; %>
	<% 
		Connection conn = new StartConnection().getConnection();
		PreparedStatement stmt=conn.prepareStatement("select * from tbl_user");  
		rs=stmt.executeQuery();  
	%>
	<div>
		<p> Hello <%= session.getAttribute("username") %>!</p>
		<a href="Logout"><input type="submit" value="Logout"></a>
	</div>
	<div>
		<p>This is the list of users.</p>
		<table cellpadding="10" border="1">
			<tr>
				<th>Id</th>
				<th>Username</th>
				<th>Email</th>
				<th>Password</th>
				<th>Address</th>
				<th>Contact Number</th>
				<th>Action</th>
			</tr>
			<%while(rs.next()){ 
				if(session.getAttribute("username").equals(rs.getString("username"))){
					continue;
				}
			%>
			<tr>
				<td><%=rs.getInt("id")%></td>
				<td><%=rs.getString("username")%></td>
				<td><%=rs.getString("email")%></td>
				<td id="pass"><%=rs.getString("password")%></td>
				<td><%=rs.getString("address")%></td>
				<td><%=rs.getString("phoneNumber")%></td>
				<td><a href="edit.jsp?id=<%=rs.getInt("id")%>">Edit</a>|<a href="Delete?id=<%=rs.getInt("id")%>">Delete</a></td>
			</tr>
			<% } %>
		</table>
	</div>
	
</body>
</html>