<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="ua.nure.miroshnichenko.touragency.db.entity.HotelType"%>
<%@ page import="ua.nure.miroshnichenko.touragency.db.entity.Food"%>
<%@ page import="ua.nure.miroshnichenko.touragency.db.entity.Beach"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post" enctype="multipart/form-data" action="/TourAgency/controller?action=saveHotel&edit=${param.edit}">
		<h3>Hotel</h3>
		<div class="form-group">
			<label for="set-hotel-name"><fmt:message key="common.name" /></label>
			<input id="set-hotel-name" name="name" value="${hotel.name}" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-info"><fmt:message key="common.info" /></label>
			<textarea id="set-hotel-info" name="info" class="form-control" required>${hotel.info}</textarea>
		</div>
		<div class="form-group">
			<label for="set-hotel-address"><fmt:message key="common.address" /></label>
			<input id="set-hotel-address" name="address" value="${hotel.address}" class="form-control" required>
		</div>	
		<div class="form-group">
			<label for="set-hotel-tel"><fmt:message key="common.telephone" /></label>
			<input id="set-hotel-tel" name="tel" value="${hotel.tel}" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-site"><fmt:message key="common.site" /></label>
			<input id="set-hotel-site" name="site" value="${hotel.site}" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-stars"><fmt:message key="common.hotel_stars" /></label>
			<input id="set-hotel-stars" name="stars" value="${hotel.stars}" class="form-control" type="number" min="1" max="5" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-max-rooms"><fmt:message key="common.max_rooms" /></label>
			<input id="set-hotel-max-rooms" class="form-control" value="${hotel.maxRooms}" name="maxRooms" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-price"><fmt:message key="common.price" /></label>
			<input id="set-hotel-price" name="price" class="form-control" value="${hotel.price}" type="number" required>
		</div>
		<div class="form-group">
			<label for="select-hotel-type"><fmt:message key="common.hotel_type" /></label>
			<select id="select-hotel-type" name="type" class="form-control">
				<option value="MOTEL" <c:if test="${hotel.type eq HotelType.MOTEL}">selected</c:if>>
				<fmt:message key="common.motel" /></option>
				<option value="RESORTS" <c:if test="${hotel.type eq HotelType.RESORTS}">selected</c:if>>
				<fmt:message key="common.resort" /></option>
				<option value="HOSTEL" <c:if test="${hotel.type eq HotelType.HOSTEL}">selected</c:if>>
				<fmt:message key="common.hostel" /></option>
				<option value="BOUTIQUE" <c:if test="${hotel.type eq HotelType.BOUTIQUE}">selected</c:if>>
				<fmt:message key="common.boutique" /></option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-hotel-food"><fmt:message key="common.food" /></label>
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
			<label for="select-hotel-beach"><fmt:message key="common.beach" /></label>
			<select id="select-hotel-beach" name="beach" class="form-control">
				<option value="SAND" <c:if test="${hotel.beach eq Beach.SAND}">selected</c:if>>
				<fmt:message key="common.sand" /></option>
				<option value="PEBBLE" <c:if test="${hotel.beach eq Beach.PEBBLE}">selected</c:if>>
				<fmt:message key="common.pebble" /></option>
				<option value="PLATE" <c:if test="${hotel.beach eq Beach.PLATE}">selected</c:if>>
				<fmt:message key="common.plate" /></option>
				<option value="SAND_PEBBLE" <c:if test="${hotel.beach eq Beach.SAND_PEBBLE}">selected</c:if>>
				<fmt:message key="common.sand_pebble" /></option>
			</select>
		</div>
		<div class="d-flex flex-column">
			<h3><fmt:message key="common.in_room" /></h3>
			<div class="custom-control custom-checkbox my-1 mr-sm-2">
				<input class="custom-control-input" name="facilities" value="WIFI" type="checkbox" id="choose-falility-wifi"
				 <c:if test="${fn:contains(hotel.facilities, 'WIFI')}">checked</c:if>>
				<label for="choose-falility-wifi" class="custom-control-label">Wifi</label>
			</div>
			<div class="custom-control custom-checkbox my-1 mr-sm-2">
				<input class="custom-control-input" name="facilities" value="MINIBAR" type="checkbox" id="choose-falility-minibar"
				<c:if test="${fn:contains(hotel.facilities, 'MINIBAR')}">checked</c:if>>
				<label for="choose-falility-minibar" class="custom-control-label"><fmt:message key="common.minibar" /></label>
			</div>
			<div class="custom-control custom-checkbox my-1 mr-sm-2">
				<input class="custom-control-input" name="facilities" value="HAIRDRYER" type="checkbox" id="choose-falility-hairdryer"
				<c:if test="${fn:contains(hotel.facilities, 'HAIRDRYER')}">checked</c:if>>
				<label for="choose-falility-hairdryer" class="custom-control-label"><fmt:message key="common.hairdryer" /></label>
			</div>
			<div class="custom-control custom-checkbox my-1 mr-sm-2">
				<input class="custom-control-input" name="facilities" value="CONDITIONER" type="checkbox" id="choose-falility-conditioner"
				<c:if test="${fn:contains(hotel.facilities, 'CONDITIONER')}">checked</c:if>>
				<label for="choose-falility-conditioner" class="custom-control-label"><fmt:message key="common.conditioner" /></label>
			</div>
			<div class="custom-control custom-checkbox my-1 mr-sm-2">
				<input class="custom-control-input" name="facilities" value="TV" type="checkbox" id="choose-falility-tv"
				<c:if test="${fn:contains(hotel.facilities, 'TV')}">checked</c:if>>
				<label for="choose-falility-tv" class="custom-control-label">TV</label>
			</div>
		</div>
		<div class="d-flex flex-column mt-2">
			<h3><fmt:message key="common.sport" /></h3>
			<div class="custom-control custom-checkbox my-1 mr-sm-2">
				<input class="custom-control-input" name="servicings" value="GYM" type="checkbox" id="choose-sport-gym"
				<c:if test="${fn:contains(hotel.servicings, 'GYM')}">checked</c:if>>
				<label for="choose-sport-gym" class="custom-control-label"><fmt:message key="common.gym" /></label>
			</div>
			<div class="custom-control custom-checkbox my-1 mr-sm-2">
				<input class="custom-control-input" name="servicings" value="FITNES" type="checkbox" id="choose-sport-fitnes"
				<c:if test="${fn:contains(hotel.servicings, 'FITNES')}">checked</c:if>>
				<label for="choose-sport-fitnes" class="custom-control-label"><fmt:message key="common.fitnes" /></label>
			</div>
			<div class="custom-control custom-checkbox my-1 mr-sm-2">
				<input class="custom-control-input" name="servicings" value="DIVING" type="checkbox" id="choose-sport-diving"
				<c:if test="${fn:contains(hotel.servicings, 'DIVING')}">checked</c:if>>
				<label for="choose-sport-diving" class="custom-control-label"><fmt:message key="common.diving" /></label>
			</div>
			<div class="custom-control custom-checkbox my-1 mr-sm-2">
				<input class="custom-control-input" name="servicings" value="YOGA" type="checkbox" id="choose-sport-yoga"
				<c:if test="${fn:contains(hotel.servicings, 'YOGA')}">checked</c:if>>
				<label for="choose-sport-yoga" class="custom-control-label"><fmt:message key="common.yoga" /></label>
			</div>
			<div class="d-flex flex-column mt-2">
				<h3><fmt:message key="common.entertainment" /></h3>
				<div class="custom-control custom-checkbox my-1 mr-sm-2">
					<input class="custom-control-input" name="servicings" value="AQUAPARK" type="checkbox" id="choose-entertainment-aqupark"
					<c:if test="${fn:contains(hotel.servicings, 'AQUAPARK')}">checked</c:if>>
					<label for="choose-entertainment-aqupark" class="custom-control-label"><fmt:message key="common.aquapark" /></label>
				</div>
				<div class="custom-control custom-checkbox my-1 mr-sm-2">
					<input class="custom-control-input" name="servicings" value="DISCO" type="checkbox" id="choose-entertainment-disco"
					<c:if test="${fn:contains(hotel.servicings, 'DISCO')}">checked</c:if>>
					<label for="choose-entertainment-disco" class="custom-control-label"><fmt:message key="common.disco" /></label>
				</div>
				<div class="custom-control custom-checkbox my-1 mr-sm-2">
					<input class="custom-control-input" name="servicings" value="RESTAURANT" type="checkbox" id="choose-entertainment-restaurant"
					<c:if test="${fn:contains(hotel.servicings, 'RESTAURANT')}">checked</c:if>>
					<label for="choose-entertainment-restaurant" class="custom-control-label"><fmt:message key="common.restaurant" /></label>
				</div>
			</div>
			<c:if test="${param.edit eq 'true'}">
				<div class="d-flex flex-column mt-2">
					<c:forEach items="${photos}" var="photo">
						<div class="d-flex-inline">
							<img src="/TourAgency/photo/${photo}" class="edit-hotel-photo rounded mx-auto d-block mr-2 mt-5 mb-5 float-left" alt="Hotel photo">
							<button class="btn btn-danger btn-delete-photo delete ml-2 mt-5 mb-5"><fmt:message key="common.delete" /></button>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="choose-photos"><fmt:message key="admin_jsp.choose_photos" /></label>
			<input type="file" id="choose-photos" class="form-control-file" name="photo" multiple />		
		</div>
		<c:if test="${param.edit eq 'true'}">
			<input type="hidden" name="id" value="${hotel.id}">
		</c:if>
		<button class="btn btn-primary mt-3" type="submit"><fmt:message key="common.save" /></button>
	</form>

	<script src="js/jquery-3.4.1.min.js"></script>
	
	<script>
		$(document).ready(() => {
			$('.btn-delete-photo').click(function (e) {
				e.preventDefault();
				
				let parent = $(this).parent();
				let deletedPhoto = parent.children('.edit-hotel-photo');
				let deletedPhotoInput = parent.append(
						'<input type="hidden" name="deletedPhotos" value="' + deletedPhoto.attr('src').split('/')[3] + '" />'
				);
				
				deletedPhoto.remove();
				$(this).remove();
			});
		});
	</script>
</body>
</html>