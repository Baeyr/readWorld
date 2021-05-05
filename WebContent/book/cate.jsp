<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${pageContext.request.contextPath}/book/CateCss.css?ver=124" rel="stylesheet" type = "text/css">

	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.18.0/TweenMax.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script>
       $(function(){
            var nextB = $("#nextB");
            var preB = $("#preB");
            var list = $("#menu li");
            var offset = $("#cate").offset;

            //왼쪽 화살표로 이동
            preB.click(function(){
                $('#menu li:last').hide(function(){
                    $(this).prependTo($('#menu ul')).show('fast');
                });
            });

            //오른쪽 화살표로 이동
            nextB.click(function(){
                $('#menu li:first').hide(function(){
                    $(this).appendTo($('#menu ul')).show('fast');
                });
            });
            
            var cate = $('#menu li');
            
          	//카테고리 메뉴 클릭시
            cate.click(function(){
            	 var nowLi = $('.now');
            	 nowLi.removeClass('now');
            	 $(".List").empty();
                 if(!$(this).hasClass("now")){
                     $(this).addClass("now");
                 }
                
                 $.ajax({
                		url : "<%=request.getContextPath()%>/BookCateMove",
                		type: "post",
                		dataType : "json",
                		data : {"startNum":0, "cate":$(this).text()},
                		success:function(data){
        					var htmls = "";
        					if(data.length<=0){
        						var htmls = "<div>마지막입니다.</div>";
        					}else{
        						$.each(data,function(index,e){
            						htmls += "<ul>";
            						htmls += "<li>";
             						htmls += "<a>";
             						htmls += "<img src=\"" + e.cover + "\">";
             						htmls += "</a>";
            						htmls += "</li>";
          							htmls += "<li>";
           							htmls += "<a>";
             						htmls += e.title;
             						htmls += "</a>";
                					htmls += "</li>"
                					htmls += "<li>"
                						htmls += "<a>";
             						htmls += e.author;
             						htmls += "</a>";	
                        			htmls += "</li>"
            						htmls += "</ul>"
            					});
            					$(".List").append(htmls);
            					$(".List").append(htmls)
        					}
        				},
        				error : function(request,status,error){
        					alert("code : " + request.status + "\n message : " + request.responseText + "\n" + "error: " + error);
        				}
                	});
               
            });//카테고리 클릭시 

          	
           //더보기 버튼 클릭시
           $('#moreBtn').click(function(){
           	var startNum = $(".List ul").length; //현재 나타난 개수 알아내기
           	var cate = $(".now").text();
           
           	$.ajax({
           		url : "<%=request.getContextPath()%>/BookCateMore",
           		type: "post",
           		dataType : "json",
           		data : {"startNum":startNum, "cate":cate},
           		success:function(data){
   					var htmls = "";
   					if(data.length<=0){
   						var htmls = "<div>마지막입니다.</div>";
   					}else{
   						$.each(data,function(index,e){
       						htmls += "<ul>";
       						htmls += "<li>";
        						htmls += "<a>";
        						htmls += "<img src=\"" + e.cover + "\">";
        						htmls += "</a>";
       						htmls += "</li>";
     							htmls += "<li>";
      							htmls += "<a>";
        						htmls += e.title;
        						htmls += "</a>";
           					htmls += "</li>"
           					htmls += "<li>"
           						htmls += "<a>";
        						htmls += e.author;
        						htmls += "</a>";	
                   			htmls += "</li>"
       						htmls += "</ul>"
       					});
       					
       					$(".List").append(htmls);
       					
   					}
   				},
   				error : function(request,status,error){
   					alert("code : " + request.status + "\n message : " + request.responseText + "\n" + "error: " + error);
   				}
           	});
           });
       });
    </script>
</head>
<body>
	<jsp:include page="../Header.jsp"></jsp:include>
	<div id="main_content">
        <section>
            <article id="main_article1" class="header">
                <hr>
                <div id="cate">
                    <div id="preB" class="moveB">◀</div>
                    <div id="menu">
                        <ul>
                            <li class="now">전체</li>
                            <li>경제경영</li>
                            <li>자기계발</li>
                            <li>소설/시/희곡</li>
                            <li>라이트노벨</li>
                            <li>에세이</li>
                            <li>인문학</li>
                            <li>사회과학</li>
                            <li>역사</li>
                            <li>청소년</li>
                            <li>예술/대중문화</li>
                            <li>외국어</li>
                            <li>종교/역학</li>
                            <li>좋은부모</li>
                            <li>컴퓨터/모바일</li>
                            <li>만화</li>
                            <li>어린이</li>
                            <li>유아</li>
                            <li>여행</li>
                            <li>고전</li>
                            <li>건강/취미/레저</li>
                            <li>가정/요리/뷰티</li>
                            <li>과학</li>
                            <li>개인 출판물</li>
                        </ul>
                    </div>
                    <div id="nextB" class="moveB">▶</div>
                </div>
                <hr>
            </article>

            <article id="main_article2" class="bodys" >
            	<div id="page">
					<div class="listB">
                        <ul class="List">
	                      	<c:forEach items="${cateBook}" var="item">
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
                    	<div id="foot">
                    		<button id="moreBtn">더보기</button>
                    	</div>
                   </div>
            </article>
        </section>
    </div>
</body>
</html>