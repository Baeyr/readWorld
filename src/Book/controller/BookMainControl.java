package Book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Book.DAO.BookDAO;
import Book.vo.Book;
import Member.vo.Genre;
import Member.vo.Member;


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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		
		Genre genre = new Genre();
		genre = (Genre) session.getAttribute("genre");
		
		//TODO 테스트 완료후 주석 해제 후 else 안에 넣기
//		if(vo == null) {
//			request.getRequestDispatcher("/login").forward(request, response);
//		}
//		else {}
			BookDAO dao = new BookDAO();
			
			//신간 정보 저장하기
			int result = dao.bookInsert();
			
			
			//============================================
			List<Book> userBook;
			
			if(genre == null || genre.getGenre().size() <= 0) {
				//베스트 셀러 책 5권 가져오기
				userBook = dao.bookBestSellerList();
			}else {
				//유저 취향 책 가져오기
				userBook = dao.bookUserList(genre);
				if(userBook.size()<=0) {
					userBook = dao.bookBestSellerList();
				}
			}
			
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
			
			request.setAttribute("newBook", newBook);
			request.setAttribute("starBook", starBook);
			request.setAttribute("userBook", userBook);
			
			request.setAttribute("randomBook", randomBook);
			request.setAttribute("developBook", developBook);
			request.setAttribute("comicBook", comicBook);
			request.setAttribute("languageBook", languageBook);
			request.getRequestDispatcher("Main.jsp").forward(request, response);
		}
	
}
