<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
    
    
    
    .topnav {
    	overflow: hidden;
    	background-color: #1C4025;
    	padding: 0px;
    }
    
    .topnav a{
    	float: left;
    	color: #F2EAC2;
    	text-align: center;
    	text-decoration: none;
    	padding: 25px 16px; 
    }
    
    ul {
    	list-style-type: none;
    	
    }
    

    
    </style>
    <nav class="topnav">
    	<ul>
    		<li><a name="mainB" href="Main.jsp">메인화면</a></li>
    		<li><a name="genreB" href="">장르별</a></li>
    		<li><a name="boardB" href="BookList.jsp" >게시판</a></li>
    		<li><a name="memberShipB" href="MemberShip.jsp"> 회원권 구매 </a></li>
    	</ul>
    </nav>

	<form>
		<input type="text" name="search">
		<button type="submit"></button>
	</form>