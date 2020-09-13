package controller;

import Validate.RegexValid;
import dao.BannerDAO;
import dao.ContactDAO;
import dao.FaqDAO;
import dao.FeedbackDAO;
import dao.IconDAO;
import dao.IntroductionDAO;
import dao.LogoDAO;
import dao.OrdersDAO;
import entity.Banners;
import entity.Contacts;
import entity.Faqs;
import entity.Feedbacks;
import entity.Icons;
import entity.Introductions;
import entity.Logo;
import entity.Orders;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Hoang Truong
 */
@Controller
@RequestMapping(value = "admin")
public class AdminAdvancedContoller {

    private final BannerDAO dao = new BannerDAO();
    private final FeedbackDAO feedbacksDAO = new FeedbackDAO();
    private final OrdersDAO ordersDAO = new OrdersDAO();
    private final ContactDAO contactDAO = new ContactDAO();
    private final FaqDAO faqDAO = new FaqDAO();
    private final LogoDAO logoDAO = new LogoDAO();
    private final IntroductionDAO introDAO = new IntroductionDAO();
    private final IconDAO iconDAO = new IconDAO();

    @RequestMapping(value = "banner", method = RequestMethod.GET)
    public String getAllBanner(HttpSession session, RedirectAttributes attributes, Model model) {
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
        model.addAttribute("listFeedback", listFeedback);
        List<Banners> listBanner = dao.getAllBanner();
        model.addAttribute("listBanner", listBanner);
        return "admin/banner-list";
    }

    @RequestMapping(value = "create-banner", method = RequestMethod.GET)
    public String createBanner(HttpSession session, RedirectAttributes attributes, Model model) {
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
        model.addAttribute("listFeedback", listFeedback);
        Banners banner = new Banners();
        model.addAttribute("banner", banner);
        return "admin/banner-insert";
    }

