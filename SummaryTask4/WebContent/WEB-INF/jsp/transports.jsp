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
			<a class="btn btn-success mt-2 mb-5" href="/TourAgency/controller?action=transportForm">
				<fmt:message key="common.create" />
			</a>
		</c:if>
		<div class="d-flex flex-wrap">
			<c:forEach items="${requestScope.transports}" var="transport">
`				<t:transportCard transport="${transport}" />
			</c:forEach>
		</div>
	</body>
</html>