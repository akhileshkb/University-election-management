

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Updatestime
 */
@WebServlet("/Updateguid")
public class Updateguid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   		PrintWriter out =  response.getWriter();
		try {
			Connection con = null;
	 		String url = "jdbc:postgresql://localhost:5432/elec_management"; //PostgreSQL URL and followed by the database name
	 		String username = "postgres"; //PostgreSQL username
	 		String password = "123"; //PostgreSQL password
			
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, username, password); //attempting to connect to PostgreSQL database
	 		
			String newtime = request.getParameter("newt");
			String s = "update election set guideline = ?";
			PreparedStatement st = con.prepareStatement(s);
			st.setString(1, newtime);
			int result = st.executeUpdate();
			if(result>0) {
				HttpSession session = request.getSession();
				session.setAttribute("guideline", newtime);
				response.sendRedirect("welcome_admin.jsp");
			}
			else {
				out.println("<meta http-equiv = 'refresh' content='3; URL= update_etime.jsp'>");
				out.println("<p style = 'color: red;'> invalid input!!!...redirecting...</p>");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			out.println("<meta http-equiv = 'refresh' content='3; URL= update_etime.jsp'>");
			out.println("<p style = 'color: red;'> invalid input!!!...redirecting...</p>");
		}
	}

}
