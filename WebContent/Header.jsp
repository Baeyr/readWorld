<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
    
	.all{
		margin: auto;
		margin-left: 5%;
		margin-right: 5%;
    }
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
 		clear: both;
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
    	padding-top: 2px;
    	padding-bottom: 10px;
    	margin-right: 8px;
    }
   
    
    #logB{
    	float:right;
    	margin-top: 15px;
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
    
 	#mbsUser {
    	cursor: pointer;
    }
    
    </style>
    
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
			$("#mbsUser").click(function(e){
				var dd = ${user.membership};
				console.log(dd);
				if(dd == 0){
					location.href='${pageContext.request.contextPath}/membership';
				}else{
					e.preventDefault();
					alert('?????? ???????????? ???????????? ????????????.');
				}
			});
	});
</script>
	
	
<div class="all">
    <div class="topnav">
    	<a class="menu" href="${pageContext.request.contextPath}/MyPage" id="profile">
	    	<img  src="<%=request.getContextPath()%>/image/profile.png">
    	</a>
  		 <a class="menu" id="logB" name="logoutB" href="${pageContext.request.contextPath}/logout"> ???????????? </a>
    	
    	
    	
    	<ul>
    		<li><a class="menu" name="mainB" href="${pageContext.request.contextPath}/main">????????????</a></li>
    		<li><a class="menu" name="genreB" href="${pageContext.request.contextPath}/BookCate">?????????</a></li>
    		<li><a class="menu" name="boardB" href="${pageContext.request.contextPath}/BoardList.do" >?????????</a></li>
    		<li><a class="menu" name="memberShipB" id="mbsUser" href="${pageContext.request.contextPath}/membership"> ????????? ?????? </a></li>
    	</ul>
    	
	
    </div>
	

	<div class="searchWindow">
		<a href="" class="menu">
			<img id="logo1" src="<%=request.getContextPath()%>/image/logo1.png">
		</a>
	<form id="searchF" action="${pageContext.request.contextPath}/search" method="get">
		<input type="text" id="search" name="search">
		<button class="menu" id="submit" type="submit"><img id="submitlodo" src="<%=request.getContextPath()%>/image/submitlodo.png"></button>
	</form>
	</div>
</div>




	