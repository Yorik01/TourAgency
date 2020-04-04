const setTours = tours => {
	let selectTour = $("#select-tour");
	for (let tour of tours) {
		selectTour.append(
			`<option value="${tour.id}">
					${tour.hotel.name} (${tour.startDate} - ${tour.endDate})
			 </option>`);
	}
};