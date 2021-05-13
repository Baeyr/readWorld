<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link href="MypageStyle.css?ver=39" rel="stylesheet" type = "text/css">

</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
	<br><br><br>

	<div id="main_content">
        <section>
            <article id="main_article1" class="header">
                <hr>
                <div>
                    <span class="s">게인페이지</span></a>
                    <span class="s s1" >
                        <a href = "./RTermination">대여권 해지</a>&nbsp;&nbsp;
                        <a href = "./DeletePage">회원탈퇴</a>
                    </span>
                </div>
                <hr>
                <div class="msBuy">
                    <h3>회원권 구매내역</h3>
                    <!-- 회원권을 구매한적이 없을경우 -->
                    <c:if test="${empty purchases}">
                    	<h2>구매한 회원권이 없습니다.</h2>
                    </c:if>
                    
                    <!-- 회원권을 구매한적이 있을경우 -->
                    <c:if test="${not empty purchases}">
                    	<c:forEach items="${purchases}" var="pli">
                    		<ul class="msList">
                    			<c:choose>
                    				<c:when test="${pli.membershipno == 1}">
		                        		<li><img src="/SEMI/image/1week.png"></li>
		                        	</c:when>
		                        	<c:when test="${pli.membershipno == 2}">
		                        		<li><img src="/SEMI/image/1month.png"></li>
		                        	</c:when>
		                        	<c:when test="${pli.membershipno == 3}">
		                        		<li><img src="/SEMI/image/3month.png"></li>
		                        	</c:when>
		                        	<c:when test="${pli.membershipno == 4}">
		                        		<li><img src="/SEMI/image/6month.png"></li>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<li><img src="/SEMI/image/12month.png"></li>
		                        	</c:otherwise>
                    			</c:choose>
		                        <li>${pli.membershipname} 회원권</li>
		                        <li>${pli.beforedate}</li>
		                        <li>~</li>
		                        <li>${pli.afterdate}</li>
		                    </ul>
                    	</c:forEach>
                    </c:if>
                </div>

                <hr class="line">

                <div class="bookBuy">
                    <h3>대여한 책</h3>
                    <c:if test="${empty rentalBook}">
                    	<h2>대여한 책이 없습니다.</h2>
                    </c:if>
                    <c:if test="${not empty rentalBook}">
                    	<ul class="msList">
	                        <li class="cover"><a href="#"><img src="${item.cover}"></a></li>
	                        <li class="title"><a href="#">${item.title}</a></li>
	                        <li class="author"><a href="#">${item.author}</a></li>
	                    </ul>
                    </c:if>
                </div>

                <hr class="line">

                <div class="myBoard">
                    <h3>내가 쓴 글</h3>
                    <table class="board" border="1">
	                    <tr>
	                        <td class="tableTop">NO</td>
	                        <td class="tableTop">제목</td>
	                        <td class="tableTop">아이디</td>
	                        <td class="tableTop">날짜</td>
	                        <td class="tableTop">추천수</td>
	                    </tr>
	               	<c:if test="${empty myBoard}">  
						<tr>
							<td colspan="5"> <h2>게시물이 없습니다.</h2> </td>
						</tr>
					</c:if>
					<c:if test="${not empty myBoard}">
						<c:forEach items="${myBoard}" var="bl">
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
	                <div id="page">
	                    <ul id="moveP">
						<c:if test="${startPage!=1}">
			            	<li><a href="<%=request.getContextPath() %>/MyPage?PageNumber=${startPage-1}">◀</a></li>
						</c:if>
						<c:forEach begin="${startPage}" end="${endPage}" var="page" step="1">
	                        <li><a href="<%=request.getContextPath() %>/MyPage?PageNumber=${page}">${page}</a></li>
						</c:forEach>
						<c:if test="${endPage < pageCnt }">
	                        <li><a href="<%=request.getContextPath() %>/MyPage?PageNumber=${endPage+1}">▶</a></li>
						</c:if>
	                    </ul>
                	</div>
                </div>

                <hr class="line">

                <div class="myBoard">
                    <h3>1대1 문의글</h3>
                    <table border="1">
                        <tr>
                            <td class="btitle" rowspan="2"><h3>글제목ss</h3></td>
                            <td class="Bdate bb">2020-01-05</td>
                            <td class="BID bb">아이디</td>
                        </tr>
                        <tr>
                            <td colspan="2" class="Bcont bb">글내용숄라로쇼ㅕㄹ라랄라라라라라~~~~</td>
                        </tr>
                    </table>
                </div>
            </article>
        </section>
    </div>
</body>
</html>