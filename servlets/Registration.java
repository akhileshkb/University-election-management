

import java.io.*;
import java.sql.*;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection con = null;
	 		String url = "jdbc:postgresql://localhost:5432/elec_management"; //PostgreSQL URL and followed by the database name
	 		String username = "postgres"; //PostgreSQL username
	 		String password = "1234"; //PostgreSQL password
			
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, username, password); //attempting to connect to PostgreSQL database
	 		HttpSession session = request.getSession();
	 		String cand_id = (String) session.getAttribute("user_id");
	 		String club_id = (String) session.getAttribute("standforclub");
	 		String achieve = request.getParameter("ahievement");
	 		//System.out.println(achieve);
	 		String sql = "insert into candidate values(?,?,?,?);";
	 		PreparedStatement st = con.prepareStatement(sql);
	 		st.setString(1, cand_id);
	 		st.setString(2, club_id);
	 		st.setInt(4, 0);
	 		st.setString(3, achieve);
	 		int i = st.executeUpdate();
	 		if(i>0) {
	 			PrintWriter out = response.getWriter();
	 			session.setAttribute("standforclub", null);
				out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
				out.println("<p> <h3 style = 'color: green;'>REGISTRATION SUCCESSFUL !!!</h3></p>");
				return;
	 		}
		}
		catch(Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
			out.println("<p> <h3 style = 'color: red;'>SOME ERROR OCCURED...REGISTRATION UNSUCCESSFUL !!!</h3></p>");
			e.printStackTrace();
		}
	}

}
