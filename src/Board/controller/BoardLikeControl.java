package Board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Board.DAO.BoardDAO;
import Board.vo.Board;


/**
 * Servlet implementation class BoardLikeControl
 */
@WebServlet("/boardlike.do")
public class BoardLikeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardLikeControl() {
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
	
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		Board vo = new Board();
		BoardDAO dao = new BoardDAO();
		
		vo.setBoardno(boardno);
		
		
		// TODO 
		// 조건문 추가
		// 빈 하트일 때 클릭하면 추천수 + 1
		// 빨간 하트일 때 클릭하면 추천수 -1
		//dao.likeUpdate(vo, id)(vo);
		
//		dao.cancelLike(vo);
		
		int like = 0 ;
		like = dao.likeCount(vo);
		
		System.out.println("추천수:"+like);
		
		JSONObject obj = new JSONObject();
		obj.put("like", like);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(obj);
		
	}

}
