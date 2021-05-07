
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>읽기편한 세상</title>
	<link href="mainCss.css?aa1232" rel="stylesheet" type = "text/css">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div id="main_content">
        <section>
            <article id="main_article1">
                <div id="bestseller">
                    <table>
                        <caption><h2>맞춤 추천</h2></caption>
                        <c:forEach items="${userBook}" var="item">
                        	<tr class="imgTr">
                        		<td class="img"><a href="#"><img src="${item.cover}"></a></td>
                        	</tr>
                        	<tr class="titleTr">
                        		<td class="title"><a href="#">${item.title}</a></td>
                        	</tr class="authorTr">
                        	<tr>
                        		<td class="author"><a href="#">${item.author}</a></td>
                        	</tr>
                        </c:forEach>
                    </table>
                </div>
    
                <div id="newBook">
                    <table>
                        <caption><h2>추천 신간</h2></caption>
                        <c:forEach items="${newBook}" var="item">
                        	<tr >
                        		<td class="img"><a href="#"><img src="${item.cover}"></a></td>
                        	</tr>
                        	<tr>
                        		<td class="title"><a href="#">${item.title}</a></td>
                        	</tr>
                        	<tr>
                        		<td class="author"><a href="#">${item.author}</a></td>
                        	</tr>
                        </c:forEach>
                    </table>
                </div>
    
                <div id="rank">
                    <table>
                        <caption><h2>최고의 별점</h2></caption>
                        <c:forEach items="${starBook}" var="item">
                        	<tr>
                        		<td class="img"><a href="#"><img src="${item.cover}"></a></td>
                        	</tr>
                        	<tr>
                        		<td class="title"><a href="#">${item.title}</a></td>
                        	</tr>
                        	<tr>
                        		<td class="author"><a href="#">${item.author}</a></td>
                        	</tr>
                        </c:forEach>
                    </table>
                </div>
            </article>

            <article id="main_article2">
                <div class="listB">
                    <h2>오늘의 Pick</h2>
                    <ul class="List">
                      	<c:forEach items="${randomBook}" var="item">
	                      	<ul>
		                       	<li>
		                       		<a href="#"><img src="${item.cover}"></a>
		                       	</li>
		                       	<li class="title">
		                       		<a href="#">${item.title}</a>
		                       	</li>
		                       	<li class="author">
		                       		<a href="#">${item.author}</a>
		                       	</li>
	                       	 </ul>
                     	</c:forEach>
                    </ul>
                </div>
                
                <hr>

                <div class="listB">
                    <h2>자기계발의 시작</h2>
                    <ul class="List">
                        <c:forEach items="${developBook}" var="item">
	                      	<ul>
		                       	<li>
		                       		<a href="#"><img src="${item.cover}"></a>
		                       	</li>
		                       	<li class="title">
		                       		<a href="#">${item.title}</a>
		                       	</li>
		                       	<li class="author">
		                       		<a href="#">${item.author}</a>
		                       	</li>
	                       	 </ul>
                     	</c:forEach>
                    </ul>
                </div>

				<hr>

                <div class="listB">
                    <h2>즐거운 만화와 함께</h2>
                    <ul class="List">
                        	<c:forEach items="${comicBook}" var="item">
	                      	<ul>
		                       	<li>
		                       		<a href="#"><img src="${item.cover}"></a>
		                       	</li>
		                       	<li class="title">
		                       		<a href="#">${item.title}</a>
		                       	</li>
		                       	<li class="author">
		                       		<a href="#">${item.author}</a>
		                       	</li>
	                       	 </ul>
                     	</c:forEach>
                    </ul>
                </div>
	
				<hr>
	
                <div class="listB">
                    <h2>오늘은 외국어 공부를 해보자</h2>
                    <ul class="List">
                        	<c:forEach items="${languageBook}" var="item">
	                      	<ul>
		                       	<li>
		                       		<a href="#"><img src="${item.cover}"></a>
		                       	</li>
		                       	<li class="title">
		                       		<a href="#">${item.title}</a>
		                       	</li>
		                       	<li class="author">
		                       		<a href="#">${item.author}</a>
		                       	</li>
	                       	 </ul>
                     	</c:forEach>
                    </ul>
                </div>
            </article>
        </section>
    </div>
</body>
</html>