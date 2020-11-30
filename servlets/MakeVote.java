

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MakeVote
 */
@WebServlet("/MakeVote")
public class MakeVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeVote() {
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
			String club= (String) session.getAttribute("voteforclub");
			String user_id = (String) session.getAttribute("user_id");
			String cand_id = request.getParameter("voteto");
			if(cand_id==null) {
				PrintWriter out = response.getWriter();
				out.println("<meta http-equiv = 'refresh' content='3; URL= showlist.jsp'>");
				out.println("<p style = 'color: red;'> you have not selected anything !!!</p>");
			}
			String sql= "insert into votes values(?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user_id);
			st.setString(2, club);
			st.setString(3, cand_id);
			st.setString(4, club);
			int i= st.executeUpdate();
			if(i>0) {
				PrintWriter out = response.getWriter();
				out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
				out.println("<p><h3 style = 'color: green;'> your response has been saved successfully !!!</h3></p>");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
