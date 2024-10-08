<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<link href="/css/index.scss" rel="stylesheet" type="text/css">
<link href="/css/include/footer.scss" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/2.9.7/jquery.fullpage.css" integrity="sha512-/AilQf/shuEGfh8c3DoIqcIqHZCKpiImSyt+fxIKJphHiNa6QMPb6AbDly6rkjmGr/5OZd35JtvVkbEKnCZO+A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/2.9.7/jquery.fullpage.extensions.min.js" integrity="sha512-0xW5KFMtT462F65vVvIejNawM1U2Fp/yhItmQVFUwdYP+R9tQFCn4QtLEhkp6RoGovZ6YCvbxy+GVCtGv0+nvA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/2.9.7/jquery.fullpage.js" integrity="sha512-XOpdFagEEv9XMxN/2EdvFL/PfGtFDjX+AkRznsFHDtdC05YWdkMnz5JZUfrbnEabOQaMc7YNTl2xPbOu3J858Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
		$(document).ready(function() {
			$('#fullpage').fullpage({
				anchors:['firstPage', 'secondPage', 'thirdPage', 'footer'],
				scrollOverflow: true,
				scrollOverflowMacStyle: false,
				scrollOverflowReset: false,
			});
		});
	</script>
	<%
		//<fmt:setLocale value="en_us"/>
		String mEmail = (String)session.getAttribute("mEmail");
		String mName = (String) session.getAttribute("mName");
		String mPhoneNum = (String) session.getAttribute("mPhoneNum");
		String mNickName = (String)session.getAttribute("mNickName");
		Integer mLevel = (Integer) session.getAttribute("mLevel");				
	%>
<fmt:bundle basename="resource.language">
	<div class="container main" id="fullpage">
		<div class="section main-slider" data-anchor="firstPage">
			<div class="row">
				<div class="col"> 
					<form action="/mainSearchServ" method="post">
						<input class="form-control" type="text" id="keysword" name="keyword">
						<select class="form-control" id="region" name="region">
							<option value="">지역선택</option>
						</select>
						<button type="submit" class="btn btn-light" id="search_btn" name="operator" value="search" >검색</button>
					</form>
				</div>
			</div>
		</div>

		<div class="section main-container" data-anchor="secondPage">
			<img src="/img/1.jpg" class="d-inline-block align-top" alt="">
		</div>
		<div class="section main-container" data-anchor="thirdPage">
			<img src="/img/2.jpg" class="d-inline-block align-top" alt="">
		</div>
			
		<%@ include file="/include/footer.jsp" %>
	</div>
</fmt:bundle>