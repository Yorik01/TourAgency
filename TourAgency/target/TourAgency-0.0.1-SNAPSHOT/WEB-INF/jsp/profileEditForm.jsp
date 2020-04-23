<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<body>
	<form action="/TourAgency/controller">
		<div class="form-group">
			<label for="input-email">Email address</label> <input type="email"
				class="form-control" id="input-email" name="email" value="${user.email}" aria-describedby="emailHelp"
				placeholder="Enter email">
		</div>
		<div class="form-group">
			<label for="input-password">Old password</label> <input type="password"
				class="form-control" id="input-password" placeholder="Password">
		</div>
		<div class="form-group">
			<label for="input-repeat-pasword">New password</label> <input
				type="password" class="form-control" id="input-repeat-password"
				placeholder="Password" name="password"> <small id="password-help"
				class="form-text text-muted"></small>
		</div>
		<div class="form-group">
			<label for="input-repeat-pasword">Repeat new password</label> <input
				type="password" class="form-control" id="input-repeat-password"
				placeholder="Password"> <small id="password-help"
				class="form-text text-muted"></small>
		</div>
		
		<input type="hidden" name="action" value="editUser" />
		<input type="hidden" name="id" value="${user.id}" />
		<input type="hidden" name="role" value="${user.role}" />
		<button type="submit" class="btn btn-primary">Save</button>
	</form>
</body>
</html>