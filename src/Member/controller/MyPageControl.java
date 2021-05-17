package Member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import Board.DAO.BoardDAO;
import Board.vo.Board;
import Book.DAO.BookDAO;
import Book.vo.Book;
import Book.vo.BookRental;
import Member.vo.Member;
import Purchase.DAO.PurchaseDAO;
import Purchase.vo.PurchaseJoin;
import Qna.DAO.QnaDAO;
import Qna.vo.Qna;

/**
 * Servlet implementation class MyPage
 */
@WebServlet("/MyPage")
public class MyPageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		excute(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		excute(request, response);
	}

	protected void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		
		if(member == null || member.getId() == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후 이용해주세요')</script>");
			out.println("<script>location.href='/SEMI/login'</script>");
			out.close();
		}
		
		
		//==========================================================
		//회원권 구매 내역 검색
		PurchaseDAO pdao = new PurchaseDAO();
		PurchaseJoin pj = new PurchaseJoin();
		List<PurchaseJoin> plist = null;
		plist = pdao.memberShipList(member.getId());
		
		List<Integer> delcheck = new ArrayList<Integer>();
		int plsize = plist.size();
		
		for(int i=0; i < plsize; i++) {
			pj = plist.get(i);
			
			Date date1 = new Date();
			Date date2  = new Date();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String day = sdf.format(pj.getAfterdate());
			
			try {
				date2 = sdf.parse(day);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			System.out.println("날짜확인11:" + date1);
			System.out.println("날짜확인22:" + date2);
			
			if(date1.before(date2)) {
				if(member.getMembership()==0) {
					delcheck.add(1);					
				}else {
					delcheck.add(2);
				}
			}else {
				delcheck.add(2);
			}
		}
		
		System.out.println("체크ㅎ타임~~~:"+delcheck);
		//==========================================================
		//대여한책 검색
		BookDAO dao = new BookDAO();
		List<BookRental> rentalBook = null;
		
		final int pageSize4 = 5 ; // 한 페이지당 게시글 수
		final int pageBlock4 = 3;  // 화면에 나타날 페이지 링크 수
		int cnt4 = 0 ;	// 총 글 개수
		
			// 페이지 번호
		cnt4 = dao.getRentalCount(member.getId());
		System.out.println("cnt확인중~~~~~~~"+cnt4);
		int pageCnt4 = (cnt4/pageSize4) + (cnt4%pageSize4 == 0?0:1);	// 총 페이지 개수
		
		int currentPage4 = 1; // 현재 페이지: 클릭시 바뀜
		
		String PageNumber4 = request.getParameter("PageNumber4");
		
		if(PageNumber4!=null) {	// 클릭된 숫자가 현재 페이지 
			try {
				currentPage4 = Integer.parseInt(PageNumber4);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int startPage4 = 1;	// 화면에 나타날 시작 페이지
		int endPage4 = 1; 	// 화면에 나타날 마지막 페이지
		
		if ((currentPage4 % pageBlock4) == 0) {
			startPage4 = ((currentPage4/pageBlock4)-1) * pageBlock4  + 1;
		}else {			
			startPage4 = (currentPage4/pageBlock4)*pageBlock4  + 1;
		}
		endPage4 = startPage4 + pageBlock4 - 1;
		 
		if(endPage4 > pageCnt4) {
			endPage4 = pageCnt4;
		}
		
		int startRnum4 = (currentPage4-1)*pageSize4 +1;
		int endRnum4 = startRnum4 + pageSize4 - 1;
		
		if(endRnum4 > cnt4) {
			endRnum4 = cnt4;
		}
		
		
		rentalBook = dao.bookRentalList(member.getId(), startRnum4,endRnum4);
		
		
		//==========================================================
		//쓴글 검색
		BoardDAO bdao = new BoardDAO();
		List<Board> myBoard = null;
		
		
		final int pageSize = 3 ; // 한 페이지당 게시글 수
		final int pageBlock = 3;  // 화면에 나타날 페이지 링크 수
		int cnt = 0 ;	// 총 글 개수
		
			// 페이지 번호
		cnt = bdao.getBoardCount(member.getId());
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
		 
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		
		int startRnum = (currentPage-1)*pageSize +1;
		int endRnum = startRnum + pageSize - 1;
		
		if(endRnum > cnt) {
			endRnum = cnt;
		}
		
		
		myBoard = bdao.getMyBoardList(member.getId(), startRnum,endRnum);
		
		
		//==========================================================
		//회원별 문의글 검색
		QnaDAO qdao = new QnaDAO();
		List<Qna> myQna = null;
		
		final int pageSize2 = 3 ; // 한 페이지당 게시글 수
		final int pageBlock2 = 3;  // 화면에 나타날 페이지 링크 수
		
		int cnt2 = 0 ;	// 총 글 개수
		
			// 페이지 번호
		cnt2 = qdao.getBoardCount(member.getId());
		int pageCnt2 = (cnt2/pageSize2) + (cnt2%pageSize2 == 0?0:1);	// 총 페이지 개수
		
		int currentPage2 = 1; // 현재 페이지: 클릭시 바뀜
		String PageNumber2 = request.getParameter("PageNumber2");
		
		if(PageNumber2!=null) {	// 클릭된 숫자가 현재 페이지 
			try {
				currentPage2 = Integer.parseInt(PageNumber2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int startPage2 = 1;	// 화면에 나타날 시작 페이지
		int endPage2 = 1; 	// 화면에 나타날 마지막 페이지
		
		if ((currentPage2 % pageBlock2) == 0) {
			startPage2 = ((currentPage2/pageBlock2)-1) * pageBlock2  + 1;
		}else {			
			startPage2 = (currentPage2/pageBlock2)*pageBlock2  + 1;
		}
		endPage2 = startPage2 + pageBlock2 - 1;
		
		
		if(endPage2 > pageCnt2) {
			endPage2 = pageCnt2;
		}
		
		int startRnum2 = (currentPage2-1)*pageSize2 +1;
		int endRnum2 = startRnum2 + pageSize2 - 1;
		
		if(endRnum2 > cnt2) {
			endRnum2 = cnt2;
		}
				
		myQna = qdao.getBoardList(member.getId(), startRnum2,endRnum2);
			
		//==========================================================
		//전체 회원글검색
		List<Qna> allQna = null;
		
		final int pageSize3 = 5; // 한 페이지당 게시글 수
		final int pageBlock3 = 3;  // 화면에 나타날 페이지 링크 수
		
		int cnt3 = 0 ;	// 총 글 개수
		
			// 페이지 번호
		cnt3 = qdao.getBoardCount(member.getId());
		
		int pageCnt3 = (cnt3/pageSize3) + (cnt3%pageSize3 == 0?0:1);	// 총 페이지 개수
		
		int currentPage3 = 1; // 현재 페이지: 클릭시 바뀜
		String PageNumber3 = request.getParameter("PageNumber3");
		
		if(PageNumber3!=null) {	// 클릭된 숫자가 현재 페이지 
			try {
				currentPage3 = Integer.parseInt(PageNumber3);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		int startPage3 = 1;	// 화면에 나타날 시작 페이지
		int endPage3 = 1; 	// 화면에 나타날 마지막 페이지
		
		if ((currentPage3 % pageBlock3) == 0) {
			startPage3 = ((currentPage3/pageBlock3)-1) * pageBlock3  + 1;
		}else {			
			startPage3 = (currentPage3/pageBlock3)*pageBlock3  + 1;
		}
		endPage3 = startPage3 + pageBlock3 - 1;
		
		
		if(endPage3 > pageCnt3) {
			endPage3 = pageCnt3;
		}
		
		int startRnum3 = (currentPage3-1)*pageSize3 +1;
		int endRnum3 = startRnum3 + pageSize3 - 1;
		
		if(endRnum3 > cnt3) {
			endRnum3 = cnt3;
		}
				
		allQna = qdao.getBoardList(member.getId(), startRnum3,endRnum3);
		
		
		request.setAttribute("purchases", plist);
		request.setAttribute("delM", delcheck);
		
		request.setAttribute("rentalBook", rentalBook);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("myBoard", myBoard);
		
		request.setAttribute("startPage2", startPage2);
		request.setAttribute("endPage2", endPage2);
		request.setAttribute("currentPage2", currentPage2);
		request.setAttribute("pageCnt2", pageCnt2);
		
		request.setAttribute("startPage3", startPage3);
		request.setAttribute("endPage3", endPage3);
		request.setAttribute("currentPage3", currentPage3);
		request.setAttribute("pageCnt3", pageCnt3);
		
		request.setAttribute("startPage4", startPage2);
		request.setAttribute("endPage4", endPage2);
		request.setAttribute("currentPage4", currentPage2);
		request.setAttribute("pageCnt4", pageCnt2);
		
		request.setAttribute("myQna", myQna);
		request.setAttribute("allQna",allQna);
		
		request.setAttribute("msckeck", member.getMembership());
		request.setAttribute("id", member.getId());
		request.getRequestDispatcher("/MyPage.jsp").forward(request, response);
	}
	
}
