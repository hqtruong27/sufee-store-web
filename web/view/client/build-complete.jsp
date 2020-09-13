<%-- 
    Document   : build-complete
    Created on : Jul 29, 2020, 11:22:05 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="widget/other/header.jsp" flush="true"/>
<jsp:include page="widget/other/navbar.jsp" flush="true"/>

<section id="breadcrumb">
    <div class="container">
        <ul class="breadcrumb">
            <li><a href="${pageContext.request.contextPath}/view/client/home-index.jsp">Trang ch?</a></li>
            <li class="active">Ki?m tra l?i c?u hình</li>
        </ul>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Ki?m tra l?i c?u hình</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 table-responsive">
                <table class="table table-hover table-striped table-condensed table-bordered">
                    <thead>
                        <tr>
                            <th>Tên s?n ph?m</th>
                            <th>Hình ?nh</th>
                            <th>S? l??ng</th>
                            <th>??n giá</th>
                            <th>Gi?m giá</th>
                            <th>Thành ti?n</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                                <tr>
                                    <td class="dp-flex-cart">Intel Core i7 8700 / 12M / 3.2GHz / 6 nh&#226;n 12 lu?ng</td>
                                    <td class="dp-flex-cart">
                                        <img src="Uploads/images/ProductImages/Intel/CPU/i7%208700/intel_core_i7_8700.jpg" alt="Intel Core i7 8700 / 12M / 3.2GHz / 6 nh&#226;n 12 lu?ng" style="width: 150px;" />
                                    </td>
                                    <td class="dp-flex-cart">
                                        <input type="number" min="1" style="width: 60px;" class="update-build-pc" data-id="6" value="1" />
                                    </td>
                                    <td class="dp-flex-cart">8,350,000</td>
                                    <td class="dp-flex-cart">00</td>
                                    <td class="dp-flex-cart">8,350,000</td>
                                    <td class="dp-flex-cart">
                                        <a href="" class="btn btn-sm btn-default remove-product-after-build" data-id="6"><i class="fa fa-close"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="dp-flex-cart">ASUS Z390 ROG MAXIMUS XI HERO LGA1151v2</td>
                                    <td class="dp-flex-cart">
                                        <img src="Uploads/images/ProductImages/Asus/mainboard/ASUS%20Z390%20ROG%20MAXIMUS%20XI%20HERO%20LGA1151v2/ASUS%20Z390%20ROG%2001.png" alt="ASUS Z390 ROG MAXIMUS XI HERO LGA1151v2" style="width: 150px;" />
                                    </td>
                                    <td class="dp-flex-cart">
                                        <input type="number" min="1" style="width: 60px;" class="update-build-pc" data-id="5" value="1" />
                                    </td>
                                    <td class="dp-flex-cart">7,990,000</td>
                                    <td class="dp-flex-cart">00</td>
                                    <td class="dp-flex-cart">7,990,000</td>
                                    <td class="dp-flex-cart">
                                        <a href="" class="btn btn-sm btn-default remove-product-after-build" data-id="5"><i class="fa fa-close"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="dp-flex-cart">(16G DDR4 2x8G 3000 ) Corsair Dominator Platinum RGB</td>
                                    <td class="dp-flex-cart">
                                        <img src="Uploads/images/ProductImages/Corsair/RAM/Corsair16GbDDR43000/ram_dominator_platinum_1.png" alt="(16G DDR4 2x8G 3000 ) Corsair Dominator Platinum RGB" style="width: 150px;" />
                                    </td>
                                    <td class="dp-flex-cart">
                                        <input type="number" min="1" style="width: 60px;" class="update-build-pc" data-id="11" value="1" />
                                    </td>
                                    <td class="dp-flex-cart">4,290,000</td>
                                    <td class="dp-flex-cart">00</td>
                                    <td class="dp-flex-cart">4,290,000</td>
                                    <td class="dp-flex-cart">
                                        <a href="" class="btn btn-sm btn-default remove-product-after-build" data-id="11"><i class="fa fa-close"></i></a>
                                    </td>
                                </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="7" align="right">
                                <b><u>T?ng ti?n:</u></b> 20,630,000
                                <div class="clearfix"><br /></div>
                                <a href="${pageContext.request.contextPath}/view/client/user-order.jsp" class="btn btn-primary" style="border-radius: unset;">Ki?m tra ??n hàng <i class="fa fa-arrow-circle-right"></i></a>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</section>
<jsp:include page="widget/footer.jsp" flush="true"/>
