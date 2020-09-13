<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/header.jsp" flush="true"/>
<jsp:include page="widget/navbar.jsp" flush="true"/>
<!--Banner -->
<section id="home">
    <div class="container" style="width: 100%">
        <div class="home-wrap">
            <div id="home-slick">
                <c:forEach items="${banner}" var="banner">
                    <div class="">
                        <img src="${banner.bannerImage}"/>
                        <div class="banner-caption text-center"><h1>${banner.bannerDescription}</h1><h3 class="white-color font-weak text-border-neon">${banner.bannerDescription}</h3>
                            <!--<a class="primary-btn border-1-fff" href="/New/Detail/?NewId=1">Xem chi tiết</a>-->
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
<!--Top giảm giá-->
<section class="section">
    <div class="container">
        <div class="row" style="background-image: url(${pageContext.request.contextPath}/view/client/uploads/sale1.png)">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Top giảm giá</h2>
                    <div class="pull-right">
                        <div class="product-slick-dots-1 custom-dots"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-6">
                <div class="product-slick">
                    <div class="row">
                        <c:if test="${not empty productTopOneSale}">
                            <div class="product product-single product-hot">
                                <div class="product-thumb">
                                    <div class="product-label">
                                        <c:if test="${productTopOneSale.isNewProduct}">
                                            <span class="text-center">Mới</span>
                                        </c:if>
                                        <c:if test="${productTopOneSale.product.productSale > 0}">
                                            <span class="sale">-${productTopOneSale.product.productSale}%</span>
                                        </c:if>
                                    </div>
                                    <img src="${productTopOneSale.product.featureImage}"/>
                                </div>
                                <div class="product-body">
                                    <h2 class="product-name">
                                        <a href="san-pham/chi-tiet.htm?productId=${productTopOneSale.product.productId}" title="${productTopOneSale.product.productName}">
                                            <c:if test="${fn:length(productTopOneSale.product.productName) > 50}">
                                                ${fn:substring(productTopOneSale.product.productName, 0, 50)}...
                                            </c:if>
                                            <c:if test="${fn:length(productTopOneSale.product.productName) <= 50}">
                                                ${productTopOneSale.product.productName}
                                            </c:if>
                                        </a>
                                    </h2>
                                    <h4 class="product-price"><fmt:formatNumber value="${productTopOneSale.product.price}"/>₫</h4>

                                    <div class=""> ${productTopOneSale.priceString}</div>

                                    <p class="product-rating-for-you">
                                        ${productTopOneSale.productStarString}
                                    </p>
                                    <a href="" class="pull-right" data-id="${productTopOneSale.product.productId}"><i class="fa fa-heart-o <c:if test="${productTopOneSale.isWishlist}">active</c:if>"></i></a>
                                    </div>
                                </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-md-9 col-sm-6 col-xs-6">
                <div id="product-slick-1" class="product-slick">
                    <c:forEach items="${product12Sale}" var="product12Sale">
                        <div class="row">
                            <div class="product product-single ">
                                <div class="product-thumb">
                                    <div class="product-label">
                                        <c:if test="${product12Sale.isNewProduct}">
                                            <span class="text-center">Mới</span>
                                        </c:if>
                                        <c:if test="${product12Sale.product.productSale > 0}">
                                            <span class="sale">-${product12Sale.product.productSale}%</span>
                                        </c:if>
                                    </div>
                                    <img src="${product12Sale.product.featureImage}"/>
                                </div>
                                <div class="product-body">
                                    <h2 class="product-name">
                                        <a href="san-pham/chi-tiet.htm?productId=${product12Sale.product.productId}" title="${product12Sale.product.productName}">${product12Sale.product.productName}</a>
                                    </h2>
                                    <h4 class="product-price"><fmt:formatNumber value="${product12Sale.product.price}"/>₫</h4>

                                    <div class=""> ${product12Sale.priceString}</div>

                                    <p class="product-rating-for-you">
                                        ${product12Sale.productStarString}
                                    </p>
                                    <a href="" class="pull-right" data-id="${product12Sale.product.productId}"><i class="fa fa-heart-o <c:if test="${product12Sale.isWishlist}">active</c:if>"></i></a>
                                    </div>
                                </div>
                            </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
