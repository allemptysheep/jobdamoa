<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@page import="recruitment.RecruitmentDAO"%>
<%@page import="java.util.List"%>
<%@page import="recruitment.RecruitmentDTO"%>

<link href="/css/baseform.scss" rel="stylesheet" type="text/css">
<link href="/css/recruitment/recruitment.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
		String mEmail = (String)session.getAttribute("mEmail");
	
		RecruitmentDAO recruitmentDAO = new RecruitmentDAO(application);
		int totalCount=recruitmentDAO.selectMyCount(mEmail);
					
		//전체 페이지 계산
		//한페이지당 보여주는 row 수
		int pageSize=10;
		//화면하단 페이지 수
		int blockPage=10;
		//전체 페이지수
		int totalPage=(int)Math.ceil((double)totalCount/pageSize);
		
		//현재 페이지 확인
		int pageNum=1;
		String pageTemp=request.getParameter("pageNum");
		
		if(pageTemp!=null && !pageTemp.equals("")){
			pageNum=Integer.parseInt(pageTemp);
		}
				
		//목록에 출력한 게시물 범위 계산
		int start=(pageNum-1)*pageSize;
		int end=pageSize;
	
		
		RecruitmentDTO recruitmentDTO = recruitmentDAO.getMyRecruitList(mEmail, start, end);
		
		List<Object> recruitmentList = recruitmentDTO.getRecruitmentData();
		
		pageContext.setAttribute("recruitList", recruitmentList);
		
		
		
		
	%>
	<script>
	function liClick (recIdx) {
		location.href="/view/member/myRecruitmentResumeList.jsp?recIdx="+recIdx;
	}
	</script>
	<div class="container main">
		<div class = "row d-flex recruit-list">
			<c:forEach items = "${recruitList}" var="recruit">
				<div class="row p-3">
					<div class = "col">
					
						<div class = "row">
						<ul class = "full-ul">
							<li class = "full-li" onclick="liClick(${recruit.get('rec_idx')})">
								<div class = "list-top">
									<c:out value = "${recruit.get('m_email')}"></c:out>
								</div>
								<div class = "list-middle">
									<div class = "in-list-top">
										<c:out value = "${recruit.get('rec_title')}"></c:out>
									</div>
									<div class = "in-list-middle">
										<c:out value = "${recruit.get('rec_title')}"></c:out>
									</div>
									<div class = "in-list-bottom">
										<c:out value = "${recruit.get('rec_title')}"></c:out>
									</div>
									
								</div>
								<div class = "list-bottom">
									<c:out value = "${recruit.get('rec_apply_startdate')}"></c:out>
									<a href = "/view/recruitment/recruitmentEdit.jsp?rec_idx=${recruit.get('rec_idx')}">수정</a>
								</div>
						  </li>
						</ul>
					
							
						</div>		
					</div>
				</div>	
			</c:forEach>
		</div>
		<div class = "row pagination-block">
		<nav aria-label="Page navigation">
		  <ul class="pagination justify-content-center">
		  		<%
					int pageCount=(((pageNum-1)/blockPage)*blockPage)+1;
				
					if(pageCount != 1){
				%>
		    <li class="page-item disabled">
		      <a class="page-link" href='list.jsp?pageNum=1'>[첫 페이지]</a>
		      <a class="page-link" href='list.jsp?pageNum=<%=pageCount-1%>'>[이전 블록]</a>
		    </li>
		    <%
				}
				
				int blockCount=1;
				while(blockCount<=blockPage && pageCount<=totalPage){
					//현재 페이지인 경우 처리
					if(pageCount==pageNum){
			%>
					<li class="page-item"><a class="page-link active-page" href = "#"><%=pageCount %></a></li>
				<%
					}else{
				%>		
						<li class="page-item"><a class="page-link" href='/view/member/myRecruitmentList.jsp?pageNum=<%=pageCount%>'><%=pageCount%></a></li>
				<%
					}
				
					pageCount++;
					blockCount++;
				}
				
				if(pageCount<=totalPage){
				%>
				<li class="page-item">
				<a href='list.jsp?pageNum=<%=pageCount+1%>'>[다음 블록]</a>
				<a href='list.jsp?pageNum=<%=totalPage%>'>[마지막 페이지]</a>
				</li>
				<% } %>
		  </ul>
		</nav>
		</div>
		
		<%@ include file="/include/footer.jsp"%>
	</div>
</fmt:bundle>