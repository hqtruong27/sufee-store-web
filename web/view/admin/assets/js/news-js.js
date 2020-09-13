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
            $(".btn-delete").on("click", function (e) {
                e.preventDefault();
                var id = $(this).data("id");
                $("#staticModal").modal('show');
                $("#confirmdelete").on("click", function () {
                    $.ajax({
                        type: 'GET',
                        url: "delete-catalog.htm",
                        data: {catalogId: id},
                        success: function (res) {
                            if (res === "success") {
                                $("#staticModal").modal('hide');
                                window.location.reload();
                                $.notify({
                                    title: '<strong>Success: </strong>',
                                    message: 'Xoá danh mục thành công'
                                }, {
                                    type: 'success',
                                    allow_dismiss: false
                                });
                            } else {
                                $("#staticModal").modal('hide');
                                $.notify({
                                    title: '<strong>Error: </strong>',
                                    message: 'Có gì đó không đúng'
                                }, {
                                    type: 'danger',
                                    allow_dismiss: false
                                });
                            }
                        }
                    });
                });
            });
        })(jQuery);

