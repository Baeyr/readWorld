<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>읽편한 세상</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/book/SearchCss.css">
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include>
<div class="mainbody">
	<section class="maincontent">
		<article class="booksearch">
			<hr>
			<span id="searchTitle" style="color:#730202; margin:0px 11%; font-size: 25px; font-weight: bold; ">${result}</span>
			<hr>
			<h2 class="title">도서</h2>
			<ul class="booklist">
				<c:forEach items="${searchBook }" var="item">
				<li>
					<a href="#">
						<img src="${item.cover }" alt="이미지좀 보여줘" class="bookcover">
					</a>
						<p class="bookTitle">${item.title}</p>
						<p class="bookData"><span>${item.author }</span><em> | </em><span>${item.publisher}</span></p>
				</li>
				</c:forEach>
			</ul>
		</article>
		<article class="boardsearch">
			<hr>
			<h2 class="title">게시판 <a href="<%=request.getContextPath()%>/BoardList.do?search=${search}" class="moreBtn">더보기</a></h2>
			<div class="viewBoard">
			<c:forEach items="${searchboard}" var="item" varStatus="status">
				<c:set var="item1" value="${boardcnt[status.index]}" />
			<table>
				<tr>
					<td rowspan="2" class="boardTitle">${item.boardtitle }</td>
					<td>&nbsp;</td>
					<td><p class="boardUser"><span>${item.boarddate }</span><span> | </span><span>${item.id }</span></p></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" class="boardData">${item1.boardcontent }</td>
				</tr>
			</table>
				</c:forEach>
			</div>
		</article>
	</section>
</div>
	<script>
			
	</script>
</body>
</html>