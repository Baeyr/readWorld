package Board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Board.DAO.BoardDAO;
import Board.vo.Board;
import Book.DAO.BookDAO;
import Book.vo.Book;

/**
 * Servlet implementation class BoardLikeUpdate
 */
@WebServlet("/boardLike.do")
public class BoardLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardLike() {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		Board vo = new Board();
		BoardDAO dao = new BoardDAO();
		
		int bordno = Integer.parseInt(request.getParameter("boardno"));
		vo.setBoardno(bordno);
		
		int result = 0 ;
		String voResult = "";
		
		// 동일 게시물에 대한 추천 여부 검색
		
		result = dao.likeCheck(vo);
		
//		if(result==0) {	// 추천이 되어있지 않은 상태라면 클릭했을 때 추천
//			dao.likeUpdate(vo);
//			System.out.println(result);
//			System.out.println("추천");
//		} else {	// 추천이 되어있는 상태라면 클릭했을 때 추천 취소
//			dao.likeDelete(vo);
//			System.out.println(result);
//			System.out.println("추천취소");
//		}
		
		if(result>0) { // 추천이 되어있는 상태라면 클릭했을 때 추천 취소
			dao.likeDelete(vo);
			System.out.println(result);
			System.out.println("추천취소");
		} else if (result==0) {	
			dao.likeUpdate(vo);
			System.out.println(result);
			System.out.println("추천");
		} else {
			System.out.println(result);
			System.out.println("추천 기능 확인 바람");
		}
		
		

		if(vo != null) {
			Gson jobj = new GsonBuilder().create();
			voResult = jobj.toJson(vo);
		}
		
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
		

////		**** 로그인 연동 후 수정  *****
//		// 게시물 번호와 id 값을 map에 저장
//		Map<String, Object> m = new HashMap<>();
//		m.put("boardno", request.getParameter("boardno"));
//		m.put("id", request.getParameter("id")); 
//		
//		
//		// 동일 게시물에 대한 이전 추천 여부 검색 			
//		int result = dao.reCheck(m);
//		
//		if(result==0) {	// 추천하지 않았다면 추천 추가
//			dao.likeUpdate(m);
//		} else { // 추천을 하였다면 추천 삭제 
//			dao.likeDelete(m);
//		}
		
	}

}
