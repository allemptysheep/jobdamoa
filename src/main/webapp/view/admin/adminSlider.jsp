<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/adminHeader.jsp"%>
<%@include file="/view/member/is_signIn.jsp"%>
<%@include file="/view/admin/is_admin.jsp"%>
<link href="/css/admin/adminslider.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
	
	%>
	<div class="body-main">
		<div class="container admin-main">
			<div class="row admin-main">
				<div class="col-2 admin-main">
					<jsp:include page="/view/admin/adminMenu.jsp"></jsp:include>
				</div>
				<div class="col-10 admin-main">
					<div class="row admin-main-row">
						<div class="row">
							<div>메인</div>
						</div>
						<div class="row">
							<form action="/SliderServ" method="post" enctype='multipart/form-data'>
								<div class="row">
									<div class="col-8">
										<input class="form-control" type="file" name="sliderFile-mainSlider" multiple="multiple">
									</div>
									<div class="col-4">
										<button class="btn" type="submit" name="operator" value="mainSlider">수정</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>