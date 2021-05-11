package Purchase.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.JDBCTemplate;

public class PurchaseDAO {
	// 회원권 구매 테이블에 insert
 public int MembershipBuy(String id, int num, Date beforedate, Date afterdate) {
	 int result = 0;
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 String sql = "insert into purchase values(seq_buyno.nextval, ?, ?, ?, ?)";
	 conn = JDBCTemplate.getConnection();
	 
	 try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setInt(2, num);
		pstmt.setDate(3, beforedate);
		pstmt.setDate(4, afterdate);
		
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
	}
	 return result;
 }
 public int MbsNoUpdate(int num, String id) {
	 int result = 0;
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 String sql = "update member set membership=? where id=?";
	 conn = JDBCTemplate.getConnection();
	 
	 try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.setString(2, id);
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
	}
	 return result;
 }
}
