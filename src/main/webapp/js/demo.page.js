$(document).ready(function () {
    excel = new ExcelGen({
        "src_id": "excel_table",
        "show_header": true
    });
    $("#generate-excel").click(function () {
        excel.generate();
    });
});