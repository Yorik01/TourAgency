<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="bootstrap/css/bootstrap.min.js" rel="stylesheet">
<title>login</title>
</head>
<body>
	<form id="form-login" action="/index">
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<label for="inputEmail" class="sr-only">Email address</label> 
		<input type="email" id="input-email" class="form-control mb-3"
			placeholder="Email address" required autofocus />
			 <label for="inputPassword" class="sr-only">Password</label>
		<input type="password" id="input-password" class="form-control"
				placeholder="Password" required />
		<button id="btn-login" class="btn btn-lg btn-primary btn-block" type="submit">
			login
		</button>
	</form>

<script src="js/jquery-3.4.1.slim.min.js" ></script>

<script>
	$('#form-login').submit(function(e) {
		e.preventDefault();
		
		let email = $('#input-email').val();
		let pass = $('#input-password').val();
		
		let checking = $.post('/login', {email: email, password: pass});
		checking.done(res => {
			if (res.status == 200) {
				$('#form-login').submit();
			} else {
				$('#form-logib').append('<p>Incorrect login or password!</p>');
			}
		});
	});
</script>
</body>
</html>