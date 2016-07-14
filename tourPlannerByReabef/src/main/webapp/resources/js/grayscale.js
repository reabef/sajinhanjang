/*!
 * Start Bootstrap - Grayscale Bootstrap Theme (http://startbootstrap.com)
 * Code licensed under the Apache License v2.0.
 * For details, see http://www.apache.org/licenses/LICENSE-2.0.
 */

// jQuery to collapse the navbar on scroll
function collapseNavbar() {
    if ($(".navbar").offset().top > 50) {
        $(".navbar-fixed-top").addClass("top-nav-collapse");
    } else {
        $(".navbar-fixed-top").removeClass("top-nav-collapse");
    }
}

$(window).scroll(collapseNavbar);
$(document).ready(collapseNavbar);

// jQuery for page scrolling feature - requires jQuery Easing plugin
$(function() {
    $('a.page-scroll').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 1500, 'easeInOutExpo');
        event.preventDefault();
    });
});

// Closes the Responsive Menu on Menu Item Click
$('.navbar-collapse ul li a').click(function() {
  if ($(this).attr('class') != 'dropdown-toggle active' && $(this).attr('class') != 'dropdown-toggle') {
    $('.navbar-toggle:visible').click();
  }
});

// Google Maps Scripts
var map = null;
// When the window has finished loading create our google map below
google.maps.event.addDomListener(window, 'load', init);
google.maps.event.addDomListener(window, 'resize', function() {
    map.setCenter(new google.maps.LatLng(40.6700, -73.9400));
});

function init() {
    // Basic options for a simple Google Map
    // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
    var mapOptions = {
        // How zoomed in you want the map to start at (always required)
        zoom: 15,

        // The latitude and longitude to center the map (always required)
        center: new google.maps.LatLng(40.6700, -73.9400), // New York

        // Disables the default Google Maps UI components
        disableDefaultUI: true,
        scrollwheel: false,
        draggable: false,

        // How you would like to style the map. 
        // This is where you would paste any style found on Snazzy Maps.
        styles: [{
            "featureType": "water",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 17
            }]
        }, {
            "featureType": "landscape",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 20
            }]
        }, {
            "featureType": "road.highway",
            "elementType": "geometry.fill",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 17
            }]
        }, {
            "featureType": "road.highway",
            "elementType": "geometry.stroke",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 29
            }, {
                "weight": 0.2
            }]
        }, {
            "featureType": "road.arterial",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 18
            }]
        }, {
            "featureType": "road.local",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 16
            }]
        }, {
            "featureType": "poi",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 21
            }]
        }, {
            "elementType": "labels.text.stroke",
            "stylers": [{
                "visibility": "on"
            }, {
                "color": "#000000"
            }, {
                "lightness": 16
            }]
        }, {
            "elementType": "labels.text.fill",
            "stylers": [{
                "saturation": 36
            }, {
                "color": "#000000"
            }, {
                "lightness": 40
            }]
        }, {
            "elementType": "labels.icon",
            "stylers": [{
                "visibility": "off"
            }]
        }, {
            "featureType": "transit",
            "elementType": "geometry",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 19
            }]
        }, {
            "featureType": "administrative",
            "elementType": "geometry.fill",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 20
            }]
        }, {
            "featureType": "administrative",
            "elementType": "geometry.stroke",
            "stylers": [{
                "color": "#000000"
            }, {
                "lightness": 17
            }, {
                "weight": 1.2
            }]
        }]
    };

    // Get the HTML DOM element that will contain your map 
    // We are using a div with id="map" seen below in the <body>
    var mapElement = document.getElementById('map');

    // Create the Google Map using out element and options defined above
    map = new google.maps.Map(mapElement, mapOptions);

    // Custom Map Marker Icon - Customize the map-marker.png file to customize your icon
    /*var image = 'img/map-marker-black.png';*/
    var myLatLng = new google.maps.LatLng(37.457269, 126.726764);
    var beachMarker = new google.maps.Marker({
        position: myLatLng,
        map: map/*,
        icon: image*/
    });
    map.setCenter(new google.maps.LatLng(37.457269, 126.726764))
    google.maps.event.addDomListener(window,"resize",function()
    	{
    	map.setCenter(new google.maps.LatLng(37.457269, 126.726764))
    	}
    );
}

