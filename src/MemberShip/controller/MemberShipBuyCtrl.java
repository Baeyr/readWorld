package MemberShip.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberShip.DAO.MemberShipDAO;
import MemberShip.vo.MemberShip;

/**
 * Servlet implementation class MemberShipBuyCtrl
 */
@WebServlet("/membershipBuy")
public class MemberShipBuyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberShipBuyCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int num = Integer.parseInt(request.getParameter("membershipno"));
		
		MemberShip mbs = new MemberShipDAO().memberShipAll(num);
		System.out.println(mbs);
		if(mbs==null) {
			out.println("<script>alert('저장된 회원권이 없습니다.');</script>");
			out.println("<script>history.back();</script>");
		}else {
			request.setAttribute("mbs", mbs);
			request.getRequestDispatcher("/BuyPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
