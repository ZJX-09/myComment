$(function() {
    common.showMessage($("#message").val());
});
function add() {
	$("#mainForm").submit();
}
function goback() {
    location.href = $('#basePath').val() + '/businesses';
}