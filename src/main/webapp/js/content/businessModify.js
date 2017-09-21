
$(function(){
    common.showMessage($("#message").val());
})



function modify() {
	$("#mainForm").submit();
}
function goback(){
	location.href = $("#basePath").val() + "/businesses";
}
