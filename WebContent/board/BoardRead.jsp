<%@page import="Board.vo.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../Header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<s:setDataSource url="jdbc:oracle:thin:@localhost:1521:xe"
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/reset.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/board/readStyle.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<title>읽편한세상</title>
</head>
<body>
<div class="header">
        <hr>
        <div>
            <span class="s">게시판</span><span class="s1">글읽기</span>
        </div>
        <hr>
    </div>
    <div class="body">
        <div class="line">
            <form id="frm">
            <div>
            <table class="thead">
                <tr>
                    <td class="lineNo1">${readboard.boardno}</td>
                    <td class="lineNo2">${readboard.boardtitle}</td>
                    <td class="lineNo3">${readboard.id}</td>
                    <td class="lineNo4">${readboard.boarddate}</td>
                    <td class="lineNo5">${readboard.boardcount}</td>
                </tr>
            </table>
            </div>
                <div>
                    <table class="table1">
                        <tr>
                            <td class="post">
                	<input type="hidden" name="boardno" value="${readboard.boardno}"/>
                                <p class="p">${readboard.boardcontent}</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="liking">
                                <span><i class="far fa-heart fa-3x" id="likeIcon"></i></span>
                                <input type="button" id="like"> <!--onclick 스크립트에-->
                                <span><br><br>좋아요</span><!--값 연결-->
                            </td>
                        </tr>
                        <tr>
                            <td class="btns">
                                <button type="button" class="bBtn mod">수정하기</button>
                                <button type="button" class="bBtn del">삭제하기</button>
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
								<td rowspan="2">
								</td>
								<td class="row1"><i class="far fa-times-circle"></i>${i['id']}</td>
								<td class="row2" rowspan="2">
									<p class="reCmt">${i['cmtcontent']}</p>
								</td>
							</tr>
							<tr class="re">
								<td><button type="button" class="srBtn reDel">답글</button>
									<button type="button" class="smBtn reMod">수정</button></td>
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
								<td><button type="button" class="smBtn reMod">수정</button></td>
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
</body>
</html>