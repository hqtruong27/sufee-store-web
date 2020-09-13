<%-- 
    Document   : customer-order-detail
    Created on : Jun 26, 2019, 6:45:47 PM
    Author     : QuanKoiNA
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>


<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/index.html">Trang chủ</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/index.html">Cá nhân</a></li>
            <li class="active">Đơn hàng</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Đơn hàng</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3">
                <div class="list-user-function text-center medium-font upper-case">
                    <div class="function-item ">
                        <a href="${pageContext.request.contextPath}/customer/index.html" title="Thông tin cá nhân">Thông tin cá nhân</a>
                    </div>
                    <div class="function-item ">
                        <a href="${pageContext.request.contextPath}/customer/change-information.html" title="Đổi thông tin người dùng">Đổi thông tin người dùng</a>
                    </div>
                    <div class="function-item ">
                        <a href="${pageContext.request.contextPath}/customer/change-password.html" title="Đổi mật khẩu người dùng">Đổi mật khẩu</a>
                    </div>
                    <div class="function-item ">
                        <a href="${pageContext.request.contextPath}/customer/change-avatar.html" title="Đổi ảnh đại diện">Đổi ảnh đại diện</a>
                    </div>
                    <div class="function-item ">
                        <a href="${pageContext.request.contextPath}/customer/wishlist.html" title="Danh sách yêu thích">Danh sách yêu thích</a>
                    </div>
                    <div class="function-item function-active">
                        <a href="${pageContext.request.contextPath}/customer/order.html" title="Đơn hàng">Đơn hàng</a>
                    </div>
                </div>
            </div>
            <div class="col-md-9" id="order-to-append">
                <div class="step-content">
                    <div class="step-item-content step-content-active" id="first">
                        <div class="step-description">Đơn hàng đã được đặt thành công vào lúc <i class="fa fa-clock-o" style="margin-left: 10px;"></i> <span class="time"><fmt:formatDate value="${order.createdDate}" /></span></div>
                        <div class="order-detail">Chi tiết đơn hàng:</div>

                        <table class="table table-bordered table-hover table-striped table-condensed table-responsive">
                            <thead>
                                <tr>
                                    <th style="width: 30%;">Sản phẩm</th>
                                    <th style="width: 25%;">Hình ảnh</th>
                                    <th style="width: 10%;" class="text-center">Số lượng</th>
                                    <th>Đơn giá</th>
                                    <th>Giảm giá</th>
                                    <th>Thành tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="width: 30%;">
                                        <a href="${pageContext.request.contextPath}/product/detail.html?productId=">}</a>
                                    </td>
                                    <td style="width: 25%;">
                                        <img src="${p.productId.productFeatureImage}" class="img-responsive" alt="${p.productId.productName}">
                                    </td>
                                    <td style="width: 10%;" class="text-center">${p.orderDetailQuantity}</td>
                                    <td></td>
                                    <td>${p.orderDetailSale}%</td>
                                    <td></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6" class="text-right">
                                        <div class="shipping-fee"><b>Phí vận chuyển:</b> </div>
                                        <div class="total-amount"><b>Tổng tiền:</b></div>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="widget/footer.jsp" flush="true"/>
