<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="pageName" value="index" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<!DOCTYPE html>
<html>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<form id="tour-filter" class="container-fluid form-group mt-5" action="/TourAgency/controller" method="get">
		<h1 class="mb-5"><fmt:message key="index_jsp.searching_tour" /></h1>
		<div id="tour-filter-main" class="shadow-sm p-3 bg-white rounded d-flex flex-row justify-content-around">
			<div>
				<select class="form-control" name="toCountry" id="select-country-to">
      			</select>
   			</div>
			<div>
				<select class="form-control" name="toCity" id="select-city-to">
      			</select>
   			</div>
			<div>
				<select class="form-control" name="fromCity" id="select-city-from">
      			</select>
   			</div>
			<div class="dates d-inline-flex">
			  <div class="start_date input-group ml-3">
			    <input class="form-control start_date" type="text" value="${param.startDate}" placeholder="<fmt:message key="common.start_date" />" name="startDate"  id="startdate_datepicker"
			    pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" title="yyyy-mm-dd" autocomplete="off">
			    <div class="input-group-append">
			      <span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    </div>
			
			  </div>
			  <div class="end_date input-group ml-3 mr-3">
			    <input class="form-control end_date" type="text" value="${param.endDate}" name="endDate" placeholder="<fmt:message key="common.end_date" />" id="enddate_datepicker"
			    pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" title="yyyy-mm-dd" autocomplete="off">
			    <div class="input-group-append">
			      <span class="fa fa-calendar input-group-text end_date_calendar" aria-hidden="true "></span>
			    </div>
			  </div>
			</div>
       		<div>
				<select class="form-control" name="peopleCount">
					<option value="1" <c:if test="${param.peopleCount eq 1}">selected</c:if>>1</option>
					<option value="2" <c:if test="${param.peopleCount eq 2}">selected</c:if>>2</option>
					<option value="3" <c:if test="${param.peopleCount eq 3}">selected</c:if>>3</option>
					<option value="4" <c:if test="${param.peopleCount eq 4}">selected</c:if>>4</option>
					<option value="5" <c:if test="${param.peopleCount eq 5}">selected</c:if>>5</option>
					<option value="6" <c:if test="${param.peopleCount eq 6}">selected</c:if>>6</option>
					<option value="7" <c:if test="${param.peopleCount eq 7}">selected</c:if>>7</option>
					<option value="8" <c:if test="${param.peopleCount eq 8}">selected</c:if>>8</option>
			  </select>
   			</div>
			<button class="btn btn-primary" type="submit"><fmt:message key="common.search" /></button>
		</div>
		<div id="tour-filter-extended" class="shadow-sm mt-3 p-3 bg-white rounded d-flex flex-column justify-content-around">
			<div class="d-inline-flex justify-content-between">
				<div id="hotel-stars">
					<h5><fmt:message key="common.hotel_stars" /></h5>
					<div class="d-inline-flex">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" name="stars" value="1" id="star-1"
						    <c:if test="${fn:contains(filterParams.stars, 1)}">checked</c:if>>
						    <label class="custom-control-label" for="star-1">1</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" name="stars" value="2" id="star-2"
						    <c:if test="${fn:contains(filterParams.stars, 2)}">checked</c:if>>
						    <label class="custom-control-label" for="star-2">2</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" name="stars" value="3" id="star-3"
						    <c:if test="${fn:contains(filterParams.stars, 3)}">checked</c:if>>
						    <label class="custom-control-label" for="star-3">3</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" name="stars" value="4" id="star-4"
						    <c:if test="${fn:contains(filterParams.stars, 4)}">checked</c:if>>
						    <label class="custom-control-label" for="star-4">4</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" name="stars" value="5" id="star-5"
						    <c:if test="${fn:contains(filterParams.stars, 5)}">checked</c:if>>
						    <label class="custom-control-label" for="star-5">5</label>
						</div>
					</div>
				</div>
				<div id="hotel-price">
					<c:set var="transportType" value="${filterParams.transportType}" />
					<c:set var="maxPrice" value="10000" />
					<c:if test="${not empty param.maxPrice}">
						<c:set var="maxPrice" value="${param.maxPrice}" />
					</c:if>
					<h5><fmt:message key="index_jsp.max_price" /></h5>
					<div class="custom-control custom-range">
						<label for="hotel-max-price" id="hotel-max-price-label">${maxPrice}</label>
						<input type="range" min="200" max="100000" value="${maxPrice}" name="maxPrice" class="custom-range" id="hotel-max-price">
					</div>
				</div>
				<div id="hotel-transport-type" class="d-flex flex-column">
					<h5><fmt:message key="common.transport_type" /></h5>
					<div class="custom-control custom-checkbox my-1 mr-sm-2">
						<input type="checkbox" class="custom-control-input" name="transportType" value="AIRPLANE" id="transport-air"
						<c:if test="${fn:contains(transportType, 'AIRPLANE')}">checked</c:if>>
						<label class="custom-control-label" for="transport-air"><fmt:message key="common.plane" /></label>
					</div>
					<div class="custom-control custom-checkbox my-1 mr-sm-2">
						<input type="checkbox" class="custom-control-input" name="transportType" value="BUS" id="transport-bus"
						<c:if test="${fn:contains(transportType, 'BUS')}">checked</c:if>>
						<label class="custom-control-label" for="transport-bus"><fmt:message key="common.bus" /></label>
					</div>
				</div>
			</div>
			<hr/>
			<div class="d-inline-flex justify-content-between">
			<div id="hotel-type">
					<c:set var="hotelType" value="${filterParams.hotelType}" />
					<h5><fmt:message key="common.hotel_type" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="hotelType" value="MOTEL" id="hotel-type-motel"
							<c:if test="${fn:contains(hotelType, 'MOTEL')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-type-motel"><fmt:message key="common.motel" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="hotelType" value="RESORTS" id="hotel-type-resorts"
							<c:if test="${fn:contains(hotelType, 'RESORTS')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-type-resorts"><fmt:message key="common.resort" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="hotelType" value="HOSTEL" id="hotel-type-hostel"
							<c:if test="${fn:contains(hotelType, 'HOSTEL')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-type-hostel"><fmt:message key="common.hostel" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="hotelType" value="BOUTIQUE" id="hotel-type-boutique"
							<c:if test="${fn:contains(hotelType, 'BOUTIQUE')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-type-boutique"><fmt:message key="common.boutique" /></label>
						</div>
					</div>
				</div>
				<div id="tour-type">
					<c:set var="tourType" value="${filterParams.tourType}" />
					<h5><fmt:message key="common.tour_type" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="tourType" value="RELAX" id="tour-type-relax"
							<c:if test="${fn:contains(tourType, 'RELAX')}">checked</c:if>>
							<label class="custom-control-label" for="tour-type-relax"><fmt:message key="common.relax" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="tourType" value="EXCURSION" id="tour-type-excursion"
							<c:if test="${fn:contains(tourType, 'EXCURSION')}">checked</c:if>>
							<label class="custom-control-label" for="tour-type-excursion"><fmt:message key="common.excursion" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="tourType" value="SHOPPING" id="tour-type-shopping"
							<c:if test="${fn:contains(tourType, 'SHOPPING')}">checked</c:if>>
							<label class="custom-control-label" for="tour-type-shopping"><fmt:message key="common.shopping" /></label>
						</div>
					</div>
				</div>
				<div id="hotel-food-type" class="mr-5">
					<c:set var="food" value="${filterParams.food}" />
					<h5><fmt:message key="common.food" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="AL" id="food-al"
							<c:if test="${fn:contains(food, 'AL')}">checked</c:if>>
							<label class="custom-control-label" for="food-al">AL</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="FB" id="food-fb"
							<c:if test="${fn:contains(food, 'FB')}">checked</c:if>>
							<label class="custom-control-label" for="food-fb">FB</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="HB" id="food-hb"
							<c:if test="${fn:contains(food, 'HB')}">checked</c:if>>
							<label class="custom-control-label" for="food-hb">HB</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="BB" id="food-bb"
							<c:if test="${fn:contains(food, 'BB')}">checked</c:if>>
							<label class="custom-control-label" for="food-bb">BB</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="OB" id="food-ob"
							<c:if test="${fn:contains(food, 'OB')}">checked</c:if>>
							<label class="custom-control-label" for="food-ob">OB</label>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<div class="d-inline-flex justify-content-between">
				<c:set var="facility" value="${filterParams.facility}" />
				<div id="hotel-facilities" class="pr-2">
					<h5><fmt:message key="common.in_room" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="WIFI" id="facility-wifi"
							<c:if test="${fn:contains(facility, 'WIFI')}">checked</c:if>>
							<label class="custom-control-label" for="facility-wifi">wifi</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="MINIBAR" id="facility-minibar"
							<c:if test="${fn:contains(facility, 'MINIBAR')}">checked</c:if>>
							<label class="custom-control-label" for="facility-minibar"><fmt:message key="common.mini_bar" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="HAIRDRYER" id="facility-hairdryer"
							<c:if test="${fn:contains(facility, 'HAIRDRYER')}">checked</c:if>>
							<label class="custom-control-label" for="facility-hairdryer"><fmt:message key="common.hairdryer" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="CONDITIONER" id="facility-conditioner"
							<c:if test="${fn:contains(facility, 'CONDITIONER')}">checked</c:if>>
							<label class="custom-control-label" for="facility-conditioner"><fmt:message key="common.conditioner" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="TV" id="facility-tv"
							<c:if test="${fn:contains(facility, 'TV')}">checked</c:if>>
							<label class="custom-control-label" for="facility-tv">TV</label>
						</div>
					</div>
				</div>
				<div id="hotel-sport" class="pr-2">
					<c:set var="servicing" value="${filterParams.servicing}" />
					<h5><fmt:message key="common.sport" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="GYM" id="hotel-sport-gym"
							<c:if test="${fn:contains(servicing, 'GYM')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-sport-gym"><fmt:message key="common.gym" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="FITNES" id="hotel-sport-fitnes"
							<c:if test="${fn:contains(servicing, 'FITNES')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-sport-fitnes"><fmt:message key="common.fitnes" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="DIVING" id="hotel-sport-diving"
							<c:if test="${fn:contains(servicing, 'DIVING')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-sport-diving"><fmt:message key="common.diving" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="YOGA" id="hotel-sport-yoga"
							<c:if test="${fn:contains(servicing, 'YOGA')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-sport-yoga"><fmt:message key="common.yoga" /></label>
						</div>
					</div>
				</div>
				<div id="hotel-entartainment" class="pr-2">
					<c:set var="entertainment" value="${filterParams.entertainment}" />
					<h5><fmt:message key="common.entertainment" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="AQUAPARK" id="hotel-entartainment-aquapark"
							<c:if test="${fn:contains(servicing, 'AQUAPARK')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-entartainment-aquapark"><fmt:message key="common.aquapark" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="DISCO" id="hotel-entartainment-disco"
							<c:if test="${fn:contains(servicing, 'DISCO')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-entartainment-disco"><fmt:message key="common.disco" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="RESTAURANT" id="hotel-entartainment-restaurant"
							<c:if test="${fn:contains(servicing, 'RESTAURANT')}">checked</c:if>>
							<label class="custom-control-label" for="hotel-entartainment-restaurant"><fmt:message key="common.restaurant" /></label>
						</div>
					</div>
				</div>
			</div>
			<hr />
			<div class="d-inline-flex justify-content-between">
				<c:set var="beach" value="${filterParams.beach}" />
				<div id="hotel-beach-type">
					<h5><fmt:message key="common.beach" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="SAND" id="beach-sand"
							<c:if test="${fn:contains(beach, 'SAND')}">checked</c:if>>
							<label class="custom-control-label" for="beach-sand"><fmt:message key="common.sand" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="PEBBLE" id="beach-pebble"
							<c:if test="${fn:contains(beach, 'PEBBLE')}">checked</c:if>>
							<label class="custom-control-label" for="beach-pebble"><fmt:message key="common.pebble" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="PLATE" id="beach-plate"
							<c:if test="${fn:contains(beach, 'PLATE')}">checked</c:if>>
							<label class="custom-control-label" for="beach-plate"><fmt:message key="common.plate" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="SAND_PEBBLE" id="beach-sand-pebble"
							<c:if test="${fn:contains(beach, 'SAND_PEBBLE')}">checked</c:if>>
							<label class="custom-control-label" for="beach-sand-pebble"><fmt:message key="common.sand_pebble" /></label>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="action" value="filterTours">
	</form>
		
	<div id="tour-all" class="p-3">
		<h1 class="mb-2"><fmt:message key="common.tours" /></h1>
		<div class="d-flex flex-wrap justify-content-between">
			<c:forEach items="${requestScope.tours}" var="tour">
				<t:tourUserCard tour="${tour}" />
			</c:forEach>
		</div>
	</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
	
