<%-- 
    Document   : category-insert
    Created on : Jul 7, 2020, 10:44:58 AM
    Author     : Hoang Truong
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
                            <strong>Thêm mới danh mục sản phẩm</strong> 
                        </div>
                        <div class="card-body card-block">
                            <f:form action="create-category.htm" method="POST" commandName="category" class="form-horizontal">    
                                <div class="row form-group">
                                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Tên danh mục</label></div>
                                    <div class="col-12 col-md-9">
                                        <f:input path="categoryName" type="text" id="text-input" name="text-input"  class="form-control" />
                                        <c:if test="${not empty errorcategoryname}"><small class="form-text text-danger">${errorcategoryname}</small></c:if>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col col-md-3"><label for="select" class=" form-control-label">Danh mục cha</label></div>
                                        <div class="col-md-9">
                                            <select name="parentId" id="parentId" class="form-control" required oninvalid="this.setCustomValidity('Vui lòng chọn danh mục')" oninput="this.setCustomValidity('')">
                                                <option value="">Vui lòng chọn</option>
                                                <option value="0">Không có danh mục cha</option>
                                            <c:if test="${not empty listcategories}">
                                                <c:forEach items="${listcategories}" var="c">
                                                    <option value="${c.categoryId}">${c.categoryName}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col col-md-3">
                                        <label for="select" class=" form-control-label">Trạng thái </label>
                                    </div>
                                    <div class=" col-md-9">
                                        <input  id="categoryStatus" class="" type="radio" name="categoryStatus" value="1" checked="" />
                                        <label for="categoryStatus">kích hoạt</label>
                                        <input  id="deActive" class="" type="radio" name="categoryStatus" value="0" />
                                        <label for="deActive">Tạm ẩn</label>
                                        <c:if test="${not empty errorcategorystatus}"><small class="form-text text-danger">${errorcategorystatus}</small></c:if>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class=" col-md-3">
                                            <button type="submit" class="btn btn-success btn-sm">
                                                <i class="ti-check"></i> Xác nhận
                                            </button>
                                            <button onclick="javascript:history.go(-1)" type="submit" class="btn btn-danger btn-sm">
                                                <i class="ti-back-left"></i> Trở lại
                                            </button>
                                        </div>
                                    </div>
                            </f:form>
                        </div>
                    </div>
                </div>
            </div><!-- .animated -->
        </div><!-- .content -->
    </div><!-- /#right-panel -->
    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card" style="border: none">
                        <div class="card-header" style="background: #f1f2f7;border: none">
                        </div>
                    </div>
                </div>
            </div><!-- .animated -->
        </div><!-- .content -->
    </div><!-- /#right-panel -->
    <jsp:include page="widget/footer.jsp" flush="true"/>
    <script src="${pageContext.request.contextPath}/view/admin/assets/bootstrap-notify-3.1.3/dist/bootstrap-notify.min.js" type="text/javascript"></script>

    <c:if test="${not empty error}">
        <script>
                                                (function ($) {
                                                    $.notify({
                                                        title: '<strong>Error !!</strong>',
                                                        message: '${error}'
                                                    }, {
                                                        type: 'danger'
                                                    });
                                                })(jQuery);
        </script>
    </c:if>
