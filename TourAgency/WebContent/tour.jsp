<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="ua.nure.miroshnichenko.touragency.db.entity.TypeServicing"%>

<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="pageName" value="tour" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="d-inline-flex w-100">
		<div id="tour-photos" class="carousel slide ml-5" data-ride="carousel">
			<ol class="carousel-indicators">
				<c:forEach var="i" begin="0" end="${fn:length(requestScope.photos) - 1}">
					<li data-target="#tour-photos" data-slide-to="${i}"  
						<c:if test="${i == 0}">class="active"</c:if>></li>
				</c:forEach>
			</ol>
			<div class="carousel-inner">
				<c:forEach items="${requestScope.photos}" var="photo">
					<div class="carousel-item">
						<img class="d-block w-100"
							src="/TourAgency/photo/${photo}"
							alt="slide">
					</div>
				</c:forEach>
			</div>
			<a class="carousel-control-prev" href="#tour-photos" role="button"
				data-slide="prev"> <span class="carousel-control-prev-icon"
				aria-hidden="true"></span> <span class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#tour-photos" role="button"
				data-slide="next"> <span class="carousel-control-next-icon"
				aria-hidden="true"></span> <span class="sr-only">Next</span>
			</a>
		</div>
		<div id="tour-main-info" class="d-flex flex-column">
			<span class="d-flex flex-column"> 
			<span id="total-price-label"
				class="text-secondary"><fmt:message key="common.price" /> 
				<c:if test="${tour.isFired() eq 1}">
					<i class="fa fa-fire"></i>
				</c:if>
				</span> 
				<span
				id="tour-price">${tour.price} $</span>
			</span>
			<form action="/TourAgency/controller?action=reserveTour"
				method="post">
				<input type="hidden" name="id" value="${tour.id}" />
				<button id="btn-reserve" class="btn btn-primary" data-toggle="modal"
					type="button" data-target="#select-count-people"><fmt:message key="tour_jsp.reserve" /></button>

				<div class="modal fade" id="select-count-people" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel"><fmt:message key="common.reservation" /></h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<select class="form-control" id="select-people-count" name="peopleCount">
									<option value="1" selected>1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
								</select>
								<br />
								<span>
									<fmt:message key="common.total" />: 
									<span id="total-price-for-reservation">${tour.price}</span>
								</span>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-outline-secondary"
									data-dismiss="modal"><fmt:message key="admin_jsp.close" /></button>
								<button type="submit" class="btn btn-primary"><fmt:message key="admin_jsp.save" /></button>
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="mt-2">
				<hr>
				<span> <span class="tour-info-name mr-2"><fmt:message key="common.country" />:</span> <span
					class="tour-info-value">${tour.transportTo.route.to.country}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2"><fmt:message key="common.city" />:</span> <span
					class="tour-info-value">${tour.transportTo.route.to.city}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2"><fmt:message key="common.from" />:</span> <span
					class="tour-info-value">${tour.transportTo.route.from.country}, ${tour.transportTo.route.from.city}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2"><fmt:message key="common.start_date" />:</span> <span
					class="tour-info-value">${tour.startDate}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2"><fmt:message key="common.end_date" />:</span> <span
					class="tour-info-value">${tour.endDate}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2"><fmt:message key="common.tour_type" />:</span> <span
					class="tour-info-value">${tour.type}</span>
				</span>
			</div>
		</div>
	</div>
	<hr>
	<div id="transport-info">
		<h3><fmt:message key="common.transport" /></h3>
		<h4 class="mt-3"><fmt:message key="common.ticket" /></h4>
		<div class="ml-5">
			<span>
				<span class="tour-info-name mr-2"><fmt:message key="common.transport_type" />:</span> 
				<span class="tour-info-value">${tour.transportTo.type}</span>
			</span>
			<hr>
			<span> 
				 <span class="tour-info-name mr-2"><fmt:message key="common.arriving_time" />: </span>
				 <span class="tour-info-value">${tour.transportTo.arrive}</span>
			</span>
			<hr>
			<span> 
				<span class="tour-info-name mr-2"><fmt:message key="common.takeoff_time" />:</span> 
				<span class="tour-info-value">${tour.transportTo.takeoff}</span>
			</span>
			<hr>
		</div>
		<h4>Ticket back</h4>
		<div class="ml-5">
			<span> 
				<span class="tour-info-name mr-2"><fmt:message key="common.transport_type" />:</span>
				<span class="tour-info-value">${tour.transportBack.type}</span>
			</span>
			<hr>
			<span> 
				<span class="tour-info-name mr-2"><fmt:message key="common.arriving_time" />:</span> 
				<span class="tour-info-value">${tour.transportBack.arrive}</span>
			</span>
			<hr>
			<span> 
				<span class="tour-info-name mr-2"><fmt:message key="common.takeoff_time" />:</span>
				<span class="tour-info-value">${tour.transportBack.takeoff}</span>
			</span>
			<hr>
		</div>
	</div>
	<div id="tour-info">
		<h3><fmt:message key="common.hotel" /></h3>
		<p class="ml-5">${tour.hotel.info}</p>
		<hr>
		<div class="ml-5">
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.name" />:</span> <span
				class="tour-info-value">${tour.hotel.name}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.hotel_type" />:</span> <span
				class="tour-info-hotel-type">${tour.hotel.type}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.address" />:</span> <span
				class="tour-info-value">${tour.hotel.address}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.site" />:</span> <span
				class="tour-info-value">${tour.hotel.site}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.telephone" />:</span> <span
				class="tour-info-value">${tour.hotel.tel}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.hotel_stars" />:</span> <span
				class="tour-info-value">${tour.hotel.stars}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.max_rooms" />:</span> <span
				class="tour-info-value">${tour.hotel.maxRooms}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.food" />:</span> <span
				class="tour-info-value">${tour.hotel.food}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.beach" />:</span> <span
				class="tour-info-value">${tour.hotel.beach}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2"><fmt:message key="common.hotel_type" />:</span> <span
				class="tour-info-value">${tour.hotel.type}</span>
			</span>
			<hr>
			<div class="d-inline-flex justify-content-between w-100">
				<div id="facility-info">
					<h5><fmt:message key="common.in_room" /></h5>
					<ul>
						<c:if test="${fn:contains(tour.hotel.facilities, 'WIFI')}">
							<li>Wifi</li>
						</c:if>
						<c:if test="${fn:contains(tour.hotel.facilities, 'MINIBAR')}">
							<li><fmt:message key="common.minibar" /></li>
						</c:if>
						<c:if test="${fn:contains(tour.hotel.facilities, 'HAIRDRYER')}">
							<li><fmt:message key="common.hairdryer" /></li>
						</c:if>
						<c:if test="${fn:contains(tour.hotel.facilities, 'CONDITIONER')}">
							<li><fmt:message key="common.conditioner" /></li>
						</c:if>
						<c:if test="${fn:contains(tour.hotel.facilities, 'TV')}">
							<li>TV</li>
						</c:if>
					</ul>
				</div>
				<div id="sport-info">
					<h5><fmt:message key="common.sport" /></h5>
					<ul>
						<c:forEach items="${tour.hotel.servicings}" var="servicing">
							<c:if test="${servicing.type eq TypeServicing.SPORT}">
								<li>${servicing.name}</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<div id="entertainment" class="mr-3">
					<h5><fmt:message key="common.entertainment" /></h5>
					<ul>
						<c:forEach items="${tour.hotel.servicings}" var="servicing">
							<c:if test="${servicing.type eq TypeServicing.ENTERTAINMENT}">
								<li>${servicing.name}</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<hr />
	
	<c:set var="userComment" value="" />
	<c:forEach items="${comments}" var="comment">
		<c:if test="${comment.user.id eq sessionScope.user.id}">
			<c:set var="userComment" value="${comment.id}" />
		</c:if>
	</c:forEach>
	
	<c:choose>
		<c:when test="${not empty userComment}">
			<button class="btn btn-success btn-edit-comment ml-5 mt-3 mb-3" id="btn-open-comment-modal" data-toggle="modal" data-target="#comment-editor-modal">
			<fmt:message key="tour_jsp.edit_comment" /></button>
			<input type="hidden" id="comment-id" value="${userComment}" />
		</c:when>
		<c:otherwise>
			<button class="btn btn-success ml-5 mt-3 mb-3" id="btn-open-comment-modal" data-toggle="modal" data-target="#comment-editor-modal">
			<fmt:message key="tour_jsp.save_comment" /></button>
			<input type="hidden" id="comment-id" value="" />
		</c:otherwise>
	</c:choose>	
		
	<%@ include file="/WEB-INF/jsp/comment/comments.jsp" %>
		
	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
	
	<%@ include file="/WEB-INF/jspf/modals/commentModal.jspf" %>
		
	<%@ include file="/WEB-INF/jspf/modals/infoModal.jspf" %>	
	
	<%@ include file="/WEB-INF/jspf/modals/confirmDeleteModal.jspf" %>
	<script>
		$(document).ready(() => {
		    setUserId('${sessionScope.user.id}');
			setTourId('${param.id}');
			
			const tourPrice = '${tour.price}';
			
			$('#select-people-count').change(function () {
			    let totalPrice = tourPrice * $(this).val()
			    $('#total-price-for-reservation').text(totalPrice);
			});
		});
	</script>
</body>
</html>