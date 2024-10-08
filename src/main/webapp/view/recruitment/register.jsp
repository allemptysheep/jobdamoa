<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@page import="region.RegionDTO"%>
<%@page import="java.util.List"%>
<%@page import="region.RegionDAO"%>
<%
		RegionDAO regionDAO = new region.RegionDAO(application);
		RegionDTO regionDTO = regionDAO.getRegion();
		RegionDTO saraminGuDTO = regionDAO.getGu();
		
		List<Object> regionList = regionDTO.getRegionData();
		List<Object> guList = saraminGuDTO.getGuData();
		
		pageContext.setAttribute("regionList", regionList);
		pageContext.setAttribute("guList", guList);

		pageContext.setAttribute("gThisList", guList);
%>
<link href="/css/recruitment/register.scss" rel="stylesheet" type="text/css">
<link href="/css/search/search.scss" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/43.0.0/ckeditor5.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css" />
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<fmt:bundle basename="resource.language">

<script type="importmap">
			{
				"imports": {
					"ckeditor5": "https://cdn.ckeditor.com/ckeditor5/43.0.0/ckeditor5.js",
					"ckeditor5/": "https://cdn.ckeditor.com/ckeditor5/43.0.0/"
				}
			}
</script>
		<script type="module">
			import {
				ClassicEditor,
				Essentials,
				Paragraph,
				Bold,
				Italic,
				Font
			} from 'ckeditor5';
			ClassicEditor
				.create( document.querySelector( '#contents' ), {
					plugins: [ Essentials, Paragraph, Bold, Italic, Font ],
					toolbar: [
						'undo', 'redo', '|', 'bold', 'italic', '|',
						'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor'
					]
				} )
				.then( editor => {
					window.editor = editor;
				} )
				.catch( error => {
					console.error( error );
				} );
