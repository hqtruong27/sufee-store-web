<%-- 
    Document   : about-us
    Created on : Jul 29, 2020, 11:23:28 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<div id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/view/client/home-index.jsp">Trang chủ</a></li>
            <li class="active">Giới thiệu</li>
        </ul>
    </div>
</div>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <p><c:if test="${not empty intro}">${intro.introductionContent}</c:if></p>

                <p><iframe frameborder="0" height="768" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3723.657600813899!2d105.78126221469924!3d21.046381985988873!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab32dd484c53%3A0x4201b89c8bdfd968!2zMjM4IEhvw6BuZyBRdeG7kWMgVmnhu4d0LCBD4buVIE5odeG6vywgQ-G6p3UgR2nhuqV5LCBIw6AgTuG7mWksIFZp4buHdCBOYW0!5e0!3m2!1svi!2s!4v1555660463769!5m2!1svi!2s" style="border:0" width="1024"></iframe></p>

            </div>
        </div>
    </div>
</div>
<jsp:include page="widget/footer.jsp" flush="true"/>
