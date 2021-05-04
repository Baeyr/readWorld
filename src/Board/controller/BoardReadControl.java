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
 * Servlet implementation class BoardReadControl
 */
@WebServlet("/boardRead.do")
public class BoardReadControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReadControl() {
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

	String boardno = request.getParameter("boardno");
	Board vo = new Board();
	BoardDAO dao = new BoardDAO();
	
	vo = dao.getBoard(vo);
	
		if(vo!=null) {
			vo.setBoardno(Integer.parseInt(boardno));
			vo = dao.getBoard(vo);
			if(vo!=null) {
				request.setAttribute("readboard", vo);
				
				// TODO: 게시글 읽기 jsp로 이동 
				request.getRequestDispatcher("board/BoardRead.jsp").forward(request, response); 
			}
		}
	
	}
	
}
