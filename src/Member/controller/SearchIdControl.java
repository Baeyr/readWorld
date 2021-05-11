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
 * Servlet implementation class SearchIdControl
 */
@WebServlet("/SearchId")
public class SearchIdControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		// 2. 변수저장
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		// 3. 비지니스로직
		MemberDAO dao = new MemberDAO();
		Member member = dao.searchId(name, email); 

		System.out.println("member : " + member);

		// 4. 뷰 처리
		if(member != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/member/IDverification.jsp");
			request.setAttribute("members", member);
			rd.forward(request, response);
		} else {
			request.setAttribute("msg", "정확한 정보를 입력해주세요!");
			request.setAttribute("loc", "/");	
			RequestDispatcher rd = request.getRequestDispatcher("/member/searchIdFail.jsp");
			rd.forward(request, response);

		}
				
	}

}
