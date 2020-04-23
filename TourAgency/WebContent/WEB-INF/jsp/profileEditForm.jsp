<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/locale.jspf" %>

<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form action="/TourAgency/controller">
		<div class="form-group">
			<label for="input-email"><fmt:message key="common.email" /></label> <input type="email"
				class="form-control" id="input-email" name="email" value="${user.email}" aria-describedby="emailHelp"
				placeholder="Enter email">
		</div>
		<div class="form-group">
			<label for="input-password"><fmt:message key="profile_jsp.old_password" /></label> <input type="password"
				class="form-control" id="input-password" placeholder="Password">
		</div>
		<div class="form-group">
			<label for="input-repeat-pasword"><fmt:message key="profile_jsp.new_password" /></label> <input
				type="password" class="form-control" id="input-repeat-password"
				placeholder="Password" name="password"> <small id="password-help"
				class="form-text text-muted"></small>
		</div>
		<div class="form-group">
			<label for="input-repeat-pasword"><fmt:message key="common.repeat_password" /></label> <input
				type="password" class="form-control" id="input-repeat-password"
				placeholder="Password"> <small id="password-help"
				class="form-text text-muted"></small>
		</div>
		
		<input type="hidden" name="action" value="editUser" />
		<input type="hidden" name="id" value="${user.id}" />
		<input type="hidden" name="role" value="${user.role}" />
		<button type="submit" class="btn btn-primary"><fmt:message key="common.save" /></button>
	</form>
</body>
</html>