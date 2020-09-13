<%-- 
    Document   : user-info
    Created on : Jul 29, 2020, 10:56:12 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/view/client/home-index.jsp">Trang chủ</a></li>
            <li class="active">Trang cá nhân người dùng</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Trang cá nhân người dùng</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3">
                <div class="list-user-function text-center medium-font upper-case">
                    <div class="function-item function-active">
                        <a href="edit-profile.htm" title="Thông tin cá nhân">Thông tin cá nhân</a>
                    </div>
                    <div class="function-item ">
                        <a href="edit-profile.htm" title="Đổi thông tin người dùng">Đổi thông tin người dùng</a>
                    </div>
                    <div class="function-item ">
                        <a href="<%=request.getContextPath()%>/change-password.htm" title="Đổi mật khẩu người dùng">Đổi mật khẩu</a>
                    </div>
                    <div class="function-item ">
                        <a href="<%=request.getContextPath()%>/change-avatar.htm" title="Đổi ảnh đại diện">Đổi ảnh đại diện</a>
                    </div>
                    <div class="function-item ">
                        <a href="${pageContext.request.contextPath}/view/client/user-wishlist.jsp" title="Danh sách yêu thích">Danh sách yêu thích</a>
                    </div>
                    <div class="function-item ">
                        <a href="${pageContext.request.contextPath}/view/client/user-order.jsp" title="Đơn hàng">Đơn hàng</a>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <table class="personal-information">
                    <tr>
                        <th>Ảnh đại diện:</th>
                        <td>
                            <img src="${pageContext.request.contextPath}/view/client/uploads/images/Customers/${customer.avatar}" style=" border-radius: 50%; border: 1px solid #a1a1a1; height: 200px; margin: 20px; text-align: center; width: 200px;" alt="Ảnh đại diện cá nhân" />
                        </td>
                    </tr>
                    <tr>
                        <th>Họ tên:</th>
                        <td>${customer.fullname}</td>
                    </tr>
                    <tr>
                        <th>Ngày sinh:</th>
                        <td><fmt:formatDate pattern = "dd-MM-yyy" value = "${customer.birthday}" /></td>
                    </tr>
                    <tr>
                        <th>Giới tính:</th>
                        <td><c:if test="${customer.gender == 0}">Nữ</c:if><c:if test="${customer.gender == 1}">Nam</c:if></td>
                        </tr>
                        <tr>
                            <th>Điện thoại:</th>
                                <td>${customer.phone}</td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td>${customer.email}</td>
                    </tr>
                    <tr>
                        <th>Địa chỉ:</th>
                        <td>${customer.customerAddress}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</section>
<jsp:include page="widget/footer.jsp" flush="true"/>
