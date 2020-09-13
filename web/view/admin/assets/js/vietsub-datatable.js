(function ($) {
    $("#myTable").DataTable({
        "oLanguage": {
            "oPaginate": {
                "sPrevious": "Trang trước",
                "sNext": "Trang sau",
                "sLast": "Trang cuối",
                "sFirst": "Trang đầu"
            },
            //searcz
            "sSearch": "Tìm kiếm:",
            "sLengthMenu": "Hiện thị _MENU_ số hàng",
            "sInfo": "Trang _START_ tổng _TOTAL_ (_START_ to _END_)",
            "sInfoEmpty": 'Không có gì để hiển thị',
            "sEmptyTable": "Không có dữ liệu, click vào <span style='font-weight:700'>Thêm mới</span> để thêm dữ liệu"
        },
        "order": [[1, "asc"]]
    });
})(jQuery);

