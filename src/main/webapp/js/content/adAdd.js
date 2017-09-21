$(function() {
	common.showMessage($("#message").val());
});

function add() {
	if(check()) {
		$("#mainForm").submit();
	}
}

function check() {

	return true;
}

function goback() {
	location.href = $('#basePath').val() + '/ad';
}