//비밀번호 체크
function validate() {
	var inputPwd = $('#inputPwd');
	var inputPwd2 = $('#inputPwd2');
	if (inputPwd.val() != inputPwd2.val()) {
		$('.pwdTitle').text('비밀번호를 확인해주세요')
		$('.pwdTitle').css('color', 'red');
		inputPwd2.val('')
		inputPwd.val('')
		inputPwd.focus()
		
		return false;
	} else{
		$('.pwdTitle').text('비밀번호 확인 완료')
		$('.pwdTitle').css('color', 'white');
		return true;
	}
}
function checkMid(){
	var regMid = /^[A-Za-z0-9+]*$/;
	var mid = $('#inputMid').val();
	if(mid.length === 0){
		$('#existId').text('아이디를 입력해주세요');
		$('#existId').css('color', 'red');
		$('#inputMid').focus();
	}else if(!regMid.test($('#inputMid').val())){
		$('#existId').text('아이디 형식은 영문자, 숫자만으로 이루어지며 4자 이상 12자 이하입니다.');
		$('#existId').css('color', 'red');
		$('#inputMid').focus();
	}else{
		$.ajax({
			url:"member/idcheck?mid="+mid,
			method:"POST",
			dataType:"text",
			success:function(data){
				if(data==="exist"){
					$('#existId').text('이미 존재하는 아이디입니다.');
					$('#existId').css('color', 'red');
					/*$('#existId').css('textShadow', 'black 0 0 0');*/
					$('#inputMid').focus();
				}else{
					$('#existId').text('사용할 수 있는 아이디입니다.')
					$('#existId').css('color', 'white');
					/*$('#existId').css('textShadow', '#dce5f5 0 1px 0');*/
				}
			}
		})
	}
}
function checkPwd(){
	if($('#inputPwd2').val().length > 0){
		$('#inputPwd').val('');
		$('#inputPwd2').val('');
		$('.pwdTitle').text('PASSWORD')
		$('#pwdTitle2').text('PASSWORD Confirm')
		$('#existId').css('color', 'white');
	}
}
function checkEmail(){
	if($('#inputEmail').val().length == 0){
		$('#emailTitle').text('이메일을 입력해주세요');
		$('#emailTitle').css('color', 'red');
		$('#inputEmail').focus();
	}else if(!regEmail.test($('#inputEmail').val())){
		$('#emailTitle').text('이메일형식이 알맞지 않습니다.');
		$('#emailTitle').css('color', 'red');
		$('#inputEmail').focus();
	}else{
		$('#emailTitle').text('이메일 확인 완료.');
		$('#emailTitle').css('color', 'white');
	}
}
function checkName(){
	var namePattern = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
	
	if($('#inputName').val() === 0){
		$('#nameTitle').text('이름을 확인해주세요')
		$('#nameTitle').css('color', 'red');
		$('#inputPwd').focus();
	}else if(!namePattern.test($('#inputName').val())){
		$('#nameTitle').text('이름형식이 알맞지 않습니다.')
		$('#nameTitle').css('color', 'red');
		$('#inputPwd').focus();
	}else{
		$('#nameTitle').text('이름 확인 완료')
		$('#nameTitle').css('color', 'white');
ㄴ	}
}
$(document).ready(function(){
	$('#inputMid').focusout(checkMid)
	
	$('#inputPwd2').focusout(validate)
	
	$('#inputPwd').focus(checkPwd)
	
	var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	$('#inputEmail').blur(checkEmail)
	$('#inputName').blur(checkName)
	$('#loginMid').blur(function(){
		$('#midTitle').text('ID');
		$('#midTitle').css('color', 'white');
	})
	$('#loginPwd').blur(function(){
		$('#pwdTitle').text('PASSWORD');
		$('#pwdTitle').css('color', 'white');
	})
})
$(document).click(function(){
	$('#messageDiv').fadeOut();
	$('#messageDivForJS').fadeOut();
})
$(document).scroll(function(){
	$('#messageDiv').fadeOut();
	$('#messageDivForJS').fadeOut();
})

