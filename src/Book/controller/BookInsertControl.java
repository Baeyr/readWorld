package Book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Book.DAO.BookDAO;

/**
 * Servlet implementation class BookInsertControl
 */
@WebServlet("/main")
public class BookInsertControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInsertControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		BookDAO bookDao = new BookDAO();
		bookDao.bookInsert();
		
		request.getRequestDispatcher("Main.jsp").forward(request, response);
	}
}
