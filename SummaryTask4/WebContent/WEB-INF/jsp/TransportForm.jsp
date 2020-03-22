<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
 
 <%@ page import="ua.nure.miroshnichenko.summarytask4.db.entity.TransportType"%>
 <%@ page import="java.sql.Timestamp"%>
 <%@ page import="java.sql.Time"%>
 <%@ page import="java.sql.Date"%>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post" action="/SummaryTask4/controller?action=saveTransport&edit=${param.edit}">
		<h3>Transport</h3>
			<div class="form-group">
				<label for="select-transport-type">Transport type</label>
				<select id="select-transport-type" name="type" class="form-control">
					<option value="AIRPLANE" <c:if test="${transport.type eq TransportType.AIRPLANE}">selected</c:if>>
					Airplane</option>
					<option value="BUS" <c:if test="${transport.type eq TransportType.BUS}">selected</c:if>>
					Bus</option>
				</select>
			</div>
			<div class="form-group">
				<label for="transport-code">Code</label>
				<input class="form-control" value="${transport.code}" name="code" id="transport-code">
			</div>
			<div class="form-group">
				<label for="transport-max-places">Max places</label>
				<input class="form-control" value="${transport.maxPlaces}"  name="maxPlaces" id="transport-max-places" type="number">
			</div>
			<div class="form-group">
                <div class='input-group date'>
                	<label for="transport-takeoffdate">Takeoff date</label>
                	<br>
                    <input type='text' name="takeoffDate" value="${takeoffDate}" class="form-control" id='transport-takeoffdate' />
                    <div class="input-group-append">
			      		<span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    	</div>
                </div>
            </div>
            <div class="form-group">
				<label for="transport-takeofftime">Takeoff time</label>
				<input type="time" class="form-control" value="${takeoffTime}" name="takeoffTime" id="transport-takeofftime">
			</div>
			<div class="form-group">
                <div class='input-group date'>
					<label for="transport-takeoffdate">Arriving date</label>
                    <input type='text' class="form-control" value="${arriveDate}" name="arrivingDate" id='transport-arrivingdate' />
                    <div class="input-group-append">
			      		<span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    	</div>
                </div>
            </div>
			<div class="form-group">
				<label for="transport-arrivingtime">Arriving time</label>
				<input type="time" class="form-control" value="${arriveTime}" name="arrivingTime" id="transport-arrivingtime">
			</div>
			<div class="form-group">
				<label for="transport-price">Price</label>
				<input class="form-control" value="${transport.price}" name="price" id="transport-price" type="number">
			</div>
			<div class="form-group">
				<label for="select-transport-route">Route</label>
				<select id="select-transport-route" name="routeId" class="form-control">
					<c:forEach items="${requestScope.routes}" var="route">
						<option value="${route.id}" <c:if test="${transport.route.id eq route.id}">selected</c:if>>
						${route.from.country}, ${route.from.city} -> ${route.to.country}, ${route.to.city} </option>
					</c:forEach>
				</select>
			</div>
			<c:if test="${param.edit eq 'true'}">
				<input type="hidden" name="id" value="${transport.id}" />
				<input type="hidden" name="edit" value="true" />
			</c:if>
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