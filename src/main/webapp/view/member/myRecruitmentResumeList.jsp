<%@page import="java.util.List"%>
<%@page import="recruitment.RecruitmentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/baseform.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
		String recIdx = (String)request.getParameter("recIdx");

		System.out.println(recIdx);
		RecruitmentDAO recruitmentDAO = new RecruitmentDAO(application);
		List<Object> list = recruitmentDAO.selectRecruitmentResumeList(recIdx);
		System.out.println(list);
		pageContext.setAttribute("list", list);
	%>
	<div class="container main">
		<div class="row">
			<c:choose>
					<c:when test="${list.size() eq 0}">
						<div>제출된 이력서가 없습니다.</div>
					</c:when>
					<c:when test="${list.size() ne 0}">
						<c:forEach items="${list}" var="data" varStatus="listStatus">
							<div class="row">${data.mEmail}</div>
						</c:forEach>
					</c:when>
			</c:choose>
		</div>
	</div>
</fmt:bundle>