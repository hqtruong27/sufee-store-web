<%-- 
    Document   : admin-profile
    Created on : Jul 6, 2020, 2:02:24 PM
    Author     : ASUS
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
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
                        <li><a href="#">UI Elements</a></li>
                        <li class="active">Cards</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title mb-3">Chi tiết phản hồi</strong>
                        </div>
                        <%--<f:form action="info-customer" commandName="customer" method="GET">--%>
                        <div class="card-body">
                            <div class="mx-auto d-block">
                                <div class="text-sm-center">
                                </div>
                                <table>
                                    <tbody>
                                        <tr>
                                            <td><i class="menu-icon mr-2 text-info  ti-arrow-circle-right"></i></td>
                                            <td><strong>Danh mục phản hồi:</strong></td>
                                            <c:forEach items="${listFeebackCatalog}" var="fbcatalog">
                                                <c:if test="${fbcatalog.feedbackCatalogId == feedbacks.feedbackCatalogs.feedbackCatalogId}">
                                                    <td>${fbcatalog.feedbackCatalogName}</td>
                                                </c:if>
                                            </c:forEach>
                                        </tr>
                                        <tr>
                                            <td><i class="menu-icon mr-2 text-info  fa fa-user"></i></td>
                                            <td><strong>Tên người gửi:</strong></td>
                                            <td>${feedbacks.fullname}</td>
                                        </tr>
                                        <tr>
                                            <td><i class="menu-icon mr-2 text-info  ti-email"></i></td>
                                            <td><strong>Email:</strong></td>
                                            <td>${feedbacks.email}</td>
                                        </tr>
                                        <tr>
                                            <td><i class="menu-icon mr-2 text-info  fa fa-phone"></i></td>
                                            <td><strong>Số điện thoại:</strong></td>
                                            <td>${feedbacks.phone}</td>
                                        </tr>
                                        <tr>
                                            <td><i class="menu-icon mr-2 text-info  fa fa-map"></i></td>
                                            <td><strong>Địa chỉ:</strong></td>
                                            <td>${feedbacks.feedbackAddress}</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <hr>
                                <div class="m-b-20">
                                    <p>
                                        <b>Nội dung phản hồi</b>
                                    </p>
                                    <span>${feedbacks.content}</span>
                                </div>
                                <hr>
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="btn btn-success btn-sm">
                                        <a class="location text-left text-white" href="<%=request.getContextPath()%>/admin/feedback.htm" data-toggle="tooltip" data-placement="top-center" title="Quay về"><i class="ti-back-left"></i> Trở lại</a>
                                    </div>
                                    <div class="float-xl-right float-none mt-xl-0 mt-4">
                                        <i class="menu-icon mr-2 text-info  ti-timer"></i>
                                        <span>${feedbacks.feedbacksTime}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--</f:form>--%>
                    </div>
                </div>
                <div class="col-md-4 ">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title mb-3">Thông tin giao dịch</strong>

                        </div>
                        <div class="card-body text-center">
                            <div class="card">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Danh mục phản hồi</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <c:forEach items="${listFeebackCatalog}" var="fbcatalog">
                                                    <p>
                                                        <a href="<%=request.getContextPath()%>/admin/feedback-list-cata.htm?feedbackCatalogId=${fbcatalog.feedbackCatalogId}">
                                                            ${fbcatalog.feedbackCatalogName}
                                                        </a>
                                                    </p>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div><!-- .row -->
            </div><!-- .animated -->
        </div><!-- .content -->


    </div><!-- /#right-panel -->

    <!-- Right Panel -->
    <jsp:include page="widget/footer.jsp" flush="true"/>