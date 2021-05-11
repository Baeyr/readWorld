package Board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Board.vo.Board;

/**
 * Servlet implementation class BoardImgControl
 */
@WebServlet("/img")
public class BoardImgControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardImgControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		excute(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		excute(request,response);
	}
	
	protected void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
		
		String savePath = "/files";
		
		try {
			//폴더가 없을 경우 생성
			String root = getServletContext().getRealPath("/");
			File path = new File(root + savePath);
			
			if(!path.exists()) {
				path.mkdirs();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		savePath = getServletContext().getRealPath("/files");
		MultipartRequest mRequest = new MultipartRequest(request, 
									savePath,
									10*1024*1024,
									"UTF-8",
									new DefaultFileRenamePolicy());
		
		
		//파일 이름 가져오기
		String fileName = mRequest.getFilesystemName("upload");
		
		//저장
		File f1 = mRequest.getFile(fileName);
		
		
		PrintWriter out = response.getWriter();
//		out.println("{"); 
//		out.println("\"uploaded\": 1,"); 
//		out.println("\"fileName\": \""+fileName+"\","); 
//		out.println("\"url\":\""+savePath+"\""); 
//		out.println("}");
//		out.flush();
//		out.close();
		
		JsonObject json = new JsonObject();
		json.addProperty("uploaded", 1);
		json.addProperty("fileName", fileName);
		json.addProperty("url", savePath);

		out.println(json);
		
		System.out.println("파일이름: " + fileName);
		System.out.println("파일경로: " + savePath);
	}
}
