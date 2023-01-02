package com.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		StartConnection connect = new StartConnection();
		Connection conn = connect.getConnection();
		
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to delete it?", "Delete", JOptionPane.YES_NO_OPTION);
		if(confirm==0) {
			try {
				String query = "DELETE FROM tbl_user where id = ?";
				
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				
				stmt.executeUpdate();
				response.sendRedirect("home.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			response.sendRedirect("home.jsp");
		}

	}

}
