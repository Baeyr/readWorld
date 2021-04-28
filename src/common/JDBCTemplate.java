package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;




public class JDBCTemplate {
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			String currentPath = JDBCTemplate.class.getResource("./").getPath();
			prop.load(new BufferedReader(new FileReader(currentPath+"driver.properties"))); 
			Class.forName(prop.getProperty("driver")); 
			conn = DriverManager.getConnection(prop.getProperty("url"), 
					prop.getProperty("user"), prop.getProperty("pwd"));
			
			if(conn == null) {
				System.out.println("db 연결 성공");
			} else {				
				System.out.println("db 연결 실패");
			}
		} catch(Exception e) {
			System.out.println("db 연결 실패");
			e.printStackTrace();
		}	
		return conn;
	}
	
	public static void close(Connection con) {
		try {
			if(con != null && !con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {

		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {

		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {

		}
	}
	
	public static void commit(Connection con) {
		try {
			if(con != null && !con.isClosed()) {
				con.commit();
			}
		} catch (Exception e) {

		}
	}
	
	public static void rollback(Connection con) {
		try {
			if(con != null && !con.isClosed()) {
				con.rollback();
			}
		} catch (Exception e) {

		}
	}
}
