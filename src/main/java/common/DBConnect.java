package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class DBConnect {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public DBConnect(ServletContext application) {
		try {
			String driver = application.getInitParameter("mysqlDriver");
			Class.forName(driver);

			String url = application.getInitParameter("mysqlUrl");
			String id = application.getInitParameter("mysqlId");
			String pwd = application.getInitParameter("mysqlPwd");
			
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("----------");
			System.out.println("DB CON application success");
			System.out.println("----------");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------");
			System.out.println("DB CON application fail");
			System.out.println("----------");
			System.out.println(e);
		}
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
