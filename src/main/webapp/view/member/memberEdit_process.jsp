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
				String rs;
				try{
					rs = (request.getAttribute("rs").toString());
				} catch(Exception e) {
					rs = "null";
				}
			
			%>
			<% if( rs == "null" || rs == "0") { %>
				<script>
					alert("회원 수정 실패.");
					history.back();
				</script>	
			<% } else if(rs == "1") { %>
				<script>
					alert("회원 수정 완료. 데이터가 정상적으로 수정 되었습니다");
					location.href="/view/member/memberEdit.jsp";
				</script>	
			<% } %>
	<div class="body-main"></div>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>