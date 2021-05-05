package Book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Book.DAO.BookDAO;
import Book.vo.Book;


/**
 * Servlet implementation class BookInsertControl
 */
@WebServlet("/main")
public class BookMainControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookMainControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		excute(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		excute(request,response);
	}
	
	protected void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao = new BookDAO();
		
		//신간 정보 저장하기
		int result = dao.bookInsert();
		
		
		//============================================
		
		
		//베스트 셀러 책 5권 가져오기
		List<Book> bestSeller = dao.bookBestSellerList();

		//신간 5궈 가져오기
		List<Book> newBook = dao.bookNewList();
		
		//가장높은 별점 5궈 가져오기
		List<Book> starBook = dao.bookPublicList();
		
		
		//============================================
		
		
		//랜덤으로 5권 가져오기
		List<Book> randomBook = dao.bookRandomList();
		
		//자기계발 5궈 가져오기
		List<Book> developBook = dao.bookDevelop();
		
		//만화 5권 가져오기
		List<Book> comicBook = dao.bookComic();
		
		//외국어 5궈 가져오기
		List<Book> languageBook = dao.bookLanguage();
		
		request.setAttribute("bestSeller", bestSeller);
		request.setAttribute("newBook", newBook);
		request.setAttribute("starBook", starBook);
		request.setAttribute("randomBook", randomBook);
		request.setAttribute("developBook", developBook);
		request.setAttribute("comicBook", comicBook);
		request.setAttribute("languageBook", languageBook);
		request.getRequestDispatcher("Main.jsp").forward(request, response);
	}
}