</script>
<script>
	$(document).ready(function() {
		$('#apply_startdate')
		.datepicker({
		    dateFormat: 'yymmdd'//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
		})
		.on('changeDate', function (e) {
		    console.log(e);
		});
		
		
		$('#apply_enddate')
		.datepicker({
		    dateFormat: 'yymmdd'//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
		})
		.on('changeDate', function (e) {
		    console.log(e);
		});
		
		var today = new Date();
		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2);
		$(document).on("change", "#apply_startdate", function() {
			var maxdate  = $("#apply_startdate").val();
			var sYear = maxdate.substring(0,4);
			var sMonth = maxdate.substring(4,6);
			var sDate = maxdate.substring(6,8);
			var custom_mindate =  new Date(Number(sYear), Number(sMonth)-1, Number(sDate));
			
			$("#apply_enddate").datepicker("option", "minDate", custom_mindate);		
			
			// 종료일은 시작일보다 이전의 날짜로 설정할 수 없음
		});
		
		$(document).on("change", "#apply_enddate", function() {
			var maxdate = $("#apply_enddate").val();
			var sYear = maxdate.substring(0,4);
			var sMonth = maxdate.substring(4,6);
			var sDate = maxdate.substring(6,8);
			var custom_maxdate =  new Date(Number(sYear), Number(sMonth)-1, Number(sDate));
			
			$("#apply_startdate").datepicker("option", "maxDate", custom_maxdate);
			// 시작일은 종료일보다 이후의 날짜로 설정할 수 없음
		});
		
		
	});
	

	let selectedGuCode = [];
	let selectedGuName = [];
	let selectedRegionCode = [];
	
	function selectRegion(){
		let area = document.getElementById('area-region');
		if(area.style.display == "none" || area.style.display == ""){
			area.style.display = "block";
		} else if (area.style.display == 'block') {
			area.style.display = "none";
		}
	}
	
	function regionClick(regionCode, regionName){
		let reCode = regionCode;
		let reName = regionName;
		// console.log(reCode);
		const RList = <%=regionList%>;
		const gList = <%=guList%>;
		const gThisList = gList.filter(item => item.regionCode == reCode);
		// console.log(gThisList);
		
		var html = "";
		
		gThisList.map(function(item){
			// console.log(item.guName);
			let code = item.guCode
			let recode = item.regionCode;
			let name = item.guName;
			html += `<button type="button" class="btn gu" id="` + code + `" onclick="guClick(` + code + `,'` + name + `',` + recode + `)">` + name + `</button>`;
		});
		
		// console.log(html);
		$('.col-8.gu').empty();
		$('.col-8.gu').append(html);
		
		// 지역 선택한 경우 지역명과 지역코드 자동 입력
		$('#regionname').val(reName);
		$('#regioncode').val(reCode);
		// 지역 선택한 경우 구 정보 리셋
		$('#guname').val("");
		$('#gucode').val("");
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
		
		$('#guname').val(guName);
		$('#gucode').val(guCode);
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
	
	function submitAgo() {
		var title = $('#title').val();
		var contents = $('#contents').val();
		var cname = $('#cname').val();
		var hiretype = $('#hiretype').val();
		var workhistory = $('#workhistory').val();
		var regionname = $('#regionname').val();
		var regioncode = $('#regioncode').val();
		var guname = $('#guname').val();
		var gucode = $('#gucode').val();
		var apply_startdate = $('#apply_startdate').val();
		var apply_enddate = $('#apply_enddate').val();
		var apply_method = $('#apply_method').val();
	

		var editErrorTitle = '';
		var editErrorMsg = '';
		// empty 는 비우고 해당 요소는 놔둠. remove는 해당 요소까지 제거.
		// $("#modal-title").empty();
		
	}
</script>
	<div class="container main">
		<div class="register-main">
			<div class="register-title-box">
				<div class="register-title">
					<fmt:message key="Recruitment" />
				</div>
			</div>
			
			<div class="register-box">
				<form action="/RecruitmentServ" method="post" onsubmit="return submitAgo()">
					<div class="register-data-box">
					
						<div class="register-title-box">
							<input class="form-control" type="text" name="recruitment_title" id="title" placeholder='<fmt:message key="Recruitment.Title" />' />
						</div>
						<div class="register-contents-box">
							<textarea class="form-control" name="recruitment_contents" id="contents"><fmt:message key="Recruitment.Contents" /></textarea>
						</div>
						
						<div class="register-cname-box">
							<input class="form-control" type="text" name="recruitment_c_name" id="cname" placeholder='<fmt:message key="Recruitment.Cname" />' />
						</div>
						
						<div class="register-hiretype-box">
							<label class = "form-label"><fmt:message key="Recruitment.Hiretype" /></label>
							<input class="" type="radio" name="recruitment_hire_type" id="hiretype" value = '정규직' /><fmt:message key="Recruitment.Hiretype_FullTime" />
							<input class="" type="radio" name="recruitment_hire_type" id="hiretype" value = '계약직' /><fmt:message key="Recruitment.Hiretype_Contract" />
						</div>
						
						<div class="register-workhistory-box">
							<label class = "form-label"><fmt:message key="Recruitment.Workhistory" /></label>
							<input class="form-control" type="radio" name="recruitment_work_history" id="workhistory" value='신입' /><fmt:message key="Recruitment.Workhistory_New" />
							<input class="form-control" type="radio" name="recruitment_work_history" id="workhistory" value='1~3년' /><fmt:message key="Recruitment.Workhistory_1to3" />
							<input class="form-control" type="radio" name="recruitment_work_history" id="workhistory" value='3~5년' /><fmt:message key="Recruitment.Workhistory_3to5" />
							<input class="form-control" type="radio" name="recruitment_work_history" id="workhistory" value='5~10년' /><fmt:message key="Recruitment.Workhistory_5to10" />
							<input class="form-control" type="radio" name="recruitment_work_history" id="workhistory" value='10년이상' /><fmt:message key="Recruitment.Workhistory_Over10" />
						</div>
						<div class="register-region-box">
						<div class="row">
							<div class="col">
								<button type="button" class="btn" onclick="selectRegion()">지역선택</button>
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
											<button type="button" class="btn region" id="${region.get('regionCode')}" onclick="regionClick(${region.get('regionCode')}, '${region.get('regionName')}')">${region.get('regionName')}</button>
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
							<div class="row">
								<div class="col-6">
									<input class="form-control" type="text" name="recruitment_region_name" id="regionname" placeholder='<fmt:message key="Recruitment.Regionname" />'>
								</div>
								<div class="col-6">
									<input class="form-control" type="text" name="recruitment_region_code" id="regioncode" placeholder='<fmt:message key="Recruitment.Regioncode" />'>
								</div>
							</div>
							<div class="row">
								<div class="col-6">
									<input type="text" name="recruitment_gu_name" id="guname"  placeholder='<fmt:message key="Recruitment.Guname" />'>
								</div>
								<div class="col-6">
									<input type="text" name="recruitment_gu_code" id="gucode" placeholder='<fmt:message key="Recruitment.Gucode" />'>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<input class="form-control" type="text"  name="recruitment_apply_startdate" id="apply_startdate" placeholder='<fmt:message key="Recruitment.Applystartdate" />'>
								</div>
							</div>
							<div class="row">
								<div class="col-12">
									<input class="form-control" type="text" name="recruitment_apply_enddate" id="apply_enddate" placeholder='<fmt:message key="Recruitment.Applyenddate" />'>
								</div>
							</div>
						</div>
						<div class="register-applymethod-box">
									<label class = "form-label"><fmt:message key="Recruitment.Applymethod" /></label>
									<input class="form-control" type="radio" name="recruitment_apply_method" id="apply_method" value='자사 홈페이지' /><fmt:message key="Recruitment.Applymethod_Homepage" />
									<input class="form-control" type="radio" name="recruitment_apply_method" id="apply_method" value='잡다모아 지원' /><fmt:message key="Recruitment.Applymethod_Jobdamoa" />
						</div>
						
						<div class="register-btn-box">
							<button type="submit" class="btn btn-light" id="submit" name="operator" value="submit"><fmt:message key="Recruitment.Register" /></button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
  	<div class="modal fade" id="modal" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header" style="border-bottom:none !important">
	        <h5 class="modal-title" id="modal-title"><fmt:message key="SignUp"/></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="modal-body">
	      
	      </div>
	      <div class="modal-footer" style="border-top:none !important">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="SignUp.Modal.Close"/></button>
	      </div>
	    </div>
	  </div>
	</div>
	
<script>
	$(document).ready(function() {
		$("#email").change(function () {
			$("#submit").attr("disabled", true);
		});
	});
</script>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>