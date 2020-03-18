<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post" action="/SummaryTask4/controller?action=addTransport">
		<h3>Transport</h3>
			<div class="form-group">
				<label for="select-transport-type">Transport type</label>
				<select id="select-transport-type" name="type" class="form-control">
					<option value="AIRPLANE">Airplane</option>
					<option value="BUS">Bus</option>
				</select>
			</div>
			<div class="form-group">
				<label for="transport-code">Code</label>
				<input class="form-control" name="code" id="transport-code">
			</div>
			<div class="form-group">
				<label for="transport-max-places">Max places</label>
				<input class="form-control" name="maxPlaces" id="transport-max-places" type="number">
			</div>
			<div class="form-group">
                <div class='input-group date'>
                	<label for="transport-takeoffdate">Takeoff date</label>
                	<br>
                    <input type='text' name="takeoffDate" class="form-control" id='transport-takeoffdate' />
                    <div class="input-group-append">
			      		<span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    	</div>
                </div>
            </div>
            <div class="form-group">
				<label for="transport-takeofftime">Takeoff time</label>
				<input type="time" class="form-control" name="takeoffTime" id="transport-takeofftime">
			</div>
			<div class="form-group">
                <div class='input-group date'>
					<label for="transport-takeoffdate">Arriving date</label>
                    <input type='text' class="form-control" name="arrivingDate" id='transport-arrivingdate' />
                    <div class="input-group-append">
			      		<span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    	</div>
                </div>
            </div>
			<div class="form-group">
				<label for="transport-arrivingtime">Arriving time</label>
				<input type="time" class="form-control" name="arrivingTime" id="transport-arrivingtime">
			</div>
			<div class="form-group">
				<label for="transport-price">Price</label>
				<input class="form-control" name="price" id="transport-price" type="number">
			</div>
			<div class="form-group">
				<label for="select-transport-route">Route</label>
				<select id="select-transport-route" name="routeId" class="form-control">
					<c:forEach items="${requestScope.routes}" var="route">
						<c:out value="aaaaaaaaaaaa"/>
						<option value="${route.id}">${route.from.country}, ${route.from.city} -> ${route.to.country}, ${route.to.city} </option>
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
			$('#transport-takeoffdate').datepicker();
	    	
	 		$('#transport-arrivingdate').datepicker();
		</script>
</body>
</html>