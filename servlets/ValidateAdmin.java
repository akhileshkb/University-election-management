

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidateAdmin
 */
@WebServlet("/ValidateAdmin")
public class ValidateAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection con = null;
	 		String url = "jdbc:postgresql://localhost:5432/elec_management"; //PostgreSQL URL and followed by the database name
	 		String username = "postgres"; //PostgreSQL username
	 		String password = "1234"; //PostgreSQL password
			
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, username, password); //attempting to connect to PostgreSQL database
	 		
			String admin_id = request.getParameter("id");
			String admin_pass = request.getParameter("password");
			
			String sql = "select * from admin natural join student where admin.a_id = ? and student.pass = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,admin_id);
			st.setString(2,admin_pass);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				HttpSession session = request.getSession();
				session.setAttribute("username",name );
				session.setAttribute("user_id", admin_id);
				
				sql = "select * from election";
			    st = con.prepareStatement(sql);
				
				ResultSet rs1 = st.executeQuery();
				while(rs1.next()) {
					String starttime = rs1.getTimestamp("start").toString();
					String endtime = rs1.getTimestamp("end_t").toString();
					String guideline = rs1.getString("guideline").toString();
					session.setAttribute("stime", starttime);
					session.setAttribute("etime", endtime);
					/*Blob blob = rs1.getBlob("guideline");
					String gline = new String(blob.getBytes(1L, (int)blob.length()));*/
					session.setAttribute("guideline", guideline);
					//System.out.println(starttime+ " " + endtime + " "+ guideline);
				}
				
				response.sendRedirect("welcome_admin.jsp");
				return;
			}
			response.sendRedirect("adminLogin.jsp");
		}
		catch (Exception e) 
 		{
 			e.printStackTrace();
 		}
	}

}
