<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<title>login</title>
</head>
<body>
	<form id="form-login" class="form-center" action="/SummaryTask4/controller" method="post">
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<label for="inputEmail" class="sr-only">Email address</label> 
		<input type="email" id="input-email" class="form-control mb-3"
			placeholder="Email address" name="email" required autofocus />
			 <label for="inputPassword" class="sr-only">Password</label>
		<input type="password" id="input-password" class="form-control"
				placeholder="Password" name="password" required />
		<button id="btn-login" class="btn btn-primary btn-block mt-3" type="submit">
			login
		</button>
		<span id="reg-offering">Don't you have the account? 
			<a href="/SummaryTask4/signup.jsp">SignUp</a>
		</span>
		<input type="hidden" name="action" value="login" />
	</form>

<script src="js/jquery-3.4.1.min.js" ></script>
<script src="js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

</body>
</html>