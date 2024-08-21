<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/adminHeader.jsp"%>
<%@include file="/view/member/is_signIn.jsp"%>
<%@include file="/view/admin/is_admin.jsp"%>
<link href="/css/admin/adminlog.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
	
			String searchWord = (String)request.getParameter("searchWord");
			String memberEmail = (String)request.getParameter("memberEmail");

			
	// paging
			int page_now = Integer.parseInt(request.getAttribute("page_now").toString());
			int pageSize = Integer.parseInt(request.getAttribute("pageSize").toString());
			int blockSize = Integer.parseInt(request.getAttribute("blockSize").toString());
			int totalCount = Integer.parseInt(request.getAttribute("totalCount").toString());
			int totalPage = Integer.parseInt(request.getAttribute("totalPage").toString());
			int first = Integer.parseInt(request.getAttribute("first").toString());
			int last = Integer.parseInt(request.getAttribute("last").toString());
	%>
	<div class="body-main">
		<div class="container admin-main">
			<div class="row admin-main">
				<div class="col-2 admin-main">
					<jsp:include page="/view/admin/adminMenu.jsp"></jsp:include>
				</div>
				<div class="col-10 admin-main">
					<div class="row admin-main-row">
						<form action="/LogServ" method="post" onsubmit="return agoLog()">
							<div class="row">
								<div class="col-9">
									<input class="form-control" name="searchWord" id="searchWord"
										value="${searchWord}" placeholder="키워드"> 
									<input
										class="form-control" name="memberEmail" id="memberEmail"
										value="${memberEmail}" placeholder="이메일">
								</div>
								<div class="col-3">
									<button class="btn search" type="submit" name="operator"
										value="getLog">
										<fmt:message key="Admin.Log.Search" />
									</button>
								</div>
							</div>
						</form>
						<hr />
						<div class="row result">
							<div class="row">
								<div class="col-2">
									<fmt:message key="Admin.Log.Idx" />
								</div>
								<div class="col-2">
									<fmt:message key="Admin.Log.IP" />
								</div>
								<div class="col-2">
									<fmt:message key="Admin.Log.Locale" />
								</div>
								<div class="col-2">
									<fmt:message key="Admin.Log.Key" />
								</div>
								<div class="col-2">
									<fmt:message key="Admin.Log.Date" />
								</div>
								<div class="col-2">
									<fmt:message key="Admin.Log.ID" />
								</div>
							</div>
							<hr />
							<c:forEach items="${logDTO}" var="log" varStatus="status">
								<div class="row">
									<div class="col-2">${log.get('logIdx')}</div>
									<div class="col-2">${log.get('logIp')}</div>
									<div class="col-2">${log.get('logLocale')}</div>
									<div class="col-2">${log.get('logText')}</div>
									<div class="col-2">${log.get('logDatetime')}</div>
									<div class="col-2">${log.get('memberEmail')}</div>
								</div>
							</c:forEach>
						</div>

						<div class="list-write-box">
							<div class="list-page-box">
								<%
							
									// 페이징 처리 시작 /////////////////////////////
									
									int total_block = 0;
									
									int block = 0;
									
									int first_page = 0;
									
									int last_page = 0;
									
									int direct_page = 0;
									
									int my_page = 0;
									
									
									total_block = (int)Math.ceil(totalPage / (double)blockSize); //총 블럭 수
									
									block = (int)Math.ceil(page_now / (double)blockSize); //현재 블럭
									
									first_page = (block - 1) * blockSize; //블럭내 시작하는 수
									
									last_page = block * blockSize; //블럭내 끝나는 수
									
									
									
									if(total_block <= block) { //마지막 블럭일 때
									
										last_page = totalPage; //반복문 변수 처리
									
									}
									
									
									
									
									
									// 이전 블럭 처리			
									
									if(block != 1){ //첫 블럭이 아니라면
									
									%>
									<form action="/LogServ" method="post" onsubmit="return agoLog()">
										<input class="form-control" name="searchWord" id="searchWord"
											value="${searchWord}" placeholder="키워드" hidden="true"> 
										<input
											class="form-control" name="memberEmail" id="memberEmail"
											value="${memberEmail}" placeholder="이메일" hidden="true">
										<input
											class="form-control" name="page_now" id="page_now"
											value="<%=first_page%>" placeholder="페이지" hidden="true">
										<button class="btn search" type="submit" name="operator"
											value="getLog">
											<a class="indicator-btn"><img src="/img/btn_left.gif"></a>
										</button>
									</form>
								<%
									
									}
									
									
									
									// 블럭내 페이지 수 출력 
									
									for(direct_page = first_page + 1; direct_page <= last_page; direct_page++){
									
										if(page_now == direct_page){
									
									%>

								<span class="page_on"><%=direct_page%></span>&nbsp;

								<%
									
										}else{
									
									%>

								<span class="page_off">
									<form action="/LogServ" method="post" onsubmit="return agoLog()">
										<input class="form-control" name="searchWord" id="searchWord"
											value="${searchWord}" placeholder="키워드" hidden="true"> 
										<input
											class="form-control" name="memberEmail" id="memberEmail"
											value="${memberEmail}" placeholder="이메일" hidden="true">
										<input
											class="form-control" name="page_now" id="page_now"
											value="<%=direct_page%>" placeholder="페이지" hidden="true">
										<button class="btn search" type="submit" name="operator"
											value="getLog">
											<%=direct_page%>
										</button>
									</form>
								</span>&nbsp;

								<%
									
										}
									
									}
									
										
									
									// 다음 블럭 처리
									
									if(block < total_block) { //다음 블럭 존재
									
								%>

								&nbsp;
									<form action="/LogServ" method="post" onsubmit="return agoLog()">
										<input class="form-control" name="searchWord" id="searchWord"
											value="${searchWord}" placeholder="키워드" hidden="true"> 
										<input
											class="form-control" name="memberEmail" id="memberEmail"
											value="${memberEmail}" placeholder="이메일" hidden="true">
										<input
											class="form-control" name="page_now" id="page_now"
											value="<%=last_page + 1%>" placeholder="페이지" hidden="true">
										<button class="btn search" type="submit" name="operator"
											value="getLog">
											<a class="indicator-btn"><img src="/img/btn_right.gif"></a>
										</button>
									</form>
								<%
									
									}
									
									//페이징 처리 끝 /////////////////////////////			
									
									%>
							</div>
							<div></div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		function agoLog() {
			var sWord = $("#searchWord").val();

			console.log(sWord);
			if (sWord == "" || sWord == null) {
				alert("검색어를 입력 해 주세요.");
				return false;
			} else {

			}
		}
	</script>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>