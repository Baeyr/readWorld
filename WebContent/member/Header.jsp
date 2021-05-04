<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
    
     #searchF {
		position:relative;
/* 		top:10px;
    	height: 10px;
    	padding-top: 30px;
    	padding-left: 650px; */
    	float:left;
    	width: 50%;

    } 
   
	.searchWindow {
		position:relative;
		top: 100px;
		width:100%;
		height: 200px
	}
    #logo1 {
    	position:relative;
    	height: 120px;
    	float: left;
    	margin-left: 35%;
    	
    	
    	/* padding-top: 100px;
    	padding-right: 30px;
    	padding-left: 500px; */
    	
    }
    
    #search {
    	
    	position:relative;
    	width: 300px;
    	height: 35px;
    	top: 10px;
    	border-color:  #1C4025;
    	border-radius: 5px;
    	margin-left: 20px;
    }
    
    #submit {
    	position:relative;
        border:0;
        background: none;
        top:25px;
        padding-left: 10px;
        padding-top: 10px;
        cursor: pointer;

        
    }
    
    #submitlodo {
    	height: 45px;
		
    }
    
    
    </style>
    
	
    <div class="topnav">
    	<img id="profile" src="<%=request.getContextPath()%>/image/profile.png">
    	<ul>
    		<li><a name="mainB" href="Main.jsp">메인화면</a></li>
    		<li><a name="genreB" href="">장르별</a></li>
    		<li><a name="boardB" href="BookList.jsp" >게시판</a></li>
    		<li><a name="memberShipB" href="MemberShip.jsp"> 회원권 구매 </a></li>
    	</ul>
    	
	
    </div>
	

	<div class="searchWindow">
		<img id="logo1" src="<%=request.getContextPath()%>/image/logo1.png">
	<form id="searchF" action="<%=request.getContextPath()%>/search" method="post">
		<input type="text" id="search" name="search">
		<button id="submit" type="submit"><img id="submitlodo" src="<%=request.getContextPath()%>/image/submitlodo.png"></button>
	</form>
	</div>





	