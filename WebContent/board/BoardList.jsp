<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<link href="<%=request.getContextPath()%>/board/BoardListStyle.css?333" rel="stylesheet" type = "text/css">
</head>
<body>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="../Header.jsp"></jsp:include>
	<div id="main_content">
        <section>
            <article id="main_article1" class="header">
                <hr>
                <div>
                    <span class="s">게시판</span><a href="<%=request.getContextPath()%>/WriteGo"><span class="s s1">글쓰기</span></a>
                </div>
                <hr>
            </article>

            <article id="main_article2" class="header">
                <table  border="1" class="board">
                    <tr>
                        <td class="tableTop">NO</td>
                        <td class="tableTop">제목</td>
                        <td class="tableTop">아이디</td>
                        <td class="tableTop">날짜</td>
                        <td class="tableTop">추천수</td>
                    </tr>
                    
			<!-- 작성한 게시글이 없는 경우 -->
			<c:if test="${empty boardList}">  
				<tr>
					<td colspan="5"> 게시물이 없습니다. </td>
				</tr>
			</c:if>
			
			<!-- 상위 고정 게시글 -->
			<c:forEach var="i" items="${d1.rows}" varStatus="s">
                    <tr class="fixed">
                        <td class="tableCon no">${i['boardno']}</td>
                        <td class="tableCon title"><a href="<%=request.getContextPath() %>/boardRead.do?boardno=${i['boardno']}">${i['boardtitle']}</a></td>
                        <td class="tableCon author">${i['id']}</td>
                        <td class="tableCon date"><fmt:formatDate value="${i['boarddate']}" pattern="yyyy-MM-dd"/></td>
                        <td class="tableCon rank">${i['boardcount']}</td>
                    </tr>
			</c:forEach>
			<!-- 작성한 게시물이 있는 경우 -->
			<c:if test="${not empty boardList}">
				
				<!-- 게시글 출력 -->
				<c:forEach items="${boardList }" var="bl">
                    <tr>
                        <td class="tableCon no">${bl.boardno}</a></td>
                        <td class="tableCon title"><a href="<%=request.getContextPath() %>/boardRead.do?boardno=${bl.boardno}">${bl.boardtitle}</td>
                        <td class="tableCon author">${bl.id}</td>
                        <td class="tableCon date">${bl.boarddate}</td>
                        <td class="tableCon rank">${bl.boardcount}</td>
                    </tr>
				
				
				</c:forEach>
			</c:if>
                </table>
			
			<!-- 게시판 페이징 -->
			
                <div id="page">
                    <ul id="moveP">
					<c:if test="${startPage!=1}">
		            	<li><a href="<%=request.getContextPath() %>/BoardList.do?PageNumber=${startPage-1}&search=${search}">◀</a></li>
					</c:if>
					<c:forEach begin="${startPage}" end="${endPage}" var="page" step="1">
                        <li><a href="<%=request.getContextPath() %>/BoardList.do?PageNumber=${page}&search=${search}">${page}</a></li>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
                        <li><a href="<%=request.getContextPath() %>/BoardList.do?PageNumber=${endPage+1}&search=${search}">▶</a></li>
					</c:if>
                    </ul>
                </div>
			
			
            </article>
        </section>
    </div>
    
  
	
	
    
    
</body>
</html>