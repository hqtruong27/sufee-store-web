<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="/">Trang chủ</a></li>
            <li class="active">Đăng ký</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Đăng ký tài khoản</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form action="dang-ky.htm" method="POST" role="form">
                    <input name="__RequestVerificationToken" type="hidden" value="PagKHthtRpxoA4kmyHqYz5FQevIODs8eqYVWdJWL5tzM8lVnL7vkWQ7TzQOVde1cji2L_Qi50O9RhLZ9rLcbG5G0KuQnfmHfSfi5AO8NT481" />
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="fullname">Họ và tên:</label>
                                <input type="text" class="input" id="fullname" name="fullname" />
                                <c:if test="${not empty errorname}"> <small class="form-text text-danger">${errorname}</small></c:if>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="birthday">Ngày sinh:</label>
                                <!--<input type="text" name="birthday" />-->
                                <input type="date" class="input" id="birthday" name="birthday"/>
                                <c:if test="${not empty errorbirthday}"> <small class="form-text text-danger">${errorbirthday}</small></c:if>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="passwords">Mật khẩu:</label>
                                <input type="password" class="input" id="passwords" name="passwords" />
                                <c:if test="${not empty errorpass}"> <small class="form-text text-danger">${errorpass}</small></c:if>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="rePassword">Nhập lại mật khẩu:</label>
                                <input type="password" class="input" id="rePassword" name="rePassword" />
                                <c:if test="${not empty errorrepassword}"> <small class="form-text text-danger">${errorrepassword}</small></c:if>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="gender">Giới tính:</label>
                                <select name="gender" id="gender" class="input" required="required">
                                    <option value="">Vui lòng chọn</option>
                                    <option value="1">Nam</option>
                                    <option value="0">Nữ</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="input" id="email" name="email" />
                                <c:if test="${not empty erroremail}"> <small class="form-text text-danger">${erroremail}</small></c:if>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="phone">Điện thoại:</label>
                                <input type="text" class="input" id="phone" name="phone" />
                                <c:if test="${not empty errorphone}"> <small class="form-text text-danger">${errorphone}</small></c:if>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="customerAddress">Địa chỉ:</label>
                                <input type="text" class="input" id="customerAddress" name="customerAddress" />
                                <c:if test="${not empty erroraddress}"> <small class="form-text text-danger">${erroraddress}</small></c:if>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="primary-btn">Đăng ký</button>
                    <button type="reset" class="primary-btn">Làm lại</button>
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
                title: '<strong>Error !!</strong>',
                message: '${error}'
            }, {
                type: 'danger'
            });
        })(jQuery);
    </script>
</c:if>