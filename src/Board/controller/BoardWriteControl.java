package Board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Board.DAO.BoardDAO;
import Board.vo.Board;
import Member.vo.Member;

@WebServlet("/boardWrite.do")
public class BoardWriteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardWriteControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	// !!filepath 포함 확인부터!! 콘솔 삭제 예정
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		
		BoardDAO dao=new BoardDAO();
		Board vo=new Board();
		
		int result = 0;
		
		PrintWriter out=response.getWriter();

		vo.setId(member.getId());
		vo.setBoardcontent(replaceParam(request.getParameter("BoardContent")));
		vo.setBoardtitle(replaceParam(request.getParameter("BoardTitle")));
		
		String mcheck = request.getParameter("modiCheck");
	
		System.out.println("mcheck확인~~~~~~~"+mcheck);
		if(mcheck.equals("notmodify")) {	// 새로운 게시글 작성하는 경우 
			result=dao.boardWrite(vo);
		} 
		else {	//글을 수정하는 경우
			int boardbno = Integer.parseInt(request.getParameter("boardno")) ;
			vo.setBoardno(boardbno);
			result = dao.boardReWrite(vo);
			System.out.println("수정후 : " + vo.getBoardcontent());
		}
		
		
		if(result>0) { //등록 성공시 알람창
			System.out.println("입력");
			System.out.println(mcheck);
			out.println("<script>alert('등록완료');</script>"); //!!경로 입력!!
			request.getRequestDispatcher("/BoardList.do").forward(request, response);
		} else { //등록 실패시 알람창
			System.out.println("실패");
			System.out.println(mcheck);
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
