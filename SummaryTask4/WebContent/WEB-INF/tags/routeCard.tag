<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="route" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Route"%>

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
				<form action="/TourAgency/controller">
					<input type="hidden" name="action" value="routeForm" />
					<input type="hidden" name="edit" value="true" />
					<input type="hidden" name="id" value="${route.id}" />
					<button class="btn btn-success" type="submit">
						Edit
					</button>
				</form>
				<form method="post" class="delete-entity" action="/TourAgency/controller?action=deleteRoute">
					<input type="hidden" name="id" value="${route.id}" />
					<button class="btn btn-danger btn-delete" type="submit">
						Delete
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>