<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<c:if test="${user.role eq 'ADMIN'}">
			<a class="btn btn-success mt-2 mb-5" href="/TourAgency/controller?action=tourForm">
				Add tour
			</a>
		</c:if>
		<div class="d-flex flex-wrap">
			<c:forEach items="${requestScope.tours}" var="tour">
				<t:tourCard
				id="${tour.id}"
				hotel="${tour.hotel.name}"
				country="${tour.transportTo.route.to.country}"
				city="${tour.transportTo.route.to.city}"
				transport="${tour.transportTo.type}"
				startDate="${tour.startDate}"
				endDate="${tour.endDate}"
				 />				
			</c:forEach>
		</div>
	</body>
</html>