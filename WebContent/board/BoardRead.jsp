<%@page import="java.io.Console"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Board.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../Header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<s:setDataSource url="jdbc:oracle:thin:@112.221.156.36:1521:xe"
	driver="oracle.jdbc.driver.OracleDriver" user="ReadWorld"
	password="1234" var="dt" scope="page" />
<s:query
	sql="select * from cmt where boardno = ${readboard.boardno} order by cmtrootno, cmtstep, cmtlevel"
	var="d1" dataSource="${dt}" />
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>읽편한세상</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/reset.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/board/readStyle.css?123">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css?123" />
</head>
<body>
<% 
		Board board = (Board)request.getAttribute("readboard");
%>
<div class="header">
        <hr>
        <div>
            <span class="s">게시판</span><span class="s1">글읽기</span>
        </div>
        <hr>
    </div>
    <div class="body">
        <div class="line">
 	   		 <div>
            <table class="thead">
                <tr>
                    <td class="lineNo1">${readboard.boardno}</td>
                    <td class="lineNo2">${readboard.boardtitle}</td>
                    <td class="lineNo3">${readboard.id}</td>
                    <td class="lineNo4">${readboard.boarddate}</td>
                    <td class="lineNo5">${readboard.boardplay}</td>
                </tr>
            </table>
             </div>
	 	    <form id="frm">
                <div>
                    <table class="table1">
                        <tr>
                            <td class="post">
                				<input type="hidden" name="boardno" value="${readboard.boardno}"/>
								<input type="hidden" name="boardId" id="loginId" value="<%= (String)request.getSession().getAttribute("logId")%>"/>            
				    	        <input type="hidden" name="boardWriter" value="${readboard.id}"/> 
                                <p class="p"><%=board.getBoardcontent()%></p>
                            </td>
                        </tr>
                        <tr>
                            <td class="liking">
                                <span><i class="far fa-heart fa-3x" id="likeIcon"></i></span>
                              <input type="button" id="like"><br><br>
                                <span class="like_count"></span><!--값 연결-->
                            </td>
                        </tr>
                        <tr>
                            <td class="Bbtns btns1">
							<!-- 게시글 작성자 id와 로그인한 id가 일치할 때만 수정, 삭제 버튼 보이도록 해야 됨 -->
							<c:if test="${nowId eq readboard.id}">
								<button type="button" class="bBtn mod" id="modB">수정하기</button>
	                         	<button type="button" class="bBtn del" id="delB">삭제하기</button>
								<script>
									var delB = document.getElementById("delB");
								    var modB = document.getElementById("modB");
								    var form = document.getElementById("frm"); 
								    
								    delB.onclick = function(){
								    	form.action	= "boardDelete.do";
								    	form.method="post";
								    	form.submit();
								    }
								    
								    modB.onclick = function(){
								    	form.action = "BoardModify.do";
								    	form.method = "post";
								    	form.submit();
								    	
								    }
								</script>
							</c:if>
	                         
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="line1">
                    <table class="table2">
                        <tr>
                            <td colspan="2">
                                <p class="p">댓글</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="text">
                                <textarea class="putCmt"></textarea>
                            </td>
                            <td class="textBtn">
                                <button type="submit" class="bBtn" id="putCmt">입력하기</button>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tbody">
                    <c:forEach var="i" items="${d1.rows}" varStatus="s">
						<c:if test="${i['cmtstep']==1}">
							<table class="table3-${i['cmtrootno']}">
							<tr class="row3">
								<td class="row1" colspan="2"><i class="far fa-times-circle"></i>${i['id']}</td>
								<td></td>
								<td class="row2" rowspan="2" colspan="2">
									<p class="reCmt">${i['cmtcontent']}</p>
								</td>
							</tr>
							<tr class="re">
									<td><button type="button" class="srBtn">답글</button></td>
									<c:if test="${nowId eq i['id']}">
									<td class="btns">
									<input type="hidden" class="cmtNo" value="${i['commentno']}">
									<button type="button" class="smBtn">수정</button>
									<button type="button" class="sdBtn">삭제</button></td>
									</c:if>
							</tr>
						</table>
							<hr>
						</c:if>
						<c:if test="${i['cmtstep']>1}">
							<table class="table5-${i['cmtrootno']}">
							<tr class="row3">
								<td class="re" rowspan="2"><i id="icon"
								class="fas fa-arrow-right fa-3x"></i></td>
								<td class="row1"><i class="far fa-times-circle"></i>${i['id']}</td>
								<td class="row2" rowspan="2">
									<p class="reCmt">${i['cmtcontent']}</p>
								</td>
							</tr>
							<tr class="re">
							<c:if test="${nowId eq i['id']}">
								<td class="btns"><input type="hidden" class="cmtNo" value="${i['commentno']}">
								<button type="button" class="smBtn">수정</button>
								<button type="button" class="sdBtn">삭제</button></td>
							</c:if>
							</tr>
						</table>
								<hr>
						</c:if>
					</c:forEach>
                </div>
            </form>
        </div>
    </div>
    
    <script src="<%=request.getContextPath()%>/board/readScript.js"></script>	
<script>
	
    
    
    $(function(){
    	
    	$("#like").click(function(){

    	
    	// 좋아요 버튼 클릭 시 추천 추가 및 제거
    	
    		$.ajax({
    			url:"boardLike.do", // 주소 확인
    			type:"POST",
    			data:{
    				boardno : $('input[name=boardno]').val(),
    				boardId : $('input[name=boardWriter]').val()
    			},
    			datatype:"json",
    			success: function(result){
    				if(result == 3){
    					alert("본인이 작성한 글에는 추천할 수 없습니다.");
    				}else{
	    				likeCount();
    				}
    			},
    			error:function(e){
    				alert("에러");
    			}
    		})
    	});
    	
    	// 게시글 추천수
    	function likeCount(){
    		$.ajax({
    			url:"likeCount.do",	// 주소 확인 
        		type:"POST",
        		data:{
        			boardno : $('input[name=boardno]').val()
        		},
        		datatype:"json",
        		success: function(count) {
        			$(".like_count").text(count);
        		},error: function (e) {
                    alert("에러" + e);
                }
    		})
    		
    	};
    
    	likeCount(); // 처음 시작했을 때 실행되도록 해당 함수 호출
    });
    
    </script>
</body>
</html>