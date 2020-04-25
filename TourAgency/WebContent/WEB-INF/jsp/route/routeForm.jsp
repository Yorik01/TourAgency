<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
	<body>
		<form method="post" action="/TourAgency/controller?action=saveRoute&edit=${param.edit}">
			<h3>><fmt:message key="common.route" /></h3>
			<div class="form-group">
				<label for="select-route-country-from"><fmt:message key="common.country_from" /></label>
				<select id="select-route-country-from" name="countryFrom" class="form-control" class="route-countries" required>
				</select>
			</div>
			<div class="form-group">
				<label for="select-route-city-from"><fmt:message key="common.city_from" /></label>
				<select id="select-route-city-from" name="cityFrom" class="form-control" required>
				</select>
			</div>
			<div class="form-group">
				<label for="select-route-country-to"><fmt:message key="common.country_to" /></label>
				<select id="select-route-country-to" name="countryTo" class="form-control" class="route-countries" required>
				</select>
			</div>
			<div class="form-group">
				<label for="select-route-city-to"><fmt:message key="common.city_to" /></label>
				<select id="select-route-city-to" name="cityTo" class="form-control" required>
				</select>
			</div>
			<c:if test="${param.edit eq 'true'}">
				<input type="hidden" name="id" value="${route.id}" />
				<input type="hidden" name="edit" value="true" />
			</c:if>
			<button class="btn btn-primary" type="submit"><fmt:message key="common.save" /></button>
		</form>
		
		<script src="js/jquery-3.4.1.min.js" ></script>
		<script src="js/popper.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
	
		<script src="js/route.js"></script>
		<script>
			let places = JSON.parse('${requestScope.places}');
			
			let countryFrom = $("#select-route-country-from");
			let countryTo = $("#select-route-country-to");
			
			let citiesFrom = $("#select-route-city-from");
			let citiesTo = $("#select-route-city-to");
			
			setCountries(places, countryFrom);
			setCountries(places, countryTo);
			
			let selectedCountry = countryFrom.children('option:selected').val();
			setCities(places[selectedCountry], citiesFrom);
			
			selectedCountry = countryTo.children('option:selected').val();
			setCities(places[selectedCountry], citiesTo);
			
			countryFrom.change(function() {
				let selectedCountry = $(this).children('option:selected').val();
				setCities(places[selectedCountry], citiesFrom);
				
				let selectedCity = citiesFrom.children('option:selected').val();
				removeCity(selectedCity, citiesTo.children('option'));
			});
			
			citiesFrom.change(function() {
			   let selectedCountry = countryTo.children('option:selected').val()
			   setCities(places[selectedCountry], citiesTo);
			   
			   let selectedCity = $(this).children('option:selected').val();
			   removeCity(selectedCity, citiesTo.children('option'));
			});
			
			countryTo.change(function() {
				let selectedCountry = $(this).children('option:selected').val();
				setCities(places[selectedCountry], citiesTo);
				
				let selectedCity = citiesFrom.children('option:selected').val();
				removeCity(selectedCity, citiesTo.children('option'));
			});
			
			<c:if test="${param.edit eq 'true'}">
				countryFrom.val("${route.from.country}");
				countryTo.val("${route.to.country}");

				selectedCountry = countryFrom.children('option:selected').val();
				setCities(places[selectedCountry], citiesFrom);
				
				selectedCountry = countryTo.children('option:selected').val();
				setCities(places[selectedCountry], citiesTo);
				
				citiesFrom.val("${route.from.city}");
				citiesTo.val("${route.to.city}");
			</c:if>
		</script>
	</body>
</html>