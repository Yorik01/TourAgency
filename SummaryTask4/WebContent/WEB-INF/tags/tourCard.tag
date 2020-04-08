<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="tour" required="true" type="ua.nure.miroshnichenko.touragency.db.entity.Tour"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<table class="table bg-white table-bordered card mt-3 mr-3">
	<tbody>
		<tr>
			<td>Type: ${tour.type}</td>
		</tr>
		<tr>
			<td>Hotel: ${tour.hotel.name}</td>
		</tr>
		<tr>
			<td>Country: ${tour.transportTo.route.to.country}</td>
		</tr>
		<tr>
			<td>City: ${tour.transportTo.route.to.city}</td>
		</tr>
		<tr>
			<td>Transport: ${transport.code}</td>
		</tr>
		<tr>
			<td>status: 
				<c:choose>
					<c:when test="${tour.isFired() == 0}">
				 		Not fired
				 	</c:when>
				 	<c:otherwise>
				 		Fired
				 	</c:otherwise>
			 	</c:choose>
			</td>
		</tr>
		<tr>
			<td>Start date: ${tour.startDate}</td>
		</tr>
		<tr>
			<td>End date: ${tour.endDate}</td>
		</tr>
		<tr>
			<td>Agency percent: ${tour.agencyProcent}</td>
		</tr>
		<tr>
			<td>Total price: ${tour.price}</td>
		</tr>
		<tr>
			<td>
				<form action="/TourAgency/controller">
					<input type="hidden" name="action" value="tourForm" />
					<input type="hidden" name="edit" value="true" />
					<input type="hidden" name="id" value="${tour.id}" />			
					<button class="btn btn-success" type="submit">
						Edit
					</button>
				</form>
				<form method="post" action="/TourAgency/controller?action=deleteTour">
					<input type="hidden" name="id" value="${tour.id}" />
					<button class="btn btn-danger btn-delete" type="submit">
						Delete
					</button>
				</form>
				<form method="post" action="/TourAgency/controller">
					<input type="hidden" name="id" value="${tour.id}" />
					<c:choose>
				 		<c:when test="${tour.isFired() == 0}">
						 	<input type="hidden" name="action" value="SetTourFired" />
						 	<input type="hidden" name="status" value="1">
							<button class="btn btn-warning" type="submit">
								Make fired
							</button>
						 </c:when>
						<c:otherwise>
							<input type="hidden" name="action" value="SetTourFired" />
							<input type="hidden" name="status" value="0">
							<button class="btn btn-warning" type="submit">
								Make unfired
							</button>
					 	</c:otherwise>
				 	</c:choose>
			 	</form>
			</td>
		</tr>
	</tbody>
</table>