<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="hotel" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Hotel"%>

<table class="table bg-white table-bordered card mr-3 mt-3">
	<tbody>
		<tr>
			<td>Name: ${hotel.name}</td>
		</tr>
		<tr>
			<td>Type: ${hotel.type}</td>
		</tr>
		<tr>
			<td>Address: ${hotel.address}</td>
		</tr>
		<tr>
			<td>Stars: ${hotel.stars}</td>
		</tr>
		<tr>
			<td>Telephone: ${hotel.tel}</td>
		</tr>
		<tr>
			<td>Site: <a href="${hotel.site}">${hotel.site}</a></td>
		</tr>
		<tr>
			<td>Max rooms: ${hotel.maxRooms}</td>
		</tr>
		<tr>
			<td>Price: ${hotel.price}</td>
		</tr>
		<tr>
			<td>
				<form action="/TourAgency/controller">
					<input type="hidden" name="action" value="hotelForm" />
					<input type="hidden" name="edit" value="true" />
					<input type="hidden" name="id" value="${hotel.id}" />
					<button class="btn btn-success" type="submit">
						Edit
					</button>
				</form>
				<form method="post" action="/TourAgency/controller?action=deleteHotel">
					<input type="hidden" name="id" value="${hotel.id}" />
					<button class="btn btn-danger btn-delete" type="submit">
						Delete
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>