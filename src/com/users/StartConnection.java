package com.users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartConnection extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/collegeassignment", "root", "");
			
		}catch(ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public void insert(String username, String email, String password, String address, String phoneNumber) {
		getConnection();
		if(conn!=null) {
			String query = "INSERT into tbl_user(username, email, password, address, phoneNumber)" + " VALUES(?,?,?,?,?)";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, username);
				stmt.setString(2, email);
				stmt.setString(3, password);
				stmt.setString(4, address);
				stmt.setString(5, phoneNumber);
				stmt.executeUpdate();
		        response.sendRedirect("index.jsp");
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void retrieve(String username, String password) {
		getConnection();
		
		if(conn!=null) {
			
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
				if(rs1.next()) {
					HttpSession session = request.getSession();
					session.setAttribute("username", rs1.getString("username"));
					System.out.print("Sucess");
					response.sendRedirect("home.jsp");
				}
				else if(rs2.next()) {
					HttpSession session = request.getSession();
					session.setAttribute("username", rs2.getString("username"));
					response.sendRedirect("home.jsp");
				}
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update(int id, String username, String email, String password, String address, String phoneNumber) {
		getConnection();
		if(conn!=null) {
			String query = "UPDATE tbl_user SET (username=?, email=?, password=?, address=?, phoneNumber=?) WHERE id=?";
			try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, email);
			st.setString(3, password);
			st.setString(4, address);
			st.setString(5, phoneNumber);
			st.setInt(6, id);
			st.executeUpdate();
			response.sendRedirect("home.jsp");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void delete(int id) {
		getConnection();
		if(conn!=null) {
			try {
				String query = "DELETE FROM tbl_user where id = ?";
				
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				
				stmt.executeUpdate();
				response.sendRedirect("home.jsp");
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
