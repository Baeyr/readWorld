package Board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.DAO.BoardDAO;
import Board.vo.Board;

/**
 * Servlet implementation class BoardModifyControl
 */
@WebServlet("/BoardModify.do")
public class BoardModifyControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModifyControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Board vo = new Board();
		Board modvo = new Board();
		BoardDAO dao = new BoardDAO();
		
		vo.setBoardno(Integer.parseInt(request.getParameter("boardno")));
		vo.setBoardcontent(request.getParameter("boardcontent"));
		vo.setBoardfile(request.getParameter("boardfile"));
		modvo = dao.getBoard(vo);
		
		//TODO:로그인 연동 코드 추가
		
		request.setAttribute("modvo",modvo);
		request.getRequestDispatcher("board/BoardWrite.jsp").forward(request, response);
	}

}
