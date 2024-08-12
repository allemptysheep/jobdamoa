<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@page import="java.sql.Statement"%>

<%@page import="java.sql.DriverManager"%>

<%@page import="java.sql.Connection"%>

<%@page import="java.sql.ResultSet"%>

<%@page import="java.text.SimpleDateFormat"%>


<%
	// 다른 페이지에서 사용 법 : <%@ include file="/include/dbconnection.jsp" % > 
%>

<%

//데이터베이스 접속	

Class.forName("com.mysql.cj.jdbc.Driver");
// Class.forName("org.mariadb.jdbc.Driver");

String url="jdbc:mysql://localhost:3336/"; //데이타베이스명 : web

// String url="jdbc:mariadb://localhost:3306/mdwdb"; 데이타베이스명 : mdwdb (mangdew web database)

String user="user"; //관리자 아이디

String password="1234"; //관리자 비밀번호


%>