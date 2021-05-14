package Purchase.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Purchase.vo.PurchaseJoin;
import common.JDBCTemplate;

public class PurchaseDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn;
	
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
 
 	//회원권 조회
 	public List<PurchaseJoin> memberShipList(String id){
 		List<PurchaseJoin> list = null;
		
		String sql1 = "(select * from purchase where id='"+id+"' order by beforedate desc) a join membership";
		String sql2 = "select a.*,membership.membershipname from "+sql1+" on a.membershipno = membership.membershipno " + 
				"where rownum<=5";
		
		
		
		conn = JDBCTemplate.getConnection();
		pstmt = null;
		rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<PurchaseJoin>();
			while(rs.next()) {
				PurchaseJoin ps = new PurchaseJoin();
				
				ps.setBuyno(rs.getInt(1));
				ps.setId(rs.getString(2));
				ps.setMembershipno(rs.getInt(3));
				ps.setBeforedate(rs.getDate(4));
				ps.setAfterdate(rs.getDate(5));
				ps.setMembershipname(rs.getString(6));
				
				list.add(ps);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		
		return list;
 	}

}