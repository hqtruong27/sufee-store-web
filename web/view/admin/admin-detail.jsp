<%-- 
    Document   : admin-profile
    Created on : Jul 6, 2020, 2:02:24 PM
    Author     : ASUS
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
                            <i class="ti-comment" title="Phản hồi khách hàng"></i>
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


                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title mb-3">Tài khoản</strong>
                        </div>
                        <div class="card-body">
                            <div class="mx-auto d-block">
                                <img class="rounded-circle mx-auto d-block" src="${pageContext.request.contextPath}/view/admin/uploads/avatar/${admin.avatar}" style="width: 95px" alt="Card image cap">
                                <h5 class="text-sm-center mt-2 mb-1">${admin.fullname}</h5>
                                <div class="location text-sm-center"><i class="menu-icon mr-2 text-secondary ti-email"></i> ${admin.email}</div>
                                <div class="location text-sm-center"><i class="menu-icon mr-2 text-secondary fa fa-phone"></i> ${admin.phone}</div>
                            </div>
                            <hr>
                            <div class="card-text text-sm-center">
                                <a href="#"><i class="fa fa-facebook pr-1"></i></a>
                                <a href="#"><i class="fa fa-twitter pr-1"></i></a>
                                <a href="#"><i class="fa fa-linkedin pr-1"></i></a>
                                <a href="#"><i class="fa fa-pinterest pr-1"></i></a>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title mb-3">Thông tin cá nhân</strong>
                        </div>
                        <div class="card-body">
                            <div class="mx-auto d-block">

                                <h5 class="text-sm mt-2 mb-1">${admin.fullname}</h5>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  fa fa-user"></i> Giới tính: <c:if test="${admin.gender == 1}">Nam</c:if><c:if test="${admin.gender == 0}">Nữ</c:if></div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  fa fa-calendar-o"></i> Ngày sinh: ${admin.birthday}</div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  ti-email"></i> Email: ${admin.email}</div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  fa fa-phone"></i> Điện thoại: ${admin.phone}</div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  ti-eye"></i> Địa chỉ: ${admin.adminAddress}</div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  fa fa-id-card-o"></i> CMND/CCCD: ${admin.idCard}</div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  fa fa-power-off"></i> Trạng thái: <c:if test="${admin.status == 1}">Kích hoạt</c:if><c:if test="${admin.status == 0}">Tạm khoá</c:if></div>
                                </div>
                                <hr>
                                <div class="row form-group">
                                    <div class="col col-md-3">
                                            <a href="update-admin.htm?adminId=${admin.adminId}" type="reset" class="btn btn-danger btn-sm">
                                        <i class="ti-back-left"></i> Sửa
                                    </a>
                                    <a href="list-admin.htm" type="reset" class="btn btn-danger btn-sm">
                                        <i class="ti-back-left"></i> Trở lại
                                    </a>
                                </div>
                            </div>


                            <div class="card-text text-sm-center">
                                <a href="#"><i class="fa fa-facebook pr-1"></i></a>
                                <a href="#"><i class="fa fa-twitter pr-1"></i></a>
                                <a href="#"><i class="fa fa-linkedin pr-1"></i></a>
                                <a href="#"><i class="fa fa-pinterest pr-1"></i></a>
                            </div>
                        </div>

                    </div>
                </div>



            </div><!-- .row -->
        </div><!-- .animated -->
    </div><!-- .content -->


</div><!-- /#right-panel -->

<!-- Right Panel -->
<jsp:include page="widget/footer.jsp" flush="true"/>