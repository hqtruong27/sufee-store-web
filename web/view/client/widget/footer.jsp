<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
</div>
<footer id="footer" class=" section-grey">
    <div class="container" style="margin-bottom: 10px">
        <div class="col-md-12">
            <div class="col-md-3 col-sm-6 col-xs-6">
                <div class="footer">
                    <h3 class="footer-header">Hỗ trợ khách hàng</h3>
                    <ul class="list-links">
                        <li><a href="${pageContext.request.contextPath}/introductions.htm">Giới thiệu Sufee Store</a></li>
                        <li><a href="${pageContext.request.contextPath}/news-list-catalog.htm?catalogId=1">Tin công nghệ</a></li>
                        <li><a href="${pageContext.request.contextPath}/faqs.htm">Hỏi đáp</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-6">
                <div class="footer">
                    <h3 class="footer-header">Cộng đồng Sufee Store</h3>
                    <ul class="list-links">
                        <li><a href="https://www.facebook.com/lamluong99"><i class="fa fa-facebook-official" style="color:  #485992"></i> Fanpage Sufee</a></li>
                        <li><a href="https://bitly.com.vn/qVuBt"><i class="fa fa-youtube-play" style="color:  #cf2200"></i> Sufee Offical</a></li>
                        <li><a href="tel:0858448627"><i class="fa fa-phone" style="color: #ff5500"></i> Gọi mua hàng: ${contact.contactHotline}</a></li>
                        <li><a href="tel:0858448627"><i class="fa fa-phone" style="color: #ff5500"></i> Gọi tư vấn: ${contact.contactHotline}</a></li>
                        <li><a href="https://www.messenger.com/t/lambkap"><i class="fa fa-wechat" style="color: #ff5500"></i> Chat với tư vấn viên</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-6">
                <div class="footer">
                    <h3 class="footer-header">Email liên hệ</h3>
                    <ul class="list-links">
                        <li><span>Hỗ trợ Khách hàng</span><p><a href="mailto:ltlam.bkap@gmail.com.vn" style="color: #0d6efd"><c:if test="${not empty contact}">${contact.contactEmail}</c:if></a></p></li>
                        <li><span>Liên hệ báo giá</span><p><a href="mailto:ltlam.bkap@gmail.com.vn" style="color: #0d6efd"><c:if test="${not empty contact}">${contact.contactEmail}</c:if></a></p></li>
                        <li><span>Hợp tác phát triên</span><p><a href="mailto:ltlam.bkap@gmail.com.vn" style="color: #0d6efd"><c:if test="${not empty contact}">${contact.contactEmail}</c:if></a></p></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-6" >
                <div class="footer" >
                    <h3 class="footer-header">Các Thương Hiệu Nổi Tiếng</h3>
                    <div class="col-md-12" style="padding-left: 0px">
                        <c:forEach items="${brand}" var="brand">
                            <div class="col-md-3 col-sm-6 col-xs-6" style="padding-left: 0px;margin-bottom: 10px"><a><img src="${brand.brandLogo}" alt="${brand.brandName}" style="width: 40px;height: 40px" class="img-responsive"/></a></div>
                                </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class=" bottom-footer">
        <div class="container">
            <div class="col-md-12">
                <div class="col-md-5 col-sm-6 col-xs-6">
                    <div class="footer">
                        <div style="font-weight: 600;margin-bottom: 20px">CÔNG TY CỔ PHẦN THƯƠNG MẠI DỊCH VỤ SUFEE</div>
                        <p style="font-size:  12px">© 1997 - 2020 Công Ty Cổ Phần Thương Mại - Dịch Vụ SUFEE / GPĐKKD số 99999999 do Sở KHĐT TP.HN cấp</p>
                    </div>
                </div>
                <div class="col-md-5 col-sm-6 col-xs-6">
                    <div class="footer">
                        <div class="footer">
                           <span style="font-size:12px;font-weight: 600">Văn phòng điều hành miền Bắc:</span><p style="font-size:  11px">Tầng 6, Số 1 Phố Thái Hà, Phường Trung Liệt, Quận Đống Đa, Hà Nội</p>
                        </div>
                        <div class="footer">
                           <span style="font-size:12px;font-weight: 600">Văn phòng điều hành miền Nam:</span><p style="font-size:  11px">Tầng 7, tòa nhà số 198 Nguyễn Thị Minh Khai, Phường 6, Quận 3, TP. Hồ Chí Minh</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-2 col-sm-6 col-xs-6" >
                    <div class="footer" >
                        <div class="col-md-12" style="padding-left: 0px;">
                            <img src="${pageContext.request.contextPath}/view/client/uploads/da-dang-ky.png" class="img-responsive"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<style>
    .back-to-top {
        position: fixed;
        top: auto;
        left: auto;
        right: 15px;
        bottom: 15px;
        font-size: 26px;
        opacity: 0.8;
        z-index: 9999;
        cursor: pointer;
        border: 1px solid;
        width: 40px;
        text-align: center;
        border-radius: 50%;
        color: white;
        background: black
    }

    .back-to-top:hover {
        opacity: 1;
    }
</style>
<div id="back-to-top" class="back-to-top" data-toggle="tooltip" data-placement="left" title="Trở lên đầu trang"><span class="fa fa-angle-up"></span></div>
<script>
    $("#back-to-top").click(function () {
        return $("body, html").animate({scrollTop: 0}, 400), !1
    });
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });
</script>
<script src="${pageContext.request.contextPath}/view/client/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/view/client/assets/js/jquery.flexisel.js"></script>
<script src="${pageContext.request.contextPath}/view/client/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/view/client/assets/js/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/view/client/assets/js/nouislider.min.js"></script>
<script src="${pageContext.request.contextPath}/view/client/assets/js/jquery.zoom.min.js"></script>
<script src="${pageContext.request.contextPath}/view/client/assets/js/main.js"></script>
<script src="${pageContext.request.contextPath}/view/admin/assets/bootstrap-notify-3.1.3/dist/bootstrap-notify.min.js" type="text/javascript"></script>
</body>
</html>
