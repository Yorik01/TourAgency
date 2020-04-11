<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<%@ attribute name="transport" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Transport"%>

<table class="table bg-white table-bordered card mr-3 mt-3">
	<tbody>
		<tr>
			<td><fmt:message key="common.type" />: ${transport.type}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.transport_code" />: ${transport.code}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.takeoff_time" />: ${transport.takeoff}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.arriving_time" />: ${transport.arrive}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.max_places" />: ${transport.maxPlaces}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.price" />: ${transport.price}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.from" />: ${transport.route.from.country}, ${transport.route.from.city}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.to" />: ${transport.route.to.country}, ${transport.route.to.city}</td>
		</tr>
		<tr>
			<td>
				<form method="get" action="/TourAgency/controller">
					<input type="hidden" name="action" value="transportForm" />
					<input type="hidden" name="edit" value="true" />
					<input type="hidden" name="id" value="${transport.id}" />
					<button class="btn btn-success" type="submit">
						<fmt:message key="common.edit" />
					</button>
				</form>
				<form method="post" action="/TourAgency/controller?action=deleteTransport">
					<input type="hidden" name="id" value="${transport.id}" />
					<button class="btn btn-danger btn-delete" type="submit">
						<fmt:message key="common.delete" />
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>