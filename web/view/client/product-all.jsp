<%-- 
    Document   : product-list
    Created on : Jul 29, 2020, 11:04:19 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/view/client/home-index.jsp">Trang chủ</a></li>
            <li class="active">Sản phẩm</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <article id="aside" class="col-md-3">
                <aside class="aside">
                    <h3 class="aside-title">Sắp xếp theo:</h3>
                    <div class="store-filter clearfix">
                        <div class="pull-left">
                            <div class="row-filter">
                                <a href="/Product/Index/?brandId=0&categoryId=0&view=grid&sort=1&pageSize=12&keyword=" class='active'><i class="fa fa-th-large"></i></a>
                                <a href="/Product/Index/?brandId=0&categoryId=0&view=list&sort=1&pageSize=12&keyword="><i class="fa fa-bars"></i></a>
                            </div>
                            <div class="sort-filter">
                                <span class="text-uppercase"></span>
                                <select class="input" id="sort-type-1">
                                    <option value="1" selected>Giá tăng dần</option>
                                    <option value="2" >Giá giảm dần</option>
                                    <option value="3" >Đánh giá tăng dần</option>
                                    <option value="4" >Đánh giá giảm dần</option>
                                    <option value="5" >Số lượng bán ra tăng dần</option>
                                    <option value="6" >Số lượng bán ra giảm dần</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </aside>
                <aside class="aside">
                    <h3 class="aside-title">Tìm theo tên</h3>
                    <form action="/Product/Search" method="post">
                        <div class="dp-flex">
                            <input name="__RequestVerificationToken" type="hidden" value="Gt2WKWQBo6gf_d634stJjbkr6_uPO6Gkwk_qPjpawZOFDAFGRF8u_M2E1kiNg34zT7ZFNqUKgQE24G-BeQDXzCTcTC33ogphbsuEHREgRTc1" />
                            <input type="text" class="input" id="search-product" name="ProductName" placeholder="Tên sản phẩm...">
                            <button type="submit" class="btn btn-custom" id="btn-product" style="border-radius: 0;"><i class="fa fa-search"></i></button>
                        </div>
                    </form>
                </aside>

                <aside class="aside">
                    <c:if test="${not empty brand}">
                        <h3 class="aside-title">Lọc theo hãng:</h3>
                        <ul class="size-option">
                            <c:forEach items="${brand}" var="brand">
                                <li><a href="${pageContext.request.contextPath}/san-pham.htm?categoryId=${categoryId}&brandId=${brand.brandId}">${brand.brandName}</a></li>
                                </c:forEach>
                        </ul>
                    </c:if>
                </aside>

                <aside class="aside">
                    <h3 class="aside-title">Lọc theo danh mục:</h3>
                    <ul class="size-option">
                        <li><a href="/Product/Index/?brandId=0&categoryId=1&view=grid&sort=1&pageSize=12&keyword=">Laptop &amp; phụ kiện</a></li>
                        <li><a href="/Product/Index/?brandId=0&categoryId=2&view=grid&sort=1&pageSize=12&keyword=">M&#225;y đồng bộ</a></li>
                        <li><a href="/Product/Index/?brandId=0&categoryId=3&view=grid&sort=1&pageSize=12&keyword=">M&#225;y chơi game</a></li>
                        <li><a href="/Product/Index/?brandId=0&categoryId=4&view=grid&sort=1&pageSize=12&keyword=">Linh kiện m&#225;y t&#237;nh</a></li>
                        <li><a href="/Product/Index/?brandId=0&categoryId=5&view=grid&sort=1&pageSize=12&keyword=">M&#225;y chủ &amp; M&#225;y trạm</a></li>
                        <li><a href="/Product/Index/?brandId=0&categoryId=6&view=grid&sort=1&pageSize=12&keyword=">Gaming Gear &amp; Console</a></li>
                        <li><a href="/Product/Index/?brandId=0&categoryId=7&view=grid&sort=1&pageSize=12&keyword=">Giải ph&#225;p tản nhiệt</a></li>
                    </ul>
                </aside>

                <aside class="aside">
                    <a href="/Product/Index/" class="primary-btn">Xoá hết bộ lọc</a>
                </aside>
                <aside class="aside">
                    <h3 class="aside-title">Sản phẩm nổi bật</h3>
                    <div class="product product-widget">
                        <div class="product-thumb">
                            <img src="Uploads/images/ProductImages/Intel/CPU/i5%208400/untitled-1_6e92f263a5c1446f98f837bf820cdb12.jpg" alt="Intel Core i5 8400 / 9M / 2.8GHz / 6 nh&#226;n 6 luồng" />
                        </div>
                        <div class="product-body">
                            <h2 class="product-name">
                                <a href="/Product/Detail?ProductId=2">
                                    Intel Core i5 8400 / 9M / 2.8GHz / 6 nh&#226;n 6 luồng                                        </a>
                            </h2>
                            <h3 class="product-price">
                                5,250,000                                     </h3>

                            <div class="product-rating">
                                <i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i>
                                <a href="" class="main-btn2 add-to-cart" data-id="2">Mua ngay</a>
                            </div>
                        </div>
                    </div>
                </aside>
            </article>
            <article id="main" class="col-md-9">

                <div id="store">
                    <div class="row">
                        <c:forEach items="${productAll}" var="p">
                            <a href="san-pham/chi-tiet.htm?productId=${p.product.productId}" title="${p.product.productName}">
                                <div class="col-md-4 col-sm-6 col-xs-6">
                                    <div class="product product-single ">
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
                                                    <c:if test="${fn:length(p.product.productName) <= 50}">
                                                        ${p.product.productName}
                                                    </c:if>
                                                </a>
                                            </h2>
                                            <h3 class="product-price">${p.priceString}</h3>
                                            <div class="product-rating">
                                                ${p.productStarString}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <div class="store-filter clearfix">
                    <div class="text-center">
                        <ul class='store-pages'>
                            <li><span class='text-uppercase'>Trang:</span></li>
                            <li><a class='disabled'><i class='fa fa-caret-left'></i></a></li>
                            <li class='active'>1</li>
                            <li><a href='/Product/Index/?brandid=0&categoryId=0&view=grid&sort=1&pageSize=12&page=2&keyword='>2</a></li>
                            <li><a href='/Product/Index/?brandid=0&categoryId=0&view=grid&sort=1&pageSize=12&page=3&keyword='>3</a></li>
                            <li><a href='/Product/Index/?brandid=0&categoryId=0&view=grid&sort=1&pageSize=12&page=4&keyword='>4</a></li>
                            <li><a href='/Product/Index/?brandid=0&categoryId=0&view=grid&sort=1&pageSize=12&page=5&keyword='>5</a></li>
                            <li><a href='/Product/Index/?brandid=0&categoryId=0&view=grid&sort=1&pageSize=12&page=2&keyword='><i class='fa fa-caret-right'></i></a></li>
                        </ul>                    
                    </div>
                </div>
            </article>
        </div>
    </div>
