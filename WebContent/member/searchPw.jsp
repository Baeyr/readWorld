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
		<h3>비밀번호 찾기</h3>
		<form name = "searchFrm">
			<input type = "hidden" name = "id">
			<input type = "hidden" name = "email">
		</form>
		
		<table class = "table" style = "margin:0 auto; width:700px;">
			<tr>
				<td><input type = "text" id = "id" name = "id" class = "form-control" placeholder = "아이디를 입력하세요."></td>
			</tr>
			<tr>
				<td><input type = "text" id = "email" name = "email" class = "form-control" placeholder = "이메일을 입력하세요."></td>
			</tr>
		</table>
		<button type = "button" class = "btn btn-outline-info btn sm" onclick = "searchPw()">검색</button><br><br><br>
		
		
		
	</section>
	
	<a href = "../loginPage.jsp"><b>로그인 화면으로 돌아가기</b></a>
	
	<script>
		function searchPw() {
			var id = document.getElementById("id").value;
			var email = document.getElementById("email").value;
			if(id == "" && email =="") {
				alert("모든 정보를 입력 해주세요.");
				return;
			}
			
			var url = "<%=request.getContextPath()%>/SearchPw"; //서블릿요청

			var title = "searchPw"; // 윈도우 창이름
			
			var status = "left=500px, top=100px, width=800px, height=500px, menubar-no, status=no, scrollbar=yes";
			
			var popup = window.open("" ,title, status); // 빈 창 오픈
			
			searchFrm.id.value = id;
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