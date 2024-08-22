<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	<% // 다국어 fmt %>
<fmt:bundle basename="resource.language">
<%
Integer mLevel = (Integer) session.getAttribute("mLevel");
if (mLevel != 10) {
%>
<script>
	var needAdminMsg = '<fmt:message key="NeedAdmin"></fmt:message>';
	alert(needAdmin);
	location.href = "/member/index.jsp";
</script>
<%
}
%>
</fmt:bundle>