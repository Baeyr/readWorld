package Member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import Book.DAO.BookDAO;
import Book.vo.Book;
import Member.DAO.MemberDAO;
import Member.vo.Genre;
import Member.vo.Member;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao = new BookDAO();
		
		List<Book> randomBookOne = dao.bookRandomOne();
		System.out.println(randomBookOne);
		request.setAttribute("randomBookOne", randomBookOne);
		request.getRequestDispatcher("loginPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		if(id == null || pwd == null) {
			out.println("<script>alert('아이디와 비밀번호를 입력해주세요'); history.back()</script>");
		}
		
		Member member = new Member();
		member.setId(id);
		member.setPwd(pwd);
		Member result = new MemberDAO().login(member);
		Genre genre = new MemberDAO().getGenre(result.getId());			
		
		if(result.getId() == null || !pwd.equals(result.getPwd())) {
			out.println("<script>alert('아이디 또는 비밀번호를 확인해주세요'); history.back()</script>");
		} else {
			System.out.println(genre.getGenre()+"취향");
			out.println("<script>alert('로그인 성공!!')</script>");
			session.setAttribute("logId",id); 
			session.setAttribute("user", result);
			session.setAttribute("genre", genre);
			request.getRequestDispatcher("/main").forward(request, response);
		}
	}

}
