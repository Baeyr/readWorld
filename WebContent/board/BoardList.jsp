<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<s:setDataSource url="jdbc:oracle:thin:@112.221.156.36:1521:xe"
	driver="oracle.jdbc.driver.OracleDriver" user="ReadWorld"
	password="1234" var="dt" scope="page" />
<s:query
	sql="select * from (select rownum r, b.* from (select * from board where id='admin' order by boardno desc) b) b1 where b1.r<=2"
	var="d1" dataSource="${dt}" />
<s:query
	sql="select b2.* from (select rownum, b1.* from (select b.* from board b order by boardcount desc) b1) b2 where rownum=1"
	var="d2" dataSource="${dt}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
                <div class="boardD">
                <table class="board">
                    <tr class=topR>
                        <td class="tableTop no">NO
			<input type="hidden" id="currentP" value="<%=request.getAttribute("currentPage")%>"/>
                        </td>
                        <td class="tableTop">제목</td>
                        <td class="tableTop">아이디</td>
                        <td class="tableTop">날짜</td>
                        <td class="tableTop">추천수</td>
                    </tr>
                    
			
		<!-- 상위 고정 게시글 -->
			<c:if test="${not empty d1.rows}">
				<c:forEach var="i" items="${d1.rows}" varStatus="s">
                    <tr class="fixed1">
                        <td class="tableCon no">${i['boardno']}</td>
                        <td class="tableCon title"><a href="<%=request.getContextPath() %>/boardRead.do?boardno=${i['boardno']}">${i['boardtitle']}</a></td>
                        <td class="tableCon author">${i['id']}</td>
                        <td class="tableCon date"><fmt:formatDate value="${i['boarddate']}" pattern="yyyy-MM-dd"/></td>
	                        <td class="tableCon rank">${i['boardcount']}</td>
				    </tr>
							</c:forEach>
					<c:forEach var="j" items="${d2.rows}">
	                    <tr class="fixed2">
	                        <td class="tableCon no">${j['boardno']}</td>
	                        <td class="tableCon title"><a href="<%=request.getContextPath() %>/boardRead.do?boardno=${j['boardno']}">${j['boardtitle']}</a></td>
	                        <td class="tableCon author">${j['id']}</td>
	                        <td class="tableCon date"><fmt:formatDate value="${j['boarddate']}" pattern="yyyy-MM-dd"/></td>
	                        <td class="tableCon rank">${j['boardcount']}</td>
	                    </tr>
				</c:forEach>
			</c:if>
			
			
			<!-- 작성한 게시글이 없는 경우 -->
			<c:if test="${empty boardList || empty d1.rows}">  
				<tr>
					<td colspan="5"> 게시물이 없습니다. </td>
				</tr>
			</c:if>
			
			<!-- 작성한 게시물이 있는 경우 -->
			<c:if test="${not empty boardList}">
				<!-- 게시글 출력 -->
				<c:forEach items="${boardList }" var="bl">
                    <tr>
                    	<td class="tableCon no">${bl.boardno}</td>
                        <td class="tableCon title"><a href="<%=request.getContextPath() %>/boardRead.do?boardno=${bl.boardno}">${bl.boardtitle}</a></td>
                        <td class="tableCon author">${bl.id}</td>
                        <td class="tableCon date">${bl.boarddate}</td>
                        <td class="tableCon rank">${bl.boardcount}</td>
                    </tr>
				
				
				</c:forEach>
			</c:if>
                </table>
			</div>
			
			<!-- 게시판 페이징 -->
			
                <div id="page">
                    <ul id="moveP">
					<c:if test="${startPage!=1}">
		            	<li><a href="<%=request.getContextPath() %>/BoardList.do?PageNumber=${startPage-1}">◀</a></li>
					</c:if>
					<c:forEach begin="${startPage}" end="${endPage}" var="page" step="1">
                        <li><a href="<%=request.getContextPath() %>/BoardList.do?PageNumber=${page}">${page}</a></li>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
                        <li><a href="<%=request.getContextPath() %>/BoardList.do?PageNumber=${endPage+1}">▶</a></li>
					</c:if>
                    </ul>
                </div>
			
			
            </article>
        </section>
    </div>
    
<script>
$(function(){
    if($('#currentP').val()!=1){
   	 $('.fixed1').css('display','none');
    	$('.fixed2').css('display','none');
    }
 })
</script>
</body>
</html>