package Book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Book.DAO.BookDAO;
import Book.vo.Book;

/**
 * Servlet implementation class BookCateMoreControl
 */
@WebServlet("/BookCateMore")
public class BookCateMoreControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCateMoreControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		excute(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		excute(request,response);
	}

	protected void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao = new BookDAO();
		String cate = request.getParameter("cate");
		int startNum = Integer.parseInt((String) request.getParameter("startNum"));
		String result = "";
		
		
		//전체 책 가져오기
		List<Book> cateBook = dao.bookCateList(cate,(startNum+1));
		
		System.out.println(startNum);
		System.out.println(cateBook.size()+"개");
		
		if(cateBook != null) {
			Gson jobj = new GsonBuilder().create();
			result = jobj.toJson(cateBook);
		}
		
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
}
