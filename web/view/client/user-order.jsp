<%-- 
    Document   : user-order
    Created on : Jul 29, 2020, 10:47:05 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<c:if test="${not empty success}">
    <script>
        swal(
                'Thành công!',
                '${success}',
                'success'
                )
    </script>
</c:if>
<c:if test="${not empty error}">
    <script>
        swal(
                'Thành công!',
                '${error}',
                'error'
                )
    </script>
</c:if>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/view/client/home-index.jsp">Trang chủ</a></li>
            <li class="active">Đơn hàng</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <aside class="col-md-3">
            <div class="avt">
                <img class="img-avt" src="<%=request.getContextPath()%>/view/client/uploads/images/Customers/${customer.avatar}" alt="Ảnh đại diện cá nhân" />
                <div class="">
                    <div class="">Tài khoản của</div>
                    <strong>${customer.fullname}</strong>
                </div>

            </div>
            <ul class="item-ul">
                <li class="item-a is-active">
                    <a href="edit-profile.htm" title="Đổi thông tin người dùng">
                        <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 24 24" height="1em" width="1em" xmlns="http//www3.org/2000/svg">
                            <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"> </path>
                        </svg>
                        <span>Thông tin tài khoản</span>
                    </a>
                </li>
                <li class="item-a ">
                    <a href="<%=request.getContextPath()%>/change-password.htm" title="Đổi mật khẩu người dùng">
                        <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 24 24" height="1em" width="1em" xmlns="http//www3.org/2000/svg">
                            <path d="M19 2H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h4l3 3 3-3h4c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2zm-6 16h-2v-2h2v2zm2.07-7.75l-.9.92C13.45 11.9 13 12.5 13 14h-2v-.5c0-1.1.45-2.1 1.17-2.83l1.24-1.26c.37-.36.59-.86.59-1.41 0-1.1-.9-2-2-2s-2 .9-2 2H8c0-2.21 1.79-4 4-4s4 1.79 4 4c0 .88-.36 1.68-.93 2.25z"> </path>
                        </svg>
                        <span>Đổi mật khẩu</span>
                    </a>
                </li>
                <li class="item-a ">
                    <a href="${pageContext.request.contextPath}/wishlists.htm?customerId=${InfoCustomer.customerId}" title="Danh sách yêu thích">
                        <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 24 24" height="1em" width="1em" xmlns="http//www3.org/2000/svg">
                            <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z" > </path>
                        </svg>
                        <span>Sản phảm yêu thích</span>
                    </a>
                </li>
                <li class="item-a ">
                    <a href="${pageContext.request.contextPath}/gio-hang.htm?customerId=${InfoCustomer.customerId}" title="Đơn hàng">
                        <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 24 24" height="1em" width="1em" xmlns="http//www3.org/2000/svg">
                            <path d="M13 12h7v1.5h-7zm0-2.5h7V11h-7zm0 5h7V16h-7zM21 4H3c-1.1 0-2 .9-2 2v13c0 1.1.9 2 2 2h18c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 15h-9V6h9v13z" > </path>
                        </svg>
                        <span>Quản lý đơn hàng</span>
                    </a>
                </li>
            </ul>
        </aside>
        <div class="col-md-9 formtt" id="order-to-append">
            <div class="table-responsive">
                <table class="table table-hover" style="border-collapse: collapse; border: none;">
                    <thead  style="border: none;">
                        <tr >
                            <th >Mã đơn hàng</th>
                            <th>Ngày mua</th>
                            <th class="text-center">Tổng tiền</th>
                            <th class="text-right">Trạng thái đơn hàng</th>
                        </tr>
                    </thead>
                    <tbody >
                        <c:forEach items="${listOrders}" var="od">
                            <tr >
                                <td style="border: none;" ><a href="order-detail.htm?orderId=${od.orderId}"  title="Chi tiết" style="color: rgb(0, 127, 240)">${od.orderId}</a></td>
                                <td  style="border: none;"><fmt:formatDate pattern="dd/MM/yyyy" value="${od.createDate}"/></td>
                                <td class="text-center "  style="border: none;"> <fmt:formatNumber value="${od.orderTotalAmount}" /> ₫</td>
                                <td class="text-right"  style="border: none;">
                                    <c:if test="${od.orderStatus == 0}">
                                        <label>Đang chờ xác nhận</label>
                                    </c:if>
                                    <c:if test="${od.orderStatus == 1}">
                                        <label>Đã giao hàng thành công</label>
                                    </c:if>
                                    <c:if test="${od.orderStatus == -1}">
                                        <label>Đơn hàng đã hủy</label>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="append-html">

            </div>
        </div>
    </div>
</div>
</section>

<script>
    $(document).ready(function () {
        $(".view-detail").click(function (event) {
            event.preventDefault();
            var orderId = $(this).data("id");

            $.ajax({
                url: '/Sufee_Store/order-detail.htm',
                data: {OrderId: orderId},
            }).done(function (res) {
                $("#append-html").html(res);
            });
        });
    });
</script>
<jsp:include page="widget/footer.jsp" flush="true"/>