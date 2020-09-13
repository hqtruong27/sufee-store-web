<%-- 
    Document   : category-parent-list
    Created on : Aug 1, 2020, 3:17:33 PM
    Author     : Hoang Truong
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/header.jsp" flush="true"/>
<jsp:include page="widget/navbar.jsp" flush="true"/>
<div id="right-panel" class="right-panel">
    <header id="header" class="header">

        <div class="header-menu">

            <div class="col-sm-7">
                <a id="menuToggle" class="menutoggle pull-left"><i class="fa fa fa-tasks"></i></a>
                <div class="header-left">
                    <button class="search-trigger"><i class="fa fa-search"></i></button>
                    <div class="form-inline">
                        <form class="search-form">
                            <input class="form-control mr-sm-2" type="text" placeholder="Search ..." aria-label="Search">
                            <button class="search-close" type="submit"><i class="fa fa-close"></i></button>
                        </form>
                    </div>
                    <div class="dropdown for-notification">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="notification" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="ti-shopping-cart"></i>
                            <c:if test="${not empty countNotifyOrder}">
                                <span class="count bg-danger">${countNotifyOrder}</span>
                            </c:if>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="notification">
                            <p class="red">Đơn hàng chưa giao</p>
                            <c:forEach items="${listOrder}" var="notifyOrder">
                                <a class="dropdown-item media bg-flat-color-0" href="<%=request.getContextPath()%>/admin/orders-detail.htm?orderId=${notifyOrder.orderId}">
                                    <span class="message media-body" >
                                        <span class="name float-left">${notifyOrder.email}</span>
                                        <p class="time float-left"><fmt:formatNumber value="${notifyOrder.orderTotalAmount}"/>₫</p>
                                        <p class="time float-left"><fmt:formatDate pattern="dd/MM/yyyy" value="${notifyOrder.createDate}"/></p>
                                    </span>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="dropdown for-message">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="message" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="ti-email" title="Phản hồi khách hàng"></i>
                            <c:if test="${not empty countNotifyFeedback}">
                                <span class="count bg-primary">${countNotifyFeedback}</span>
                            </c:if>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="message">
                            <c:forEach items="${listFeedback}" var="notifyFeedback">
                                <a class="dropdown-item media bg-flat-color-0" href="<%=request.getContextPath()%>/admin/feedback-detail.htm?feedbackId=${notifyFeedback.feedbackId}">
                                    <span class="message media-body" >
                                        <span class="name float-left">${notifyFeedback.fullname}</span>
                                        <p class="time float-left">${notifyFeedback.content}</p>
                                        <p class="time float-left"><fmt:formatDate pattern="dd/MM/yyyy" value="${notifyFeedback.feedbacksTime}"/></p>
                                    </span>
                                </a>
                            </c:forEach>
                            <a href="<%=request.getContextPath()%>/admin/feedback.htm"><button style="width: 100%" type="button" class="btn btn-outline-secondary btn-sm">Xem thêm </button></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-5">
                <c:if test="${not empty InfoAdmin}">
                    <div class="user-area dropdown float-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img class="user-avatar rounded-circle" src="${pageContext.request.contextPath}/view/admin/uploads/admin.jpg" alt="User Avatar">
                        </a>
                        <div class="user-menu dropdown-menu">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/info-admin.htm"><i class="mr-2 fa fa-user"></i> Thông tin </a>
                            <a class="nav-link" href="logout.htm"><i class="mr-2 fa fa-power-off"></i> Logout</a>
                        </div>
                    </div>
                    <div class="float-right"  style="margin: 7px 14px 0px 0px;"id="">
                        <a href="#">${InfoAdmin.fullname}</a>
                    </div>
                </c:if>
            </div>
        </div>
    </header>
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">

                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">Danh mục cấp 2</strong>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6 col-xs-6">
                                    <a data-toggle="tooltip" title="Thêm mới danh mục" href="<%=request.getContextPath()%>/admin/create-category.htm"><button type="button" class="btn btn-success btn-sm mr-2"><i class="menu-icon ti-plus"></i>&nbsp; Thêm mới</button></a>
                                </div>
                                <div class="col-md-6 col-xs-6">
                                    <p style="float: right" class="">
                                        <a data-placement="top" data-toggle="tooltip" class="btn btn-info btn-sm mr-2" title="Tới trang danh mục cấp 1" href="<%=request.getContextPath()%>/admin/categories.htm"><i class="menu-icon ti ti-arrow-right"></i> Danh mục</a>
                                    </p>
                                </div>
                            </div>
                            <table id="myTable" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Tên danh mục</th>
                                        <th>Độ ưu tiên</th>
                                        <th>Danh mục cha</th>
                                        <th>Trạng thái</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listParentCategory}" var="c">
                                        <tr>
                                            <td>${c.categoryName}</td>
                                            <td>${c.categoryPiority}</td>
                                            <td>${c.parentName}</td>
                                            <td>
                                                <c:if test="${c.categoryStatus == 1}">
                                                    <p class="badge badge-pill badge-success" style="padding: 4px 9px 5px 9px;">Kích hoạt</p>
                                                </c:if>
                                                <c:if test="${c.categoryStatus == 0}">
                                                    <p class="badge badge-pill badge-dark" style="padding: 4px 9px 5px 9px;">Tạm ẩn</p>
                                                </c:if>
                                            </td>
                                            <td>
                                                <a class="mr-3" href="update-category.htm?categoryId=${c.categoryId}" data-toggle="tooltip" data-placement="top-center" title="Chỉnh Sửa"><span class="ti-pencil"></span></a>
                                                <a class="btn-delete" href="" data-id="${c.categoryId}" ><span data-toggle="tooltip" data-placement="top" title="Xóa" class="ti-trash"></span></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- .animated -->
    </div><!-- .content -->
