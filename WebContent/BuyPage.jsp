<%@page import="Member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Member vo = (Member)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>읽편한세상</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<style>
.topnav {
    	overflow: hidden;
    	background-color: #1C4025;
    	padding: 0px;
    	position: fixed;
    	top: 0;
    	left: 0px;
 		width: 100%;
 		height : 70px;
 		z-index: 1000;
    }
    
    .topnav a{
    	float: left;
    	display: block;
    	color: #F2EAC2;
    	text-align: center;
    	text-decoration: none;
    	padding: 10px 10px 25px 25px;
    }
    .topnav ul {
    	list-style-type: none;
    	
    }
    
    #profile {
    	float:right;
    	width: 50px;
    	padding-top: 2px;
    	padding-bottom: 10px;
    	margin-right: 8px;
    }
    
    #logB{
    	float:right;
    	margin-top: 15px;
    }
    
    .mainbody{
    	max-width: 700px;
    	margin: 0px auto;
    	padding: 0 15px;
    	height: 1000px;
    }
    section {
    	position: relative;
    	top:50px;
    }
    h2 {
	    margin: 0;
	    letter-spacing: -.4px;
	    box-sizing: border-box;
	    overflow: hidden;
	    padding: 0 45px;
	    font-size: 18px;
	    text-align: center;
	    text-overflow: ellipsis;
	    line-height: 50px;
    }
    .main{
		    font-size: 14px;
		    margin: 0;
		    padding: 0;
		    box-sizing: border-box;
		    position: relative;
		    left: 0;
		    right: 0;
		    top: 50px;
		    height: 50px;
		    border-bottom: 1px solid #eee;
    }
    .content {
    	position: relative;
    	top: 100px;
    }
    .content2 {
    	position: relative;
    	top: 120px;
    	
    }
    .content3 {
    	position: relative;
    	top: 140px;
    }
    .content4 {
    	position: relative;
    	top: 160px;
    }
    .buydata {
    	margin-bottom: 5px;
    	font-size: 14px;
    }
    .buydata2 {
    	margin-top: 0px;
    	font-size: 12px;
    	color: #999;
    }
    .data {
    		background-color: #f5f5f5;
    	    color: #333;
		    font-size: 14px;
		    border: 1px solid #ccc;
		    border-radius: 2px;
		    width: 95%;
		    padding: 11px 12px;
		    outline: none;
    }
    input[name='way']{
    	visibility: hidden;
    	margin: 0;
    	display: none;
    }
    input[name='way']:checked+label{
    	border: 2px solid #F2EAC2;
    	box-shadow: 0 8px 20px rgb(0 0 0 / 10%), 0 2px 8px rgb(0 0 0 / 10%);
    	color: #400101;
    }
    .payment {
    	padding: 16px;
   		border: 1px solid #ccc;
    	border-radius: 6px;
    	font-size: 14px;
    }
    .payment-option {
    	    line-height: 1.3;
		    font-size: 14px;
		    color: #242424;
		    margin: 0;
		    padding: 0;
		    box-sizing: border-box;
		    display: flex;
		    margin-top: 8px;
		    flex-wrap: wrap;
    }
    .option1{
    	    line-height: 1.3;
		    margin: 0;
		    padding: 0;
		    box-sizing: border-box;
		    text-align: center;
		    height: 60px;
		    min-width: calc(50% - 4px);
		    flex: 1;
		    margin-left: 0;
    }
    .option2{
    	    line-height: 1.3;
		    margin: 0;
		    padding: 0;
		    box-sizing: border-box;
		    text-align: center;
		    margin-left: 8px;
		    height: 60px;
		    min-width: calc(50% - 4px);
		    flex: 1;
    }
    .option3{
    	    line-height: 1.3;
		    margin: 0;
		    padding: 0;
		    box-sizing: border-box;
		    text-align: center;
		    height: 60px;
		    min-width: calc(50% - 4px);
		    flex: 1;
		    margin-left: 0;
		    margin-top: 8px;	
    }
    .paylabel{
    		cursor: pointer;
    	   	display:flex;
    	   	color: #999;
		    border: 1px solid #ccc;
		    border-radius: 12px;
		    position: relative;
		    align-items: center;
		    justify-content: center;
		    height: 100%;
    		background-repeat: no-repeat;
    		background-position: center 24px;
    		box-sizing: border-box;
    }
    .means {
    	margin-top: 30px;
    }
    .btnBuy{
    	position: relative;
    	top : 180px;
    	bottom: 0px;
    	width: 100%;
    	height: 56px;
    	line-height: 56px;
    	text-align: center;
    	border: 0;
    	background-color: #F2EAC2;
    	font-weight: bold;
    	color: #400101;
    	cursor: pointer;
    }
    .date {
    	font-size: 14px;
    }
    .buyTitle {
    	font-weight: bold;
    	color: #400101;
	}
    .pay {
    	float: right;
    }
    .won {
    	float: right;
    }
	    
    .contentpay {
    	margin-top: 0px;
    }
    .allpay {
    	border-top: 1px solid #ccc;
    	padding-top: 15px;
    	margin-bottom: 0px;
    }
    input[type='checkbox']{
    	 vertical-align: -2px;
    }
    input[type='checkbox']+label{
    	font-size: 14px;
    }
    .warning {
    	margin-top: 30px;
    	margin-bottom: 25px;
    }
    .warning li {
    	font-size: 12px;
    	color: #999;
    }
