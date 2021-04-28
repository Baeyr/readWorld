package Book.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Book.vo.Book;
import common.JDBCTemplate;

public class BookDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn = JDBCTemplate.getConnection();
	
	//api에서 신간 정보 추출
	public List<Book> bookInsert(Connection conn){
		List<Book> book = null;
		
		String key = "ttbripel10180824001";
		String urlStr = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?"
				+ "ttbkey="+ key +"&QueryType=ItemNewAll"
						+ "&MaxResults=5"
						+ "&start=1"
						+ "&SearchTarget=eBook"
						+ "&output=JS"
						+ "&Version=20131101";
		
		BufferedReader br = null;
		String json = null;
		
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + urlConnection.getResponseCode());
			
	        if(urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300) {
	        	br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	        } else {
	        	br = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
	        }
			
	        json = br.readLine();
			
	        System.out.println(json);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(json);
			
			JSONArray item = (JSONArray)obj.get("item");
			for(int i=0; i<item.size();i++) {
				JSONObject tmp = (JSONObject)item.get(i);
				String title = (String)tmp.get("title"); //제목
				String author = (String)tmp.get("author"); //작가
				String publisher = (String)tmp.get("publisher"); //출판사
				String pubDate = (String)tmp.get("pubDate"); //출판일
				String description = (String)tmp.get("description"); //요약
				String isbn = (String) tmp.get("isbn"); //isbn
				String pricesales = (String) tmp.get("pricesales"); //가격
				String cover = (String)tmp.get("cover"); //표지
				String CustomerReviewRank = (String)tmp.get("CustomerReviewRank"); //별점
				boolean adult = (Boolean)tmp.get("adult"); //성인유무
				String CategoryName = (String)tmp.get("categoryName"); //카테고리명
				
				System.out.println("----- "+(i+1)+"번째 검색 결과 -----");
				System.out.println("제목 : "+title);
				System.out.println("저자 : "+author);
				System.out.println("출판사 : "+publisher);
				System.out.println("출판일 : "+pubDate);
				System.out.println("요약 : "+description);
				System.out.println("isbn : "+isbn);
				System.out.println("표지 : "+cover);
				System.out.println("별점 : "+CustomerReviewRank);
				System.out.println("성인 : "+adult);
				System.out.println("카테고리 : "+CategoryName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return book;
	}
}
