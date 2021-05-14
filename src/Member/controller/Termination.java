package Member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.DAO.MemberDAO;

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

		MemberDAO dao = MemberDAO.getInstance();
//		String id = (String) request.getSession().getAttribute("id");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		

		int result = dao.TicketTermination(id, pwd);
		int result2 = dao.TicketTermination2(id, pwd);
		System.out.println(result);
		System.out.println("id:" + id);

		if (result > 0 && result2 > 0) {
			System.out.println("00");
			out.print("<script>alert('대여권이 해지되었습니다.마이페이지로 이동합니다.');</script>");
			out.print("<script>location.href='./MyPage';</script>");
		} else {
			System.out.println("01");
			out.print("<script>alert('비밀번호를 다시 확인해주세요.');history.back()</script>");
		}

	
	}

}
