/**
 * 
 */

$(document).ready(function(){
	var ptIdx = $('#pIdx').text();
	$.ajax({
		url:getContextPath()+"/plan/planParticipantGet?ptIdx="+ptIdx,
			method:"POST",
			dataType:"text",
			success:function(data){
				if(data=="exist"){
					$('#subPPButton').show();
					$('#addPPButton').hide();
				}else{
					$('#addPPButton').show();
					$('#subPPButton').hide();
				}
			}
	})
})
