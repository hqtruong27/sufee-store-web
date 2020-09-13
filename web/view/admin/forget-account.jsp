<%-- 
    Document   : login
    Created on : Jul 6, 2020, 2:56:16 PM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sufee Admin - HTML5 Admin Template</title>
        <meta name="description" content="Sufee Admin - HTML5 Admin Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="apple-touch-icon" href="apple-icon.png">
        <link rel="shortcut icon" href="favicon.ico">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/assets/vendors/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/assets/vendors/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/assets/vendors/themify-icons/css/themify-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/assets/vendors/flag-icon-css/css/flag-icon.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/assets/vendors/selectFX/css/cs-skin-elastic.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/assets/vendors/jqvmap/dist/jqvmap.min.css">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/assets/css/style.css">

        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>
    </head>
    <body class="bg-dark">
        <div class="sufee-login d-flex align-content-center flex-wrap">
            <div class="container">
                <div class="login-content">
                    <div class="login-logo">
                        <a href="index.html">
                            <img class="align-content" src="images/logo.png" alt="">
                        </a>
                    </div>
                    <div class="login-form">
                        <form>
                            <div class="form-group">
                                <label>Địa chỉ Email</label>
                                <input type="email" class="form-control" placeholder="Email">
                            </div>
                            <div class="register-link m-t-15 text-center">
                                <p>Quay trở lại đăng nhập ? <a href="login.jsp"> Đăng nhập</a></p>
                            </div>
                            <button type="submit" class="btn btn-primary btn-flat m-b-15">Tìm Kiếm</button>

                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/view/admin/assets/vendors/jquery/dist/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/view/admin/assets/vendors/popper.js/dist/umd/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/view/admin/assets/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/view/admin/assets/js/main.js"></script>


    </body>

</html>
