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
<jsp:include page="../Header.jsp"></jsp:include>
<div id="body">
	<section>
	
		<article class="bookdetail">
			<hr>
			<c:forEach items="${detailbook}" var="item">
					<h2>${item.title }</h2>
			
			<h2 class="booktitle"></h2>	<!-- 책 제목 출력 -->
			<hr>
			<img class="cover" alt="책 표지" src="${item.cover }">
			<div class="bdiv">
				<p> <span>${item.title }</span> <span>${item.author}</span></p>
				<hr>
				<p>카테고리 값 출력</p>
				<p>${item.pubDate }</p> 
				<p>${item.publisher }</p> 
				<p>${item.isbn }</p> 
			</div>
			</c:forEach>
		
			<input type="button" id="b1" class="button" value="읽기편한 세상 구독하고 무료로 보기"> <!-- 구독 버튼 -->

		</article>
		
		
		<article class="bookintro">
			<hr>
			<h2 class="title">책 소개 [요약]</h2>
			<p>
			Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
			Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
			when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
			It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. 
			It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, 
			and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
			</p>
		</article>
		
		<article class="bookscore">
			<hr>
			<h2 class="title">책 평가하기</h2>
			<div class="rating">
				<input type="button" id="b2" class="button" value="등록"> <!-- 등록 버튼 -->
			 	<!-- 해당 별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
			 	<!-- checkbox 숨겨짐 -->
			 	<div class="star">
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
				</div>
			</div>
		</article>
		
	</section>
	
</div>


</body>
</html>