<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title> 
	<style>
		#wrap{
			width : 530px;
			margin-left : auto;
			margin-right : auto;			
		}
	
		#log{
			text-align : center;
		}
		
	</style>
	
	<script language = "javascript">
		function checkAll() {
			if(!checkId(form.id.value)){
				return false;
			}
			if(!checkPassword(form.id.value, form.password1.value, form.password2.value)) {
				return false;
			}
			if(!checkEmail(form.email.value)) {
				return false;
			}
			if(!checkName(form.name.value)) {
				return false;
			}
			if(!checkPhone(form.phone.value)) {
				return false;
			}
			
			return true;
		}
	
		//공백확인 함수
		function checkExistData(value, dataName) {
			if(value == "") {
				alert(dataName + " 입력해주세요!");
				return false;
			}
			return true;
		}
		
		function checkId(id) {
			// id가 입력되었는지 확인
			if(!checkExistData(id,"아이디를"))
				return false;
			
			var idRegExp = /^[a-zA-z0-9]{4,20}$/; //아이디 유효성 검사
			if(!idRegExp.test(id)) {
				alert("아이디는 영문 대소문자와 숫자 4~20자리로 입력해야합니다!");
				form.id.value = "";
				form.id.focus();
				return false;
			}
			return true; // 확인이 완료되었을때
		}
		
		function checkPassword(id, password1 , password2) {
			// 비밀번호가 입력되었는지 확인
			if(!checkExistData(password1, "비밀번호를"))
				return false;
			//비밀번호 확인이 입력되었는지 확인
			if(!checkExistData(password2, "비밀번호 확인을"))
				return false;
			
			var password1RegExp = /^[a-zA-z0-9!@#$%^&*()]{4,20}$/; // 비밀번호 유효성 검사
			if(!password1RegExp.test(password1)) {
				alert("비밀번호는 영문 대소문자와 숫자와 특수문자로 4~20자리로 입력해야합니다!");
				form.password1.value = "";
				form.password1.focus();
				return false;
			}
			// 비밀번호와 비밀번호 확인이 맞지 않다면
			if(password1 != password2){
				alert("두 비밀번호가 맞지 않습니다!");
				form.password1.value = "";
				form.password2.value = "";
				form.password2.focus();
				return false;
		}
			// 아이디와 비밀번호가 같을때
			if(id == password1) {
				alert("아이디와 비밀번호는 같을 수 없습니다!");
				form.password1.value = "";
				form.password2.value = "";
				form.password2.focus();
				return false;
			}
			return true; // 확인이 완료되었을 때
		}
		
		function checkEmail(email) {
			// email이 입력되었는지 확인
			if(!checkExistData(email, "이메일을")) 
				return false;
			
			var emailRegExp = /^[a-zA-z0-9]{1,30}$/;
			if(!emailRegExp.test(email)) {
				form.email.value = "";
				form.email.focus();
				return false;
		}	
			return true; // 확인이 완료되엇을 때 
		}
		
		function checkName(name){
			if(!checkExistData(name, "이름을"))
				return false;
			
			var nameRegExp = /^[가-힣]{2,4}$/;
			if(!nameRegExp.test(name)) {
				alert("이름이 올바르지 않습니다.");
				return false;
			}
			return true;	
		}
		
		function checkPhone(phone) {
			if(!checkExistData(phone,"전화번호를"))
			 	return false;
			
			var phoneRegExp = /^[0-9]{8,8}$/;
			if(!phoneRegExp.test(phone)) {
				alert("전화번호가 올바르지 않습니다.");
				return false;
			}
			return true;
		}
		
		//id 중복체크
		function idCheck() {
			// 팝업창 좌우 크기의 1/2만큼 보정값으로 빼주었음
			var popupX = (window.screen.width / 2) - (200 / 2);
			// 팝업창 상하 크기의 1/2만큼 보정값으로 빼주었음
			var popupY = (window.screen.height / 2) - (300 / 2);
			window.open("idCheckForm.jsp" , 'status = no, height = 300, width = 200 , left = ' + popupX + ', top = ' + popupY + ', screenX = ' + popupX + ', screenY = ' + popupY);			
		}
	</script>
	
</head>
<body>
	
	<!--  왼쪽,오른쪽,바깥여백을 auto로 주면 중앙정렬된다. -->
	<div id = "wrap">
	<br><br>
	<h1>회원가입</h1>
	<br><br>
	<form name ="form" method = "post" action="<%=request.getContextPath()%>/memberjoin" onsubmit="return checkAll()">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					<input type = "text" name = "id" minlength = "4" maxlength = "20">
					<input type = "button" value = "중복확인" onclick = "idCheck()">
				</td>
			</tr>
		
			<tr>
				<td>비밀번호</td>
				<td>
					<input type = "password" name = "password1" minlength = "4" maxlength = "20" placeholder = "영문자+숫자+특수문자조합">
					
				</td>
			</tr>
		
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input type = "password" name = "password2" minlength = "4" maxlength = "20">
				</td>
			</tr>
		
			<tr>
				<td>이메일</td>
				<td>
					<input type = "text" name = "email1" maxlength = "30">@
					<select name="email2">
						<option>naver.com</option>
						<option>daum.net</option>
						<option>gmail.com</option>
						<option>nate.com</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>이름</td>
				<td>
					<input type = "text" name = "name" minlength = "2" maxlength = "4">
				</td>
			</tr>
		
			<tr>
				<td>전화번호</td>
				<td>
					<select name="phone1">
						<option>010</option>
						<option>011</option>
						<option>012</option>
					</select>
					<input name="phone2" type = "text" size = "12" maxlength = "8" placeholder = "-빼고 적어주세요">
				</td>				
			</tr>
		
			<tr>
				<td>성별</td>
				<td>
				<input type="radio" name = "gender" value = "남">남
				<input type="radio" name = "gender" value = "여">여
				</td>
			</tr>
			<tr>
				<td>선호장르</td>
				<td>
					<input type = "text" size = "20" maxlength = "100" placeholder = "선호하는 책 장르 적어주세요">	
				</td>
			</tr>
		</table>
		<br>
		<div id = "log">
		<input type = "submit" value = "회원 가입"> 
		<input type = "reset" value = "다시 입력">
		</div>
	</form>
	</div>
</body>
</html>