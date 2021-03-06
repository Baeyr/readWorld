package Member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Member.DAO.MemberDAO;
import Member.vo.Member;

/**
 * Servlet implementation class Termination
 */
@WebServlet("/Termination")
public class Termination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Termination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		
		MemberDAO dao = MemberDAO.getInstance();
//		String id = (String) request.getSession().getAttribute("id");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		

		int result = dao.TicketTermination(id, pwd);

		
		Member resultVo = new Member();
		resultVo = new MemberDAO().login(member);
		
		if (result > 0) {
			session.removeAttribute("user");
			session.setAttribute("user", resultVo);
			out.print("<script>alert('대여권이 해지되었습니다.마이페이지로 이동합니다.');</script>");
			out.print("<script>location.href='./MyPage';</script>");
		} else {
			out.print("<script>alert('비밀번호를 다시 확인해주세요.');history.back()</script>");
		}

	
	}

}
