<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section id="navigation">
    <div class="container">
        <div id="responsive-nav">
            <div class="menu-nav">
                <span class="menu-header">Menu <i class="fa fa-bars"></i></span>
                <ul class="menu-list">
                    <li><a href="${pageContext.request.contextPath}/trang-chu.htm">Trang chủ</a></li>
                    <li class="dropdown mega-dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">Tin tức <i class="fa fa-caret-down"></i></a>
                        <div class="custom-menu">
                            <div class="row">
                                <c:if test="${not empty newsHtml}">
                                    ${newsHtml}
                                </c:if>
                            </div>
                        </div>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/view/client/about-us.jsp">Giới thiệu</a></li>
                    <li><a href="<%=request.getContextPath()%>/feedback.htm">Liên hệ</a></li>
                </ul>
            </div>
        </div>
    </div>
</section>