package Board.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import Board.DAO.BoardDAO;
import Board.vo.Board;
import Board.vo.Comment;
import Member.vo.Member;

/**
 * Servlet implementation class BoardReadControl
 */
@WebServlet("/boardRead.do")
public class BoardReadControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReadControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		Member member = new Member();
		member = (Member) session.getAttribute("user");
		
		String boardno = request.getParameter("boardno");
		
		Board vo = new Board();
		BoardDAO dao = new BoardDAO();
		
		vo = dao.getBoard(vo); 
				if(vo!=null) {
					vo.setBoardno(Integer.parseInt(boardno));
					vo = dao.getBoard(vo);
					dao.readCount(vo);
					
					if(vo!=null) {
						
						vo.setBoardcontent(replaceParam(vo.getBoardcontent()));
						vo.setBoardtitle(replaceParam(vo.getBoardtitle()));

						request.setAttribute("readboard", vo);
						request.setAttribute("nowId", member.getId());
					}
					// TODO: 게시글 읽기 jsp로 이동 
					request.getRequestDispatcher("/CommentReadControl").forward(request, response); 
				}
		}

		private String replaceParam(String param) {
			String result="";
			
			//System.out.println("전:"+param);
			result = StringEscapeUtils.unescapeHtml(param);
			result = StringEscapeUtils.unescapeHtml(result);
			//System.out.println("후:"+result);
			return result;
		}
	}

