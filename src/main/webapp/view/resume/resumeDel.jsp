<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<link href="/css/baseform.scss" rel="stylesheet" type="text/css">
<fmt:bundle basename="resource.language">
	<%
	
	// jdk 21 tomcat 9
	//
	// include 폴더는 header, footer 와 같이 사이트에 중복적으로 포함되는 페이지를 포함
	// 일반적인 jsp 페이지는 view 폴더 안에 새로운(기능의 이름 지정) 폴더를 생성하여 기재
	// img 폴더 안에는 사이의 정적인 기본 이미지(로고, 유저 기본 이미지)등이 포함된다.
	// 사용자가 업로드 혹은 관리자가 업로드 하는 이미지는 resources 폴더 아래에 포함된다.
	// xml 파일 수정 혹은 fmt message 수정이 필요할 경우 직접 수정하지 말고 수정 요청.
	
	//
	// 패키지 명은 소문자로 한 단어로 작성
	// 표준 패턴을 따른다. Ex) [com].[Company].[Project].[toppackage].[lowerpackage]
	// 좋은 예: com.jobdamoa.saramin.search.object
	// 나쁜 예:  com.jobdamoa.saraminSearchObject
	
	//
	// JSP 파일 제목 camelCase 기법으로 작성 ex) loginForm, memberEdit, memberFunctionTest
	// scss 파일 제목 소문자 고정 아무 기법 적용 하지 안고 작성 ex) testmemberinfo.scss
	// JAVA 파일 제목 PascalCase 기법으로 작성 ex) LoginServ, MemberEditServ, MemberFunctionTestServ
	// DAO, DTO 파일 제목 양식 ex) MemberDTO, MemberDAO
	// Servlet 파일 뒤 Serv 표기 ex) MemberServ, SetLocaleServ
	// 폴더 제목 양식 ex) main-img, test-img

	// html class 	saramin-btn
	//		id		saramin_btn
	//		name	saraminBtn

	//
	// 데이터 베이스
	// DB name : jobdamoa
	// DB user : user
	// DB pass : 1234
	// table 이름 작성 법 : snake_case (맨 앞 문자도 소문자)
	// column 이름 작성 법 : snake_case (맨 앞 문자도 소문자)
	// 인덱스 표기 idx
	// 기본적으로 외래키 사용 없음.

	// 
	// 작업 전 항상 깃에서 코드 다운.
	// 커밋 시 작업 표기 
	// [Add] 추가 작업 내용.(한글 영문 표기 상관없음)
	// [Fix] 수정 작업 내용.(한글 영문 표기 상관없음)
	// fmt Message 사용법 : <fmt:message key="Write" />
	// 스크립틀릿 데이터 html 에서 사용 : pageContext.setAttribute("planDTO", planDTO.getPlanData());
	
	
	// 새로운 작업 시 baseForm 내역 붙여넣어서 사용
	// container main 클레스는 헤더에 지정. 건들지 않는다.
	
	%>
	<div class="container main">
	<script>
		function deleteP(){
			confirm("삭제하시겠습니까?]");
			if(true){
				alert("삭제되었습니다.");
			location.href="/view/resume/resumeList.jsp";
			}else{
				history.back();
			}
		}
	
	</script>
	</div>
</fmt:bundle>