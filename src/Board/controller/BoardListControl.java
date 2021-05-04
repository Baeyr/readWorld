package Board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.DAO.BoardDAO;
import Board.vo.Board;

/**
 * Servlet implementation class BoardListControl
 */
@WebServlet("/BoardList.do")
public class BoardListControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListControl() {
        super();
        // TODO Auto-generated constructor stub
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

		final int pageSize = 10 ; // 한 페이지당 게시글 수
		final int pageBlock = 3;  // 화면에 나타날 페이지 링크 수
		int cnt = 0 ;	// 총 글 개수
		
		BoardDAO dao = new BoardDAO();
		List<Board> list = null;
		
		// 페이지 번호
		cnt = dao.getBoardCount();
		int pageCnt = (cnt/pageSize) + (cnt%pageSize == 0?0:1);	// 총 페이지 개수
		
		int currentPage = 1; // 현재 페이지: 클릭시 바뀜
		String PageNumber = request.getParameter("PageNumber");
		
		if(PageNumber!=null) {	// 클릭된 숫자가 현재 페이지 
			try {
				currentPage = Integer.parseInt(PageNumber);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int startPage = 1;	// 화면에 나타날 시작 페이지
		int endPage = 1; 	// 화면에 나타날 마지막 페이지
		
		if ((currentPage % pageBlock) == 0) {
			startPage = ((currentPage/pageBlock)-1) * pageBlock  + 1;
		}else {			
			startPage = (currentPage/pageBlock)*pageBlock  + 1;
		}
		endPage = startPage + pageBlock - 1;
		
		//총 페이지 개수보다 endPage가 더 클 수 없음
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		
		int startRnum = (currentPage-1)*pageSize +1;
		int endRnum = startRnum + pageSize - 1;
		
		if(endRnum > cnt) {
			endRnum = cnt;
		}
		
		list = dao.getBoardList(startRnum, endRnum);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("boardList", list);
		
		// TODO: 경로 확인
		// 리스트 페이지로 이동 
		request.getRequestDispatcher("board/BoardList.jsp").forward(request, response);
	}

	
}