</div><!-- /#right-panel -->
<!-- Right Panel -->

<div class="modal fade" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticModalLabel">Thông báo</h5>
            </div>
            <div class="modal-body">
                <p>
                    Bạn có muốn xóa không ?
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Không</button>
                <button id="confirmdelete" type="button" class="btn btn-primary">Xác nhận</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="widget/footer.jsp" flush="true"/>
<script src="${pageContext.request.contextPath}/view/admin/assets/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/view/admin/assets/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="${pageContext.request.contextPath}/view/admin/assets/vendors/datatables.net-buttons/js/buttons.colVis.min.js"></script>
<script src="${pageContext.request.contextPath}/view/admin/assets/bootstrap-notify-3.1.3/dist/bootstrap-notify.min.js" type="text/javascript"></script>
<c:if test="${not empty success}">
    <script>
        (function ($) {
            $.notify({
                title: '<strong>Success: </strong>',
                message: '${success}'
            }, {
                type: 'success'
            });
        })(jQuery);
    </script>
</c:if>
<script>
    (function ($) {
        $("#myTable").DataTable({
            "oLanguage": {
                "oPaginate": {
                    "sPrevious": "Trang trước",
                    "sNext": "Trang sau",
                    "sLast": "Trang cuối",
                    "sFirst": "Trang đầu"
                },
                //search
                "sSearch": "Tìm kiếm:",
                "sLengthMenu": "Hiện thị _MENU_ số hàng",
                "sInfo": "Trang _START_ tổng _TOTAL_ (_START_ to _END_)",
                "sInfoEmpty": 'Không có gì để hiển thị',
                "sEmptyTable": "Không có dữ liệu, click vào <span style='font-weight:700'>Thêm mới</span> để thêm dữ liệu"
            },
            "order": [[2, "asc"]]
        });
    })(jQuery);
    
    (function ($) {
        $(".btn-delete").on("click", function (e) {
            e.preventDefault();
            var id = $(this).data("id");
            $("#staticModal").modal('show');
            $("#confirmdelete").on("click", function () {
                $.ajax({
                    type: 'GET',
                    url: "delete-category.htm",
                    data: {categoryId: id},
                    success: function () {
                        $("#staticModal").modal('hide');
                        window.location.reload();
                    }
                });
            });
        });
    })(jQuery);
</script>
