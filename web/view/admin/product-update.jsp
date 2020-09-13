<%-- 
    Document   : product-update
    Created on : Jul 7, 2020, 10:44:58 AM
    Author     : Hoang Truong
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckfinder" %>
<jsp:include page="widget/header.jsp" flush="true"/>
<jsp:include page="widget/navbar.jsp" flush="true"/>
<div id="right-panel" class="right-panel">
    <header id="header" class="header">

        <div class="header-menu">

            <div class="col-sm-7">
                <a id="menuToggle" class="menutoggle pull-left"><i class="fa fa fa-tasks"></i></a>
                <div class="header-left">
                    <button class="search-trigger"><i class="fa fa-search"></i></button>
                    <div class="form-inline">
                        <form class="search-form">
                            <input class="form-control mr-sm-2" type="text" placeholder="Search ..." aria-label="Search">
                            <button class="search-close" type="submit"><i class="fa fa-close"></i></button>
                        </form>
                    </div>
                    <div class="dropdown for-notification">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="notification" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="ti-shopping-cart"></i>
                            <c:if test="${not empty countNotifyOrder}">
                                <span class="count bg-danger">${countNotifyOrder}</span>
                            </c:if>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="notification">
                            <p class="red">Đơn hàng chưa giao</p>
                            <c:forEach items="${listOrder}" var="notifyOrder">
                                <a class="dropdown-item media bg-flat-color-0" href="<%=request.getContextPath()%>/admin/orders-detail.htm?orderId=${notifyOrder.orderId}">
                                    <span class="message media-body" >
                                        <span class="name float-left">${notifyOrder.email}</span>
                                        <p class="time float-left"><fmt:formatNumber value="${notifyOrder.orderTotalAmount}"/>₫</p>
                                        <p class="time float-left"><fmt:formatDate pattern="dd/MM/yyyy" value="${notifyOrder.createDate}"/></p>
                                    </span>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="dropdown for-message">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="message" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="ti-email" title="Phản hồi khách hàng"></i>
                            <c:if test="${not empty countNotifyFeedback}">
                                <span class="count bg-primary">${countNotifyFeedback}</span>
                            </c:if>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="message">
                            <c:forEach items="${listFeedback}" var="notifyFeedback">
                                <a class="dropdown-item media bg-flat-color-0" href="<%=request.getContextPath()%>/admin/feedback-detail.htm?feedbackId=${notifyFeedback.feedbackId}">
                                    <span class="message media-body" >
                                        <span class="name float-left">${notifyFeedback.fullname}</span>
                                        <p class="time float-left">${notifyFeedback.content}</p>
                                        <p class="time float-left"><fmt:formatDate pattern="dd/MM/yyyy" value="${notifyFeedback.feedbacksTime}"/></p>
                                    </span>
                                </a>
                            </c:forEach>
                            <a href="<%=request.getContextPath()%>/admin/feedback.htm"><button style="width: 100%" type="button" class="btn btn-outline-secondary btn-sm">Xem thêm </button></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-5">
                <c:if test="${not empty InfoAdmin}">
                    <div class="user-area dropdown float-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img class="user-avatar rounded-circle" src="${pageContext.request.contextPath}/view/admin/uploads/admin.jpg" alt="User Avatar">
                        </a>
                        <div class="user-menu dropdown-menu">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/info-admin.htm"><i class="mr-2 fa fa-user"></i> Thông tin </a>
                            <a class="nav-link" href="logout.htm"><i class="mr-2 fa fa-power-off"></i> Logout</a>
                        </div>
                    </div>
                    <div class="float-right"  style="margin: 7px 14px 0px 0px;"id="">
                        <a href="#">${InfoAdmin.fullname}</a>
                    </div>
                </c:if>
            </div>
        </div>
    </header>
    <div class="breadcrumbs">
        <div class="col-sm-4">
            <div class="page-header float-left">
                <div class="page-title">
                    <h1>Dashboard</h1>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="page-header float-right">
                <div class="page-title">
                    <ol class="breadcrumb text-right">
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Table</a></li>
                        <li class="active">Data table</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>

    <div class="content mt-3">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>Thêm mới sản phẩm</strong> 
                        </div>
                        <div class="card-body card-block">
                            <f:form action="update-product.htm" commandName="product" method="POST"  class="form-horizontal"> 
                                <f:hidden id="productId" path="productId"  class="form-control" />
                                <div class="row form-group">
                                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Tên sản phẩm ${required}</label></div>
                                    <div class="col-12 col-md-9">
                                        <f:input id="productName" path="productName"  class="form-control" />
                                        <c:if test="${not empty errorproductname}"> <small class="form-text text-danger">${errorproductname}</small></c:if>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col col-md-3"><label for="text-input" class=" form-control-label">Mã hiệu ${required}</label></div>
                                    <div class="col-12 col-md-9">
                                        <f:input type="text" id="productCode" path="productCode"  class="form-control" />
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col col-md-3"><label for="select" class=" form-control-label">Danh mục ${required}</label></div>
                                    <div class="col-12 col-md-9">
                                        <select name="categories.categoryId" id="categoryId" class="form-control" required oninvalid="this.setCustomValidity('Vui lòng chọn danh mục')" oninput="this.setCustomValidity('')">
                                            <option value="">Vui lòng chọn</option>
                                            <c:forEach items="${newListCategoriesHasParent}" var="c">
                                                <optgroup label="${c.categoryName}">
                                                    <c:forEach items="${listParentCategories}" var="pr">
                                                        <c:if test="${pr.parentId == c.categoryId}">
                                                            <option <c:if test="${product.categories.categoryId == pr.categoryId}">selected</c:if> value="${pr.categoryId}">${pr.categoryName}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </optgroup>
                                            </c:forEach>
                                            <c:forEach items="${newListCategoriesNoParent}" var="cateNoParent">
                                                <option <c:if test="${product.categories.categoryId == cateNoParent.categoryId}">selected</c:if> value="${cateNoParent.categoryId}">${cateNoParent.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col col-md-3"><label for="select" class=" form-control-label">Hãng sản xuất ${required}</label></div>
                                    <div class="col-12 col-md-9">
                                        <select name="brands.brandId" id="brandId" class="form-control" required oninvalid="this.setCustomValidity('Vui lòng chọn thương hiệu')" oninput="this.setCustomValidity('')">
                                            <option valsue="0">Vui lòng chọn</option>
                                            <c:forEach items="${listBrands}" var="lb">
                                                <option  <c:if test="${product.brands.brandId == lb.brandId}">selected</c:if> value="${lb.brandId}">${lb.brandName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col col-md-3"><label for="price" class=" form-control-label">Giá ${required}</label></div>
                                    <div class="col-12 col-md-9">
                                        <f:input type="text" id="price" path="price"  class="form-control" />
                                        <c:if test="${not empty errorprice}"> <small class="form-text text-danger">${errorprice}</small></c:if>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col col-md-3"><label for="productSale" class=" form-control-label">Mức giảm giá ${required}</label></div>
                                    <div class="col-12 col-md-9">
                                        <f:input type="text" id="productSale" path="productSale"  class="form-control"/>
                                        <small class="form-text text-muted">% giảm giá <c:if test="${not empty errorpricecheck}"> ${errorpricecheck}</c:if></small>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col col-md-3"><label for="warranty" class=" form-control-label">Bảo hành ${required}</label></div>
                                    <div class="col-12 col-md-9">
                                        <f:input type="text" id="warranty" path="warranty"  class="form-control" />
                                        <c:if test="${not empty errorwarranty}"> <small class="form-text text-danger">${errorwarranty}</small></c:if>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col col-md-3"><label for="" class=" form-control-label">Hình ảnh đại diện ${required}</label></div>
                                    <div class="col-12 col-md-9">
                                        <small class="form-text text-muted">Hình ảnh đại diện cho sản phẩm</small>
                                        <button id="btn-upload" class="btn btn-secondary " style="display: inline-block;margin-top: -6px">Chọn ảnh</button>
                                        <f:input  type="text" id="featureImage" readonly="true" multiple="false" path="featureImage" class="form-control" cssStyle="border-left: none; border-radius: 0;margin-left: -4px;max-width: 766px;display: inline-block;" />
                                        <div class="show-for-featureImage"/><img src="${product.featureImage}" width="60"/></div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="" class=" form-control-label">Hình ảnh</label></div>
                                <div class="col-12 col-md-9">
                                    <small class="form-text text-muted">Nhiều ảnh (bỏ trống nếu không chọn)</small>
                                    <button id="btn-uploads" class="btn btn-secondary " style="display: inline-block;margin-top: -6px">Chọn ảnh</button>
                                    <f:input  type="text" id="images-url" readonly="true"  path="images" class="form-control" cssStyle="border-left: none; border-radius: 0;margin-left: -4px;max-width: 766px;display: inline-block;" />
                                    <div class="show-for-images"/>
                                    <c:if test="${not empty listProductImages}">
                                        <c:forEach items="${listProductImages}" var="img">
                                            <img src="${img}" width="60"/>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col col-md-3"><label for="productStatus" class=" form-control-label">Trạng thái ${required}</label></div>
                            <div class="col-12 col-md-9">
                                <select name="productStatus" id="productStatus" class="form-control" required oninvalid="this.setCustomValidity('Vui lòng chọn trạng thái sản phẩm')" oninput="this.setCustomValidity('')">
                                    <option <c:if test="${product.productStatus == 1}">selected</c:if> value="1">Còn hàng</option>
                                    <option <c:if test="${product.productStatus == 2}">selected</c:if> value="2">Hết hàng</option>
                                    <option <c:if test="${product.productStatus == 0}">selected</c:if> value="0">Tạm ẩn</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="productDescription" class=" form-control-label">Mô tả ngắn ${required}</label></div>
                                <div class="col-12 col-md-9">
                                <f:textarea path="productDescription" id="productDescription" rows="3" class="form-control" />
                                <c:if test="${not empty errordescriptiom}"> <small class="form-text text-danger">${errordescriptiom}</small></c:if>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col col-md-3"><label for="SpecificationName" class=" form-control-label">Thông số kỹ thuật ${required}</label></div>
                            <div class="col-12 col-md-9">
                                <f:textarea path="specificationName" id="SpecificationName" rows="9" class="form-control" />
                                <c:if test="${not empty errorSpecificationName}"> <small class="form-text text-danger">${errorSpecificationName}</small></c:if>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col col-md-3"><label for="specificationValue" class=" form-control-label">Mô tả chi tiết ${required}</label></div>
                            <div class="col-12 col-md-9">
                                <f:textarea path="specificationValue" id="SpecificationValue" rows="9" class="form-control" />
                                <c:if test="${not empty errorSpecificationValue}"> <small class="form-text text-danger">${errorSpecificationValue}</small></c:if>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col col-md-3">
                                <button type="submit" class="btn btn-success btn-sm">
                                    <i class="ti-check"></i> Xác nhận
                                </button>
                                <a href="${pageContext.request.contextPath}/admin/product.htm" type="reset" class="btn btn-danger btn-sm">
                                    <i class="ti-back-left"></i> Trở lại
                                </a>
                            </div>
                        </div>
                    </f:form>
                </div>
            </div>
        </div>
    </div><!-- .animated -->
</div><!-- .content -->
</div><!-- /#right-panel -->
s

<jsp:include page="widget/footer.jsp" flush="true"/>
<script src="${pageContext.request.contextPath}/view/admin/assets/bootstrap-notify-3.1.3/dist/bootstrap-notify.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/view/admin/ckeditor/ckeditor.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/view/admin/ckfinder/ckfinder.js" type="text/javascript"></script>
<script>CKFinder.setupCKEditor(CKEDITOR.replace('SpecificationName'), '${pageContext.request.contextPath}/view/admin/ckfinder/');</script>
<script>CKFinder.setupCKEditor(CKEDITOR.replace('SpecificationValue'), '${pageContext.request.contextPath}/view/admin/ckfinder/');</script>
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
<script>
    (function ($) {
        $("#btn-upload").click(function (event) {
            event.preventDefault();
            var finder = new CKFinder();
            finder.selectActionFunction = function (url) {
                $("#featureImage").val(url);
                $(".show-for-featureImage").html("<img src='" + url + "' width='150' />");
            };
            finder.popup();
        });

        $("#btn-uploads").click(function (event) {
            event.preventDefault();
            var finder = new CKFinder();
            finder.selectActionFunction = function (url, file, files) {
                var str = "";
                var imgs = "";
                $.each(files, function (key, val) {
                    str += val.url + ";";
                    imgs += "<img src='" + val.url + "' width='150' />";
                });
                str = str.slice(0, str.length - 1);
                $(".show-for-images").html(imgs);
                $("#images-url").val(str);
            };
            finder.popup();
        });
    })(jQuery);
</script>