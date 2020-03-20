<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="hotel" required="true" type="java.lang.String"%>
<%@ attribute name="country" required="true" type="java.lang.String"%>
<%@ attribute name="city" required="true" type="java.lang.String"%>
<%@ attribute name="transport" required="true" type="java.lang.String"%>
<%@ attribute name="startDate" required="true" type="java.sql.Date"%>
<%@ attribute name="endDate" required="true" type="java.sql.Date"%>
<%@ attribute name="id" required="true" type="java.lang.Integer"%>

<table class="table bg-white table-bordered">
	<tbody>
		<tr>
			<td>Hotel: ${hotel.name}</td>
		</tr>
		<tr>
			<td>Country: ${country}</td>
		</tr>
		<tr>
			<td>City: ${City}</td>
		</tr>
		<tr>
			<td>Transport: ${transport.type}</td>
		</tr>
		<tr>
			<td>Start date: ${startDate}</td>
		</tr>
		<tr>
			<td>End date: ${endDate}</td>
		</tr>
		<tr>
			<td>
				<form method="post" action="/SummaryTask4/controller?action=hotelForm&edit=true">
					<input type="hidden" name="id" value="${id}" />
					<button class="btn btn-success" type="submit">
						Edit
					</button>
				</form>
				<form method="post" action="/SummaryTask4/controller?action=deleteHotel">
					<input type="hidden" name="id" value="${id}" />
					<button class="btn btn-danger" type="submit">
						Delete
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>