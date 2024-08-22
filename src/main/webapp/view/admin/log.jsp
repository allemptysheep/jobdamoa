<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/adminHeader.jsp"%>
<%@include file="/view/member/is_signIn.jsp"%>
<%@include file="/view/admin/is_admin.jsp"%>
<link href="/css/admin/adminlog.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
		int first = 0;
		int last = 10;
	%>
	<%
		String pageName = request.getRequestURI().toString();
		String[] pageNameList = pageName.split("/");
		pageName = pageNameList[pageNameList.length -1].toString();
	
		pageContext.setAttribute("pageName", pageName);
	%>
	<div class="body-main">
		<div class="container admin-main">
			<div class="row admin-main">
				<div class="col-2 admin-main">
					<jsp:include page="/view/admin/adminMenu.jsp" flush="false">
						<jsp:param value="${pageName}" name="pageName"/>
					</jsp:include>
				</div>
				<div class="col-10 admin-main">
					<div class="row admin-main-row">
						<form action="/LogServ" method="post" onsubmit="return agoLog()">
							<div class="row">
								<div class="col-9">
									<input class="form-control" name="searchWord" id="searchWord" placeholder="키워드">
									<input class="form-control" name="memberEmail" id="memberEmail" placeholder="이메일">
									<input hidden="true" name="first" value="0">
									<input hidden="true" name="last" value="10">
								</div>
								<div class="col-3">
									<button class="btn search" type="submit" name="operator" value="getLog"><fmt:message key="Admin.Log.Search" /></button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		function agoLog() {
			var sWord = $("#searchWord").val();
			
			console.log(sWord);
			if (sWord == "" || sWord == null){
				alert("검색어를 입력 해 주세요.");
				return false;
			} else {
				
			}
		}
	</script>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>