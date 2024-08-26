<%@page import="selfIntro.SelfIntroDTO"%>
<%@page import="selfIntro.SelfIntroDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@include file="/view/member/is_signIn.jsp"%>
<%@ page import="member.MemberDAO" %>
<link href="/css/member/signup.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
<style>
	table{
		margin: 2rem;
		padding:0;
		border: 1px solid black; 
	}
	th{	
		text-align: center;
	}
	
	h1{
				text-align: center;
		
	}

</style>	
<%
		String selfIntroIdx = request.getParameter("selfIntroIdx");	// 받은 파라미터
		System.out.println("selfIntroIdx : " + selfIntroIdx);
		
		SelfIntroDAO  selfIntroDAO = new SelfIntroDAO(application);
		SelfIntroDTO selfintroData = selfIntroDAO.selectselfIntro(selfIntroIdx);

		pageContext.setAttribute("selfIntroIdx", selfIntroIdx);
		pageContext.setAttribute("selfintroData", selfintroData);
	%>
<div class="container main">

	<h1>자기소개서</h1>
<form action="/selfIntroServ" method="post" >
		<input name="selfIntroIdx" value="${selfIntroIdx}" readonly="readonly" hidden>

					<div class="row">	
						<div class="col-4">
							<h4>자기소개서 이름</h4>
							<input name="sname" type="text" class="form-control form-control-sm" value="${selfintroData.sname}" readonly="readonly">
						</div>
					</div>
					<h5>■ 당사에 지원한 동기와 입사후의 포부를 기술하여 주십시오.</h5>
				<table border="1" width="80%">
			 <tr>
				<!--<th width="15%" rowspan="6"></th>-->
				<td colspan="2">
				<div class="row">
					<div class="col-12">
						<textarea name="squestion" class="form-control" rows="3" cols="25" readonly="readonly">${selfintroData.squestion1}</textarea>
					</div>
					</div>
				</td>
			</tr>
			</table>	
			<h5>■ 본인이 지원하는 직무에 있어 다른 지원자와는 차별화된 역략 및 그와 관련된 경험 혹은 경력을 기술하여 주십시오.</h5>
				<table border="1" width="80%">
			 <tr>
				<!--<th width="15%" rowspan="6"></th>-->
				<td colspan="2">
				<div class="row">
					<div class="col-12">
						<textarea name="squestion2" class="form-control" rows="3" cols="25" readonly="readonly">${selfintroData.squestion2}</textarea>
					</div>
					</div>
				</td>
			</tr>
			</table>	
			<h5>■ 기존과 다른 새로운 변화를 시도했던 경험과 그를 통해 배운 점이 무엇인지 기술하여 주십시오.</h5>
				<table border="1" width="80%">
			 <tr>
				<!--<th width="15%" rowspan="6"></th>-->
				<td colspan="2">
				<div class="row">
					<div class="col-12">
						<textarea name="squestion3" class="form-control" rows="3" cols="25" readonly="readonly">${selfintroData.squestion3}</textarea>
					</div>
					</div>
				</td>
			</tr>
			</table>	
			<div class="row">
		<div class="col offset-md-5">
			<button type="button" class="btn btn-dark" onclick="history.back()">목록으로</button>
		</div>
	</div> 	
	</form>
	</div>
</fmt:bundle>