
<%@page import="Member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!DOCTYPE html>
 <%	
		request.setCharacterEncoding("UTF-8");
    	Member member = (Member)request.getAttribute("members");
    %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		*{
			margin : 0 auto;
			padding : 0;
		}
		
		#userName{
			text-align : center;
			font-size : 15px;
		}
		
		#userId{
			text-align :center;
		}
		
	</style>
	
	
</head>
<body>
	<div class = "wrap">
	<div id = "userName">[<%=member.getName()%>]님의 아이디는</div><br>
	<div id = "userId">[<%=member.getId() %>]입니다.</div><br><br><br>
	</div>
</body>
</html>