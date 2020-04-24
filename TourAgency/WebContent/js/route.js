const setCountries = (places, DOM) => {
	for(let country in places) {
		DOM.append(`<option value=${country}>${country}</option>`);
	}
};

const setCities = (cities, DOM) => {
	DOM.empty();
	for(let city of cities) {
		DOM.append(`<option value=${city}>${city}</option>`);
	}
};

const removeCity = (city, DOM) => {
    return DOM.each(function() {
	if ($(this).val() === city) {
	    $(this).remove();
	}
    });
};