    @RequestMapping(value = "create-banner", method = RequestMethod.POST)
    public String createBanner(HttpSession session, RedirectAttributes attributes, Banners banner) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "redirect:login.htm";
        }
        boolean check = dao.createBanner(banner);
        if (check) {
            attributes.addFlashAttribute("success", "Thêm mới thành công!");
            return "redirect:banner.htm";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:create-banner.htm";
        }
    }

    @RequestMapping(value = "update-banner", method = RequestMethod.GET)
    public String updateBanner(HttpSession session, RedirectAttributes attributes, Model model, int bannerId) {
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
        model.addAttribute("listFeedback", listFeedback);
        Banners banner = dao.getBannerById(bannerId);
        model.addAttribute("banner", banner);
        return "admin/banner-update";
    }

    @RequestMapping(value = "update-banner", method = RequestMethod.POST)
    public String updateBanner(HttpSession session, RedirectAttributes attributes, Banners banner, int bannerId, int bannerPiority) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "redirect:login.htm";
        }
        boolean check = dao.updateBanner(banner, bannerPiority);
        if (check) {
            attributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:banner.htm";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:update-banner.htm?bannerId=" + bannerId;
        }
    }

    @RequestMapping(value = "delete-banner")
    public @ResponseBody
    String deleteBanner(HttpSession session, RedirectAttributes attributes, Banners banner) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "error";
        }
        boolean check = dao.deleteBanner(banner.getBannerId());
        if (banner.getBannerId() < 0) {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra");
            return "error";
        }
        if (check) {
            attributes.addFlashAttribute("success", "Xoá banner thành công!");
            return "success";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "error";
        }
    }
    
    // Contact
    @RequestMapping(value = "contact", method = RequestMethod.GET)
    public String getAllContact(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        List<Contacts> listContact = contactDAO.getAllContact();
        model.addAttribute("listContact", listContact);
        return "admin/contact-list";
    }

    @RequestMapping(value = "create-contact", method = RequestMethod.GET)
    public String createContact(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        Contacts contact = new Contacts();
        model.addAttribute("contact", contact);
        return "admin/contact-insert";
    }

    @RequestMapping(value = "create-contact", method = RequestMethod.POST)
    public String createContact(HttpSession session, RedirectAttributes attributes, Contacts contact) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(contact.getContactAddress())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập địa chỉ !");
            return "redirect:create-contact.htm";
        }
        if (RegexValid.isEmpty(contact.getContactEmail())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập email !");
            return "redirect:create-contact.htm";
        }
        if (RegexValid.isEmpty(contact.getContactHotline())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập điện thoại liên hệ !");
            return "redirect:create-contact.htm";
        }
        if (!(RegexValid.isMail(contact.getContactEmail()))) {
            attributes.addFlashAttribute("error", "Email không đúng định dạng !");
            return "redirect:create-contact.htm";
        }
        if (!(RegexValid.isPhoneNumber(contact.getContactHotline()))) {
            attributes.addFlashAttribute("error", "Số điện thoại không đúng định dạng !");
            return "redirect:create-contact.htm";
        }
        boolean check = contactDAO.createContact(contact);
        if (check) {
            attributes.addFlashAttribute("success", "Thêm mới thành công!");
            return "redirect:contact.htm";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:create-contact.htm";
        }
    }

    @RequestMapping(value = "update-contact", method = RequestMethod.GET)
    public String updateContact(HttpSession session, RedirectAttributes attributes, Model model, int contactId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "redirect:login.htm";
        }
        Contacts contact = contactDAO.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "admin/contact-update";
    }

    @RequestMapping(value = "update-contact", method = RequestMethod.POST)
    public String updateContact(HttpSession session, RedirectAttributes attributes, Contacts contact, int contactId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(contact.getContactAddress())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập địa chỉ !");
            return "redirect:update-contact.htm?contactId="+contactId;
        }
        if (RegexValid.isEmpty(contact.getContactEmail())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập email !");
            return "redirect:update-contact.htm?contactId="+contactId;
        }
        if (RegexValid.isEmpty(contact.getContactHotline())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập điện thoại liên hệ !");
            return "redirect:update-contact.htm?contactId="+contactId;
        }
        if (!(RegexValid.isMail(contact.getContactEmail()))) {
            attributes.addFlashAttribute("error", "Email không đúng định dạng !");
            return "redirect:update-contact.htm?contactId="+contactId;
        }
        if (!(RegexValid.isPhoneNumber(contact.getContactHotline()))) {
            attributes.addFlashAttribute("error", "Số điện thoại không đúng định dạng !");
            return "redirect:update-contact.htm?contactId="+contactId;
        }
        boolean check = contactDAO.updateContact(contact);
        if (check) {
            attributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:contact.htm";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:update-contact.htm?contactId=" + contactId;
        }
    }

    @RequestMapping(value = "delete-contact")
    public @ResponseBody
    String deleteContact(HttpSession session, RedirectAttributes attributes, Contacts contact) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "error";
        }
        boolean check = contactDAO.deleteContact(contact.getContactId());
        if (contact.getContactId() < 0) {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra");
            return "error";
        }
        if (check) {
            attributes.addFlashAttribute("success", "Xoá contact thành công!");
            return "success";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "error";
        }
    }

    // FAQs
    @RequestMapping(value = "faqs", method = RequestMethod.GET)
    public String getAllFaqs(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        List<Faqs> listFaq = faqDAO.getAllFaqs();
        model.addAttribute("listFaq", listFaq);
        return "admin/faqs-list";
    }

    @RequestMapping(value = "create-faqs", method = RequestMethod.GET)
    public String createFaqs(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        Faqs faq = new Faqs();
        model.addAttribute("faq", faq);
        return "admin/faqs-insert";
    }

    @RequestMapping(value = "create-faqs", method = RequestMethod.POST)
    public String createFaqs(HttpSession session, RedirectAttributes attributes, Faqs faq) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(faq.getFastQuestion())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập câu hỏi !");
            return "redirect:create-faqs.htm";
        }
        if (RegexValid.isEmpty(faq.getFastAnswer())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập câu trả lời !");
            return "redirect:create-faqs.htm";
        }
        boolean check = faqDAO.createFaq(faq);
        if (check) {
            attributes.addFlashAttribute("success", "Thêm mới thành công!");
            return "redirect:faqs.htm";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:create-faqs.htm";
        }
    }

    @RequestMapping(value = "update-faqs", method = RequestMethod.GET)
    public String updateFaqs(HttpSession session, RedirectAttributes attributes, Model model, int faqid) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        Faqs faq = faqDAO.getFaqById(faqid);
        model.addAttribute("faq", faq);
        return "admin/faqs-update";
    }

    @RequestMapping(value = "update-faqs", method = RequestMethod.POST)
    public String updateFaqs(HttpSession session, RedirectAttributes attributes, Faqs faq, int faqid) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(faq.getFastQuestion())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập câu hỏi !");
            return "redirect:update-faqs.htm?faqid="+faqid;
        }
        if (RegexValid.isEmpty(faq.getFastAnswer())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập câu trả lời !");
            return "redirect:update-faqs.htm?faqid="+faqid;
        }
        boolean check = faqDAO.updateFaq(faq);
        if (check) {
            attributes.addFlashAttribute("success", "Chỉnh sửa thành công!");
            return "redirect:faqs.htm";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:update-faqs.htm?faqid=" + faqid;
        }
    }

    @RequestMapping(value = "delete-faqs")
    public @ResponseBody
    String deleteFaqs(HttpSession session, RedirectAttributes attributes, Faqs faq) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        boolean check = faqDAO.deleteFaq(faq.getFaqid());
        if (check) {
            attributes.addFlashAttribute("success", "Xoá FAQs thành công !!");
            return "success";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra !!");
            return "error";
        }
    }

    //Logo
    @RequestMapping(value = "logo")
    public String getAllLogo(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        List<Logo> listLogo = logoDAO.getAllLogo();
        model.addAttribute("listLogo", listLogo);
        return "admin/logo-list";
    }

    @RequestMapping(value = "create-logo", method = RequestMethod.GET)
    public String createLogo(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        Logo logo = new Logo();
        model.addAttribute("logo", logo);
        return "admin/logo-insert";
    }

    @RequestMapping(value = "create-logo", method = RequestMethod.POST)
    public String createLogo(HttpSession session, RedirectAttributes attributes, Logo logo) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(logo.getLogoImage())) {
            attributes.addFlashAttribute("error", "Vui lòng tải ảnh lên! ");
            return "redirect:create-logo.htm";
        }
        boolean check = logoDAO.createLogo(logo);
        if (check) {
            attributes.addFlashAttribute("success", "Thêm mới logo thành công!");
            return "redirect:logo.htm";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:create-logo.htm";
        }
    }

    @RequestMapping(value = "update-logo", method = RequestMethod.GET)
    public String updateLogo(HttpSession session, RedirectAttributes attributes, Model model, int logoId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "redirect:login.htm";
        }
        Logo logo = logoDAO.getLogoById(logoId);
        model.addAttribute("logo", logo);
        return "admin/logo-update";
    }

    @RequestMapping(value = "update-logo", method = RequestMethod.POST)
    public String updateLogo(HttpSession session, RedirectAttributes attributes, Logo logo, int logoId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(logo.getLogoImage())) {
            attributes.addFlashAttribute("error", "Vui lòng tải ảnh lên! ");
            return "redirect:update-logo.htm?logoId="+logoId;
        }
        boolean check = logoDAO.updateLogo(logo);
        if (check) {
            attributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:logo.htm";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:update-logo.htm?logoId=" + logoId;
        }
    }

    @RequestMapping(value = "delete-logo")
    public @ResponseBody
    String deleteLogo(HttpSession session, RedirectAttributes attributes, Logo logo, int logoId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        boolean check = logoDAO.deleteLogo(logoId);
        if (logo.getLogoId() < 0) {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "error";
        }
        if (check) {
            attributes.addFlashAttribute("success", "Xóa logo thành công!");
            return "success";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "error";
        }
    }

    //Introductions
    @RequestMapping(value = "introductions")
    public String getAllIntro(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        List<Introductions> listIntro = introDAO.getAllIntro();
        model.addAttribute("listIntro", listIntro);
        return "admin/introduction-list";
    }

    @RequestMapping(value = "create-intro", method = RequestMethod.GET)
    public String createIntro(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        Introductions intro = new Introductions();
        model.addAttribute("intro", intro);
        return "admin/introduction-insert";
    }

    @RequestMapping(value = "create-intro", method = RequestMethod.POST)
    public String createIntro(HttpSession session, RedirectAttributes attributes, Introductions intro) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(intro.getIntroductionContent())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập nội dung giới thiệu! ");
            return "redirect:create-intro.htm";
        }
        boolean check = introDAO.createIntro(intro);
        if (check) {
            attributes.addFlashAttribute("success", "Thêm mới thành công!");
            return "redirect:introductions.htm";
        }else{
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:create-intro.htm";
        }
    }
    
    @RequestMapping(value = "update-intro", method = RequestMethod.GET)
    public String updateIntro(HttpSession session, RedirectAttributes attributes, Model model, int introductionId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        Introductions intro = introDAO.getIntroById(introductionId);
        model.addAttribute("intro", intro);
        return "admin/introduction-update";
    }

    @RequestMapping(value = "update-intro", method = RequestMethod.POST)
    public String updateIntro(HttpSession session, RedirectAttributes attributes, Introductions intro, int introductionId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(intro.getIntroductionContent())) {
            attributes.addFlashAttribute("error", "Vui lòng nhập nội dung giới thiệu! ");
            return "redirect:update-intro.htm?introductionId="+introductionId;
        }
        boolean check = introDAO.updateIntro(intro);
        if (check) {
            attributes.addFlashAttribute("success", "Sửa giới thiệu thành công!");
            return "redirect:introductions.htm";
        }else{
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:create-intro.htm?introductionId="+introductionId;
        }
    }
    
    @RequestMapping(value = "delete-intro")
    public @ResponseBody
    String deleteIntro(HttpSession session, RedirectAttributes attributes, Introductions intro, int introductionId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        boolean check = introDAO.deleteIntro(introductionId);
        if (intro.getIntroductionId() < 0) {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "error";
        }
        if (check) {
            attributes.addFlashAttribute("success", "Xóa intro thành công!");
            return "success";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "error";
        }
    }
    
    // Icons
    @RequestMapping(value = "icons")
    public String getAllIcons(HttpSession session, RedirectAttributes attributes, Model model){
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        List<Icons> listIcon = iconDAO.getAllIcons();
        model.addAttribute("listIcon", listIcon);
        return "admin/icon-list";
    }
    
    @RequestMapping(value = "create-icon", method = RequestMethod.GET)
    public String createIcon(HttpSession session, RedirectAttributes attributes, Model model){
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        Icons icon = new Icons();
        model.addAttribute("icon", icon);
        return "admin/icon-insert";
    }
    
    @RequestMapping(value = "create-icon", method = RequestMethod.POST)
    public String createIcon(HttpSession session, RedirectAttributes attributes, Icons icon){
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(icon.getIconImage())) {
            attributes.addFlashAttribute("error", "Vui lòng tải ảnh lên! ");
            return "redirect:create-icon.htm";
        }
        boolean check = iconDAO.createIcon(icon);
        if (check) {
            attributes.addFlashAttribute("success", "Thêm mới thành công!");
            return "redirect:icons.htm";
        }else{
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:create-icon.htm";
        }
    }
    
    @RequestMapping(value = "update-icon", method = RequestMethod.GET)
    public String updateIcon(HttpSession session, RedirectAttributes attributes, Model model, int iconId){
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        Icons icon = iconDAO.getIconById(iconId);
        model.addAttribute("icon", icon);
        return "admin/icon-update";
    }
    
    public String updateIcon(HttpSession session, RedirectAttributes attributes, Icons icon, int iconId){
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        if (RegexValid.isEmpty(icon.getIconImage())) {
            attributes.addFlashAttribute("error", "Vui lòng tải ảnh lên! ");
            return "redirect:update-icon.htm?iconId="+iconId;
        }
        boolean check = iconDAO.updateIcon(icon);
        if (check) {
            attributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:icons.htm";
        }else{
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "redirect:create-icon.htm?iconId="+iconId;
        }
    }
    
    public @ResponseBody String deleteIcon(HttpSession session, RedirectAttributes attributes,Icons icon, int iconId){
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục!");
            return "redirect:login.htm";
        }
        boolean check = iconDAO.deleteIcon(iconId);
        if (icon.getIconId() < 0) {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "error";
        }
        if (check) {
            attributes.addFlashAttribute("success", "Xóa icon thành công!");
            return "success";
        } else {
            attributes.addFlashAttribute("error", "Có lỗi xảy ra!");
            return "error";
        }
    }
}
