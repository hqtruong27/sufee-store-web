<%-- 
    Document   : customer-list
    Created on : Jul 7, 2020, 10:44:58 AM
    Author     : ASUS
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/header.jsp" flush="true"/>
<jsp:include page="widget/navbar.jsp" flush="true"/>
<div id="right-panel" class="right-panel" >
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
    <div class="breadcrumbs">
        <div class="col-sm-4">
            <div class="page-header float-left">
                <div class="page-title">
                    <h1>Dashboard</h1>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="page-header float-right">
                <div class="page-title">
                    <ol class="breadcrumb text-right">
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Table</a></li>
                        <li class="active">Data table</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">

                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">Phản hồi khách hàng</strong>
                        </div>
                        <div class="card-body">
                            <p> 
                                <a href="<%=request.getContextPath()%>/admin/feedback-catalog.htm"><button type="button" class="btn btn-success btn-sm mr-2"><i class="menu-icon ti-layout-tab-window"></i>&nbsp; Danh mục phản hồi</button></a>
                            </p>
                            <table id="myTable" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Tên khách hàng</th>
                                        <th>Email</th>
                                        <th>Số điện thoại</th>
                                        <th>Thời gian</th>
                                        <th>Địa chỉ</th>
                                        <th>Trạng thái</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listFeedbacks}" var="list">
                                        <tr id="rows_${list.feedbackId}">
                                            <td>${list.fullname}</td>
                                            <td>${list.email}</td>
                                            <td>${list.phone}</td>
                                            <td><i class="ti-timer mr-2 text-primary"></i><fmt:formatDate pattern="dd/MM/yyyy" value="${list.feedbacksTime}"/></td>
                                            <td>${list.feedbackAddress}</td>
                                            <td> 
                                                <c:if test="${list.feedbackStatus == 1}">
                                                    <label style="border-radius: 20px;padding: 0px;width: 90%" class="btn btn-success btn-sm">Đã đọc</label>
                                                </c:if>
                                                <c:if test="${list.feedbackStatus == 0}">
                                                    <label style="border-radius: 20px;padding: 0px;width: 90%" class="btn btn-danger btn-sm">Chưa đọc</label>
                                                </c:if>

                                            </td>
                                            <td style="text-align: center">
                                                <a class="mr-3" href="<%=request.getContextPath()%>/admin/feedback-detail.htm?feedbackId=${list.feedbackId}" data-toggle="tooltip" data-placement="bottom" title="Chi tiết"><span class="ti-eye"></span></a>
                                                <a class="btn-delete" href="" data-id="${list.feedbackId}" ><span data-toggle="tooltip" data-placement="top" title="Xóa" class="ti-trash"></span></a>
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
                <button type="button" id="confirmdelete" class="btn btn-primary">Xác nhận</button>
            </div>
        </div>
    </div>
</div>


<jsp:include page="widget/footer.jsp" flush="true"/>
<script src="${pageContext.request.contextPath}/view/admin/assets/bootstrap-notify-3.1.3/dist/bootstrap-notify.min.js" type="text/javascript"></script>

<c:if test="${not empty success}">
    <script>
        (function ($) {
            $.notify({
                title: '<strong>Success: </strong>',
                message: '${success}'
            }, {
                type: 'success',
                allow_dismiss: false
            });
        })(jQuery);
    </script>
</c:if>
<c:if test="${not empty error}">
    <script>
        (function ($) {
            $.notify({
                title: '<strong>Error: </strong>',
                message: '${error}'
            }, {
                type: 'danger',
                allow_dismiss: false
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
                //searcz
                "sSearch": "Tìm kiếm:",
                "sLengthMenu": "Hiện thị _MENU_ số hàng",
                "sInfo": "Trang _START_ tổng _TOTAL_ (_START_ to _END_)",
                "sInfoEmpty": 'Không có gì để hiển thị',
                "sEmptyTable": "Không có dữ liệu, click vào <span style='font-weight:700'>Thêm mới</span> để thêm dữ liệu"
            },
            "order": [[1, "asc"]]
        });
        //
        $(".btn-delete").on("click", function (e) {
            e.preventDefault();
            var id = $(this).data("id");
            $(this).addClass("delete_feedback");
            $("#staticModal").modal('show');
        });
        //confirmdelete
        $("#confirmdelete").on("click", function () {
            var id = $(".delete_feedback").data('id');
            $.ajax({
                type: 'GET',
                url: "delete-feedback.htm",
                data: {feedbackId: id},
                success: function (e) {
                    if (e === "success") {
                        $("#rows_" + id).remove();
                        $("#staticModal").modal('hide');
                        $.notify({
                            title: '<strong>Success: </strong>',
                            message: 'Xoá phản hồi thành công !'
                        }, {
                            type: 'success',
                            allow_dismiss: false
                        });
                    } else {
                        $("#staticModal").modal('hide');
                        console.log(e);
                        $.notify({
                            title: '<strong>Error </strong>',
                            message: 'Có gì đó không đúng'
                        }, {
                            type: 'danger',
                            allow_dismiss: false
                        });
                    }
                }
            });
        });
    })(jQuery);

</script>