<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="route" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Tour"%>

<table class="table bg-white table-bordered card mr-3 mt-3">
	<tbody>
		<tr>
			<td>Country from: ${route.from.country}</td>
		</tr>
		<tr>
			<td>City from: ${route.from.city}</td>
		</tr>
		<tr>
			<td>Country to: ${route.to.country}</td>
		</tr>
		<tr>
			<td>City to: ${route.to.city}</td>
		</tr>
		<tr>
			<td>
				<form method="post" action="/TourAgency/controller?action=routeForm&edit=true&id=${route.id}">
					<button class="btn btn-success" type="submit">
						Edit
					</button>
				</form>
				<form method="post" action="/TourAgency/controller?action=deleteRoute">
					<input type="hidden" name="id" value="${route.id}" />
					<button class="btn btn-danger" type="submit">
						Delete
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>