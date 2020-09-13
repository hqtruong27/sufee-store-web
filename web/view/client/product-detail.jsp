<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/trang-chu.htm">Trang chủ</a></li>
            <li><a href="${pageContext.request.contextPath}/san-pham.htm?categoryId=${productDetail.product.categories.categoryId}">
                    <c:forEach items="${listCategory}" var="c">
                        <c:if test="${c.categoryId == productDetail.product.categories.categoryId}">${c.categoryName}</c:if>
                    </c:forEach>
                </a>
            </li>
            <li class="active">${productDetail.product.productName}</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <div class="product product-details clearfix">
                <div style="margin-top: 20px">
                    <div class="col-md-4">
                        <div id="product-main-view" style="max-height:350px">
                            <div class="product-view">
                                <img style="max-height:350px" src="${productDetail.product.featureImage}" alt="Hình ảnh sản phẩm" />
                            </div>
                            <c:forEach items="${productImages}" var="img">
                                <div class="product-view">
                                    <img style="max-height:350px" src="${img}" alt="Hình ảnh sản phẩm" />
                                </div>
                            </c:forEach>
                        </div>
                        <div id="product-view">
                            <div class="product-view">
                                <img style="height:60px;" src="${productDetail.product.featureImage}" alt="Hình ảnh sản phẩm" />
                            </div>
                            <c:forEach items="${productImages}" var="img">
                                <div class="product-view">
                                    <img style="height:60px;" src="${img}" alt="Hình ảnh sản phẩm" />
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="product-body">

                            <h2 class="product-name">${productDetail.product.productName}</h2>
                            <p>
                                <span><b>Mã:</b></span>
                                <span> ${productDetail.product.productCode}</span>
                                <span> | </span>
                                <span><strong>By</strong></span>
                                <span style="color: blue">
                                    <c:forEach items="${listBrand}" var="br">
                                        <c:if test="${br.brandId == productDetail.product.brands.brandId}">${br.brandName}</c:if>
                                    </c:forEach>
                                </span>
                            </p>
                            <h3 class="product-price"><fmt:formatNumber value="${productDetail.product.price}"/>₫</h3>
                            <p><strong>Trạng thái:</strong> ${productDetail.productStatusString}</p>
                            <p><strong>Danh mục:</strong> 
                                <c:forEach items="${listCategory}" var="c">
                                    <c:if test="${c.categoryId == productDetail.product.categories.categoryId}">${c.categoryName}</c:if>
                                </c:forEach>
                            </p>
                            <div>
                                <div class="product-rating">
                                    ${productDetail.productStarString}
                                </div>
                                <a><span>${countProductCmt}</span> Đánh giá</a>
                                <div class="pull-right">
                                    <a href="#" title="Thêm vào danh sách yêu thích" data-id="${productDetail.product.productId}" class=" icon-btn" ><i class="fa fa-heart-o <c:if test="${productDetail.isWishlist}">active</c:if>"></i></a>
                                    </div>
                                </div>
                                <hr>
                                <div><span style="font-weight: 600">Sản phẩm được khuyến mãi:</span> <span> ${productDetail.product.productSale}%</span></div>
                            <div class="product-detail">
                                <div style="color: rgb(20, 53, 195);"></div>
                                <div style="margin-top: 10px">${productDetail.priceDetail}</div>
                            </div>
                            <div class="product-btns">
                                <c:if test="${productDetail.product.productStatus == 1}">
                                    <div class="qty-input">
                                        <input class="input" type="hidden" id="productQuantity" value="1" min="1" />
                                    </div>
                                    <button class="primary-btn add-to-cart-from-detail" data-id="${productDetail.product.productId}"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng</button>
                                </c:if>

                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="product-body">
                            <div class="product-name" style="font-weight: 700;font-size: 16px">Chính sách bán hàng</div>
                            <br>
                            <p style="font-size: 16px;"><i class="fa fa-check-circle-o"  style="color: #1435c3"></i><span> Cam kết hàng chính hãng 100% </span></p>
                            <p style="font-size: 16px;"><i class="fa fa-truck"  style="color: #1435c3"></i><span> Miên phí giao hàng từ 99k </span></p>
                            <p style="font-size: 16px;"><i class="fa fa-undo"  style="color: #1435c3"></i><span> Đổi trả miễn phí sau 10 ngày </span></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="product-tab">
                        <ul class="tab-nav">
                            <li class="active"><a data-toggle="tab" href="#tab1">Mô tả sản phẩm</a></li>
                            <li><a data-toggle="tab" href="#tab2">Thông số kỹ thuật</a></li>
                            <li><a data-toggle="tab" href="#tab3">Đánh giá</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab1" class="tab-pane fade in active" style="overflow: hidden;">
                                ${productDetail.product.specificationValue}
                            </div>
                            <div id="tab2" class="tab-pane fade in" style="overflow: hidden;">
                                ${productDetail.product.specificationName}
                            </div>
                            <div id="tab3" class="tab-pane fade in">
                                <div class="row">
                                    <div class="product-reviews" id="product-comment-append">
                                        <c:forEach items="${productComments}" var="comment">
                                            <div>
                                                <div class="avt">
                                                    <c:forEach items="${customerses}" var="customer">
                                                        <c:if test="${customer.customerId == comment.customers.customerId}">
                                                            <img class="img-avt" src="<%=request.getContextPath()%>/view/client/uploads/images/Customers/${customer.avatar}" alt="Ảnh đại diện cá nhân" />
                                                            <div class="">
                                                                <div>${customer.fullname}</div>
                                                                <div class="review-form">
                                                                    <div class="form-group">
                                                                        <div class="input-rating">
                                                                            <div class="stars">
                                                                                <c:choose>
                                                                                    <c:when test="${comment.commentRate == 1}">
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" checked=""  disabled=""/><label for="star1"></label>
                                                                                    </c:when>
                                                                                    <c:when test="${comment.commentRate == 2}">
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" checked="" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                    </c:when>
                                                                                    <c:when test="${comment.commentRate == 3}">
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled="" checked="" /><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                    </c:when>
                                                                                    <c:when test="${comment.commentRate == 4}">
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" checked=""  disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                    </c:when>
                                                                                    <c:when test="${comment.commentRate == 5}">
                                                                                        <input type="radio" checked="" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                        <input type="radio" disabled=""/><label for="start"></label>
                                                                                    </c:when>
                                                                                </c:choose>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                                <div style="margin-left: 62px;">${comment.commentContent}</div>
                                                <div style="margin-left: 62px;font-size: 12px"><fmt:formatDate pattern="dd/MM/yyy h:mm a" value="${comment.commentTime}"/></div>
                                                <hr>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <h4 class="text-uppercase">Để lại đánh giá của bạn</h4>
                                    <form class="review-form" action="${pageContext.request.contextPath}/binh-luan.htm" method="POST">
                                        <input name="__RequestVerificationToken" type="hidden" value="k1ZFqQvu_-DaJ6QHPThpXk61--lRqIO6tgULg9DXiT4-6dYxHWly74fSHVqGDOhyaKi13nYNZxcsXK6Lb45j3eeVLe2pFBlp502q1gC6v141" />
                                        <div class="form-group">
                                            <textarea class="input" name="commentContent" placeholder="Nhập đánh giá của bạn..."></textarea>
                                            <input type="hidden" name="productId" value=" ${productDetail.product.productId}" />
                                        </div>
                                        <div class="form-group">
                                            <div class="input-rating">
                                                <strong class="text-uppercase">Đánh giá: </strong>
                                                <div class="stars">
                                                    <input type="radio" id="star5" name="commentRate" value="5" /><label for="star5"></label>
                                                    <input type="radio" id="star4" name="commentRate" value="4" /><label for="star4"></label>
                                                    <input type="radio" id="star3" name="commentRate" value="3" /><label for="star3"></label>
                                                    <input type="radio" id="star2" name="commentRate" value="2" /><label for="star2"></label>
                                                    <input type="radio" id="star1" name="commentRate" value="1" /><label for="star1"></label>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="primary-btn" type="submit">Đánh giá</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</section>

