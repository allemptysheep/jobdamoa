<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ page import="java.util.List" %>
<%
	
%>

<link href="/css/index.scss" rel="stylesheet" type="text/css">

<fmt:bundle basename="resource.language">
	<div class="container main">
		<div class="row">
			<div class="col">
				<form action="/mainSearchServ" method="post">
					<input class="form-control" type="text" id="keysword" name="keyword">
					<select class="form-control" id="region" name="region">
						<option value="">지역선택</option>
					</select>
					<button type="submit" class="btn btn-light" id="search_btn" name="operator" value="search" >검색</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col">
				결과
			</div>
		</div>
		<%@ include file="/include/footer.jsp"%>
	</div>
</fmt:bundle>