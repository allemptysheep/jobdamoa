<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@include file="/view/member/is_signIn.jsp"%>
<%@ page import="company.CompanyDAO" %>
<%@page import="company.CompanyDTO"%>
<link href="/css/member/memberedit.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
<% 
	String mEmail = (String)session.getAttribute("mEmail");
	CompanyDAO companyDAO = new CompanyDAO(application);
	CompanyDTO companyDTO = companyDAO.getCompanyDTO(mEmail);
%>
<script>
	function submitAgo() {
		var email = $('#email').val();
		var passWd = $('#passWd').val();
		var passWdChk = $('#passWdChk').val();
		var lastName = $('#lastName').val();
		var firstName = $('#firstName').val();
		var nickName = $('#nickName').val();
		var phoneNum = $('#phoneNum').val();


		var editErrorTitle = '';
		var editErrorMsg = '';
		// empty 는 비우고 해당 요소는 놔둠. remove는 해당 요소까지 제거.
		// $("#modal-title").empty();
	}
</script>
	<div class="container main">
		<div class="signup-main">
			<div class="signup-title-box">
				<div class="signup-title">
					<fmt:message key="MemberInfo" />
				</div>
			</div>
			
			<div class="signup-box">
				<form action="/CompanyInfoServ" method="post" onsubmit="return submitAgo()">
					<div class="signup-data-box">
					
						<div class="signup-email-box">
							<input class="form-control" type="text" name="company_name" id="name" value="<%=companyDTO.getC_Name()%>" placeholder='<fmt:message key="MemberInfo.Email"/>'/>
						</div>
						
						<div class="signup-password-box">
							<input class="form-control" type="password" name="member_password" id="passWd" value="" placeholder='<fmt:message key="MemberInfo.Password" />'/>
						</div>
						
						<div class="signup-name-box">
							<input class="form-control" type="text" name="member_last_name" id="lastName" value="<%=memberDTO.getName().split(" ")[0]%>" placeholder='<fmt:message key="MemberInfo.LastName" />' readonly="readonly"/>
							<input class="form-control" type="text" name="member_first_name" id="firstName" value="<%=memberDTO.getName().split(" ")[1]%>" placeholder='<fmt:message key="MemberInfo.FirstName" />' readonly="readonly"/>
						</div>
							
						<div class="signup-phone-num-box">
							<input class="form-control" type="text" name="member_Phonenum" id="phoneNum" value="<%=memberDTO.getPhoneNum()%>" placeholder='<fmt:message key="MemberInfo.PhoneNumber" />' />
						</div>
						
						<div class="signup-nick-name-box">
							<input class="form-control" type="text" name="member_Nickname" id="nickName" value="<%=memberDTO.getNickName()%>" placeholder='<fmt:message key="MemberInfo.NickName" />' />
						</div>
						
						<div class="signup-address-box">
							<div class="row">
								<div class="col-6">
									<input class="btn" type="button" id="add_search_btn" onclick="sample4_execDaumPostcode()" value='<fmt:message key="MemberInfo.AddressSearch" />'>
								</div>
								<div class="col-6">
									<input class="form-control" type="text" id="sample4_postcode" name="zipCode" value="<%=memberDTO.getZipcode()%>" placeholder='<fmt:message key="MemberInfo.PostNumber" />'>
								</div>
							</div>
							<div class="row">
								<div class="col-6">
									<input type="text" id="sample4_roadAddress" name="roadAddredd" value="<%=memberDTO.getRoadAddress()%>" placeholder='<fmt:message key="MemberInfo.RoadNameAddress" />'>
								</div>
								<div class="col-6">
									<input type="text" id="sample4_jibunAddress" name="jibunAddress" value="<%=memberDTO.getJibunAddress()%>" placeholder='<fmt:message key="MemberInfo.StreetNumberAddress" />'>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<input class="form-control" type="text" id="sample4_detailAddress" name="detailAddress" value="<%=memberDTO.getDetailAddress()%>" placeholder='<fmt:message key="MemberInfo.DetailAddress" />'>
								</div>
							</div>
							<span id="guide" style="color:#999;display:none"></span>
							<div class="row">
								<div class="col-12">
									<input class="form-control" type="text" id="sample4_extraAddress" name="refAddress" value="<%=memberDTO.getRefAddress()%>" placeholder='<fmt:message key="MemberInfo.AdditionalAddress" />'>
								</div>
							</div>
						</div>
						
						<div class="signup-btn-box">
							<div class="row">
								<div class="col-6 edit">
									<button type="submit" class="btn btn-light" id="submit_edit" name="operator" value="edit" ><fmt:message key="MemberInfo.Edit" /></button>
								</div>
								<div class="col-6 delete">
									<button type="submit" class="btn btn-light" id="submit_delete" name="operator" value="delete" ><fmt:message key="MemberInfo.Delete" /></button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
  	<div class="modal fade" id="modal" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="modal-title"><fmt:message key="SignUp"/></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="modal-body">
	      
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="SignUp.Modal.Close"/></button>
	      </div>
	    </div>
	  </div>
	</div>
	
<script>
	$(document).ready(function() {
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