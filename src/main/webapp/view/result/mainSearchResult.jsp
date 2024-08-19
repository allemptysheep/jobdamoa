<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="search.MainSearchDTO" %>
<%
	MainSearchDTO region_list = (MainSearchDTO)request.getAttribute("regionList");
	List<Object> list = region_list.getRegionData();
	int listSize = list.size();
	pageContext.setAttribute("MainSerachDTO", list);
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
						<c:forEach items = "${MainSerachDTO}" var = "region">
							<option value = "${region.get('regionName')}"><c:out value="${region.get('regionName')}"></c:out></option>
						</c:forEach>
					</select>
					<button type="submit" class="btn btn-light" id="search_btn" name="operator" value="search" >검색</button>
				</form>
			</div>
		</div>
		<div class="row">
				결과
		</div>
		<%@ include file="/include/footer.jsp"%>
	</div>
</fmt:bundle>