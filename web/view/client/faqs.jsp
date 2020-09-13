<%-- 
    Document   : faqs
    Created on : Sep 12, 2020, 1:41:14 AM
    Author     : ASUS
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
            <li class="active">FAQs</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Câu hỏi thường gặp</h2>
                    <div class="pull-right">
                        <div class="product-slick-dots-1 custom-dots"></div>
                    </div>
                </div>
            </div>
        </div>
        
            <div class="row">
                <c:forEach items="${listFaq}" var="faq">
                <div class="col-md-12">
                    <div class="form-group">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="content"><b>Question: </b></label>
                                        <p>${faq.fastQuestion}</p>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="content"><b>Answer: </b></label>
                                        <p>${faq.fastAnswer}</p>
                                    </div>
                                </div>
                            </div>        
                    </div>
                </div>
                </c:forEach>
            </div>
            

        
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
