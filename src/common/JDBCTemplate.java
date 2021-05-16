package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class JDBCTemplate {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			Context initContext2 = (Context)initContext.lookup("java:/comp/env"); 
				//web.xml파일로 가서 찾기
			DataSource ds = (DataSource)initContext2.lookup("jdbc/readWolrd"); 
				//xml내에서 db관련 찾기
			conn = ds.getConnection();
			
			if(conn != null) {
				System.out.println("db 연결 성공");
			} else {				
				System.out.println("db 연결 실패");
			}
		} catch(Exception e) {
			System.out.println("db 연결 실패");
		}	
		return conn;
	}
	
	public static void close(Connection con) {
		try {
			if(con != null) {
				con.close();
				System.out.println("db 연결해제");
			}
		} catch (Exception e) {

		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {

		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null) {
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
