<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="name" required="true" type="java.lang.String"%>
<%@ attribute name="address" required="true" type="java.lang.String"%>
<%@ attribute name="stars" required="true" type="java.lang.Integer"%>
<%@ attribute name="id" required="true" type="java.lang.Integer"%>

<table class="table bg-white table-bordered">
	<tbody>
		<tr>
			<td>Name: ${name}</td>
		</tr>
		<tr>
			<td>Address: ${address}</td>
		</tr>
		<tr>
			<td>Stars: ${stars}</td>
		</tr>
		<tr>
			<td>
				<form method="post" action="/TourAgency/controller?action=hotelForm&edit=true">
					<input type="hidden" name="id" value="${id}" />
					<button class="btn btn-success" type="submit">
						Edit
					</button>
				</form>
				<form method="post" action="/TourAgency/controller?action=deleteHotel">
					<input type="hidden" name="id" value="${id}" />
					<button class="btn btn-danger" type="submit">
						Delete
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>