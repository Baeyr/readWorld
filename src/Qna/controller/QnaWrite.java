package Qna.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Member.vo.Member;
import Qna.DAO.QnaDAO;
import Qna.vo.Qna;

/**
 * Servlet implementation class QnaWrite
 */
@WebServlet("/Qna")
public class QnaWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWrite() {
        super();
        // TODO Auto-generated constructor stub
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
		excute(request, response);
	}

	protected void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		
		QnaDAO dao = new QnaDAO();
		Qna qna = new Qna();
		
		int result = 0;
		
		qna.setId(member.getId());
		qna.setQnacontent(replaceParam(request.getParameter("BoardContent")));
		qna.setQnatitle(replaceParam(request.getParameter("BoardTitle")));
		
		System.out.println("null체크 과연 무슨값일까 : " + request.getParameter("ref"));
		
		
		int ref = Integer.parseInt(request.getParameter("ref"));
		
		result=dao.qnaWrite(qna, ref);
		
		if(result>0) { //등록 성공시 알람창
			System.out.println("입력");
			out.println("<script>alert('등록완료');</script>"); //!!경로 입력!!
			request.getRequestDispatcher("/MyPage").forward(request, response);
		} else { //등록 실패시 알람창
			System.out.println("실패");
			out.println("<script>alert('등록실패'); history.back()</script>");
		}
	}
	
	private String replaceParam(String param) {
		String result="";
		
		if(param!=null&&param!="") {
			result=param.replaceAll("<", "&lt;");
			result=result.replaceAll(">", "&gt;");
			result=result.replaceAll("&", "&amp;");
			result=result.replaceAll("\"", "&quot;");
		}
		return result;
	}
}
