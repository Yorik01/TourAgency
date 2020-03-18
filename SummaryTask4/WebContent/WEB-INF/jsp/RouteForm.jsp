<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
	<body>
		<form method="post" action="/SummaryTask4/controller?action=${op}Route">
			<div class="form-group">
				<label for="select-route-country-from">Country from</label>
				<select id="select-route-country-from" name="countryFrom" class="form-control" class="route-countries">
				</select>
			</div>
			<div class="form-group">
				<label for="select-route-city-from">City from</label>
				<select id="select-route-city-from" name="cityFrom" class="form-control">
				</select>
			</div>
			<div class="form-group">
				<label for="select-route-country-to">Country to</label>
				<select id="select-route-country-to" name="countryTo" class="form-control" class="route-countries">
				</select>
			</div>
			<div class="form-group">
				<label for="select-route-city-to">City to</label>
				<select id="select-route-city-to" name="cityTo" class="form-control">
				</select>
			</div>
			<button class="btn btn-primary" type="submit">Save</button>
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
			});
			
			countryTo.change(function() {
				let selectedCountry = $(this).children('option:selected').val();
				setCities(places[selectedCountry], citiesTo);
			});
			
			<c:if test="${op eq 'edit'}">
				countryFrom.val("${route.from.country}");
				countryTo.val("${route.to.country}");

				selectedCountry = countryFrom.children('option:selected').val();
				setCities(places[selectedCountry], citiesFrom);
				
				selectedCountry = countryTo.children('option:selected').val();
				setCities(places[selectedCountry], citiesTo);
				
				cityFrom.val("${route.from.city}");
				cityTo.val("${route.to.city}");
			</c:if>
		</script>
	</body>
</html>