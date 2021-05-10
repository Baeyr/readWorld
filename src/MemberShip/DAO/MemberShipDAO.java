package MemberShip.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MemberShip.vo.MemberShip;
import common.JDBCTemplate;

public class MemberShipDAO {
	public MemberShip memberShipAll(int num){
		MemberShip mbs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = JDBCTemplate.getConnection();
		
		String sql = "select * from membership where membershipno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mbs = new MemberShip();
				mbs.setMembershipno(rs.getInt("membershipno"));
				mbs.setMembershipname(rs.getString("membershipname"));
				mbs.setMembershipdate(rs.getInt("membershipdate"));
				mbs.setMembershipprice(rs.getInt("membershipprice"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		return mbs;
	}
}
