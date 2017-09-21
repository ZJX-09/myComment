$(function() {
    common.showMessage($("#message").val());
});
function remove(id) {
	if(confirm("你确定要删除这个商户?")){
        $("#mainForm").attr("action",$("#basePath").val() + "/businesses/" + id);
        $("#mainForm").submit();
	}
}
function search(currentPage) {
    $("#currentPage").val(currentPage);
	$("#mainForm").attr("method","GET");
	$("#mainForm").attr("action",$("#basePath").val() + "/businesses");
	$("#mainForm").submit();
}
function modifyInit(id) {
	location.href = $("#basePath").val() + "/businesses/" + id;
}