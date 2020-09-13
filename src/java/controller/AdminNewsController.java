package controller;

import dao.AdminDAO;
import dao.FeedbackDAO;
import dao.NewsDAO;
import dao.OrdersDAO;
import entity.Admins;
import entity.Catalogs;
import entity.Feedbacks;
import entity.News;
import entity.Orders;
import java.util.Calendar;
import java.util.Date;
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
 * @author Hoang Truong
 */
@Controller
@RequestMapping(value = "admin")
public class AdminNewsController {

    private final NewsDAO dao = new NewsDAO();
    private final AdminDAO adminDAO = new AdminDAO();
    private final FeedbackDAO feedbacksDAO = new FeedbackDAO();
    private final OrdersDAO ordersDAO = new OrdersDAO();

    //GET: get all catalog with catalog = 0
    @RequestMapping(value = "catalog-news", method = RequestMethod.GET)
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
        List<Orders> listOrder = ordersDAO.notifyOrder();
        int countNotifyOrder = ordersDAO.countNotifyOrder();
        if (countNotifyOrder >= 0) {
            model.addAttribute("countNotifyOrder", countNotifyOrder);
        }
        model.addAttribute("listOrder", listOrder);
        List<Catalogs> catalogses = dao.getAllCatalogsNoParent();
        model.addAttribute("catalogses", catalogses);
        model.addAttribute("listFeedback", listFeedback);
        return "admin/catalog-list";
    }

    //GET: get all catalog with catalog = 0
    @RequestMapping(value = "parent-catalog", method = RequestMethod.GET)
    public String parentcatalog(HttpSession session, RedirectAttributes attributes, Model model) {
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
        List<Catalogs> catalogses = dao.getAllParentCatalogs();
        List<Catalogs> catalogNoParent = dao.getAllCatalogsNoParent();
        model.addAttribute("catalogs", catalogses);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("catalogNoParent", catalogNoParent);
        return "admin/catalog-parent-list";
    }

    //GET: get/catalog - view - insert
    @RequestMapping(value = "create-catalog", method = RequestMethod.GET)
    public String createCatalogs(HttpSession session, RedirectAttributes attributes, Model model) {
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
        List<Catalogs> catalogses = dao.getAllCatalogsNoParent();
        Catalogs catalogs = new Catalogs();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("catalogs", catalogs);
        model.addAttribute("catalogses", catalogses);
        return "admin/catalog-insert";
    }

    //POST:Create catalog
    @RequestMapping(value = "create-catalog", method = RequestMethod.POST)
    public String createCatalogs(HttpSession session, RedirectAttributes attributes, Catalogs c) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }
        if (c.getCatalogName().isEmpty()) {
            attributes.addFlashAttribute("error", "Tên danh mục không được để trống !!");
            return "redirect:create-catalog.htm";
        }
        boolean bl = dao.createCatalogs(c);
        if (bl) {
            if (c.getParentId() == 0) {
                attributes.addFlashAttribute("success", "Thêm mới danh mục thành công !!");
                return "redirect:catalog-news.htm";
            } else {
                attributes.addFlashAttribute("success", "Thêm mới danh mục thành công !!");
                return "redirect:parent-catalog.htm";
            }
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "redirect:create-catalog.htm";
        }
    }

    //GET: get/catalog - view upadte
    @RequestMapping(value = "update-catalog", method = RequestMethod.GET)
    public String updateCatalogs(HttpSession session, RedirectAttributes attributes, Model model, int catalogId) {
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
        Catalogs catalogs = dao.getCatalogId(catalogId);
        long countCatalogIfhasParent = dao.findCatalogHasParent(catalogId);
        List<Catalogs> cataloghasparentbutnotself = dao.getCatalogHasParentButNotSelf(catalogId);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("catalogs", catalogs);
        model.addAttribute("countCatalogIfhasParent", countCatalogIfhasParent);
        model.addAttribute("cataloghasparentbutnotself", cataloghasparentbutnotself);
        return "admin/catalog-update";
    }

    //POST: get/catalog -  upadte
    @RequestMapping(value = "update-catalog", method = RequestMethod.POST)
    public String updateCatalogs(HttpSession session, RedirectAttributes attributes, Catalogs c, int piority) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }
        boolean bl = dao.updateCatalog(c, piority);
        if (bl) {
            if (c.getParentId() == 0) {
                attributes.addFlashAttribute("success", "Cập nhật danh mục thành công !!");
                return "redirect:catalog-news.htm";
            } else {
                attributes.addFlashAttribute("success", "Cập nhật danh mục thành công !!");
                return "redirect:parent-catalog.htm";
            }
        } else {
            return "redirect:update-catalog.htm?catalogId=" + c.getCatalogId();
        }
    }

    @RequestMapping(value = "delete-catalog")
    public @ResponseBody
    String deleteCatalog(HttpSession session, Catalogs c, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "error";
        }
        boolean check = dao.deleteCatalog(c.getCatalogId());
        if (c.getCatalogId() < 0) {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "redirect:categories.htm";
        }
        if (check) {
            if (c.getParentId() == 0) {
                return "success";
            } else {
                return "success";
            }
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "error";
        }
    }

    @RequestMapping(value = "news", method = RequestMethod.GET)
    public String getallNews(HttpSession session, RedirectAttributes attributes, Model model) {
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
        List<News> listNew = dao.getAllNewsAdmin();
        List<Admins> listAdmin = adminDAO.getAllAdmin();
        List<Catalogs> listCatalog = dao.getAllCatalogs();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listNew", listNew);
        model.addAttribute("listCatalog", listCatalog);
        model.addAttribute("listAdmin", listAdmin);
        return "admin/new-list";
    }

    @RequestMapping(value = "create-news", method = RequestMethod.GET)
    public String createNews(HttpSession session, RedirectAttributes attributes, Model model) {
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
        Admins admin = (Admins) session.getAttribute("InfoAdmin");
        List<Catalogs> listCatalog = dao.getAllCatalogs();
        model.addAttribute("listCatalog", listCatalog);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("admin", admin);
        News news = new News();
        model.addAttribute("new", news);
        return "admin/new-insert";
    }

    @RequestMapping(value = "create-news", method = RequestMethod.POST)
    public String createNews(RedirectAttributes attributes, News news, HttpSession session) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }
        if (news.getNewDescription().isEmpty()) {
            attributes.addFlashAttribute("errordescription", "Mô tả không được để trống !!");
            return "redirect:create-news.htm";
        }
        if (news.getNewImage().isEmpty()) {
            attributes.addFlashAttribute("errorimgs", "Ảnh không được để trống !!");
            return "redirect:create-news.htm";
        }
        if (news.getNewContent().isEmpty()) {
            attributes.addFlashAttribute("errorcontent", "Nội dung không được để trống !!");
            return "redirect:create-news.htm";
        }
        Admins admins = (Admins) session.getAttribute("InfoAdmin");
        news.setAdmins(admins);
