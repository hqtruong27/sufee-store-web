<%-- 
    Document   : user-wishlist
    Created on : Jul 29, 2020, 10:38:19 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/view/client/home-index.jsp">Trang chủ</a></li>
            <li class="active">Danh sách yêu thích</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <aside class="col-md-3">
            <div class="avt">
                <img class="img-avt" src="<%=request.getContextPath()%>/view/client/uploads/images/Customers/${customers.avatar}" alt="Ảnh đại diện cá nhân" />
                <div class="">
                    <div class="">Tài khoản của</div>
                    <strong>${customers.fullname}</strong>
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
        <div class="col-md-9">
            <div class="row">
                <c:if test="${not empty wishlistses}">
                    <c:forEach items="${wishlistses}" var="wishlist">
                        <div class="col-md-4 col-sm-6 col-xs-6">
                            <div class="product product-single">
                                <c:forEach items="${products}" var="product">
                                    <c:if test="${product.productId == wishlist.products.productId}">
                                        <div class="product-thumb">
                                            <img src="${product.featureImage}" alt="Intel Optane Memory 32GB - M.2 NVMe Read 1350MB/s Write 290MB/s" />
                                        </div>
                                        <div class="product-body">
                                            <h2 class="product-name"><a href="san-pham/chi-tiet.htm?productId=${product.productId}">${product.productName}</a></h2>
                                            <h3 class="product-price"><fmt:formatNumber value="${product.price}"/></h3>
                                            <div class="product-rating">
                                                <i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i>
                                            </div>
                                            <div class="product-btns">
                                                <a href="" class="main-btn icon-btn" data-id="${product.productId}"><i class="fa fa-heart-o active"></i></a>
                                                <a href="" class="primary-btn add-to-cart pull-right" data-id="${product.productId}"><i class="fa fa-shopping-cart"></i> Mua ngay</a>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>
</section>
<jsp:include page="widget/footer.jsp" flush="true"/>

