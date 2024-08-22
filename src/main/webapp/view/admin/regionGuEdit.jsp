<%@page import="java.util.List"%>
<%@page import="region.RegionDTO"%>
<%@page import="region.RegionDAO"%>
<%@page import="admin.AdminGuDTO"%>
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
		RegionDAO regionDAO = new region.RegionDAO(application);
		RegionDTO regionDTO = regionDAO.getRegion();
		
		List<Object> regionList = regionDTO.getRegionData();
		
		pageContext.setAttribute("regionList", regionList);
	%>
	<%
		String guCode = (String)request.getParameter("guCode");
		
		AdminRegionDAO adminRegionDAO = new AdminRegionDAO(application);
		AdminGuDTO guData =  adminRegionDAO.selectGu(guCode);
		
		pageContext.setAttribute("guData", guData);
		System.out.println(guCode);
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
								<input class="form-control" type="text" name="guName" value="${guData.getGuName()}">
							</div>
							<div>
								<label>구 코드</label>
							</div>
							<div>
								<input class="form-control" type="text" name="guCode" value="${guData.getGuCode()}" readonly="readonly">
							</div>
							<div>
								<label>지역코드</label>
							</div>
							<div>
								<select name="regionCode">
									<c:forEach items="${regionList}" var="region" varStatus="regionStatus">
										<option value="${region.get('regionCode')}" ${guData.getRegionCode() eq region.get('regionCode')?'selected':''}>${region.get('regionName')}</option>
									</c:forEach>
								</select>
							</div>
							<div>
								<label>나라</label>
							</div>
							<div>
								<input class="form-control" type="text" name="country"  value="${guData.getCountry()}">
							</div>
							<div>
								<button type="submit" class="btn" name="operator" value="guEdit">확인</button>
								<button type="submit" class="btn" name="operator" value="guDelete">삭제</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</fmt:bundle>