package Board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.DAO.CommentDAO;
import Board.vo.Board;
import Board.vo.Comment;

@WebServlet("/commentWrite")
public class CommentWriteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentWriteControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	//콘솔 삭제 예정 / 기호 입력 시 변환 추가할지 확인
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentDAO dao=new CommentDAO();
		Comment co = new Comment();
		try {
			co.setBoardno(Integer.parseInt(request.getParameter("boardno")));
			co.setId(request.getParameter("id"));
			co.setCmtcontent(request.getParameter("content"));
			co.setCmtrootno(Integer.parseInt(request.getParameter("cmtrootno")));
			co.setCmtstep(Integer.parseInt(request.getParameter("cmtstep")));
			co.setCmtlevel(Integer.parseInt(request.getParameter("cmtlevel")));
			
			int result=dao.commentWrite(co);
			System.out.println(co);
			
			PrintWriter out  = response.getWriter();
			if (result > 0) { 
				out.println("<script>alert('등록')</script>");
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
