<%-- 
    Document   : dashboard
    Created on : Jun 30, 2020, 4:40:35 PM
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
                            <i class="ti-comment" title="Phản hồi khách hàng"></i>
                            <c:if test="${not empty countNotifyFeedback}">
                                <span class="count bg-primary">${countNotifyFeedback}</span>
                            </c:if>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="message">
                            <p class="red">Phản hồi gần đây</p>
                            <c:forEach items="${listFeedback}" var="notifyFeedback">
                                <a class="dropdown-item media bg-flat-color-0" href="<%=request.getContextPath()%>/admin/feedback-detail.htm?feedbackId=${notifyFeedback.feedbackId}">
                                    <span class="message media-body" >
                                        <span class="name float-left">${notifyFeedback.fullname}</span>
                                        <p class="time float-left">${notifyFeedback.content}</p>
                                        <p class="time float-left"><fmt:formatDate pattern="dd/MM/yyyy" value="${notifyFeedback.feedbacksTime}"/></p>
                                    </span>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-5">
                <c:if test="${not empty InfoAdmin}">
                    <div class="user-area dropdown float-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img class="user-avatar rounded-circle" src="${pageContext.request.contextPath}/view/admin/uploads/avatar/${InfoAdmin.avatar}" alt="User Avatar">
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
                        <li class="active">Dashboard</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <div class="content mt-3">
        <div class="col-sm-12">
            <div class="col-sm-6 col-lg-3">
                <div class="card text-white bg-flat-color-1">
                    <div class="card-body pb-0">

                        <h4 class="mb-0">
                            <span class="text-uppercase">Sản phẩm</span>
                        </h4>
                        <p class="text-light">Tổng sản phẩm</p>

                        <div class="chart-wrapper px-0 float-right d-inline-block" style="height:70px;" height="70">
                            <br>
                            <span>
                                <c:if test="${not empty countProduct}">
                                    <span class="count">${countProduct}</span>
                                </c:if> Sản phẩm
                            </span>
                        </div>

                    </div>

                </div>
            </div>
            <div class="col-sm-6 col-lg-3">
                <div class="card text-white bg-flat-color-2">
                    <div class="card-body pb-0">

                        <h4 class="mb-0">
                            <span class="text-uppercase">Đơn hàng</span>
                        </h4>
                        <p class="text-light">Tổng đơn hàng</p>

                        <div class="chart-wrapper px-0 float-right d-inline-block" style="height:70px;" height="70">
                            <br>
                            <span>
                                <c:if test="${not empty countOrder}">
                                    <span class="count">${countOrder}</span>
                                </c:if> Đơn hàng đã giao
                            </span>
                        </div>

                    </div>

                </div>
            </div>
            <div class="col-sm-6 col-lg-3">
                <div class="card text-white bg-flat-color-3">
                    <div class="card-body pb-0">

                        <h4 class="mb-0">
                            <span class="text-uppercase">Phản hồi</span>
                        </h4>
                        <p class="text-light">Tổng phản hồi chưa đọc</p>
                        <div class="chart-wrapper px-0 float-right d-inline-block" style="height:70px;" height="70">
                            <br>
                            <span>
                                <c:if test="${not empty countNotifyFeedback}">
                                    <span class="count">${countNotifyFeedback}</span>
                                </c:if> Phản hồi mới
                            </span>
                        </div>

                    </div>

                </div>
            </div>
            <div class="col-sm-6 col-lg-3">
                <div class="card text-white bg-flat-color-4">
                    <div class="card-body pb-0">

                        <h4 class="mb-0">
                            <span class="text-uppercase">Nhân viên</span>
                        </h4>
                        <p class="text-light">Nhân viên</p>

                        <div class="chart-wrapper px-0 float-right d-inline-block" style="height:70px;" height="70">
                            <br>
                            <span>
                                <c:if test="${not empty countAdmin}">
                                    <span class="count">${countAdmin}</span>
                                </c:if> Nhân viên
                            </span>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="col-sm-12">
                <div class="col-lg-9">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="mb-3">Đơn hàng theo tháng</h4>
                            <canvas id="barChart"></canvas>
                        </div>
                    </div>
                </div><!-- /# column -->
                <div class="col-xl-3 col-lg-6">
                    <div class="card">
                        <div class="card-body">
                            <div class="stat-widget-one">
                                <div class="stat-icon dib"><i class="ti-money text-success border-success"></i></div>
                                <div class="stat-content dib">
                                    <div class="stat-text">Tổng tiền</div>
                                    <div class="stat-digit"><c:if test="${not empty totalOrder}"><fmt:formatNumber value="${totalOrder}"/></c:if>₫</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <div class="stat-widget-one">
                                    <div class="stat-icon dib"><i class="ti-user text-primary border-primary"></i></div>
                                    <div class="stat-content dib">
                                        <div class="stat-text">Khách hàng</div>
                                        <div class="stat-digit"><c:if test="${not empty countCustomer}">${countCustomer}</c:if></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <div class="stat-widget-one">
                                    <div class="stat-icon dib"><i class="ti-layout-grid2 text-warning border-warning"></i></div>
                                    <div class="stat-content dib">
                                        <div class="stat-text">Hãng sản xuất</div>
                                        <div class="stat-digit"><c:if test="${not empty countBrand}">${countBrand}</c:if></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /#right-panel -->

        <!-- Right Panel -->

    <jsp:include page="widget/footer.jsp" flush="true"/>
    <script src="${pageContext.request.contextPath}/view/admin/assets/bootstrap-notify-3.1.3/dist/bootstrap-notify.min.js" type="text/javascript"></script>
    <c:if test="${not empty success}">
        <script>
            (function ($) {
                $.notify({
                    title: "<strong>Xin chào: </strong> ",
                    message: '${success}',
                    delay: 5000,

                }, {
                    type: 'success',
                    allow_dismiss: false
                });
            })(jQuery);
        </script>
    </c:if>

    <script>
        var arrayTenThang = [];
        var arrayDangChoDuyet = [];
        var arrayDaGiaoHang = [];
        var arrayDaHuyDon = [];
        var arrayColorDangChoDuyet = [];
        var arrayColorDaGiaoHang = [];
        var arrayColorDaHuyDon = [];
        var myChart;

        (function ($) {
            "use strict";
            var ctx = document.getElementById('barChart').getContext('2d');
            
        <c:forEach items="${listThongKe}" var="t">
            arrayTenThang.push("${t.tenThang}");
            arrayDangChoDuyet.push(${t.dangChoDuyet});
            arrayDaGiaoHang.push(${t.daGiaoHang});
            arrayDaHuyDon.push(${t.daHuyDon});
            arrayColorDangChoDuyet.push('#4e73df');
            arrayColorDaGiaoHang.push('#1cc88a');
            arrayColorDaHuyDon.push('#e74a3b');
        </c:forEach>
            myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: arrayTenThang,
                    datasets: [{
                            label: 'Đơn Hàng Đang Chờ',
                            data: arrayDangChoDuyet,
                            backgroundColor: arrayColorDangChoDuyet,
                        }, {
                            label: 'Đơn hàng đã giao',
                            data: arrayDaGiaoHang,
                            backgroundColor: arrayColorDaGiaoHang,
                        }, {
                            label: 'Đơn hàng đã hủy',
                            data: arrayDaHuyDon,
                            backgroundColor: arrayColorDaHuyDon,
                        }]
                },
                options: {
                    responsive: true,
                    scales: {
                        yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                    stepSize: 1
                                }
                            }]
                    }
                }
            });

        })(jQuery);

    </script>