<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ page import="ua.nure.miroshnichenko.touragency.db.entity.TourType"%>
 <%@ page import="java.sql.Date"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post" action="/TourAgency/controller?action=saveTour&edit=${param.edit}">
		<h3>Tour</h3>
		<div class="form-group">
			<label for="select-tour-type">Tour type</label>
			<select name="type" id="select-tour-type" class="form-control">
				<option value="RELAX" <c:if test="${tour.type eq TourType.RELAX}">selected</c:if>>
				Relax</option>
				<option value="EXCURSION" <c:if test="${tour.type eq TourType.EXCURSION}">selected</c:if>>
				Excursion</option>
				<option value="SHOPPING" <c:if test="${tour.type eq TourType.SHOPPING}">selected</c:if>>
				Shopping</option>
			</select>
		</div>
		<div class="form-group">
			<label for="startdate-datepicker">Start date</label>
			<div class="input-group date">
				<input name="startDate" class="form-control" value="${startDate}" type="text"  id="startdate-datepicker">
				<div class="input-group-append">
				    <span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="enddate-datepicker">Start date</label>
			<div class="input-group date">
				<input name="endDate" class="form-control" value="${endDate}" type="text" id="enddate-datepicker">
				<div class="input-group-append">
					<span class="fa fa-calendar input-group-text end_date_calendar" aria-hidden="true "></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="set-max-discount">Max discount</label>
			<input name="maxDiscount" class="form-control" value="${tour.maxDiscount}" type="number" id="set-max-discount">
		</div>
		<div class="form-group">
			<label for="set-max-discount">Agency procent</label>
			<input name="agencyProcent" class="form-control" value="${tour.agencyProcent}" type="number" id="set-max-discount">
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
					<option value="${transport.id}" <c:if test="${tour.transportTo.id eq transport.id}">selected</c:if>>
					${transport.code}</option>
				</c:forEach>			
			</select>
		</div>
		<div class="form-group">
			<label for="select-transport-back">Transport back</label>
			<select name="transportBack" id="select-transport-back" class="form-control">
				<c:forEach items="${transports}" var="transport">
					<option value="${transport.id}" <c:if test="${tour.transportBack.id eq transport.id}">selected</c:if>>
					${transport.code}</option>
				</c:forEach>
			</select>
		</div>
		<div class="custom-control custom-checkbox my-1 mr-sm-2">
			<input name="isFired" type="checkbox" class="custom-control-input" id="set-fired"
			<c:if test="${tour.isFired() eq 1}">checked</c:if>>
			<label for="set-fired" class="custom-control-label">Fired</label>
		</div>
		<c:if test="${param.edit eq 'true'}">
				<input type="hidden" name="id" value="${tour.id}" />
				<input type="hidden" name="edit" value="true" />
		</c:if>
		<button class="btn btn-primary mt-3" type="submit">Save</button>
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