<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>읽편한세상</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- **리셋/스타일/웹아이콘/폰트 경로 확인** -->
<link rel="stylesheet" href="./reset.css">
<link rel="stylesheet" href="./loginStyle.css?asd">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@300&display=swap"
	rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<!-- 이분할 왼쪽 -->
	<div class="left-side">
		<!--좌측 상단 문구  -->
		<div class="top">
			<br>
			<p class="intro">언제나 함께하는 도서관,</p>
			
			<br>
			<p class="intro" id="intro">언제나 함께하는 읽기 편한</p>
			<br>
		</div>
		<!-- 중앙 추천도서 슬라이드 -->
		<div class="recommend">
			<div class='image-container'>
				<ul class='slider-container'>
					<li class='slide slide1'>
						<div>
							<c:forEach items="${randomBookOne}" var="item">
								<img class="bookimg" src="${item.cover}"/>
								<p class="showBook1">지금 같이하면 읽을 수 있는 독서!</p>
								<p class="showBook2">그 외에도 많은 책이 여러분을 기다립니다.</p>
							</c:forEach>
						</div>
					</li> 
					<li class='slide'><img class="bookimg2" src="image/p4.png"></li>
					<li class='slide'><img src="image/p3.png"></li>
				</ul>
				<p class="pager"></p>
				<i class="fas fa-angle-left fa-9x" id="prev"></i>
				<i class="fas fa-angle-right fa-9x" id="next"></i>
			</div>
		</div>
	</div>
	<!-- 이분할 오른쪽 -->
	<div class="right-side">
		<!-- 우측 상단 로고 -->
		<div class="logo">
			<img src="image/logo2.png" id="logoImg">
		</div>
		<!-- 우측 중앙 로그인/회원가입 -->
		<form action="<%=request.getContextPath() %>/login" method="POST">
			<input type="text" placeholder="아이디" name="id" id="id">
			<input type="password" placeholder="비밀번호" name="pwd" id="pwd"><br><br>
			<a href="JoinGo" id="join">아직 회원이 아니신가요? &nbsp;</a><br>
			<a href="#" id="find">기억이 나지 않는다면 &nbsp;</a><br>
			<!-- 로그인 버튼 -->
			<div class="loginBtn">
				<input type="submit" id="loginBtn" /> <i class="fas fa-play-circle fa-5x" id="loginIcon"></i>
			</div>
		</form>
	</div>
	<script src="./loginScript.js"></script>
</body>
</html>