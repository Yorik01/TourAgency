<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
	<body>
	<a class="btn btn-success mt-2 mb-5" href="/TourAgency/controller?action=routeForm">
			Add route
	</a>
		<div class="d-flex flex-wrap">
			<c:forEach items="${requestScope.routes}" var="route">
				<t:routeCard 
				countryFrom="${route.from.country}"
				cityFrom="${route.from.city}"
				countryTo="${route.to.country}"
				cityTo="${route.to.city}"
				id="${route.id}" />
			</c:forEach>
		</div>
	</body>
</html>