<%@page import="java.util.ArrayList"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="region.RegionDTO"%>
<%@page import="java.util.List"%>
<%@page import="region.RegionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/search/search.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
		RegionDAO regionDAO = new region.RegionDAO(application);
		RegionDTO regionDTO = regionDAO.getRegion();
		RegionDTO saraminGuDTO = regionDAO.getGu();
		
		List<Object> regionList = regionDTO.getRegionData();
		List<Object> guList = saraminGuDTO.getGuData();
		
		List<String> regionCodeList = new ArrayList<String>(); 
		for(Object r : regionList){    
			JSONObject json = (JSONObject) JSONValue.parse(r.toString());
			String val = json.get("regionCode").toString();
			regionCodeList.add(val);
		}

		pageContext.setAttribute("regionCodeList", regionCodeList);
		pageContext.setAttribute("regionList", regionList);
		pageContext.setAttribute("guList", guList);
		pageContext.setAttribute("gThisList", guList);
	%>
	<script>
	
		let selectedGuCode = [];
		let selectedGuName = [];
		let selectedRegionCode = [];
		
		function selectRegion(){
			let area = document.getElementById('area-region');
			let areaClose = document.getElementById('area-job');
			if(area.style.display == "none" || area.style.display == ""){
				area.style.display = "block";
				areaClose.style.display = "none";
			} else if (area.style.display == 'block') {
				area.style.display = "none";
			}
		}
		
		function selectJob(){
			let area = document.getElementById('area-job');
			let areaClose = document.getElementById('area-region');
			if(area.style.display == "none" || area.style.display == ""){
				area.style.display = "block";
				areaClose.style.display = "none";
			} else if (area.style.display == 'block') {
				area.style.display = "none";
			}
		}
		
		function regionClick(regionCode){
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
				
				if(regionCode == itemRegionCode){
					htmlRegion += `<button style="background-color:black;color: white;font-weight: bold;" type="button" class="btn region" id="` + itemRegionCode + `" onclick="regionClick(` + itemRegionCode + `)">` + itemRegionName + `</button>`;
					
				} else {
					htmlRegion += `<button type="button" class="btn region" id="` + itemRegionCode + `" onclick="regionClick(` + itemRegionCode + `)">` + itemRegionName + `</button>`;
				}
			});
			
			gThisList.map(function(item){
				// console.log(item.guName);
				let code = item.guCode
				let recode = item.regionCode;
				let name = item.guName;
				html += `<button type="button" class="btn gu" id="` + code + `" onclick="guClick(` + code + `,'` + name + `',` + recode + `)">` + name + `</button>`;
			});

			$('.col-4.region').empty();
			$('.col-4.region').append(htmlRegion);
			// console.log(html);
			$('.col-8.gu').empty();
			$('.col-8.gu').append(html);
				 
		}
		
		function guClick(guCode, guName, regionCode){

			// 전체 눌렀을 때 개별 사라지게
			if(guCode == regionCode){
				
				let reGuCode = [];
				let reGuName = [];
				let reReCode = [];
				
				console.log('selectedRegionCode : ' + selectedRegionCode);
				console.log('selectedRegionCode : ' + selectedRegionCode.length);
				selectedRegionCode.map(function (item, index){
					console.log('item : ' + item);
					console.log('regionCode : ' + regionCode);
					if(item != regionCode){
						reGuCode.push(item);
						reGuName.push(selectedGuName[index]);
						reReCode.push(selectedRegionCode[index]);
					}
				});
				
				console.log(reGuCode);
				console.log(reGuName);
				console.log(reReCode);
				
				selectedGuCode = reGuCode;
				selectedGuName = reGuName;
				selectedRegionCode = reReCode;
			}

			
			if(selectedGuCode.length >= 10){
				alert("10개 이상 선택 할 수 없습니다.");
				return false;	
			}
			
			// 개별 눌렀을 때 전체 사라지게
			let isInReCode = selectedGuCode.indexOf(regionCode);
			console.log('isInReCode : ' + isInReCode);
			if(isInReCode >= 0){
				selectedGuCode.splice(isInReCode, 1);
				selectedGuName.splice(isInReCode, 1);
				selectedRegionCode.splice(isInReCode, 1);
			}
			
			// 개별 눌렀을 때 배열에 들어가게
			let html = "";
			let isIn = selectedGuCode.indexOf(guCode);
			if(isIn == -1){
				// 데이터가 배열 안에 없음.
				selectedGuCode.push(guCode);
				selectedGuName.push(guName);
				selectedRegionCode.push(regionCode);
				
			} else if (isIn >= 0){
				// 데이터가 배열 안에 있음.
				selectedGuCode.splice(isIn, 1);
				selectedGuName.splice(isIn, 1);
				selectedRegionCode.splice(isIn, 1);
			}
			console.log(selectedGuCode);
			
			selectedGuCode.map(function(item, index){
				html += `<div class="selected-div">` + selectedGuName[index] + `<button type="button" class="btn seleted-gu" onclick="guX(` + item + `)">X</button></div>`;
			});
			
			$('.selected-region').empty();
			$('.selected-region').append(html);
		}
		
		function guX(guCode){
			
			let html = "";
			let isIn = selectedGuCode.indexOf(guCode);
			selectedGuCode.splice(isIn, 1);
			selectedGuName.splice(isIn, 1);
			selectedRegionCode.splice(isIn, 1);
			
			selectedGuCode.map(function(item, index){
				html += `<div class="selected-div">` + selectedGuName[index] + `<button type="button" class="btn seleted-gu" onclick="guX(` + item + `)">X</button></div>`;
			});
			
			$('.selected-region').empty();
			$('.selected-region').append(html);
		}
		
		function search(){
			if(selectedGuCode.length == 0){
				alert('지역 선택은 필수 입니다.');
				return false;
			}
			console.log(selectedGuCode);
			document.getElementById("guList").setAttribute('value', selectedGuCode);
		}
	</script>
	<div class="container main">
		<div class="row">
			<form action="/MainSearchServ" method="post">
				<div class="row select-search-option">
					<div class="col-6 region-select-box">
						<button type="button" class="btn" onclick="selectRegion()">지역선택</button>
					</div>
					<div class="col-6 job-select-box">
						<button type="button" class="btn" onclick="selectJob()">직업선택</button>
					</div>
				</div>
				<div class="row area-region" id="area-region">
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
										<button type="button" class="btn gu" id="${gu.get('guCode')}" onclick="guClick(${gu.get('guCode')}, '${gu.get('guName')}', ${gu.get('regionCode')})">${gu.get('guName')}</button></c:when>
								</c:choose>
							</c:forEach>
						</div>
					</div>
					<div class="selected-region">
					</div>
				</div>
				
				<div class="row area-job" id="area-job">
					<div class="row job-search">
						<div class="col job-search">
							<input>
						</div>
					</div>
					<div class="row viewport">
						<div class="col-4 job-class">
							<c:forEach items="${regionList}" var="region" varStatus="regionStatus">
								<button type="button" class="btn job-class" id="${region.get('regionCode')}" onclick="regionClick(${region.get('regionCode')})">${region.get('regionName')}</button>
							</c:forEach>
						</div>
						<div class="col-8 job">
							<c:forEach items="${guList}" var="gu" varStatus="regionStatus">
								<c:choose>
									<c:when test="${gu.get('regionCode') eq 101000}">
										<button type="button" class="btn job" id="${gu.get('guCode')}" onclick="guClick(${gu.get('guCode')}, '${gu.get('guName')}', ${gu.get('regionCode')})">${gu.get('guName')}</button></c:when>
								</c:choose>
							</c:forEach>
						</div>
					</div>
					<div class="selected-job-class">
					</div>
				</div>
				
				<div class="row">
					<div class="col">
						<input type="text" id="keyword" name="keyword">
						<input type="hidden" id="regionList" name="regionList" value='${regionCodeList}'>
						<input type="hidden" id="guList" name="guList">
						<button class="btn" type="submit" name="operator" value="search" onclick="return search()">검색</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</fmt:bundle>
