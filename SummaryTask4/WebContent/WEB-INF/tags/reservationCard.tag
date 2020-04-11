<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="reservation" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Reservation"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<table class="table bg-white table-bordered card">
	<tbody>
		<tr>
			<td>
				<a href="/TourAgency/controller?action=tourInfo&id=${reservation.id}">
				<fmt:message key="common.tour" />: ${reservation.tour.hotel.name}
				</a>
			</td>
		</tr>
		<tr>
			<td><fmt:message key="common.user" />: ${reservation.user.email}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.date" />: ${reservation.resrveDate}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.discount" />: ${reservation.discount}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.status" />: ${reservation.status}</td>
		</tr>
	</tbody>
</table>