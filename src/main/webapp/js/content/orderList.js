function search(currentPage) {
    $("#currentPage").val(currentPage);
    $("#mainForm").attr("method", "GET");
    $("#mainForm").submit();
}
