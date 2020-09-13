/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.CartSingle;
import common.EmailSend;
import common.MessageReturn;
import common.ShoppingCart;
import dao.BrandDAO;
import dao.CategoryDAO;
import dao.NewsDAO;
import dao.OrdersDAO;
import dao.ProductDAO;
import entity.Brands;
import entity.Customers;
import entity.OrderDetails;
import entity.Orders;
import entity.Products;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ASUS
 */
@Controller
public class ClientOrderController {

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final NewsDAO newsDAO = new NewsDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final OrdersDAO ordersDAO = new OrdersDAO();
    private final BrandDAO brandDAO = new BrandDAO();

    @RequestMapping(value = "orders")
    public String index(HttpSession session, Model model) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        String navHtml = categoryDAO.generateNavbarHtml();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();

        if (shoppingCart != null) {
            model.addAttribute("shoppingCart", shoppingCart);
            model.addAttribute("carts", shoppingCart.carts);
        }
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }

        return "client/shopping-cart";
    }

    @RequestMapping(value = "add-to-cart")
    public @ResponseBody
    String addToCarts(HttpSession session, int productId, Integer productQuantity) {
        Products products = productDAO.getProductId(productId);
        MessageReturn message = new MessageReturn();

        if (products.getProductCode() == null) {
            message.setResult(false);
            message.setMessage("Mã sản phẩm không tồn tại!");
            message.setMessageCode("product-null");
        }
        if (products.getProductStatus() != 1) {
            message.setResult(false);
            message.setMessage("Sản phẩm không khả dụng!");
            message.setMessageCode("product-unavailable");
        }
        if (productQuantity == null) {
            message.setResult(false);
            message.setMessage("Số lượng sản phẩm không tồn tại!");
            message.setMessageCode("quantity-invalid");
        }
        if (productQuantity <= 0) {
            message.setResult(false);
            message.setMessage("Số lượng sản phẩm không khả dụng!");
            message.setMessageCode("quantity-invalid");
        }

        Object cart = session.getAttribute("shoppingCart");
        ShoppingCart shoppingCart;

        if (cart == null) {
            shoppingCart = new ShoppingCart();
        } else {
            shoppingCart = (ShoppingCart) cart;
        }

        boolean insertCarts = shoppingCart.insertCarts(products, productQuantity);

        if (!insertCarts) {
            message.setResult(false);
            message.setMessage("Thêm sản phẩm vào giỏ hàng thất bại!");
            message.setMessageCode("add-failed");
        } else {
            message.setResult(true);
            message.setMessage("Thêm sản phẩm vào giỏ hàng thành công!");
            message.setMessageCode("add-success");
        }

        session.setAttribute("shoppingCart", shoppingCart);
        return message.toString();
    }

    @RequestMapping(value = "update-cart")
    public @ResponseBody
    String updateCarts(HttpSession session, int productId, Integer productQuantity) {
        Products products = productDAO.getProductId(productId);
        MessageReturn message = new MessageReturn();

        if (products.getProductCode() == null) {
            message.setResult(false);
            message.setMessage("Mã sản phẩm không tồn tại!");
            message.setMessageCode("product-null");
        }
        if (products.getProductStatus() != 1) {
            message.setResult(false);
            message.setMessage("Sản phẩm không khả dụng!");
            message.setMessageCode("product-unavailable");
        }
        if (productQuantity == null) {
            message.setResult(false);
            message.setMessage("Số lượng sản phẩm không tồn tại!");
            message.setMessageCode("quantity-invalid");
        }
        if (productQuantity <= 0) {
            message.setResult(false);
            message.setMessage("Số lượng sản phẩm không khả dụng!");
            message.setMessageCode("quantity-invalid");
        }

        Object cart = session.getAttribute("shoppingCart");
        ShoppingCart shoppingCart;

        if (cart == null) {
            message.setResult(false);
            message.setMessage("Giỏ hàng không tồn tại!");
            message.setMessageCode("cart-unexists");
            return message.toString();
        } else {
            shoppingCart = (ShoppingCart) cart;
            boolean updateCarts = shoppingCart.updateCarts(products, productQuantity);

            if (!updateCarts) {
                message.setResult(false);
                message.setMessage("Cập nhật giỏ hàng thất bại!");
                message.setMessageCode("update-failed");
                return message.toString();
            } else {
                message.setResult(true);
                message.setMessage("Cập nhật giỏ hàng thành công!");
                message.setMessageCode("update-success");
                session.setAttribute("shoppingCart", shoppingCart);
                return message.toString();
            }
        }
    }

    @RequestMapping(value = "delete-one-cart")
    public @ResponseBody
    String deleteCarts(HttpSession session, int productId, Integer productQuantity) {
        Products products = productDAO.getProductId(productId);
        MessageReturn message = new MessageReturn();

        if (products == null) {
            message.setResult(false);
            message.setMessage("Mã sản phẩm không tồn tại!");
            message.setMessageCode("product-null");
            return message.toString();
        }
        if (products.getProductStatus() != 1) {
            message.setResult(false);
            message.setMessage("Sản phẩm không khả dụng!");
            message.setMessageCode("product-unavailable");
            return message.toString();
        }

        Object cart = session.getAttribute("shoppingCart");

        if (cart == null) {
            message.setResult(false);
            message.setMessage("Giỏ hàng không tồn tại!");
            message.setMessageCode("cart-unexists");
            return message.toString();
        } else {
            ShoppingCart shoppingCart = (ShoppingCart) cart;
            boolean deleteOneCart = shoppingCart.deleteOneOfCarts(products);

            if (!deleteOneCart) {
                message.setResult(false);
                message.setMessage("Cập nhật giỏ hàng thất bại!");
                message.setMessageCode("update-failed");
                return message.toString();
            } else {
                message.setResult(true);
                message.setMessage("Xóa sản phẩm thành công!");
                message.setMessageCode("update-success");
                session.setAttribute("shoppingCart", shoppingCart);
                return message.toString();
            }
        }
    }

    @RequestMapping(value = "delete-all-cart")
    public @ResponseBody
    String deleteAllCarts(HttpSession session) {
        Object cart = session.getAttribute("shoppingCart");
        MessageReturn message = new MessageReturn();

        if (cart == null) {
            message.setResult(false);
            message.setMessage("Giỏ hàng không tồn tại!");
            message.setMessageCode("cart-unexists");
        } else {
            ShoppingCart shoppingCart = (ShoppingCart) cart;

            boolean deleteAllCart = shoppingCart.deleteAllCarts();

            if (!deleteAllCart) {
                message.setResult(false);
                message.setMessage("Xóa giỏ hàng thất bại!");
                message.setMessageCode("remove-failed");
            } else {
                message.setResult(true);
                message.setMessage("Xóa giỏ hàng thành công!");
                message.setMessageCode("remove-success");
                session.removeAttribute("shoppingCart");
            }
        }
        return message.toString();
    }

    @RequestMapping(value = "dat-hang", method = RequestMethod.GET)
    public String checkOut(HttpSession session, Model model) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        Customers customers = (Customers) session.getAttribute("InfoCustomer");
        String navHtml = categoryDAO.generateNavbarHtml();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();
        if (shoppingCart == null) {
            return "redirect:trang-chu.htm";
        }

        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }
        model.addAttribute("customers", customers);
        model.addAttribute("carts", shoppingCart.carts);
        return "client/check-out";
    }

    @RequestMapping(value = "dat-hang", method = RequestMethod.POST)
    public String checkOut(HttpSession session, Model model, Orders orders, RedirectAttributes attributes, String email) throws MessagingException {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        Customers customers = (Customers) session.getAttribute("InfoCustomer");
        orders.setCustomers(customers);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 10);
        orders.setTimeExpired(calendar.getTime());
        boolean check = ordersDAO.createOrders(orders, email);
        if (check) {
            List<CartSingle> carts = shoppingCart.carts;
            List<OrderDetails> listOrderdetail = new ArrayList<>();

            for (CartSingle c : carts) {
                OrderDetails od = new OrderDetails();
                od.setCreateDate(new Date());
                double priceSale = c.getProducts().getPrice() * c.getProducts().getProductSale() / 100;
                double price = c.getProducts().getPrice() - priceSale;
                od.setOrderDetailPrice(price);
                od.setOrderDetailQuantity(c.getProductQuantity());
                od.setOrders(orders);
                od.setProducts(c.getProducts());
                check = ordersDAO.createOrdersDetail(od);
                if (check) {
                    listOrderdetail.add(od);
                } else {
                    attributes.addFlashAttribute("error", "Đặt hàng không thành công");
                    return "redirect:dat-hang.htm";
                }
            }
            if (customers != null) {
                String emailTitle = "[SUFEE-STORE: XÁC NHẬN ĐẶT HÀNG THÀNH CÔNG]";
                String emailBody = generateOrderEmailBody(listOrderdetail);
                EmailSend.sendMail(email, emailTitle, emailBody);
                session.removeAttribute("shoppingCart");
                attributes.addFlashAttribute("success", "Đặt hàng thành công! Vui lòng kiểm tra trong email của bạn và trong mục đơn hàng của trang cá nhân!");
                return "redirect:gio-hang.htm?customerId=" + customers.getCustomerId();
            } else {
                String emailTitle = "[SUFEE-STORE: XÁC NHẬN ĐẶT HÀNG THÀNH CÔNG]";
                String emailBody = generateOrderEmailBody(listOrderdetail);
                EmailSend.sendMail(email, emailTitle, emailBody);
                session.removeAttribute("shoppingCart");
                return "redirect:trang-chu.htm";
            }
        } else {
            attributes.addFlashAttribute("error", "Đặt hàng không thành công");
            return "redirect:dat-hang.htm";
        }
    }

    @RequestMapping(value = "gio-hang", method = RequestMethod.GET)
    public String orderInseted(HttpSession session, Model model, int customerId) {
         if (session.getAttribute("InfoCustomer") == null) {
            return "redirect:trang-chu.htm";
        }
        String navHtml = categoryDAO.generateNavbarHtml();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();

        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }

        List<Orders> listOrders = ordersDAO.getOrderByCustomerId(customerId);
        Customers customer = (Customers) session.getAttribute("InfoCustomer");
        model.addAttribute("customer", customer);
        model.addAttribute("listOrders", listOrders);
        return "client/user-order";
    }

    @RequestMapping(value = "order-detail", method = RequestMethod.GET)
    public String orderDetail(HttpSession session, Model model, int orderId) {
         if (session.getAttribute("InfoCustomer") == null) {
            return "redirect:trang-chu.htm";
        }
        Customers customers = (Customers) session.getAttribute("InfoCustomer");
        String navHtml = categoryDAO.generateNavbarHtml();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();

        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }

        List<Products> products = productDAO.getAllProduct();
        List<Brands> brands = brandDAO.getAllBrands();
        Orders orders = ordersDAO.getOrderId(orderId);
        boolean timeExpired = orders.getTimeExpired().getTime() > new Date().getTime();
        if (timeExpired) {
            model.addAttribute("timeExpired", timeExpired);
        }
        List<OrderDetails> listOrderDetail = ordersDAO.getOrderDetailByOrderId(orderId);
        model.addAttribute("listOrderDetail", listOrderDetail);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("orders", orders);
        return "client/user-order-detail";
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
        emailBody += " <h2 style='font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;'> Cảm Ơn Bạn Đã Đặt Hàng.</h2>\n";
        emailBody += "</td>\n";
        emailBody += "</tr>\n";

        emailBody += "<tr>\n";
        emailBody += "<td align='left' style=' font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;'>\n";
        emailBody += "<p style='font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;'> Rất vui được phục vụ quý khách hàng, cảm ơn bạn đã ủng hộ Sufee Store! </p>\n";
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

    @RequestMapping(value = "cannel-order")
    public String cannelOrder(HttpSession session, RedirectAttributes attributes, Model model, int orderId) throws MessagingException {
        if (session.getAttribute("InfoCustomer") == null) {
            return "redirect:trang-chu.htm";
        }
        
        Customers customers = (Customers) session.getAttribute("InfoCustomer");
        
        Orders orders = ordersDAO.getOrderId(orderId);
        
        if (orders == null) {
            attributes.addFlashAttribute("error", "Mã đơn hàng không tồn tại");
            return "redirect:orders-detail.htm";
        }
        boolean check = ordersDAO.cannelOrder(orderId);
        if (check) {
            String email = orders.getEmail();
            List<OrderDetails> listOrderdetail = ordersDAO.getOrderDetailByOrderId(orderId);
            String emailTitle = "[SUFEE-STORE: XÁC NHẬN HỦY ĐƠN HÀNG]";
            String emailBody = generateOrderEmailBody(listOrderdetail);
            EmailSend.sendMail(email, emailTitle, emailBody);
            attributes.addFlashAttribute("error", "Đơn hàng đã bị hủy");
            return "redirect:gio-hang.htm?customerId=" + customers.getCustomerId();
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra");
            return "redirect:order-detail.htm?orderId="+orderId;
        }
    }
}
