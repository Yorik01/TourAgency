<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post" action="/SummaryTask4/controller?action=addHotel">
		<div class="form-group row">
			<label for="set-hotel-name">Name</label>
			<input id="set-hotel-name" name="name" class="form-control" required>
		</div>
		<div class="form-group row">
			<label for="set-hotel-info">Info</label>
			<textarea id="set-hotel-info" name="info" class="form-control" required></textarea>
		</div>
		<div class="form-group row">
			<label for="set-hotel-address">Address</label>
			<input id="set-hotel-address" name="address" class="form-control" required>
		</div>	
		<div class="form-group">
			<label for="set-hotel-tel">Telephone</label>
			<input id="set-hotel-tel" name="tel" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-site">Site</label>
			<input id="set-hotel-site" name="site" class="form-control" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-stars">Stars</label>
			<input id="set-hotel-stars" name="stars" class="form-control" type="number" minValue="1" maxValue="5" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-max-rooms">Max count of rooms</label>
			<input id="set-hotel-max-rooms" class="form-control" name="maxRooms" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-price">Price</label>
			<input id="set-hotel-price" name="price" class="form-control" type="number" required>
		</div>
		<div class="form-group">
			<label for="select-hotel-type">Hotel type</label>
			<select id="select-hotel-type" name="type" class="form-control">
				<option value="MOTEL">motel</option>
				<option value="RESORTS">resorts</option>
				<option value="HOSTEL">hostel</option>
				<option value="BOUTIQUE">boutique</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-hotel-food">Type of food</label>
			<select id="select-hotel-food" name="food" class="form-control">
				<option value="AL">AL</option>
				<option value="FB">FB</option>
				<option value="HB">HB</option>
				<option value="BB">BB</option>
				<option value="OB">OB</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-hotel-beach">Type of beach</label>
			<select id="select-hotel-beach" name="beach" class="form-control">
				<option value="SAND">sand</option>
				<option value="PEBBLE">pebble</option>
				<option value="PLATE">plate</option>
				<option value="SAND_PEBBLE">sand and pebble</option>
			</select>
		</div>
		<div class="d-flex flex-column">
			<h3>Facilities</h3>
			<div class="form-group">
				<label for="choose-falility-wifi">Wifi</label>
				<input class="form-check-input form-control" name="facilities" value="WIFI" type="checkbox" id="choose-falility-wifi">
			</div>
			<div class="form-group">
				<label for="choose-falility-minibar">Minibar</label>
				<input class="form-check-input form-control" name="facilities" value="MINIBAR" type="checkbox" id="choose-falility-minibar">
			</div>
			<div class="form-group">
				<label for="choose-falility-hairdryer">Hairdryer</label>
				<input class="form-check-input form-control" name="facilities" value="HAIRDRYER" type="checkbox" id="choose-falility-hairdryer">
			</div>
			<div class="form-group">
				<label for="choose-falility-conditioner">Conditioner</label>
				<input class="form-check-input form-control" name="facilities" value="CONDITIONER" type="checkbox" id="choose-falility-conditioner">
			</div>
			<div class="form-group">
				<label for="choose-falility-tv">TV</label>
				<input class="form-check-input form-control" name="facilities" value="TV" type="checkbox" id="choose-falility-tv">
			</div>
		</div>
		<div class="d-flex flex-column">
			<h3>Facilities</h3>
			<h4>Sport</h4>
			<div class="form-group">
				<label for="choose-sport-gym">Gym</label>
				<input class="form-check-input form-control" name="servicings" value="GYM" type="checkbox" id="choose-sport-gym">
			</div>
			<div class="form-group">
				<label for="choose-sport-fitnes">Fitnes</label>
				<input class="form-check-input form-control" name="servicings" value="FITNES" type="checkbox" id="choose-sport-fitnes">
			</div>
			<div class="form-group">
				<label for="choose-sport-diving">Diving</label>
				<input class="form-check-input form-control" name="servicings" value="DIVING" type="checkbox" id="choose-sport-diving">
			</div>
			<div class="form-group">
				<label for="choose-sport-yoga">Yoga</label>
				<input class="form-check-input form-control" name="servicings" value="YOGA" type="checkbox" id="choose-sport-yoga">
			</div>
			<h4>Entertainment</h4>
			<div class="form-group">
				<label for="choose-entertainment-aquapark">Aquapark</label>
				<input class="form-check-input form-control" name="servicings" value="AQUAPARK" type="checkbox" id="choose-entertainment-aqupark">
			</div>
			<div class="form-group">
				<label for="choose-entertainment-disco">Disco</label>
				<input class="form-check-input form-control" name="servicings" value="DISCO" type="checkbox" id="choose-entertainment-disco">
			</div>
			<div class="form-group">
				<label for="choose-entertainment-restaurant">Restaurant</label>
				<input class="form-check-input form-control" name="servicings" value="RESTAURANT" type="checkbox" id="choose-entertainment-restaurant">
			</div>
		</div>		
		<button class="btn btn-primary" type="submit">Save</button>
	</form>
</body>
</html>