<%-- 
    Document   : forgot
    Created on : Jul 29, 2020, 11:16:27 AM
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
            <li class="active">Quên mật khẩu</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Quên mật khẩu</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <form action="quen-mat-khau.htm" method="POST" role="form">
                <input name="__RequestVerificationToken" type="hidden" value="ZpXTrwF4NjyjkP_cyD99MK-uLF_HCOoQihziDx1UEF4bRztTfUgSJ-kjeGma5ztx8oHr9_aYnNX7PEjjRLHqPP8NJ5_-YbfcRtnnGGQQB9s1" />
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="input" id="email" name="email" />
                        </div>
                        <button type="submit" class="primary-btn">Xác nhận</button>
                        <button type="reset" class="primary-btn">Làm lại</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<jsp:include page="widget/footer.jsp" flush="true"/>
<c:if test="${not empty error}">
    <script>
        (function ($) {
            $.notify({
                title: '<strong>error !!</strong>',
                message: '${error}',
                delay: 4000
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