</style>
<script>
	$(function(){
		var all = document.getElementById("clauseAll");
		var checks = document.getElementsByName("clause");
		// 체크박스 전체 선택
		$("#clauseAll").click(function(){
			if(all.checked == true){
				for(var i =0; i<checks.length; i++){
					checks[i].checked = true;
				}
			}else{
				for(var i =0; i<checks.length; i++){
					checks[i].checked = false;
				}
			}
		});
		// 체크박스 개별 선택
		for(var i =0; i<checks.length; i++){
			checks[i].onclick = function(){
				var checks2 = document.getElementsByName("clause");
				var count = 0;
				for(var i =0; i<checks2.length; i++){
					if(checks2[i].checked){
						count++;
					}
				}
				if(count == checks2.length){
					all.checked = true;
				}else{
					all.checked = false;
				}
			}
		}
		
		// 정기 구독 기간 설정
		// 현재 시간
		var now = new Date();
		var date1 = moment(now).format('YYYY-MM-DD');
		
		// 회원권 클릭 시 회원권마다 정해진 기간 더해진 시간 
		var subscription = new Date(now.setDate(now.getDate()+${mbs.membershipdate}));
		var date2 = moment(subscription).format('YYYY-MM-DD');
		
		$("#date").html(date1+" ~ "+date2);
		
		// hidden에 담을 시간
		$("#beforedate").val(date1); // 회원권 구매 날짜
		$("#afterdate").val(date2); // 회원권 끝나는 날짜
		
		// 총 금액 계산
		var money = $("#amounts").html();
		var money1 = $("#discount").html();
		var allmoney = parseInt(money)+parseInt(money1);
		$("#allAmounts").html(allmoney);
		
		
		
		// 결제버튼 클릭시 체크박스 선택 x 경고창 출력
		$("#btn").click(function(){
			var frm = document.getElementById("frm1");
			var radios = document.getElementsByName("way");
			var chk = false;
			var chk1 = false;
			if(all.checked == true){
				chk = true;
			}else{
				chk = false;
			}
			for(var i =0; i<radios.length; i++){
				if(radios[i].checked == true){
					chk1 = true;
				} else {
					chk2 = false;
				}	
			}
			if(chk&&chk1){
				frm.action = "<%=request.getContextPath()%>/purchase";
				frm.method = "post";
				frm.submit();
			}else{
				alert('약관동의에 선택이 안되어있거나 결제수단이 선택되지않았습니다.');
			}
		});
		
	});
