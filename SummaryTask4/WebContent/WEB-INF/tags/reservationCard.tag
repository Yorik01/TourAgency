<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="reservation" required="true" type="ua.nure.miroshnichenko.summarytask4.db.entity.Reservation"%>

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
			<td>User: ${reservation.user.email}</td>
		</tr>
		<tr>
			<td>Date: ${reservation.resrveDate}</td>
		</tr>
		<tr>
			<td>Status: ${reservation.status}</td>
		</tr>
	</tbody>
</table>