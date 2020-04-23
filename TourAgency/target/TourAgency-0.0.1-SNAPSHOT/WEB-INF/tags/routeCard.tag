<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="countryFrom" required="true" type="java.lang.String"%>
<%@ attribute name="cityFrom" required="true" type="java.lang.String"%>
<%@ attribute name="countryTo" required="true" type="java.lang.String"%>
<%@ attribute name="cityTo" required="true" type="java.lang.String"%>
<%@ attribute name="id" required="true" type="java.lang.Integer"%>

<table class="table bg-white table-bordered">
	<tbody>
		<tr>
			<td>Country from: ${countryFrom}</td>
		</tr>
		<tr>
			<td>City from: ${cityFrom}</td>
		</tr>
		<tr>
			<td>Country to: ${countryTo}</td>
		</tr>
		<tr>
			<td>City to: ${cityTo}</td>
		</tr>
		<tr>
			<td>
				<form method="post" action="/TourAgency/controller?action=routeForm&edit=true">
					<input type="hidden" name="id" value="${id}" />
					<button class="btn btn-success" type="submit">
						Edit
					</button>
				</form>
				<form method="post" action="/TourAgency/controller?action=deleteRoute">
					<input type="hidden" name="id" value="${id}" />
					<button class="btn btn-danger" type="submit">
						Delete
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>