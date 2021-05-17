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
import Member.vo.Genre;
import Member.vo.Member;

/**
 * Servlet implementation class BookRentalControl
 */
@WebServlet("/BookRental")
public class BookRentalControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookRentalControl() {
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
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		
		BookDAO dao = new BookDAO();
		int result = 0;
		
		
		if(member == null || member.getId() == null) {
			out.println("<script>alert('로그인 후 이용해주세요')</script>");
			out.println("<script>location.href='/SEMI/login'</script>");
		}
		
		if(member.getMembership() == 0) {
			out.println("<script>alert('회원권 구매 후 이용해주세요')</script>");
			out.println("<script>location.href='/SEMI/membership'</script>");
		}else {
			String isbn = request.getParameter("isbn");
			
			
			//이미 등록했는지 확인
			result = dao.bookRentalCkeck(member.getId(),isbn);
			if(result == 1) {
				out.println("<script>alert('이미 대여한 책입니다.')</script>");
				out.println("<script>history.back()</script>");
			}else {
				//북렌탈 등록
				result = dao.bookRental(member,isbn);
				
				if(result == 0) {
					out.println("<script>alert('대여에 실패하였습니다.')</script>");
				}else {
					out.println("<script>alert('책을 대여했습니다!')</script>");
					out.println("<script>location.href='/SEMI/bookDetail.do?isbn="+isbn+"'</script>");
					
				}
			}
		}
	}

}
