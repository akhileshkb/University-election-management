

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShowList
 */
@WebServlet("/ShowList")
public class ShowList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowList() {
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
				
				String etime = (String) session.getAttribute("etime");
				String stime = (String) session.getAttribute("stime");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");  
				LocalDateTime now = LocalDateTime.now();  
				LocalDateTime elec_etime = LocalDateTime.parse(etime, dtf);
				LocalDateTime elec_stime = LocalDateTime.parse(stime, dtf);
				if(elec_etime.isBefore(now))
				{
					System.out.println("success");
					PrintWriter out = response.getWriter();
					out = response.getWriter();
					out.println("<meta http-equiv = 'refresh' content='3; URL= result.jsp'>");
					out.println("<p style = 'color: red;'> Election Ended!!!</p>");
					return;
				}
				if(elec_stime.isAfter(now))
				{
					System.out.println("success");
					PrintWriter out = response.getWriter();
					out = response.getWriter();
					out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
					out.println("<p style = 'color: red;'> Election not Started!!!</p>");
					return;
				}
				
				session.removeAttribute("voteforclub");
				String club_id = (String)request.getParameter("standfor1");
				String user_id = (String) session.getAttribute("user_id");
				String sql= "select * from votes where Voter=? and voter_club =?;";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, user_id);
				st.setString(2, club_id);
				ResultSet rs= st.executeQuery();
				while(rs.next()) {
					PrintWriter out = response.getWriter();
					out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
					out.println("<p><h3 style = 'color: green;'> You already voted for this club !!!</h3></p>");
					return;
				}
				String club1 = club_id;
				session.setAttribute("voteforclub",club1);
				sql = "select candidate.s_id, student.name from candidate natural join student where candidate.c_id=?;";
				st= con.prepareStatement(sql);
				st.setString(1, club_id);
				rs = st.executeQuery();
				int flag=0;
				ArrayList<String> cand_list = new ArrayList<String>();
				while(rs.next()) {
					flag=1;
					String cand = (String)rs.getString(1);
					String cname= (String)rs.getString(2);
					
					cand_list.add("'"+cand+"'");
					cand_list.add("'"+cname+"'");
				}
				
				if(flag==0) {
					PrintWriter out = response.getWriter();
					out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
					out.println("<p style = 'color: red;'> no candidate !!!</p>");
					return;
				}
				session.setAttribute("candlist", cand_list);
				//System.out.println(cand_list);
				response.sendRedirect("showlist.jsp");
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}

}
