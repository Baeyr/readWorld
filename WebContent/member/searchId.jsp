<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<style>
		*{
			text-align : center;
		}
	</style>
	<section>
	
	
		<form name = "searchFrm">
			<input type = "hidden" name = "name">
			<input type = "hidden" name = "email">
		</form>
		
		<div id = "div1">
		<h3>아이디 찾기</h3>
		
		<table class = "table" style = "margin:0 auto; width:700px">
			<tr>
				<td><input type = "text" name = "name" id = "name" class = "form-control" placeholder = "이름을 입력하세요."></td>
			</tr>
			<tr>
				<td><input type = "text" name = "email" id = "email" class = "form-control" placeholder = "이메일을 입력하세요."></td>
			</tr>
		</table>
		
		<button type = "button" id = "searchBtn"  class = "btn btn-outline-info btn sm" onclick = "searchId()">검색</button>
		</div><br><br><br>
		
		<a href = "../member/searchPw.jsp"><b>비밀번호 찾기</b></a>
	
		<br><br><br>
		
		<a href = "../loginPage.jsp"><b>로그인 화면으로 돌아가기</b></a>
	</section>
	
	<script>
		function searchId() {
			var name = document.getElementById("name").value;
			var email = document.getElementById("email").value;
			if(name == "" && email == "") {
				alert("모든 정보를 입력해주세요.");
				return;
				}
			
			var url = "<%=request.getContextPath()%>/SearchId";  //서블릿 요청url
			
			var title = "searchId" // 윈도우 창 이름
			
			var status = "left=500px, top=100px, width=800px, height=500px, menubar-no, status=no, scrollbar=yes";
			
			var popup = window.open("", title,status); // 빈창 오픈
			
			searchFrm.name.value = name;
			searchFrm.email.value = email;
			
			searchFrm.target = title; // popup창과 form 태그를 연결
			
			//action, method 설정 후 form태그 submit
			searchFrm.action = url;
			searchFrm.method = "post";
			searchFrm.submit();
			}		
		
		
	</script>
</body>
</html>