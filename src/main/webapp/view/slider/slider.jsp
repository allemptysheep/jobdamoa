<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="admin.SliderDTO"%>
<%@page import="admin.SliderDAO"%>
<link href="/css/slider/slider.scss" rel="stylesheet" type="text/css">
<%
String sliderName = request.getParameter("sliderName");
String innerClass = request.getParameter("innerClass");
SliderDAO sliderDAO = new SliderDAO(application);
SliderDTO sliderDTO = sliderDAO.selectSlider(sliderName);

List<Object> list = sliderDTO.getSliderData();
System.out.println(list);
pageContext.setAttribute("mainSliderList", list);
%>
<div id="carouselExampleIndicators" class="carousel slide"
	data-ride="carousel">
	<ol class="carousel-indicators">
		<c:forEach items="${mainSliderList}" var="slider" varStatus="status">
			<c:choose>
				<c:when test="${status.index eq 0}">
					<li data-target="#carouselExampleIndicators"
						data-slide-to="${status.index}" class="active"></li>
				</c:when>
				<c:when test="${status.index ne 0}">
					<li data-target="#carouselExampleIndicators"
						data-slide-to="${status.index}"></li>
				</c:when>
			</c:choose>
		</c:forEach>
	</ol>

	<div class="carousel-inner <%=innerClass%>">
		<c:forEach items="${mainSliderList}" var="slider" varStatus="status">
			<c:choose>
				<c:when test="${status.index eq 0}">
					<div class="carousel-item active">
						<a href="#">
							<img class="d-block w-100"
							src="/resources/slider-img/${slider.get('sliderFileO')}">
						</a>
					</div>
				</c:when>
				<c:when test="${status.index ne 0}">
					<div class="carousel-item">
						<a href="#">
							<img class="d-block w-100"
							src="/resources/slider-img/${slider.get('sliderFileO')}">
						</a>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>

	<a class="carousel-control-prev" href="#carouselExampleIndicators"
		role="button" data-slide="prev"> <span
		class="carousel-control-prev-icon" aria-hidden="true"></span> <span
		class="sr-only">Previous</span>
	</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
		role="button" data-slide="next"> <span
		class="carousel-control-next-icon" aria-hidden="true"></span> <span
		class="sr-only">Next</span>
	</a>
</div>