</section>


<script>
    $(document).ready(function () {
        $("#sort-type-1").change(function (event) {
            event.preventDefault();
            console.log($("#sort-type-1").val());
            window.location.href = '/Product/Index/?brandId=0&categoryId=0&view=grid&sort=' + $("#sort-type-1").val() + '&pageSize=12&page=1&keyword=';
        });

        $("#sort-type-2").change(function (event) {
            event.preventDefault();
            console.log($("#sort-type-2").val());
            window.location.href = '/Product/Index/?brandId=0&categoryId=0&view=grid&sort=' + $("#sort-type-2").val() + '&pageSize=12&page=1&keyword=';
        });

        $("#page-size-1").change(function (event) {
            event.preventDefault();
            console.log($("#page-size-1").val());
            window.location.href = '/Product/Index/?brandId=0&categoryId=0&view=grid&sort=1&pageSize=' + $("#page-size-1").val() + '&page=1&keyword=';
        });

        $("#page-size-2").change(function (event) {
            event.preventDefault();
            console.log($("#page-size-2").val());
            window.location.href = '/Product/Index/?brandId=0&categoryId=0&view=grid&sort=1&pageSize=' + $("#page-size-2").val() + '&page=1&keyword=';
        });

        var keyword = $("#search-product").val();
        $("#search-product").keydown(function (event) {
            if (event.keyCode == 13) {
                window.location.href = '${pageContext.request.contextPath}/tat-ca-san-pham.htm&keyword' + keyword;
            }
        });

        $("#btn-product").click(function (event) {
            event.preventDefault();
            var keyword = $("#search-product").val();
            window.location.href = '${pageContext.request.contextPath}/tat-ca-san-pham.htm&keyword' + keyword;
        });
    });
</script>
<jsp:include page="widget/footer.jsp" flush="true"/>