</script>
</head>
<body>
 	
	<div class="topnav">
    	<a class="menu" href="${pageContext.request.contextPath}/MyPage" id="profile">
	    	<img  src="<%=request.getContextPath()%>/image/profile.png">
    	</a>
  		<a class="menu" id="logB" name="logoutB" href="${pageContext.request.contextPath}/logout"> 로그아웃 </a>
    	<ul>
    		<li><a name="mainB" href="${pageContext.request.contextPath}/main">메인화면</a></li>
    		<li><a name="genreB" href="${pageContext.request.contextPath}/BookCate">장르별</a></li>
    		<li><a name="boardB" href="${pageContext.request.contextPath}/BoardList.do">게시판</a></li>
    		<li><a name="memberShipB" href="${pageContext.request.contextPath}/membership"> 회원권 구매 </a></li>
    	</ul>
    </div>
    <section>
	    <div class="main">
	    	<h2>결제정보 입력</h2>
	    </div>
    <div class="mainbody">
	    <div class="content">
	    	<p class="buyTitle">회원권 월 정기구독</p>
	    	<p class="date"><span>구독기간</span><span> | </span><span id="date"></span></p>
	    </div>
	    <div class="content2">
	    	<p class="buyTitle">결제 금액</p>
	    	<div class="payment">
	    		<p class="contentpay">상품금액<span class="won">원</span><span class="pay" id="amounts">${mbs.membershipprice }</span></p>
	    		<p>할인금액<span class="won">원</span><span class="pay" id="discount">0</span></p>
	    		<p class="allpay">총 결제금액<span class="won">원</span><span class="pay" id="allAmounts"></span></p>
	    	</div>
	    	<p class="buyTitle means">결제수단</p>
	    	<div class="payment-option">
	    		<div class="option1">
	    			<input type="radio" name="way" id="kakao">
	    			<label for="kakao" class="paylabel"><span>카카오페이</span></label>
	    		</div>
	    		<div class="option2">
	    			<input type="radio" name="way" id="phone">
	    			<label for="phone" class="paylabel"><span>휴대폰결제</span></label>
	    		</div>
	    		<div class="option3">
	    			<input type="radio" name="way" id="card">
	    			<label for="card" class="paylabel"><span>신용카드</span></label>
	    		</div>
	    	</div>
	    </div>
	    <div class="content3">
	    	<p class="buyTitle">결제정보 입력</p>
	    	<p class="buydata">휴대폰 번호<span>*</span></p>
	    	<input type="text" class="data" value="<%= vo.getPhone()%>">;
	    	<p class="buydata2"><span>등록된 휴대폰번호가 맞는지 확인해주세요. </span></p>
	    </div>
	    <div class="content4">
	    	<p><input type="checkbox" id="clauseAll" class="clause"><label for="clauseAll">전체 약관 동의하기</label></p>
	    	<hr>
	    	<p><input type="checkbox" id="clause1" class="clause" name="clause"><label for="clause1">[필수] 자동결제 상품임을 인지하였으며 취소/환불, 소득공제 정책 등 상품 구매 정책에 동의합니다.</label></p>
	    	<p><input type="checkbox" id="clause2" class="clause" name="clause"><label for="clause2">[필수] 개인정보 수집 및 이용에 동의합니다.</label></p>
	    	<ul class="warning">
	    		<li>구독결제는 구독기간 마지막 날 결제되며, 결제 후 구독기간은 자동 갱신됩니다.</li>
	    		<li>구독결제 갱신을 중단하고자 할 경우, 구독기간 종료 하루 전까지 구독을 해지하셔야 합니다.</li>
	    		<li>개인정보는 가입하신 동안에만 저장 되어 있으며 탈퇴 시 삭제 처리 됩니다.</li>
	    		<li>구독기간 내 하루라도 회원권을 사용하셨다면 환불처리가 되지 않습니다</li>
	    	</ul>
	    </div>
	    <form id="frm1">
		    <input type="hidden" name="id" value="<%= vo.getId()%>">
		    <input type="hidden" name="membershipno" value="${mbs.membershipno }">
		    <input type="hidden" name="beforedate" id="beforedate">
		    <input type="hidden" name="afterdate" id="afterdate">
		    <button type="button" class="btnBuy" id="btn">결제하기</button>
	    </form>
    </div>
    </section>
</body>
</html>