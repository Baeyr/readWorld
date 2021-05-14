package Qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QnaWriteGoControl
 */
@WebServlet("/QnaWrite")
public class QnaWriteGoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWriteGoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qna = Integer.parseInt(request.getParameter("qna"));
		
		int ref = 0;
		
		if(request.getParameter("ref") != null) {
			ref = Integer.parseInt(request.getParameter("ref"));
		}else {
			ref = 0;
		}
		
		request.setAttribute("qna", qna);
		request.setAttribute("ref", ref);
		request.getRequestDispatcher("board/BoardWrite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("board/BoardWrite.jsp").forward(request, response);
	}

}
