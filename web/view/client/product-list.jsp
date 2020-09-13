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
                        <div class="" >
                            <div class="" >
                                <select class="input" id="sort-type-1" >
                                    <option value="1" <c:if test="${sort == 1}">selected</c:if>>Giá tăng dần</option>
                                    <option value="2" <c:if test="${sort == 2}">selected</c:if>>Giá giảm dần</option>
                                    <option value="7" <c:if test="${sort == 7}">selected</c:if>>Mới nhất</option>
                                    </select>
                                </div>
                            </div>
                        </div>
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
                        <c:forEach items="${listCate}" var="listCate">
                            <li><a href="${pageContext.request.contextPath}/san-pham.htm?categoryId=${listCate.categoryId}">${listCate.categoryName}</a></li>
                            </c:forEach>
                    </ul>
                </aside>

                <aside class="aside">
                    <a href="xoa-loc.htm" class="primary-btn">Xoá hết bộ lọc</a>
                </aside>

            </article>
            <article id="main" class="col-md-9">
                <div id="store">
                    <div class="row">
                        <c:forEach items="${productBycategory}" var="p">
                            <a href="san-pham/chi-tiet.htm?productId=${p.product.productId}" title="${p.product.productName}">
                                <div class="col-md-4 col-sm-6 col-xs-6">
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
                                                <c:if test="${fn:length(p.product.productName) > 50}">
                                                    ${p.product.productName}...
                                                </c:if>
                                                <c:if test="${fn:length(p.product.productName) <= 50}">
                                                    ${p.product.productName}
                                                </c:if>
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


            </article>

        </div>
        <div class="store-filter clearfix" style="margin-top: 20px">
            <div class="pull-left">
                <div class="page-filter">
                    <span class="text-uppercase">Hiển thị mỗi trang:</span>
                    <select class="input" id="page-size-2">
                        <option value="9" <c:if test="${pageSize == 9}">selected</c:if>>9</option>
                        <option value="24" <c:if test="${pageSize == 24}">selected</c:if>>24</option>
                        <option value="60" <c:if test="${pageSize == 60}">selected</c:if>>60</option>
                        </select>
                    </div>
                </div>
                <div class="text-center">

                <c:if test="${not empty paging}">
                    ${paging}
                </c:if>
            </div>
        </div>
    </div>
</section>


<script>
    $(document).ready(function () {
        $("#sort-type-1").change(function (event) {
            event.preventDefault();
            console.log($("#sort-type-1").val());
            window.location.href = '${pageContext.request.contextPath}/san-pham.htm?brandId=${brandId}&categoryId=${categoryId}&view=grid&sort=' + $("#sort-type-1").val() + '&pageSize=9&page=1&keyword=';
        });

        $("#sort-type-2").change(function (event) {
            event.preventDefault();
            console.log($("#sort-type-2").val());
            window.location.href = '${pageContext.request.contextPath}/san-pham.htm?brandId=${brandId}&categoryId=${categoryId}&view=grid&sort=' + $("#sort-type-2").val() + '&pageSize=9&page=1&keyword=';
        });

        $("#page-size-1").change(function (event) {
            event.preventDefault();
            console.log($("#page-size-1").val());
            window.location.href = '${pageContext.request.contextPath}/san-pham.htm?categoryId=${categoryId}&brandId=${brandId}&view=grid&sort=1&pageSize=' + $("#page-size-1").val() + '&page=1&keyword=';
        });

        $("#page-size-2").change(function (event) {
            event.preventDefault();
            console.log($("#page-size-2").val());
            window.location.href = '${pageContext.request.contextPath}/san-pham.htm?categoryId=${categoryId}&brandId=${brandId}&view=grid&sort=1&pageSize=' + $("#page-size-2").val() + '&page=1&keyword=';
        });

        var keyword = $("#search-product").val();
        $("#search-product").keydown(function (event) {
            if (event.keyCode === 13) {
                window.location.href = '${pageContext.request.contextPath}/san-pham.htm?categoryId=${categoryId}&brandId=${brandId}&view=grid&sort=1&pageSize=9&page=1&keyword=' + keyword;
            }
        });

        $("#btn-product").click(function (event) {
            event.preventDefault();
            var keyword = $("#search-product").val();
            window.location.href = '${pageContext.request.contextPath}/san-pham.htm?categoryId=${categoryId}&brandId=${brandId}&view=grid&sort=1&pageSize=9&page=1&keyword=' + keyword;
        });
    });
</script>
<jsp:include page="widget/footer.jsp" flush="true"/>
