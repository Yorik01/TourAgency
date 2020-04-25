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
		<a class="btn btn-success mt-2 mb-5" href="/TourAgency/controller?action=routeForm">
			<fmt:message key="common.create" />
		</a>
	</c:if>
		<div class="d-flex flex-wrap">
			<c:forEach items="${requestScope.routes}" var="route">
 				<t:routeCard route="${route}" />
			</c:forEach>
		</div>
	</body>
</html>