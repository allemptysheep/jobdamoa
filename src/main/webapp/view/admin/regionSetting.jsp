<%@page import="java.util.List"%>
<%@page import="region.RegionDTO"%>
<%@page import="region.RegionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/adminHeader.jsp"%>
<%@include file="/view/member/is_signIn.jsp"%>
<%@include file="/view/admin/is_admin.jsp"%>
<link href="/css/admin/regionsetting.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
	%>
	<%
		RegionDAO regionDAO = new region.RegionDAO(application);
		RegionDTO regionDTO = regionDAO.getRegion();
		RegionDTO saraminGuDTO = regionDAO.getGu();
		
		List<Object> regionList = regionDTO.getRegionData();
		List<Object> guList = saraminGuDTO.getGuData();
		
		pageContext.setAttribute("regionList", regionList);
		pageContext.setAttribute("guList", guList);
	%>
	<div class="body-main">
		<div class="container admin-main">
			<div class="row admin-main">
				<div class="col-2 admin-main">
					<jsp:include page="/view/admin/adminMenu.jsp"></jsp:include>
				</div>
				<div class="col-10 admin-main">
					<div class="row admin-main-row">
						<div class="row region-label">
							<div>지역</div>
						</div>
						<div class="row region-list">
							<c:forEach items="${regionList}" var="region" varStatus="regionStatus">
								<form action="" method="post" class="region-form">
									<div class="card">
									  <div class="card-body">
									    <h5 class="card-title">${region.get('regionName')}</h5>
									    <p class="card-text">${region.get('regionCode')}</p>
										<button type="button" class="btn region-edit">수정</button>
									  </div>
									</div>
								</form>
							</c:forEach>
						</div>
						<div class="row gu-label">
							<div>구 (지역을 클릭시 확인 가능)</div>
						</div>
						<div class="row gu-list">
							<c:forEach items="${guList}" var="gu" varStatus="guStatus">
								<c:choose>
									<c:when test="${gu.get('regionCode') eq 101000}">
										<form action="" method="post" class="gu-form">
											<div class="card">
											  <div class="card-body">
											    <h5 class="card-title">${gu.get('guName')}</h5>
											    <p class="card-text">${gu.get('guCode')}</p>
												<button type="button" class="btn region-edit">수정</button>
											  </div>
											</div>
										</form>
									</c:when>
								</c:choose>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</fmt:bundle>