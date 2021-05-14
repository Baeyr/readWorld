package Board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Board.DAO.CommentDAO;
import Board.vo.Board;
import Board.vo.Comment;

@WebServlet("/commentDelete")
public class CommentDeleteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentDeleteControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		CommentDAO dao=new CommentDAO();
		Comment co = new Comment();
		
		try {
			co.setCommentno(Integer.parseInt(request.getParameter("commentno")));
			System.out.println("지울거"+co);
			int result=dao.commentDelete(co);

			PrintWriter out  = response.getWriter();
			if (result > 0) { 
				out.println("<script>alert('삭제')</script>");
				out.println("<script>history.back();</script>");
			} else {  
				out.println("<script>alert('실패')</script>");
				out.println("<script>history.back();</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
