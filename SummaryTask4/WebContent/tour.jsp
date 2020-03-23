<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<%@ page
	import="ua.nure.miroshnichenko.summarytask4.db.entity.TypeServicing"%>

<c:set var="title" value="Error" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="font/font-awesome/css/all.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<title>Tour</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="d-inline-flex w-100">
		<div id="tour-photos" class="carousel slide ml-5" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#tour-photos" data-slide-to="0" class="active"></li>
				<li data-target="#tour-photos" data-slide-to="1"></li>
				<li data-target="#tour-photos" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img class="d-block w-100"
						src="https://q-cf.bstatic.com/images/hotel/max1024x768/681/68184730.jpg"
						alt="First slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100"
						src="https://q-cf.bstatic.com/images/hotel/max1024x768/681/68184730.jpg"
						alt="Second slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100"
						src="https://q-cf.bstatic.com/images/hotel/max1024x768/681/68184730.jpg"
						alt="Third slide">
				</div>
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
			<span class="d-flex flex-column"> <span id="total-price-label"
				class="text-secondary">total <i class="fa fa-fire"></i></span> <span
				id="tour-price">${tour.price} $</span>
			</span>
			<form action="/SummaryTask4/controller?action=reserveTour"
				method="post">
				<input type="hidden" value="${tour.id}" />
				<button id="btn-reserve" class="btn btn-primary" data-toggle="modal" type="button" data-target="#select-count-people">Reserve</button>

				<div class="modal fade" id="select-count-people" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">...</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-outline-secondary"
									data-dismiss="modal">Close</button>
								<button type="submit" class="btn btn-primary">Save changes</button>
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="mt-2">
				<hr>
				<span> <span class="tour-info-name mr-2">Country:</span> <span
					class="tour-info-value">${tour.transportTo.route.to.country}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2">City:</span> <span
					class="tour-info-value">${tour.transportTo.route.to.city}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2">From:</span> <span
					class="tour-info-value">${tour.transportTo.route.from.country}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2">Date start:</span> <span
					class="tour-info-value">${tour.startDate}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2">Date end:</span> <span
					class="tour-info-value">${tour.endDate}</span>
				</span>
				<hr>
				<span> <span class="tour-info-name mr-2">Tour type:</span> <span
					class="tour-info-value">${tour.type}</span>
				</span>
			</div>
		</div>
	</div>
	<hr>
	<div id="transport-info">
		<h3>Transport</h3>
		<h4 class="mt-3">Ticket to</h4>
		<div class="ml-5">
			<span> <span class="tour-info-name mr-2">Transport
					type:</span> <span class="tour-info-value">${tour.transportTo.type}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Arriving time:
			</span> <span class="tour-info-value">${tour.transportTo.arrive}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Taking off
					time: </span> <span class="tour-info-value">${tour.transportTo.takeoff}</span>
			</span>
			<hr>
		</div>
		<h4>Ticket back</h4>
		<div class="ml-5">
			<span> <span class="tour-info-name mr-2">Transport
					type:</span> <span class="tour-info-value">${tour.transportBack.type}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Arriving time:
			</span> <span class="tour-info-value">${tour.transportBack.arrive}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Taking off
					time: </span> <span class="tour-info-value">${tour.transportBack.takeoff}</span>
			</span>
			<hr>
		</div>
	</div>
	<div id="tour-info">
		<h3>Hotel</h3>
		<p class="ml-5">${tour.hotel.info}</p>
		<hr>
		<div class="ml-5">
			<span> <span class="tour-info-name mr-2">Name:</span> <span
				class="tour-info-value">${tour.hotel.name}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Address:</span> <span
				class="tour-info-value">${tour.hotel.address}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Site:</span> <span
				class="tour-info-value">${tour.hotel.site}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Telephone:</span> <span
				class="tour-info-value">${tour.hotel.tel}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Stars:</span> <span
				class="tour-info-value">${tour.hotel.stars}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Max rooms:</span> <span
				class="tour-info-value">${tour.hotel.maxRooms}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Food:</span> <span
				class="tour-info-value">${tour.hotel.food}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Beach:</span> <span
				class="tour-info-value">${tour.hotel.beach}</span>
			</span>
			<hr>
			<span> <span class="tour-info-name mr-2">Hotel type:</span> <span
				class="tour-info-value">${tour.hotel.type}</span>
			</span>
			<hr>
			<div class="d-inline-flex justify-content-between w-100">
				<div id="facility-info">
					<h5>In room</h5>
					<ul>
						<c:if test="${fn:contains(tour.hotel.facilities, 'WIFI')}">
							<li>Wifi</li>
						</c:if>
						<c:if test="${fn:contains(tour.hotel.facilities, 'MINIBAR')}">
							<li>Minibar</li>
						</c:if>
						<c:if test="${fn:contains(tour.hotel.facilities, 'CONDITIONER')}">
							<li>Conditioner</li>
						</c:if>
						<c:if test="${fn:contains(tour.hotel.facilities, 'TV')}">
							<li>TV</li>
						</c:if>
					</ul>
				</div>
				<div id="sport-info">
					<h5>Sport</h5>
					<ul>
						<c:forEach items="${tour.hotel.servicings}" var="servicing">
							<c:if test="${servicing.type eq TypeServicing.SPORT}">
								<li>${servicing.name}</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<div id="entertainment" class="mr-3">
					<h5>Entertainment</h5>
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
	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
</body>
</html>