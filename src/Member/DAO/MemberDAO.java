package Member.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Member.vo.Genre;
import Member.vo.Member;
import common.JDBCTemplate;

public class MemberDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn = JDBCTemplate.getConnection();
	
	//회원가입
	public int generInsert(String id,Genre g,int i) {
		int result = 0;
		int maxNo = 0;
		
		String LIKINGNO = "Select nvl(max(LIKINGNO),0)+1 from Liking";
		String sql = "Insert into Liking values(?,?,?)";
		
		pstmt = null;
		rs = null;
		
		//maxno가져오기
		try {
			pstmt = conn.prepareStatement(LIKINGNO);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxNo = rs.getInt(1);
			}else {
				maxNo = 0;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		//저장
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,maxNo);
			pstmt.setString(2, id);
			pstmt.setString(3, g.getGenre().get(i));
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	public int MemberInsert(Member m,Genre g) {
		int result = 0;
		int result2 = 0;
		
		
		int maxNo = 0;
		
		String sql = "Insert into member values(?,?,?,?,?,?,?)";
		
		
		pstmt = null;
		rs = null;
	
		//ȸ�����
		try {
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
	
	// 로그인
	public Genre getGenre(String id) {
		Genre genre = new Genre();
		
		List<String> text = new ArrayList<String>();
		
		String sql2 = "select * from Liking where id=?";
		try {
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String gen = rs.getString("genre");
				text.add(gen);
			}
			
			genre.setGenre(text);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return genre;
	}
	
	public Member login(Member m) {
			Connection conn = JDBCTemplate.getConnection();
			
			String id = m.getId();
			String pwd = m.getPwd();
			
			String sql = "SELECT * FROM member where id = ?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Member resultVo = new Member();
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
			
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
			
			return resultVo;
	}
			
	//중복확인
	public int duplecateID(String id) {
		int cnt =0;
	 try {
		 Connection conn = JDBCTemplate.getConnection();
		 StringBuilder sql = new StringBuilder();
		 
//		 String sql = "select count(id) as cnt from member where id = ? ";
		 // 아이디 중복 확인
		 sql.append("SELECT count(id) as cnt");
		 sql.append(" FROM member ");
		 sql.append(" WHERE id = ? ");
		 
		 PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		 pstmt.setString(1, id);
		 
		 ResultSet rs = pstmt.executeQuery();
		 if(rs.next()) {
			 cnt = rs.getInt("cnt");
			 System.out.println("cnt: " + cnt);
		 }
	 } catch(Exception e) {
		 System.out.println("아이디 중복 확인 실패 : " + e );
	 	}
	 return cnt;
	}
// 아이디찾기
	public Member searchId(String name, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		conn = JDBCTemplate.getConnection();
		String query = "select * from member where name=? and email=?";
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				m = new Member();
				m.setId(rset.getString("id"));
				m.setPwd(rset.getString("pwd"));
				m.setName(rset.getString("name"));
				m.setEmail(rset.getString("email"));
				m.setGenre(rset.getInt("genre"));
				m.setPhone(rset.getInt("phone"));
				m.setMembership(rset.getInt("membership"));				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}	
		return m;
	}
	// 비밀번호 찾기(분실시)
	public Member searchPw(String id, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		conn = JDBCTemplate.getConnection();
		String query = "select * from member where id=? and email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Member();
				m.setId(rset.getString("id"));
				m.setPwd(rset.getString("pwd"));
				m.setName(rset.getString("name"));
				m.setEmail(rset.getString("email"));
				m.setGenre(rset.getInt("genre"));
				m.setPhone(rset.getInt("phone"));
				m.setMembership(rset.getInt("membership"));				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		
		return m;
		}

	// 회원 탈퇴
	public int deleteId(String id, String pwd) {
		int result = 0;
		String dbpw = "";
		try {
			conn = JDBCTemplate.getConnection();
			String sql = "select pwd from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
					dbpw = rs.getString("pwd");
					if(dbpw.equals(pwd)) {
						try {rs.close();}catch(SQLException s) {}
						try {pstmt.close();}catch(SQLException s) {}
						String delsql = "delete from member where id = ?";
						pstmt = conn.prepareStatement(delsql);
						pstmt.setString(1, id);
						result= pstmt.executeUpdate();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally{
			try {rs.close();}catch(SQLException s) {}
			try {pstmt.close();}catch(SQLException s) {}
			try {conn.close();}catch(SQLException s) {}
		}
		return result;
	}
	
}
