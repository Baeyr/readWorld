package Member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.DAO.MemberDAO;

/**
 * Servlet implementation class RentalticketTermination
 */
@WebServlet("/RTermination")
public class RTermination extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RTermination() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/RentalticketTermination.jsp").forward(request, response);
			
	}
}
