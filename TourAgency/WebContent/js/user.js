const setPasswordEqualityEvent = (msg) => {
    $('#btn-signup').click(e => {
	e.preventDefault();

	let inputPassword = $('#input-password').val();
	let repeatPassword = $('#input-repeat-password').val();
	
	if (inputPassword === repeatPassword) {
	    $('#form-signup').submit();
	} else {
	    $('#password-help').text(msg);
	}
    });
};