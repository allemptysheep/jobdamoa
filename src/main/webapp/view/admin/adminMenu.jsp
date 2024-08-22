<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	<% // 다국어 fmt %>
<link href="/css/admin/adminmenu.scss" rel="stylesheet" type="text/css">
	<%
		String language = null;
		try {
			language = (String)session.getAttribute("sLocale");
		} catch(Exception e) {
			language = "ko_KR";
		}
		
		String pageName = request.getParameter("pageName");
		pageContext.setAttribute("pageName", pageName);
	%>
	<fmt:setLocale value="<%=language%>"/>
	<fmt:bundle basename="resource.language">
			<div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 280px;">
			    <ul class="nav nav-pills flex-column mb-auto">
			      <li class="nav-item">
			        <a href="/view/admin/adminPage.jsp" class="nav-link text-white ${pageName eq 'adminPage.jsp'?'active':''}" aria-current="page">
			          	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-fill" viewBox="0 0 16 16">
						  <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293z"/>
						  <path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293z"/>
						</svg>
			          	<fmt:message key="Admin.Page" />
			        </a>
			      </li>
			      <li>
			        <a href="/view/admin/regionSetting.jsp" class="nav-link text-white ${pageName eq 'regionSetting.jsp'?'active':''}">
				        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-easel" viewBox="0 0 16 16">
						  <path d="M8.5 5a.5.5 0 1 0-1 0h-2A1.5 1.5 0 0 0 4 6.5v2A1.5 1.5 0 0 0 5.5 10h.473l-.447 1.342a.5.5 0 1 0 .948.316L7.027 10H7.5v1a.5.5 0 0 0 1 0v-1h.473l.553 1.658a.5.5 0 1 0 .948-.316L10.027 10h.473A1.5 1.5 0 0 0 12 8.5v-2A1.5 1.5 0 0 0 10.5 5zM5 6.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5h-5a.5.5 0 0 1-.5-.5z"/>
						  <path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2zm10-1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1"/>
						</svg>
			          	<fmt:message key="Admin.Region" />
			        </a>
			      </li>
			      <li>
			        <a href="/view/admin/jobSetting.jsp" class="nav-link text-white ${pageName eq 'jobSetting.jsp'?'active':''}">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-list" viewBox="0 0 16 16">
						  <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2z"/>
						  <path d="M5 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 5 8m0-2.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5m0 5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5m-1-5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0M4 8a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0m0 2.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0"/>
						</svg>
			          	<fmt:message key="Admin.Job" />
			        </a>
			      </li>
			      <li>
			        <a href="/view/admin/log.jsp" class="nav-link text-white ${pageName eq 'log.jsp'?'active':''}">
			         	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
						  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
						</svg>
			         	<fmt:message key="Admin.Log" />
			        </a>
			      </li>
			      
			    </ul>
			    
			    <ul class="nav nav-pills flex-column mb">
			      <li>
			        <a href="/" class="nav-link text-white">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-left" viewBox="0 0 16 16">
							<path fill-rule="evenodd" d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0z"/>
							<path fill-rule="evenodd" d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708z"/>
						</svg>
			          	<fmt:message key="Admin.Exit" />
			        </a>
			      </li>
			      
					<li class="dropdown">
						<button class="btn btn-secondary dropdown-toggle language" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<fmt:message key="Language" />
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<form action="/SetLocaleAdmin" method="post">
								<div class="row language">
									<button class="btn language" type="submit" name="operator" value="ko_KR">한국어</button>
								</div>
								<div class="row language">
									<button class="btn language" type="submit" name="operator" value="en_US">English</button>
								</div>
							</form>
						</div>
					</li>
			    </ul>
			</div>

	</fmt:bundle>