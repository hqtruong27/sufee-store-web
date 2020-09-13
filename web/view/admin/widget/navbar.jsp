<%-- 
    Document   : navbar
    Created on : Jun 30, 2020, 4:42:23 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <!-- Left Panel -->
    <aside id="left-panel" class="left-panel">
        <nav class="navbar navbar-expand-sm navbar-default">

            <div class="navbar-header">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand" href="<%=request.getContextPath()%>/admin/index.htm"><img src="<%=request.getContextPath()%>/view/admin/uploads/logo.png" alt="Logo"></a>
                <a class="navbar-brand hidden" href="./"><img src="<%=request.getContextPath()%>/view/admin/uploads/logo2.png" alt="Logo"></a>
            </div>

            <div id="main-menu" class="main-menu collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="<%=request.getContextPath()%>/admin/index.htm"> <i class="menu-icon fa fa-dashboard"></i>Bảng điều khiển </a>
                    </li>
                    <h3 class="menu-title">QUẢN TRỊ</h3><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-users"></i>Nhóm khách hàng</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon ti-user"></i><a href="<%=request.getContextPath()%>/admin/customers.htm">Khách hàng</a></li>
                            <li><i class="menu-icon ti-email"></i><a href="<%=request.getContextPath()%>/admin/feedback-catalog.htm">Danh mục phản hồi</a></li>
                            <li><i class="menu-icon ti-comments"></i><a href="<%=request.getContextPath()%>/admin/feedback.htm">Phản hồi khách hàng</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon ti-layout-grid2"></i>Nhóm sản phẩm</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon ti-stamp"></i><a href="<%=request.getContextPath()%>/admin/brand.htm">Hãng sản xuất</a></li>
                            <li><i class="menu-icon ti-credit-card"></i><a href="<%=request.getContextPath()%>/admin/categories.htm">Danh mục sản phẩm</a></li>
                            <li><i class="menu-icon ti-tablet"></i><a href="<%=request.getContextPath()%>/admin/product.htm">Sản phẩm</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-laptop"></i>Nhóm tin tức</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon ti-layout-tab-window"></i><a href="<%=request.getContextPath()%>/admin/news.htm">Tin tức</a></li>
                            <li><i class="menu-icon ti-layout-media-overlay"></i><a href="<%=request.getContextPath()%>/admin/catalog-news.htm">Danh mục tin tức</a></li>
                        </ul>
                    </li>
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-shopping-cart"></i>Nhóm đơn hàng</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon ti-write"></i><a href="<%=request.getContextPath()%>/admin/orders.htm">Đơn hàng</a></li>
                        </ul>
                    </li>
                    <h3 class="menu-title">TÍNH NĂNG</h3><!-- /.menu-title -->
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon ti-settings"></i>Cài đặt</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon ti-save-alt"></i><a href="<%=request.getContextPath()%>/admin/banner.htm">Banner</a></li>
                            <li><i class="menu-icon ti-gallery"></i><a href="<%=request.getContextPath()%>/admin/logo.htm">Logo</a></li>
                        </ul>
                    </li>
                     <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon ti-harddrives"></i>Nâng cao</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon ti-user"></i><a href="<%=request.getContextPath()%>/admin/list-admin.htm">Quản trị viên</a></li>
                            <li><i class="menu-icon ti-comments"></i><a href="<%=request.getContextPath()%>/admin/contact.htm">Liên hệ</a></li>
                            <li><i class="menu-icon ti-help"></i><a href="<%=request.getContextPath()%>/admin/faqs.htm">FAQ</a></li>
                            <li><i class="menu-icon ti-announcement"></i><a href="<%=request.getContextPath()%>/admin/introductions.htm">Giới thiệu</a></li>
                        </ul>
                    </li>
                    <h3 class="menu-title">Extras</h3><!-- /.menu-title -->
                    <li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-glass"></i>Pages</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="menu-icon fa fa-sign-in"></i><a href="login.jsp">Login</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </aside><!-- /#left-panel -->

    <!-- Left Panel -->
