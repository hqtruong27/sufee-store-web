<%-- 
    Document   : icon-insert
    Created on : Aug 18, 2020, 11:00:46 AM
    Author     : ADMIN
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckfinder" %>
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
                        <strong>Thêm mới icon</strong> 
                    </div>
                    <div class="card-body card-block">
                        <f:form action="create-icon.htm" commandName="icon" method="POST"  class="form-horizontal">    
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="iconImage" class=" form-control-label">Icon</label></div>
                                <div class="col-12 col-md-9">
                                    <a id="btn-upload" class="btn btn-secondary " style="display: inline-block;margin-top: -6px">Chọn ảnh</a>
                                    <f:input  type="text" id="iconImage" readonly="true" multiple="false" path="iconImage" class="form-control" cssStyle="border-left: none; border-radius: 0;margin-left: -4px;max-width: 766px;display: inline-block;" />
                                    <div class="show-for-iconImage"/></div>
                            </div>
                        </div>     

                        <div class="row form-group">
                            <div class="col col-md-3"><label for="iconStatus" class=" form-control-label">Trạng thái</label></div>
                            <div class="col-12 col-md-9">
                                <select name="iconStatus" id="iconStatus" class="form-control">
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
                                <a href="icons.htm" type="reset" class="btn btn-danger btn-sm">
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
<script src="${pageContext.request.contextPath}/view/admin/ckfinder/ckfinder.js" type="text/javascript"></script>
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
<script>
    (function ($) {
        $("#btn-upload").click(function (event) {
            event.preventDefault();
            var finder = new CKFinder();
            finder.selectActionFunction = function (url) {
                $("#iconImage").val(url);
                $(".show-for-iconImage").html("<img src='/Sufee_Store" + url + "' width='150' />");
            };
            finder.popup();
        });
    })(jQuery);
</script>
