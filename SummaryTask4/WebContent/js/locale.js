$(document).ready(() => {
	$('#locale-ru').click(() => {
		$.post('/TourAgency/controller', {action: 'changeLocale', locale: 'ru'})
			.done(() => {
				window.location.reload();
			});
	});
	
	$('#locale-en').click(() => {
		$.post('/TourAgency/controller', {action: 'changeLocale', locale: 'en'})
			.done(() => {
				window.location.reload();
			});
	});
});