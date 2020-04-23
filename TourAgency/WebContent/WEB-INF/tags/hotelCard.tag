<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="hotel" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Hotel"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<table class="table bg-white table-bordered card mr-3 mt-3">
	<tbody>
		<tr>
			<td><fmt:message key="common.name" />: ${hotel.name}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.type" />: ${hotel.type}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.address" />: ${hotel.address}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.hotel_stars" />: ${hotel.stars}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.telephone" />: ${hotel.tel}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.site" />: <a href="${hotel.site}">${hotel.site}</a></td>
		</tr>
		<tr>
			<td><fmt:message key="common.max_rooms" />: ${hotel.maxRooms}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.price" />: ${hotel.price}</td>
		</tr>
		<tr>
			<td>
				<form action="/TourAgency/controller">
					<input type="hidden" name="action" value="hotelForm" />
					<input type="hidden" name="edit" value="true" />
					<input type="hidden" name="id" value="${hotel.id}" />
					<button class="btn btn-success" type="submit">
						<fmt:message key="common.edit" />
					</button>
				</form>
				<form method="post" action="/TourAgency/controller?action=deleteHotel">
					<input type="hidden" name="id" value="${hotel.id}" />
					<button class="btn btn-danger btn-delete" type="submit">
						<fmt:message key="common.delete" />
					</button>
				</form>
			</td>
		</tr>
	</tbody>
</table>