<%-- 
    Document   : shopping-cart
    Created on : Jul 29, 2020, 10:31:20 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/view/client/home-index.jsp">Trang chủ</a></li>
            <li class="active">Thanh toán</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="order-summary clearfix">
                    <div class="section-title">
                        <h3 class="title">Giỏ hàng</h3>
                    </div>
                    <c:if test="${not empty carts}">
                        <table class="shopping-cart-table table">
                            <thead>
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Thông tin</th>
                                    <th class="text-center">Đơn giá</th>
                                    <th class="text-center">Số lượng</th>
                                    <th class="text-center">Giảm giá</th>
                                    <th class="text-center">Thành tiền</th>
                                    <th class="text-right"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${carts}" var="c">
                                    <tr>
                                        <td class="thumb">
                                            <img src="${c.products.featureImage}" alt="${c.products.productName}" />
                                        </td>
                                        <td class="details">
                                            <a href="/Product/Detail?ProductId=46">${c.products.productName}</a>
                                        </td>
                                        <td class="price text-center">
                                            <strong><fmt:formatNumber value="${c.products.price}" /></strong>
                                            <br>
                                        </td>
                                        <td class="qty text-center">
                                            <input class="input" id="product-in-cart${c.products.productId}" type="number" value="${c.productQuantity}" min="1"/>
                                        </td>
                                        <td class="price text-center">
                                            <strong><fmt:formatNumber value="${c.products.price * c.products.productSale / 100 * c.productQuantity}" /></strong>
                                            <br>
                                        </td>
                                        <td class="total text-center">
                                            <strong><fmt:formatNumber value="${c.products.price * (100 - c.products.productSale) / 100 * c.productQuantity}" /></strong>
                                            <br>
                                        </td>
                                        <td class="text-right">
                                            <button class="main-btn icon-btn update-one-cart" data-id="${c.products.productId}"><i class="fa fa-refresh"></i></button>
                                            <button class="main-btn icon-btn remove-one-cart" data-id="${c.products.productId}"><i class="fa fa-close"></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th class="empty" colspan="4"></th>
                                    <th>Tổng tiền</th>
                                    <th colspan="2" class="total">
                                        <fmt:formatNumber value="${shoppingCart.totalAmount}" />
                                    </th>
                                </tr>
                            </tfoot>
                        </table>
                        <div class="pull-right">
                            <a class="primary-btn remove-cart" href="">Xoá giỏ hàng</a>
                            <a class="primary-btn" href="${pageContext.request.contextPath}/dat-hang.htm">Thanh toán</a>
                        </div>
                    </c:if>
                    <c:if test="${empty carts}">
                        <div class="jumbotron">
                            <div class="container" style="text-align: center">
                                <p>
                                    <img src="${pageContext.request.contextPath}/view/client/uploads/suffeorder.png" style="width: 180px"/>
                                </p>
                                <p>Không có sản phẩm nào trong giỏ hàng của bạn.</p>
                                <p>
                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/trang-chu.htm">Tiếp tục mua sắm</a>
                                </p>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="widget/footer.jsp" flush="true"/>
