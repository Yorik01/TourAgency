<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="reservation" required="true" type="ua.nure.miroshnichenko.summarytask4.db.entity.Reservation"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<table class="table bg-white table-bordered">
	<tbody>
		<tr>
			<td>
				<a href="/SummaryTask4/controller?action=tourInfo&id=${reservation.id}">
				Tour: ${reservation.tour.hotel.name}
				</a>
			</td>
		</tr>
		<tr>
			<td>Date: ${reservation.resrveDate}</td>
		</tr>
		<tr>
			<td>Count of people: ${reservation.peopleCount}</td>
		</tr>
		<tr>
			<td>Status: ${reservation.status}</td>
		</tr>
		<tr>
			<td>
				<c:if test="${reservation.status eq 'RESERVED'}">
					<form method="post" action="/SummaryTask4/controller">
						<input type="hidden" name="id" value="${reservation.id}" />
						<input type="hidden" name="action" value="revokeReservation" />
						<button class="btn btn-danger" type="submit">
							Revoke
						</button>
					</form>
				</c:if>
			</td>
		</tr>
	</tbody>
</table>