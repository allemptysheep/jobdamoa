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
		String pageName = request.getRequestURI().toString();
		String[] pageNameList = pageName.split("/");
		pageName = pageNameList[pageNameList.length -1].toString();
	
		pageContext.setAttribute("pageName", pageName);
	%>
	<%
		RegionDAO regionDAO = new region.RegionDAO(application);
		RegionDTO regionDTO = regionDAO.getRegion();
		RegionDTO GuDTO = regionDAO.getGu();
		
		List<Object> regionList = regionDTO.getRegionData();
		List<Object> guList = GuDTO.getGuData();
		
		pageContext.setAttribute("regionList", regionList);
		pageContext.setAttribute("guList", guList);
		pageContext.setAttribute("gThisList", guList);
	%>
	<script>
		function regionCardClick(regionCode) {

			let reCode = regionCode;
			// console.log(reCode);
			const RList = <%=regionList%>;
			const gList = <%=guList%>;
			const gThisList = gList.filter(item => item.regionCode == reCode);
			// console.log(gThisList);
			
			var htmlRegion = "";
			var html = "";
			
			RList.map(function(item){
				let itemRegionName = item.regionName;
				let itemRegionCode = item.regionCode;
				
				htmlRegion += ``;
				
				htmlRegion += `<form action="" method="post" class="region-form">`;
				htmlRegion += `<div class="card" onclick="regionCardClick(` + itemRegionCode + `)">`;
				if(regionCode == itemRegionCode){
					htmlRegion += `<div class="card-body" style="background-color:black;">`;
					htmlRegion += `<h5 class="card-title text-white">` + itemRegionName + `</h5>`;
					htmlRegion += `<p class="card-text text-white">` + itemRegionCode + `</p>`;
					
				} else {
					htmlRegion += `<div class="card-body">`;
					htmlRegion += `<h5 class="card-title">` + itemRegionName + `</h5>`;
					htmlRegion += `<p class="card-text">` + itemRegionCode + `</p>`;
					
				}
				htmlRegion += `<button type="button" class="btn region-edit" onclick="location.href='/view/admin/regionEdit.jsp?regionCode=` + itemRegionCode + `';">수정</button>`;
				htmlRegion += `</div>`;
				htmlRegion += `</div>`;
				htmlRegion += `</form>`;
			});
			
			gThisList.map(function(item){
				// console.log(item.guName);
				let guName = item.guName
				let guCode = item.guCode;
				html += ``;
				
				html += `<form action="" method="post" class="gu-form">`;
				html += `<div class="card">`;
				html += `<div class="card-body">`;
				html += `<h5 class="card-title">` + item.guName + `</h5>`;
				html += `<p class="card-text">` + guCode + `</p>`;
				html += `<button type="button" class="btn region-edit" onclick="location.href='/view/admin/regionGuEdit.jsp?guCode=` + guCode + `';">수정</button>`;
				html += `</div>`;
				html += `</div>`;
				html += `</form>`;
			});
			

			$('.row.region-list').empty();
			$('.row.region-list').append(htmlRegion);
			// console.log(html);
			$('.row.gu-list').empty();
			$('.row.gu-list').append(html);
		}
		
	</script>
	<div class="body-main">
		<div class="container admin-main">
			<div class="row admin-main">
				<div class="col-2 admin-main">
					<jsp:include page="/view/admin/adminMenu.jsp" flush="false">
						<jsp:param value="${pageName}" name="pageName"/>
					</jsp:include>
				</div>
				<div class="col-10 admin-main">
					<div class="row admin-main-row">
						<div class="row region-label">
							<div>지역</div>
							<button class="btn" onclick="location.href='/view/admin/regionAdd.jsp';">지역 추가</button>
						</div>
						<div class="row region-list">
							<c:forEach items="${regionList}" var="region" varStatus="regionStatus">
								<form action="" method="post" class="region-form">
									<div class="card" onclick="regionCardClick(${region.get('regionCode')})">
									  <div class="card-body"  style="${region.get('regionCode') eq '101000'?'background-color:black;':''}">
									    <h5 class="card-title ${region.get('regionCode') eq '101000'?'text-white':''}">${region.get('regionName')}</h5>
									    <p class="card-text ${region.get('regionCode') eq '101000'?'text-white':''}">${region.get('regionCode')}</p>
										<button type="button" class="btn region-edit" onclick="location.href='/view/admin/regionEdit.jsp?regionCode=${region.get('regionCode')}';">수정</button>
									  </div>
									</div>
								</form>
							</c:forEach>
						</div>
						<div class="row gu-label">
							<div>구 (지역을 클릭시 확인 가능)</div>
							<button class="btn" onclick="location.href='/view/admin/regionGuAdd.jsp';">구 추가</button>
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
												<button type="button" class="btn region-edit" onclick="location.href='/view/admin/regionGuEdit.jsp?guCode=${gu.get('guCode')}';">수정</button>
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