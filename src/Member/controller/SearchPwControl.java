package Member.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.DAO.MemberDAO;
import Member.vo.Member;

/**
 * Servlet implementation class SearchPwControl
 */
@WebServlet("/SearchPw")
public class SearchPwControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPwControl() {
        super();
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
		doGet(request,response);
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
			
	// 2. 변수저장
		String id = request.getParameter("id2");
		String email = request.getParameter("email2");

	// 3. 비지니스로직
		MemberDAO dao = new MemberDAO();
		Member member = dao.searchPw(id,email);
		
	// 4. 뷰 처리
		System.out.println(id);
		System.out.println(email);
		System.out.println(member);
		if(member != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/member/Pwverification.jsp");
			request.setAttribute("members", member);
			rd.forward(request, response);
		} else {
			request.setAttribute("msg", "정확한 정보를 입력해주세요!");
			request.setAttribute("loc", "/");
			RequestDispatcher rd = request.getRequestDispatcher("/member/searchPwFail.jsp");
			rd.forward(request, response);
		}
}

}
