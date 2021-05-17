<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link href="MypageStyle.css?444" rel="stylesheet" type = "text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
	<br><br><br>
	<script>
		$(function(){
			$("#dddd").click(function(){
				var ww = ${user.membership}; 
				if(ww>0){
					location.href = "./RTermination"; 
				}else{
					alert("구매하신 회원권이 없어서 해지가 불가능합니다.");
					location.href = "./MyPage";
				}
				console.log(ww);
			});
		});
	</script>
	<div id="main_content">
        <section>
            <article id="main_article1" class="header">
                <hr>
                <div>
                    <span class="s">
                    	개인페이지 
                    	<c:if test="${msckeck ne 0}">
                    		: 회원권 적용중
                    	</c:if>
                    </span>
                    
                    <c:if test="${id ne 'admin'}">
                    	<span class="s s1" >
                        <a href = "#" id="dddd">회원권 해지</a>&nbsp;&nbsp;
                        <a href = "./DeletePage">회원탈퇴</a>
                    </span>
                    </c:if>
                </div>
                
                <hr>
                <c:if test="${id ne 'admin'}">
                <div class="msBuy">
                    <h3>회원권 구매내역</h3>
                    <!-- 회원권을 구매한적이 없을경우 -->
                    <c:if test="${empty purchases}">
                    	<h2>구매한 회원권이 없습니다.</h2>
                    </c:if>
                    
                    <!-- 회원권을 구매한적이 있을경우 -->
                    <c:if test="${not empty purchases}">
                    	<c:forEach items="${purchases}" var="pli" varStatus="status">
                    		<ul class="msList">
		                        <c:if test="${delM[status.index] eq 1}">
		                        	<li class="delM">중도해지</li>
			                     </c:if>
			                     <c:if test="${delM[status.index] eq 2}">
			                     	<li class="delM">&nbsp;</li>
			                     </c:if>
                    			<c:choose>
                    				<c:when test="${pli.membershipno == 1}">
		                        		<li><img src="<%=request.getContextPath()%>/image/1week.png"></li>
		                        	</c:when>
		                        	<c:when test="${pli.membershipno == 2}">
		                        		<li><img src="<%=request.getContextPath()%>/image/1month.png"></li>
		                        	</c:when>
		                        	<c:when test="${pli.membershipno == 3}">
		                        		<li><img src="<%=request.getContextPath()%>/image/3month.png"></li>
		                        	</c:when>
		                        	<c:when test="${pli.membershipno == 4}">
		                        		<li><img src="<%=request.getContextPath()%>/image/6month.png"></li>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<li><img src="<%=request.getContextPath()%>/image/12month.png"></li>
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
				</c:if>
				<c:if test="${id ne 'admin'}">
                <hr class="line">

                <div class="bookBuy">
                    <h3>대여한 책</h3>
                    <c:if test="${empty rentalBook}">
                    	<h2>대여한 책이 없습니다.</h2>
                    </c:if>
                    <c:if test="${not empty rentalBook}">
                    	<c:forEach items="${rentalBook}" var="item">
                    		<ul class="msList">
	                        	<li class="cover"><a href="<%=request.getContextPath()%>/bookDetail.do?isbn=${item.isbn}"><img src="${item.cover}"></a></li>
	                        	<li class="title"><a href="<%=request.getContextPath()%>/bookDetail.do?isbn=${item.isbn}">${item.title}</a></li>
	                        	<li class="author"><a href="<%=request.getContextPath()%>/bookDetail.do?isbn=${item.isbn}">${item.author}</a></li>
	                    	</ul>
                    	</c:forEach>
	                    <div id="page">
		                    <ul id="moveP">
							<c:if test="${startPage4!=1}">
				            	<li><a href="<%=request.getContextPath() %>/MyPage?PageNumber4=${startPage4-1}">◀</a></li>
							</c:if>
							<c:forEach begin="${startPage4}" end="${endPage4}" var="page" step="1">
		                        <li><a href="<%=request.getContextPath() %>/MyPage?PageNumber4=${page}">${page}</a></li>
							</c:forEach>
							<c:if test="${endPage4 < pageCnt4 }">
		                        <li><a href="<%=request.getContextPath() %>/MyPage?PageNumber4=${endPage4+1}">▶</a></li>
							</c:if>
		                    </ul>
                		</div>
                    </c:if>
                </div>

                <hr class="line">
				</c:if>
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
                
				<c:if test="${id ne 'admin'}">
                <hr class="line">
                <div class="myQna">
                    <h3>1대1 문의글</h3>
                    <a id="qnaW" href="<%=request.getContextPath()%>/QnaWrite?qna=0"><h3>문의하기</h3></a>
                    <table id="qnaT" border="1">
                    	<c:if test = "${empty myQna}">
                    		<tr>
                    			<td><h3>작성한 문의글이 없습니다.</h3></td>
                    		</tr>
                    	</c:if>
                    	<c:if test = "${not empty myQna}">
                    		<c:forEach items="${myQna}" var="bl">
	                    		<tr class="accordian">
	                            <td class="tableCon no">${bl.qnano}</td>
	                            <c:if test="${bl.id eq 'admin'}">
	                            	<td class="tableCon title">-▶ 답변 : ${bl.qnatitle}</td>
	                            </c:if>
	                            <c:if test ="${bl.id ne 'admin'}">
	                            	<td class="tableCon title">${bl.qnatitle}</td>
	                            </c:if>
	                            <td class="tableCon author" style="text-align: center;">${bl.id}</td>
	                            <td class="tableCon date" style="text-align: center;">${bl.qnadate}</td>
		                        </tr>
		                        <tr class="pannel">
		                        	<td colspan="4">
		                        		<p>&nbsp; ${bl.qnacontent}</p>
		                        	</td>
		                        </tr>
	                        </c:forEach>
                    	</c:if>
                    </table>
                    <div id="page">
	                    <ul id="moveP">
						<c:if test="${startPage2!=1}">
			            	<li><a href="<%=request.getContextPath() %>/MyPage?PageNumber2=${startPage2-1}">◀</a></li>
						</c:if>
						<c:forEach begin="${startPage2}" end="${endPage2}" var="page" step="1">
	                        <li><a href="<%=request.getContextPath() %>/MyPage?PageNumber2=${page}">${page}</a></li>
						</c:forEach>
						<c:if test="${endPage2 < pageCnt2 }">
	                        <li><a href="<%=request.getContextPath() %>/MyPage?PageNumber2=${endPage2+1}">▶</a></li>
						</c:if>
	                    </ul>
                	</div>
                </div>
                </c:if>
                
                <c:if test="${id eq 'admin'}">
                <hr class="line">
                <div class="QnaAll">
                    <h3>1대1 문의글</h3>
                    <table border="1">
                    	<c:if test = "${empty allQna}">
                    		<tr>
                    			<td><h3>작성한 문의글이 없습니다.</h3></td>
                    		</tr>
                    	</c:if>
                        <c:if test = "${not empty allQna}">
                    		<c:forEach items="${allQna}" var="bl">
	                    		<tr class="accordian">
	                            <td class="tableCon no">${bl.qnano}</td>
	                            <c:if test="${bl.id eq 'admin'}">
	                            	<td class="tableCon title">-▶ 답변 : ${bl.qnatitle}</td>
	                            </c:if>
	                            <c:if test ="${bl.id ne 'admin'}">
	                            	<td class="tableCon title">${bl.qnatitle}</td>
	                            </c:if>
	                            <td class="tableCon author" style="text-align: center;">${bl.id}</td>
	                            <td class="tableCon date" style="text-align: center;">${bl.qnadate}</td>
		                        </tr>
		                        <tr class="pannel">
		                        	<td colspan="4">
		                        		<p>&nbsp; ${bl.qnacontent}</p>
		                        		<c:if test ="${bl.id ne 'admin'}">
			                        		<a href="<%=request.getContextPath()%>/QnaWrite?qna=0&ref=${bl.qnano}" id="qnaW"><p>&nbsp;답변하기&nbsp;</p></a>	                        		
		                        		</c:if>
		                        	</td>
		                        </tr>
	                        </c:forEach>
                    	</c:if>
                    </table>
                    <div id="page">
	                    <ul id="moveP">
						<c:if test="${startPage3!=1}">
			            	<li><a href="<%=request.getContextPath() %>/MyPage?PageNumber3=${startPage3-1}">◀</a></li>
						</c:if>
						<c:forEach begin="${startPage3}" end="${endPage3}" var="page" step="1">
	                        <li><a href="<%=request.getContextPath() %>/MyPage?PageNumber3=${page}">${page}</a></li>
						</c:forEach>
						<c:if test="${endPage3 < pageCnt3 }">
	                        <li><a href="<%=request.getContextPath() %>/MyPage?PageNumber3=${endPage3+1}">▶</a></li>
						</c:if>
	                    </ul>
                	</div>
                </div>
                </c:if>
            </article>
        </section>
    </div>
</body>

<script>
	$(function(){
	    $('.accordian').click(function(e){
	        e.preventDefault();
	        $(this).next().slideToggle();
	    })
	});
</script>
</html>