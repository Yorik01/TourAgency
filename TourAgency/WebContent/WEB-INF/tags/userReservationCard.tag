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
			<td><fmt:message key="common.date" />: ${reservation.resrveDate}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.people_count" />: ${reservation.peopleCount}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.status" />: ${reservation.status}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.discount" />: ${reservation.discount}</td>
		</tr>
		<tr>
			<td>
				<c:if test="${reservation.status eq 'RESERVED'}">
					<form method="post" action="/TourAgency/controller">
						<input type="hidden" name="id" value="${reservation.id}" />
						<input type="hidden" name="action" value="revokeReservation" />
						<button class="btn btn-danger" type="submit">
							<fmt:message key="common.revoke" />
						</button>
					</form>
				</c:if>
			</td>
		</tr>
	</tbody>
</table>