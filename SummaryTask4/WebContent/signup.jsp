<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title><fmt:message key="signup_jsp.title" /></title>
</head>
<body>
	<form id="form-signup" class="form-center" action="/TourAgency/controller" method="post">
		<div class="form-group">
			<label for="input-email"><fmt:message key="common.email" /></label> 
			<input type="email" name="email"
				class="form-control" id="input-email" aria-describedby="emailHelp"
				placeholder="<fmt:message key="common.email" />">
		</div>
		<div class="form-group">
			<label for="input-password"><fmt:message key="common.password" /></label> <input type="password"
				class="form-control" name="password" id="input-password" placeholder="<fmt:message key="common.password" />">
		</div>
		<div class="form-group">
			<label for="input-repeat-pasword"><fmt:message key="common.repeat_password" /></label> <input
				type="password" class="form-control" id="input-repeat-password"
				placeholder="<fmt:message key="common.password" />"> <small id="password-help"
				class="form-text text-muted"></small>
		</div>
		<input type="hidden" name="action" value="signup" />
		<button type="submit" class="btn btn-primary"><fmt:message key="common.signup" /></button>
	</form>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>

</body>
</html>