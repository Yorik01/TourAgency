<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="ua.nure.miroshnichenko.touragency.db.entity.HotelType"%>
<%@ page import="ua.nure.miroshnichenko.touragency.db.entity.Food"%>
<%@ page import="ua.nure.miroshnichenko.touragency.db.entity.Beach"%>

 <%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post" enctype="multipart/form-data" action="/TourAgency/controller?action=saveHotel&edit=${param.edit}">
		<div class="form-group row">
			<label for="set-hotel-name">Name</label>
			<input id="set-hotel-name" name="name" value="${hotel.name}" class="form-control" required>
		</div>
		<div class="form-group row">
			<label for="set-hotel-info">Info</label>
			<textarea id="set-hotel-info" name="info" class="form-control" required>${hotel.info}</textarea>
		</div>
		<div class="form-group row">
			<label for="set-hotel-address">Address</label>
			<input id="set-hotel-address" name="address" value="${hotel.address}" class="form-control" required>
		</div>	
		<div class="form-group">
			<label for="set-hotel-tel">Telephone</label>
			<input id="set-hotel-tel" name="tel" value="${hotel.tel}" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-site">Site</label>
			<input id="set-hotel-site" name="site" value="${hotel.site}" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-stars">Stars</label>
			<input id="set-hotel-stars" name="stars" value="${hotel.stars}" class="form-control" type="number" minValue="1" maxValue="5" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-max-rooms">Max count of rooms</label>
			<input id="set-hotel-max-rooms" class="form-control" value="${hotel.maxRooms}" name="maxRooms" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-price">Price</label>
			<input id="set-hotel-price" name="price" class="form-control" value="${hotel.price}" type="number" required>
		</div>
		<div class="form-group">
			<label for="select-hotel-type">Hotel type</label>
			<select id="select-hotel-type" name="type" class="form-control">
				<option value="MOTEL" <c:if test="${hotel.type eq HotelType.MOTEL}">selected</c:if>>
				motel</option>
				<option value="RESORTS" <c:if test="${hotel.type eq HotelType.RESORTS}">selected</c:if>>
				resorts</option>
				<option value="HOSTEL" <c:if test="${hotel.type eq HotelType.HOSTEL}">selected</c:if>>
				hostel</option>
				<option value="BOUTIQUE" <c:if test="${hotel.type eq HotelType.BOUTIQUE}">selected</c:if>>
				boutique</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-hotel-food">Type of food</label>
			<select id="select-hotel-food" name="food" class="form-control">
				<option value="AL" <c:if test="${hotel.food eq Food.AL}">selected</c:if>>
				AL</option>
				<option value="FB" <c:if test="${hotel.food eq Food.FB}">selected</c:if>>
				FB</option>
				<option value="HB" <c:if test="${hotel.food eq Food.HB}">selected</c:if>>
				HB</option>
				<option value="BB" <c:if test="${hotel.food eq Food.BB}">selected</c:if>>
				BB</option>
				<option value="OB" <c:if test="${hotel.food eq Food.OB}">selected</c:if>>
				OB</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-hotel-beach">Type of beach</label>
			<select id="select-hotel-beach" name="beach" class="form-control">
				<option value="SAND" <c:if test="${hotel.beach eq Beach.SAND}">selected</c:if>>
				sand</option>
				<option value="PEBBLE" <c:if test="${hotel.beach eq Beach.PEBBLE}">selected</c:if>>
				pebble</option>
				<option value="PLATE" <c:if test="${hotel.beach eq Beach.PLATE}">selected</c:if>>
				plate</option>
				<option value="SAND_PEBBLE" <c:if test="${hotel.beach eq Beach.SAND_PEBBLE}">selected</c:if>>
				sand and pebble</option>
			</select>
		</div>
		<div class="d-flex flex-column">
			<h3>Facilities</h3>
			<div class="form-group">
				<label for="choose-falility-wifi">Wifi</label>
				<input class="form-check-input form-control" name="facilities" value="WIFI" type="checkbox" id="choose-falility-wifi"
				 <c:if test="${fn:contains(hotel.facilities, 'WIFI')}">checked</c:if>>
			</div>
			<div class="form-group">
				<label for="choose-falility-minibar">Minibar</label>
				<input class="form-check-input form-control" name="facilities" value="MINIBAR" type="checkbox" id="choose-falility-minibar"
				<c:if test="${fn:contains(hotel.facilities, 'MINIBAR')}">checked</c:if>>
			</div>
			<div class="form-group">
				<label for="choose-falility-hairdryer">Hairdryer</label>
				<input class="form-check-input form-control" name="facilities" value="HAIRDRYER" type="checkbox" id="choose-falility-hairdryer"
				<c:if test="${fn:contains(hotel.facilities, 'HAIRDRYER')}">checked</c:if>>
			</div>
			<div class="form-group">
				<label for="choose-falility-conditioner">Conditioner</label>
				<input class="form-check-input form-control" name="facilities" value="CONDITIONER" type="checkbox" id="choose-falility-conditioner"
				<c:if test="${fn:contains(hotel.facilities, 'CONDITIONER')}">checked</c:if>>
			</div>
			<div class="form-group">
				<label for="choose-falility-tv">TV</label>
				<input class="form-check-input form-control" name="facilities" value="TV" type="checkbox" id="choose-falility-tv"
				<c:if test="${fn:contains(hotel.facilities, 'TV')}">checked</c:if>>
			</div>
		</div>
		<div class="d-flex flex-column">
			<h3>Facilities</h3>
			<h4>Sport</h4>
			<div class="form-group">
				<label for="choose-sport-gym">Gym</label>
				<input class="form-check-input form-control" name="servicings" value="GYM" type="checkbox" id="choose-sport-gym"
				<c:if test="${fn:contains(hotel.servicings, 'GYM')}">checked</c:if>>
			</div>
			<div class="form-group">
				<label for="choose-sport-fitnes">Fitnes</label>
				<input class="form-check-input form-control" name="servicings" value="FITNES" type="checkbox" id="choose-sport-fitnes"
				<c:if test="${fn:contains(hotel.servicings, 'FITNES')}">checked</c:if>>
			</div>
			<div class="form-group">
				<label for="choose-sport-diving">Diving</label>
				<input class="form-check-input form-control" name="servicings" value="DIVING" type="checkbox" id="choose-sport-diving"
				<c:if test="${fn:contains(hotel.servicings, 'DIVING')}">checked</c:if>>
			</div>
			<div class="form-group">
				<label for="choose-sport-yoga">Yoga</label>
				<input class="form-check-input form-control" name="servicings" value="YOGA" type="checkbox" id="choose-sport-yoga"
				<c:if test="${fn:contains(hotel.servicings, 'YOGA')}">checked</c:if>>
			</div>
			<h4>Entertainment</h4>
			<div class="form-group">
				<label for="choose-entertainment-aquapark">Aquapark</label>
				<input class="form-check-input form-control" name="servicings" value="AQUAPARK" type="checkbox" id="choose-entertainment-aqupark"
				<c:if test="${fn:contains(hotel.servicings, 'AQUAPARK')}">checked</c:if>>
			</div>
			<div class="form-group">
				<label for="choose-entertainment-disco">Disco</label>
				<input class="form-check-input form-control" name="servicings" value="DISCO" type="checkbox" id="choose-entertainment-disco"
				<c:if test="${fn:contains(hotel.servicings, 'DISCO')}">checked</c:if>>
			</div>
			<div class="form-group">
				<label for="choose-entertainment-restaurant">Restaurant</label>
				<input class="form-check-input form-control" name="servicings" value="RESTAURANT" type="checkbox" id="choose-entertainment-restaurant"
				<c:if test="${fn:contains(hotel.servicings, 'RESTAURANT')}">checked</c:if>>
			</div>
		</div>
		<input type="file" name="photo" multiple />		
		<c:if test="${param.edit eq 'true'}">
			<input type="hidden" name="id" value="${hotel.id}">
		</c:if>
		<button class="btn btn-primary" type="submit">Save</button>
	</form>
</body>
</html>