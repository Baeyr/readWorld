package Qna.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import Board.vo.Board;
import Qna.vo.Qna;
import common.JDBCTemplate;



public class QnaDAO {
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private Connection conn=null;
	
	//qna글쓰기
	public int qnaWrite(Qna qna,int ref) {
		int result = 0;
		int max=1;
		
		conn = JDBCTemplate.getConnection();
		
		String sqlMaxNo="select nvl(max(qnano),0)+1 from qnaboard";
		String sql = "";
		
		pstmt=null; 
		rs=null;
		
		//maxno 구하기
		try {
			pstmt=conn.prepareStatement(sqlMaxNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				max=rs.getInt(1);
			} else {
				return 0;
			}
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			if(ref == 0) {
				sql="insert into qnaboard values(?,?,?,current_timestamp,?,"+max+",0,0)" ;
			}else {
				sql="insert into qnaboard values(?,?,?,current_timestamp,?,"+ref+",1,1)" ; //테이블명 확인
			}
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, max);
			pstmt.setString(2, qna.getQnatitle());
			pstmt.setString(3, qna.getQnacontent());
			pstmt.setString(4, qna.getId());
			result=pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	//id별 qna출력
	public List<Qna> getBoardList(String id,int start, int end){
		
		List<Qna> list = null; 
		
		conn = JDBCTemplate.getConnection();
		String sql1 = "";
		String sql2 = "";
		
		if(id.equals("admin")) {
			sql1 = "(select rownum r,q2.* from (select * from qnaboard)q1, qnaboard q2 where (q1.qnano = q2.qnaref) order by q1.qnaref desc, q1.qna_step asc)";
			sql2 = "select * from"+sql1+" where r>=? and r<=?";
		}else {
			sql1 = "(select rownum r,q2.* from (select * from qnaboard where id='"+id+"')q1, qnaboard q2 where (q1.qnano = q2.qnaref) order by q1.qnaref desc, q1.qna_step asc)";
			sql2 = "select * from"+sql1+" where r>=? and r<=?";
		}
		
		
		
		try {
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if(rs.next()) {	// sql2 쿼리문으로 실행된 결과(게시물)들의 정보 저장
				list = new ArrayList<Qna>();
				
				do {
					Qna vo = new Qna();
					vo.setQnano(rs.getInt(2));
					vo.setQnatitle(replaceParam(rs.getString(3)));
					vo.setQnacontent(replaceParam(rs.getString(4)));
					vo.setQnadate(rs.getDate(5));
					vo.setId(rs.getString(6));
					vo.setQnaref(rs.getInt(7));
					vo.setQna_step(rs.getInt(8));
					vo.setQna_level(rs.getInt(9));
					
					list.add(vo);
				} while(rs.next());
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		return list;
	}
	
	//회원별 보드수 구하기
	public int getBoardCount(String id) {
		
		conn = JDBCTemplate.getConnection();
		
		int result = 0;
		String sql ="";
		
		if(id.equals("admin")) {
			sql = "SELECT COUNT(*) FROM qnaboard";
		}else {
			sql = "SELECT COUNT(*) FROM qnaboard where id = '"+id+"' or id = 'admin'";
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		
		
		return result;
	}
	
	private String replaceParam(String param) {
		String result="";
		
		//System.out.println("전:"+param);
		result = StringEscapeUtils.unescapeHtml(param);
		result = StringEscapeUtils.unescapeHtml(result);
		//System.out.println("후:"+result);
		return result;
	}
}
