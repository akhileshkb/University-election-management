

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterCandidate
 */
@WebServlet("/RegisterCandidate")
public class RegisterCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCandidate() {
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
	 		
			HttpSession session = request.getSession();
			session.removeAttribute("standforclub");
			String club_id = (String)request.getParameter("standfor");
			String user_id = (String) session.getAttribute("user_id");
			String club1 = club_id;
			session.setAttribute("standforclub",club1);
			String sql = "select from candidate where s_id=? and c_id=?;";
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, user_id);
			st.setString(2, club_id);
			ResultSet rs = st.executeQuery();
			int flag=0;
			while(rs.next()) {
				PrintWriter out = response.getWriter();
				out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
				out.println("<p style = 'color: green;'> <h3>YOU ARE ALREADY REGISTERED AS A CANDIDATE FOR THIS CLUB !!!</h3></p>");
				flag=1;
				break;
				
			}
			if(flag==0) {
				//System.out.println(session.getAttribute("standforclub")+" this "+user_id);
				response.sendRedirect("CandidateRegisterForm.jsp");
				
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
