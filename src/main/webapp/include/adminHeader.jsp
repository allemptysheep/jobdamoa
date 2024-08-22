<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Locale" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	<% // 다국어 fmt %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="/css/include/adminheader.scss" rel="stylesheet" type="text/css">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Rubik+Mono+One&display=swap" rel="stylesheet">
	
	<title>JSP</title>
	<script
		src="https://code.jquery.com/jquery-3.7.1.min.js"
	 	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	
	<script src="https://js.tosspayments.com/v2/standard"></script>
</head>

<body>
	<fmt:requestEncoding value = "UTF-8" />
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
	
	<%
		String mEmail = (String) session.getAttribute("mEmail");
		String mNickName = (String) session.getAttribute("mNickName");
		Integer mLevel = (Integer) session.getAttribute("mLevel");
	%>
	
	<% 
	/**
		<div class="test-session">
		test: <fmt:message key="test"></fmt:message><br>
		locale : <br>
		session_id : <br> 
		session_name : <br> 
		session_level : ><br>
		</div>
	**/ 
	%>


	<% // 어드민 모바일 메뉴 %>
	<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light" id="header">
		<div class="container-fluid">
			<jsp:include page="/view/admin/adminMobileMenu.jsp" />
		</div>
	</nav>
	</fmt:bundle>