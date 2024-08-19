<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/member/login.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
	%>
	<%
		String rs = "0";
		try{
			rs = (request.getAttribute("rs").toString());
		} catch(Exception e) {
			rs = null;
		}
		
		System.out.println("process rs : " + rs);
	%>
			
	<div class="body-main">
	</div>
		<div class="modal" id="modal" tabindex="-1" role="dialog">
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
		      	<% if( rs == "0") { %>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="location.href ='/view/member/signUp.jsp'"><fmt:message key="SignUp.Modal.Close"/></button>
		        <% } else if(rs == "1") { %>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="location.href ='/view/member/signIn.jsp'"><fmt:message key="SignUp.Modal.Close"/></button>
		        <% } else {}%>
		      </div>
		    </div>
		  </div>
		</div>
	<% if( rs == "0") { %>
		<script>
			alert("실패");
			var editErrorMsg = '';
			$("#modal-body").empty();
	    	editErrorMsg = '<fmt:message key="SignUp.Fail"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").hide();
		</script>	
	<% } else if(rs == "1") { %>
		<script>
			var editErrorMsg = '';
			$("#modal-body").empty();
	    	editErrorMsg = '<fmt:message key="SignUp.Success"/>';
			$("#modal-body").prepend(editErrorMsg);
			$("#modal").show();
		</script>	
	<% } else {}%>
	</fmt:bundle>
<%@ include file="/include/footer.jsp"%>