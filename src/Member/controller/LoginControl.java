package Member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.DAO.MemberDAO;
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
		response.sendRedirect("loginPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		Member member = new Member();
		member.setId(id);
		member.setPwd(pwd);
		Member result = new MemberDAO().login(member);
		
		if(result==null || !result.getPwd().equals(pwd)) {
			out.println("<script>alert('아이디 또는 비밀번호를 확인해주세요');</script>");
		} else {
			out.println("<script>alert('로그인 성공!!')</script>");
			out.println("<script>location.href='Main.jsp';</script>");
		}
	
	}

}
