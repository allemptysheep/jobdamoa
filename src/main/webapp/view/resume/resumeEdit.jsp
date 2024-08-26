<%@page import="resume.ResumeDAO"%>
<%@page import="resume.ResumeDTO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.DBConnect" %>
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
<script>
</script>
	<%
		String resumeInfoIdx = request.getParameter("resumeInfoIdx");	// 받은 파라미터
		System.out.println("resumeInfoIdx : " + resumeInfoIdx);
		
		ResumeDAO resumeDAO = new ResumeDAO(application);
		ResumeDTO resumeData =  resumeDAO.selectResume(resumeInfoIdx);
		
		System.out.println("resumeData : " + resumeData);
		
		pageContext.setAttribute("resumeInfoIdx", resumeInfoIdx);
		pageContext.setAttribute("resumeData", resumeData);
	%>
<div class="container main">
	<h1>이력서 수정</h1>
<form action="/ResumeServ" method="post">
		<input type="hidden" name="resumeInfoIdx" value="${resumeInfoIdx}" readonly="readonly">
		<input type="hidden" name="resumeData" value="${resumeData}" readonly="readonly">
		<input type="hidden" name="resumeData" value="${resumeData.key}" readonly="readonly">
					<div class="row">	
						<div class="col-4">
							<h4>이력서이름</h4>
							<input name="name" type="text" class="form-control form-control-sm" placeholder="" value="${resumeData.name}">
						</div>
					</div>
					<h5>■ 기본사항</h5>
		<table border="1" width="80%">
			 <tr>
				<!--<th width="15%" rowspan="6"></th>-->
				<th width="15%" rowspan="2">성명</th>
				<td colspan="2">
				<div class="row">
					<div class="col-12">
						<input name="mname" type="text" class="form-control form-control-sm" placeholder="한글" value="${resumeData.mname}">
					</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><div class="row">
					<div class="col-12">
						<input name="ename" type="text" class="form-control form-control-sm" placeholder="영어" value="${resumeData.ename}">
					</div>
					</div></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><div class="row">
					<div class="col-12">
						<input name="birth" type="text" class="form-control form-control-sm" placeholder="EX) 1900-01-01" value="${resumeData.birth}">
					</div>
					</div></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><div class="row">
					<div class="col-12">
						<input name="phone" type="text" class="form-control form-control-sm" placeholder="EX) 010-1234-5678" value="${resumeData.phone}">
					</div>
					</div></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><div class="row">
					<div class="col-12">
						<input name="email" type="text" class="form-control form-control-sm"  placeholder="EX) example@exam.com" value="${resumeData.email}">
					</div>
					</div></td>
			</tr>
			<tr>
				<th rowspan="3">주소</th>
				<td>
					<div class="row">
					
						<div class="col-2">
							<input class="form-control text-center" type="text" id="sample4_postcode" placeholder="우편번호" name="zipCode" value="${resumeData.zipcode}">
						</div>
							<input class="btn btn-dark" type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
						</div>
					</td>
				</tr>
				<tr>
					<td>
					<div class="row">
						<div class="col-6">
							<input class="form-control text-center" type="text" id="sample4_roadAddress" placeholder="도로명주소" name="roadAdd" value="${resumeData.roadAdd}">
						</div>
						<div class="col-6">
							<input class="form-control text-center" type="text" id="sample4_jibunAddress" placeholder="지번주소" name="jibunAdd" value="${resumeData.jibunAdd}">
							<span id="guide" style="color:#999;display:none"></span>
						</div>
					</div>
					</td>
				</tr>
				<tr>
					<td>
					<div class="row">
						<div class="col-6">
							<input class="form-control text-center" type="text" id="sample4_detailAddress" placeholder="상세주소" name="detailAdd" value="${resumeData.detailAdd}">
						</div>
						<div class="col-6">
							<input class="form-control text-center" type="text" id="sample4_extraAddress" placeholder="참고항목" name="refAdd" value="${resumeData.refAdd}">
						</div>
					</div>
					</td>
				</tr>
				 <tr>
			<table border="1"' width="80%">
				<h5>■ 학력 및 경력사항</h5>
				<tr>
					<th width="2%" rowspan="3">학력사항</th>
					<th width="18%">학교명</th>
					<th width="14%">전공</th>
					<th>입학년도</th>
					<th>졸업년도</th>
					<th>졸업여부</th>
				</tr>
				<tr>
					<td><div class="row">
					<div class="col-12">
						<input name="eduName" type="text" class="form-control form-control-sm text-center"  placeholder="EX) @@고등학교" value="${resumeData.eduName}">
					</div>
					</div></td>
					<td><div class="row">
					<div class="col-12">
						<input name="eduClass" type="text" class="form-control form-control-sm text-center"  placeholder="EX) @@과" value="${resumeData.eduClass}">
					</div>
					</div></td>
					<td><div class="row">
					<div class="col-12">
						<input name="eduEnt" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 1900-3-1~" value="${resumeData.eduEnt}">
					</div>
					</div></td>
					<td><div class="row">
					<div class="col-12">
						<input name="eduGrad" type="text" class="form-control form-control-sm text-center"  placeholder="EX) ~1900-2-21" value="${resumeData.eduGrad}">
					</div>
					</div></td>
					<td><div class="row">
					<div class="col-12">
						<input name="eduGradEn" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 재학중/졸업/퇴학/휴학" value="${resumeData.eduGradEn}">
					</div>
					</div></td>					
				</tr>				
				<tr>
				<td colspan="5" align="right">
						<button type="button" class="btn btn-dark">추가</button>
				</td>
				</tr>
						<tr>
					<th rowspan="5" width="2%">경력사항</th>
					<th width="12%"  height="15">근무처</th>
					<th width="8%">직위</th>
					<th>담당업무</th>
					<th>입사년월</th>
					<th>퇴사년월</th>
				</tr>
				<tr>
					<td><div class="row">
					<div class="col-12">
						<input name="expLoc" type="text" class="form-control form-control-sm text-center"  placeholder="EX) @@테크" value="${resumeData.expLoc}">
					</div>
					</div></td>
					<td><div class="row">
					<div class="col-12">
						<input name="expRank" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 사원/대리..." value="${resumeData.expRank}">
					</div>
					</div></td>
					<td><div class="row">
					<div class="col-12">
						<input name="expResp" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 소프트웨어 개발" value="${resumeData.expResp}">
					</div>
					</div></td>
					<td><div class="row">
					<div class="col-12">
						<input name="expSWork" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 1900년-1-1 " value="${resumeData.expSWork}">
					</div>
					</div></td>
					<td><div class="row">
					<div class="col-12">
						<input name="expEWork" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 1900-1-1" value="${resumeData.expEWork}">
					</div>
					</div></td>
				</tr>
				<tr>
			<td colspan="5" align="right">
						<button type="button" class="btn btn-dark">추가</button>
				</td>
		</tr>			
			</table>
		
	</table>
	<table border="1" width="80%">
		<h5>■ 자격 및 특기사항</h5>
		<tr>
			<th width="20%" height="15">날짜</th>
			<th width="25%">자격증</th>
			<th width="20%">점수/급수</th>
			<th>발급기관</th>
		</tr>
		<tr>
			<td height="20"><div class="row">
					<div class="col-12">
						<input name="quaDate" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 1900-1-1" value="${resumeData.quaDate}">
					</div>
					</div></td>
			<td><div class="row">
					<div class="col-12">
						<input name="quaName" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 정보처리기사" value="${resumeData.quaName}">
					</div>
					</div></td>
			<td><div class="row">
					<div class="col-12">
						<input name="quaRank" type="text" class="form-control form-control-sm text-center"  placeholder="EX) @@점/@급" value="${resumeData.quaRank}">
					</div>
					</div></td>
			<td><div class="row">
					<div class="col-12">
						<input name="quaAppr" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 기관이름" value="${resumeData.quaAppr}">
					</div>
					</div></td>
		</tr>
		<tr>
			<td colspan="4" align="right">
						<button type="button" class="btn btn-dark">추가</button>
				</td>
		</tr>
	</table>
	<table border="1" width="80%">
		<h5>■ 근로조건</h5>
		<tr>
			<th width="15%">희망직종</th>
			<td><div class="row">
					<div class="col-12">
						<input name="hopeJob" type="text" class="form-control form-control-sm"  placeholder="EX) 프로그래머" value="${resumeData.hopeJob}">
					</div>
					</div></td>
			<th width="15%">희망급여</th>
			<td><div class="row">
					<div class="col-12">
						<input name="hopePay" type="text" class="form-control form-control-sm"  placeholder="EX) @@@만원" value="${resumeData.hopePay}">
					</div>
					</div></td>
		</tr>
		<tr>
			<th>희망근무지</th>
			<td><div class="row">
					<div class="col-12">
						<input name="hopeLoc" type="text" class="form-control form-control-sm"  placeholder="EX) 지역이름" value="${resumeData.hopeLoc}">
					</div>
					</div></td>
			<th>종전급여</th>
			<td><div class="row">
					<div class="col-12">
						<input name="hopePres" type="text" class="form-control form-control-sm"  placeholder="EX) @@@만원" value="${resumeData.hopePres}">
					</div>
					</div></td>
		</tr>
	</table>
	<table border="1" width="80%">
		<h5>■ 대외활동</h5>
		<tr>
			<th width="20%" height="15">기간</th>
			<th width="25%">활동내용</th>
			<th width="20%">발급기관</th>
			<th>비고</th>
		</tr>
		<tr><div class="row">
					
		
			<td height="20"><div class="row">
					<div class="col-12">
						<input name="extDate" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 1900-1-1" value="${resumeData.extDate}">					
					</div>
					</div></td>
			<td><div class="row">
					<div class="col-12">
						<input name="extContent" type="text" class="form-control form-control-sm text-center"  placeholder="" value="${resumeData.extContent}" >
					</div>
					</div></td>
			<td><div class="row">
					<div class="col-12">
						<input name="extName" type="text" class="form-control form-control-sm text-center"  placeholder="EX) 기관이름" value="${resumeData.extName}" >
					</div>
					</div></td>
			<td><div class="row">
					<div class="col-12">
						<input name="extEct" type="text" class="form-control form-control-sm text-center"  placeholder="" value="${resumeData.extEct}">
					</div>
					</div></td>
		</tr>
		<tr>
			<td colspan="4" align="right">
						<button type="button" class="btn btn-dark">추가</button>
				</td>
		</tr> 
	</table>
	<div class="row">
		<div class="col offset-md-5">
			<button type="submit" class="btn btn-dark" name="operator" value="edit">수정완료</button>
		</div>
	</div>
	</form>
	</div>
</fmt:bundle>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>