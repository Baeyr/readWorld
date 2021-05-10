package Board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Board.DAO.BoardDAO;
import Board.vo.Board;
import Board.vo.Comment;
 
/**
 * Servlet implementation class CommentReadControl
 */

@WebServlet("/CommentReadControl")
public class CommentReadControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentReadControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardno=Integer.parseInt(request.getParameter("boardno"));
		Comment cmt = new Comment(); 
		List<Comment> co=new ArrayList<Comment>();
		Board vo=(Board)request.getAttribute("readboard");
		BoardDAO dao=new BoardDAO();
		
			cmt.setBoardno(vo.getBoardno());
			co=dao.getComment(cmt);
			
			if(co!=null) { //여기서 수정
				request.setAttribute("cmt", co);
				request.setAttribute("readboard", vo);
				request.getRequestDispatcher("/board/BoardRead.jsp").forward(request, response); 
			} else {
				request.setAttribute("readboard", vo);
				request.getRequestDispatcher("/board/BoardRead.jsp").forward(request, response); 
				
			}
	}
}
