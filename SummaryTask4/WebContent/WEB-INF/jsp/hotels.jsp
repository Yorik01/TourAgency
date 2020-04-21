<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
	<body>
		<c:if test="${user.role eq 'ADMIN'}">
		<div class="mt-2 mb-5">
			<a class="btn btn-success float-left" href="/TourAgency/controller?action=hotelForm">
				<fmt:message key="common.create" />
			</a>
			<div class="float-right">
				<form class="d-inlie-flex" action="/TourAgency/controller">
					<input type="hidden" name="action" value="allHotels" />
					<input type="text" name="keyword" value="${param.keyword}" />
					<button class="btn btn-primary">
						<fmt:message key="common.search" />
					</button>
				</form>
			</div>
		</div>
		</c:if>	
		<div class="d-flex flex-wrap">
			<c:forEach items="${requestScope.hotels}" var="hotel">
				<t:hotelCard hotel="${hotel}" />
			</c:forEach>
		</div>
	</body>
</html>