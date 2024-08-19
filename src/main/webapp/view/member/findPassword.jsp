<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/member/findpassword.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
	%>
	<div class="body-main">
		<div class="container">
			<div class="row">
				<div class="col">
					<form action="/MemberSignupServ" method="post" onsubmit="return submitAgo()">
						<div class="row form">
							<input class="form-control" type="text" name="member_email" id="email" placeholder='<fmt:message key="Email" />' />
						</div>
						<div class="row form">
							<input class="form-control" type="text" name="member_Phonenum" id="phoneNum" placeholder='<fmt:message key="PhoneNumber" />' />
							<button type="button" class="btn btn-light" id="PhoneNumAuth" onclick="Auth()" name="operator" value="PhoneNumAuth">인증</button>
						</div>
						<div class="row form">
							<button type="submit" class="btn btn-light" id="submit" name="operator" value="findPassword"><fmt:message key="Search" /></button>
						</div>
					</form>
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
    function Auth(){
    	var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
        var id = $('#email').val(); //id값이 "id"인 입력란의 값을 저장
        var PhoneNumAuth = $('#PhoneNumAuth').val(); //id값이 "id"인 입력란의 값을 저장

		var editErrorMsg = '';
		$("#modal-body").empty();
        if(!emailRegex.test(id)){
        	// 이메일 형식이 맞지 않음.
			editErrorMsg = '<fmt:message key="Signup.Modal.Content.EmailReg"/>';
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
                data:{id:id, operator:PhoneNumAuth},
                success:function(data){ //컨트롤러에서 넘어온 cnt값을 받는다 
                	// alert(data);
                	if(data == 1){
                		// 사용 할 수 없음
	                	editErrorMsg = '<fmt:message key="Signup.Modal.Content.EmailCheck0"/>';
	        			$("#modal-body").prepend(editErrorMsg);
	        			$("#modal").modal("show");
                		$("#submit").attr("disabled", true);
                	} else {
                		// 사용 가능 함.
	                	editErrorMsg = '<fmt:message key="Signup.Modal.Content.EmailCheck1"/>';
	        			$("#modal-body").prepend(editErrorMsg);
	        			$("#modal").modal("show");
                		$("#submit").attr("disabled", false);
                	}
                },
                error:function(){
                	editErrorMsg = '<fmt:message key="Signup.Error"/>';
        			$("#modal-body").prepend(editErrorMsg);
        			$("#modal").modal("show");
                }
            });
        }

        };
</script>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>