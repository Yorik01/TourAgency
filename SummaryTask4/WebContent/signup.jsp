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
	<form id="form-signup" class="form-center">
		<div class="form-group">
			<label for="input-email">Email address</label> 
			<input type="email"
				class="form-control" id="input-email" aria-describedby="emailHelp"
				placeholder="Enter email">
		</div>
		<div class="form-group">
			<label for="input-password">Password</label> <input type="password"
				class="form-control" id="input-password" placeholder="Password">
		</div>
		<div class="form-group">
			<label for="input-repeat-pasword">Repeat password</label> <input
				type="password" class="form-control" id="input-repeat-password"
				placeholder="Password"> <small id="password-help"
				class="form-text text-muted"></small>
		</div>
		<button type="submit" class="btn btn-primary">Sign up</button>
	</form>

	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>

	<script>
	let passHelp = $('#password-help');
	let repeatedPass = $('#input-repeat-password');
	
	$('#form-signup').submit(e => {
		e.preventDefault();
		if($('#input-password').val() === repeatedPass.val()) {
			let email = $("#input-email").val();
			let pass = $("#input-password").val();

			let checking = $.post('/SummaryTask4/controller', {action: 'signup', email: email, password: pass});
			checking.done(res => {
				if (res.status == 200) {
					$('#form-login').submit();
				} else {
					$('#form-logib').append(`<p>${res.responseText}</p>`);
				}
			});
		} else {
			repeatedPass.css({'box-shadow': '0 0 0 0.2rem rgba(223,78,95,.25)'});
			repeatedPass.css({'border-color': 'rgba(223,78,95,.25)'});
			passHelp.text('The both passwords must be the same!');
		}	
	});
</script>
</body>
</html>