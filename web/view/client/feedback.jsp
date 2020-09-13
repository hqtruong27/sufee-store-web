<%-- 
    Document   : feedback
    Created on : Jul 29, 2020, 11:18:11 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/view/client/home-index.jsp">Trang chủ</a></li>
            <li class="active">Phản hồi</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Phản hồi cho chúng tôi</h2>
                    <div class="pull-right">
                        <div class="product-slick-dots-1 custom-dots"></div>
                    </div>
                </div>
            </div>
        </div>
        <f:form method="POST" action="feedback.htm" commandName="feedbacks" >
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="feedbackCatalogId">Bạn muốn phản hồi về vấn đề gì?</label>
                        <select name="feedbackCatalogs.feedbackCatalogId" id="feedbackCatalogId" class="form-control" required="required">
                            <option value="0">Chọn danh mục phản hồi</option>
                            <c:forEach items="${listFeebackCatalog}" var="listfbc">
                                <option value="${listfbc.feedbackCatalogId}">${listfbc.feedbackCatalogName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="fullname">Họ tên:</label>
                        <f:input type="text" class="form-control" id="fullname" path="fullname" required="required"/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <f:input type="email" class="form-control" id="email" path="email" required="required"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="phone">Điện thoại:</label>
                        <f:input type="text" class="form-control" id="phone" path="phone" required="required"/>
                    </div>
                    <div class="form-group">
                        <label for="feedbackAddress">Địa chỉ:</label>
                        <f:input type="text" class="form-control" id="feedbackAddress" path="feedbackAddress" required="required"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <label for="content">Nội dung:</label>
                        <f:textarea path="content" id="content" class="form-control" rows="8" required="required"></f:textarea>
                        </div>
                    </div>
                </div>
                <button type="submit" class="primary-btn">Gửi đi</button>
                <button type="reset" class="second-btn">Làm lại</button>
        </f:form>
    </div>
</div>
<jsp:include page="widget/footer.jsp" flush="true"/>
<script src="${pageContext.request.contextPath}/view/admin/assets/bootstrap-notify-3.1.3/dist/bootstrap-notify.min.js" type="text/javascript"></script>
<c:if test="${not empty error}">
    <script>
        (function ($) {
            $.notify({
                title: '<strong>Error: </strong>',
                message: '${error}'
            }, {
                type: 'danger',
                allow_dismiss: false
            });
        })(jQuery);
    </script>
</c:if>
<c:if test="${not empty success}">
    <script>
        (function ($) {
            $.notify({
                title: '<strong>success !!</strong>',
                message: '${success}',
                delay: 4000
            }, {
                type: 'success',
                allow_dismiss: false
            });
        })(jQuery);
    </script>
</c:if>