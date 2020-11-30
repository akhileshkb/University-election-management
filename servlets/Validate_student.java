import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Validate_student
 */
@WebServlet("/Validate_student")
public class Validate_student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate_student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		try
		{
			Connection con = null;
	 		String url = "jdbc:postgresql://localhost:5432/elec_management"; //PostgreSQL URL and followed by the database name
	 		String username = "postgres"; //PostgreSQL username
	 		String password = "1234"; //PostgreSQL password
			
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, username, password); //attempting to connect to PostgreSQL database
			
	 		String s_id = request.getParameter("id");
			String s_pass = request.getParameter("password");
			PreparedStatement st = con.prepareStatement("select * from student where s_id=? and pass=?;");
			st.setString(1,s_id);
			st.setString(2,s_pass);
			ResultSet rs = st.executeQuery();
			while(rs.next()) 
			{
				String name = rs.getString("name");
				HttpSession session = request.getSession();
				session.setAttribute("username",name );
				session.setAttribute("user_id", s_id);
				
			    st = con.prepareStatement("select * from election;");
				
				ResultSet rs1 = st.executeQuery();
				while(rs1.next()) 
				{
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
				
				
				String sql = "select c_id from voter where v_id = ?";
				st = con.prepareStatement(sql);
				st.setString(1, s_id);
				rs1 = st.executeQuery();
				//String clubdata = "";
				ArrayList<String> clubdata = new ArrayList<String>();
				while(rs1.next()) {
					String c_id = rs1.getString("c_id");
					sql = "select name from club where c_id =?";
					st= con.prepareStatement(sql);
					st.setString(1, c_id);
					rs = st.executeQuery();
					if(rs.next()) {
						String club_name = rs.getString(1);
						clubdata.add("'"+c_id+"'");
						clubdata.add("'"+club_name+"'");
						/*clubdata +=c_id;
						clubdata+=" ";
						clubdata+=club_name;
						clubdata+=" ";*/
					}
				}
				session.setAttribute("clubdata", clubdata);
				response.sendRedirect("dashboard.jsp");
				return;
			}
			//response.sendRedirect("studentLogin.jsp");
			PrintWriter out = response.getWriter();
			out.println("<meta http-equiv = 'refresh' content='3; URL= studentLogin.jsp'>");
			out.println("<p style = 'color: red;'> user or password incorrct or empty !!!</p>");
		}
		catch (Exception e) 
 		{
 			e.printStackTrace();
 		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
