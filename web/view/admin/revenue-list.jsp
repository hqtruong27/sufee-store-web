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
                            <i class="fa fa-bell"></i>
                            <span class="count bg-danger">5</span>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="notification">
                            <p class="red">You have 3 Notification</p>
                            <a class="dropdown-item media bg-flat-color-1" href="#">
                                <i class="fa fa-check"></i>
                                <p>Server #1 overloaded.</p>
                            </a>
                            <a class="dropdown-item media bg-flat-color-4" href="#">
                                <i class="fa fa-info"></i>
                                <p>Server #2 overloaded.</p>
                            </a>
                            <a class="dropdown-item media bg-flat-color-5" href="#">
                                <i class="fa fa-warning"></i>
                                <p>Server #3 overloaded.</p>
                            </a>
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

    <!-- /# column -->
    <div class="col-lg-12">
        <div class="card">
            <div class="card-header">
                <h4>Thống kê doanh thu</h4>
            </div>
            <div class="card-body">


                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="promotion-tab" data-toggle="tab" href="#promotion" role="tab" aria-controls="promotion" aria-selected="true">Doanh thu theo khuyến mãi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="week-tab" data-toggle="tab" href="#week" role="tab" aria-controls="week" aria-selected="false">Doanh thu theo tuần</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="month-tab" data-toggle="tab" href="#month" role="tab" aria-controls="month" aria-selected="false">Doanh thu theo tháng </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="year-tab" data-toggle="tab" href="#year" role="tab" aria-controls="year" aria-selected="false">Doanh thu theo năm </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="time-tab" data-toggle="tab" href="#time" role="tab" aria-controls="time" aria-selected="false">Doanh thu theo khoảng thời gian </a>
                    </li>
                </ul>
                <div class="tab-content pl-3 p-1" id="myTabContent">
                    <div class="tab-pane fade show active" id="promotion" role="tabpanel" aria-labelledby="promotion-tab">
                        <p>
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Tổng doanh thu</strong>
                                </div>
                                <div class="card-body ">
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê doanh thu</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Số sản phẩm bán ra</th>
                                                    <th scope="col">Tổng số doanh thu</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">1</th>
                                                    <th scope="row">1</th>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê sản phẩm</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Tên sản phẩm</th>
                                                    <th scope="col">Giá</th>
                                                    <th scope="col">Số lượng đã bán</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Mark</td>
                                                    <td>Otto</td>
                                                    <td>@mdo</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="week" role="tabpanel" aria-labelledby="week-tab">
                        <p>
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Tổng doanh thu</strong>
                                </div>
                                <div class="card-body ">
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê doanh thu</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Số sản phẩm bán ra</th>
                                                    <th scope="col">Tổng số doanh thu</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">1</th>
                                                    <th scope="row">1</th>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê sản phẩm</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Tên sản phẩm</th>
                                                    <th scope="col">Giá</th>
                                                    <th scope="col">Số lượng đã bán</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Mark</td>
                                                    <td>Otto</td>
                                                    <td>@mdo</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="month" role="tabpanel" aria-labelledby="month-tab">
                        <p>
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Tổng doanh thu</strong>
                                </div>
                                <div class="card-body ">
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê doanh thu</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Số sản phẩm bán ra</th>
                                                    <th scope="col">Tổng số doanh thu</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">1</th>
                                                    <th scope="row">1</th>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê sản phẩm</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Tên sản phẩm</th>
                                                    <th scope="col">Giá</th>
                                                    <th scope="col">Số lượng đã bán</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Mark</td>
                                                    <td>Otto</td>
                                                    <td>@mdo</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="year" role="tabpanel" aria-labelledby="year-tab">
                        <p>
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Tổng doanh thu</strong>
                                </div>
                                <div class="card-body ">
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê doanh thu</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Số sản phẩm bán ra</th>
                                                    <th scope="col">Tổng số doanh thu</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">1</th>
                                                    <th scope="row">1</th>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê sản phẩm</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Tên sản phẩm</th>
                                                    <th scope="col">Giá</th>
                                                    <th scope="col">Số lượng đã bán</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Mark</td>
                                                    <td>Otto</td>
                                                    <td>@mdo</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="time" role="tabpanel" aria-labelledby="time-tab">
                        <p>
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong class="card-title">Tổng doanh thu</strong>
                                </div>
                                <div class="card-body ">
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê doanh thu</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Số sản phẩm bán ra</th>
                                                    <th scope="col">Tổng số doanh thu</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th scope="row">1</th>
                                                    <th scope="row">1</th>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-6">
                                        <h6 class="card-title"><b>Thống kê sản phẩm</b></h6>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Tên sản phẩm</th>
                                                    <th scope="col">Giá</th>
                                                    <th scope="col">Số lượng đã bán</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Mark</td>
                                                    <td>Otto</td>
                                                    <td>@mdo</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>
<!-- /# column -->


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
                <button type="button" class="btn btn-primary">Xác nhận</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="widget/footer.jsp" flush="true"/>