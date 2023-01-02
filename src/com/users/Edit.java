package com.users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		
		StartConnection connect = new StartConnection();
		Connection conn = connect.getConnection();
//		connect.update(id, username, email, password, address, phoneNumber);
		
		
		String sql = "UPDATE tbl_user SET username=?, email=?, password=?, address=?, phoneNumber=? WHERE id=?";
		
			try{			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, email);
			stmt.setString(3, password);
			stmt.setString(4, address);
			stmt.setString(5, phoneNumber);
			stmt.setInt(6, id);
			stmt.executeUpdate();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			}
			catch(Exception e){
				e.printStackTrace();
			}

	}

}