<!--Sản phẩm bán chạy-->
<section class="section" >
    <div class="container">
        <div class="row" style="background-image: url(${pageContext.request.contextPath}/view/client/uploads/background-sell.jpg)">
            <c:if test="${not empty product12SellQuantity || not empty productTopOneSell}">
                <div class="col-md-12">
                    <div class="section-title">
                        <h2 class="title">Sản phẩm bán chạy</h2>
                        <div class="pull-right">
                            <div class="product-slick-dots-2 custom-dots">
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="col-md-3 col-sm-6 col-xs-6">
                <div class="product-slick">
                    <div class="row">
                        <c:if test="${not empty productTopOneSell}">
                            <div class="product product-single">
                                <div class="product-thumb ">
                                    <div class="product-label">
                                        <c:if test="${productTopOneSell.isNewProduct}">
                                            <span class="text-center">Mới</span>
                                        </c:if>
                                        <c:if test="${productTopOneSell.product.saleQuantity > 0}">
                                            <span class="sale">Bán chạy nhất</span>
                                        </c:if>
                                    </div>
                                    <img src="${productTopOneSell.product.featureImage}" class="img-responsive" alt="${productTopOneSell.product.productName}">
                                </div>
                                <div class="product-body">
                                    <h2 class="product-name">
                                        <a href="san-pham/chi-tiet.htm?productId=${productTopOneSell.product.productId}" title="${productTopOneSell.product.productName}">
                                            <c:if test="${fn:length(productTopOneSell.product.productName) > 50}">
                                                ${productTopOneSell.product.productName}...
                                            </c:if>
                                            <c:if test="${fn:length(productNew.product.productName) <= 50}">
                                                ${productTopOneSell.product.productName}
                                            </c:if>
                                        </a>
                                    </h2>
                                    <h4 class="product-price"><fmt:formatNumber value="${productTopOneSell.product.price}"/>₫</h4>

                                    <div class=""> ${productTopOneSell.priceString}</div>

                                    <p class="product-rating-for-you">
                                        ${productTopOneSell.productStarString}
                                    </p>
                                    <a href="" class="pull-right" data-id="${productTopOneSell.product.productId}"><i class="fa fa-heart-o <c:if test="${productNew.isWishlist}">active</c:if>"></i></a>
                                    </div>
                                </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <c:if test="${not empty product12SellQuantity}">
                <div class="col-md-9 col-sm-6 col-xs-6">
                    <div id="product-slick-2" class="product-slick">
                        <c:forEach items="${product12SellQuantity}" var="p">
                            <div class="row">
                                <div class="product product-single">
                                    <div class="product-thumb">
                                        <div class="product-label">
                                            <c:if test="${p.isNewProduct}">
                                                <span class="text-center">Mới</span>
                                            </c:if>
                                            <c:if test="${p.product.productSale > 0}">
                                                <span class="sale">-${p.product.productSale}%</span>
                                            </c:if>
                                        </div>
                                        <img src="${p.product.featureImage}" class="img-responsive" alt="${p.product.productName}">
                                    </div>
                                    <div class="product-body">
                                        <h2 class="product-name">
                                            <a href="san-pham/chi-tiet.htm?productId=${p.product.productId}" title="${p.product.productName}">
                                                <c:if test="${fn:length(p.product.productName) > 50}">
                                                    ${p.product.productName}...
                                                </c:if>
                                                <c:if test="${fn:length(productNew.product.productName) <= 50}">
                                                    ${p.product.productName}
                                                </c:if>
                                            </a>
                                        </h2>
                                        <h4 class="product-price"><fmt:formatNumber value="${p.product.price}"/>₫</h4>

                                        <div class=""> ${p.priceString}</div>

                                        <p class="product-rating-for-you">
                                            ${p.productStarString}
                                        </p>
                                        <a href="" class="pull-right" data-id="${p.product.productId}"><i class="fa fa-heart-o <c:if test="${p.isWishlist}">active</c:if>"></i></a>
                                        </div>
                                    </div>
                                </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</section>
