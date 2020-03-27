<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<title>SignUp</title>
</head>
<body>
	<form id="form-signup" class="form-center" action="/SummaryTask4/controller" method="post">
		<div class="form-group">
			<label for="input-email">Email address</label> 
			<input type="email" name="email"
				class="form-control" id="input-email" aria-describedby="emailHelp"
				placeholder="Enter email">
		</div>
		<div class="form-group">
			<label for="input-password">Password</label> <input type="password"
				class="form-control" name="password" id="input-password" placeholder="Password">
		</div>
		<div class="form-group">
			<label for="input-repeat-pasword">Repeat password</label> <input
				type="password" class="form-control" id="input-repeat-password"
				placeholder="Password"> <small id="password-help"
				class="form-text text-muted"></small>
		</div>
		<input type="hidden" name="action" value="signup" />
		<button type="submit" class="btn btn-primary">Sign up</button>
	</form>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>

</body>
</html>