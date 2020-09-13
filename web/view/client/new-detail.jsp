<%-- 
    Document   : new-detail
    Created on : Jul 29, 2020, 11:11:00 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/trang-chu.htm">Trang chủ</a></li>
            <li><a href="#" onclick="history.go(-1)">Tin tức</a></li>
            <li class="active">${news.newTitle}</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <div class="row-newsDetail">
                <div class="col-md-12 col-xs-12 col-centered">
                    <div class="new-detail-content" style="overflow: hidden;">
                        <h2 style="line-height: 1.5;margin-bottom: 20px"><strong>${news.newTitle}</strong></h2>
                                <c:forEach items="${listAdmin}" var="admin">
                                    <c:if test="${admin.adminId == news.admins.adminId}">
                                <div class="new-detail-info ">
                                    <ul class="pull-left">
                                        <li>
                                            <a href="#">
                                                <i class="fa fa-user"></i> ${admin.fullname}
                                            </a>
                                        </li>
                                        <li>
                                            <i class="fa fa-clock-o"></i> <fmt:formatDate pattern="dd/MM/yyyy" value="${news.createDate}"/>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li>
                                            <i class="fa fa-eye"></i> <fmt:formatNumber type = "number" maxFractionDigits  = "3" value="${news.countView}" /> lượt xem
                                        </li>
                                    </ul>
                                </div>
                            </c:if>
                        </c:forEach>
                        <hr>
                        <p><strong>${news.newDescription}</strong></p>
                        <ul>
                            <div class="title-news">
                                <h4 class="block-title">
                                    <span style="background-color: black;color: white;display: inline-block;line-height: 18px;padding: 7px 12px 4px;text-transform: uppercase">Tin tức mới nhất</span>
                                </h4>
                            </div>
                            <c:forEach items="${listNews}" var="list">
                                <a href="${pageContext.request.contextPath}/news-detail.htm?newsId=${list.newsId}">
                                    <li style="color: blue;font-size: 13px"><span>-</span> ${list.newTitle}</li>
                                </a>
                            </c:forEach>
                        </ul>
                        <hr>
                        <a href="${pageContext.request.contextPath}/news-detail.htm?newsId=${news.newsId}">
                            <img src="${news.newImage}" alt="Duy nhất chỉ c&#243; tại QTC" class="img-responsive" />
                        </a>
                        <div>${news.newContent}</div>
                    </div>
                    <div class="clearfix"><br></div>
                    <hr>
                </div>
            </div>
        </div>
    </div> 
</section>
<jsp:include page="widget/footer.jsp" flush="true"/>
