<%@page import="recruitment.SubmittedResumeDAO"%>
<%@page import="resume.ResumeDAO"%>
<%@page import="java.util.List"%>
<%@page import="recruitment.RecruitmentDTO"%>
<%@page import="recruitment.RecruitmentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/recruitment/recruitmentview.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
		String recIdx = request.getParameter("recIdx");
		pageContext.setAttribute("recIdx", recIdx);
			
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
			ResumeDAO resumeDAO = new ResumeDAO(application);
			List<Object> resumeList = resumeDAO.selectResumeList(mEmail);
			pageContext.setAttribute("resumeList", resumeList);

			// System.out.println("list : " + resumeList);
			// System.out.println("list size : " + resumeList.size());
			
			Integer mLevel = (Integer) session.getAttribute("mLevel");
			
			SubmittedResumeDAO submitedResumeDAO = new SubmittedResumeDAO(application);
			int submitted = submitedResumeDAO.selectExists(recIdx, mEmail);
			pageContext.setAttribute("submitted", submitted);
			
			// System.out.println("is : " + submitted);
			}
	%>
	<script>
		function resumeSubmit() {
			if(${mEmail eq null}){
				alert("로그인이 필요합니다.");
				return false;
			} else {
				if(${mLevel eq "10"}){
					alert("관리자는 이력서 제출이 불가능 합니다.");
					return false;
				} else if (${mLevel eq "9"}){
					alert("기업 회원은 이력서 제출이 불가능 합니다.");
					return false;
				} else {
					if(${submitted eq "1"}){
						alert("이미 제출 되었습니다.");
						return false;
					} else {
						$("#modal").modal("show");
					}
				}
			}
		}
	</script>
	<div class="container main">
		<div class="row recruitment-view-main">
			<div class="row title-box">
				<div class="row title">
						<h1>${data.getRec_title()}</h1>
						<button class="btn" type="button" onclick="resumeSubmit()">이력서 제출</button>
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
	
	<div class="modal fade" id="modal" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header" style="border-bottom:none !important">
	        <h5 class="modal-title" id="modal-title">이력서 제출</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="modal-body">
	      		<c:choose>
	      			<c:when test="${resumeList.size() eq 0}">
	      				<div class="modal-resume">
	      					이력서가 없습니다.
	      					<a href="/view/resume/resumeWrite.jsp">이력서 작성</a>
	      				</div>
	      			</c:when>
	      			<c:when test="${resumeList.size() ne 0}">
			      		<c:forEach items="${resumeList}" var="resumeInfo" varStatus="resumeStatus">
			      			<div class="modal-resume">
					      		<form class="modal-form" action="/SubmittedResumeServ" method="post">
					      			<div>${resumeInfo.resumeName}</div>
									<input name="recIdx" value="${recIdx}" readonly="readonly" hidden="true">
									<input name="resumeInfoIdx" value="${resumeInfo.resumeInfoIdx}" readonly="readonly" hidden="true">
									<input name="mEmail" value="${mEmail}" readonly="readonly" hidden="true">
									<input name="mEmail" value="${mEmail}" readonly="readonly" hidden="true">
									<button class="btn" type="submit" name="operator" value="submit">이력서 제출</button>
					      		</form>
			      			</div>
			      		</c:forEach>
	      			</c:when>
	      		</c:choose>
	      </div>
	      <div class="modal-footer" style="border-top:none !important">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="SignUp.Modal.Close"/></button>
	      </div>
	    </div>
	  </div>
	</div>
</fmt:bundle>