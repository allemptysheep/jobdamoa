<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/baseform.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<div class="container main">
	
		<div class="row">
			<div class="col">
				<form action="/SaraminCrawlingServ" method="post">
					<button type="submit" class="btn btn-light" id="get_region_btn" name="operator" value="getRegion" >검색</button>
				</form>
			</div>
		</div>
		<%@ include file="/include/footer.jsp"%>
	</div>
</fmt:bundle>