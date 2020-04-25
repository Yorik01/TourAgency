<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="pageName" value="signup" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>

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
		<button type="submit" id="btn-signup" class="btn btn-primary"><fmt:message key="common.signup" /></button>
	</form>

	<%@ include file="/WEB-INF/jspf/footer.jspf" %>
	
	<script src="js/user.js"></script>
	<script>
		$(document).ready(() => {
		   setPasswordEqualityEvent('<fmt:message key="common.passwords_equal" />');
		});
	</script>
</body>
</html>