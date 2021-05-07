<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
</head>
<body>
	<div style = "text-align : center">
	<h3>아이디 중복확인</h3>
	<form method = "post" action = "idCheckPro.jsp" onsubmit = "return blankCheck(this)">
	아이디 : <input type = "text" name= "id" minlength = "4" maxlength="20" autofocus>
	<input type="submit" value = "중복확인">
	
	</form>
	</div>

	<script>
		function blankCheck(f) {
			var id = f.id.value;
			id = id.trim();
			if(!(id >='0' && id <='9') && !(id >='a' && id <='z') && !(id >= 'A' && id <='Z')) {
				alert("아이디는 영문 대소문자와 숫자만 입력가능합니다");
				
				return false;
			}
		}
	</script>
</body>
</html>