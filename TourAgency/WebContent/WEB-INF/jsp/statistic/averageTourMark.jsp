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
		<table class="table bg-white table-bordered card mr-3 mt-3">
			<thead>
				<tr>
					<th scope="col">Tour</th>
					<th scope="col">Average mark</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${statistics}" var="statistic">
					<tr>
						<th>${statistic.hotelName}</th>
						<th>${statistic.averageMark}</th>
					</tr>
				</c:forEach>
			</tbody>
		</table>		
	</body>
</html>