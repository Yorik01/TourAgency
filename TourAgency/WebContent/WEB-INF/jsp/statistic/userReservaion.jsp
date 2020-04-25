<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<!DOCTYPE html>
<html>
		<body>
		<%@ include file="/WEB-INF/jspf/exportButtons.jspf" %>
		<table id="statistic-table" class="table table-bordered bg-white mr-3 mt-3">
			<thead>
				<tr>
					<th scope="col">Email</th>
					<th scope="col">Revenue</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${statistics}" var="statistic">
					<tr>
						<td>${statistic.email}</td>
						<td>${statistic.reservationsCount}</td>
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