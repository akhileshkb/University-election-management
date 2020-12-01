

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
import java.sql.ResultSet;
/**
 * Servlet implementation class MakeVote
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
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
			if(elec_etime.isAfter(now))
			{
				System.out.println("success");
				//PrintWriter out = response.getWriter();
				//out = response.getWriter();
				out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
				out.println("<p style = 'color: red;'> Election is not over yet!!!</p>");
				return;
			}
			else {
				//out.println("<p style = 'color: red;'> er yet!!!</p>");
				String sql4 = "select * from winner";
				PreparedStatement st6 = con.prepareStatement(sql4);
			    ResultSet rs3 = st6.executeQuery();
			    if(!rs3.next()) {
			    
				String[] array1 = {"club1","club2","club3","club4","club5","club6","club7","club8","club9"};
				String winner_id[] = {"asdf","ads","asfd","ads","ads","ads","ads","ads","ads"};
				String club_id[] = {"safd","ads","ads","ads","ads","ads","ads","ads","ads"};
				int margin[] = {0,0,0,0,0,0,0,0,0};
 				for(int g = 0; g<9;g++) {
					String sql1 = "select * from candidate where c_id = ? order by vote_count desc, s_id, c_id ";
					PreparedStatement st2 = con.prepareStatement(sql1);
					st2.setString(1, array1[g]);
					//st2.setString(2, array1[g]);
					ResultSet rs1 = st2.executeQuery();
					int check = 0;
					int val1 = 0;
					int val2 = 0;
					while(rs1.next()) {
						if(check<2) {
							if(check ==0 && rs1.getString(1).length()<10 && rs1.getString(2).length()<10) {
								winner_id[g] = rs1.getString(1);
								club_id[g] = rs1.getString(2);
								System.out.println(rs1.getString(1));
								System.out.println(rs1.getString(2));
								val1 = rs1.getInt(4);
							}
							if(check==1) {
								val2 = rs1.getInt(4);
							}

							check++;
						}
						else {
							break;
						}
					}
					if(val1!=0 && val2!=0) {
					System.out.println(val1-val2);
					margin[g] = val1- val2;
					}
					else if(val1!=0 && val2==0){
						System.out.println(val1);
						margin[g] = val1;
					}
				}
 				for(int j= 0; j<9; j++) {
 					//out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");

 					System.out.println();
 					if(club_id[j].equals(array1[j])) {
 	 					System.out.println(club_id[j]);
 	 					System.out.println(array1[j]);
 					String sql2 = "insert into winner values(?,?,?)";
 					PreparedStatement st3 = con.prepareStatement(sql2);
 					st3.setString(1, winner_id[j]);
 					st3.setString(2,club_id[j]);
 					st3.setInt(3,margin[j]);
 					
 					int re = st3.executeUpdate();
 					if(re>0) {
 						out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
						out.println("<p><h3 style = 'color: green;'> There was a problem fetching results...!!!</h3></p>");
 					}}
			}
			    }
			    else {
					String sql10 = "select student.name, club.name, winner.margin,student.s_id  from student, club, winner where winner.s_id = student.s_id and winner.c_id = club.c_id";
					PreparedStatement st10 = con.prepareStatement(sql10);
					
					//st10.setString(1, s_id);
					ResultSet rs10 = st10.executeQuery();
					//String clubdata = "";
					ArrayList<String> winnerdata = new ArrayList<String>();
					while(rs10.next()) {
//						String c_id = rs1.getString("c_id");
//						sql = "select name from club where c_id =?";
//						st= con.prepareStatement(sql);
//						st.setString(1, c_id);
//						rs = st.executeQuery();
//						if(rs.next()) {
							System.out.println(rs10.getString(1));
							System.out.println(rs10.getString(2));
							System.out.println(rs10.getString(3));
							String winner_name = rs10.getString(1);
							String club_name = rs10.getString(2);
							String margin_number = rs10.getString(3);
							String stu_name = rs10.getString(4);
							winnerdata.add("'"+stu_name+"'");
							winnerdata.add("'"+winner_name+"'");
							winnerdata.add("'"+club_name+"'");
							winnerdata.add("'"+margin_number+"'");
							/*clubdata +=c_id;
							clubdata+=" ";
							clubdata+=club_name;
							clubdata+=" ";*/
						
					}
					session.setAttribute("winnerdata", winnerdata);
 				response.sendRedirect("result.jsp");}
			}
			// statement to check if already it has some values......
			

//			String sql= "insert into votes values(?,?,?,?)";
//			PreparedStatement st = con.prepareStatement(sql);
//			st.setString(1, user_id);
//			st.setString(2, club);
//			st.setString(3, cand_id);
//			st.setString(4, club);
//			int i= st.executeUpdate();
//			if(i>0) {
//				String sql1 = "update candidate set vote_count = vote_count + 1 where s_id = ?";
//				PreparedStatement st1 = con.prepareStatement(sql1);
//				st1.setString(1, cand_id);
//				int i1 = st1.executeUpdate();
//				if(i1>0) {
//					out.println("<meta http-equiv = 'refresh' content='3; URL= dashboard.jsp'>");
//					out.println("<p><h3 style = 'color: green;'> your response has been saved successfully !!!</h3></p>");
//				}
//			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
