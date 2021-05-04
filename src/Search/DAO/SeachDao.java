package Search.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Board.vo.Board;
import Book.vo.Book;
import common.JDBCTemplate;

public class SeachDao {
	public List<Book> bookSearch(String search){
		List<Book> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = JDBCTemplate.getConnection();
		
		String sql = "select * from book where title like '%"+ search +"%'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<Book>();
				do {
					Book book = new Book();
					book.setIsbn(rs.getString("isbn"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setPublisher(rs.getString("publisher"));
					book.setPubDate(rs.getDate("pubDate"));
					book.setDescription(rs.getString("descriptions"));
					book.setCover(rs.getString("cover"));
					book.setRanks(rs.getInt("ranks"));
					book.setAdult(rs.getString("adult"));
					book.setCategoryName(rs.getString("CategoryName"));
					book.setSiteRanks(rs.getInt("siteranks"));
					
					list.add(book);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		
		return list;
	}
	public List<Board> boardSearch(String search){
		List<Board> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = JDBCTemplate.getConnection();
		
		String sql = "select * from board where boardtitle like '%"+ search +"%' or boardcontent like '%"+ search + "%'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<Board>();
				do {
					Board board = new Board();
					board.setBoardno(rs.getInt("boardno"));
					board.setId(rs.getString("id"));
					board.setBoarddate(rs.getDate("boarddate"));
					board.setBoardcontent(rs.getString("boardcontent"));
					board.setBoardtitle(rs.getString("boardtitle"));
					board.setBoardplay(rs.getInt("boardplay"));
					board.setBoardcount(rs.getInt("boardcount"));
					list.add(board);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		return list;
	}
}
