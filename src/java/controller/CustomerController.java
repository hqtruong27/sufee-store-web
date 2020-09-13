package controller;

import Validate.RegexValid;
import common.EmailSend;
import common.MessageReturn;
import dao.CustomerDAO;
import dao.CategoryDAO;
import dao.FeedbackDAO;
import dao.NewsDAO;
import dao.ProductDAO;
import dao.WishlistDAO;
import entity.Admins;
import entity.Customers;
import entity.FeedbackCatalogs;
import entity.Feedbacks;
import entity.Products;
import entity.Wishlists;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ASUS
 */
@Controller
public class CustomerController {

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final FeedbackDAO feedbackDAO = new FeedbackDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final NewsDAO newsDAO = new NewsDAO();
    private final WishlistDAO wishlistDAO = new WishlistDAO();
    private final ProductDAO productDAO = new ProductDAO();

    @RequestMapping(value = "dang-nhap", method = RequestMethod.GET)
    public String Login(HttpSession session, Model model) {
        if (session.getAttribute("InfoCustomer") != null) {
            return "redirect:trang-chu.htm";
        }
        String navHtml = categoryDAO.generateNavbarHtml();
        String newsHtml = newsDAO.generateNewsHtml();
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }
        return "client/customer-login";
    }

    @RequestMapping(value = "dang-nhap", method = RequestMethod.POST)
    public String Login(Customers customer, HttpSession session, RedirectAttributes attributes) {
        Customers check = customerDAO.login(customer.getEmail(), customer.getPasswords());
        if (check != null) {
            switch (check.getCustomerStatus()) {
                case 0:
                    attributes.addFlashAttribute("error", "Tài khoản của bạn đã bị khóa !!");
                    return "redirect:dang-nhap.htm";
                case 1:
                    session.setAttribute("InfoCustomer", check);
                    attributes.addFlashAttribute("success", check.getFullname());
                    return "redirect:trang-chu.htm";
                default:
                    attributes.addFlashAttribute("error", "Tài khoản hoặc mật khẩu không chính xác !!");
                    return "redirect:dang-nhap.htm";
            }
        } else {
            attributes.addFlashAttribute("error", "Tài khoản hoặc mật khẩu không chính xác !!");
            return "redirect:dang-nhap.htm";
        }
    }

    @RequestMapping(value = "logout")
    public String Logout(HttpSession session, RedirectAttributes attributes) {
        if (session.getAttribute("InfoCustomer") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:dang-nhap.htm";
        }
        session.removeAttribute("InfoCustomer");
        return "redirect:dang-nhap.htm";
    }

    @RequestMapping(value = "dang-ky", method = RequestMethod.GET)
    public String Register(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoCustomer") != null) {
            return "redirect:trang-chu.htm";
        }
        String navHtml = categoryDAO.generateNavbarHtml();
        String newsHtml = newsDAO.generateNewsHtml();
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }
        Customers customer = new Customers();
        model.addAttribute("customer", customer);
        return "client/customer-register";
    }

    @RequestMapping(value = "dang-ky", method = RequestMethod.POST)
    public String Register(RedirectAttributes attributes, String fullname, String birthday, String passwords, String rePassword, String gender, String email, String phone, String customerAddress) {
       
        boolean check = RegexValid.isEmpty(fullname);
        if (check) {
            attributes.addFlashAttribute("errorname", "Chưa nhập họ và tên");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.isEmpty(passwords);
        if (check) {
            attributes.addFlashAttribute("errorpass", "Chưa nhập mật khẩu");
            return "redirect:dang-ky.htm";
        }

        check = RegexValid.isEmpty(birthday);

        if (check) {
            attributes.addFlashAttribute("errorbirthday", "Chưa nhập ngày sinh");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.isEmpty(email);

        if (check) {
            attributes.addFlashAttribute("erroremail", "Chưa nhập email cuả bạn");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.isEmpty(phone);
        if (check) {
            attributes.addFlashAttribute("errorphone", "Chưa nhập số điện thoại");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.isEmpty(customerAddress);
        if (check) {
            attributes.addFlashAttribute("erroraddress", "Chưa nhập địa chỉ");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.isEmpty(rePassword);
        if (check) {
            attributes.addFlashAttribute("errorrepassword", "Chưa nhập lại mật khẩu");
            return "redirect:dang-ky.htm";
        }
        
        check = passwords.equals(rePassword);
        if (!check) {
            attributes.addFlashAttribute("errorrepassword", "Mật khẩu nhập lại không đúng");
            return "redirect:dang-ky.htm";
        }
        
        check = RegexValid.checkMaxLenght(fullname, 250);
        if (!check) {
            attributes.addFlashAttribute("errorname", "Họ tên không vượt quá 250 ký tự");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.checkMaxLenght(email, 250);
        if (!check) {
            attributes.addFlashAttribute("erroremail", "Email không vượt quá 250 ký tự");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.checkMaxLenght(passwords, 20);
        if (!check) {
            attributes.addFlashAttribute("errorpass", "Mật khẩu không vượt 20 ký tự");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.checkMinLenght(passwords, 6);
        if (!check) {
            attributes.addFlashAttribute("errorpass", "Mật khẩu ít nhất 6 ký tự");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.isMail(email);
        if (!check) {
            attributes.addFlashAttribute("erroremail", "Email không đúng định dạng");
            return "redirect:dang-ky.htm";
        }
        check = RegexValid.isPhoneNumber(phone);
        if (!check) {
            attributes.addFlashAttribute("errorphone", "Số điện thoại không đúng định dạng");
            return "redirect:dang-ky.htm";
        }
        check = customerDAO.checkEmailExists(email);
        if (check) {
            attributes.addFlashAttribute("erroremail", "Email này đã được sử dụng");
            return "redirect:dang-ky.htm";
        }
        check = customerDAO.checkPhoneExists(phone);
        if (check) {
            attributes.addFlashAttribute("errorphone", "Số điện này đã được sử dụng");
            return "redirect:dang-ky.htm";
        }
        Customers c = new Customers();
        Date registerCustomerBirthday = RegexValid.convertStringToDate(birthday, "yyyy-MM-dd");
        int registerCustomerGender = RegexValid.convertStringToInt(gender, 0);
        c.setFullname(fullname);
        c.setBirthday(registerCustomerBirthday);
        c.setPasswords(passwords);
        c.setGender(registerCustomerGender);
        c.setEmail(email);
        c.setPhone(phone);
        c.setCustomerAddress(customerAddress);
        c.setCustomerStatus(1);
        check = customerDAO.register(c);
        if (check) {
            attributes.addFlashAttribute("success", "Đăng ký thành công !");
            return "redirect:dang-nhap.htm";
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !");
            return "redirect:dang-ky.htm";
        }
    }

    @RequestMapping(value = "edit-profile", method = RequestMethod.GET)
    public String EditProfile(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoCustomer") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:dang-nhap.htm";
        }
        String navHtml = categoryDAO.generateNavbarHtml();
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        Customers customer = (Customers) session.getAttribute("InfoCustomer");
        model.addAttribute("customer", customer);
        return "client/user-change-info";
    }

    @RequestMapping(value = "edit-profile", method = RequestMethod.POST)
    public String EditProfile(@RequestParam(value = "avatar") CommonsMultipartFile commonsMultipartFiles, HttpServletRequest request, HttpSession session, String fullname, String phone, String birthday, String customerAddress, RedirectAttributes attributes) {
        if (session.getAttribute("InfoCustomer") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:dang-nhap.htm";
        }
        Customers customer = (Customers) session.getAttribute("InfoCustomer");

        if (customerDAO.checkPhoneExists(phone) && !phone.equals(customer.getPhone())) {
            attributes.addFlashAttribute("error", "Số điện thoại này đã được sử dụng !!");
            return "redirect:edit-profile.htm";
        }

        String file = commonsMultipartFiles.getOriginalFilename();
        String extension = FilenameUtils.getExtension(file);
        String oldFileName;

        if (!"".equals(file)) {
            if (!RegexValid.isValidImage(extension)) {
                attributes.addFlashAttribute("error", "Định dạng tệp tin không được hỗ trợ!");
                return "redirect:edit-profile.htm";
            }

            String newFileName = RegexValid.generateNameTypeB("avatar");
            newFileName += "." + extension;

            String path = request.getServletContext().getRealPath("view/client/uploads/images/Customers");
            path = path.substring(0, path.indexOf("\\build"));
            path = path + "\\web\\view\\client\\uploads\\images\\Customers";
            File fileDir = new File(path);
            oldFileName = path + "\\" + customer.getAvatar();

            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            try {
                commonsMultipartFiles.transferTo(new File(fileDir + File.separator + newFileName));

                boolean check = customerDAO.changeInfoCustomer(customer.getCustomerId(), fullname, phone, birthday, customerAddress, newFileName);

                if (!check) {
                    attributes.addFlashAttribute("error", "Thay đổi avatar thất bại!");
                    return "redirect:edit-profile.htm";
                }
            } catch (IOException | IllegalStateException e) {
                attributes.addFlashAttribute("error", "Tải file thất bại! \n" + e.getMessage());
                return "redirect:edit-profile.htm";
            }
        } else {
            boolean check = customerDAO.changeInfoCustomer(customer.getCustomerId(), fullname, phone, birthday, customerAddress, customer.getAvatar());
            if (check) {
                attributes.addFlashAttribute("success", "Thay đổi thông tin thành công! Vui lòng đăng nhập lại!");
                session.removeAttribute("InfoCustomer");
                return "redirect:dang-nhap.htm";
            } else {
                attributes.addFlashAttribute("error", "Không thể thay đổi thông tin!");
                return "redirect:edit-profile.htm";
            }
        }

        if (oldFileName.length() > 0) {
            Path oldPath = Paths.get(oldFileName);

            if (Files.exists(oldPath)) {
                try {
                    Files.delete(oldPath);
                } catch (IOException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        attributes.addFlashAttribute("success", "Thay đổi thông tin thành công! Vui lòng đăng nhập lại!");
        session.removeAttribute("InfoCustomer");
        return "redirect:dang-nhap.htm";
    }

    @RequestMapping(value = "change-password", method = RequestMethod.GET)
    public String ChangePassword(HttpSession session, Model model, RedirectAttributes attributes) {
        if (session.getAttribute("InfoCustomer") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";

        }
        String navHtml = categoryDAO.generateNavbarHtml();
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        Customers customer = (Customers) session.getAttribute("InfoCustomer");
        model.addAttribute("customer", customer);
        return "client/user-change-password";
    }

    @RequestMapping(value = "change-password", method = RequestMethod.POST)
    public String ChangePassword(HttpSession session, RedirectAttributes attributes, String passwordOld, String passwordNew, String repasswordNew) {
        if (session.getAttribute("InfoCustomer") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:client/login.htm";
        }

        Customers customer = (Customers) session.getAttribute("InfoCustomer");
        if (RegexValid.isEmpty(passwordOld)) {
            attributes.addFlashAttribute("error", "Mật khẩu cũ không được để trống !!");
            return "redirect:change-password.htm";
        }
        if (RegexValid.isEmpty(passwordNew)) {
            attributes.addFlashAttribute("error", "Mật khẩu mới không được để trống !!");
            return "redirect:change-password.htm";
        }
        if (RegexValid.isEmpty(repasswordNew)) {
            attributes.addFlashAttribute("error", "Mật khẩu nhập lại không được để trống !!");
            return "redirect:change-password.htm";
        }
        if (!passwordOld.equals(customer.getPasswords())) {
            attributes.addFlashAttribute("error", "Mật khẩu cũ không đúng !!");
            return "redirect:change-password.htm";
        }
        if (!passwordNew.equals(repasswordNew)) {
            attributes.addFlashAttribute("error", "Mật khẩu nhập lại không khớp !!");
            return "redirect:change-password.htm";
        }
        if (passwordNew.equals(customer.getPasswords())) {
            attributes.addFlashAttribute("error", "Mật khẩu mới không được giống mật khẩu cũ !!");
            return "redirect:change-password.htm";
        }

        boolean check = customerDAO.changePassword(customer.getCustomerId(), passwordNew);
        if (!check) {
            attributes.addFlashAttribute("error", "Không thể thay đổi mật khẩu !!");
            return "redirect:change-infocustomer.htm";
        } else {
            attributes.addFlashAttribute("success", "Bạn đã thay đổi mật khẩu, Mời đăng nhập lại !!");
            session.removeAttribute("InfoCustomer");
            return "redirect:login.htm";
        }
    }

    //FeebBack for customer on view client GET
    @RequestMapping(value = "feedback", method = RequestMethod.GET)
    public String Feedback(HttpSession session, Model model) {
        Admins admin = (Admins) session.getAttribute("InfoAdmin");
        List<FeedbackCatalogs> listFeebackCatalog = feedbackDAO.getAllFeedbackCataLog();
        String navHtml = categoryDAO.generateNavbarHtml();
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        model.addAttribute("listFeebackCatalog", listFeebackCatalog);
        model.addAttribute("admin", admin);
        Feedbacks feedbacks = new Feedbacks();
        model.addAttribute("feedbacks", feedbacks);
        return "client/feedback";
    }

    //FeebBack for customer on view client POST
    @RequestMapping(value = "feedback", method = RequestMethod.POST)
    public String Feedback(RedirectAttributes attributes, Feedbacks feedbacks, HttpSession session) {
        Admins admins = (Admins) session.getAttribute("InfoAdmin");
        feedbacks.setAdmins(admins);
        boolean bl = feedbackDAO.createFeedback(feedbacks);
        if (bl) {
            attributes.addFlashAttribute("success", "Cảm ơn bạn đã đánh giá !!");
            return "redirect:feedback.htm";
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "redirect:feedback.htm";
        }
    }

    //wishlists for customer on view client GET
    @RequestMapping(value = "wishlists", method = RequestMethod.GET)
    public String Wishlists(HttpSession session, Model model) {
        Customers customers = (Customers) session.getAttribute("InfoCustomer");
        List<Wishlists> wishlistses = wishlistDAO.getWishlistByCustomerId(customers.getCustomerId());
        List<Products> products = productDAO.getAllProduct();
        String navHtml = categoryDAO.generateNavbarHtml();
        String newsHtml = newsDAO.generateNewsHtml();
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }
        model.addAttribute("wishlistses", wishlistses);
        model.addAttribute("customers", customers);
        model.addAttribute("products", products);
        return "client/user-wishlist";
    }

    //wishlists for customer on view client GET
    @RequestMapping(value = "yeu-thich")
    public @ResponseBody
    String createWishlists(HttpSession session, Model model, Wishlists wishlists, int productId) {
        MessageReturn message = new MessageReturn();

        if (session.getAttribute("InfoCustomer") == null) {
            message.setResult(false);
            message.setMessageCode("not-login");
            return message.toString();
        }

        Products products = productDAO.getProductId(productId);
        Customers customers = (Customers) session.getAttribute("InfoCustomer");
        boolean check;
        if (products == null || products.getProductStatus() != 1) {
            message.setResult(false);
            message.setMessageCode("product-invalid");
            return message.toString();
        } else {
            List<Wishlists> wishlistByCustomer = wishlistDAO.getWishlistByCustomerId(customers.getCustomerId());

            for (Wishlists w : wishlistByCustomer) {
                if (Objects.equals(w.getProducts().getProductId(), products.getProductId())) {
                    check = wishlistDAO.removeWishlist(customers, products);

                    if (check) {
                        message.setResult(true);
                        message.setMessageCode("remove-success");
                        return message.toString();
                    } else {
                        message.setResult(false);
                        message.setMessageCode("remove-failed");
                        return message.toString();
                    }
                }
            }

            Wishlists wl = new Wishlists();
            wl.setWishlistStatus(1);
            wl.setCustomers(customers);
            wl.setProducts(products);
            check = wishlistDAO.createWishlist(wl);

            if (check) {
                message.setResult(true);
                message.setMessageCode("add-success");
                return message.toString();
            } else {
                message.setResult(false);
                message.setMessageCode("add-failed");
                return message.toString();
            }
        }

    }

    //wishlists for customer on view client GET
    @RequestMapping(value = "quen-mat-khau", method = RequestMethod.GET)
    public String forgot(HttpSession session, Model model) {
        Customers customers = (Customers) session.getAttribute("InfoCustomer");
        model.addAttribute("customers", customers);
        return "client/forgot";
    }

    @RequestMapping(value = "quen-mat-khau", method = RequestMethod.POST)
    public String forgot(HttpSession session, Model model, RedirectAttributes attributes, String email) throws MessagingException {

        boolean check = customerDAO.checkEmailExists(email);
        if (!check) {
            attributes.addFlashAttribute("error", "Email không tồn tại!");
            return "redirect:quen-mat-khau.htm";
        }
        Customers customers = customerDAO.getCustomerByEmail(email);
        customers.setCheckCode(RegexValid.generateRandomString());

        check = customerDAO.updateCustomer(customers);
        if (check) {
            attributes.addFlashAttribute("success", "Yêu cầu đặt lại mật khẩu thành công! Một email xác nhận đã được gửi tới email đăng ký của bạn! Vui lòng kiểm tra và xác nhận lại!");
            String emailTitle = "[SUFEE-STORE: YÊU CẦU ĐẶT LẠI MẬT KHẨU]";
            String emailBody = "Đã có yêu cầu đặt lại mật khẩu cho tài khoản của bạn! Click <a href='http://localhost:8080/Sufee_Store/xac-thuc.htm?email=" + email + "&checkCode=" + customers.getCheckCode() + "'>vào đây</a> để xác nhận và đổi mật khẩu";
            EmailSend.sendMail(customers.getEmail(), emailTitle, emailBody);
            return "redirect:quen-mat-khau.htm";
        } else {
            attributes.addFlashAttribute("error", "Không thể đặt lại mật khẩu vào lúc này!");
            return "redirect:quen-mat-khau.htm";
        }

    }

    @RequestMapping(value = "xac-thuc")
    public String confirmForgot(HttpSession session, Model model, RedirectAttributes attributes, String email, String checkCode) {
        Customers customers = customerDAO.getCustomerByEmail(email);
        if (customers == null) {
            attributes.addFlashAttribute("error", "Email không tồn tại!");
            return "redirect:dang-nhap.htm";
        }
        if (!checkCode.equals(customers.getCheckCode())) {
            attributes.addFlashAttribute("error", "Mã xác thực không chính xác");
            return "redirect:dang-nhap.htm";
        }

        session.setAttribute("confirmChange", email);
        return "redirect:doi-mat-khau.htm";
    }

    @RequestMapping(value = "doi-mat-khau", method = RequestMethod.GET)
    public String confirmChange(HttpSession session, Model model, RedirectAttributes attributes) {
        if (session.getAttribute("confirmChange") == null) {
            attributes.addFlashAttribute("error", "Mã xác thực không chính xác");
            return "redirect:dang-nhap.htm";
        }

        Customers customers = customerDAO.getCustomerByEmail((String) session.getAttribute("confirmChange"));
        if (customers == null) {
            attributes.addFlashAttribute("error", "Email không tồn tại!");
            return "redirect:dang-nhap.htm";
        }

        return "client/confirm-change";
    }

    @RequestMapping(value = "doi-mat-khau", method = RequestMethod.POST)
    public String confirmChange(HttpSession session, Model model, RedirectAttributes attributes, String passwords, String rePassword) {
        if (session.getAttribute("confirmChange") == null) {
            attributes.addFlashAttribute("error", "Mã xác thực không chính xác");
            return "redirect:dang-nhap.htm";
        }

        Customers customers = customerDAO.getCustomerByEmail((String) session.getAttribute("confirmChange"));
        if (customers == null) {
            attributes.addFlashAttribute("error", "Email không tồn tại!");
            return "redirect:dang-nhap.htm";
        }

        if (!(RegexValid.checkMaxLenght(passwords, 20))) {
            attributes.addFlashAttribute("errorpassword", "Mật khẩu không vượt 20 ký tự");
            return "redirect:doi-mat-khau.htm";
        }
        if (!(RegexValid.checkMinLenght(passwords, 6))) {
            attributes.addFlashAttribute("errorpassword", "Mật khẩu ít nhất 6 ký tự");
            return "redirect:doi-mat-khau.htm";
        }
        if (!passwords.equals(rePassword)) {
            attributes.addFlashAttribute("errorRepassword", "Mật khẩu nhập lại không giống nhau");
            return "redirect:doi-mat-khau.htm";
        }

        boolean check = customerDAO.changePassword(customers.getCustomerId(), passwords);
        if (check) {
            attributes.addFlashAttribute("success", "Đặt mật khẩu thành công");
            return "redirect:dang-nhap.htm";
        } else {
            attributes.addFlashAttribute("success", "Đặt mật khẩu thành công");
            return "redirect:doi-mat-khau.htm";
        }

    }

}
