package Member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.DAO.MemberDAO;
import Member.vo.Genre;
import Member.vo.Member;

/**
 * Servlet implementation class MemberJoinControl
 */
@WebServlet("/memberjoin")
public class MemberJoinControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String[] hobbyT = request.getParameterValues("chkBox");
		
		if(hobbyT.length<0) {
			out.println("<script>alert('취향을 1개이상 선택해주세요');</script>");
			out.println("<script>history.back();</script>");
		}
		
		List<String> hobby = new ArrayList<String>();
		
		for(int i=0; i<hobbyT.length;i++) {
			hobby.add(hobbyT[i]);
		}
	
		
		Member member = new Member();
		Genre genre = new Genre();
	
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("password1"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email")+"@"+request.getParameter("email2"));
		member.setPhone(Integer.parseInt(request.getParameter("phone2")+request.getParameter("phone")));

		genre.setGenre(hobby);
		
		int result = new MemberDAO().MemberInsert(member,genre);
		int result2 = 0;
		
		for(int i=0; i<genre.getGenre().size(); i++) {
			result2 = new MemberDAO().generInsert(member.getId(),genre,i);
		}
		
		if(result>0 && result2>0) {
			out.println("<script>alert('회원가입 성공!!');</script>");
			out.println("<script>location.href='./login';</script>");
		} else {
			out.println("<script>alert(회원가입 실패!!');</script>");
			out.println("<script>history.back();</script>");
		}
	}
}
