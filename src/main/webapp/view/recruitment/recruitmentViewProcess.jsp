<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<fmt:bundle basename="resource.language">
	<%
	// write_prop1 : '<fmt:message key="Write.prop1" />'
	//<fmt:message key="Login" />
	%>
			<% 
				String rs;
				try{
					rs = (request.getAttribute("rs").toString());
				} catch(Exception e) {
					rs = "null";
				}
				
				String recIdx = request.getParameter("recIdx");
				pageContext.setAttribute("recIdx", recIdx);
			%>
			
			<% if( rs == "null" || rs == "0") { %>
				System.out.println("0000");
				<script>
					alert("이력서 제출 실패.");
					history.back();
				</script>	
			<% } else if(rs == "1") { %>
				<script>
					alert("이력서 제출  완료.");
					location.href="/view/recruitment/recruitmentView.jsp?recIdx=" + ${recIdx};
				</script>	
			<% } %>
	<div class="body-main"></div>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>