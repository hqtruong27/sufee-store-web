<%-- 
    Document   : customer-list
    Created on : Jul 7, 2020, 10:44:58 AM
    Author     : ASUS
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/navbar.jsp" flush="true" />

<jsp:include page="widget/header.jsp" flush="true" />

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
                        <strong class="card-title">Danh mục Intro</strong>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6 col-xs-6">
                                <a data-toggle="tooltip" title="Thêm mới intro" href="<%=request.getContextPath()%>/admin/create-intro.htm"><button type="button" class="btn btn-success btn-sm mr-2"><i class="menu-icon ti-plus"></i>&nbsp; Thêm mới</button></a>
                            </div>
                        </div>
                        <table id="myTable" class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>Nội dung Intro</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listIntro}" var="intro">
                                    <tr>
                                        <td>${intro.introductionContent}</td>
                                        <td>
                                            <c:if test="${intro.introductionStatus == 1}">
                                                <label style="border-radius: 20px;padding: 0px;width: 40%" class="btn btn-success btn-sm">Hiển thị</label>
                                            </c:if>
                                            <c:if test="${intro.introductionStatus == 0}">
                                                <label style="border-radius: 20px;padding: 0px;width: 40%" class="btn btn-secondary btn-sm">Tạm ẩn</label>
                                            </c:if>
                                        </td>
                                        <td>
                                            <a class="mr-3" href="update-intro.htm?introductionId=${intro.introductionId}" data-toggle="tooltip" data-placement="top-center" title="Chỉnh Sửa"><span class="ti-pencil"></span></a>
                                            <a data-id="${intro.introductionId}" class="btn-delete" href="" data-toggle="modal" data-target="#staticModal"><span data-toggle="tooltip" data-placement="top" title="Xóa" class="ti-trash"></span></a>
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
<script src="${pageContext.request.contextPath}/view/admin/assets/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="${pageContext.request.contextPath}/view/admin/assets/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="${pageContext.request.contextPath}/view/admin/assets/vendors/datatables.net-buttons/js/buttons.colVis.min.js"></script>
<script src="${pageContext.request.contextPath}/view/admin/assets/bootstrap-notify-3.1.3/dist/bootstrap-notify.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/view/admin/assets/js/vietsub-datatable.js" type="text/javascript"></script>
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
        $(".btn-delete").on("click", function (e) {
            e.preventDefault();
            var id = $(this).data("id");
            $("#staticModal").modal('show');
            $("#confirmdelete").on("click", function () {
                $.ajax({
                    type: 'GET',
                    url: "delete-intro.htm",
                    data: {introductionId: id},
                    success: function () {
                        $("#staticModal").modal('hide');
                        window.location.reload();
                    }
                });
            });
        });
    })(jQuery);
</script>