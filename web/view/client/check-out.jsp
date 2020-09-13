<%-- 
    Document   : check-out
    Created on : Jul 29, 2020, 11:19:16 AM
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
            <form id="checkout-form" class="clearfix" method="post" action="dat-hang.htm">
                <input name="__RequestVerificationToken" type="hidden" value="Jl5mW4jKDXVoV0_QCdzwASDq0hdoXaALEEy_PGPtm-X8MCa4y6WE0qKwiJZut0Bm6flMIE2yIz7VAh3Lb4VhtfAPFmczikpiv0xRxv1x4vk1" />
                <input type="hidden" name="customerId" <c:if test="${not empty customers}"> value="${customers.customerId}"</c:if> />
                    <div class="col-md-6">
                        <div class="billing-details">
                            <div class="section-title">
                                <h3 class="title">Chi tiết đặt hàng</h3>
                            </div>
                            <div class="form-group">
                                <input class="input" type="text" name="fullName" <c:if test="${not empty customers}"> value="${customers.fullname}"</c:if> placeholder="Họ và tên">
                            </div>
                            <div class="form-group">
                                <input class="input" type="email" name="email" <c:if test="${not empty customers}"> value="${customers.email}"</c:if> placeholder="Email"/>
                            </div>
                            <div class="form-group">
                                <input class="input" type="text" name="phone" <c:if test="${not empty customers}"> value="${customers.phone}"</c:if> placeholder="Điện thoại">
                            </div>
                            <div class="form-group">
                                <input class="input" type="text" name="orderAddress" <c:if test="${not empty customers}"> value="${customers.customerAddress}"</c:if> placeholder="Địa chỉ">
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="payments-methods">
                            <div class="section-title">
                                <h3 class="title">Phương thức thanh toán</h3>
                            </div>
                            <table class="table" style="border: 1px solid #DADADA; ">
                                <tbody>
                                <c:forEach items="${carts}" var="c">
                                    <tr>
                                        <td><span>${c.productQuantity}x </span>${c.products.productName}</td>
                                        <td><fmt:formatNumber value="${c.products.price * (100 - c.products.productSale) / 100 * c.productQuantity}"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <table class="shopping-cart-table table" id="all-price">
                        <tbody>
                            <tr>
                                <th class="empty" colspan="3"></th>
                                <th>Tổng tiền</th>
                                <th class="total">
                                    <fmt:formatNumber value="${shoppingCart.totalAmount}"/> ₫
                                </th>
                            </tr>
                        <input type="hidden" name="orderTotalAmount" value="${shoppingCart.totalAmount}" />
                        </tbody>
                    </table>
                </div>
                <div class="clearfix"><br></div>
                <div class="col-md-6 col-md-offset-6 text-right">
                    <div class="form-inline">
                        <button class="primary-btn" type="submit">Đặt hàng</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>

<script>
    $(document).ready(function () {
        $("#same-address").change(function (event) {
            event.preventDefault();
            var customerAddress = $("#CustomerAddress").val();

            if ($(this).is(":checked")) {
                $("#OrderAddress").val(customerAddress);
            } else {
                $("#OrderAddress").val("");
            }
        });
    });
</script>
<jsp:include page="widget/footer.jsp" flush="true"/>
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