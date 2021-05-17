<%@page import="Book.vo.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>읽편한 세상</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/book/DetailCss.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/a64ef4f2e5.js" crossorigin="anonymous"></script>
</head>
<body>
<% 
	String isbnV = (String)request.getAttribute("isbn");
%>
<jsp:include page="../Header.jsp"></jsp:include>
<div id="body">
	<section>
	
		<article class="bookdetail">
			<hr>
			<c:forEach items="${detailbook}" var="item">
					<h2>${item.title }</h2>
			
			<h2 class="booktitle"></h2>
			<hr>
			<img class="cover" alt="책 표지" src="${item.cover }">
			<div class="bdiv">
				<p> <span>${item.title }</span> <span>${item.author}</span></p>
				<hr>
				<p>카테고리: <%=request.getAttribute("category") %></p>
				<p>발매일: ${item.pubDate }</p> 
				<p>${item.publisher }</p> 
				<p>${item.isbn }</p>
				<p class="agv"> <i class="fas fa-star"></i> <%=request.getAttribute("avg") %>점  (${item.count}명) </p> 
			</div>
			</c:forEach>
			
			<input type="button" id="b1" class="button" 
				value="읽기편한 세상 구독하고 무료로 보기" 
				onclick = "location='<%=request.getContextPath()%>/BookRental?isbn=<%=isbnV%>'">
				
		</article>
		
		
		<article class="bookintro">
			<hr>
			<h2 class="title">책 소개 [요약]</h2>
			<p>
			<c:forEach items="${detailbook}" var="item">
				${item.description }
			</c:forEach>
			</p>
		</article>
		
		<article class="bookscore">
			<hr>
			<h2 class="title">책 평가하기</h2>
			<div class="rating">
				<input type="button" id="b2" class="button" value="등록"> <!-- 등록 버튼 -->
			 	<!-- 해당 별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
			 	<!-- checkbox 숨겨짐 -->
			 	<form id="frm" class="star">
					<c:forEach items="${detailbook}" var="item">
						<input type="hidden" name="isbn" value="${item.isbn }">
					</c:forEach>
					<input type="checkbox" name="star" id="star1" value="1" class="star_radio" title="1점">
					<label for="star1"><i class="fas fa-star"></i></label>
					<input type="checkbox" name="star" id="star2" value="2" class="star_radio" title="2점">
					<label for="star2"><i class="fas fa-star"></i></label>
					<input type="checkbox" name="star" id="star3" value="3" class="star_radio" title="3점">
					<label for="star3"><i class="fas fa-star"></i></label>
					<input type="checkbox" name="star" id="star4" value="4" class="star_radio" title="4점">
					<label for="star4"><i class="fas fa-star"></i></label>
					<input type="checkbox" name="star" id="star5" value="5" class="star_radio" title="5점">
					<label for="star5"><i class="fas fa-star"></i></label>
				</form>
			</div>
		</article>
		
	</section>
	
</div>
<script>
	var form = document.getElementById("frm");
	var enrB = document.getElementById("b2");
	
	enrB.onclick = function(){
		
		form.action ="bookScore.do";
		form.method="post";
		form.submit();
	}
	
//별점 마킹 모듈 - 프로토타입으로 생성
function Rating(){};
Rating.prototype.rate = 0;
Rating.prototype.setRate = function(newrate){
    //별점 마킹 - 클릭한 별 이하 모든 별 체크 처리
    this.rate = newrate;
    let items = document.querySelectorAll('.star_radio');
    items.forEach(function(item, idx){
        if(idx < newrate){
            item.checked = true;
        }else{
            item.checked = false;
        }
    });
}
let rating = new Rating();	//별점 인스턴스 생성
document.addEventListener('DOMContentLoaded', function(){
    //별점선택 이벤트 리스너
    document.querySelector('.rating').addEventListener('click',function(e){
        let elem = e.target;
        if(elem.classList.contains('star_radio')){
            rating.setRate(parseInt(elem.value));
        }
    })
    
    
});
</script>
</body>
</html>