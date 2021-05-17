<%@page import="Member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Member vo = (Member)session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대여권 해지</title>
	<style>
		#d1{
			text-align: center;
		}
		.form-control{	
    	position:relative;
    	width: 300px;
    	height: 25px;
    	top: 10px;
    	border-color:  #1C4025;
    	border-radius: 5px;
    	margin-left: 20px;
    }
	</style>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
	<script type = "text/javascript">
		// 비밀번호 미입력시 경고
		function check() {
			console.log(document.Terminationform.pwd.value);
			if (!document.Terminationform.pwd.value) {
				alert("비밀번호를 입력하지 않았습니다.");
				return false;
			}
		}
	</script>
	<div id = "d1">
	<h1>진짜로 해지하시겠습니까?</h1>
	<form name = "Terminationform" action = "Termination" method = "post"  onsubmit = "return check()">
	<input type = "hidden" name = "id" value="<%=vo.getId()%>">
	<input type = "password" name = "pwd" class = "form-control" placeholder = "비밀번호를 입력해주세요"><br><br>
	<input type = "submit" value = "해지">&nbsp;&nbsp;&nbsp;
	<input type = "button" value = "취소" onclick = "location.href= './MyPage'"/>
	</form>
	</div>
</body>
</html>