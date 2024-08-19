<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	<% // 다국어 fmt %>
<fmt:bundle basename="resource.language">
<%
String mEmail = (String)session.getAttribute("mEmail");
if (mEmail == null) {
%>
<script>
	var needLoginMsg = '<fmt:message key="NeedSignIn"></fmt:message>';
	alert(needLoginMsg);
	location.href = "/member/signIn.jsp";
</script>
<%
}
%>
</fmt:bundle>