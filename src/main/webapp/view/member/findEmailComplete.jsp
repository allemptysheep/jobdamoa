<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/member/findemail.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
		String memberEmail = (String)request.getAttribute("memberEmail");
	%>
	<div class="body-main">
		<div class="container">
			<div class="row">
				<div class="col">
					<div>ID : 
						<c:choose>
							<c:when test="${memberEmail eq ''}">no ID</c:when>
							<c:when test="${memberEmail ne ''}"><%=memberEmail%></c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>