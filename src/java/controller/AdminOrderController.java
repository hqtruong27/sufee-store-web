/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.EmailSend;
import dao.AdminDAO;
import dao.BrandDAO;
import dao.FeedbackDAO;
import dao.OrdersDAO;
import dao.ProductDAO;
import entity.Brands;
import entity.Feedbacks;
import entity.OrderDetails;
import entity.Orders;
import entity.Products;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping(value = "admin")
public class AdminOrderController {

    private final AdminDAO adminDAO = new AdminDAO();
    private final FeedbackDAO feedbacksDAO = new FeedbackDAO();
    private final OrdersDAO ordersDAO = new OrdersDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final BrandDAO brandDAO = new BrandDAO();

    @RequestMapping(value = "orders", method = RequestMethod.GET)
    public String getAllOrder(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "redirect:login.htm";
        }
        List<Feedbacks> listFeedback = feedbacksDAO.notifyFeedback();
        int countNotifyFeedback = feedbacksDAO.countNotifyFeedback();
        if (countNotifyFeedback >= 0) {
            model.addAttribute("countNotifyFeedback", countNotifyFeedback);
        }
        List<Orders> listOrder = ordersDAO.notifyOrder();
        int countNotifyOrder = ordersDAO.countNotifyOrder();
        if (countNotifyOrder >= 0) {
            model.addAttribute("countNotifyOrder", countNotifyOrder);
        }
        model.addAttribute("listOrder", listOrder);
        List<Orders> listOrders = ordersDAO.getAllOrders();
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("listFeedback", listFeedback);
        return "admin/order-list";
    }

    @RequestMapping(value = "orders-detail", method = RequestMethod.GET)
    public String orderDetail(HttpSession session, RedirectAttributes attributes, Model model, int orderId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "redirect:login.htm";
        }
        List<Feedbacks> listFeedback = feedbacksDAO.notifyFeedback();
        int countNotifyFeedback = feedbacksDAO.countNotifyFeedback();
        if (countNotifyFeedback >= 0) {
            model.addAttribute("countNotifyFeedback", countNotifyFeedback);
        }
        List<Orders> listOrder = ordersDAO.notifyOrder();
        int countNotifyOrder = ordersDAO.countNotifyOrder();
        if (countNotifyOrder >= 0) {
            model.addAttribute("countNotifyOrder", countNotifyOrder);
        }
        model.addAttribute("listOrder", listOrder);
        List<Products> products = productDAO.getAllProduct();
        List<Brands> brands = brandDAO.getAllBrands();
        Orders orders = ordersDAO.getOrderId(orderId);
        List<OrderDetails> listOrderDetails = ordersDAO.getOrderDetailByOrderId(orderId);
        model.addAttribute("listOrderDetails", listOrderDetails);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("brands", brands);
        model.addAttribute("orders", orders);
        model.addAttribute("products", products);
        return "admin/order-detail";
    }

    @RequestMapping(value = "accept-order")
    public String acceptOrder(HttpSession session, RedirectAttributes attributes, Model model, int orderId) throws MessagingException {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục");
            return "redirect:login.htm";
        }
        Orders orders = ordersDAO.getOrderId(orderId);
        
        if (orders == null) {
            attributes.addFlashAttribute("error", "Mã đơn hàng không tồn tại");
            return "redirect:orders-detail.htm";
        }
        boolean check = ordersDAO.acceptOrder(orderId);
        
        if (check) {
            String email = orders.getEmail();
            List<OrderDetails> listOrderdetail = ordersDAO.getOrderDetailByOrderId(orderId);
            String emailTitle = "[SUFEE-STORE: XÁC NHẬN GIAO HÀNG THÀNH CÔNG]";
            String emailBody = generateOrderEmailBody(listOrderdetail);
            EmailSend.sendMail(email, emailTitle, emailBody);
            attributes.addFlashAttribute("success", "Xác nhận giao hàng");
            return "redirect:orders.htm";
        }else{
            attributes.addFlashAttribute("error", "Có lỗi xảy ra");
            return "redirect:orders-detail.htm";
        }
    }
    
     private String generateOrderEmailBody(List<OrderDetails> orderDetails) {
        String emailBody = "";

        emailBody += "<table border='0' cellpadding='0' cellspacing='0' width='60%'>\n";
        emailBody += "<tr>\n";
        emailBody += "<td align='center' valign='top' style='font-size:0; padding: 35px;' bgcolor='#F44336'>\n";
        emailBody += "<div style='display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;'>\n";
        emailBody += "<table align='left' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:300px;'>\n";
        emailBody += "<tr>\n";
        emailBody += "<td align='left' valign='top' style=' font-size: 36px; font-weight: 800; line-height: 48px;' class='mobile-center'>\n";
        emailBody += "<h1 style='font-size: 36px; font-weight: 800; margin: 0; color: #ffffff;'>SUFEESTORE</h1>\n";
        emailBody += "</td>\n";
        emailBody += "</tr>\n";
        emailBody += "</table>\n";
        emailBody += "</div>\n";
        emailBody += "<div style='display:inline-block; max-width:10%; min-width:100px; vertical-align:top; width:100%;' class='mobile-hide'>\n";
        emailBody += "<table align='left' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:300px;'>\n";
        emailBody += "<tr>\n";
        emailBody += "<td align='right' valign='top' style=' font-size: 48px; font-weight: 400; line-height: 48px;'>\n";
        emailBody += "<table cellspacing='0' cellpadding='0' border='0' align='right'>\n";
        emailBody += "<tr>\n";
        emailBody += "<td style=' font-size: 18px; font-weight: 400;'>\n";
        emailBody += "<p style='font-size: 18px; font-weight: 400; margin: 0; color: #ffffff;'><a href='http://localhost:8080/Sufee_Store/trang-chu.htm' target='_blank' style='color: #ffffff; text-decoration: none;'>Shop &nbsp;</a></p>\n";
        emailBody += "</td>\n";
        emailBody += "<td style=' font-size: 18px; font-weight: 400; line-height: 24px;'><a href='http://localhost:8080/Sufee_Store/trang-chu.htm' target='_blank' style='color: #ffffff; text-decoration: none;'><img src='https://img.icons8.com/color/48/000000/small-business.png' width='27' height='23' style='display: block; border: 0px;' /></a></td>\n";
        emailBody += "</tr>\n";
        emailBody += "</table>\n";
        emailBody += "</td>\n";
        emailBody += "</tr>\n";
        emailBody += "</table>\n";
        emailBody += "</div>\n";
        emailBody += "</td>\n";
        emailBody += "</tr>\n";

        emailBody += "<tr>\n";
        emailBody += "<td align='center' style='padding: 35px 35px 20px 35px; background-color: #ffffff;' bgcolor='#ffffff'>\n";
        emailBody += "<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:100%;'>\n";
        emailBody += "<tr>\n";
        emailBody += "<td align='center' style=' font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;'> <img src='https://img.icons8.com/carbon-copy/100/000000/checked-checkbox.png' width='125' height='120' style='display: block; border: 0px;' /><br>\n";
        emailBody += " <h2 style='font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;'>Đơn Hàng Đang Được Gửi</h2>\n";
        emailBody += "</td>\n";
        emailBody += "</tr>\n";

        emailBody += "<tr>\n";
        emailBody += "<td align='left' style=' font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;'>\n";
        emailBody += "<p style='font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;'> Rất vui được phục vụ quý khách hàng, đơn hàng của quý khách sẽ được giao tới trong vòng 2 đến 3 ngày tới ! </p>\n";
        emailBody += "<p style='font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;'> Chi tiết đơn hàng: </p>\n";
        emailBody += "</td>\n";
        emailBody += "</tr>\n";

        emailBody += "<tr>\n";
        emailBody += "<td align='left' style='padding-top: 20px;'>\n";

        emailBody += "<table cellspacing='0' cellpadding='0' border='0' width='100%'>\n";

        emailBody += "<thead>\n";
        emailBody += "<tr>\n";
        emailBody += "<td width='30%' align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'> Tên sản phẩm </td>\n";
        emailBody += "<td align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'>Số lượng</td>\n";
        emailBody += "<td align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'> Đơn giá</td>\n";
        emailBody += "<td align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'>Giảm giá</td>\n";
        emailBody += "<td align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'>Thành tiền</td>\n";
        emailBody += "</tr>\n";
        emailBody += "</thead>\n";

        emailBody += "<tbody>\n";

        for (OrderDetails od : orderDetails) {
            emailBody += "<tr>\n";
            emailBody += "<td width='30%' align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'>" + od.getProducts().getProductName() + " </td>\n";
            emailBody += "<td align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'>" + od.getOrderDetailQuantity() + "</td>\n";
            emailBody += "<td align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'>" + String.format("%,.0f", od.getOrderDetailPrice()) + "</td>\n";
            emailBody += "<td align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'>" + od.getProducts().getProductSale() + "%</td>\n";
            emailBody += "<td align='left' bgcolor='#eeeeee' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'>" + String.format("%,.0f", od.getOrderDetailPrice() * (100 - od.getProducts().getProductSale()) / 100 * od.getOrderDetailQuantity()) + "</td>\n";
            emailBody += "</tr>\n";
        }

        emailBody += "</tbody>\n";

        emailBody += "</table>\n";

        emailBody += "</td>\n";
        emailBody += "</tr>\n";

        emailBody += "<tr>\n";
        emailBody += "<td align='left' style='padding-top: 20px;'>\n";
        emailBody += "<table cellspacing='0' cellpadding='0' border='0' width='100%'>\n";
        emailBody += "<tr>\n";
        emailBody += "<td width='75%' align='left' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;'> TỔNG TIỀN </td>\n";
        emailBody += "<td width='25%' align='left' style=' font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;'> " + String.format("%,.0f", orderDetails.get(0).getOrders().getOrderTotalAmount()) + " VNĐ </td>\n";
        emailBody += "</tr>\n";

        emailBody += "</table>\n";
        emailBody += "</td>\n";
        emailBody += "</tr>\n";
        emailBody += "</table>\n";
        emailBody += "</td>\n";
        emailBody += "</tr>\n";
        emailBody += "</table>\n";
        emailBody += "</td>\n";
        emailBody += "</tr>\n";
        emailBody += "</table>\n";

        return emailBody;

    }
}
