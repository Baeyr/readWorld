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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Board.DAO.BoardDAO;
import Board.vo.Board;

@WebServlet("/boardWrite")
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		BoardDAO dao=new BoardDAO();
		String saveDirectory=getServletContext().getRealPath("/files");
		String encType="utf-8";
		int maxSize=5*1024*1024; //파일크기 확인 후 수정
		
		try { //폴더 없을 경우 생성
			File path=new File(saveDirectory);
			System.out.println("path: "+path);
			
			if(!path.exists()) {
				path.mkdirs();
			}
			
			MultipartRequest mReq=new MultipartRequest(request, saveDirectory, maxSize, encType, new DefaultFileRenamePolicy());
			String fileName="";
			Enumeration<?> files=mReq.getFileNames();
			
			while(files.hasMoreElements()) {
				String name=(String)files.nextElement();
				fileName=mReq.getFilesystemName(name);
				File f1=mReq.getFile(name);
				if(f1==null) {
					System.out.println("파일 업로드 실패");
				} else {
					System.out.println("파일 업로드 성공 : "+f1.length());
				}
			}
			
			Board vo=new Board();

			
			//vo.setId(replaceParam(mReq.getParameter("id")));
			vo.setId("test");
			vo.setBoardcontent(replaceParam(mReq.getParameter("BoardContent")));
			vo.setBoardtitle(replaceParam(mReq.getParameter("BoardTitle")));
			vo.setBoardfile(fileName);
			
			int result=dao.boardWrite(vo);
			PrintWriter out=response.getWriter();
			
			if(result>0) { //등록 성공시 알람창
				System.out.println("입력");
				out.println("<script>alert('등록완료');</script>"); //!!경로 입력!!
				request.getRequestDispatcher("/BoardList.do").forward(request, response);
			} else { //등록 실패시 알람창
				System.out.println("실패");
				out.println("<script>alert('등록실패'); history.back()</script>");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
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
