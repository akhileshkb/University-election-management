

import java.io.IOException;
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
		try 
		{
			Connection con = null;
	 		String url = "jdbc:postgresql://localhost:5432/elec_management"; //PostgreSQL URL and followed by the database name
	 		String username = "project"; //PostgreSQL username
	 		String password = "123"; //PostgreSQL password
	 		
	 		Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, username, password);
			
			String club_id = request.getParameter("club_id");
			String achievement  = request.getParameter("achievement");
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("user_id");
			PreparedStatement st = con.prepareStatement("select * from voter where v_id=? and c_id=?;");
			st.setString(1,user_id);
			st.setString(2, club_id);
			ResultSet rs = st.executeQuery();
			int i=0;
			while(rs.next()) 
			{
				i++;
				st = con.prepareStatement("select * from candidate where s_id = ?;");
				st.setString(1, user_id);
				ResultSet rs1 = st.executeQuery();
				if(rs1.next()) {
					i=0;
					break;
				}
				st = con.prepareStatement("insert into candidate values(?,?,?,?);");
				st.setString(1, user_id);
				st.setString(2, club_id);
				st.setInt(3, 0);
				st.setString(4, achievement);
				int result = st.executeUpdate();
//				st = con.prepareStatement("insert into votes values(?,?,?,?);");
//				st.setString(1, );
				response.sendRedirect("dashboard.jsp");
				return;
			}
			if(i == 0)
			{
				response.sendRedirect("StandCandidate.jsp");
				return;
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("StandCandidate.jsp");
		}
	}

}
