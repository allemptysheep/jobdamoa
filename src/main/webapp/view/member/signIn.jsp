<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/member/signin.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<div class="body-main">
		<div class="login-main">
			<div class="login-title-box">
				<div class="login-title">
					<fmt:message key="SignIn" />
				</div>
			</div>
			<div class="login-box">
				<form action="/MemberSigninServ" method="post">
				
					<div class="login-data-box">
						<div class="email-box">
							<input type="text" class="mb_email" name="member_email" placeholder="<fmt:message key="SignIn.Email" />" required />
						</div>
						
						<div class="password-box">
							<input type="password" class="mb_password" name="member_password" placeholder="<fmt:message key="SignIn.Password" />" required />
						</div>
					</div>

					<div class="login-btn-box">
						<button type="submit" class="btn btn-light">
							<fmt:message key="SignIn.Button" />
						</button>
					</div>

				</form>
			</div>
			<div class="signup-btn-box">
				<div class="signup-btn" >
					<button type="button" class="btn btn-light" onclick="location.href='/view/member/signUp.jsp'">
						<fmt:message key="SignIn.SignUp" />
					</button>
					<button type="button" class="btn btn-light" onclick="location.href='/view/member/findMyInfo.jsp'">
						<fmt:message key="SignIn.Find" />
					</button>
				</div>
			</div>
			
		</div>
	</div>
</fmt:bundle>
<%@ include file="/include/footer.jsp"%>

