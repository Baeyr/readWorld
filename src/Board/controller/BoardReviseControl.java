package Board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.DAO.BoardDAO;
import Board.vo.Board;
import Member.vo.Member;

/**
 * Servlet implementation class BoardReviseControl
 */
@WebServlet("/boardrevise.do")
public class BoardReviseControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReviseControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int result = 0 ;
		
		Board vo = new Board();
		Board reboard = new Board();
		BoardDAO dao = new BoardDAO();
		
		Member membervo = new Member();
		
		// 게시물 아이디 
		String id = request.getParameter("id");
		vo.setId(id);
		
		
		// 게시물 번호에 해당하는  내용 담기
		vo.setBoardno(Integer.parseInt(request.getParameter("boardno")));
		reboard = dao.getBoard(vo);
		
		
		
		// 작성자와 로그인한 사용자가 일치할 때만 게시물 수정 가능 
		
		if(!membervo.getId().equals(id)) {
			System.out.println("로그인 정보 불일치");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('작성자만 게시물 수정이 가능합니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			System.out.println("로그인 정보 일치");
			request.setAttribute("reboard", reboard);
			// TODO : ************** 경로 확인하기 ******************
			// 게시글 쓰기 페이지로 이동 !! 
			request.getRequestDispatcher("BoardWrite.jsp").forward(request, response);
		} 
	}
}
