<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<div class="d-flex fex-column">
		<c:forEach items="${reservations}" var="reservation">
			<t:reservationCard
			reservation="${reservation}"
			 />
		</c:forEach>
	</div>
</body>
</html>