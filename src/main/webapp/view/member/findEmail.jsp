<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/member/findemail.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
	%>
	<script>
	function submitAgo() {
		var phoneNum = $('#phoneNum').val();

		var regexPhoneNum = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
		
		var editErrorTitle = '';
		var editErrorMsg = '';
		
		$("#modal-body").empty();
		if(phoneNum == "" || phoneNum == null) {
			editErrorMsg = '<fmt:message key="Signup.Modal.Content.PhoneNum"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").modal("show");
			return false;
		} else if (!regexPhoneNum.test(phoneNum)){
			editErrorMsg = '<fmt:message key="Signup.Modal.Content.PhoneNumCheck"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").modal("show");
			return false;
		} else {
			
		}
	}
</script>
	<div class="body-main">
		<div class="container">
			<div class="row">
				<div class="col">
					<form action="/MemberSignupServ" method="post" onsubmit="return submitAgo()">
						<div class="row form">
							<input class="form-control" type="text" name="member_Phonenum" id="phoneNum" placeholder='<fmt:message key="PhoneNumber" />' />
						</div>
						<div class="row form">
							<button type="submit" class="btn btn-light" id="submit" name="operator" value="findEmail"><fmt:message key="Search" /></button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modal" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header" style="border-bottom:none !important">
	        <h5 class="modal-title" id="modal-title"><fmt:message key="Signup"/></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="modal-body">
	      
	      </div>
	      <div class="modal-footer" style="border-top:none !important">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="Signup.Modal.Close"/></button>
	      </div>
	    </div>
	  </div>
	</div>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>