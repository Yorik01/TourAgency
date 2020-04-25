$(document).ready(() => {
    $('#json-export').click(() => {
	$("#statistic-table").tableHTMLExport({
	    type:'json',
	    filename:'report-' + "json-" + new Date().toISOString() + '.json'
	});
    });
    
    $('#csv-export').click(() => {
	$("#statistic-table").tableHTMLExport({
	    type:'csv',
	    filename:'report-' + "csv-" + new Date().toISOString() + '.csv'
	});
    });
    
    $('#pdf-export').click(() => {
	$("#statistic-table").tableHTMLExport({
	    type:'pdf',
	    header: 'SSSSS',
	    filename:'report-' + "pdf-" + new Date().toISOString() + '.pdf'
	});
    });
    
    $('#txt-export').click(() => {
	$("#statistic-table").tableHTMLExport({
	    type:'txt',
	    filename:'report-' + "txt-" + new Date().toISOString() + '.txt'
	});
    });
});