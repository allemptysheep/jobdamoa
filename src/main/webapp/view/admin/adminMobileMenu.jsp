<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	<% // 다국어 fmt %>
	<%
		String language = null;
		try {
			language = (String)session.getAttribute("sLocale");
		} catch(Exception e) {
			language = "ko_KR";
		}
	%>
<fmt:setLocale value="<%=language%>"/>
<fmt:bundle basename="resource.language">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link header" href="/admin/adminPage.jsp">
				<fmt:message key="Admin.Page" />
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link header" href="/admin/adminSlider.jsp">
				<fmt:message key="Admin.Slider" />
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link header" href="/admin/planList.jsp">
				<fmt:message key="Admin.Plan.List" />
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link header" href="/admin/log.jsp">
				<fmt:message key="Admin.Log" />
				</a>
			</li>
			
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