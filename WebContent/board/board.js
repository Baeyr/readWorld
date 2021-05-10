function boardLike(){
	$.ajax({
		url:"boardread.do",
		type:"POST",
		cache:false,
		dataType:"json",
		data: $('#like_form').serialize(),	// id명 수정 필요. // 아이디가 like_form인 곳의 모든 정보를 가져와 파라미터 전송 형태(표준 쿼리형태)로 만들어줌
		success:
			function(data){
				alert("''좋아요'가 반영되었습니다!");
				$("#like_form").html(data.like);	// id값이 like_result인 html을 찾아서 data.like 값으로 바꿔줌
			},
		error:
			function(request, status, error){
				alert("ajax 실패")
			}
			
	});
}