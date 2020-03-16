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
	<form id="tour-filter" class="container-fluid form-group mt-5">
		<h1 class="mb-5">Searching of tours</h1>
		<div id="tour-filter-main" class="shadow-sm p-3 bg-white rounded d-flex flex-row justify-content-around">
			<div>
				<select class="form-control">
					<option selected>Choose...</option>
					<option>...</option>
      			</select>
   			</div>
			<div>
				<select class="form-control">
					<option selected>Choose...</option>
					<option>...</option>
      			</select>
   			</div>
			<div>
				<select class="form-control">
					<option selected>Choose...</option>
					<option>...</option>
      			</select>
   			</div>
			<div class="dates d-inline-flex">
			  <div class="start_date input-group ml-3">
			    <input class="form-control start_date" type="text" placeholder="start date" class="startdate_datepicker">
			    <div class="input-group-append">
			      <span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    </div>
			
			  </div>
			  <div class="end_date input-group ml-3 mr-3">
			    <input class="form-control end_date" type="text" placeholder="end date" class="enddate_datepicker">
			    <div class="input-group-append">
			      <span class="fa fa-calendar input-group-text end_date_calendar" aria-hidden="true "></span>
			    </div>
			  </div>
			</div>
       		<div>
				<select class="form-control">
					<option selected>Choose...</option>
					<option>...</option>
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
						    <input type="checkbox" class="custom-control-input" id="star-1">
						    <label class="custom-control-label" for="star-1">1</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" id="star-2">
						    <label class="custom-control-label" for="star-2">2</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" id="star-3">
						    <label class="custom-control-label" for="star-3">3</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" id="star-4">
						    <label class="custom-control-label" for="star-4">4</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
						    <input type="checkbox" class="custom-control-input" id="star-5">
						    <label class="custom-control-label" for="star-5">5</label>
						</div>
					</div>
				</div>
				<div id="hotel-price">
					<h5>Max Price</h5>
					<div class="custom-control custom-range">
						<label for="hotel-max-price" id="hotel-max-price-label">10000</label>
						<input type="range" min="200" max="100000" value="10000" class="custom-range" id="hotel-max-price">
					</div>
				</div>
				<div id="hotel-transport-type" class="d-flex flex-column">
					<h5>Transport type</h5>
					<div class="custom-control custom-checkbox my-1 mr-sm-2">
						<input type="checkbox" class="custom-control-input" id="transport-air">
						<label class="custom-control-label" for="transport-air">airplane</label>
					</div>
					<div class="custom-control custom-checkbox my-1 mr-sm-2">
						<input type="checkbox" class="custom-control-input" id="transport-bus">
						<label class="custom-control-label" for="transport-bus">bus</label>
					</div>
				</div>
			</div>
			<hr/>
			<div class="d-inline-flex justify-content-between">
				<div id="tour-type">
					<h5>Tour type</h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="tour-type-relax">
							<label class="custom-control-label" for="tour-type-relax">relax</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="tour-type-excursion">
							<label class="custom-control-label" for="tour-type-excursion">excursion</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="tour-type-shopping">
							<label class="custom-control-label" for="tour-type-shopping">shopping</label>
						</div>
					</div>
				</div>
				<div id="hotel-food-type">
					<h5>Food type</h5>
					<div class="d-inline-flex">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="food-al">
							<label class="custom-control-label" for="food-al">AL</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="food-fb">
							<label class="custom-control-label" for="food-fb">FB</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="food-hb">
							<label class="custom-control-label" for="food-hb">HB</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="food-bb">
							<label class="custom-control-label" for="food-bb">BB</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="food-ob">
							<label class="custom-control-label" for="food-ob">OB</label>
						</div>
					</div>
				</div>
				<div id="hotel-beach-type">
					<h5>Beach type</h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="beach-sand">
							<label class="custom-control-label" for="beach-sand">sand</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="beach-pebble">
							<label class="custom-control-label" for="beach-pebble">pebble</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="beach-plate">
							<label class="custom-control-label" for="beach-plate">plate</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="beach-sand-pebble">
							<label class="custom-control-label" for="beach-sand-pebble">sand and pebble</label>
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
							<input type="checkbox" class="custom-control-input" id="facility-wifi">
							<label class="custom-control-label" for="facility-wifi">wifi</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="facility-minibar">
							<label class="custom-control-label" for="facility-minibar">mini bar</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="facility-hairdryer">
							<label class="custom-control-label" for="facility-hairdryer">hairdryer</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="facility-conditioner">
							<label class="custom-control-label" for="facility-conditioner">conditioner</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="facility-tv">
							<label class="custom-control-label" for="facility-tv">tv</label>
						</div>
					</div>
				</div>
				<div id="hotel-sport" class="pr-2">
					<h5>Sport</h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="hotel-sport-gym">
							<label class="custom-control-label" for="hotel-sport-gym">gym</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="hotel-sport-fitnes">
							<label class="custom-control-label" for="hotel-sport-fitnes">fitnes</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="hotel-sport-diving">
							<label class="custom-control-label" for="hotel-sport-diving">diving</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="hotel-sport-yoga">
							<label class="custom-control-label" for="hotel-sport-yoga">yoga</label>
						</div>
					</div>
				</div>
				<div id="hotel-entartainment" class="pr-2">
					<h5>Entertainment</h5>
					<div class="d-flex flex-column">
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="hotel-entartainment-aquapark">
							<label class="custom-control-label" for="hotel-entartainment-aquapark">aquapark</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="hotel-entartainment-disco">
							<label class="custom-control-label" for="hotel-entartainment-disco">disco</label>
						</div>
						<div class="custom-control custom-checkbox my-1 mr-sm-2">
							<input type="checkbox" class="custom-control-input" id="hotel-entartainment-restaurant">
							<label class="custom-control-label" for="hotel-entartainment-restaurant">restaurant</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="tour-fired" class="p-3">
	<h1 class="mb-2">Fired tours</h1>
		<div class="tour-cards">
			<div class="card tour-card">
  				<img class="card-img-top" src="https://q-cf.bstatic.com/images/hotel/max1024x768/681/68184730.jpg" alt="Card image cap">
				  <div class="card-body">
			  		<h5 class="card-title">Tour name <i class="fa fa-fire"></i></h5>
			  		<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
			   		<a href="#" class="btn btn-primary">Look</a>
 			 	</div>
			</div>
		</div>
	</div>

<script src="bootstrap/js/moment.js"></script>	
<script src="js/jquery-3.4.1.min.js" ></script>
<script src="js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/moment-with-locales.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.min.js"></script>
	
<script>
$(function () {
	$("#startdate_datepicker").datepicker();
	$("#enddate_datepicker").datepicker();
	
	let hotelMaxPriceLabel = $("#hotel-max-price-label");
	$("#hotel-max-price").on("input change", function() {
		hotelMaxPriceLabel.text($(this).val());
	});
});
</script>
	
</body>
</html>