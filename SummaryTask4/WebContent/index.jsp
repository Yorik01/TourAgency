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
			    <input class="form-control start_date" type="text" placeholder="<fmt:message key="common.start_date" />" name="startDate"  id="startdate_datepicker">
			    <div class="input-group-append">
			      <span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    </div>
			
			  </div>
			  <div class="end_date input-group ml-3 mr-3">
			    <input class="form-control end_date" type="text" name="endDate" placeholder="<fmt:message key="common.end_date" />" id="enddate_datepicker">
			    <div class="input-group-append">
			      <span class="fa fa-calendar input-group-text end_date_calendar" aria-hidden="true "></span>
			    </div>
			  </div>
			</div>
       		<div>
				<select class="form-control" name="peopleCount">
					<option value="1" selected>1</option>
					<option value="2" selected>2</option>
					<option value="3" selected>3</option>
					<option value="4" selected>4</option>
					<option value="5" selected>5</option>
					<option value="6" selected>6</option>
					<option value="7" selected>7</option>
					<option value="8" selected>8</option>
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
						    <input type="checkbox" class="custom-control-input" name="stars" value="1" id="star-1">
						    <label class="custom-control-label" for="star-1">1</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" name="stars" value="2" id="star-2">
						    <label class="custom-control-label" for="star-2">2</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" name="stars" value="3" id="star-3">
						    <label class="custom-control-label" for="star-3">3</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" name="stars" value="4" id="star-4">
						    <label class="custom-control-label" for="star-4">4</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" name="stars" value="5" id="star-5">
						    <label class="custom-control-label" for="star-5">5</label>
						</div>
					</div>
				</div>
				<div id="hotel-price">
					<h5><fmt:message key="index_jsp.max_price" /></h5>
					<div class="custom-control custom-range">
						<label for="hotel-max-price" id="hotel-max-price-label">10000</label>
						<input type="range" min="200" max="100000" value="10000" name="maxPrice" class="custom-range" id="hotel-max-price">
					</div>
				</div>
				<div id="hotel-transport-type" class="d-flex flex-column">
					<h5><fmt:message key="common.transport_type" /></h5>
					<div class="custom-control custom-checkbox my-1 mr-sm-2">
						<input type="checkbox" class="custom-control-input" name="transportType" value="AIRPLANE" id="transport-air">
						<label class="custom-control-label" for="transport-air"><fmt:message key="common.plane" /></label>
					</div>
					<div class="custom-control custom-checkbox my-1 mr-sm-2">
						<input type="checkbox" class="custom-control-input" name="transportType" value="BUS" id="transport-bus">
						<label class="custom-control-label" for="transport-bus"><fmt:message key="common.bus" /></label>
					</div>
				</div>
			</div>
			<hr/>
			<div class="d-inline-flex justify-content-between">
				<div id="tour-type">
					<h5><fmt:message key="common.tour_type" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="tourType" value="RELAX" id="tour-type-relax">
							<label class="custom-control-label" for="tour-type-relax"><fmt:message key="common.relax" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="tourType" value="EXCURSION" id="tour-type-excursion">
							<label class="custom-control-label" for="tour-type-excursion"><fmt:message key="common.excursion" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="tourType" value="SHOPPING" id="tour-type-shopping">
							<label class="custom-control-label" for="tour-type-shopping"><fmt:message key="common.shopping" /></label>
						</div>
					</div>
				</div>
				<div id="hotel-food-type">
					<h5><fmt:message key="common.food" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="AL" id="food-al">
							<label class="custom-control-label" for="food-al">AL</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="FB" id="food-fb">
							<label class="custom-control-label" for="food-fb">FB</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="HB" id="food-hb">
							<label class="custom-control-label" for="food-hb">HB</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="BB" id="food-bb">
							<label class="custom-control-label" for="food-bb">BB</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="food" value="OB" id="food-ob">
							<label class="custom-control-label" for="food-ob">OB</label>
						</div>
					</div>
				</div>
				<div id="hotel-beach-type">
					<h5><fmt:message key="common.beach" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="SAND" id="beach-sand">
							<label class="custom-control-label" for="beach-sand"><fmt:message key="common.sand" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="PEBBLE" id="beach-pebble">
							<label class="custom-control-label" for="beach-pebble"><fmt:message key="common.pebble" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="PLATE" id="beach-plate">
							<label class="custom-control-label" for="beach-plate"><fmt:message key="common.plate" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="SAND_PEBBLE" id="beach-sand-pebble">
							<label class="custom-control-label" for="beach-sand-pebble"><fmt:message key="common.sand_pebble" /></label>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<div class="d-inline-flex justify-content-between">
				<div id="hotel-facilities" class="pr-2">
					<h5><fmt:message key="common.in_room" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="WIFI" id="facility-wifi">
							<label class="custom-control-label" for="facility-wifi">wifi</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="MINIBAR" id="facility-minibar">
							<label class="custom-control-label" for="facility-minibar"><fmt:message key="common.mini_bar" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="HAIRDRYER" id="facility-hairdryer">
							<label class="custom-control-label" for="facility-hairdryer"><fmt:message key="common.hairdryer" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="CONDITIONER" id="facility-conditioner">
							<label class="custom-control-label" for="facility-conditioner"><fmt:message key="common.conditioner" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="TV" id="facility-tv">
							<label class="custom-control-label" for="facility-tv">TV</label>
						</div>
					</div>
				</div>
				<div id="hotel-sport" class="pr-2">
					<h5><fmt:message key="common.sport" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="GYM" id="hotel-sport-gym">
							<label class="custom-control-label" for="hotel-sport-gym"><fmt:message key="common.gym" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="FITNES" id="hotel-sport-fitnes">
							<label class="custom-control-label" for="hotel-sport-fitnes"><fmt:message key="common.fitnes" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="DIVING" id="hotel-sport-diving">
							<label class="custom-control-label" for="hotel-sport-diving"><fmt:message key="common.diving" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="YOGA" id="hotel-sport-yoga">
							<label class="custom-control-label" for="hotel-sport-yoga"><fmt:message key="common.yoga" /></label>
						</div>
					</div>
				</div>
				<div id="hotel-entartainment" class="pr-2">
					<h5><fmt:message key="common.entertainment" /></h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="AQUAPARK" id="hotel-entartainment-aquapark">
							<label class="custom-control-label" for="hotel-entartainment-aquapark"><fmt:message key="common.aquapark" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="DISCO" id="hotel-entartainment-disco">
							<label class="custom-control-label" for="hotel-entartainment-disco"><fmt:message key="common.disco" /></label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="RESTAURANT" id="hotel-entartainment-restaurant">
							<label class="custom-control-label" for="hotel-entartainment-restaurant"><fmt:message key="common.restaurant" /></label>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="action" value="filterTours">
	</form>
		
	<div id="tour-all" class="p-3">
		<h1 class="mb-2"><fmt:message key="common.tours" /></h1>
		<c:forEach items="${requestScope.tours}" var="tour">
			<t:tourUserCard
				tour="${tour.key}" 
				photo="${tour.value}" />
		</c:forEach>
	</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
	
<script src="js/route.js"></script>
<script>
$(function () {
	$("#startdate_datepicker").datepicker();
	$("#enddate_datepicker").datepicker();
	
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
	
	selectedCountry = countryTo.children('option:selected').val();
	setCities(places[selectedCountry], citiesTo);
	
	countryTo.change(function() {
		let selectedCountry = $(this).children('option:selected').val();
		setCities(places[selectedCountry], citiesTo);
	});
});
</script>
	
</body>
</html>