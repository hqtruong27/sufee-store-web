package controller;

import Validate.RegexValid;
import common.ThongKe;
import dao.AdminDAO;
import dao.BrandDAO;
import dao.CustomerDAO;
import dao.FeedbackDAO;
import dao.OrdersDAO;
import dao.ProductDAO;
import entity.Admins;
import entity.Customers;
import entity.Feedbacks;
import entity.Orders;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.SufeeHibernateUtil;

/**
 *
 * @author Hoang Truong
 */
@EnableWebMvc
@Controller
@RequestMapping(value = "admin")
public class AdminController {

    private final AdminDAO dao;
    private final CustomerDAO customerDAO;
    private final FeedbackDAO feedbacksDAO;
    private final ProductDAO productDAO;
    private final OrdersDAO ordersDAO;
    private final BrandDAO brandDAO;

    public AdminController() {
        dao = new AdminDAO();
        customerDAO = new CustomerDAO();
        feedbacksDAO = new FeedbackDAO();
        productDAO = new ProductDAO();
        ordersDAO = new OrdersDAO();
        brandDAO = new BrandDAO();
    }

    //VIEW: admin/home
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }
        List<Feedbacks> listFeedback = feedbacksDAO.notifyFeedback();
        int countNotifyFeedback = feedbacksDAO.countNotifyFeedback();
        if (countNotifyFeedback >= 0) {
            model.addAttribute("countNotifyFeedback", countNotifyFeedback);
        }
        int countProduct = productDAO.countProduct();
        if (countProduct >= 0) {
            model.addAttribute("countProduct", countProduct);
        }
        int countOrder = ordersDAO.countOrder();
        if (countOrder >= 0) {
            model.addAttribute("countOrder", countOrder);
        }
        int countAdmin = dao.countAdmin();
        if (countAdmin >= 0) {
            model.addAttribute("countAdmin", countAdmin);
        }
        int countCustomer = customerDAO.countCustomer();
        if (countCustomer >= 0) {
            model.addAttribute("countCustomer", countCustomer);
        }
        int countBrand = brandDAO.countBrand();
        if (countBrand >= 0) {
            model.addAttribute("countBrand", countBrand);
        }
        double totalOrder = ordersDAO.totalOrder();
        if (totalOrder >= 0) {
            model.addAttribute("totalOrder", totalOrder);
        }
        List<Orders> listOrder = ordersDAO.notifyOrder();
        int countNotifyOrder = ordersDAO.countNotifyOrder();
        if (countNotifyOrder >= 0) {
            model.addAttribute("countNotifyOrder", countNotifyOrder);
        }

        // thong ke don hang
        List<Orders> listOrders = ordersDAO.getAllOrders();
        List<ThongKe> listThongKe = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            ThongKe thongKe = new ThongKe();
            thongKe.setThangId(i);
            thongKe.setTenThang("Tháng " + i);
            int dangChoDuyet = 0;
            int daGiaoHang = 0;
            int daHuyDon = 0;
            Calendar c = Calendar.getInstance();
            if (listOrders != null) {
                for (Orders items : listOrders) {
                    c.setTime(items.getCreateDate());
                    if ((c.get(Calendar.MONTH) + 1) == i) {
                        if (items.getOrderStatus() == 0) {
                            dangChoDuyet++;
                        } else if (items.getOrderStatus() == 1) {
                            daGiaoHang++;
                        } else if (items.getOrderStatus() == -1) {
                            daHuyDon++;
                        }
                    }
                }
            }
            thongKe.setDangChoDuyet(dangChoDuyet);
            thongKe.setDaHuyDon(daHuyDon);
            thongKe.setDaGiaoHang(daGiaoHang);
            listThongKe.add(thongKe);
        }

        model.addAttribute("listThongKe", listThongKe);

        model.addAttribute("listOrder", listOrder);

        model.addAttribute("listFeedback", listFeedback);
        return "admin/dashboard";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        if (session.getAttribute("InfoAdmin") != null) {
            return "redirect:index.htm";
        }
        return "admin/login";
    }

    //POST: admin/login
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Admins admin, HttpSession session, RedirectAttributes attributes) {
        if (admin.getEmail().isEmpty()) {
            attributes.addFlashAttribute("erroremail", "Vui lòng nhập đầy đủ các trường !!");
            return "redirect:login.htm";
        } else if (admin.getPasswords().isEmpty()) {
            attributes.addFlashAttribute("errorpwd", "Vui lòng nhập đầy đủ các trường !!");
            return "redirect:login.htm";
        } else if (!(RegexValid.isMail(admin.getEmail()))) {
            attributes.addFlashAttribute("erroremail", "Email không đúng định dạng !!");
            return "redirect:login.htm";
        } else if (!(RegexValid.checkMinLenght(admin.getPasswords(), 6))) {
            attributes.addFlashAttribute("errorpwd", "Mật khẩu phải có ít nhất 6 ký tự !!");
            return "redirect:login.htm";
        }
        Admins result = dao.login(admin.getEmail(), admin.getPasswords());
        if (result != null) {
            if (result.getStatus() == 0) {
                attributes.addFlashAttribute("error", "Tài khoản của bạn đã bị khoá !!");
                return "redirect:login.htm";
            }
            if (result.getStatus() == -1) {
                attributes.addFlashAttribute("error", "Tài khoản không tồn tại !!");
                return "redirect:login.htm";
            }
            session.setAttribute("InfoAdmin", result);
            attributes.addFlashAttribute("success", result.getFullname());
            return "redirect:index.htm";
        } else {
            attributes.addFlashAttribute("error", "Email hoặc tài khoản không chính xác !!");
            return "redirect:login.htm";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        if (session.getAttribute("InfoAdmin") != null) {
            session.removeAttribute("InfoAdmin");
            return "redirect:login.htm";
        }
        return "admin/login";
    }

    @RequestMapping(value = "list-admin", method = RequestMethod.GET)
    public String getAllAdmin(HttpSession session, Model model, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }
        List<Feedbacks> listFeedback = feedbacksDAO.notifyFeedback();
        int countNotifyFeedback = feedbacksDAO.countNotifyFeedback();
        if (countNotifyFeedback >= 0) {
            model.addAttribute("countNotifyFeedback", countNotifyFeedback);
        }
        List<Admins> listAdmin = dao.getAllAdmin();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listAdmin", listAdmin);
        return "admin/admin-list";
    }

    //admin/ detail
    @RequestMapping(value = "detail-admin")
    public String detailAdmin(HttpSession session, Model model, RedirectAttributes attributes, int adminId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
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
        model.addAttribute("listFeedback", listFeedback);
        Admins admin = dao.getAdminById(adminId);
        model.addAttribute("admin", admin);
        return "admin/admin-detail";
    }

    //admin/ info
    @RequestMapping(value = "info-admin", method = RequestMethod.GET)
    public String infoAdmin(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
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
        Admins admins = (Admins) session.getAttribute("InfoAdmin");
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("admins", admins);
        return "admin/admin-info";
    }

    //POST: Info Admin
    @RequestMapping(value = "info-admin", consumes = {"multipart/form-data"}, method = RequestMethod.POST)
    public String infoAdmin(@RequestParam(value = "avatar") CommonsMultipartFile avatar, HttpServletRequest request, HttpSession session, RedirectAttributes attributes, String fullname, String email, String birthday, String idCard, int gender, String adminAddress, String phone) {
        Admins admins = (Admins) session.getAttribute("InfoAdmin");
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }
        String file = avatar.getOriginalFilename();
        String extension = FilenameUtils.getExtension(file);
        String oldFileName;

        if (!"".equals(file)) {
            if (!RegexValid.isValidImage(extension)) {
                attributes.addFlashAttribute("error", "Định dạng tệp tin không được hỗ trợ!");
                return "redirect:info-admin.htm";
            }

            String newFileName = RegexValid.generateNameTypeB("avatar");
            newFileName += "." + extension;

            String path = request.getServletContext().getRealPath("view/admin/uploads/avatar");
            path = path.substring(0, path.indexOf("\\build"));
            path = path + "\\web\\view\\admin\\uploads\\avatar";
            File fileDir = new File(path);
            oldFileName = path + "\\" + admins.getAvatar();

            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            try {
                File file1 = new File(fileDir + File.separator + newFileName);
                avatar.transferTo(new File(fileDir + File.separator + newFileName));

                boolean check = dao.changeAdminInfo(admins.getAdminId(), fullname, email, birthday, idCard, gender, adminAddress, phone, file1.getName());

                if (!check) {
                    attributes.addFlashAttribute("error", "Thay đổi avatar thất bại!");
                    return "redirect:info-admin.htm";
                }
            } catch (IOException | IllegalStateException e) {
                attributes.addFlashAttribute("error", "Tải file thất bại! \n" + e.getMessage());
                return "redirect:info-admin.htm";
            }
        } else {
            boolean check = dao.changeAdminInfo(admins.getAdminId(), fullname, email, birthday, idCard, gender, adminAddress, phone, admins.getAvatar());

            if (!check) {
                attributes.addFlashAttribute("error", "Thay đổi avatar thất bại!");
                return "redirect:info-admin.htm";
            } else {
                attributes.addFlashAttribute("success", "Thay đổi avatar thành công! Vui lòng đăng nhập lại!");
                session.removeAttribute("InfoAdmin");
                return "redirect:login.htm";
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
        attributes.addFlashAttribute("success", "Thay đổi avatar thành công! Vui lòng đăng nhập lại!");
        session.removeAttribute("InfoAdmin");
        return "redirect:login.htm";
    }

    //GET: Create Admin
    @RequestMapping(value = "create-admin", method = RequestMethod.GET)
    public String createAdmin(HttpSession session, Model model, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
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
        Admins admin = new Admins();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("admin", admin);
        return "admin/admin-insert";
    }

    //POST: Create Admin
    @RequestMapping(value = "create-admin", method = RequestMethod.POST)
    public String createAdmin(HttpSession session, @Valid @ModelAttribute(value = "admin") Admins admin, BindingResult result, RedirectAttributes attributes, String fullname, String email, String passwords, String birthday, int gender, String idCard, int status, String adminAddress, String phone) {

        if (RegexValid.isEmpty(admin.getFullname())) {
            attributes.addFlashAttribute("error", "Chưa nhập tên nhân viên !");
            return "redirect:create-admin.htm";
        }
        if (RegexValid.isEmpty(admin.getEmail())) {
            attributes.addFlashAttribute("error", "Chưa nhập email !");
            return "redirect:create-admin.htm";
        }

        if (RegexValid.isEmpty(admin.getPasswords())) {
            attributes.addFlashAttribute("error", "Chưa nhập mật khẩu !");
            return "redirect:create-register.htm";
        }
        if (RegexValid.isEmpty(admin.getPhone())) {
            attributes.addFlashAttribute("error", "Chưa nhập số điện thoại !");
            return "redirect:create-admin.htm";
        }
        if (RegexValid.isEmpty(admin.getAdminAddress())) {
            attributes.addFlashAttribute("error", "Chưa nhập địa chỉ !");
            return "redirect:create-admin.htm";
        }
//        if (RegexValid.isEmpty(rePassword)) {
//            attributes.addFlashAttribute("error", "Chưa nhập lại mật khẩu !");
//            return "redirect:create-register.htm";
//        }
//
//        if (!(c.getPasswords().equals(rePassword))) {
//            attributes.addFlashAttribute("error", "Mật khẩu nhập lại không đúng !");
//            return "redirect:create-register.htm";
//        }

        if (!(RegexValid.checkMaxLenght(admin.getFullname(), 250))) {
            attributes.addFlashAttribute("error", "Họ tên không vượt quá 250 ký tự !");
            return "redirect:create-admin.htm";
        }

        if (!(RegexValid.checkMaxLenght(admin.getEmail(), 250))) {
            attributes.addFlashAttribute("error", "Email không vượt quá 250 ký tự !");
            return "redirect:create-admin.htm";
        }

        if (!(RegexValid.checkMinLenght(admin.getPasswords(), 6))) {
            attributes.addFlashAttribute("error", "Mật khẩu ít nhất 6 ký tự !");
            return "redirect:create-admin.htm";
        }
        if (!(RegexValid.isMail(admin.getEmail()))) {
            attributes.addFlashAttribute("error", "Email không đúng định dạng !");
            return "redirect:create-admin.htm";
        }
        if (!(RegexValid.isPhoneNumber(admin.getPhone()))) {
            attributes.addFlashAttribute("error", "Số điện thoại không đúng định dạng !");
            return "redirect:create-admin.htm";
        }

        if (dao.checkEmailExists(admin.getEmail())) {
            attributes.addFlashAttribute("error", "Email này đã được sử dụng !");
            return "redirect:create-admin.htm";
        }
        if (dao.checkPhoneExists(admin.getPhone())) {
            attributes.addFlashAttribute("error", "Số điện này đã được sử dụng !");
            return "redirect:create-admin.htm";
        }

        Date createAdminBirthday = RegexValid.convertStringToDate(birthday, "yyyy-MM-dd");
        admin.setFullname(fullname);
        admin.setEmail(email);
        admin.setPasswords(passwords);
        admin.setBirthday(createAdminBirthday);
        admin.setGender(gender);
        admin.setIdCard(idCard);
        admin.setStatus(status);
        admin.setAdminAddress(adminAddress);
        admin.setPhone(phone);
        boolean check = dao.createAdmins(admin);
        if (check) {
            attributes.addFlashAttribute("success", "Thêm mới thành công!");
            return "redirect:list-admin.htm";
        } else {
            attributes.addFlashAttribute("error", "Thêm mới thất bại!");
            return "redirect:create-admin.htm";
        }
    }

    //GET: Update Admin
    @RequestMapping(value = "update-admin", method = RequestMethod.GET)
    public String updateAdmin(HttpSession session, Model model, RedirectAttributes attributes, int adminId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
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
        Admins admin = dao.getAdminById(adminId);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("admin", admin);
        return "admin/admin-update";
    }

    //POST: Update Admin
    @RequestMapping(value = "update-admin", method = RequestMethod.POST)
    public String updateAdmin(HttpSession session, Admins admin, RedirectAttributes attributes, int adminId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }

        if (RegexValid.isEmpty(admin.getFullname())) {
            attributes.addFlashAttribute("error", "Chưa nhập tên nhân viên !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }
        if (RegexValid.isEmpty(admin.getEmail())) {
            attributes.addFlashAttribute("error", "Chưa nhập email !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }

        if (RegexValid.isEmpty(admin.getPasswords())) {
            attributes.addFlashAttribute("error", "Chưa nhập mật khẩu !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }
        if (RegexValid.isEmpty(admin.getPhone())) {
            attributes.addFlashAttribute("error", "Chưa nhập số điện thoại !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }
        if (RegexValid.isEmpty(admin.getAdminAddress())) {
            attributes.addFlashAttribute("error", "Chưa nhập địa chỉ !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }

        if (!(RegexValid.checkMaxLenght(admin.getFullname(), 250))) {
            attributes.addFlashAttribute("error", "Họ tên không vượt quá 250 ký tự !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }

        if (!(RegexValid.checkMaxLenght(admin.getEmail(), 250))) {
            attributes.addFlashAttribute("error", "Email không vượt quá 250 ký tự !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }

        if (!(RegexValid.checkMinLenght(admin.getPasswords(), 6))) {
            attributes.addFlashAttribute("error", "Mật khẩu ít nhất 6 ký tự !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }
        if (!(RegexValid.isMail(admin.getEmail()))) {
            attributes.addFlashAttribute("error", "Email không đúng định dạng !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }
        if (!(RegexValid.isPhoneNumber(admin.getPhone()))) {
            attributes.addFlashAttribute("error", "Số điện thoại không đúng định dạng !");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }

        boolean check = dao.updateAdmins(admin);
        if (check) {
            attributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:list-admin.htm";
        } else {
            attributes.addFlashAttribute("error", "Cập nhật thất bại!");
            return "redirect:update-admin.htm?adminId=" + adminId;
        }
    }

    //Delete Admin
    @RequestMapping(value = "delete-admin")
    public @ResponseBody
    String deleteAdmin(HttpSession session, Admins a, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "error";
        }

        boolean check = dao.deleteAdmins(a.getAdminId());
        if (a.getAdminId() < 0) {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "error";
        }
        if (check) {
            attributes.addFlashAttribute("success", "Xoá nhân viên thành công !!");
            return "success";
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "error";
        }
    }

    //Change-admin-password
    @RequestMapping(value = "change-admin-password", method = RequestMethod.GET)
    public String changeAdminPassword(HttpSession session, Model model, Admins admin, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            return "redirect:login.htm";
        }
        List<Feedbacks> listFeedback = feedbacksDAO.notifyFeedback();
        int countNotifyFeedback = feedbacksDAO.countNotifyFeedback();
        if (countNotifyFeedback >= 0) {
            model.addAttribute("countNotifyFeedback", countNotifyFeedback);
        }
        Admins admins = (Admins) session.getAttribute("InfoAdmin");
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("admins", admins);
        return "admin/admin-info";
    }

    @RequestMapping(value = "change-admin-password", method = RequestMethod.POST)
    public String changePassword(HttpSession session, Admins admin, RedirectAttributes attributes, String passwordOld, String passwordNew, String repasswordNew) {
        Admins admins = (Admins) session.getAttribute("InfoAdmin");
        if (RegexValid.isEmpty(passwordOld)) {
            attributes.addFlashAttribute("error", "Mật khẩu cũ không được để trống !!");
            return "redirect:change-admin-password.htm";
        }
        if (RegexValid.isEmpty(passwordNew)) {
            attributes.addFlashAttribute("error", "Mật khẩu mới không được để trống !!");
            return "redirect:change-admin-password.htm";
        }
        if (RegexValid.isEmpty(repasswordNew)) {
            attributes.addFlashAttribute("error", "Mật khẩu nhập lại không được để trống !!");
            return "redirect:change-admin-password.htm";
        }
        if (!passwordOld.equals(admins.getPasswords())) {
            attributes.addFlashAttribute("error", "Mật khẩu cũ không đúng !!");
            return "redirect:change-admin-password.htm";
        }
        if (!passwordNew.equals(repasswordNew)) {
            attributes.addFlashAttribute("error", "Mật khẩu nhập lại không khớp !!");
            return "redirect:change-admin-password.htm";
        }
        if (passwordNew.equals(admins.getPasswords())) {
            attributes.addFlashAttribute("error", "Mật khẩu mới không được giống mật khẩu cũ !!");
            return "redirect:change-admin-password.htm";
        }

        boolean check = dao.changeAdminPassword(admins.getAdminId(), passwordNew);
        if (!check) {
            attributes.addFlashAttribute("error", "Không thể thay đổi mật khẩu !!");
            return "redirect:info-admin.htm";
        } else {
            attributes.addFlashAttribute("success", "Bạn đã thay đổi mật khẩu, Mời đăng nhập lại !!");
            session.removeAttribute("InfoAdmin");
            return "redirect:login.htm";
        }
    }

    /**
     * Customer
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "customers", method = RequestMethod.GET)
    public String customers(HttpSession session, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
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
        List<Customers> customer = customerDAO.getAllCustomer();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listCustomer", customer);
        return "admin/customer-list";
    }

    @RequestMapping(value = "info-customer", method = RequestMethod.GET)
    public String infoCustomer(HttpSession session, Model model, int customerId) {
        if (session.getAttribute("InfoAdmin") == null) {
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
        Customers customer = customerDAO.getCustomerId(customerId);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("customer", customer);
        return "admin/customer-detail";
    }

    @RequestMapping(value = "update-customer", method = RequestMethod.GET)
    public String updateCustomer(HttpSession session, Model model, RedirectAttributes attributes, int customerId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
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
        Customers customer = customerDAO.getCustomerId(customerId);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("customer", customer);
        return "admin/customer-update";
    }

    @RequestMapping(value = "update-customer", method = RequestMethod.POST)
    public String updateCustomer(HttpSession session, int customerId, Customers c, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            return "redirect:login.htm";
        }
        boolean bl = customerDAO.updateCustomer(c);
        if (bl) {
            attributes.addFlashAttribute("success", "Cập nhật thành công !");
            return "redirect:customers.htm";
        } else {

            attributes.addFlashAttribute("success", "Cập nhật thành công !");
            return "update-customer";
        }
    }

    @RequestMapping(value = "change-status", method = RequestMethod.GET)
    public @ResponseBody
    String changeStatus(HttpSession session1, RedirectAttributes attributes, int customerId) {
        if (session1.getAttribute("InfoAdmin") == null) {
            return "redirect:admin/login";
        }
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean bl = false;
        try {
            Customers customers = customerDAO.getCustomerId(customerId);
            if (customers == null) {
                attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
                return "redirect:customers.htm";
            }
            switch (customers.getCustomerStatus()) {
                case 0:
                    customers.setCustomerStatus(1);
                    session.update(customers);
                    session.getTransaction().commit();
                    return "1";
                case 1:
                    customers.setCustomerStatus(0);
                    session.update(customers);
                    session.getTransaction().commit();
                    return "0";
                default:
                    return "error";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return "error";
        } finally {
            session.close();
        }
    }
}
