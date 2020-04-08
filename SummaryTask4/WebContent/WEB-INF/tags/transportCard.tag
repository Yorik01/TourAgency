<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="transport" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Transport"%>

<table class="table bg-white table-bordered card mr-3 mt-3">
	<tbody>
		<tr>
			<td>Type: ${transport.type}</td>
		</tr>
		<tr>
			<td>Code: ${transport.code}</td>
		</tr>
		<tr>
			<td>Takeoff time: ${transport.takeoff}</td>
		</tr>
		<tr>
			<td>Arriving time: ${transport.arrive}</td>
		</tr>
		<tr>
			<td>Max places: ${transport.maxPlaces}</td>
		</tr>
		<tr>
			<td>Price: ${transport.price}</td>
		</tr>
		<tr>
			<td>From: ${transport.route.from.country}, ${transport.route.from.city}</td>
		</tr>
		<tr>
			<td>To: ${transport.route.to.country}, ${transport.route.to.city}</td>
		</tr>
		<tr>
			<td>
				<form method="get" action="/TourAgency/controller">
					<input type="hidden" name="action" value="transportForm" />
					<input type="hidden" name="edit" value="true" />
					<input type="hidden" name="id" value="${transport.id}" />
					<button class="btn btn-success" type="submit">
						Edit
					</button>
				</form>
				<form method="post" action="/TourAgency/controller?action=deleteTransport">
					<input type="hidden" name="id" value="${transport.id}" />
					<button class="btn btn-danger btn-delete" type="submit">
						Delete
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>