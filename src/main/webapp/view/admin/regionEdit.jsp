<%@page import="admin.AdminRegionDTO"%>
<%@page import="admin.AdminRegionDAO"%>
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
	<%
		String regionCode = (String)request.getParameter("regionCode");
	
		AdminRegionDAO adminRegionDAO = new AdminRegionDAO(application);
		AdminRegionDTO regionData =  adminRegionDAO.selectRegion(regionCode);
		
		pageContext.setAttribute("regionData", regionData);
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
								<label>인덱스</label>
							</div>
							<div>
								<input class="form-control" type="text" name="idx" value="${regionData.getIdx()}" readonly="readonly">
							</div>
							<div>
								<label>지역명</label>
							</div>
							<div>
								<input class="form-control" type="text" name="regionName" value="${regionData.getRegionName()}">
							</div>
							<div>
								<label>지역코드</label>
							</div>
							<div>
								<input class="form-control" type="text" name="regionCode" value="${regionData.getRegionCode()}">
							</div>
							<div>
								<label>나라</label>
							</div>
							<div>
								<input class="form-control" type="text" name="country" value="${regionData.getCountry()}">
							</div>
							<div>
								<button type="submit" class="btn" name="operator" value="regionEdit">확인</button>
								<button type="submit" class="btn" name="operator" value="regionDelete">삭제</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>