<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div class="d-flex flex-column">
			<c:forEach items="${requestScope.transports}" var="transport">
				<t:transportCard 
					id="${transport.id}"
					code="${transport.code}"
					takeoff="${transport.takeoff}"
					arrive="${transport.arrive}"
					type="${transport.type}"
					route="${transport.route}" />
			</c:forEach>
		</div>
	</body>
</html>