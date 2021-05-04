$(".mod").click(function(){
    location.href="#";
})
 
$(".del").click(function(){
    location.href="#";
})

$("#putCmt").click(function(){
    $("#frm").attr({action:"#",method:"post"}).submit();    
})

$('.sBtn').click(function(){
		var parent=$(this).parent().parent().parent().parent();
		console.log(parent);
		$(parent).after('<table class="table4"> <tr class="row3"> <td class="re"><i id="icon" class="fas fa-arrow-right fa-3x"></i></td> <td class="text"><textarea class="putReCmt"></textarea></td> <td class="textBtn"> <button type="submit" class="bBtn" id="putReCmt">입력하기</button> </td> </tr></table>');
	})

$(".reDel").click(function(){
	
})
 
