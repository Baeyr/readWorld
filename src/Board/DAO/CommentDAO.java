package Board.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Board.vo.*;
import common.JDBCTemplate;
 
public class CommentDAO {
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private Connection conn=null;
	
	BoardDAO dao=new BoardDAO();

	public int commentWrite(Comment co) {
		int result=0;
		int cmtrootno=0;
		int cmtstep=0;
		int cmtlevel=0;

		String sql="insert into comment values(?, ?, ?, ?, ?, ?)";
		try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, co.getCommentno());
				pstmt.setInt(2, co.getBoardno());
				pstmt.setString(3, co.getId());
				pstmt.setInt(4, co.getCmtrootno());
				pstmt.setInt(5, co.getCmtstep());
				pstmt.setInt(6, co.getCmtlevel());
				
				cmtrootno=co.getCmtrootno();
				cmtstep=co.getCmtstep()+1;
				cmtlevel=co.getCmtlevel()+1;

				result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
}
