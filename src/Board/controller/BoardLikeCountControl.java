package Board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.DAO.BoardDAO;
import Board.vo.Board;
import Member.vo.Member;

/**
 * Servlet implementation class BoardLikeCountControl
 */
@WebServlet("/likeCount.do")
public class BoardLikeCountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardLikeCountControl() {
        super();
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
		PrintWriter out = response.getWriter();
		
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		
		Board vo = new Board();
		BoardDAO dao = new BoardDAO();
		
		vo.setBoardno(boardno);
		
		// 게시글 총 추천 수를 구함
		int count = dao.likeCount(vo);
		
		// 게시글 상세 페이지 (BoardRead.jsp)에 추천수 출력
		out.println(count);
		out.close();
	}

}