//        news.setCreateDate(new Date().getTime());
        boolean bl = dao.createNews(news);
        if (bl) {
            attributes.addFlashAttribute("success", "Đăng tin thành công !!");
            return "redirect:news.htm";
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "redirect:create-news.htm";
        }
    }

    @RequestMapping(value = "delete-news", method = RequestMethod.GET)
    public @ResponseBody
    String deleteNews(HttpSession session1, RedirectAttributes attributes, int newsId) {
        if (session1.getAttribute("InfoAdmin") == null) {
            return "redirect:admin/login";
        }
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean bl = false;
        try {
            News news = dao.getNewsId(newsId);
            if (news == null) {
                attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
                return "redirect:news.htm";
            }
            news.setNewStatus(-1);
            switch (news.getNewStatus()) {
                case -1:
                    bl = true;
                    break;
                default:
                    bl = false;
                    break;
            }
            if (bl) {
                session.update(news);
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

    @RequestMapping(value = "update-news", method = RequestMethod.GET)
    public String updateNews(HttpSession session, RedirectAttributes attributes, Model model, int newsId) {
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
        List<Catalogs> listCatalog = dao.getAllCatalogs();
        model.addAttribute("listCatalog", listCatalog);
        News news = dao.getNewsId(newsId);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("new", news);
        return "admin/new-update";
    }

    @RequestMapping(value = "update-news", method = RequestMethod.POST)
    public String updateNews(HttpSession httpSession, RedirectAttributes attributes, News news, int newStatus, int newsId) {
        if (httpSession.getAttribute("InfoAdmin") == null) {
            return "redirect:admin/login";
        }
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Admins admins = (Admins) httpSession.getAttribute("InfoAdmin");
        news.setAdmins(admins);
        boolean bl = false;
        if (news.getNewDescription().isEmpty()) {
            attributes.addFlashAttribute("errordescription", "Mô tả không được để trống !!");
            return "redirect:update-news.htm?newsId=" + newsId;
        }
        if (news.getNewImage().isEmpty()) {
            attributes.addFlashAttribute("errordescription", "Ảnh không được để trống !!");
            return "redirect:update-news.htm?newsId=" + newsId;
        }
        if (news.getNewContent().isEmpty()) {
            attributes.addFlashAttribute("errorcontent", "Nội dung không được để trống !!");
            return "redirect:update-news.htm?newsId=" + newsId;
        }
        try {
            switch (newStatus) {
                case 1:
                    bl = true;
                    break;
                case 0:
                    bl = true;
                    break;
                default:
                    bl = false;
                    break;
            }
            if (bl) {
                Date date = Calendar.getInstance().getTime();
                news.setCreateDate(date);
                session.update(news);
                session.getTransaction().commit();
                attributes.addFlashAttribute("success", "Cập nhật thành công !!");
                return "redirect:news.htm";
            } else {
                attributes.addFlashAttribute("error", "Sai câu lệnh 1!!");
                return "redirect:update-news.htm?newsId=" + newsId;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            attributes.addFlashAttribute("error", "Sai câu lệnh!!");
            return "redirect:update-news.htm?newsId=" + newsId;
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "news-detail", method = RequestMethod.GET)
    public String newsDetail(HttpSession session, RedirectAttributes attributes, Model model, int newsId) {
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
        List<Admins> listAdmin = adminDAO.getAllAdmin();
        News news = dao.getNewsId(newsId);
        List<Catalogs> catalogses = dao.getAllCatalogsNoParent();
        model.addAttribute("catalogses", catalogses);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("news", news);
        model.addAttribute("listAdmin", listAdmin);
        return "admin/new-detail";
    }
}
