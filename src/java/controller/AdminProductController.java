package controller;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.FeedbackDAO;
import dao.OrdersDAO;
import dao.ProductDAO;
import entity.Brands;
import entity.Categories;
import entity.Feedbacks;
import entity.Orders;
import entity.Products;
import java.util.ArrayList;
import java.util.Arrays;
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
public class AdminProductController {

    private final ProductDAO productDAO = new ProductDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final BrandDAO brandDAO = new BrandDAO();
    private final FeedbackDAO feedbacksDAO = new FeedbackDAO();
    private final OrdersDAO ordersDAO = new OrdersDAO();

    /**
     * Crud Product
     *
     * @param session
     * @param attributes
     * @param model
     * @return
     */
    //GET: Product/Get all Product
    @RequestMapping(value = "product", method = RequestMethod.GET)
    public String getallProduct(HttpSession session, RedirectAttributes attributes, Model model) {
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
        List<Products> listProduct = productDAO.getAllProduct();
        List<Categories> listCategories = categoryDAO.fullCategories();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listCategories", listCategories);
        return "admin/product-list";
    }

    //GET: Product/Create Product
    @RequestMapping(value = "create-product", method = RequestMethod.GET)
    public String createProduct(HttpSession session, RedirectAttributes attributes, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }
        String required = "<span data-toggle=\"tooltip\" title=\"Bắt buộc nhập\" class=\"text-danger\" style=\"cursor: help;\"> (*)</span>";
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
        List<Categories> listCategories = categoryDAO.getAllCategories();
        List<Categories> listParentCategories = categoryDAO.getAllParentCategories();
        List<Categories> newListCategoriesHasParent = new ArrayList<>();
        List<Categories> newListCategoriesNoParent = new ArrayList<>();
        listCategories.forEach((c) -> {
            List<Categories> listCateHasParent = categoryDAO.getCategoriesParent(c.getCategoryId());
            if (listCateHasParent.size() > 0) {
                newListCategoriesHasParent.add(c);
            } else {
                newListCategoriesNoParent.add(c);
            }
        });
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listParentCategories", listParentCategories);
        model.addAttribute("newListCategoriesHasParent", newListCategoriesHasParent);
        model.addAttribute("newListCategoriesNoParent", newListCategoriesNoParent);
        List<Brands> listBrands = brandDAO.getAllBrands();
        model.addAttribute("listBrands", listBrands);
        Products products = new Products();
        model.addAttribute("required", required);
        model.addAttribute("product", products);
        return "admin/product-insert";
    }

    //POST: Product/Create Product
    @RequestMapping(value = "create-product", method = RequestMethod.POST)
    public String createProduct(Products p, String warranty, HttpSession session, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            return "redirect:login.htm";
        }
        if (p.getProductName().isEmpty()) {
            attributes.addFlashAttribute("errorproductname", "Tên sản phẩm không được để trống");
            return "redirect:create-product.htm";
        } else if (p.getFeatureImage().isEmpty()) {
            attributes.addFlashAttribute("errorfeatureimage", "Ảnh đại diện sản phẩm không được để trống");
            return "redirect:create-product.htm";
        } else if (p.getPrice() < 0) {
            attributes.addFlashAttribute("errorprice", "Giá sản phẩm không được âm");
            return "redirect:create-product.htm";
        } else if (p.getProductSale() < 0 || p.getProductSale() > 100) {
            attributes.addFlashAttribute("errorpricecheck", "Giảm giá sản phẩm không được nhỏ hơn 0 hoặc lớn hơn 100");
            return "redirect:create-product.htm";
        } else if (p.getProductCode().isEmpty()) {
            attributes.addFlashAttribute("errorproductcode", "Mã sản phẩm không được để trống");
            return "redirect:create-product.htm";
        } else if (warranty.isEmpty()) {
            attributes.addFlashAttribute("errorwarranty", "Năm bảo hành không được để trống");
            return "redirect:create-product.htm";
        } else if(productDAO.checkProductCodeExists(p.getProductCode())){
            attributes.addFlashAttribute("errorproductcode", "Mã sản phẩm đã tồn tại");
            return "redirect:create-product.htm";
        }else if (p.getProductDescription().isEmpty()) {
            attributes.addFlashAttribute("errordescriptiom", "Mô tả chi tiết không được để trống");
            return "redirect:create-product.htm";
        }else if (p.getSpecificationName().isEmpty()) {
            attributes.addFlashAttribute("errorSpecificationName", "Thông số kỹ thuật không được để trống");
            return "redirect:create-product.htm";
        }else if (p.getSpecificationValue().isEmpty()) {
            attributes.addFlashAttribute("errorSpecificationValue", "Thông số kỹ thuật không được để trống");
            return "redirect:create-product.htm";
        }
        boolean bl = productDAO.createProduct(p);
        if (bl) {
            attributes.addFlashAttribute("success", "Thêm mới sản phẩm thành công !!");
            return "redirect:product.htm";
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "redirect:create-product.htm";
        }
    }

    //GET: Product/update Product
    @RequestMapping(value = "update-product", method = RequestMethod.GET)
    public String updateProduct(HttpSession session, RedirectAttributes attributes, Model model, int productId) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }
        if (productId < 0) {
            return "redirect:product.htm";
        }
        String required = "<span data-toggle=\"tooltip\" title=\"Bắt buộc nhập\" class=\"text-danger\" style=\"cursor: help;\"> (*)</span>";
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
        List<Categories> listCategories = categoryDAO.getAllCategories();
        List<Categories> listParentCategories = categoryDAO.getAllParentCategories();
        List<Categories> newListCategoriesHasParent = new ArrayList<>();
        List<Categories> newListCategoriesNoParent = new ArrayList<>();
        for (Categories c : listCategories) {
            List<Categories> listCateHasParent = categoryDAO.getCategoriesParent(c.getCategoryId());
            if (listCateHasParent.size() > 0) {
                newListCategoriesHasParent.add(c);
            } else {
                newListCategoriesNoParent.add(c);
            }
        }
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listParentCategories", listParentCategories);
        model.addAttribute("newListCategoriesHasParent", newListCategoriesHasParent);
        model.addAttribute("newListCategoriesNoParent", newListCategoriesNoParent);
        List<Brands> listBrands = brandDAO.getAllBrands();
        model.addAttribute("listBrands", listBrands);
        Products products = productDAO.getProductId(productId);
        if (products == null) {
            attributes.addFlashAttribute("error", "Hưmmmm !!");
            return "redirect:product.htm";
        }
        List<String> listProductImages = new ArrayList<>();
        String[] imgs = products.getImages().split(";");
        listProductImages.addAll(Arrays.asList(imgs));
        if (listProductImages.size() > 0) {
            model.addAttribute("listProductImages", listProductImages);
        }
        model.addAttribute("required", required);
        model.addAttribute("product", products);
        return "admin/product-update";
    }

    //POST: Product/Update Product
    @RequestMapping(value = "update-product", method = RequestMethod.POST)
    public String updateProduct(Products p,String warranty,  HttpSession httpSession, RedirectAttributes attributes, int productStatus, int productId) {
        if (httpSession.getAttribute("InfoAdmin") == null) {
            return "redirect:login.htm";
        }
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if (p.getProductName().isEmpty()) {
            attributes.addFlashAttribute("errorproductname", "Tên sản phẩm không được để trống");
            return "redirect:update-product.htm?productId=" + p.getProductId();
        } else if (p.getFeatureImage().isEmpty()) {
            attributes.addFlashAttribute("errorfeatureimage", "Ảnh đại diện sản phẩm không được để trống");
            return "redirect:update-product.htm?productId=" + p.getProductId();
        } else if (p.getPrice() < 0) {
            attributes.addFlashAttribute("errorprice", "Giá sản phẩm không được âm");
            return "redirect:update-product.htm?productId=" + p.getProductId();
        } else if (p.getProductSale() < 0 || p.getProductSale() > 100) {
            attributes.addFlashAttribute("errorpricecheck", "Giảm giá sản phẩm không được nhỏ hơn 0 hoặc lớn hơn 100");
            return "redirect:update-product.htm?productId=" + p.getProductId();
        } else if (p.getProductCode().isEmpty()) {
            attributes.addFlashAttribute("errorproductcode", "Mã sản phẩm không được để trống");
            return "redirect:update-product.htm?productId=" + p.getProductId();
        } else if (warranty.isEmpty()) {
            attributes.addFlashAttribute("errorwarranty", "Năm bảo hành không được để trống");
            return "redirect:update-product.htm?productId=" + p.getProductId();
        }else if (p.getProductDescription().isEmpty()) {
            attributes.addFlashAttribute("errordescriptiom", "Mô tả chi tiết không được để trống");
            return "redirect:update-product.htm?productId="+ p.getProductId();
        }else if (p.getSpecificationName().isEmpty()) {
            attributes.addFlashAttribute("errorSpecificationName", "Thông số kỹ thuật không được để trống");
            return "redirect:update-product.htm?productId="+ p.getProductId();
        }else if (p.getSpecificationValue().isEmpty()) {
            attributes.addFlashAttribute("errorSpecificationValue", "Thông số kỹ thuật không được để trống");
            return "redirect:update-product.htm?productId="+ p.getProductId();
        }
        
        boolean bl = false;
        try {
            switch (productStatus) {
                case 0:
                    bl = true;
                    break;
                case 1:
                    bl = true;
                    break;
                case 2:
                    bl = true;
                    break;
                default:
                    bl = false;
                    break;
            }
            if (bl) {
                session.update(p);
                session.getTransaction().commit();
                attributes.addFlashAttribute("success", "Cập nhật sản phẩm thành công !!");
                return "redirect:product.htm";
            } else {
                attributes.addFlashAttribute("error", "Please select the correct state !!");
                return "redirect:update-product.htm?productId=" + productId;

            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            attributes.addFlashAttribute("error", "có gì đó không đúng !!");
            return "redirect:update-product.htm?productId=" + productId;
        } finally {
            session.close();
        }
    }

    //POST: Product/delete Product with change status = -1
    @RequestMapping(value = "delete-product", method = RequestMethod.GET)
    public @ResponseBody
    String deleteProduct(HttpSession httpSession, RedirectAttributes attributes, int productId) {
        if (httpSession.getAttribute("InfoAdmin") == null) {
            return "redirect:login.htm";
        }
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        boolean bl = false;
        session.beginTransaction();
        try {
            Products products = productDAO.getProductId(productId);
            if (products == null) {
                attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
                return "redirect:product.htm";
            }
            products.setProductStatus(-1);
            switch (products.getProductStatus()) {
                case -1:
                    bl = true;
                    break;
                default:
                    bl = false;
                    break;
            }
            if (bl) {
                session.update(products);
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

    /**
     * Crud Product Categories
     *
     * @param session
     * @return
     */
    //GET: List/Categories
    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public String categories(HttpSession session, RedirectAttributes attributes, Model model) {
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
        List<Categories> listCategory = categoryDAO.getAllCategories();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listCategory", listCategory);
        return "admin/category-list";
    }

    //GET: List/Parent Categories
    @RequestMapping(value = "categories-parent", method = RequestMethod.GET)
    public String isParentCategories(HttpSession session, RedirectAttributes attributes, Model model) {
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
        List<Categories> list = categoryDAO.getAllParentCategories();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listParentCategory", list);
        return "admin/category-parent-list";
    }

    //GET: Create Categories
    @RequestMapping(value = "create-category", method = RequestMethod.GET)
    public String createCategories(HttpSession session, Model model) {
        if (session.getAttribute("InfoAdmin") == null) {
            return "redirect:login.htm";
        }
        List<Feedbacks> listFeedback = feedbacksDAO.notifyFeedback();
        int countNotifyFeedback = feedbacksDAO.countNotifyFeedback();
        if (countNotifyFeedback >= 0) {
            model.addAttribute("countNotifyFeedback", countNotifyFeedback);
        }
        Categories category = new Categories();
        List<Categories> listcategories = categoryDAO.getAllCategories();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("listcategories", listcategories);
        model.addAttribute("category", category);
        return "admin/category-insert";
    }

    //POST: Create Categories
    @RequestMapping(value = "create-category", method = RequestMethod.POST)
    public String createCategories(HttpSession session, Categories c, RedirectAttributes attributes, String categoryName) {
        if (session.getAttribute("InfoAdmin") == null) {
            return "redirect:login";
        }
        if (c.getCategoryName().isEmpty()) {
            attributes.addFlashAttribute("errorcategoryname", "Tên danh mục không được để trống !!");
            return "redirect:create.htm";
        } else if (Integer.toString(c.getCategoryStatus()).isEmpty()) {
            attributes.addFlashAttribute("errorcategorystatus", "Vui lòng chọn trạng thái");
            return "redirect:update-category.htm?categoryId=" + c.getCategoryId();
        } else if (c.getCategoryStatus() < 0 || c.getCategoryStatus() > 1) {
            attributes.addFlashAttribute("errorcategorystatus", "Vui lòng chọn trạng thái");
            return "redirect:update-category.htm?categoryId=" + c.getCategoryId();
        }
        boolean check = categoryDAO.createCategories(c);
        if (check) {
            if (c.getParentId() == 0) {
                attributes.addFlashAttribute("success", "Thêm mới danh mục thành công !!");
                return "redirect:categories.htm";
            } else {
                attributes.addFlashAttribute("success", "Thêm mới danh mục thành công !!");
                return "redirect:categories-parent.htm";
            }
        } else {
            attributes.addFlashAttribute("error", "có gì đó không đúng !!");
            return "redirect:create.htm";
        }
    }

    //GET:update/categories
    @RequestMapping(value = "update-category", method = RequestMethod.GET)
    public String updateCategories(HttpSession session, int categoryId, Model model) {
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
        Categories category = categoryDAO.getId(categoryId);
        long cahasParent = categoryDAO.findCateHasParent(categoryId);
        List<Categories> listcategories = categoryDAO.getParentIdNotSelf(category.getCategoryId());
        model.addAttribute("listcategories", listcategories);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("cahasParent", cahasParent);
        model.addAttribute("category", category);
        return "admin/category-update";
    }

    //POST:update/categories
    @RequestMapping(value = "update-category", method = RequestMethod.POST)
    public String updateCategories(HttpSession session, Categories c, String categoryPiority, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            return "redirect:login.htm";
        }
        if (c.getCategoryName().isEmpty()) {
            attributes.addFlashAttribute("errorcategoryname", "Vui lòng nhập tên danh mục.");
            return "redirect:update-category.htm?categoryId=" + c.getCategoryId();
        } else if (categoryPiority.isEmpty()) {
            attributes.addFlashAttribute("errorcategorypority", "Vui lòng vị trí sắp xếp danh mục.");
            return "redirect:update-category.htm?categoryId=" + c.getCategoryId();
        } else if (Integer.toString(c.getCategoryStatus()).isEmpty()) {
            attributes.addFlashAttribute("errorcategorystatus", "Vui lòng chọn trạng thái");
            return "redirect:update-category.htm?categoryId=" + c.getCategoryId();
        } else if (c.getCategoryStatus() < 0 || c.getCategoryStatus() > 1) {
            attributes.addFlashAttribute("errorcategorystatus", "Vui lòng chọn trạng thái");
            return "redirect:update-category.htm?categoryId=" + c.getCategoryId();
        }
        boolean check = categoryDAO.updateCategories(c, Integer.parseInt(categoryPiority));
        if (check) {
            if (c.getParentId() == 0) {
                attributes.addFlashAttribute("success", "Cập nhập danh mục thành công !!");
                return "redirect:categories.htm";
            } else {
                attributes.addFlashAttribute("success", "Cập nhập danh mục thành công !!");
                return "redirect:categories-parent.htm";
            }
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "redirect:update-category.htm?categoryId=" + c.getCategoryId();
        }
    }

    @RequestMapping(value = "delete-category")
    public @ResponseBody
    String deleteCategories(HttpSession session, Categories c, RedirectAttributes attributes) {
        if (session.getAttribute("InfoAdmin") == null) {
            return "redirect:login.htm";
        }
        boolean check = categoryDAO.deleteCategories(c.getCategoryId());
        if (c.getCategoryId() < 0) {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "redirect:categories.htm";
        }
        if (check) {
            if (c.getParentId() == 0) {
                attributes.addFlashAttribute("success", "Xoá danh mục thành công !!");
                return "redirect:categories.htm";
            } else {
                attributes.addFlashAttribute("success", "Xoá danh mục thành công !!");
                return "redirect:categories.htm";
            }
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "redirect:categories.htm";
        }
    }

    /**
     * Crud Brand
     *
     * @param session
     * @param attributes
     * @param model
     * @return
     */
    //Get-all Brand
    @RequestMapping(value = "brand", method = RequestMethod.GET)
    public String brand(HttpSession session, RedirectAttributes attributes, Model model) {
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
        List<Brands> listBr = brandDAO.getAllBrands();
        model.addAttribute("listBrand", listBr);
        model.addAttribute("listFeedback", listFeedback);
        return "admin/brand-list";
    }

    //GET: Create Brand
    @RequestMapping(value = "create-brand", method = RequestMethod.GET)
    public String createBrand(HttpSession session, RedirectAttributes attributes, Model model) {
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
        Brands brand = new Brands();
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("brand", brand);
        return "admin/brand-insert";
    }

    //POST: Create Brand
    @RequestMapping(value = "create-brand", method = RequestMethod.POST)
    public String createBrand(HttpSession session, RedirectAttributes attributes, Brands brand) {
        if (brand.getBrandName().isEmpty()) {
            attributes.addFlashAttribute("error", "Tên thương hiệu không được để trống");
            return "redirect:create-brand.htm";
        }
        if (brand.getBrandLogo().isEmpty()) {
            attributes.addFlashAttribute("error", "Ảnh thương hiệu không được để trống");
            return "redirect:create-brand.htm";
        }
        if (brandDAO.checkBrandNameExists(brand.getBrandName())) {
            attributes.addFlashAttribute("error", "Tên thương hiệu đã tồn tại");
            return "redirect:create-brand.htm";
        }
        boolean check = brandDAO.createBrands(brand);
        if (check) {
            attributes.addFlashAttribute("success", "Thêm mới thành công!");
            return "redirect:brand.htm";
        } else {
            attributes.addFlashAttribute("error", "Thêm mới thất bại!");
            return "redirect:create-brand.htm";
        }
    }

    //GET: Update Brand
    @RequestMapping(value = "update-brand", method = RequestMethod.GET)
    public String updateBrand(HttpSession session, Model model, RedirectAttributes attributes, int brandId) {
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
        Brands brand = brandDAO.getBrandId(brandId);
        model.addAttribute("listFeedback", listFeedback);
        model.addAttribute("brand", brand);
        return "admin/brand-update";
    }

    //POST: Update Brand
    @RequestMapping(value = "update-brand", method = RequestMethod.POST)
    public String updateBrand(HttpSession session, Brands brand, RedirectAttributes attributes, int brandId, String brandPiority) {
        if (session.getAttribute("InfoAdmin") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục !!");
            return "redirect:login.htm";
        }
        String idBrand = String.valueOf(brandId);
        if (idBrand.isEmpty()) {
            attributes.addFlashAttribute("error", "Không tìm thấy thương hiệu");
            return "redirect:brand.htm";
        }
        if (brand.getBrandName().isEmpty()) {
            attributes.addFlashAttribute("error", "Tên thương hiệu không được để trống");
            return "redirect:update-brand.htm?brandId=" + brandId;
        }
        if (brand.getBrandLogo().isEmpty()) {
            attributes.addFlashAttribute("error", "Ảnh thương hiệu không được để trống");
            return "redirect:update-brand.htm?brandId=" + brandId;
        }
        if (brandPiority.isEmpty()) {
            attributes.addFlashAttribute("error", "Vị trí sắp xếp không được để chống");
            return "redirect:update-brand.htm?brandId=" + brandId;
        }
        boolean check = brandDAO.updateBrand(brand, Integer.parseInt(brandPiority));
        if (check) {
            attributes.addFlashAttribute("success", "Cập nhật thành công!");
            return "redirect:brand.htm";
        } else {
            attributes.addFlashAttribute("error", "Cập nhật thất bại!");
            return "redirect:update-brand.htm?brandId=" + brandId;
        }
    }

    //Delete Brand
    @RequestMapping(value = "delete-brand")
    public @ResponseBody
    String deleteBrand(HttpSession session, Brands brand, RedirectAttributes attributes) {
        boolean check = brandDAO.deleteBrand(brand.getBrandId());
        if (brand.getBrandId() < 0) {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "error";
        }
        if (check) {
            attributes.addFlashAttribute("success", "Xoá thương hiệu thành công !!");
            return "success";
        } else {
            attributes.addFlashAttribute("error", "Có gì đó không đúng !!");
            return "error";
        }
    }
}
