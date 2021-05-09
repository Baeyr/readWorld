<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>읽편한세상</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="/SEMI/board/BoardStyle.css">
<script type="text/javascript" src="/SEMI/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/SEMI/ckeditor/config.js"></script>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include>
	<div class="header">
	<hr>
    <div>
        <span class="s">게시판</span><span class="s s1">글쓰기</span>
    </div>
    <hr>
    </div>
    <div class="body">
    
    
    <!-- 게시글을 새로 쓰는 경우 -->
    <c:if test="${empty modvo.boardno }">
       <form action="<%=request.getContextPath()%>/boardWrite" method="post" enctype="multipart/form-data" class="form">
    	<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
	    <table class="board">
	        <tr>
	            <td><input type="text" name="BoardTitle" placeholder="제목"></td>
	        </tr>
	        <tr>
	            <td>
	            <textarea name="BoardContent" id="BoardContent" cols="71" rows="30" placeholder="내용을 입력하세요"></textarea>
	            <script type="text/javascript">
					 CKEDITOR.replace('BoardContent'
	                , {height: 500                                                  
	                 });
				</script>
	            </td>
	        </tr>
	        <tr>
	            <td><div class="filebox">
	            		<label for="file">이미지첨부</label>
	            		<input type="file" name="BoardFile" id="file">
	            		<input class="upload" value="파일선택" readonly>
	            	</div>
	            </td>
	        </tr>
	    </table>
    	<input type="submit" value="등록하기" class="btn1">
    </form>
    </c:if>
    
    <!-- 게시글을 수정하는 경우 -->
    <c:if test="${not empty modvo.boardno and modvo.boardno ne''}">
    
          <form action="<%=request.getContextPath()%>/boardWrite" method="post" enctype="multipart/form-data" class="form">
    	<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
	    <table class="board">
	        <tr>
	            <td><input type="text" name="BoardTitle" value="${modvo.boardtitle }"></td>
	        </tr>
	        <tr>
	            <td>
	            <textarea name="BoardContent" id="BoardContent" cols="71" rows="30" value="${modvo.boardcontent }"></textarea>
	            <script type="text/javascript">
					 CKEDITOR.replace('BoardContent'
	                , {height: 500                                                  
	                 });
				</script>
	            </td>
	        </tr>
	        <tr>
	            <td><div class="filebox">
	            		<label for="file">이미지첨부</label>
	            		<input type="file" name="BoardFile" id="file" value="${modvo.boardfile }">
	            		<input class="upload" value="파일선택" readonly>
	            	</div>
	            </td>
	        </tr>
	    </table>
    	<input type="submit" value="등록하기" class="btn1">
    </form>
    
    </c:if>
    
 
    </div>
    
    <script>
    	// 파일 선택 해오면 파일명 이름으로 바뀌는 이벤트
   		$("#file").on('change',function(){
    	  var fileName = $("#file").val();
    	  $(".upload").val(fileName);
    	});
    </script>
</body>
</html>