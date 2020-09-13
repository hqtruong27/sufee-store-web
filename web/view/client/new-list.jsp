<%-- 
    Document   : new-list
    Created on : Jul 29, 2020, 11:09:07 AM
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
            <li class="active">Tin tức</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="row">
        <div class="container" >
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Tin tức</h2>
                </div>
            </div>
        </div>
        <div class="about">
            <div class="container" >
                <div class="about-main">
                    <div class="col-md-12 about-left">
                        <div class="about-two">
                            <c:forEach items="${listNewsHot}" var="newsHot">
                                <div class="img-detail">
                                    <a href=""><img src="${newsHot.newImage}" alt="${newsHot.newTitle}" /></a>
                                </div>
                                <div class="text-block">
                                    <div>
                                        <h3 ><a style="color: white;font-weight: 500" href="${pageContext.request.contextPath}/news-detail.htm?newsId=${newsHot.newsId}">${newsHot.newTitle}</a></h3>
                                    </div>
                                    <div style="font-size: 10px">
                                        <c:forEach items="${listAdmin}" var="admin">
                                            <c:if test="${admin.adminId == newsHot.admins.adminId}">
                                                <span style="color: red">${admin.fullname}</span>
                                                <span>-</span>
                                                <span style="margin-right:3px"><fmt:formatDate pattern="dd/MM/yyyy" value="${newsHot.createDate}"/></span>
                                                <span><i class="fa fa-eye"></i> <fmt:formatNumber type = "number" maxFractionDigits  = "3" value="${newsHot.countView}" /> lượt xem</span>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="clearfix"></div>
                        </div>
                        <div class="clearfix"><hr></div>
                        <div class="about-three">
                            <div class="about-main">
                                <div class="col-md-8 about-left">
                                    <div class="clearfix"></div>
                                    <div class="about-three">
                                        <c:forEach items="${newses}" var="news" >
                                            <div class="abt-left">
                                                <div class="img-items-detail" >
                                                    <a href="${pageContext.request.contextPath}/news-detail.htm?newsId=${news.newsId}">
                                                        <img src="${news.newImage}" alt="${news.newTitle}" style="width:218px;height: 150px"  />
                                                    </a>
                                                </div>

                                                <div class="items-detail">
                                                    <h3 style="font-weight: 500"><a href="${pageContext.request.contextPath}/news-detail.htm?newsId=${news.newsId}">${news.newTitle}</a></h3>
                                                    <p style="font-size: 10px">
                                                        <c:forEach items="${listAdmin}" var="admin">
                                                            <c:if test="${admin.adminId == news.admins.adminId}">
                                                                <span style="color: red" class="lb-right">${admin.fullname}</span>
                                                                <span>-</span>
                                                                <span><fmt:formatDate pattern="dd/MM/yyyy" value="${news.createDate}"/></span>
                                                                <span><i class="fa fa-eye"></i> <fmt:formatNumber type = "number" maxFractionDigits  = "3" value="${news.countView}" /> lượt xem</span>
                                                            </c:if>
                                                        </c:forEach>
                                                    </p>
                                                    <p>${news.newDescription}</p>
                                                </div>
                                                <hr>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="about-four">
                                        <div class="pull-right">
                                            <div class="page-filter">
                                                <span class="text-uppercase">Hiển thị trên mỗi trang:</span>
                                                <select class="input" id="new-page-size">
                                                    <option value="10" <c:if test="${pageSize == 5}">selected</c:if>>5</option>
                                                    <option value="20" <c:if test="${pageSize == 20}">selected</c:if>>20</option>
                                                    <option value="50" <c:if test="${pageSize == 50}">selected</c:if>>50</option>
                                                </select>
                                            </div>
                                            <c:if test="${not empty paging}">
                                                ${paging}
                                            </c:if>                  
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 about-left">
                                    <div class="title-news">
                                        <h4 class="block-title">
                                            <span style="background-color: black;color: white;display: inline-block;line-height: 18px;padding: 7px 12px 4px;text-transform: uppercase">Bài viết phổ biến</span>
                                        </h4>
                                    </div>

                                    <c:forEach items="${sortByCountView}" var="newsCountView" >
                                        <div class="abt-left">
                                            <div class="img-items-detail-mini" >
                                                <a href="${pageContext.request.contextPath}/news-detail.htm?newsId=${newsCountView.newsId}">
                                                    <img src="${newsCountView.newImage}" alt="Duy nhất chỉ c&#243; tại QTC" style="width:100px;height: 70px"  />
                                                </a>
                                            </div>

                                            <div class="items-detail-mini">
                                                <h3 style="font-size: 14px;font-weight: 500"><a href="${pageContext.request.contextPath}/news-detail.htm?newsId=${newsCountView.newsId}">${newsCountView.newTitle}</a></h3>
                                                <p style="font-size: 10px">
                                                    <c:forEach items="${listAdmin}" var="admin">
                                                        <c:if test="${admin.adminId == newsCountView.admins.adminId}">
                                                            <span style="color: red" class="lb-right">${admin.fullname}</span>
                                                            <span>-</span>
                                                            <span><fmt:formatDate pattern="dd/MM/yyyy" value="${newsCountView.createDate}"/></span>
                                                            <br>
                                                            <span><i class="fa fa-eye"></i> <fmt:formatNumber type = "number" maxFractionDigits  = "3" value="${newsCountView.countView}" /> lượt xem</span>
                                                        </c:if>
                                                    </c:forEach>
                                                </p>
                                            </div>
                                            <hr>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="clearfix"><hr /></div>
                    </div>

                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
        $("#new-page-size").change(function (event) {
            event.preventDefault();
            var pageSize = $(this).val();
    <c:if test="${empty catalogId}">
            window.location.href = '${pageContext.request.contextPath}/news-list-catalog.htm?pageSize=' + pageSize;
    </c:if>
    <c:if test="${not empty catalogId}">
            window.location.href = '${pageContext.request.contextPath}/news-list-catalog.htm?catalogId=' + ${catalogId} + '&pageSize=' + pageSize;
    </c:if>

        });
    });
</script>
<jsp:include page="widget/footer.jsp" flush="true"/>
