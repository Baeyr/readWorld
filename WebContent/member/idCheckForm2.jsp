<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style = "text-align : center">
	<h3>아이디 중복확인</h3>
	<form method = "post" action = "idCheckPro.jsp" onsubmit = "return blankCheck(this)">
	아이디 : <input type = "text" name= "id" maxlength="20" autofocus>
	<input type="submit" value = "중복확인">
	</form>
	</div>
	
	<script>
		function blacnkCheck(f) {
			var id = f.id.value;
			id = id.trim();
			if(id.length<4) {
				alert("아이디는 4자 이상 입력해주세요.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>