<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="tour" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Tour"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<table class="table bg-white table-bordered card mt-3 mr-3">
	<tbody>
		<tr>
			<td><fmt:message key="common.type" />: ${tour.type}</td>
		</tr>
		<tr><fmt:message key="common.name" />
			<td><fmt:message key="common.hotel" />: ${tour.hotel.name}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.country" />: ${tour.transportTo.route.to.country}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.city" />: ${tour.transportTo.route.to.city}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.transport" />: ${transport.code}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.status" />: 
				<c:choose>
					<c:when test="${tour.isFired() == 0}">
				 		<fmt:message key="common.un_fired" />
				 	</c:when>
				 	<c:otherwise>
				 		<fmt:message key="common.fired" />
				 	</c:otherwise>
			 	</c:choose>
			</td>
		</tr>
		<tr>
			<td><fmt:message key="common.start_date" />: ${tour.startDate}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.end_date" />: ${tour.endDate}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.agency_procent" />: ${tour.agencyProcent}</td>
		</tr>
		<tr>
			<td><fmt:message key="common.price" />: ${tour.price}</td>
		</tr>
		<tr>
			<td>
				<form action="/TourAgency/controller">
					<input type="hidden" name="action" value="tourForm" />
					<input type="hidden" name="edit" value="true" />
					<input type="hidden" name="id" value="${tour.id}" />			
					<button class="btn btn-success" type="submit">
						<fmt:message key="common.edit" />
					</button>
				</form>
				<form method="post" action="/TourAgency/controller?action=deleteTour">
					<input type="hidden" name="id" value="${tour.id}" />
					<button class="btn btn-danger btn-delete" type="submit">
						<fmt:message key="common.delete" />
					</button>
				</form>
				<form method="post" action="/TourAgency/controller">
					<input type="hidden" name="id" value="${tour.id}" />
					<c:choose>
				 		<c:when test="${tour.isFired() == 0}">
						 	<input type="hidden" name="action" value="SetTourFired" />
						 	<input type="hidden" name="status" value="1">
							<button class="btn btn-warning" type="submit">
								<fmt:message key="common.make_fired" />
							</button>
						 </c:when>
						<c:otherwise>
							<input type="hidden" name="action" value="SetTourFired" />
							<input type="hidden" name="status" value="0">
							<button class="btn btn-warning" type="submit">
								<fmt:message key="common.make_unfired" />
							</button>
					 	</c:otherwise>
				 	</c:choose>
			 	</form>
			</td>
		</tr>
	</tbody>
</table>