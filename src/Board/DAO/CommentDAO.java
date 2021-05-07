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

	//코멘트 쓰기
	public int commentWrite(Comment co) {
		int result=0;
		int max=1, levMax=1;
		int step=co.getCmtstep();
		int root=co.getCmtrootno();
		int bno=co.getBoardno();
		System.out.println(root);

		conn=JDBCTemplate.getConnection();
		String sql="insert into cmt values(?,?,?,?,?,?,?)";
		String cmtNo="select NVL(max(commentno),0)+1 from cmt";
		String cmtRoot="select NVL(max(cmtrootno),0)+1 from cmt where boardno=?";
		String cmtLev="select NVL(max(cmtlevel),0)+1 from cmt where boardno=? and cmtrootno=? and cmtstep=?";
		pstmt=null; rs=null;
		System.out.println(12);

		try {
			pstmt=conn.prepareStatement(cmtNo); //글번호 지정
			rs=pstmt.executeQuery();
			if(rs.next()) {
				max=rs.getInt(1);
			} else {
				System.out.println("글번호문제");
				return 0;
			}
			
			pstmt=conn.prepareStatement(cmtLev); 
			pstmt.setInt(1, bno);
			pstmt.setInt(2, root);
			pstmt.setInt(3, step);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				levMax=rs.getInt(1);
			} else {
				System.out.println("레벨문제");
				return 0;
			}
			if(root==0) {
				pstmt=conn.prepareStatement(cmtRoot);
				pstmt.setInt(1, bno);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					root=rs.getInt(1);
				} else {
					System.out.println("루트문제");
					return 0;
				}
			}
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, max);
				pstmt.setInt(2, bno);
				pstmt.setString(3, co.getId());
				pstmt.setString(4, co.getCmtcontent());
				pstmt.setInt(5, root);
				pstmt.setInt(6, step);
				pstmt.setInt(7, levMax);

				result=pstmt.executeUpdate();
				System.out.println("result="+result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return result;
	}
}
