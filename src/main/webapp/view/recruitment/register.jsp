<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ page import="member.MemberDAO" %>
<link href="/css/member/signup.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
<script>
	function submitAgo() {
		var email = $('#email').val();
		var passWd = $('#passWd').val();
		var passWdChk = $('#passWdChk').val();
		var lastName = $('#lastName').val();
		var firstName = $('#firstName').val();
		var nickName = $('#nickName').val();
		var phoneNum = $('#phoneNum').val();
		var regexPhoneNum = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;

		var editErrorTitle = '';
		var editErrorMsg = '';
		// empty 는 비우고 해당 요소는 놔둠. remove는 해당 요소까지 제거.
		// $("#modal-title").empty();
		$("#modal-body").empty();
		if(email == ""){
			editErrorMsg = '<fmt:message key="SignUp.Modal.Content.Email"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").modal("show");
			return false;
		} else if(passWd == "") {
			editErrorMsg = '<fmt:message key="SignUp.Modal.Content.Password"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").modal("show");
			return false;
		} else if(lastName == "" || firstName == "") {
			editErrorMsg = '<fmt:message key="SignUp.Modal.Content.Name"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").modal("show");
			return false;
		} else if(nickName == "") {
			editErrorMsg = '<fmt:message key="SignUp.Modal.Content.NickName"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").modal("show");
			return false;
		} else if(phoneNum == "" || phoneNum == null) {
			editErrorMsg = '<fmt:message key="SignUp.Modal.Content.PhoneNum"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").modal("show");
			return false;
		} else if(!regexPhoneNum.test(phoneNum)) {
			editErrorMsg = '<fmt:message key="SignUp.Modal.Content.PhoneNumCheck"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").modal("show");
			return false;
		} else {
			
		}
	}
</script>
	<div class="body-main">
		<div class="signup-main">
			<div class="signup-title-box">
				<div class="signup-title">
					<fmt:message key="Recruitment" />
				</div>
			</div>
			
			<div class="signup-box">
				<form action="/RecruitmentServ" method="post" onsubmit="return submitAgo()">
					<div class="signup-data-box">
					
						<div class="signup-email-box">
							<input class="form-control" type="text" name="recruitment_title" id="title" placeholder='<fmt:message key="Recruitment.Title" />' />
						</div>
						<div class="signup-password-box">
							<textarea class="form-control" name="recruitment_contents" id="contents"><fmt:message key="Recruitment.Contents" /></textarea>
						</div>
						
						<div class="signup-name-box">
							<input class="form-control" type="text" name="recruitment_c_name" id="cname" placeholder='<fmt:message key="Recruitment.Cname" />' />
						</div>
						
						<div class="signup-phone-num-box">
							<input class="form-control" type="text" name="recruitment_hire_type" id="hiretype" placeholder='<fmt:message key="Recruitment.Hiretype" />' />
						</div>
						
						<div class="signup-nick-name-box">
							<input class="form-control" type="text" name="recruitment_work_history" id="workhistory" placeholder='<fmt:message key="Recruitment.Workhistory" />' />
						</div>
						<div class="signup-address-box">
							<div class="row">
								<div class="col-6">
									<input class="form-control" type="text" name="recruitment_region_name" id="regionname" placeholder='<fmt:message key="Recruitment.Regionname" />'>
								</div>
								<div class="col-6">
									<input class="form-control" type="text" name="recruitment_region_code" id="regioncode" placeholder='<fmt:message key="Recruitment.Regioncode" />'>
								</div>
							</div>
							<div class="row">
								<div class="col-6">
									<input type="text" name="recruitment_gu_name" id="guname"  placeholder='<fmt:message key="Recruitment.Guname" />'>
								</div>
								<div class="col-6">
									<input type="text" name="recruitment_gu_code" id="gucode" placeholder='<fmt:message key="Recruitment.Gucode" />'>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<input class="form-control" type="text"  name="recruitment_apply_startdate" id="apply_startdate" placeholder='<fmt:message key="Recruitment.Applystartdate" />'>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<input class="form-control" type="text" name="recruitment_apply_enddate" id="apply_enddate" placeholder='<fmt:message key="Recruitment.Applyenddate" />'>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<input class="form-control" type="text" name="recruitment_apply_method" id="apply_method" placeholder='<fmt:message key="Recruitment.Applymethod" />'>
								</div>
							</div>
						</div>
						
						<div class="signup-btn-box">
							<button type="submit" class="btn btn-light" id="submit" name="operator" value="submit" disabled ><fmt:message key="Register" /></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
  	<div class="modal fade" id="modal" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header" style="border-bottom:none !important">
	        <h5 class="modal-title" id="modal-title"><fmt:message key="SignUp"/></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="modal-body">
	      
	      </div>
	      <div class="modal-footer" style="border-top:none !important">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="SignUp.Modal.Close"/></button>
	      </div>
	    </div>
	  </div>
	</div>
	
<script>
	$(document).ready(function() {
		$("#email").change(function () {
			$("#submit").attr("disabled", true);
		});
	});
</script>
<script>
    function idDupChk(){
    	var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
        var id = $('#email').val(); //id값이 "id"인 입력란의 값을 저장
        var iddupchk = $('#iddupchk').val(); //id값이 "id"인 입력란의 값을 저장

		var editErrorMsg = '';
		$("#modal-body").empty();
        if(!emailRegex.test(id)){
        	// 이메일 형식이 맞지 않음.
			editErrorMsg = '<fmt:message key="SignUp.Modal.Content.EmailReg"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").modal("show");
        	// $('.modal-content').load("/modal/textModal?id="+id);
        	// $('.modal-content').modal();
        } else {
        	// 이메일 형식이 맞음.
            $.ajax({
                url:'/MemberSignupServ', 		//Controller에서 요청 받을 주소
                type:'post', 			//POST 방식으로 전달
                dataType: 'text',
                data:{id:id, operator:iddupchk},
                success:function(data){ //컨트롤러에서 넘어온 cnt값을 받는다 
                	// alert(data);
                	if(data == 1){
                		// 사용 할 수 없음
	                	editErrorMsg = '<fmt:message key="SignUp.Modal.Content.EmailCheck0"/>';
	        			$("#modal-body").prepend(editErrorMsg);
	        			$("#modal").modal("show");
                		$("#submit").attr("disabled", true);
                	} else {
                		// 사용 가능 함.
	                	editErrorMsg = '<fmt:message key="SignUp.Modal.Content.EmailCheck1"/>';
	        			$("#modal-body").prepend(editErrorMsg);
	        			$("#modal").modal("show");
                		$("#submit").attr("disabled", false);
                	}
                },
                error:function(){
                	editErrorMsg = '<fmt:message key="SignUp.Error"/>';
        			$("#modal-body").prepend(editErrorMsg);
        			$("#modal").modal("show");
                }
            });
        }

        };
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '.';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>