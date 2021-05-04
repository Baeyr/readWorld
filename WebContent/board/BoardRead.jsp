<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../Header.jsp"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>읽편한세상</title>
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
            <table class="thead">
                <tr>
                    <td class="lineNo1">${readboard.boardno }</td>
                    <td class="lineNo2">${readboard.boardtitle }</td>
                    <td class="lineNo3">${readboard.id }</td>
                    <td class="lineNo4">${readboard.boarddate }</td>
                    <td class="lineNo5">${readboard.boardcount }</td>
                </tr>
            </table>
            
            <form id="frm">
            
            <input type="hidden" name="boardno" value="${readboard.boardno }">
            
                <div>
                    <table class="table1">
                        <tr>
                            <td class="post">
                                <p class="p">${readboard.boardcontent }</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="liking">
                                <span><i class="far fa-heart fa-3x" id="likeIcon"></i></span>
                                <input type="button" id="like">
                                <span><br><br>좋아요</span><!--값 연결-->
                            </td>
                        </tr>
                        <tr>
                            <td class="btns">
                            		<!-- 게시글 작성자 id와 로그인한 id가 일치할 때만 수정, 삭제 버튼 보이도록 해야 됨 -->
	                                <button type="button" class="bBtn mod" id="modB">수정하기</button>
	                                <button type="button" class="bBtn del" id="delB">삭제하기</button>
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
                <div>
                    <table class="table3">
                        <tr class="row3">
                            <td class="row1"><i class="far fa-times-circle"></i>id</td><!--값 연결-->
                            <td class="row2" rowspan="2">
                                <p class="reCmt">내용내용</p><!--값 연결-->
                            </td>
                        </tr>
                        <tr class="re">
                            <td><button type="button" class="sBtn reDel">답글</button><button type="button"
                                    class="sBtn reMod">수정</button>
                            </td>
                        </tr>
                    </table>
                    <hr>
                </div>
                <div>
                    <table class="table5" id="newReCmt"><!--생성하는 걸로 script 이동-->
                        <tr class="row3">
                            <td class="re" rowspan="2"><i id="icon" class="fas fa-arrow-right fa-3x"></i></td>
                            <td class="row1"><i class="far fa-times-circle"></i>id</td><!--값 연결-->
                            <td class="row2" rowspan="2">
                                <p class="reCmt">내용내용</p><!--값 연결-->
                            </td>
                        </tr>
                        <tr class="re">
                            <td><button type="button" class="sBtn reDel">답글</button>
                                <button type="button" class="sBtn reMod">수정</button>
                            </td>
                        </tr>
                    </table>
                    <hr>
                </div>
                <div class="tfooter">
                    <table class="table4">
                        <tr class="row3">
                            <td class="re"><i id="icon" class="fas fa-arrow-right fa-3x"></i></td>
                            <td class="text">
                                <textarea class="inputReCmt"></textarea>
                            </td>
                            <td class="textBtn">
                                <button type="submit" class=bBtn>입력하기</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
    <script src="readScript.js"></script>	
    
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
    	form.action = "boardModify.do";
    	form.method = "post";
    	form.submit();
    	
    }
    
    </script>
    
</body>
</html>