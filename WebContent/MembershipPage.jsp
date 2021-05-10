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
    	padding-top: 10px;
    }
</style>
</head>
<body>
	<div class="topnav">
    	<img id="profile" src="<%=request.getContextPath()%>/image/profile.png">
    	<ul>
    		<li><a name="mainB" href="Main.jsp">메인화면</a></li>
    		<li><a name="genreB" href="">장르별</a></li>
    		<li><a name="boardB" href="BookList.jsp" >게시판</a></li>
    		<li><a name="memberShipB" href="MemberShip.jsp"> 회원권 구매 </a></li>
    	</ul>
    </div>


</body>
</html> 