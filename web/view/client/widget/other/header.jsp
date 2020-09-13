
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Trang chủ</title>

        <link href="${pageContext.request.contextPath}/view/client/assets/customerclient.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans+SC:100,300,400,500,700&amp;subset=vietnamese" rel="stylesheet" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/client/assets/css/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/client/assets/css/slick.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/client/assets/css/slick-theme.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/client/assets/css/nouislider.min.css" />
        <link type="text/css" rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/client/assets/css/style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/client/assets/libs/css/sweetalert2.min.css">
        <script src="${pageContext.request.contextPath}/view/client/assets/libs/js/sweetalert2.min.js"></script>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/view/client/uploads/logo-sufee.png" style="width: 100%"/>


        <script src="${pageContext.request.contextPath}/view/client/assets/js/jquery.min.js"></script>
    </head>

    <body>
        <header>
            <div id="top-header">
                <div class="container">
                    <div class="pull-left">
                    </div>
                    <div class="pull-right">
                        <ul class="header-top-links">
                            <li><a href="tel:0858448627"><span><i class="fa fa-phone"></i></span> Tư vấn mua hàng:<span style="color: #64B5F6"> 1900 0019</span></a></li>
                            <li><a href="tel:0858448627"><span><i class="fa fa-phone"></i></span> CSKH:<span style="color: #64B5F6"> 1900 0019</span></a></li>
                            <li><a href="${pageContext.request.contextPath}/news-list-catalog.htm?catalogId=1"><span><i class="fa fa-television"></i></span><span> Tin tức công nghệ</span></a></li>
                            <li><a href="${pageContext.request.contextPath}/feedback.htm"><span><i class="fa fa-envelope-open-o"></i></span><span> Đánh giá</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <style>

            </style>
            <div id="header">
                <div class="container">
                    <div class="pull-left dp-flex">
                        <div id="responsive-nav" class="dp-flex" style="margin-right: 50px">
                            <a href="${pageContext.request.contextPath}/trang-chu.htm"><img src="${pageContext.request.contextPath}/view/client/uploads/logo-sufee.png"></a>
                            <div class="category-nav show-on-click">
                                <span class="category-header"> <i class="fa fa-reorder"></i> Danh mục sản phẩm</span>
                                <ul class="category-list ">
                                    <c:if test="${not empty navHtml}">${navHtml}</c:if>
                                    </ul>
                                </div>
                            </div>
                            <div class="header-search">
                                <form action="${pageContext.request.contextPath}/tim-kiem.htm" method="POST">
                                    <input class="input search-input" name="productName" type="text" placeholder="Nhập từ khoá cần tìm...">
                                    <button class="search-btn"><i style="color:white " class="fa fa-search"></i></button>
                                </form>
                            </div>
                        </div>
                        <div class="pull-right" style="padding: 13.5px">
                            <ul class="header-btns">
                                <li class="header-account dropdown default-dropdown">
                                <c:if test="${empty InfoCustomer}">
                                    <div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
                                        <div class="header-btns-icon">
                                            <i class="fa fa-user"></i>
                                        </div>
                                        <p style="margin-left: -10px">Tài khoản</p>
                                    </div>
                                    <ul class="custom-menu">
                                        <li><a href="${pageContext.request.contextPath}/dang-nhap.htm" class="btn btn-primary" style="width:250px">Đăng nhập</a></li>
                                        <p/>
                                        <li><a href="${pageContext.request.contextPath}/dang-ky.htm" class="btn btn-primary" style="width:250px">Tạo tài khoản</a></li>
                                    </ul>
                                </c:if>
                                <c:if test="${not empty InfoCustomer}" >
                                    <div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
                                        <div class="">
                                            <img src="${pageContext.request.contextPath}/view/client/uploads/images/Customers/${InfoCustomer.avatar}" class="header-btns-icon"/>
                                        </div>

                                    </div>
                                    <ul class="custom-menu">
                                        <li >
                                            <div style="display: flex;margin-bottom: 15px" >
                                                <img src="${pageContext.request.contextPath}/view/client/uploads/images/Customers/${InfoCustomer.avatar}" style="width: 40px;border-radius: 50% ; height: 40px;margin-right:  7px"/>
                                                <div style="display: block">
                                                    <span>${InfoCustomer.fullname}</span>
                                                    <span>${InfoCustomer.email}</span>
                                                </div>
                                            </div>
                                        </li>
                                        <li><a href="${pageContext.request.contextPath}/edit-profile.htm"><i class="fa fa-user-o"></i>Thông tin cá nhân</a></li>
                                        <li><a href="${pageContext.request.contextPath}/wishlists.htm?customerId=${InfoCustomer.customerId}"><i class="fa fa-heart-o"></i>Danh sách yêu thích</a></li>
                                        <li><a href="${pageContext.request.contextPath}/gio-hang.htm?customerId=${InfoCustomer.customerId}"><i class="fa fa-gift"></i>Quản lý đơn hàng</a></li>
                                        <li><a href="${pageContext.request.contextPath}/logout.htm" class="btn btn-primary"></i> Đăng xuất</a></li>
                                    </ul>
                                </c:if>
                            </li>
                            <li class="header-cart dropdown default-dropdown" id="shopping-cart-ajax">
                                <c:if test="${not empty shoppingCart}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <div class="header-btns-icon">
                                            <i class="fa fa-shopping-cart"></i>
                                            <c:if test="${shoppingCart.carts.size() > 0}">
                                                <span class="qty">${shoppingCart.carts.size()}</span>
                                            </c:if>
                                            <c:if test="${shoppingCart.carts.size() == 0}">
                                                <span class="qty">${shoppingCart.carts.size()}</span>
                                            </c:if>
                                        </div>
                                        <p style="margin-left: -8px">Giỏ hàng</p>
                                    </a>
                                    <div class="custom-menu">
                                        <div id="shopping-cart">
                                            <div class="shopping-cart-list">
                                                <c:forEach items="${shoppingCart.carts}" var="c">
                                                    <div class="product product-widget">
                                                        <div class="product-thumb">
                                                            <img src="${c.products.featureImage}" alt="${c.products.productName}" />
                                                        </div>
                                                        <div class="product-body">
                                                            <c:if test="${c.products.productSale > 0}">
                                                                <h3 class="product-price"><fmt:formatNumber value="${c.products.price * (100 - c.products.productSale) / 100}" /> <span class="qty">x ${c.productQuantity}</span></h3>
                                                            </c:if>
                                                            <c:if test="${c.products.productSale == 0}">
                                                                <h3 class="product-price"><fmt:formatNumber value="${c.products.price}" /> <span class="qty">x ${c.productQuantity}</span></h3>
                                                            </c:if>
                                                            <h2 class="product-name"><a href="${pageContext.request.contextPath}/product/detail.html?productId=${c.products.productId}">${c.products.productName}</a></h2>
                                                        </div>
                                                        <button class="cancel-btn" data-id="${c.products.productId}"><i class="fa fa-trash"></i></button>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="shopping-cart-btns">
                                                <button class="main-btn medium-font" id="show-cart">Giỏ hàng</button>
                                                <button class="primary-btn medium-font" id="pay-cart">Thanh toán</button>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${empty shoppingCart}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <div class="header-btns-icon">
                                            <i class="fa fa-shopping-cart"></i>
                                            <span class="qty">0</span>
                                        </div>
                                        <p style="margin-left: -8px">Giỏ hàng</p>
                                    </a>
                                    <div class="custom-menu">
                                        <div id="shopping-cart">
                                            <div class="shopping-cart-list">

                                            </div>
                                            <div class="shopping-cart-btns">
                                                <button class="main-btn medium-font" id="show-cart">Giỏ hàng</button>
                                                <button class="primary-btn medium-font" id="pay-cart">Thanh toán</button>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </li>
                            <li class="nav-toggle">
                                <button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>