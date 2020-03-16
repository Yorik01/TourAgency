<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post">
		<h3>Transport</h3>
			<div class="form-group">
				<label for="select-transport-type">Transport type</label>
				<select id="select-transport-type" class="form-control">
					<option>...</option>
				</select>
			</div>
			<div class="form-group">
				<label for="transport-number">Number</label>
				<input class="form-control" id="transport-number">
			</div>
			<div class="form-group">
				<label for="transport-max-places">Max places</label>
				<input class="form-control" id="transport-max-places" type="number">
			</div>
			<div class="form-group">
				<label for="transport-takeoff-time">Taking off time</label>
				<input class="form-control" id="transport-takeoff-time" type="time">
			</div>
			<div class="form-group">
				<label for="transport-arriving-time">Arriving time</label>
				<input class="form-control" id="transport-arriving-time" type="time">
			</div>
			<div class="form-group">
				<label for="transport-price">Price</label>
				<input class="form-control" id="transport-price" type="number">
			</div>
			<div class="form-group">
				<label for="select-transport-country">Country</label>
				<select id="select-transport-country" class="form-control">
					<option>...</option>
				</select>
			</div>
			<div class="form-group">
				<label for="select-transport-city">City</label>
				<select id="select-transport-city" class="form-control">
					<option>...</option>
				</select>
			</div>
			<button class="btn btn-primary" type="submit">Save</button>
		</form>
</body>
</html>