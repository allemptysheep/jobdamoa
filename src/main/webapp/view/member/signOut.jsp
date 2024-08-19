<%@page import="log.LogDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
LogDAO insertLog = new LogDAO(application);
insertLog.insertLog(request, session, "signOut success");
session.invalidate(); //세션 종료
%>

<script type="text/javascript">
	location.href = "/";
</script>