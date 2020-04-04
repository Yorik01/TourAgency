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
			<a class="btn btn-success mt-2 mb-5" href="/TourAgency/controller?action=hotelForm">
				Add hotel
			</a>
		</c:if>	
		<div class="d-flex flex-wrap">
			<c:forEach items="${requestScope.hotels}" var="hotel">
				<t:hotelCard 
					id="${hotel.id}"
					name="${hotel.name}"
					address="${hotel.address}"
					stars="${hotel.stars}" />
			</c:forEach>
		</div>
	</body>
</html>