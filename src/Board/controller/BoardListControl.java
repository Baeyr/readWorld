package Board.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import Member.vo.Member;

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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		String search = request.getParameter("search");
		
		if(member == null || member.getId() == null) {
			out.println("<script>alert('로그인 후 이용해주세요')</script>");
			out.println("<script>location.href='/SEMI/login'</script>");
			out.close();
			//request.getRequestDispatcher("/login").forward(request, response);
		}
		if (search !=null) {
		} else {
			search = null;
		}
		
		
		int pageSize = 10 ; // 한 페이지당 게시글 수
		final int pageBlock = 3;  // 화면에 나타날 페이지 링크 수
		int cnt = 0 ;	// 총 글 개수
		int currentPage = 1; // 현재 페이지: 클릭시 바뀜
		
		BoardDAO dao = new BoardDAO();
		List<Board> list = null;
		List<Integer> cnt1=new ArrayList<Integer>();
		cnt1=dao.fixedList();
		int cnt2=cnt1.size();
		System.out.println(cnt2);
		
		// 페이지 번호
		if (search == null) {
			System.out.println("검색이 없을때");
			cnt = dao.getBoardCount();
		} else {
			System.out.println("검색했을때");
			cnt = dao.getBoardCount2(search);
		}
		int pageCnt = (cnt/pageSize) + (cnt%pageSize == 0?0:1);	
		System.out.println("페이지"+cnt);
		System.out.println("페이징"+pageCnt);		
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
		
		int startRnum = 0;
		int endRnum = 0;
		if (search == null) {
			System.out.println("검색이 없을때1");
		if ((currentPage % pageBlock) == 0) {
			startPage = ((currentPage/pageBlock)-1) * pageBlock  + 1;
		}else {			
			startPage = (currentPage/pageBlock)*pageBlock  + 1;
		}
		endPage = startPage + pageBlock - 1;
		System.out.println("페이지"+startPage+" 끝"+endPage+" 합"+pageCnt);
		
		//총 페이지 개수보다 endPage가 더 클 수 없음
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		startRnum = cnt - cnt2;
			endRnum = startRnum - 6;
		
		if(currentPage==1) {
			System.out.println("시작1 "+startRnum+"끝1 "+endRnum);
		} else if(currentPage==2) {
			pageSize = 7;
			startRnum -= pageSize;
			endRnum = startRnum-pageSize-2;
			System.out.println("시작2 "+startRnum+"끝2 "+endRnum);
		} else {
			pageSize=10;
			startRnum = startRnum - pageSize*(currentPage-2) - 7;
			endRnum = startRnum - pageSize*(currentPage-2) + 1;
			System.out.println("시작3 "+startRnum+"끝3 "+endRnum);
		}
			
		} else {
			System.out.println("검색했을때1");
			if ((currentPage % pageBlock) == 0) {
				startPage = ((currentPage / pageBlock) - 1) * pageBlock + 1;
			} else {
				startPage = (currentPage / pageBlock) * pageBlock + 1;
			
			}
			endPage = startPage + pageBlock - 1;

			if (endPage > pageCnt) {
				endPage = pageCnt;
			}

			

			startRnum = (currentPage - 1) * pageSize + 1;
			endRnum = startRnum + pageSize - 1;

		

			if (endRnum > cnt) {
				endRnum = cnt;
			}
		}
			
		if(search !=null && !search.equals("")) {
			list = dao.getBoardList(startRnum, endRnum, search);

		}else {
			list = dao.getBoardList(startRnum, endRnum);
		}
		request.setAttribute("search", search);
						
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
