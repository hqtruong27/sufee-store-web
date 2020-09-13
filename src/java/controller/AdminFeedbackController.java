package controller;

import dao.AdminDAO;
import dao.FeedbackDAO;
import dao.OrdersDAO;
import entity.Admins;
import entity.FeedbackCatalogs;
import entity.Feedbacks;
import entity.Orders;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.SufeeHibernateUtil;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping(value = "admin")
public class AdminFeedbackController {

    private final FeedbackDAO dao = new FeedbackDAO();
    private final AdminDAO adminDAO = new AdminDAO();
    private final OrdersDAO ordersDAO = new OrdersDAO();

    @RequestMapping(value = "feedback-catalog", method = RequestMethod.GET)
    public String getAllFeedbackCatalogs(HttpSession session, Model model, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục");
            return "redirect:login.htm";
        }
        List<Feedbacks> listFeedback = dao.notifyFeedback();
        int countNotifyFeedback = dao.countNotifyFeedback();
        if (countNotifyFeedback >= 0) {
            model.addAttribute("countNotifyFeedback", countNotifyFeedback);
        }
        List<Orders> listOrder = ordersDAO.notifyOrder();
        int countNotifyOrder = ordersDAO.countNotifyOrder();
        if (countNotifyOrder >= 0) {
            model.addAttribute("countNotifyOrder", countNotifyOrder);
        }
        model.addAttribute("listOrder", listOrder);
        List<FeedbackCatalogs> listFeebackCatalog = dao.getALlFeedbackCataLogAdmin();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listFeebackCatalog", listFeebackCatalog);
        return "admin/feedback-catalog";
    }

    @RequestMapping(value = "feedback-catalog", method = RequestMethod.POST)
    public String createFeedbackCatalogs(HttpSession session, FeedbackCatalogs feedbackCatalogs, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            return "redirect:admin/login";
        }

        if (dao.checkFeedbackCatalogExists(feedbackCatalogs.getFeedbackCatalogName())) {
            attributes.addFlashAttribute("error", "Danh mục tin tức này đã có !");
            return "redirect:feedback-catalog.htm";
        }

        boolean bl = dao.createFeedbackCatalogas(feedbackCatalogs);
        if (bl) {
            attributes.addFlashAttribute("success", "Thêm mới thành công !!");
            return "redirect:feedback-catalog.htm";
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "redirect:feedback-catalog.htm";
        }
    }

    @RequestMapping(value = "delete-feedback-catalog", method = RequestMethod.GET)
    public @ResponseBody
    String deleteFeedbackCatalogs(HttpSession session, int feedbackCatalogId) {
        if (session.getAttribute("InfoAdmin") == null) {
            return "redirect:admin/login";
        }
        boolean check = dao.deleteFeedbackCatalogs(feedbackCatalogId);
        if (check) {
            return "success";
        }
        return "error";
    }

    @RequestMapping(value = "feedback", method = RequestMethod.GET)
    public String getAllFeedbacks(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục");
            return "redirect:login.htm";
        }
        List<Feedbacks> listFeedback = dao.notifyFeedback();
        int countNotifyFeedback = dao.countNotifyFeedback();
        if (countNotifyFeedback >= 0) {
            model.addAttribute("countNotifyFeedback", countNotifyFeedback);
        }
        List<Orders> listOrder = ordersDAO.notifyOrder();
        int countNotifyOrder = ordersDAO.countNotifyOrder();
        if (countNotifyOrder >= 0) {
            model.addAttribute("countNotifyOrder", countNotifyOrder);
        }
        model.addAttribute("listOrder", listOrder);
        List<Feedbacks> listFeedbacks = dao.getAllFeedback();
        List<Admins> listAdmin = adminDAO.getAllAdmin();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listAdmin", listAdmin);
        model.addAttribute("listFeedbacks", listFeedbacks);
        return "admin/feedback-list";
    }

    @RequestMapping(value = "feedback-list-cata", method = RequestMethod.GET)
    public String getAllFeedbacksCata(HttpSession session, Model model, RedirectAttributes attributes, int feedbackCatalogId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục");
            return "redirect:login.htm";
        }
        List<Feedbacks> listFeedback = dao.notifyFeedback();
        int countNotifyFeedback = dao.countNotifyFeedback();
        if (countNotifyFeedback >= 0) {
            model.addAttribute("countNotifyFeedback", countNotifyFeedback);
        }
        List<Orders> listOrder = ordersDAO.notifyOrder();
        int countNotifyOrder = ordersDAO.countNotifyOrder();
        if (countNotifyOrder >= 0) {
            model.addAttribute("countNotifyOrder", countNotifyOrder);
        }
        model.addAttribute("listOrder", listOrder);
        Feedbacks feedback = dao.getFeedbackHasFeedbackCatalogId(feedbackCatalogId);
        List<FeedbackCatalogs> listFeebackCatalog = dao.getAllFeedbackCataLog();
        List<Feedbacks> feedbacks = dao.getAllFeedbackHasFeedbackCatalogId(feedbackCatalogId);
        List<Admins> listAdmin = adminDAO.getAllAdmin();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listAdmin", listAdmin);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("feedback", feedback);
        model.addAttribute("listFeebackCatalog", listFeebackCatalog);
        return "admin/feedback-list_ctlId";
    }

    @RequestMapping(value = "feedback-detail", method = RequestMethod.GET)
    public String detailFeedbacks(HttpSession httpsession, Model model, int feedbackId, RedirectAttributes attributes) {
        if (httpsession.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục");
            return "redirect:login.htm";
        }
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean bl = false;
        try {
            Feedbacks feedbacks = dao.getFeedbackId(feedbackId);
            if (feedbacks == null) {
                attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
                return "redirect:feedback.htm";
            }
            feedbacks.setFeedbackStatus(1);
            switch (feedbacks.getFeedbackStatus()) {
                case 1:
                    bl = true;
                    break;
                default:
                    bl = false;
                    break;
            }
            if (bl) {
                List<Feedbacks> listFeedback = dao.notifyFeedback();
                int countNotifyFeedback = dao.countNotifyFeedback();
                if (countNotifyFeedback >= 0) {
                    model.addAttribute("countNotifyFeedback", countNotifyFeedback);
                }
                List<Orders> listOrder = ordersDAO.notifyOrder();
                int countNotifyOrder = ordersDAO.countNotifyOrder();
                if (countNotifyOrder >= 0) {
                    model.addAttribute("countNotifyOrder", countNotifyOrder);
                }
                model.addAttribute("listOrder", listOrder);
                session.update(feedbacks);
                session.getTransaction().commit();
                List<FeedbackCatalogs> listFeebackCatalog = dao.getAllFeedbackCataLog();
                model.addAttribute("listFeedback", listFeedback);
                model.addAttribute("feedbacks", feedbacks);
                model.addAttribute("listFeebackCatalog", listFeebackCatalog);
                return "admin/feedback-detail";
            } else {
                return "redirect:feedback.htm";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return "redirect:feedback.htm";
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "delete-feedback", method = RequestMethod.GET)
    public @ResponseBody
    String deleteFeedback(HttpSession session1, RedirectAttributes attributes, int feedbackId) {
        if (session1.getAttribute("InfoAdmin") == null) {
            return "redirect:admin/login";
        }
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean bl = false;
        try {
            Feedbacks feedbacks = dao.getFeedbackId(feedbackId);
            if (feedbacks == null) {
                attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
                return "error";
            }
            feedbacks.setFeedbackStatus(-1);
            switch (feedbacks.getFeedbackStatus()) {
                case -1:
                    bl = true;
                    break;
                default:
                    bl = false;
                    break;
            }
            if (bl) {
                session.update(feedbacks);
                session.getTransaction().commit();
                return "success";
            } else {
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

    @RequestMapping(value = "change-statusfeedbackcatalog", method = RequestMethod.GET)
    public @ResponseBody
    String changeStatusFeedbackCatalog(HttpSession httpsession, RedirectAttributes attributes, int feedbackCatalogId) {
        if (httpsession.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục");
            return "redirect:login.htm";
        }
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean bl = false;
        try {
            FeedbackCatalogs feedbackCatalogs = dao.getFeedbackCatalogId(feedbackCatalogId);
            if (feedbackCatalogs == null) {
                attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
                return "error";
            }
            switch (feedbackCatalogs.getFeedbackCatalogStatus()) {
                case 0:
                    feedbackCatalogs.setFeedbackCatalogStatus(1);
                    session.update(feedbackCatalogs);
                    session.getTransaction().commit();
                    return "1";
                case 1:
                    feedbackCatalogs.setFeedbackCatalogStatus(0);
                    session.update(feedbackCatalogs);
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
