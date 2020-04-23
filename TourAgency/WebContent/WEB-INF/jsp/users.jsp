<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
		<div class="d-flex flex-wrap">
			<c:forEach items="${requestScope.users}" var="user">
				<t:userInfo
					user="${user}"
				 />
			</c:forEach>
		</div>
	</body>
</html>