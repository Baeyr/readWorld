package Search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		List<Book> list = new ArrayList<Book>();
		List<Board> list1 = new ArrayList<Board>();
		String search = request.getParameter("search");
		list = new SeachDao().bookSearch(search);
		list1 = new SeachDao().boardSearch(search);
		if(search=="") {
			request.setAttribute("result", "검색결과가없습니다.");
			request.getRequestDispatcher("searchPage.jsp").forward(request, response);
		}else {
			request.setAttribute("searchBook", list);
			request.setAttribute("searchboard", list1);
			request.setAttribute("result", "'" + search + "' 검색결과");
			request.getRequestDispatcher("searchPage.jsp").forward(request, response);
		}
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