<script src="js/route.js"></script>
<script>
$(function () {
	$("#startdate_datepicker").datepicker({
	    format: 'yyyy-mm-dd'
	});
	$("#enddate_datepicker").datepicker({
	    format: 'yyyy-mm-dd'
	});
	
	let hotelMaxPriceLabel = $("#hotel-max-price-label");
	$("#hotel-max-price").on("input change", function() {
		hotelMaxPriceLabel.text($(this).val());
	});
	
	let places = JSON.parse('${requestScope.places}');
	
	let countryTo = $("#select-country-to");
	let citiesTo = $("#select-city-to");
	
	let citiesFrom = $("#select-city-from");
		
	setCities(places['Ukraine'], citiesFrom);

	delete places['Ukraine'];

	setCountries(places, countryTo);

	<c:if test="${not empty param.toCountry}">
		countryTo.val('${param.toCountry}');
		citiesFrom.val('${param.fromCity}');
	</c:if>
	
	selectedCountry = countryTo.children('option:selected').val();
	setCities(places[selectedCountry], citiesTo);
	
	<c:if test="${not empty param.toCountry}">
		citiesTo.val('${param.toCity}');
	</c:if>
	
	countryTo.change(function() {
		let selectedCountry = $(this).children('option:selected').val();
		setCities(places[selectedCountry], citiesTo);
	});
});
</script>
	
</body>
</html>