<section class="section">
    <c:if test="${not empty fourRelatedProduct}">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="section-title">
                        <h2 class="title">Sản phẩm liên quan</h2>
                    </div>
                </div>
                <c:forEach items="${fourRelatedProduct}" var="product">
                    <div class="col-md-3 col-sm-6 col-xs-6">
                        <div class="product product-single">
                            <div class="product-thumb">
                                <div class="product-label">
                                    <span class='text-center'>Mới</span>
                                </div>
                                <a href="product/detail.htm/${product.product.productId}" class="main-btn quick-view"><i class="fa fa-eye"></i> Chi tiết</a>
                                <img src="${product.product.featureImage}" alt="${product.product.productName}" />
                            </div>
                            <div class="product-body">
                                <h2 class="product-name"><a href="product/detail.htm/${product.product.productId}">${product.product.productName}</a></h2>
                                <h3 class="product-price">${product.priceString}</h3>
                                <div class="product-rating">
                                    ${product.productStarString}
                                </div>
                                <div class="product-btns">
                                    <a href="" class="main-btn icon-btn" data-id="1"><i class="fa fa-heart "></i></a>
                                    <a href="" class="main-btn icon-btn" data-id="1"><i class="fa fa-exchange"></i></a>
                                    <a href="" class="primary-btn add-to-cart pull-right" data-id="1"><i class="fa fa-shopping-cart"></i> Mua ngay</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
</section>
<jsp:include page="widget/footer.jsp" flush="true"/>
<c:if test="${not empty error}">
    <script>
        Swal.fire({
            type: 'error',
            title: 'Thất bại',
            text: '${error}'
        });
    </script>
</c:if>
<c:if test="${not empty success}">
    <script>
        Swal.fire({
            type: 'success',
            title: 'Thành công!',
            text: '${success}'
        });
    </script>
</c:if>