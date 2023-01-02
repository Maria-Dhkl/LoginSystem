package com.users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		StartConnection connect = new StartConnection();
		Connection conn = connect.getConnection();
//		connect.retrieve(username, password);
		
		String query1 = "SELECT * from tbl_user WHERE username=? AND password=?";
		String query2 = "SELECT * from tbl_user WHERE email=? AND password=?";
		try {
			PreparedStatement stmt1 = conn.prepareStatement(query1);
			PreparedStatement stmt2 = conn.prepareStatement(query2);
			stmt1.setString(1, username);
			stmt1.setString(2, password);
			stmt2.setString(1, username);
			stmt2.setString(2, password);
			ResultSet rs1 = stmt1.executeQuery();
			ResultSet rs2 = stmt2.executeQuery();
			if(rs1.next()||rs2.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("username", rs1.getString("username"));
				response.sendRedirect("home.jsp");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
//		        dispatcher.forward(request, response);
//			}
//			else if(rs2.next()) {
//				HttpSession session = request.getSession();
//				session.setAttribute("username", rs2.getString("username"));
//				response.sendRedirect("home.jsp");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
//		        dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
