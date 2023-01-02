package com.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/VerifyEmail")
public class VerifyEmail extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		response.setContentType("text/html;charset-UTF-8");
		HttpSession session = request.getSession();
			try(PrintWriter out = response.getWriter()){
	
				User user = (User) session.getAttribute("authenticationCode");
				
				String code = request.getParameter("authenticationCode");
				
				if(code.equals(user.getCode())) {			
					String username = user.getUsername();
					String email = user.getEmail();
					String password = user.getPassword();
					String address = user.getAddress();
					String phoneNumber = user.getPhoneNumber();
					
					StartConnection connect = new StartConnection();
					Connection conn = connect.getConnection();
//					connect.insert(username, email, password, address, phoneNumber);
					
					String query = "INSERT into tbl_user(username, email, password, address, phoneNumber)" + " VALUES(?,?,?,?,?)";
					try {
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.setString(1, username);
						stmt.setString(2, email);
						stmt.setString(3, password);
						stmt.setString(4, address);
						stmt.setString(5, phoneNumber);
						stmt.executeUpdate();
				         
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("Verified Sucessfully");
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.include(request, response);
				}
			}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
}
