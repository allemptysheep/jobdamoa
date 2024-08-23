<%@page import="search.MainSearchListDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="region.RegionDTO"%>
<%@page import="java.util.List"%>
<%@page import="region.RegionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/search/search.scss" rel="stylesheet" type="text/css">
<link href="/css/recruitment/recruitment.scss" rel="stylesheet" type="text/css">
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
		

		
	
		MainSearchListDTO searchList = (MainSearchListDTO)request.getAttribute("mainSearchListDTO");
		pageContext.setAttribute("searchList", searchList);
		
		String keyword_old = request.getParameter("keyword");
		String regionList_old = request.getParameter("regionList");
		String guList_old = request.getParameter("guList");
		
		System.out.print("oldkey : " + regionList_old);
		
// paging
		int page_now = Integer.parseInt(request.getAttribute("page_now").toString());
		int pageSize = Integer.parseInt(request.getAttribute("pageSize").toString());
		int blockSize = Integer.parseInt(request.getAttribute("blockSize").toString());
		int totalCount = Integer.parseInt(request.getAttribute("totalCount").toString());
		int totalPage = Integer.parseInt(request.getAttribute("totalPage").toString());
		int first = Integer.parseInt(request.getAttribute("first").toString());
		int last = Integer.parseInt(request.getAttribute("last").toString());
		
		
	%>
	<script>
	
		let selectedGuCode = [];
		let selectedGuName = [];
		let selectedRegionCode = [];
	      
	      function selectRegion(){
	         let area = document.getElementById('area-region');
	         let areaClose = document.getElementById('area-job');

	         let regionBtn = document.getElementById('btn-region');
	         let jobBtn = document.getElementById('btn-job');
	         
	         if(area.style.display == "none" || area.style.display == ""){
	            area.style.display = "block";
	            areaClose.style.display = "none";
	            
	            regionBtn.style.color = "white";
	            regionBtn.style.backgroundColor = "black";

	            jobBtn.style.color = "black";
	            jobBtn.style.backgroundColor = "#f1f1f1";
	            
	         } else if (area.style.display == 'block') {
	            area.style.display = "none";
	            
	            regionBtn.style.color = "black";
	            regionBtn.style.backgroundColor = "#f1f1f1";
	         }
	      }
	      
	      function selectJob(){
	         let area = document.getElementById('area-job');
	         let areaClose = document.getElementById('area-region');
	         
	         let regionBtn = document.getElementById('btn-region');
	         let jobBtn = document.getElementById('btn-job');
	         
	         if(area.style.display == "none" || area.style.display == ""){
	            area.style.display = "block";
	            areaClose.style.display = "none";

	            jobBtn.style.color = "white";
	            jobBtn.style.backgroundColor = "black";

	            regionBtn.style.color = "black";
	            regionBtn.style.backgroundColor = "#f1f1f1";
	         } else if (area.style.display == 'block') {
	            area.style.display = "none";
	            
	            jobBtn.style.color = "black";
	            jobBtn.style.backgroundColor = "#f1f1f1";
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
		
		function recruitmentView(idx){
			var link = '/view/recruitment/recruitmentView.jsp?recIdx=' + idx;
			location.href=link;
		}
	</script>
	<div class="container main">
		<div class="row search">
			<form action="/MainSearchServ" method="post">
				<div class="row select-search-option">
					<div class="col-6 region-select-box">
						<button type="button" class="btn" id="btn-region" onclick="selectRegion()">지역선택</button>
					</div>
					<div class="col-6 job-select-box">
						<button type="button" class="btn" id="btn-job" onclick="selectJob()">직업선택</button>
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
		                        <c:choose>
		                           <c:when test="${region.get('regionCode') eq 101000}">
		                              <button type="button" style="background-color: black;color:white;" class="btn region" id="${region.get('regionCode')}" onclick="regionClick(${region.get('regionCode')})">${region.get('regionName')}</button>
		                           </c:when>
		                           <c:when test="${region.get('regionCode') ne 101000}">
		                              <button type="button" class="btn region" id="${region.get('regionCode')}" onclick="regionClick(${region.get('regionCode')})">${region.get('regionName')}</button>
		                           </c:when>
		                        </c:choose>
		                     </c:forEach>
						</div>
						<div class="col-8 gu">
							<c:forEach items="${guList}" var="gu" varStatus="regionStatus">
		                       <button type="button" class="btn gu" id="${gu.get('guCode')}" onclick="guClick(${gu.get('guCode')}, '${gu.get('guName')}', ${gu.get('regionCode')})">${gu.get('guName')}</button>
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
				
				<div class="row submit">
					<div class="col submit">
						<input type="text" id="keyword" name="keyword">
						<input type="hidden" id="regionList" name="regionList" value='${regionCodeList}'>
						<input type="hidden" id="guList" name="guList">
						<input hidden="true" name="first" value="0">
						<input hidden="true" name="last" value="10">
						<button class="btn" type="submit" name="operator" value="search" onclick="return search()">검색</button>
					</div>
				</div>
			</form>
		</div>
		<div class="row">
			<c:choose>
				<c:when test="${searchList.getMainSearchData().size() eq 0}">
					<div>0</div>
				</c:when>
				<c:when test="${searchList.getMainSearchData().size() ne 0}">
					<c:forEach items="${searchList.getMainSearchData()}" var="searchData" varStatus="searchDataStatus">
						<div class="row search-result">
								<div class = "col">
									<div class = "row">
										<ul class = "full-ul">
											<li class = "full-li">
												<div class = "list-top">
													<c:out value = "${searchData.get('m_email')}"></c:out>
												</div>
												<div class = "list-middle">
													<div class = "in-list-top">
														<c:out value = "${searchData.get('rec_title')}"></c:out>
													</div>
													<div class = "in-list-middle">
														<c:out value = "${searchData.get('rec_title')}"></c:out>
													</div>
													<div class = "in-list-bottom">
														<c:out value = "${searchData.get('rec_title')}"></c:out>
													</div>
													
												</div>
												<div class = "list-bottom">
													<c:out value = "${recruit.get('rec_apply_startdate')}"></c:out>
													<button onclick="recruitmentView(${searchData.get('rec_idx')})">자세히</button>
													<a href = "/view/recruitment/recruitmentEdit.jsp?rec_idx=${searchData.get('rec_idx')}">수정</a>
												</div>
										  </li>
										</ul>
									</div>		
								</div>
						</div>
					</c:forEach>
					<div class="list-write-box">
							<div class="list-page-box">
								<%
							
									// 페이징 처리 시작 /////////////////////////////
									
									int total_block = 0;
									
									int block = 0;
									
									int first_page = 0;
									
									int last_page = 0;
									
									int direct_page = 0;
									
									int my_page = 0;
									
									
									total_block = (int)Math.ceil(totalPage / (double)blockSize); //총 블럭 수
									
									block = (int)Math.ceil(page_now / (double)blockSize); //현재 블럭
									
									first_page = (block - 1) * blockSize; //블럭내 시작하는 수
									
									last_page = block * blockSize; //블럭내 끝나는 수
									
									
									
									if(total_block <= block) { //마지막 블럭일 때
									
										last_page = totalPage; //반복문 변수 처리
									
									}
									
									
									
									
									
									// 이전 블럭 처리			
									
									if(block != 1){ //첫 블럭이 아니라면
									
									%>
									<form action="/MainSearchServ" method="post" onsubmit="return agoSearch()">
									<input class="form-control" id="keyword" name="keyword" value = "<%= keyword_old %>" hidden="true">
									<input class="form-control" id="regionList" name="regionList" value='<%= regionList_old %>' hidden="true">
									<input class="form-control" id="guList" name="guList" value = "<%= guList_old %>" hidden="true">
										<input
											class="form-control" name="page_now" id="page_now"
											value="<%=first_page%>" placeholder="페이지" hidden="true">
										<button class="btn search" type="submit" name="operator"
											value="search">
											<a class="indicator-btn"><img src="/img/btn_left.gif"></a>
										</button>
									</form>
								<%
									
									}
									
									
									
									// 블럭내 페이지 수 출력 
									
									for(direct_page = first_page + 1; direct_page <= last_page; direct_page++){
									
										if(page_now == direct_page){
									
									%>

								<span class="page_on"><%=direct_page%></span>&nbsp;

								<%
									
										}else{
									
									%>

								<span class="page_off">
									<form action="/MainSearchServ" method="post" onsubmit="return agoSearch()">
									<input class="form-control" id="keyword" name="keyword" value = "<%= keyword_old %>" hidden="true">
									<input class="form-control" id="regionList" name="regionList" value='<%= regionList_old %>' hidden="true">
									<input class="form-control" id="guList" name="guList" value = "<%= guList_old %>" hidden="true">
										<input
											class="form-control" name="page_now" id="page_now"
											value="<%=direct_page%>" placeholder="페이지" hidden="true">
										<button class="btn search" type="submit" name="operator"
											value="search">
											<%=direct_page%>
										</button>
									</form>
								</span>&nbsp;

								<%
									
										}
									
									}
									
										
									
									// 다음 블럭 처리
									
									if(block < total_block) { //다음 블럭 존재
									
								%>

								&nbsp;
									<form action="/MainSearchServ" method="post" onsubmit="return agoSearch()">
									<input class="form-control" id="keyword" name="keyword" value = "<%= keyword_old %>" hidden="true">
									<input class="form-control" id="regionList" name="regionList" value='<%= regionList_old %>' hidden="true">
									<input class="form-control" id="guList" name="guList" value = "<%= guList_old %>" hidden="true">
										<input
											class="form-control" name="page_now" id="page_now"
											value="<%=last_page + 1%>" placeholder="페이지" hidden="true">
										<button class="btn search" type="submit" name="operator"
											value="getLog">
											<a class="indicator-btn"><img src="/img/btn_right.gif"></a>
										</button>
									</form>
								<%
									
									}
									
									//페이징 처리 끝 /////////////////////////////			
									
									%>
							</div>
							<div></div>
						</div>
				</c:when>
			</c:choose>
		</div>
	</div>
</fmt:bundle>