<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="member.MemberDTO"%>
<%@ page import="member.MemberDAO"%>
<%@ page import="member.Encrypt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	<% // 다국어 fmt %>
<fmt:bundle basename="resource.language">
	
	<script>
	var editErrorMsg = '<fmt:message key="SignIn.Fail"></fmt:message>';
	alert(editErrorMsg);
	history.back();
	</script>

</fmt:bundle>