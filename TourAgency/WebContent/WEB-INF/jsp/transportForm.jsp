<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>
 
 <%@ page import="ua.nure.miroshnichenko.touragency.db.entity.TransportType"%>
 <%@ page import="java.sql.Timestamp"%>
 <%@ page import="java.sql.Time"%>
 <%@ page import="java.sql.Date"%>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form method="post" action="/TourAgency/controller?action=saveTransport&edit=${param.edit}">
		<h3><fmt:message key="common.transport" /></h3>
			<div class="form-group">
				<label for="select-transport-type"><fmt:message key="common.transport_type" /></label>
				<select id="select-transport-type" name="type" class="form-control">
					<option value="AIRPLANE" <c:if test="${transport.type eq TransportType.AIRPLANE}">selected</c:if>>
					<fmt:message key="common.plane" /></option>
					<option value="BUS" <c:if test="${transport.type eq TransportType.BUS}">selected</c:if>>
					<fmt:message key="common.bus" /></option>
				</select>
			</div>
			<div class="form-group">
				<label for="transport-code"><fmt:message key="common.transport_code" /></label>
				<input class="form-control" value="${transport.code}" name="code" id="transport-code">
			</div>
			<div class="form-group">
				<label for="transport-max-places"><fmt:message key="common.max_places" /></label>
				<input class="form-control" value="${transport.maxPlaces}"  name="maxPlaces" id="transport-max-places" type="number">
			</div>
			<div class="form-group">
				<label for="transport-takeoffdate"><fmt:message key="common.takeoff_date" /></label>
                <div class='input-group date'>
                	<br>
                    <input type='text' name="takeoffDate" value="${takeoffDate}" class="form-control" id='transport-takeoffdate' 
                    pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" title="yyyy-mm-dd" autocomplete="off" />
                    <div class="input-group-append">
			      		<span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    	</div>
                </div>
            </div>
            <div class="form-group">
				<label for="transport-takeofftime"><fmt:message key="common.takeoff_time" /></label>
				<input type="time" class="form-control" value="${takeoffTime}" name="takeoffTime" id="transport-takeofftime">
			</div>
			<div class="form-group">
				<label for="transport-takeoffdate"><fmt:message key="common.arriving_date" /></label>
                <div class='input-group date'>
                    <input type='text' class="form-control" value="${arriveDate}" name="arrivingDate" id='transport-arrivingdate' 
                    pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" title="yyyy-mm-dd" autocomplete="off" />
                    <div class="input-group-append">
			      		<span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
			    	</div>
                </div>
            </div>
			<div class="form-group">
				<label for="transport-arrivingtime"><fmt:message key="common.arriving_time" /></label>
				<input type="time" class="form-control" value="${arriveTime}" name="arrivingTime" id="transport-arrivingtime">
			</div>
			<div class="form-group">
				<label for="transport-price"><fmt:message key="common.price" /></label>
				<input class="form-control" value="${transport.price}" name="price" id="transport-price" type="number">
			</div>
			<div class="form-group">
				<label for="select-transport-route"><fmt:message key="common.route" /></label>
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
			<button class="btn btn-primary" type="submit"><fmt:message key="common.save" /></button>
		</form>
		
		<script src="bootstrap/js/moment.min.js"></script>	
		<script src="js/jquery-3.4.1.min.js" ></script>
		<script src="js/popper.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<script src="bootstrap/js/moment-with-locales.min.js"></script>
		<script src="bootstrap/js/bootstrap-datepicker.min.js"></script>
		
		<script>
			$('#transport-takeoffdate').datepicker({
			    format: 'yyyy-mm-dd'
			});
	    	
	 		$('#transport-arrivingdate').datepicker({
	 		    format: 'yyyy-mm-dd'
	 		});
		</script>
</body>
</html>