function formCheck(){
	if($('#inputMid').val().length == 0){
		$('#existId').text('아이디를 입력해주세요');
		$('#existId').css('color', 'red');
		$('#inputMid').focus();
		return false;
	}else if($('#inputPwd2').val().length == 0){
		$('.pwdTitle').text('비밀번호를 입력해주세요')
		$('.pwdTitle').css('color', 'red');
		$('#inputPwd').focus();
		checkPwd();
		return false;
	}else if($('#inputName').val().length == 0){
		$('#nameTitle').text('이름을 확인해주세요')
		$('#nameTitle').css('color', 'red');
		$('#inputName').focus();
		return false;
	}else if($('#inputEmail').val().length == 0){
		$('#emailTitle').text('이메일을 입력해주세요');
		$('#emailTitle').css('color', 'red');
		$('#inputEmail').focus();
		return false;
	}else{
		return true;
	}
}
function loginCheck(){
	if($('#loginMid').val().length === 0){
		$('#midTitle').text('아이디를 입력해주세요');
		$('#midTitle').css('color', 'red');
		return false;
	}else if($('#loginPwd').val().length === 0){
		$('#pwdTitle').text('비밀번호를 입력해주세요');
		$('#pwdTitle').css('color', 'red');
		return false;
	}else{
		return true;
	}
}
function openJoin(){
	$('#joinSection').show();
}
function closeJoin(){
	$('#joinSection').fadeOut();
}
function openLogin(){
	$('#loginSection').show();
}
function closeLogin(){
	$('#loginSection').fadeOut();
}
$(document).ready(function () {
    $.datepicker.regional['ko'] = {
        closeText: '닫기',
        prevText: '이전달',
        nextText: '다음달',
        currentText: '오늘',
        monthNames: ['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUN)',
        '7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월',
        '7월','8월','9월','10월','11월','12월'],
        dayNames: ['일','월','화','수','목','금','토'],
        dayNamesShort: ['일','월','화','수','목','금','토'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
        weekHeader: 'Wk',
        dateFormat: 'yy-mm-dd',
        firstDay: 0,
        isRTL: false,
        showMonthAfterYear: true,
        yearSuffix: '',
        /*showOn: 'both',*/
        /*buttonText: "달력",*/
        changeMonth: true,
        changeYear: true,
        /*showButtonPanel: true,*/
        yearRange: 'c-99:c+99',
    };
    $.datepicker.setDefaults($.datepicker.regional['ko']);
 
    $('#sdate').datepicker();
    $('#sdate').datepicker("option", "maxDate", $("#edate").val());
    $('#sdate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#edate").datepicker( "option", "minDate", selectedDate );
    });
 
    $('#edate').datepicker();
    $('#edate').datepicker("option", "minDate", $("#sdate").val());
    $('#edate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#sdate").datepicker( "option", "maxDate", selectedDate );
    });
});
function planCheck(){
	var sdateArray = $('#sdate').val().split("-");
	var sdate = new Date(sdateArray[0], Number(sdateArray[1])-1, sdateArray[2]);
	var edateArray = $('#edate').val().split("-");
	var edate = new Date(edateArray[0], Number(edateArray[1])-1, edateArray[2]);
	var period = (edate.getTime() - sdate.getTime())/1000/60/60/24;
	if(!$('#pName').val().length>0){
		$('#pNameTitle').css('color','red')
		$('#pName').focus();
		return false;
	}else if(!$('#numPeople').val().length>0){
		$('#numPeopleTitle').focus();
		$('#numPeopleTitle').css('color','red')
		$('#numPeople').focus();
		return false;
	}else if(!$('#sdate').val().length>0){
		$('#sdate').focus();
		return false;
	}else if(!$('#edate').val().length>0){
		$('#edate').focus();
		return false;
	}else if(!confirm($('#sdate').val()+" ~ "+$('#edate').val()+" ("+period+"박"+(period+1)+"일)이 맞습니까?")){
		return false;
	}else{
		return true;
	}
}
var inputCnt = 0;
$(document).ready(function(){
	$('#pName').blur(function(){
		$('#pNameTitle').css('color','white');
	})
	$('#numPeople').blur(function(){
		$('#numPeopleTitle').css('color','white');
	})
	addInputTr();
	$('#addPlanDetail').click(addInputTr);
	
	$('.tableMiddle').hover(
		function(){
			/*$('#messageDivForJS').css('display', 'block');*/
			/*$('#messageDivForJS').fadeIn();
			$('#messageDivForJS').html('삭제하시려면 클릭해주세요');*/
		},
		function(){
			/*$('#messageDivForJS').css('display', 'none');*/
			$('#messageDivForJS').fadeOut();
			$('#messageDivForJS').html('');
		}
	);
	
	$('.tableMiddle #DBlist > tr').click(function(){
		//db 리스크 클릭시 수정 부분
		/*alert('')*/
		/*alert($('#DBlist > tr').index($(this)))
		alert($(this).index())*/
		var psIdx = $(this).children('td').html();
	})
	
	$('.removeSchedule').click(function(){
		var psIdx = $(this).parent().parent().children('td').html();
		var thisTr = $(this).parent().parent();
		if(confirm('해당 일정을 삭제하시겠습니까?')){
			$.ajax({
				url:getContextPath()+"/plan/remove?psIdx="+psIdx,
				method:"POST",
				dataType:"text",
				success:function(data){
					if(data=="success"){
						thisTr.remove();
						$('#messageDivForJS').fadeIn();
						$('#messageDivForJS').html('데이터 삭제 완료하였습니다.');
					}else{
						$('#messageDivForJS').fadeIn();
						$('#messageDivForJS').html('데이터 삭제 실패하였습니다.');
					}
				}
			})
		}else{
			
		}
	})
	$('.removePlanTitle').click(function(){
		alert('아직 구현 안됐어요...')
	})
})
function showKeyCode(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if( ( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
	{
		return;
	}
	else
	{
		return false;
	}
}
function addInputTr(){
	//날짜 연산용
	var sdateArray = $('#spanSdate').html().split("-");
	var sdate = new Date(sdateArray[0], Number(sdateArray[1])-1, sdateArray[2]);
	var edateArray = $('#spanEdate').html().split("-");
	var edate = new Date(edateArray[0], Number(edateArray[1])-1, edateArray[2]);
	var period = (edate.getTime() - sdate.getTime())/1000/60/60/24;
	var ptIdx = $('#pIdx').text();
	var selectBox = '<select class="form-control detailInput" name="planSchedules['+inputCnt+'].psDate">';
	for (var i = 1; i <= period+1; i++) {
		selectBox += '<option value="'+i+'">제'+i+'일차</option>';
	}
	selectBox += '</select>';
	$('.tableMiddle > #inputlist:last').append(	'<tr><td id="dateTd">'+selectBox+'</td>'+
											'<td id="placeTd"><input class="form-control detailInput" type="text" name="planSchedules['+inputCnt+'].psPlace" placeholder="장소 입력"></td>'+
									        '<td id="transTd"><input class="form-control detailInput" type="text" name="planSchedules['+inputCnt+'].psTrans" placeholder="교통편 입력"></td>'+
									        '<td id="timeTd"><input class="form-control detailInput" type="time" name="planSchedules['+inputCnt+'].pstime"></td>'+
									        '<td id="scheTd"><input class="form-control detailInput" type="text" name="planSchedules['+inputCnt+'].psSchedule" placeholder="일정 입력" style="width:100%;"></td>'+
									        '<td id="remarkTd"><input class="form-control detailInput" type="text" name="planSchedules['+inputCnt+'].psRemark" placeholder="비고 입력"></td>'+'<tr>'
									        +'<input type="hidden" name="planSchedules['+inputCnt+'].ptIdx" value="'+ptIdx+'">');
	inputCnt++;
	$('.detailInput').keyup(function(){
		$('#messageDivForJS').show();
		$('#messageDivForJS').html('현재 입력하는 내용 : <br>'+$(this).val());
	})
}
function regCheck(){
	if(!confirm('등록하시겠습니까?')){
		return false;
	}else{
		return true;
	}
	
}

function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}
