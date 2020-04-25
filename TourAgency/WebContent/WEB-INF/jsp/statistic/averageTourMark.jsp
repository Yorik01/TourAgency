<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="pageName" value="index" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>
<!DOCTYPE html>
<html>
	<body>
		<%@ include file="/WEB-INF/jspf/exportButtons.jspf" %>
		<table id="statistic-table" class="table table-bordered bg-white mr-3 mt-3">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Start date</th>
					<th scope="col">End date</th>
					<th scope="col">Hotel name</th>
					<th scope="col">Average mark</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${statistics}" var="statistic">
					<tr>
						<td>${statistic.tourId}</td>
						<td>${statistic.startDate}</td>
						<td>${statistic.endDate}</td>
						<td>${statistic.hotelName}</td>
						<td>${statistic.averageMark}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>		
	</body>
	<script src="js/jspdf.min.js"></script>
	<script src="js/jspdf.plugin.autotable.min.js"></script>
	<script src="js/tableHTMLExport.js"></script>
	<script src="js/reports.js"></script>
</html>