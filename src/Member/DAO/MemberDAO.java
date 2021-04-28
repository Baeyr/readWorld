package Member.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Member.vo.Member;
import common.JDBCTemplate;

public class MemberDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn = JDBCTemplate.getConnection();
	
	//회원가입
	public int MemberInsert(Member m) {
		int result = 0;
		
		String LIKINGNO = "Select nvl(max(LIKINGNO),0)+1 from Liking";
		int maxNo = 0;
		
		String sql = "Insert into member values(?,?,?,?,?,?,?)";
		
		pstmt = null;
		rs = null;
		
		// db 취향 max LIKINGNO 가져오기
//		try {
//			pstmt = conn.prepareStatement(LIKINGNO);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				maxNo = rs.getInt(1);
//			} else {
//				maxNo = 0;
//				return 0;
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		JDBCTemplate.close(rs);
//		JDBCTemplate.close(pstmt);
		
		//ȸ�����
		try {
			System.out.println(m.getPhone());
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getEmail());
			pstmt.setInt(5, maxNo);
			pstmt.setInt(6,m.getPhone());
			pstmt.setInt(7, 0);
			
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally { // �ݱ�
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);	
		}
		return result;
	}
	
	// �α���
	
	public Member login(Member m) {
			String id = m.getId();
			String pwd = m.getPwd();
			
			String sql = "SELECT * FROM member where id = ? and pwd = ?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Member resultVo = new Member();
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					resultVo.setId(rs.getString("id"));
					resultVo.setPwd(rs.getString("pwd"));
					resultVo.setName(rs.getString("name"));
					resultVo.setPhone(rs.getInt("phone"));
					resultVo.setEmail(rs.getString("email"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(conn);
			}
			
			System.out.println(resultVo);
			return resultVo;
		}
}
