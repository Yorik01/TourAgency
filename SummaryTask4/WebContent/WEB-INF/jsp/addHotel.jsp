<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post">
		<div class="form-group">
			<label for="set-hotel-name">Name</label>
			<input id="set-hotel-name" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-info">Info</label>
			<textarea id="set-hotel-info" required></textarea>
		</div>
		<div class="form-group">
			<label for="set-hotel-address">Address</label>
			<input id="set-hotel-address" required>
		</div>	
		<div class="form-group">
			<label for="set-hotel-tel">Telephone</label>
			<input id="set-hotel-tel" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-site">Site</label>
			<input id="set-hotel-site" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-stars">Stars</label>
			<input id="set-hotel-stars" type="number" minValue="1" maxValue="5" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-max-rooms">Max count of rooms</label>
			<input id="set-hotel-max-rooms" required>
		</div>
		<div class="form-group">
			<label for="set-hotel-price">Price</label>
			<input id="set-hotel-price" type="number" required>
		</div>
		<div class="form-group">
			<label for="select-hotel-type">Hotel type</label>
			<select id="select-hotel-type" class="form-control">
				<option>...</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-hotel-food">Type of food</label>
			<select id="select-hotel-food" class="form-control">
				<option>...</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-hotel-beach">Type of beach</label>
			<select id="select-hotel-beach" class="form-control">
				<option>...</option>
			</select>
		</div>
	</form>
</body>
</html>