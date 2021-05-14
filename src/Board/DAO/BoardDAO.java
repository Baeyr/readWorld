package Board.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Board.vo.Board;
import Board.vo.Comment;
import Book.vo.Book;
import Member.vo.Member;
import common.JDBCTemplate;

public class BoardDAO {
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private Connection conn=null;
	int a=0, b=0, c=0;

	public void close() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//게시판 상단 고정
	public List<Integer> fixedList(){
		List<Integer> n = new ArrayList<Integer>();
		conn=JDBCTemplate.getConnection();

		String sql1="select b1.boardno from (select rownum r, b.* from (select * from board where id='admin' order by boardno desc) b) b1 where b1.r<=2";
		String sql2="select b2.boardno from (select rownum r, b1.* from (select b.* from board b order by boardcount desc) b1) b2 where b2.r=1";

		try {
			pstmt=conn.prepareStatement(sql1);
			rs=pstmt.executeQuery();

			if(rs.next()) 
				a=rs.getInt(1);
				n.add(a);
			if(rs.next()) 
				b=rs.getInt(1);
				n.add(b);
			close();

			pstmt=null; rs=null;
			conn = JDBCTemplate.getConnection();
			pstmt=conn.prepareStatement(sql2);
			rs=pstmt.executeQuery();

			if(rs.next()) {
				c=rs.getInt(1);
				n.add(c);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	//게시판 10개 추출
	public List<Board> getBoardList(int start, int end, String search){
		List<Board> list = null; 
		List<Integer> n = new ArrayList<Integer>();
		conn = JDBCTemplate.getConnection();
		
		try {
			
			String sql1="select b1.boardno from (select rownum r, b.* from (select * from board where id='admin' order by boardno desc) b) b1 where b1.r<=2";
			String sql2="select b2.boardno from (select rownum r, b1.* from (select b.* from board b order by boardcount desc) b1) b2 where b2.r=1";

		
			pstmt=conn.prepareStatement(sql1);
			rs=pstmt.executeQuery();

			if(rs.next()) 
				a=rs.getInt(1);
				n.add(a);
			if(rs.next()) 
				b=rs.getInt(1);
				n.add(b);
			close();

			pstmt=null; rs=null;
			conn = JDBCTemplate.getConnection();
			pstmt=conn.prepareStatement(sql2);
			rs=pstmt.executeQuery();

			if(rs.next()) {
				c=rs.getInt(1);
				n.add(c);
			}
			
			close();
			
			conn = JDBCTemplate.getConnection();
			pstmt=null; rs=null;
			
			// 검색 후 더보기 버튼 클릭시 게시판 출력
			String sql4_1 = "(select * from board where boardcontent like '%" + search + "%' or boardtitle like '%" + search + "%' and boardno not in ( "+a+" , "+b+ " , "+c+ " ) )b ) b1";
			
			String sql4="SELECT b1.* FROM ( SELECT ROWNUM r, b.* from "+ sql4_1 + " where r>=1 and r<=3";
			
			pstmt=conn.prepareStatement(sql4);
			//pstmt.setInt(1, start);
			//pstmt.setInt(2, end);
			
			System.out.println(sql4);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				list=new ArrayList<Board>();
				
				do {
				Board vo = new Board();
				vo.setBoardno(rs.getInt("boardno"));
				vo.setId(rs.getString("id"));
				vo.setBoarddate(rs.getDate("boarddate"));
				vo.setBoardcontent(rs.getString("boardcontent"));
				vo.setBoardtitle(rs.getString("boardtitle"));
				vo.setBoardplay(rs.getInt("boardplay"));
				vo.setBoardcount(rs.getInt("boardcount"));
				
				list.add(vo);
				} while(rs.next());
			}else {
				System.out.println("왜 null이냐~~~~~없음:"+list);
			}
			close();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	//게시판 상단 고정
	
	
	// 게시판 고정 제외 게시글
	public List<Board> getBoardList(int start, int end){
		List<Board> list = null; 
		conn = JDBCTemplate.getConnection();

		try {
			String sql4="SELECT b1.boardno, b1.id, b1.boarddate, b1.boardcontent, b1.boardtitle, b1.boardplay, b1.boardcount "
					+ "FROM ( SELECT ROWNUM r, b.* from (select * from board where boardno not in  ( "+a+" , "+b+" , "+c+" ) order by boardno )b ) b1"
							+ " where r>=? and r<=? order by boardno desc";
			System.out.println("시작"+a+"/"+b);
			pstmt=conn.prepareStatement(sql4);

			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			System.out.println(sql4);

			rs=pstmt.executeQuery();

			if(rs.next()) {
				list=new ArrayList<Board>();
				do {
					Board vo = new Board();
					vo.setBoardno(rs.getInt("boardno"));
					vo.setId(rs.getString("id"));
					vo.setBoarddate(rs.getDate("boarddate"));
					vo.setBoardcontent(rs.getString("boardcontent"));
					vo.setBoardtitle(rs.getString("boardtitle"));
					vo.setBoardplay(rs.getInt("boardplay"));
					vo.setBoardcount(rs.getInt("boardcount"));
					list.add(vo);
				} while(rs.next());
			}
			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	
	}
	
	// 총 게시글 개수 가져오기
	public int getBoardCount() {
		
		conn = JDBCTemplate.getConnection();
		
		int result = 0;
		
		String sql = "SELECT COUNT(*) FROM board";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return result;
	}
	
	public int getBoardCount(String id) {
		
		conn = JDBCTemplate.getConnection();
		
		int result = 0;
		
		String sql = "SELECT COUNT(*) FROM board where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return result;
	}
	
	public List<Board> getMyBoardList(String id,int start,int end){
		List<Board> list = null;
		
		String sql1 = null;
		String sql2 = null;
		
		sql1 = "(select rownum r, b.* from (select * from board where id='"+id+"') b order by boarddate)";
		sql2 = "select * from "+sql1+" where r>="+ (start) +"and r<="+(end);
		
		conn = JDBCTemplate.getConnection();
		pstmt = null;
		rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Board>();
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardno(rs.getInt("boardno"));
				board.setId(rs.getString("id"));
				board.setBoarddate(rs.getDate("boarddate"));
				board.setBoardcontent(rs.getString("boardcontent"));
				board.setBoardtitle(rs.getString("boardtitle"));
				board.setBoardplay(rs.getInt("boardplay"));
				board.setBoardcount(rs.getInt("boardcount"));
				
				
				list.add(board);
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
	
	// 게시글 내용 불러오기
	public Board getBoard(Board vo) {
		
		conn = JDBCTemplate.getConnection();
		
		Board getvo = new Board();
		int boardno = vo.getBoardno();
		
		// 특정 게시물 번호에 해당하는 모든 정보를 가져오기 
		String sql = "SELECT * FROM board WHERE boardno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,boardno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 글에 대한 정보를 담을 객체 생성
				getvo.setBoardno(boardno);
				getvo.setId(rs.getString("id"));
				getvo.setBoarddate(rs.getDate("boarddate"));
				getvo.setBoardcontent(rs.getString("boardcontent"));
				getvo.setBoardtitle(rs.getString("boardtitle"));
				getvo.setBoardplay(rs.getInt("boardplay"));
				getvo.setBoardcount(rs.getInt("boardcount"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return getvo;
	}

	// 게시글 조회수 업데이트
	public int readCount(Board vo) {
				
			int result = 0 ;
			conn = JDBCTemplate.getConnection();
				
			int boardno = vo.getBoardno();
				
			String sql = "update board set boardplay = (boardplay + 1) where boardno = ?";
				
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardno);
				result = pstmt.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
				
	}
	
	// 코멘트 내용 불러오기
	public List<Comment> getComment(Comment co) {
		
		conn = JDBCTemplate.getConnection();
		
		List<Comment> cmt=null;
		int boardno = co.getBoardno();
		System.out.println("getComment:" + boardno);
		// 특정 게시물 번호에 해당하는 모든 코멘트를 가져오기 
		String sql = "SELECT * FROM cmt WHERE boardno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,boardno);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				// 코멘트에 대한 정보를 담을 객체 생성
				cmt=new ArrayList<Comment>();
				do{
				Comment getco=new Comment();
				getco.setCommentno(rs.getInt("commentno"));
				getco.setBoardno(boardno);
				getco.setId(rs.getString("id"));
				getco.setCmtcontent(rs.getString("cmtcontent"));
				System.out.println("co:"+rs.getString("cmtcontent"));
				getco.setCmtrootno(rs.getInt("cmtrootno"));
				getco.setCmtstep(rs.getInt("cmtstep"));
				getco.setCmtlevel(rs.getInt("cmtlevel"));
				cmt.add(getco);
				} while(rs.next());
			} else {
				System.out.println("안들어간다!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cmt;
	}

	//게시글 쓰기
	public int boardWrite(Board vo) {
		int result=0;
		int max=1;

		conn = JDBCTemplate.getConnection();
		
		String sql="insert into board values(?,?,current_timestamp,?,?,0,0)" ; //테이블명 확인
		String sqlMaxNo="select nvl(max(boardno),0)+1 from board"; //테이블명 확인
		pstmt=null; rs=null;

		try {
			pstmt=conn.prepareStatement(sqlMaxNo); //글번호 지정
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				max=rs.getInt(1);
			} else {
				System.out.println("확인 요");
				return 0;
			}
			
			
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, max);
				pstmt.setString(2, vo.getId());
				pstmt.setString(3, vo.getBoardcontent());
				pstmt.setString(4, vo.getBoardtitle());
				result=pstmt.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// 게시글 수정
	public int boardReWrite(Board vo) {
		
		conn = JDBCTemplate.getConnection();
		
		int result = 0;
		int boardno = vo.getBoardno();
		String sql = "update board set id=?, boardtitle=?, boardcontent=? where boardno=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getBoardtitle());
			pstmt.setString(3, vo.getBoardcontent());
			pstmt.setInt(5, boardno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	// 게시글 삭제
	public int boardDelete(Board vo) {
		conn = JDBCTemplate.getConnection();
		int result = 0;
		
		String sql = "DELETE FROM board WHERE boardno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,vo.getBoardno());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	
	// 게시글 추천 여부 검사
	public int likeCheck(Board vo,String id) {
		
		int result = 0 ;
		
		String sql ="select * from likeboard where boardno = ? and id = ?";
		
		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBoardno());
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("이미추천함~");
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return result;
		
	}
	
	
	// 빈 하트 버튼을 누르면 추천 
	public int likeUpdate(Board vo,String id) {
		
		int result = 0 ;
		
		String sql = "insert into likeboard values(?,?)";
		String sql2 = "update board set BOARDCOUNT = (BOARDCOUNT+1) where boardno = ?";
		
		
		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBoardno());
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		
			JDBCTemplate.close(pstmt);
			if(result == 1) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, vo.getBoardno());
				result = pstmt.executeUpdate();
			}else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result ;
	}
		
		
	// 빨간 하트 버튼을 누르면 추천 취소
	public int likeDelete(Board vo,String id) {
		
		int result = 0 ;
		
		String sql = "delete from likeboard where boardno = ? and id = ?";
		String sql2 = "update board set BOARDCOUNT = (BOARDCOUNT-1) where boardno = ?";
		
		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBoardno());
			pstmt.setString(2,id);
			result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			if(result == 1) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, vo.getBoardno());
				result = pstmt.executeUpdate();
			}else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result ;
		
	}
	
	
	// 하트 버튼 아래에 추천수 찾기 
	public int likeCount(Board vo) {
		
		
		String sql ="select count(*) from likeboard where boardno = ?";
		int count = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBoardno());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
				System.out.println(count);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
			return count; 
		
	}
	
	
	// 베스트 게시물 상단 고정 
	
}
