

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Createaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection conn;
    public Createaccount() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost/student","kausar","admin123");
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s1 = request.getParameter("uid");
		String s2 = request.getParameter("pwd");
		try {
			
			PreparedStatement ps= conn.prepareStatement("insert into login values(?,?)");
			ps.setString(1, s1);
			ps.setString(2,s2);
			ps.executeUpdate();
			ps.close();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter pw=response.getWriter();
		pw.print("<script>window.history.forward();</script>");
		pw.print("<center>");
		pw.print("<form action='login.jsp' method='post'>");
		pw.print("<p>"+"THANKS FOR REGISTRATION"+"</p>");
		pw.print("<p2>"+"YOUR USERNAME IS:"+s1+"</p>");
		pw.print("Click Here To Login"+"<input type='submit' value='Login'>");
		pw.print("</form>");
		pw.print("</center>");
	
	}

}
