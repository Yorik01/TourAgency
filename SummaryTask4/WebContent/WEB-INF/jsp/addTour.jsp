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
			<label for="select-tour-type">Tour type</label>
			<select id="select-tour-type" class="form-control">
				<option>...</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-from">From</label>
			<select id="select-country" class="form-control">
				<option>...</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-country">Country</label>
			<select id="select-country" class="form-control">
				<option>...</option>
			</select>
		</div>
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
		<div class="form-group">
			<label for="set-max-discount">Max discount</label>
			<input type="number" id="set-max-discount">
		</div>
		<div class="form-group">
			<label for="select-hotel">Hotel</label>
			<select id="select-hotel" class="form-control">
				<option>...</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-transport-to">Transport to</label>
			<select id="select-transport-to" class="form-control">
				<option>...</option>
			</select>
		</div>
		<div class="form-group">
			<label for="select-transport-back">Transport back</label>
			<select id="select-transport-back" class="form-control">
				<option>...</option>
			</select>
		</div>
	</form>
</body>
</html>