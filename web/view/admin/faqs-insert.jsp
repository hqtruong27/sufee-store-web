<%-- 
    Document   : faqs-insert
    Created on : Aug 17, 2020, 1:17:53 AM
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
                        <strong>Thêm mới FAQs</strong> 
                    </div>
                    <div class="card-body card-block">
                        <f:form action="create-faqs.htm" commandName="faq" method="POST"  class="form-horizontal">    
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="fastQuestion" class=" form-control-label">Câu hỏi</label></div>
                                <div class="col-12 col-md-9">
                                    <f:input type="text" id="fastQuestion" path="fastQuestion"  class="form-control" />
                                </div>
                            </div>        
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="fastAnswer" class=" form-control-label">Trả lời</label></div>
                                <div class="col-12 col-md-9">
                                    <f:input type="text" id="fastAnswer" path="fastAnswer"  class="form-control" />
                                </div>
                            </div>    
                            <div class="row form-group">
                                <div class="col col-md-3"><label for="faqstatus" class=" form-control-label">Trạng thái</label></div>
                                <div class="col-12 col-md-9">
                                    <select name="faqstatus" id="faqstatus" class="form-control">
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
                                    <a href="faqs.htm" type="reset" class="btn btn-danger btn-sm">
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
