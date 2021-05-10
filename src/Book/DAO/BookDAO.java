package Book.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Book.vo.Book;
import Member.vo.Genre;
import common.JDBCTemplate;

public class BookDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn;
	
	//api에서 신간 정보 저장
	public int bookInsert(){
		conn = JDBCTemplate.getConnection();
		pstmt = null;
		int result = 0;
		String isbnV = "";
		
		String sql = "insert into book values(?,?,?,?,?,?,?,?,?,?,?,0)"; //책정보 insert sql
		String ibsnSearch = "select * from book where isbn=?"; //이미등록되어 있는 경우를 서치
		
		for(int i=1;i<=1;i++) {

			//api 값 불러오기
			String key = "ttbripel10180824001";
			String urlStr = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?"
					+ "ttbkey="+ key +"&QueryType=Bestseller"
							+ "&MaxResults=100"
							+ "&start=" + i
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
				for(int j=0; j<item.size();j++) {
					JSONObject tmp = (JSONObject)item.get(j);
					String isbn = (String)tmp.get("isbn"); //isbn
					String title = (String)tmp.get("title"); //제목
					String author = (String)tmp.get("author"); //작가
					String publisher = (String)tmp.get("publisher"); //출판사
					String pubDate = (String)tmp.get("pubDate"); //출판일
					String description = (String)tmp.get("description"); //요약
					String priceSales = String.valueOf(tmp.get("priceSales")); //가격
					String cover = (String)tmp.get("cover"); //표지
					String customerReviewRank = String.valueOf(tmp.get("customerReviewRank"));//별점
					boolean adult = (Boolean)tmp.get("adult"); //성인유무
					String CategoryName = (String)tmp.get("categoryName"); //카테고리명
					
					//이미 등록된 책일 경우를 서치
					pstmt = conn.prepareStatement(ibsnSearch);
					pstmt.setString(1, isbn);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						isbnV = rs.getString(1);
					}
					
					//이미 등록되었으면 다음으로 넘어감
					if(isbnV.equals(isbn)) {
						continue;
					}
					
					JDBCTemplate.close(rs);
					JDBCTemplate.close(pstmt);
					
					//별점이 null일경우 0 으로 처리
					if(customerReviewRank == null) {
						customerReviewRank = "0";
					}
					
					//책 insert하기
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,isbn);
					pstmt.setString(2,title);
					pstmt.setString(3,author);
					pstmt.setString(4,publisher);
					pstmt.setDate(5,Date.valueOf(pubDate));
					pstmt.setString(6,description);
					pstmt.setInt(7,Integer.parseInt(priceSales));
					pstmt.setString(8,cover);
					pstmt.setInt(9,Integer.parseInt(customerReviewRank));
					pstmt.setString(10,String.valueOf(adult));
					pstmt.setString(11,CategoryName);
					
					result = pstmt.executeUpdate();
					
//					System.out.println("----- "+(j+1)+"번째 검색 결과 -----");
//					System.out.println("제목 : "+title);
//					System.out.println("저자 : "+author);
//					System.out.println("출판사 : "+publisher);
//					System.out.println("출판일 : "+pubDate);
//					System.out.println("요약 : "+description);
//					System.out.println("isbn : "+isbn);
//					System.out.println("가격 : "+priceSales);
//					System.out.println("표지 : "+cover);
//					System.out.println("별점 : "+customerReviewRank);
//					System.out.println("성인 : "+String.valueOf(adult).charAt(0));
//					System.out.println("카테고리 : "+CategoryName);
					
					if(result == 1) {
						System.out.println(j+1+"번째 등록완료");
						JDBCTemplate.commit(conn);
					}else {
						System.out.println(j+1+"번째 등록실패");
						JDBCTemplate.rollback(conn);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	//베스트 셀러 정보 가져오기
	public List<Book> bookBestSellerList(){
		List<Book> list = null;
		
		
		String sql1 = "(select * from book order by ranks desc)";
		String sql2 = "select * from "+sql1+" where adult = 'false' and rownum<=5";
		
		list = ListSave(sql2);
		
		return list;
	}

	//신간 정보 가져오기
	public List<Book> bookNewList(){
		List<Book> list = null;
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yy/MM/dd");
				
		String today= format1.format(new java.util.Date());
		
		String sql1 = "( select * from book where pubDate <= '"+today+"' order by pubDate desc)";
		String sql2 = "select * from "+sql1+" where adult = 'false' and rownum<=5";
		
		list = ListSave(sql2);
		
		return list;
	}
	
	//가장 높은 유저 별점 가져오기
	public List<Book> bookPublicList(){
		List<Book> list = null;
		
		String sql1 = "(select * from book order by siteRanks desc)";
		String sql2 = "select * from "+sql1+" where adult = 'false' and rownum<=5";
		
		list = ListSave(sql2);
		
		return list;
	}
	
	//유저 취향추천 가져오기
	public List<Book> bookUserList(Genre g){
		List<Book> list = null;
		String sql1 ="";
		List<String> genre = g.getGenre();
		
		if(g.getGenre() != null && g.getGenre().size()>0) {
			System.out.println("사이즈 : " + g.getGenre().size());
			int min = 0;
			int size = genre.size()-1;
			int random = (int)(Math.random()*(size - min +1)) + min;
			
			sql1 = "(select * from book where CategoryName like '%"+g.getGenre().get(random)+"%' order by siteRanks desc)";
		}else {
			System.out.println("사이즈 : " + g.getGenre().size());
			sql1 = "(select * from book where CategoryName order by siteRanks desc)";
		}
		
		String sql2 = "select * from "+sql1+" where adult = 'false' and rownum<=5";
		
		list = ListSave(sql2);
		
		return list;
	}

	//랜덤으로 가져오기
	public List<Book> bookRandomList(){
		List<Book> list = null;
		
		String sql1 = "(select * from book order by dbms_random.value)";
		String sql2 = "select * from "+sql1+" where adult = 'false' and rownum<=5";
		
		list = ListSave(sql2);
		
		return list;
	}

	//자기계발 책 가져오기
	public List<Book> bookDevelop(){
		List<Book> list = null;
		
		String sql1 = "(select * from book where CategoryName like '%자기계발%' order by dbms_random.value)";
		String sql2 = "select * from "+sql1+" where adult = 'false' and rownum<=5";
		
		list = ListSave(sql2);
		
		return list;
	}
	
	//만화 가져오기
	public List<Book> bookComic(){
		List<Book> list = null;
		
		String sql1 = "(select * from book where CategoryName like '%만화%' order by dbms_random.value)";
		String sql2 = "select * from "+sql1+" where adult = 'false' and rownum<=5";
		
		list = ListSave(sql2);
		
		return list;
	}
	
	//외국어 가져오기
	public List<Book> bookLanguage(){
			List<Book> list = null;
			
			String sql1 = "(select * from book where CategoryName like '%외국어%' order by dbms_random.value)";
			String sql2 = "select * from "+sql1+" where adult = 'false' and rownum<=5";
			
			list = ListSave(sql2);
			
			return list;
	}

	//카테고리별 책 가져오기
	public List<Book> bookCateList(String cate,int startNum){
			List<Book> list = null;
			
			String sql1 = null;
			String sql2 = null;
			
			
			if(cate == null || cate.equals("전체")) {
				cate = "";
			}
			
			sql1 = "(select rownum r, b.* from (select * from book  where CategoryName like '%"+cate+"%' and adult = 'false' ) b order by title)";
			sql2 = "select * from "+sql1+" where r>="+ (startNum) +"and r<="+(startNum+20);
			
			list = ListSave(sql2);
			
			return list;
	}
	
	//로그인화면 노출 1개 가져오기
	public List<Book> bookRandomOne(){
		List<Book> list = null;
		
		String sql1 = "(select * from book where CategoryName like '%자기계발%' order by dbms_random.value)";
		String sql2 = "select * from "+sql1+" where adult = 'false' and rownum<=1";
		
		list = ListSave(sql2);
		
		return list;
	}
	
	
	//정보 가져오기 함수
	private List<Book> ListSave(String sql) {
		List<Book> list = null;
		
		
		conn = JDBCTemplate.getConnection();
		pstmt = null;
		rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Book>();
			while(rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPubDate(rs.getDate("pubDate"));
				book.setDescription(rs.getString("descriptions"));
				book.setCover(rs.getString("cover"));
				book.setRanks(rs.getInt("ranks"));
				book.setAdult(rs.getString("adult"));
				book.setCategoryName(rs.getString("CategoryName"));
				book.setSiteRanks(rs.getInt("siteranks"));
				
				list.add(book);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		
		return list;
	}

}