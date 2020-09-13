<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Trang chủ</a></li>
            <li class="active">Đăng nhập</li>
        </ul>
    </div>
</section>
<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Đăng nhập</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form action="dang-nhap.htm" method="POST" role="form">
                    <div class="row">
                        <div class="col-md-6 col-md-offset-3">
                            <div class="form-group">
                                <label for="passwords">Email:</label>
                                <input type="email" class="input" id="email" name="email" />
                            </div>
                            <div class="form-group">
                                <label for="passwords">Mật khẩu:</label>
                                <input type="password" class="input" id="passwords" name="passwords" />
                            </div>
                            <button type="submit" id="submit" class="primary-btn">Đăng nhập</button>
                            <br />
                            <div style="margin-bottom: 20px"><a href="${pageContext.request.contextPath}/quen-mat-khau.htm"><u>Quên mật khẩu?</u></a></div>
                        </div>
                    </div>
                </form>
            </div>
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