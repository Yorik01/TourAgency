<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="bootstrap/css/bootstrap.min.js" rel="stylesheet">
	<title>SignUp</title>
</head>
<body>
	<form id="form-signup">
  		<div class="form-group">
	    	<label for="input-email">Email address</label>
    		<input type="email" class="form-control" id="input-email" aria-describedby="emailHelp" placeholder="Enter email">
 		 </div>
	  	<div class="form-group">
    		<label for="input-password">Password</label>
    		<input type="password" class="form-control" id="input-password" placeholder="Password">
	  	</div>
  		<div class="form-group">
	    	<label for="input-repeat-pasword">Repeat password</label>
    		<input type="password" class="form-control" id="input-repeat-password" placeholder="Password">
    		 <small id="password-help" class="form-text text-muted"></small>
  		</div>
  		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
<script src="js/jquery-3.4.1.slim.min.js" ></script>
	
<script>
	let canSubmit = false;

	$('#input-repeat-password').keypress(() => {
		
		let repeatedPass = $('#input-repeat-password');
		let passHelp = $('#password-help');
		if($('#input-password').val() === repeatedPass.val()){
			repeatedPass.focus(function() {
				$(this).css({'box-shadow': '0 0 0 0.2rem rgba(40,167,69,.25)'});
				passHelp.text('');
				canSubmit = true;
			});
		} else {
			repeatedPass.focus(function() {
				$(this).css({'box-shadow': '0 0 0 0.2rem rgba(223,78,95,.25)'});
				passHelp.text('The both passwords must be the same!');
				canSubmit = false;
			});
		}
	});
	
	$('#form-signup').submit(e => {
		e.preventDefault();
		if(canSubmit) {
			let checking = $.post('/login', {email: email, password: pass});
			checking.done(res => {
				if (res.status == 200) {
					$('#form-login').submit();
				} else {
					$('#form-logib').append(`<p>${res.responseText}</p>`);
				}
			});
		}
	});
</script>
</body>
</html>