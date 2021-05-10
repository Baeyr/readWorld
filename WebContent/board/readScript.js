var cnt=0;
var idx1="";
var idx2="";

$(".mod").click(function(){
    location.href="#";
})

$(".del").click(function(){
    location.href="#";
})

$('.srBtn').click(function(){
		cnt++;
		if(cnt%2==1){
		var parent=$(this).parent().parent().parent().parent();
		var parent1=$(this).parent().parent().parent().parent().attr('class');
		idx1=String(parent1).charAt(parent1.length-2);
		if(idx1=="-"){
			idx2=String(parent1).charAt(parent1.length-1);
		} else {
			idx2=String(parent1).slice(-2);
		}
		$(parent).after('<table class="table4"> <tr class="row3"> <td class="re"><i id="icon" class="fas fa-arrow-right fa-3x"></i></td>'+
		'<td class="text">'+
		'<input type="hidden" class="rootNo" value="'+idx2+'">'+
		'<textarea class="putReCmt" name="cmtcontent" id="reCmt">'+
		'</textarea></td> <td class="textBtn"> <button type="submit" class="bBtn" id="putReCmt">입력하기</button> </td> </tr></table>');
		}
		else if(cnt%2==0){
			$(".table4").remove();
		}
	})
	
$('.smBtn').click(function(){
		cnt++;
		if(cnt%2==1){
		var num=123;
		var parent=$(this).parent().parent().parent().parent();
		var parent1=$(this).parent().parent().parent().parent().attr('class');
		var parent2=String(parent.children().children().children().eq(2).children().text());
		var cmtNo=String($(this).prev().val());
		idx1=String(parent1).charAt(parent1.length-2);
		if(idx1=="-"){
			idx2=String(parent1).charAt(parent1.length-1);
		} else {
			idx2=String(parent1).slice(-2);
		}
		$(parent).after('<table class="table6"> <tr class="row3"> <td class="re"><i id="icon" class="fas fa-arrow-right fa-3x"></i></td>'+
		'<td class="text">'+
		'<input type="hidden" class="rootNo" value="'+idx2+'">'+
		'<textarea class="putReCmt" name="cmtcontent" id="reCmt1">'+parent2+
		'</textarea></td> <td class="textBtn">'+
		'<input type="hidden" class="cmtNo" value="'+cmtNo+'">'+
		'<button type="submit" class="bBtn" id="modReCmt">입력하기</button> </td> </tr></table>');
		}
		else if(cnt%2==0){
			$(".table6").remove();
		}
	})
	
$(document).on("click","#putReCmt",function(){
	var content=$('#reCmt').val();
	var id=$('#loginId').val();
	var bno=$('.lineNo1').text();
	var title=$('.lineNo2').text();
	var rootNo=$('.rootNo').val();
	$.ajax({
		url:'commentWrite',
		type:'POST',
		data:{
			content:content,
			id:id,
			cmtrootno:rootNo, 
			boardno:bno,
			cmtstep:2,
			cmtlevel:0
		},
		success: function(msg){
			alert("성공");
			window.location.href.reload();
		},
		error:function(request, error){
			alert("code"+request.status+"\n"+"message:"+request.responseText+"\n"+"error"+error);
		}
	});
})
	
$(document).on("click","#putCmt",function(){
	var content=$('.putCmt').val();
	var id=$('#loginId').val();
	var bno=$('.lineNo1').text();
	var title=$('.lineNo2').text();
	$.ajax({
		url:'commentWrite',
		type:'POST',
		data:{
			content:content,
			id:id,
			cmtrootno:0,
			boardno:bno,
			cmtstep:1,
			cmtlevel:0
		},
		success: function(msg){
			alert("성공");
			window.location.href.reload();
		},
		error:function(request, error){
			alert("code"+request.status+"\n"+"message:"+request.responseText+"\n"+"error"+error);
		}
	});
})
	
$(document).on("click","#modReCmt",function(){
	var content=$('#reCmt1').val();
	var cmtno=String($(this).prev().val());
	$.ajax({
		url:'commentMod',
		type:'POST',
		data:{
			content:content,
			commentno:cmtno
		},
		success: function(msg){
			alert("성공");
			window.location.href.reload();
		},
		error:function(request, error){
			alert("code"+request.status+"\n"+"message:"+request.responseText+"\n"+"error"+error);
		}
	});
})

$(".reDel").click(function(){
	
})

