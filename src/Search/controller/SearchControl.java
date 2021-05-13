package Search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import Board.vo.Board;
import Book.vo.Book;
import Search.DAO.SeachDao;

/**
 * Servlet implementation class SearchControl
 */
@WebServlet("/search")
public class SearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
				
		List<Book> list = new ArrayList<Book>();
		List<Board> list1 = new ArrayList<Board>();
		List<Board> list2 = new ArrayList<Board>();
		Board vo = null;
		
		String search = request.getParameter("search");
		list = new SeachDao().bookSearch(search);
		list1 = new SeachDao().boardSearch(search);
		
		if(list==null || list1==null) {
			out.println("<script>alert('검색하신 결과가 없습니다. 다시 입력해주세요.')</script>");
			out.println("<script>history.back();</script>");
		}else {
			for(int i=0; i<list1.size();i++) {
				vo = new Board();
				String content = list1.get(i).getBoardcontent();
				vo.setBoardcontent(replaceParam(content));
				list2.add(vo);
			}
			
			if(search=="") {
				request.setAttribute("result", "검색결과가없습니다.");
				request.getRequestDispatcher("searchPage.jsp").forward(request, response);
			}else {
				request.setAttribute("searchBook", list);
				request.setAttribute("searchboard", list1);
				request.setAttribute("boardcnt", list2);
				request.setAttribute("search", search);
				request.setAttribute("result", "'" + search + "' 검색결과");
				request.getRequestDispatcher("/book/searchPage.jsp").forward(request, response);
			}
		}
	}

	private String replaceParam(String param) {
		String result="";
		
		result = StringEscapeUtils.unescapeHtml(param);
		result = StringEscapeUtils.unescapeHtml(result);
				
		return result;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		doGet(request, response);
	}

}
