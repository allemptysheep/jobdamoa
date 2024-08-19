<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="org.jsoup.Jsoup"%>
<%@ page import="org.jsoup.nodes.Document"%>
<%@ page import="org.jsoup.nodes.Element"%>
<%@ page import="org.jsoup.select.Elements"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
table {
	border-collapse: collapse;
	border: 1px solid #000;
	width: 80%;
	margin: 0 auto;
	text-align: center;
}

tr, td {
	border: 1px solid #000;
}

h3 {
	text-align: center;
}
</style>
<%
Class.forName("org.mariadb.jdbc.Driver");
String url = "jdbc:mariadb://localhost:3306/jobdamoa";
String user = "user";
String password = "1234";

LocalDateTime now = LocalDateTime.now();

%>
<body>
	<%
	String field = "";
	String field1 = "";
	String field2 = "";
	String search = "";
	
	if (request.getParameter("field") != null) {
		field = request.getParameter("field");
		search = request.getParameter("search");
	}
	String sql_count = "";

	if (!field1.equals("")) {
		sql_count = "select count(*) from gu where 1=1 and " + field + " like '%" + search + "%'";
	} else {
		sql_count = "select count(*) from gu";
	}
	
	Connection con_count = DriverManager.getConnection(url, user, password);
	Statement stmt_count = con_count.createStatement();
	ResultSet rs_count = stmt_count.executeQuery(sql_count);

	int total_count = 0;

	if (rs_count.next()) {
		total_count = rs_count.getInt(1);
	}
	/* 페이징 처리 */
	int page_now = 1;
	if(request.getParameter("page_now") != null){
		page_now = Integer.parseInt(request.getParameter("page_now"));
	}

	int num_per_page = 20; // 한 페이지당 출력 게시물 수
	int page_per_block = 3; // 한 블럭당 출력 링크 수
	int total_page = (int)Math.ceil(total_count / (double)num_per_page); //총 페이지 수 
	int first = num_per_page * (page_now-1); //limit 첫 항의 값

	
	
	%>
	<div class="container main">
	<br><br><br><br><br>
		<h3>JobKorea</h3>
			<table width="98%" border="0">
		<tr>
			<td align="left"><h5>
				<font color=black>전체 <%=total_count%> 건</font></h5></td>
			<td align="right">
				<form>
					<select name="field1">
						<option value="region_name" <%if(field.equals("region_name")){%> selected <%} %>>지역</option>
						<%
						try{
						String sql = "";
						Connection con_r = null;
						Statement stmt_r = null;
						ResultSet rs_r = null;

						String sql_r = "select * from region";
						

						con_r = DriverManager.getConnection(url, user, password);
						stmt_r = con_r.createStatement();
						rs_r = stmt_r.executeQuery(sql_r);
						
						while (rs_r.next()) {

							String idx = rs_r.getString("idx");
							String region_name = rs_r.getString("region_name");
							String region_code = rs_r.getString("region_code");
							String jobsite = rs_r.getString("job_site");
						%>
						<option value="<%=region_code%>"><%=region_name%></option>
						<%
							}
					
						} catch (SQLException e) {
							out.println("예외발생:" + e.getMessage());
							}
						%>
					</select>
					<select name="field2">
					<option value="gu_code>">구</option>
						<%
						try{
						String sql = "";
						Connection con_g = null;
						Statement stmt_g = null;
						ResultSet rs_g = null;

						String sql_g = "select * from gu g,region r where r.region_code=g.region_code";
						

						con_g = DriverManager.getConnection(url, user, password);
						stmt_g = con_g.createStatement();
						rs_g = stmt_g.executeQuery(sql_g);
						
						while (rs_g.next()) {

							String gu_name = rs_g.getString("gu_name");
							String gu_code = rs_g.getString("gu_code");
							String region_code = rs_g.getString("region_code");
							%>
							<%if(true){ %>
						<option value="<%=gu_code%>"><%=gu_name%></option>
						<%
							}
							}
					
						} catch (SQLException e) {
							out.println("예외발생:" + e.getMessage());
							}
						%>
					</select> 
				    <input name="search" type="text" <%if(search != null || search.equals("")){ %>value="<%=search%><%}%>">	
					<button class="btn btn-primary">검색</button>
				</form>
			</td>
		</tr>
	</table>
	<table>
	<%
	int num = total_count - ((page_now - 1 ) * num_per_page);
						
	ResultSet rs = null;
	Statement stmt = null;
	Connection con = null;

	try {
		String sql = "";

		if (!field.equals("")) {
			sql = "select * from region where " + field + " like '%" + search + "%' order by idx limit "+first+","+num_per_page;
		} else {
			sql = "select * from region order by idx limit "+first+","+num_per_page;
		}

		con = DriverManager.getConnection(url, user, password);
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {

			String idx = rs.getString("idx");
			String region_name = rs.getString("region_name");
			String region_code = rs.getString("region_code");
			String jobsite = rs.getString("job_site");
			
	%>
		<tr align="center">
			<td><%=idx %></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
<%
		}
		} catch (SQLException e) {
		out.println("예외발생:" + e.getMessage());
		} finally {
		if (rs != null)
		rs.close();
		if (stmt != null)
		stmt.close();
		if (con != null)
		con.close();
		}
		%>
	</table>	
		
</body>
</html>