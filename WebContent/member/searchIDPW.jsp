<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../Header.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <jsp:include page="Header.jsp"></jsp:include> --%>
	<br><br><br>
	<style>
		#d1{
			text-align : center;
		}
		.form-control{	
    	position:relative;
    	width: 300px;
    	height: 25px;
    	top: 10px;
    	left:1100px;
    	border-color:  #1C4025;
    	border-radius: 5px;
    	margin-left: 20px;
    	}
	</style>
	<section>
	<div id = "d1">
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
		<br>
		<button type = "button" id = "searchBtn"  class = "btn btn-outline-info btn sm" onclick = "searchId()">아이디 찾기</button>
		</div><br>
	
				<h3>비밀번호 찾기</h3>
		<form name = "searchFrm2">
			<input type = "hidden" name = "id2">
			<input type = "hidden" name = "email2">
		</form>
		
		<table class = "table" style = "margin:0 auto; width:700px;">
			<tr>
				<td><input type = "text" id = "id2" name = "id2" class = "form-control" placeholder = "아이디를 입력하세요."></td>
			</tr>
			<tr>
				<td><input type = "text" id = "email2" name = "email2" class = "form-control" placeholder = "이메일을 입력하세요."></td>
			</tr>
		</table>
		<br>
		<button type = "button" class = "btn btn-outline-info btn sm" onclick = "searchPw()">비밀번호 찾기</button><br><br><br>
		</div>
	</section>
	
	<script>
		function searchId() {
			var name = document.getElementById("name").value;
			var email = document.getElementById("email").value;
			//if(name == "" && email == "") {
			//alert("모든 정보를 입력해주세요.");
			//	return;
			//}
			if(name == "") {
			alert("이름을 입력해주세요.");
				return false;
			}
			if(email == "") {
				alert("이메일을 입력해주세요.");
					return false;
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
		
		function searchPw() {
			var id2 = document.getElementById("id2").value;
			var email2 = document.getElementById("email2").value;
			//if(id2 == "" && email2 =="") {
			//	alert("모든 정보를 입력 해주세요.");
			//	return;
			// }
			
			if(id2 == "" ) {
				alert("아이디를 입력 해주세요.");
				return false;
			}
			
			if(email2 == "" ) {
				alert("이메일을 입력 해주세요.");
				return false;
			}
			var url = "<%=request.getContextPath()%>/SearchPw"; //서블릿요청url
			var title = "searchPw"; // 윈도우 창이름
			var status = "left=500px, top=100px, width=800px, height=500px, menubar-no, status=no, scrollbar=yes";
			var popup = window.open("" ,title, status); // 빈 창 오픈
			
			searchFrm2.id2.value = id2;
			searchFrm2.email2.value = email2;
			searchFrm2.target = title; // popup창과 form 태그를 연결
			//action, method 설정 후 form태그 submit
			searchFrm2.action = url;
			searchFrm2.method = "post";
			searchFrm2.submit();
			
		}
		

		//비회원 메뉴 클릭 이동 방지
		$(".menu").click(function(e){
			e.preventDefault();
			alert("로그인 후 이용해주세요.");
		});
	</script>
	
	<footer>	
	<div id = "d1">
		<a href = "<%=request.getContextPath() %>/login"><b>로그인 화면으로 돌아가기</b></a>
	</div>	
	</footer>
</body>
</html>