package Book.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import Search.DAO.SeachDao;

/**
 * Servlet implementation class BookDetailControl
 */
@WebServlet("/bookDetail.do")
public class BookDetailControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		 this.doPost(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		
		Genre genre = new Genre();
		genre = (Genre) session.getAttribute("genre");
		
		Book vo = new Book();
		List<Book> list = null;
		BookDAO bookdao = new BookDAO();
		
		
		// 메인, 검색화면에서 isbn값 전달받기 
		String isbn = request.getParameter("isbn");
		
		if(isbn!=null) {
			list = bookdao.getBook(isbn);
			request.setAttribute("isbn", isbn);
			request.setAttribute("detailbook", list);
			
			String category=list.get(0).getCategoryName(); //카테고리 값 null 에러 때문에 따로 받아옴
			request.setAttribute("category", category);

			
			double avg = Math.round(list.get(0).getAvgsiteranks()*1000/1000.0);
			request.setAttribute("avg", avg);
		}
		
		request.getRequestDispatcher("/book/Detail.jsp").forward(request, response);
		
	}

}
