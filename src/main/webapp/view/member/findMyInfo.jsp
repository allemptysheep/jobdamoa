<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/member/findmyinfo.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<div class="body-main">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="login-title">
						<fmt:message key="Find" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<button type="button" class="btn btn-light" onclick="location.href='/member/findEmail.jsp'">
						<fmt:message key="FindEmail" />
					</button>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<button type="button" class="btn btn-light" onclick="location.href='/member/findPassword.jsp'">
						<fmt:message key="FindPassword" />
					</button>
				</div>
			</div>
			
		</div>
	</div>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>

