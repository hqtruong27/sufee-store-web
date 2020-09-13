<%-- 
    Document   : contact-insert
    Created on : Aug 13, 2020, 8:51:09 PM
    Author     : Kai
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/navbar.jsp" flush="true" />
<jsp:include page="widget/header.jsp" flush="true" />

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
                        <strong>Thêm mới Contact</strong> 
                    </div>
                    <div class="card-body card-block">
                        <f:form action="create-contact.htm" commandName="contact" method="POST"  class="form-horizontal">    
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="contactAddress" class=" form-control-label">Địa chỉ liên hệ</label></div>
                                <div class="col-12 col-md-9">
                                    <f:input type="text" id="contactAddress" path="contactAddress"  class="form-control" />
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="contactEmail" class=" form-control-label">Email</label></div>
                                <div class="col-12 col-md-9">
                                    <f:input type="text" id="contactEmail" path="contactEmail"  class="form-control" />
                                </div>
                            </div>    
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="contactHotline" class=" form-control-label">Số điện thoại</label></div>
                                <div class="col-12 col-md-9">
                                    <f:input type="text" id="contactHotline" path="contactHotline"  class="form-control" />
                                </div>
                            </div>    
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="contactStatus" class=" form-control-label">Trạng thái</label></div>
                                <div class="col-12 col-md-9">
                                    <select name="contactStatus" id="bannerStatus" class="form-control">
                                        <option value="1">Hiển thị</option>
                                        <option value="0">Tạm ẩn</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <button type="submit" class="btn btn-success btn-sm">
                                        <i class="ti-check"></i> Xác nhận
                                    </button>
                                    <a href="contact.htm" type="reset" class="btn btn-danger btn-sm">
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

