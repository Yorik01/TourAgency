<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="route" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Route"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<table class="table bg-white table-bordered card mr-3 mt-3">
	<tbody>
		<tr>
			<td><fmt:message key="common.country_from" />: ${route.from.country}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.city_from" />: ${route.from.city}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.country_to" />: ${route.to.country}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.city_to" />: ${route.to.city}</td>
		</tr>
		<tr>
			<td>
				<form action="/TourAgency/controller">
					<input type="hidden" name="action" value="routeForm" />
					<input type="hidden" name="edit" value="true" />
					<input type="hidden" name="id" value="${route.id}" />
					<button class="btn btn-success" type="submit">
						<fmt:message key="common.edit" />
					</button>
				</form>
				<form method="post" class="delete-entity" action="/TourAgency/controller?action=deleteRoute">
					<input type="hidden" name="id" value="${route.id}" />
					<button class="btn btn-danger btn-delete" type="submit">
						<fmt:message key="common.delete" />
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>