<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post" action="/SummaryTask4/controller?action=addTour">
		<div class="form-group">
			<label for="select-tour-type">Tour type</label>
			<select name="type" id="select-tour-type" class="form-control">
				<option value="RELAX">Relax</option>
				<option value="EXCURSION">Excursion</option>
				<option value="SHOPPING">Shopping</option>
			</select>
		</div>
		<div class="start_date input-group ml-3">
			<input name="startDate" class="form-control start_date" type="text" placeholder="start date" id="startdate-datepicker">
			<div class="input-group-append">
			    <span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			</div>
		</div>
		<div class="end_date input-group ml-3 mr-3">
			<input name="endDate" class="form-control end_date" type="text" placeholder="end date" id="enddate-datepicker">
			<div class="input-group-append">
				<span class="fa fa-calendar input-group-text end_date_calendar" aria-hidden="true "></span>
			</div>
		</div>
		<div class="form-group">
			<label for="set-max-discount">Max discount</label>
			<input name="maxDiscount" type="number" id="set-max-discount">
		</div>
		<div class="form-group">
			<label for="set-max-discount">Agency procent</label>
			<input name="agencyProcent" type="number" id="set-max-discount">
		</div>
		<div class="form-group">
			<label for="set-max-discount">Fired</label>
			<input name="isFired" type="checkbox" id="set-max-discount">
		</div>
		<div class="form-group">
			<label for="select-hotel">Hotel</label>
			<select name="hotel" id="select-hotel" class="form-control">
				<c:forEach items="${hotels}" var="hotel">
					<option value="${hotel.id}">${hotel.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="select-transport-to">Transport to</label>
			<select name="transportTo" id="select-transport-to" class="form-control">
				<c:forEach items="${transports}" var="transport">
					<option value="${transport.id}">${transport.code}</option>
				</c:forEach>			
			</select>
		</div>
		<div class="form-group">
			<label for="select-transport-back">Transport back</label>
			<select name="transportBack" id="select-transport-back" class="form-control">
				<c:forEach items="${transports}" var="transport">
					<option value="${transport.id}">${transport.code}</option>
				</c:forEach>
			</select>
		</div>
		<button class="btn btn-primary" type="submit">Save</button>
	</form>
	
<script src="bootstrap/js/moment.min.js"></script>	
<script src="js/jquery-3.4.1.min.js" ></script>
<script src="js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/moment-with-locales.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.min.js"></script>

<script>
	$("#startdate-datepicker").datepicker();
	$("#enddate-datepicker").datepicker();
</script>
</body>
</html>