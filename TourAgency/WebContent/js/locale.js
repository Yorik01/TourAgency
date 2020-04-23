$(document).ready(() => {
	$('.change-local').click( function(e) {
		e.preventDefault();
		
		let locale = $(this).children('input').val();
		
		$.post('/TourAgency/controller', {action: 'changeLocale', locale: locale})
			.done(() => {
				window.location.reload();
			})
			.fail(err => {
				console.error(err);
			});
	});
});