<%@page import="saramin.SaraminRegionDTO"%>
<%@page import="java.util.List"%>
<%@page import="saramin.SaraminRegionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/saramin/saraminsearch.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
		SaraminRegionDAO saraminRegionDAO = new SaraminRegionDAO(application);
		SaraminRegionDTO saraminRegionDTO = saraminRegionDAO.getRegion();
		SaraminRegionDTO saraminGuDTO = saraminRegionDAO.getGu();
		
		List<Object> regionList = saraminRegionDTO.getSaraminRegionData();
		List<Object> guList = saraminGuDTO.getSaraminGuData();
		
		pageContext.setAttribute("regionList", regionList);
		pageContext.setAttribute("guList", guList);

		pageContext.setAttribute("gThisList", guList);
	%>
	<script>
		function selectRegion(){
			let area = document.getElementById('area');
			if(area.style.display == "none" || area.style.display == ""){
				area.style.display = "block";
			} else if (area.style.display == 'block') {
				area.style.display = "none";
			}
		}
		
		function regionClick(regionCode){
			let reCode = regionCode;
			console.log(reCode);
			const RList = <%=regionList%>;
			const gList = <%=guList%>;
			const gThisList = gList.filter(item => item.regionCode == reCode);
			console.log(gThisList);
			
			var html = "";
			
			gThisList.map(function(item){
				console.log(item.guName);
				let code = item.guCode
				let name = item.guName;
				html += `<button type="button" class="btn gu" id="` + code + `" onclick="guClick(` + code + `)">` + name + `</button>`;
			});
			
			console.log(html);
			$('.col-8.gu').empty();
			$('.col-8.gu').append(html);
				 
		}
		
		function guClick(guCode){
			console.log(guCode);
		}
		
	</script>
	<div class="container main">
		<div class="row">
			<form action="" method="post">
				<div class="row">
					<div class="col">
						<button type="button" class="btn" onclick="selectRegion()">지역선택</button>
						<select>
							<option value="">지역 선택</option>
							<c:forEach items="${regionList}" var="region" varStatus="regionStatus">
								<option value="${region.get('regionCode')}">${region.get('regionName')}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row area" id="area">
					<div class="row region-search">
						<div class="col region-search">
							<input>
						</div>
					</div>
					<div class="row viewport">
						<div class="col-4 region">
							<c:forEach items="${regionList}" var="region" varStatus="regionStatus">
								<button type="button" class="btn region" id="${region.get('regionCode')}" onclick="regionClick(${region.get('regionCode')})">${region.get('regionName')}</button>
							</c:forEach>
						</div>
						<div class="col-8 gu">
							<c:forEach items="${guList}" var="gu" varStatus="regionStatus">
								<c:choose>
									<c:when test="${gu.get('regionCode') eq 101000}">
										<button type="button" class="btn gu" id="${gu.get('guCode')}" onclick="regionClick(${gu.get('guCode')})">${gu.get('guName')}</button></c:when>
								</c:choose>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<input type="text">
					</div>
				</div>
			</form>
		</div>
		<%@ include file="/include/footer.jsp"%>
	</div>
</fmt:bundle>