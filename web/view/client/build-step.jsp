<%-- 
    Document   : build-step
    Created on : Jul 29, 2020, 11:20:57 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/view/client/home-index.jsp">Trang chủ</a></li>
            <li class="active">Trang xây dựng cấu hình</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Xây dựng cấu hình</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="col-md-6">
                        <a href="/Product/RebuildPC" class="btn btn-primary" style="border-radius: 0;"><i class="fa fa-refresh"></i> Tạo lại</a>
                    </div>
                    <div class="col-md-6 text-right">
                        <span><b>Tổng tiền dự tính:</b></span> <span id="build-total-amount">61,990,000</span> VNĐ
                    </div>
                </div>
                <div style="border-bottom: 1px solid #ccc; margin-bottom: 10px; padding-bottom: 10px;"></div>
                <button type="button" class="primary-btn" id="choose-cpu" href="#content-product" data-toggle="modal">Chọn CPU (vi xử lí)</button>
                <div id="append-cpu" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-mainboard" href="#content-product" data-toggle="modal">Chọn Mainboard (bo mạch chủ)</button>
                <div id="append-mainboard" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-ram" href="#content-product" data-toggle="modal">Chọn RAM (bộ nhớ trong)</button>
                <div id="append-ram" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-vga" href="#content-product" data-toggle="modal">Chọn VGA (card màn hình)</button>
                <div id="append-vga" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-ssd" href="#content-product" data-toggle="modal">Chọn SSD (ổ cứng thể rắn)</button>
                <div id="append-ssd" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-hdd" href="#content-product" data-toggle="modal">Chọn HDD (ổ đĩa cứng)</button>
                <div id="append-hdd" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-psu" href="#content-product" data-toggle="modal">Chọn PSU (nguồn máy tính)</button>
                <div id="append-psu" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-odd" href="#content-product" data-toggle="modal">Chọn ODD (Ổ đĩa)</button>
                <div id="append-odd" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-sound" href="#content-product" data-toggle="modal">Chọn Sound Card</button>
                <div id="append-sound" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-case" href="#content-product" data-toggle="modal">Chọn Case (vỏ máy tính)</button>
                <div id="append-case" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-mouse" href="#content-product" data-toggle="modal">Chọn chuột</button>
                <div id="append-mouse" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-keyboard" href="#content-product" data-toggle="modal">Chọn bàn phím</button>
                <div id="append-keyboard" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-headphone" href="#content-product" data-toggle="modal">Chọn Tai nghe</button>
                <div id="append-headphone" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-speaker" href="#content-product" data-toggle="modal">Chọn Loa</button>
                <div id="append-speaker" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <button type="button" class="primary-btn" id="choose-monitor" href="#content-product" data-toggle="modal">Chọn Monitor (màn hình)</button>
                <div id="append-monitor" style="border-bottom: 1px solid #ccc; margin: 20px 0; padding-bottom: 10px;">
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <b>Tổng tiền dự tính:</b> <span id="build-total-amount-1">61,990,000</span> VNĐ
                    </div>
                    <div class="col-md-6 text-right">
                        <a class="btn btn-primary" href="/Product/CheckAfterBuild" style="border-radius: 0;">Hoàn thành <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <div class="modal fade" id="content-product">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title">Lựa chọn linh kiện</h4>
                            </div>
                            <div class="modal-body table-responsive">

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Bỏ qua linh kiện này</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="widget/footer.jsp" flush="true"/>
