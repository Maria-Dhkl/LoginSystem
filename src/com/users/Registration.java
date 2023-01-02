package com.users;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		response.setContentType("text/html;charset-UTF-8");
		try(PrintWriter out = response.getWriter()){
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			
			StartConnection connect = new StartConnection();
			Connection conn = connect.getConnection();
			PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM tbl_user where username=?");
			stmt1.setString(1, username);
			PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM tbl_user where email=?");
			stmt2.setString(1, email);
			
			ResultSet rs1 = stmt1.executeQuery();
			ResultSet rs2 = stmt2.executeQuery();
			
			if(rs1.next()) {
				out.print("Username already exist.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("registrationForm.jsp");
				dispatcher.include(request, response);
			}
			else if(rs2.next()) {
				out.print("Email already exist.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("registrationForm.jsp");
				dispatcher.include(request, response);
			}
			else {
				SendEmail sendEmail = new SendEmail();
				String code = sendEmail.getRandom();
				
				User user = new User(username, email, password, address, phoneNumber, code);
				boolean test = sendEmail.SendEmail(user);
				
				if(test) {
					HttpSession session = request.getSession();
					session.setAttribute("authenticationCode", user);
					response.sendRedirect("verifyUser.jsp");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
