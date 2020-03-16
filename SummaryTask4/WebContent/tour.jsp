<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

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
<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<div class="d-inline-flex w-100">
		<div id="tour-photos" class="carousel slide ml-5" data-ride="carousel">
		  <ol class="carousel-indicators">
		    <li data-target="#tour-photos" data-slide-to="0" class="active"></li>
		    <li data-target="#tour-photos" data-slide-to="1"></li>
		    <li data-target="#tour-photos" data-slide-to="2"></li>
		  </ol>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img class="d-block w-100" src="https://q-cf.bstatic.com/images/hotel/max1024x768/681/68184730.jpg" alt="First slide">
		    </div>
		    <div class="carousel-item">
		      <img class="d-block w-100" src="https://q-cf.bstatic.com/images/hotel/max1024x768/681/68184730.jpg" alt="Second slide">
		    </div>
		    <div class="carousel-item">
		      <img class="d-block w-100" src="https://q-cf.bstatic.com/images/hotel/max1024x768/681/68184730.jpg" alt="Third slide">
		    </div>
		  </div>
		  <a class="carousel-control-prev" href="#tour-photos" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#tour-photos" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
		<div id="tour-main-info" class="d-flex flex-column">
			<span class="d-flex flex-column">
				<span id="total-price-label" class="text-secondary">total <i class="fa fa-fire"></i></span>
				<span id="tour-price">17658 $</span>
			</span>
			<button id="btn-reserve" class="btn btn-primary">Reserve</button>
			<div class="mt-2">
				<hr>
				<span>
					<span class="tour-info-name mr-2">Country:</span>
					<span class="tour-info-value">Egypt</span>
				</span>
				<hr>
				<span>
					<span class="tour-info-name mr-2">City:</span>
					<span class="tour-info-value">Kair</span>
				</span>
				<hr>
				<span>
					<span class="tour-info-name mr-2">From:</span>
					<span class="tour-info-value">Kharkov</span>
				</span>
				<hr>
				<span>
					<span class="tour-info-name mr-2">Date start:</span>
					<span class="tour-info-value">18.09.2020</span>
				</span>
				<hr>
				<span>
					<span class="tour-info-name mr-2">Date end:</span>
					<span class="tour-info-value">28.09.2020</span>
				</span>
				<hr>
				<span>
					<span class="tour-info-name mr-2">Tour type:</span>
					<span class="tour-info-value">Relaxing</span>
				</span>
			</div>
		</div>
	</div>
	<hr>
	<div id="transport-info">
		<h3>Transport</h3>
		<h4 class="mt-3">Ticket to</h4>
		<div class="ml-5">
			<span>
				<span class="tour-info-name mr-2">Transport type:</span>
				<span class="tour-info-value">airplane</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Arriving time: </span>
				<span class="tour-info-value">13:09</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Taking off time: </span>
				<span class="tour-info-value">13:09</span>
			</span>
			<hr>
		</div>
		<h4>Ticket back</h4>
		<div class="ml-5">
			<span>
				<span class="tour-info-name mr-2">Transport type:</span>
				<span class="tour-info-value">airplane</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Arriving time: </span>
				<span class="tour-info-value">13:09</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Taking off time: </span>
				<span class="tour-info-value">13:09</span>
			</span>
			<hr>
		</div>
	</div>
	<div id="tour-info">
		<h3>Hotel</h3>
		<p class="ml-5">Небольшой экономичный отель, который находится в районе Hadaba. Открыт в 2004 году, последняя реновация проведена — 2013 году. В отеле по мере необходимости проводится косметический ремонт. Состоит из основного здания и двухэтажных корпусов. В шаговой доступности находится El Mercato — чудесный район с сувенирными магазинами, ресторанами, кафе и кальянными. Невдалеке — один из трех знаменитых рыбных ресторанов Fares Seafood. А любителей восточного колорита порадует близкое расположение к Старому Городу (Old Market). Отель подходит для спокойного экономичного отдыха с семьей или друзьями.</p>
		<hr>
		<div class="ml-5">
			<span>
				<span class="tour-info-name mr-2">Name:</span>
				<span class="tour-info-value">Olkd</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Address:</span>
				<span class="tour-info-value">Olkd</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Site:</span>
				<span class="tour-info-value">Olkd</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Telephone:</span>
				<span class="tour-info-value">Olkd</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Stars:</span>
				<span class="tour-info-value">5</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Max rooms:</span>
				<span class="tour-info-value">4566</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Food:</span>
				<span class="tour-info-value">Al</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Beach:</span>
				<span class="tour-info-value">Sand</span>
			</span>
			<hr>
			<span>
				<span class="tour-info-name mr-2">Hotel type:</span>
				<span class="tour-info-value">Resorts</span>
			</span>
			<hr>
			<div class="d-inline-flex justify-content-between w-100">
				<div id="facility-info">
					<h5>In room</h5>
					<ul>
						<li>wifi</li>
						<li>minibar</li>
						<li>contitioner</li>
						<li>tv</li>
					</ul>
				</div>
				<div id="sport-info">
					<h5>Sport</h5>
					<ul>
						<li>gym</li>
						<li>diving</li>
						<li>yoga</li>
						<li>tv</li>
					</ul>
				</div>
				<div id="entertainment" class="mr-3">
					<h5>Entertainment</h5>
					<ul>
						<li>aquapark</li>
						<li>disco</li>
						<li>restaurant</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<script src="js/jquery-3.4.1.min.js" ></script>
<script src="js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>