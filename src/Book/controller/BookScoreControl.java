package Book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Book.DAO.BookDAO;
import Member.vo.Member;

/**
 * Servlet implementation class BookScoreControl
 */
@WebServlet("/bookScore.do")
public class BookScoreControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookScoreControl() {
        super();
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
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		
		PrintWriter out = response.getWriter(); 
		
		String[] string = request.getParameterValues("star");
		
		
		//확인용 for문
		/*for(int x=0;x<string.length;x++) {
		     
			System.out.println("string: " + string[x]);

		}*/
		
		int score = string.length;
		System.out.println(score); //확인용
		
		String isbn = request.getParameter("isbn");
		System.out.println(isbn); //확인용
		
		int result = 0;
		int result2 = 0;
		
		
		BookDAO dao = new BookDAO();
		result = dao.addScore(isbn, score);
		result2 = dao.addCount(isbn);
		
		dao.avgScore(isbn);
		
		if(result>0&&result2>0) {
			
			System.out.println("별점이 등록됐습니다.");
			out.println("<script>");
			out.println("alert('별점이 등록됐습니다.');");
			out.println("</script>");
			out.println("<script>");
			out.println( "location.href = document.referrer;");
			out.println("</script>");
			out.println("<script>");
			out.println( "history.back();");
			out.println("</script>");

		}
		
		out.close();

	}
	

}
