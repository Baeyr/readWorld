package Board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.DAO.BoardDAO;
import Board.vo.Board;
import Board.vo.Comment;
 
/**
 * Servlet implementation class CommentReadControl
 */
// CommentReadControl 을 연결하는 곳이 없네요.
@WebServlet("/CommentReadControl")
public class CommentReadControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentReadControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);   //forward 형태로 오면 doGet으로 들어오는데. 여기 이줄 없어서.. 안된것입니다.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardno=Integer.parseInt(request.getParameter("boardno"));
		System.out.println(boardno);
		Comment cmt=new Comment(); 
		List<Comment> co=new ArrayList<Comment>();
		Board vo=(Board)request.getAttribute("readboard");
		System.out.println("vo : "+vo);
		BoardDAO dao=new BoardDAO();
		
			cmt.setBoardno(vo.getBoardno());
			System.out.println("cmtno:"+cmt.getBoardno());
			co=dao.getComment(cmt);
			System.out.println(co);
			if(co!=null) { //여기서 수정
				System.out.println("aaaa:"+cmt);
				request.setAttribute("cmt", co);
				request.setAttribute("readboard", vo);
				request.getRequestDispatcher("/board/BoardRead.jsp").forward(request, response); 
			} else {
				request.setAttribute("readboard", vo);
				request.getRequestDispatcher("/board/BoardRead.jsp").forward(request, response); 
				
			}
	}
}
