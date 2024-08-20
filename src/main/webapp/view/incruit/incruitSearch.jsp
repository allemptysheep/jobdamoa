<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/baseform.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<div class="container main">
		<div class="row">
			<div class="col">
			<select>
			<option value = "">지역선택 - 대분류</option>
			</select>
			<select>
			<option value = "">지역선택 - (구)</option>
			</select>
				<form action="/IncruitControllerServ" method="post">
					<input class="form-control" type="text" id="keysword" name="keyword">
					<button type="submit" class="btn btn-light" id="get_incruit_btn" name="operator" value="getIncruitTop20" >검색</button>
				</form>
			</div>
		</div>
		<%@ include file="/include/footer.jsp"%>
	</div>
</fmt:bundle>