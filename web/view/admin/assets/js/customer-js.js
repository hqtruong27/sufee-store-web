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
    $(".btn-change-status").off("click").on("click", function (e) {
        e.preventDefault();
        var id = $(this).data("id");
        $.ajax({
            type: 'GET',
            url: "change-status.htm",
            data: {customerId: id},
            success: function (res) {
                if (res === "1") {
                    $("#rows_" + id + " td p").text("Kích hoạt");
                    $("#rows_" + id + " td p").removeClass();
                    $("#rows_" + id + " td p").addClass("badge badge-pill badge-success");
                    $('.notifyjs-corner').empty();
                    $.notify('Thay đổi trạng thái thành công !', {
                        globalPosition: "top center",
                        className: 'success'
                    });
                } else if (res === "0") {
                    $("#rows_" + id + " td a").remove("data-status");
                    $("#rows_" + id + " td p").text("Đã bị khoá");
                    $("#rows_" + id + " td p").removeClass();
                    $("#rows_" + id + " td p").addClass("badge badge-pill badge-secondary");
                    $('.notifyjs-corner').empty();
                    $.notify('Thay đổi trạng thái thành công !', {
                        globalPosition: "top center",
                        className: 'success'
                    });
                } else {
                    $('.notifyjs-corner').empty();
                    $.notify('Có gì đó không đúng !', {
                        globalPosition: "top center",
                        className: 'error'
                    });
                }
                $("#ModalLock").modal('hide');
            }
        });
    });
})(jQuery);