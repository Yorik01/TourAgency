<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="id" required="true" type="java.lang.Integer"%>
<%@ attribute name="type" required="true" type="java.lang.String"%>
<%@ attribute name="code" required="true" type="java.lang.String"%>
<%@ attribute name="takeoff" required="true" type="java.sql.Timestamp"%>
<%@ attribute name="arrive" required="true" type="java.sql.Timestamp"%>
<%@ attribute name="route" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Route"%>

<table class="table bg-white table-bordered card mr-3 mt-3">
	<tbody>
		<tr>
			<td>Type: ${type}</td>
		</tr>
		<tr>
			<td>Code: ${code}</td>
		</tr>
		<tr>
			<td>Takeoff time: ${takeoff}</td>
		</tr>
		<tr>
			<td>Arriving time: ${arrive}</td>
		</tr>
		<tr>
			<td>From: ${route.from.country}, ${route.from.city}</td>
		</tr>
		<tr>
			<td>To: ${route.to.country}, ${route.to.city}</td>
		</tr>
		<tr>
			<td>
				<form method="post" action="/TourAgency/controller?action=transportForm&edit=true">
					<input type="hidden" name="id" value="${id}" />
					<button class="btn btn-success" type="submit">
						Edit
					</button>
				</form>
			</td>
			<td>
				<form method="post" action="/TourAgency/controller?action=deleteTransport">
					<input type="hidden" name="id" value="${id}" />
					<button class="btn btn-danger" type="submit">
						Delete
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>