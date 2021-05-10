<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../Header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title> 
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style>
		#wrap{
			width: 80%;

            margin-left: 10%;
            margin-top: 30px;

			text-align:center;
			
    		border: 2px solid #1C4025;
			border-radius: 20px;			
		}

        #wrap h1,h2{
            color: #1C4025;
        }

        #formMain table{
            margin: 0 auto;
        }

        #inputV .labels{
            padding: 8px;
            text-align: right;
        }

        #inputV input,select{
            border: 1px solid #1C4025;
        }
        
		#log{
			text-align : center;
		}
		
		label {
			display: inline-block;
	 		width: 145px;
			height: 30px; 
		}
        
        .btn{
            border: 2px solid #1C4025;
            border-radius: 20px;
            color : #F2EAC2;
            background-color: #1C4025;
        }

        .btn:hover{
            background-color: #F2EAC2;
            color : #1C4025;
            font-weight: bold;
            cursor: pointer;
        }

        .fiB{
            font-size: 20px;
        }
	</style>
	<script language = "javascript">
		
		function checkAll() {
			if(!checkId(form.id.value)){
				return false;
			}		
			if(!checkPassword(form.id.value, form.password1.value, form.password2.value)) {
				return false;
			}
			if(!checkEmail(form.email.value)) {
				return false;
			}
			if(!checkName(form.name.value)) {
				return false;
			}
			if(!checkPhone(form.phone.value)) {
				return false;
			}
			if(form.idDuplication.value == "idCheck"){
				alert("아이디 중복체크를 해주세요");
				return false;	
			}	
			if(!checkCehck()){
				return false;
			}
			return true;
		}
	
		//공백확인 함수
		function checkExistData(value, dataName) {
			if(value == "") {
				alert(dataName + " 입력해주세요!");
				return false;
			}
			return true;
		}
		
		function checkId(id) {
			// id가 입력되었는지 확인
			if(!checkExistData(id,"아이디를"))
				return false;
			
			var idRegExp = /^[a-zA-z0-9]{4,20}$/; //아이디 유효성 검사
			if(!idRegExp.test(id)) {
				alert("아이디는 영문 대소문자와 숫자 4~20자리로 입력해야합니다!");
				form.id.value = "";
				form.id.focus();
				return false;
			}
			return true; // 확인이 완료되었을때
		}	
		
		function checkPassword(id, password1 , password2) {
			// 비밀번호가 입력되었는지 확인
			if(!checkExistData(password1, "비밀번호를"))
				return false;
			//비밀번호 확인이 입력되었는지 확인
			if(!checkExistData(password2, "비밀번호 확인을"))
				return false;
			
			var password1RegExp = /^[a-zA-z0-9!@#$%^&*()]{4,20}$/; // 비밀번호 유효성 검사
			if(!password1RegExp.test(password1)) {
				alert("비밀번호는 영문 대소문자와 숫자와 특수문자로 4~20자리로 입력해야합니다!");
				form.password1.value = "";
				form.password1.focus();
				return false;
			}
			// 비밀번호와 비밀번호 확인이 맞지 않다면
			if(password1 != password2){
				alert("두 비밀번호가 맞지 않습니다!");
				form.password1.value = "";
				form.password2.value = "";
				form.password2.focus();
				return false;
			}
			
			// 아이디와 비밀번호가 같을때
			if(id == password1) {
				alert("아이디와 비밀번호는 같을 수 없습니다!");
				form.password1.value = "";
				form.password2.value = "";
				form.password2.focus();
				return false;
			}
			return true; // 확인이 완료되었을 때
		}
		
		function checkEmail(email) {
			// email이 입력되었는지 확인
			if(!checkExistData(email, "이메일을")) 
				return false;
			
			var emailRegExp = /^[a-zA-z0-9_.]{1,30}$/;
			if(!emailRegExp.test(email)) {
				alert("이메일 형식이 올바르지 않습니다!");
				form.email.value = "";
				form.email.focus();
				return false;
		}
			return true; // 확인이 완료되엇을 때 
		}
		
		function checkName(name){
			if(!checkExistData(name, "이름을"))
				return false;
			
			var nameRegExp = /^[가-힣]{2,4}$/;
			if(!nameRegExp.test(name)) {
				alert("이름이 올바르지 않습니다.");
				return false;
			}
			return true;
		}	
		
		function checkPhone(phone) {
			if(!checkExistData(phone,"전화번호를"))
			 	return false;
			
			var phoneRegExp = /^[0-9]{8,8}$/;
			if(!phoneRegExp.test(phone)) {
				alert("전화번호가 올바르지 않습니다.");
				return false;
			}
			return true;
		}
		
		function checkCehck(){
			var num = $('input:checkbox[name=chkBox]:checked').length;
			if(num==0){
				alert("선호장르를 한 개 이상 선택해주세요.")
				return false;
			}
			
			return true;
		}
		// id 중복체크
		function idCheck() {
			//새창만들기
			window.open("<%=request.getContextPath()%>/member/idCheckForm.jsp" , "idwin" , "width=500 , height=350 , left=800, top=100");
		}
		//비회원 메뉴 클릭 이동 방지
		$(".menu").click(function(e){
			e.preventDefault();
			alert("로그인 후 이용해주세요.");
		});
		
	</script>
	
</head>
<body>
	<!--<jsp:include page="../Header.jsp"/>-->
	<!--  왼쪽,오른쪽,바깥여백을 auto로 주면 중앙정렬된다. -->
	<div id = "wrap">
        <br>
        <h1>회원가입</h1>
        <br>
        <form name = "form" action="<%=request.getContextPath()%>/memberjoin" method = "post" onsubmit="return checkAll()">
            <div id = "formMain">
                <table id="inputV">
                    <tr>
                        <td class="labels">아이디</td>
                        <td>
                            <input type = "text" name = "id" minlength = "4" maxlength = "20">
                            <input class="btn" type = "button" value = "중복확인" onclick = "idCheck()">			
                        </td>
                    </tr>
                
                    <tr>
                        <td class="labels">비밀번호</td>
                        <td>
                            <input type = "password" name = "password1" minlength = "4" maxlength = "20" placeholder = "영문자+숫자+특수문자조합">
                            
                        </td>
                    </tr>
                
                    <tr>
                        <td class="labels">비밀번호 확인</td>
                        <td>
                            <input type = "password" name = "password2" minlength = "4" maxlength = "20" placehplder = "다시 한번 입력해주세요">
                        </td>
                    </tr>
                
                    <tr>
                        <td class="labels">이메일</td>
                        <td>
                            <input type = "text" name = "email" maxlength = "30"> @
                            <select name="email2">
                                <option>naver.com</option>
                                <option>daum.net</option>
                                <option>gmail.com</option>
                                <option>nate.com</option>
                            </select>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="labels">이름</td>
                        <td>
                            <input type = "text" name = "name" minlength = "2" maxlength = "4">
                        </td>
                    </tr>
                
                    <tr>
                        <td class="labels">전화번호</td>
                        <td>
                            <select name="phone2">
                                <option>010</option>
                                <option>011</option>
                                <option>012</option>
                            </select>
                            <input type = "text" name = "phone" size = "12" maxlength = "8" placeholder = "-빼고 적어주세요">
                        </td>				
                    </tr>
                
                    <!--  <tr>
                        <td>성별</td>
                        <td>
                        <input type="radio" name = "gender" value = "남" checked>남
                        <input type="radio" name = "gender" value = "여">여
                        </td>
                    </tr> -->
                    
                    </table>
                    
                    <br><br>

                    <h2>선호장르(선호하시는 책 장르를 선택해주세요.)</h2>
                    <table id="checkV"><br>
                        <tr>	
                            <td>
                                <label><input type = "checkbox" name = "chkBox" value="유아">유아</label>
                                <label><input type = "checkbox" name = "chkBox" value="어린이">어린이</label>
                                <label><input type = "checkbox" name = "chkBox" value="청소년">청소년</label>
                                <label><input type = "checkbox" name = "chkBox" value="부모">좋은부모</label>
                                <label><input type = "checkbox" name = "chkBox" value="시">소설/시/희곡</label>
                                <br>
                                <label><input type = "checkbox" name = "chkBox" value="소설">장르소설</label>
                                <label><input type = "checkbox" name = "chkBox" value="에세이">에세이</label>
                                <label><input type = "checkbox" name = "chkBox" value="고전">고전</label>
                                <label><input type = "checkbox" name = "chkBox" value="만화">만화</label>
                            </td>
                        </tr>
                        <tr>	
                            <td>
                                <label><input type = "checkbox" name = "chkBox" value="초등">초등학교참고서</label>
                                <label><input type = "checkbox" name = "chkBox" value="중학교">중학교참고서</label>
                                <label><input type = "checkbox" name = "chkBox" value="고등">고등학교참고서</label>
                                <label><input type = "checkbox" name = "chkBox" value="대학">대학교재</label>
                                <label><input type = "checkbox" name = "chkBox" value="자격증">수험서/자격증</label>
                                <br>
                                <label><input type = "checkbox" name = "chkBox" value="경제">경제경영</label>
                                <label><input type = "checkbox" name = "chkBox" value="자기계발">자기계발</label>
                                <label><input type = "checkbox" name = "chkBox" value="외국어">외국어</label>
                                <label><input type = "checkbox" name = "chkBox" value="컴퓨터">컴퓨터/모바일</label>
                            </td>
                        </tr>
                        <tr>	
                            <td>
                                <label><input type = "checkbox" name = "chkBox" value="인문학">인문학</label>
                                <label><input type = "checkbox" name = "chkBox" value="사회과학">사회과학</label>
                                <label><input type = "checkbox" name = "chkBox" value="역사">역사</label>
                                <label><input type = "checkbox" name = "chkBox" value="과학">과학</label>
                                <label><input type = "checkbox" name = "chkBox" value="예술">예술/대중문화</label>
                                <br>
                                <label><input type = "checkbox" name = "chkBox" value="종교">종교/역학</label>
                                <label><input type = "checkbox" name = "chkBox" value="가정">가정/요리/뷰티</label>
                                <label><input type = "checkbox" name = "chkBox" value="건강">건강/취미/레저</label>
                                <label><input type = "checkbox" name = "chkBox" value="여행">여행</label>
                                <label><input type = "checkbox" name = "chkBox" value="개인">개인</label>
                                </td>
                        </tr>
                            </td>
                        </tr>
                </table>
            </div>
            <br>

            <div id = "log">
                <input type = "hidden" name = "idDuplication" value="idCheck">
                <input type = "submit" value = "회원 가입" class="btn fiB"> 
                &nbsp;&nbsp;&nbsp;
                <input type = "reset" value = "다시 입력" class="btn fiB">
            </div>
        </form>
	</div>
</body>
</html>