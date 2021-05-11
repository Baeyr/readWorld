package Member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import Member.DAO.MemberDAO;
import Member.vo.Member;

/**
 * Servlet implementation class DeleteControl
 */
@WebServlet("/delete")
public class DeleteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("01");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		System.out.println("02");
		Member member = new Member(); 
		MemberDAO dao = MemberDAO.getInstance();
		member.setId(id); // null
		member.setPwd(pwd); // bbbb
		
		System.out.println("03");
		int result = dao.deleteId(id, pwd);
		
		System.out.println(result); // ?
		System.out.println(id); // ?
		System.out.println(pwd);
		System.out.println("04");
		
		if(result > 0) {
			System.out.println("05"); // ?
			out.print("<script>alert('삭제되었습니다. 맨처음페이지로 이동합니다.');location.href='/main'</script>");
		} else {
			System.out.println("06");
			out.print("<script>alert('비밀번호를 다시 확인해주세요');history.back()</script>");
		}
	}
}