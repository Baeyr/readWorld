package Purchase.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Member.DAO.MemberDAO;
import Member.vo.Member;
import Purchase.DAO.PurchaseDAO;

/**
 * Servlet implementation class PurchaseCtrl
 */
@WebServlet("/purchase")
public class PurchaseCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseCtrl() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int result = 0;
		int result1 = 0;
		Member vo = new Member();
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); 
		
		// 회원권 구매테이블 값 전달
		String id = request.getParameter("id");
		int num = Integer.parseInt(request.getParameter("membershipno"));
		String date = request.getParameter("beforedate");
		String date1 = request.getParameter("afterdate");
		Date beforedate = Date.valueOf(date);
		Date afterdate = Date.valueOf(date1);

		// 세션 초기화 후 다시 세션에 저장  // 회원권 보유 시 회원권 페이지 못들어가게 막기 위함
		vo.setId(id);
		Member resultVo = new Member();
		
		result = new PurchaseDAO().MembershipBuy(id, num, beforedate, afterdate);
		result1 = new PurchaseDAO().MbsNoUpdate(num, id);
		resultVo = new MemberDAO().login(vo);
		System.out.println(resultVo);
		if(result>0 && result1>0) {
			session.removeAttribute("user");
			session.setAttribute("user", resultVo);
			out.println("<script>alert('회원권 구입 완료');</script>");
			out.println("<script>location.href='./main';</script>");
		}else {
			out.println("<script>alert('오류로 인해 결제가 안됐습니다.');</script>");
			out.println("<script>location.href='./main';</script>");
		}
	}

}
