<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/adminHeader.jsp"%>
<%@include file="/view/member/is_signIn.jsp"%>
<%@include file="/view/admin/is_admin.jsp"%>
<link href="/css/admin/admin.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
	%>
	<div class="body-main">
		<div class="container admin-main">
			<div class="row admin-main">
				<div class="col-2 admin-main">
					<jsp:include page="/view/admin/adminMenu.jsp" flush="false">
						<jsp:param value="regionSetting.jsp" name="pageName"/>
					</jsp:include>
				</div>
				<div class="col-10 admin-main">
					<div class="row admin-main-row">
						<form action="/AdminRegionServ" method="post">
							<div>
								<label>지역명</label>
							</div>
							<div>
								<input class="form-control" type="text" name="regionName">
							</div>
							<div>
								<label>지역코드</label>
							</div>
							<div>
								<input class="form-control" type="text" name="regionCode">
							</div>
							<div>
								<label>나라</label>
							</div>
							<div>
								<input class="form-control" type="text" name="country">
							</div>
							<div>
								<button type="submit" class="btn" name="operator" value="regionAdd">확인</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>