<%@page import="recruitment.RecruitmentDTO"%>
<%@page import="recruitment.RecruitmentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/recruitment/recruitmentview.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
		String recIdx = request.getParameter("recIdx");
		
		RecruitmentDAO recruitmentDAO = new RecruitmentDAO(application);
		RecruitmentDTO data = recruitmentDAO.getRecruitDTO(Integer.parseInt(recIdx));
		// System.out.println(recIdx);
		System.out.println(data);
		
		pageContext.setAttribute("data", data);
		
		/*
		 member
		*/

		String mEmail = (String)session.getAttribute("mEmail");
		System.out.println("mEmail : " + mEmail);
		pageContext.setAttribute("mEmail", mEmail);
		
		if(mEmail != null){
			
		}
	%>
	<script>
		function resumeSubmit() {
			if(${mEmail eq null}){
				alert("로그인이 필요합니다.");
				return false;
			} else {
				alert("${mEmail}");
			}
		}
	</script>
	<div class="container main">
		<div class="row recruitment-view-main">
			<div class="row title-box">
				<div class="row title">
						<h1>${data.getRec_title()}</h1>
						<button class="btn" onclick="resumeSubmit()">이력서 제출</button>
				</div>
				<div class="row info">
					<div class="col-6">
						<div class="row">
							<div class="col-6"><fmt:message key="Recruitment.Hiretype" /></div>
							<div class="col-6">${data.getRec_hire_type()}</div>
						</div>
						<div class="row">
							<div class="col-6"><fmt:message key="Recruitment.Regionname" /></div>
							<div class="col-6">${data.getRegion_name()} ${data.getGu_name()}</div>
						</div>
						<div class="row">
							<div class="col-6"><fmt:message key="Recruitment.Workhistory" /></div>
							<div class="col-6">${data.getRec_work_history()}</div>
						</div>
					</div>
					<div class="col-6">
						<div class="row">
							<div class="col-6">기간</div>
							<div class="col-6">${data.getRec_apply_startdate()} ~ ${data.getRec_apply_enddate()}</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row data-box">
				<div class="row data-btn-box">
					<div class="data-btn">상세정보</div>
					<div class="data-btn">취업내용</div>
				</div>
				
				<div class="row data-contents">
					<div class="col">
						${data.getRec_contents()}
					</div>
				</div>
			</div>
			

		</div>
	</div>
</fmt:bundle>