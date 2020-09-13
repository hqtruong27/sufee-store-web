<%-- 
    Document   : admin-info
    Created on : Aug 7, 2020, 10:37:35 AM
    Author     : Kai
--%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
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
                        <div class="card-header"><strong class="card-title mb-3">Tài khoản</strong></div>
                        <div class="card-body">
                            <div class="mx-auto d-block">
                                <img class="rounded-circle mx-auto d-block" src="${pageContext.request.contextPath}/view/admin/uploads/avatar/${admins.avatar}" id="previewAvatar" alt="Card image cap" style="max-width: 250px;max-height: 250px">
                                <h5 class="text-sm-center mt-2 mb-1">${admins.fullname}</h5>
                                <hr>
                                <h5 class="text-sm mt-2 mb-1">Thông tin liên lạc</h5>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  fa fa-user"></i> Giới tính: <c:if test="${admins.gender == 1}">Nam</c:if><c:if test="${admin.gender == 0}">Nữ</c:if></div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  fa fa-calendar-o"></i> Ngày sinh: ${admins.birthday}</div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  ti-email"></i> Email: ${admins.email}</div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  fa fa-phone"></i> Điện thoại: ${admins.phone}</div>
                                <div class="location text-sm"><i class="menu-icon mr-2 text-secondary  fa fa-power-off"></i> Trạng thái: <c:if test="${admins.status == 1}">Kích hoạt</c:if><c:if test="${admin.status == 0}">Tạm khoá</c:if></div>
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

                                <ul class="nav nav-tabs" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="cmtproduct-tab" data-toggle="tab" href="#cmtproduct" role="tab" aria-controls="cmtproduct" aria-selected="true">Sửa thông tin cá nhân</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="cmtorder-tab" data-toggle="tab" href="#cmtorder" role="tab" aria-controls="cmtorder" aria-selected="false">Đổi mật khẩu</a>
                                    </li>
                                </ul>
                                <div class="tab-content pl-3 p-1" id="myTabContent">
                                    <div class="tab-pane fade show active" id="cmtproduct" role="tabpanel" aria-labelledby="cmtproduct-tab">
                                        <div class="card-body ">
                                            <div class="row">
                                                <div class="col-md-12 col-xs-12">
                                                <f:form action="info-admin.htm" commandName="admins" method="POST" role="form" class="form-horizontal" enctype="multipart/form-data">    
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label for="adminId" class=" form-control-label">ID nhân viên</label></div>
                                                        <div class="col-12 col-md-9 form-input">
                                                            <f:input type="hidden" path="adminId" />
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label for="fullname"  class=" form-control-label">Tên nhân viên</label></div>
                                                        <div class="col-12 col-md-9">
                                                            <f:input path="fullname"  class="form-control"  />
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label for="email" class=" form-control-label">Email</label></div>
                                                        <div class="col-12 col-md-9">
                                                            <f:input type="email" path="email"  class="form-control"  />
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label for="birthday" class=" form-control-label">Ngày sinh</label></div>
                                                        <div class="col-12 col-md-9">
                                                            <fmt:formatDate value="${admins.birthday}" pattern="dd/MM/yyyy" var="myBirthday" />
                                                            <input type="text" name="birthday" value="${myBirthday}"  class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label for="idCard" class=" form-control-label">CMND/CCCD</label></div>
                                                        <div class="col-12 col-md-9">
                                                            <f:input type="text" path="idCard" class="form-control" />
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label for="adminAddress" class=" form-control-label">Địa chỉ</label></div>
                                                        <div class="col-12 col-md-9">
                                                            <f:input type="text" id="adminAddress" path="adminAddress"  class="form-control" />
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label for="phone" class=" form-control-label">Số điện thoại</label></div>
                                                        <div class="col-12 col-md-9">
                                                            <f:input type="text" id="phone" path="phone" class="form-control" />
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label for="" class=" form-control-label">Ảnh đại diện</label></div>
                                                        <div class="col-12 col-md-9">
                                                            <f:input class="avatar-uploader_file-input" type="file" id="avatar" path="avatar"/>
                                                        </div>
                                                    </div>   
                                                    <div class="row form-group">
                                                        <div class="col col-md-3"><label for="gender" class=" form-control-label">Giới tính</label></div>
                                                        <div class="col-12 col-md-9">
                                                            <c:choose>
                                                                <c:when test="${admins.gender == 1}">
                                                                    <input type="radio"  id="male" name="gender" value="1" checked=""/>
                                                                    <label>Nam</label>
                                                                    <input type="radio"  id="famale" name="gender" value="0" />
                                                                    <label>Nữ</label>
                                                                </c:when>
                                                                <c:when test="${admins.gender == 0}">
                                                                    <input type="radio"  id="male" name="gender" value="1" />
                                                                    <label>Nam</label>
                                                                    <input type="radio"  id="famale" name="gender" value="0" checked="" />
                                                                    <label>Nữ</label>
                                                                </c:when>
                                                            </c:choose>
                                                        </div>
                                                    </div>        
                                                    <div class="form-group">
                                                        <button type="submit" class="btn btn-success btn-sm m-r-10"><i class="ti-check"></i>Cập nhật</button>
                                                        <a href="${pageContext.request.contextPath}/admin/list-admin.htm" type="reset" class="btn btn-danger btn-sm"><i class="ti-back-left"></i> Trở lại</a>
                                                    </div>
                                                </f:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="cmtorder" role="tabpanel" aria-labelledby="cmtorder-tab">
                                    <div class="card-body ">
                                        <div class="col-md-12">
                                            <div class="col-md-9 formtt">
                                                <form action="change-admin-password.htm" method="POST" role="form">
                                                    <div class="form-cc">
                                                        <div class="form-label">
                                                            <label for="passwordOld">Mật khẩu cũ:</label>
                                                        </div>
                                                        <div class="form-input">
                                                            <input type="password" class="input" id="passwordOld" name="passwordOld" />
                                                        </div>
                                                    </div>
                                                    <div class="form-cc ">
                                                        <div class="form-label">
                                                            <label for="passwordNew">Mật khẩu mới:</label>
                                                        </div>
                                                        <div class="form-input">
                                                            <input type="password" class="input" id="passwordNew" name="passwordNew" />
                                                        </div>
                                                    </div>
                                                    <div class="form-cc ">
                                                        <div class="form-label">
                                                            <label for="repasswordNew">Nhập lại mật khẩu mới:</label>
                                                        </div>
                                                        <div class="form-input">
                                                            <input type="password" class="input" id="repasswordNew" name="repasswordNew" />
                                                        </div>
                                                    </div>
                                                    <div class="form-cc">
                                                        <div class="form-label"></div>
                                                        <div class="form-input">
                                                            <button type="submit" class="btn-submit">Đổi mật khẩu</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
<script src="${pageContext.request.contextPath}/view/admin/assets/bootstrap-notify-3.1.3/dist/bootstrap-notify.min.js" type="text/javascript"></script>
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
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#previewAvatar').attr('src', e.target.result);
                };
                reader.readAsDataURL(input.files[0]);
            }
        }
        $("#avatar").change(function () {
            readURL(this);
        });
    })(jQuery);
</script>