<!--Sản phẩm mới-->
<section class="section">
    <div class="container">
        <c:if test="${not empty fourNewProduct}">
            <div class="row" >
                <div class="col-md-12">
                    <div class="section-title">
                        <h2 class="title">Sản phẩm mới nhất</h2>
                    </div>
                </div>
                <c:forEach items="${fourNewProduct}" var="productNew">
                    <div class="col-md-3 col-sm-6 col-xs-6">
                        <div class="product product-single ">
                            <div class="product-thumb">
                                <div class="product-label">
                                    <c:if test="${productNew.isNewProduct}">
                                        <span class="text-center">Mới</span>
                                    </c:if>
                                    <c:if test="${productNew.product.productSale > 0}">
                                        <span class="sale">-${productNew.product.productSale}%</span>
                                    </c:if>
                                </div>
                                <img src="${productNew.product.featureImage}" class="img-responsive" alt="${productNew.product.productName}">
                            </div>
                            <div class="product-body">
                                <h2 class="product-name">
                                    <a href="san-pham/chi-tiet.htm?productId=${productNew.product.productId}" title="${productNew.product.productName}">
                                        <c:if test="${fn:length(productNew.product.productName) > 50}">
                                            ${productNew.product.productName}...
                                        </c:if>
                                        <c:if test="${fn:length(productNew.product.productName) <= 50}">
                                            ${productNew.product.productName}
                                        </c:if>
                                    </a>
                                </h2>
                                <h4 class="product-price"><fmt:formatNumber value="${productNew.product.price}"/>₫</h4>

                                <div class=""> ${productNew.priceString}</div>

                                <p class="product-rating-for-you">
                                    ${productNew.productStarString}
                                </p>
                                <a href="" class="pull-right" data-id="${productNew.product.productId}"><i class="fa fa-heart-o <c:if test="${productNew.isWishlist}">active</c:if>"></i></a>
                                </div>
                            </div>
                        </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</section>
<!--Dành cho bạn-->
<section class="section">
    <div class="container">
        <c:if test="${not empty product12foryou}">
            <div class="row">
                <div class="col-md-12">
                    <div class="section-title">
                        <h2 class="title">Dành cho bạn</h2>
                    </div>
                </div>
                <c:forEach items="${product12foryou}" var="product12foryou">
                    <div class="col-md-2 col-sm-6 col-xs-6 border-bottom-home">
                        <div class="product product-single ">
                            <div class="product-thumb-for-you">
                                <div class="product-btns">
                                    <a href="" class="main-btn quick-view add-to-cart pull-right" data-id="${product12foryou.product.productId}"><i class="fa fa-shopping-cart"></i></a>
                                </div>
                                <img src="${product12foryou.product.featureImage}" class="img-responsive" alt="${product12foryou.product.productName}">
                            </div>
                            <div class="product-body">
                                <h2 class="product-name-for-you">
                                    <a href="san-pham/chi-tiet.htm?productId=${product12foryou.product.productId}" title="${product12foryou.product.productName}">
                                        <c:if test="${fn:length(product12foryou.product.productName) > 50}">
                                            ${product12foryou.product.productName}...
                                        </c:if>
                                        <c:if test="${fn:length(product12foryou.product.productName) <= 50}">
                                            ${product12foryou.product.productName}
                                        </c:if>
                                    </a>
                                </h2>
                                <p class="product-price">${product12foryou.priceString}</p>
                                <div class="product-rating-for-you">
                                    ${product12foryou.productStarString}
                                </div>
                                <a href="" class="pull-right" data-id="${product12foryou.product.productId}"><i class="fa fa-heart-o <c:if test="${product12foryou.isWishlist}">active</c:if>"></i></a>
                                </div>
                            </div>
                        </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</section>
<div style="width: 100%;text-align: center;">
    <a href="${pageContext.request.contextPath}/san-pham.htm" class="primary-btn" style="min-width: 24.375rem;">Xem thêm</a>
</div>
<style>
    div .li{
        border: 1px solid
    }
</style>
<jsp:include page="widget/footer.jsp" flush="true"/>