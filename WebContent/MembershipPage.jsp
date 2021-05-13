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
<style>
.topnav {
    	overflow: hidden;
    	background-color: #1C4025;
    	padding: 0px;
    	position: fixed;
    	top: 0;
    	left: 0px;
 		width: 100%;
 		height : 70px;
 		z-index: 1000;
    }
    
    .topnav a{
    	float: left;
    	display: block;
    	color: #F2EAC2;
    	text-align: center;
    	text-decoration: none;
    	padding: 10px 10px 25px 25px;
    }
    .topnav ul {
    	list-style-type: none;
    	
    }
    
    #profile {
    	float:right;
    	width: 50px;
    	padding-top: 2px;
    	padding-bottom: 10px;
    	margin-right: 8px;
    }
    
    #logB{
    	float:right;
    	margin-top: 15px;
    }
    
   	.contentmain{
   		max-width: 700px;
    	margin: 0px auto;
    	padding: 0 15px;
    	height: 800px;
   	}
   	.mbstitle{
   		position: relative;
   		top: 120px;
   		height: 120px;
   	}
   	.mbstitle > span {
   		font-size: 20px; 
   		font-weight: bold;
   		color: #400101;
   	}
   	.mbstitle > img{
   		float: right;
   		width: 200px;
   		height: 100px;
   	}
    .mbs {
    	position: relative;
    	top: 120px;
    	height: 300px;
    	background-color: #F2EAC2;
    	text-align: center;
    	line-height: 7;
    	padding: 20px;
    	padding-top: 60px;
    }
    
    .memShip {
    	width: 200px;
    	height: 100px;
    	cursor: pointer;
    	margin-left: 8px;
    }
    .information{
    	position: relative;
    	top: 120px;
    	background-color: #1C4025;
    	padding: 18px 16px;
    }
    .information > p {
    	font-size: 14px;
    	font-weight: bold;
    	margin-top: 0px;
    	color: #666;
    }
    .information > ul {
    	font-size: 14px;
    	padding-left: 20px;
    }
    .information li {
    	font-size: 14px;
    	color: #999;
    	margin-bottom: 4px;
    }
</style>
</head>
<body>
	<div class="topnav">
    	<a class="menu" href="${pageContext.request.contextPath}/MyPage" id="profile">
	    	<img  src="<%=request.getContextPath()%>/image/profile.png">
    	</a>
  		<a class="menu" id="logB" name="logoutB" href="${pageContext.request.contextPath}/logout"> 로그아웃 </a>
    	
    	<ul>
    		<li><a name="mainB" href="${pageContext.request.contextPath}/main">메인화면</a></li>
    		<li><a name="genreB" href="${pageContext.request.contextPath}/BookCate">장르별</a></li>
    		<li><a name="boardB" href="${pageContext.request.contextPath}/BoardList.do">게시판</a></li>
    		<li><a name="memberShipB" href="${pageContext.request.contextPath}/membership"> 회원권 구매 </a></li>
    	</ul>
    </div>
    <div class="contentmain">
    	<div class="mbstitle">
    		<span>회원권 구매</span>
    		<img alt="사진없음" src="image/logo2.png">
    	</div>
	    <div class="mbs">
	    	<a href="<%=request.getContextPath()%>/membershipBuy?membershipno=1"><img alt="사진없음" src="image/1week.png" class="memShip"></a>
	    	<a href="<%=request.getContextPath()%>/membershipBuy?membershipno=2"><img alt="사진없음" src="image/1month.png" class="memShip"></a>
	    	<a href="<%=request.getContextPath()%>/membershipBuy?membershipno=3"><img alt="사진없음" src="image/3month.png" class="memShip"></a>
	    	<a href="<%=request.getContextPath()%>/membershipBuy?membershipno=4"><img alt="사진없음" src="image/6month.png" class="memShip"></a>
	    	<a href="<%=request.getContextPath()%>/membershipBuy?membershipno=5"><img alt="사진없음" src="image/12month.png" class="memShip"></a>
	    </div>
	    <div class="information">
	    	<p>이용 안내</p>
	    	<ul>
	    		<li>하나의 회원권만 결제 할 수 있습니다. </li>
	    		<li>모든 회원권은 구매한 날짜로부터 기한이 시작됩니다.</li>
	    		<li>회원권 구매 시 이미 구매 하셨던 고객님은 구매 하실 수 없습니다.</li>
	    		<li>모든 회원권은 결제 후 7일간 미사용 시 1:1문의를 통해 취소할 수 있습니다.</li>
	    	</ul>
	    </div>
    </div>
    <script>
    	
    </script>
</body>
</html>