<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@page import="recruitment.RecruitmentDAO"%>
<%@page import="java.util.List"%>
<%@page import="recruitment.RecruitmentDTO"%>

<link href="/css/baseform.scss" rel="stylesheet" type="text/css">
<link href="/css/recruitment/recruitment.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
		RecruitmentDAO recruitmentDAO = new RecruitmentDAO(application);
		RecruitmentDTO recruitmentDTO = recruitmentDAO.getRecruitList();
		
		List<Object> recruitmentList = recruitmentDTO.getRecruitmentData();
		
		pageContext.setAttribute("recruitList", recruitmentList);
	%>
	<div class="container main">
		<div class = "row d-flex recruit-list">
			<c:forEach items = "${recruitList}" var="recruit">
				<div class="row p-3">
					<div class = "col">
					
						<div class = "row">
						<ul class = "full-ul">
							<li class = "full-li">
								<div class = "list-top">
									<c:out value = "${recruit.get('m_email')}"></c:out>
								</div>
								<div class = "list-middle">
									<div class = "in-list-top">
										<c:out value = "${recruit.get('rec_title')}"></c:out>
									</div>
									<div class = "in-list-middle">
										<c:out value = "${recruit.get('rec_title')}"></c:out>
									</div>
									<div class = "in-list-bottom">
										<c:out value = "${recruit.get('rec_title')}"></c:out>
									</div>
									
								</div>
								<div class = "list-bottom">
									<c:out value = "${recruit.get('rec_apply_startdate')}"></c:out>
								</div>
						  </li>
						</ul>
					
							
						</div>		
					</div>
				</div>	
			</c:forEach>
		</div>
		
		
		<%@ include file="/include/footer.jsp"%>
	</div>
</fmt:bundle>