<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="title" value="Error" scope="page" />
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<link href="font/font-awesome/css/all.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	
	<title>Tours</title>
</head>
<body>
	<form id="tour-filter" class="container-fluid form-group mt-5" action="/TourAgency/controller" method="get">
		<h1 class="mb-5">Searching of tours</h1>
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
			    <input class="form-control start_date" type="text" placeholder="start date" name="startDate"  id="startdate_datepicker">
			    <div class="input-group-append">
			      <span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    </div>
			
			  </div>
			  <div class="end_date input-group ml-3 mr-3">
			    <input class="form-control end_date" type="text" name="endDate" placeholder="end date" id="enddate_datepicker">
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
			<button class="btn btn-primary" type="submit">Seacrh</button>
		</div>
		<div id="tour-filter-extended" class="shadow-sm mt-3 p-3 bg-white rounded d-flex flex-column justify-content-around">
			<div class="d-inline-flex justify-content-between">
				<div id="hotel-stars">
					<h5>Hotel stars</h5>
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
					<h5>Max Price</h5>
					<div class="custom-control custom-range">
						<label for="hotel-max-price" id="hotel-max-price-label">10000</label>
						<input type="range" min="200" max="100000" value="10000" name="maxPrice" class="custom-range" id="hotel-max-price">
					</div>
				</div>
				<div id="hotel-transport-type" class="d-flex flex-column">
					<h5>Transport type</h5>
					<div class="custom-control custom-checkbox my-1 mr-sm-2">
						<input type="checkbox" class="custom-control-input" name="transportType" value="AIRPLANE" id="transport-air">
						<label class="custom-control-label" for="transport-air">Airplane</label>
					</div>
					<div class="custom-control custom-checkbox my-1 mr-sm-2">
						<input type="checkbox" class="custom-control-input" name="transportType" value="BUS" id="transport-bus">
						<label class="custom-control-label" for="transport-bus">Bus</label>
					</div>
				</div>
			</div>
			<hr/>
			<div class="d-inline-flex justify-content-between">
				<div id="tour-type">
					<h5>Tour type</h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="tourType" value="RELAX" id="tour-type-relax">
							<label class="custom-control-label" for="tour-type-relax">Relax</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="tourType" value="EXCURSION" id="tour-type-excursion">
							<label class="custom-control-label" for="tour-type-excursion">Excursion</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="tourType" value="SHOPPING" id="tour-type-shopping">
							<label class="custom-control-label" for="tour-type-shopping">Shopping</label>
						</div>
					</div>
				</div>
				<div id="hotel-food-type">
					<h5>Food type</h5>
					<div class="d-inline-flex">
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
					<h5>Beach type</h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="SAND" id="beach-sand">
							<label class="custom-control-label" for="beach-sand">Sand</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="PEBBLE" id="beach-pebble">
							<label class="custom-control-label" for="beach-pebble">Pebble</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="PLATE" id="beach-plate">
							<label class="custom-control-label" for="beach-plate">Plate</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="beach" value="SAND_PEBBLE" id="beach-sand-pebble">
							<label class="custom-control-label" for="beach-sand-pebble">Sand and pebble</label>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<div class="d-inline-flex justify-content-between">
				<div id="hotel-facilities" class="pr-2">
					<h5>In room</h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="WIFI" id="facility-wifi">
							<label class="custom-control-label" for="facility-wifi">Wi-Fi</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="MINIBAR" id="facility-minibar">
							<label class="custom-control-label" for="facility-minibar">Mini bar</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="HAIRDRYER" id="facility-hairdryer">
							<label class="custom-control-label" for="facility-hairdryer">Hairdryer</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="CONDITIONER" id="facility-conditioner">
							<label class="custom-control-label" for="facility-conditioner">Conditioner</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="facility" value="TV" id="facility-tv">
							<label class="custom-control-label" for="facility-tv">TV</label>
						</div>
					</div>
				</div>
				<div id="hotel-sport" class="pr-2">
					<h5>Sport</h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="GYM" id="hotel-sport-gym">
							<label class="custom-control-label" for="hotel-sport-gym">Gym</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="FITNES" id="hotel-sport-fitnes">
							<label class="custom-control-label" for="hotel-sport-fitnes">Fitnes</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="DIVING" id="hotel-sport-diving">
							<label class="custom-control-label" for="hotel-sport-diving">Diving</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="YOGA" id="hotel-sport-yoga">
							<label class="custom-control-label" for="hotel-sport-yoga">Yoga</label>
						</div>
					</div>
				</div>
				<div id="hotel-entartainment" class="pr-2">
					<h5>Entertainment</h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="AQUAPARK" id="hotel-entartainment-aquapark">
							<label class="custom-control-label" for="hotel-entartainment-aquapark">Aquapark</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="DISCO" id="hotel-entartainment-disco">
							<label class="custom-control-label" for="hotel-entartainment-disco">Disco</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" name="servicing" value="RESTAURANT" id="hotel-entartainment-restaurant">
							<label class="custom-control-label" for="hotel-entartainment-restaurant">Restaurant</label>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="action" value="filterTour">
	</form>
		
	<div id="tour-all" class="p-3">
		<h1 class="mb-2">All tours</h1>
		<c:forEach items="${requestScope.tours}" var="tour">
			<t:tourUserCard 
			id="${tour.id}"
			title="${tour.hotel.name}" 
			info="${tour.hotel.info}"
			photo=""
			fired="${tour.isFired()}"/>
		</c:forEach>
	</div>

<script src="bootstrap/js/moment.min.js"></script>	
<script src="js/jquery-3.4.1.min.js" ></script>
<script src="js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/moment-with-locales.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.min.js"></script>
	
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