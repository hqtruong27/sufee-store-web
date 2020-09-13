package controller;

import Validate.RegexValid;
import common.Paging;
import common.ProductCommon;
import common.ProductFilter;
import dao.AdminDAO;
import dao.BannerDAO;
import dao.BrandDAO;
import dao.CategoryDAO;
import dao.ContactDAO;
import dao.CustomerDAO;
import dao.FaqDAO;
import dao.IntroductionDAO;
import dao.LogoDAO;
import dao.ProductDAO;
import dao.NewsDAO;
import dao.WishlistDAO;
import entity.Admins;
import entity.Banners;
import entity.Brands;
import entity.Catalogs;
import entity.Categories;
import entity.Contacts;
import entity.Customers;
import entity.Faqs;
import entity.Introductions;
import entity.Logo;
import entity.News;
import entity.ProductComments;
import entity.Products;
import entity.Wishlists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Hoang Truong
 */
@Controller
public class ClientController {

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final NewsDAO newsDAO = new NewsDAO();
    private final BannerDAO bannerDAO = new BannerDAO();
    private final BrandDAO brandDAO = new BrandDAO();
    private final AdminDAO adminDAO = new AdminDAO();
    private final WishlistDAO wishlistDAO = new WishlistDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final FaqDAO faqDAO = new FaqDAO();
    private final ContactDAO contactDAO = new ContactDAO();
    private final IntroductionDAO introDAO = new IntroductionDAO();
    private final LogoDAO logoDAO = new LogoDAO();
    

    @RequestMapping(value = "trang-chu", method = RequestMethod.GET)
    public String Home(Model model, HttpSession session) {
        Customers customers = (Customers) session.getAttribute("InfoCustomer");
        //get 1 product have the highest % discount
        Products productTopOneSale = productDAO.getBestSaleProduct();
        //get 1 product have the highest sell
        Products productTopOneSell = productDAO.getBestSellProduct();
        //get 4 latest product
        List<Products> fourNewProduct = productDAO.getFourNewProductShowForClient();
        //get 12 most discounted product
        List<Products> product12Sale = productDAO.get12SalePriceProductShowForClient();
        //get 12 most sell highest qty product
        List<Products> product12SellQuantity = productDAO.get12SellQuantityProductShowForClient();
        //get 12 most for you qty product
        List<Products> product12foryou = productDAO.get12ProductForYouShowForClient();
        //Get banner show client
        List<Banners> banner = bannerDAO.getAllBannerShowForClient();
        //Get brand show client
        List<Brands> brand = brandDAO.getAllBrandShowForClient();
        //navbar categories product
        String navHtml = categoryDAO.generateNavbarHtml();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();

        ProductCommon productSale = null;
        ProductCommon productSell = null;
        List<ProductCommon> pro12Sale = new ArrayList<>();
        List<ProductCommon> pro12Sell = new ArrayList<>();
        List<ProductCommon> fourProNew = new ArrayList<>();
        List<ProductCommon> pro12FouYou = new ArrayList<>();
        if (customers == null) {
            //get 1 product have the highest % discount 
            if (productTopOneSale != null) {
                ProductCommon product = new ProductCommon(productTopOneSale, null, null);
                product.setIsNewProduct(productDAO.checkNewProduct(product.getProduct().getProductId()));
                productSale = product;
            }
            //get 1 product have the highest sell
            if (productTopOneSell != null) {
                ProductCommon product = new ProductCommon(productTopOneSell, null, null);
                product.setIsNewProduct(productDAO.checkNewProduct(product.getProduct().getProductId()));
                productSell = product;
            }

            //get 12 product have the highest % discount
            if (product12Sale.size() > 0) {
                for (Products pr : product12Sale) {
                    ProductCommon product = new ProductCommon(pr, null, null);
                    product.setIsNewProduct(productDAO.checkNewProduct(pr.getProductId()));
                    pro12Sale.add(product);
                }
            }
            //get 12 product have the highest sellQty
            if (product12SellQuantity.size() > 0) {
                for (Products pr : product12SellQuantity) {
                    ProductCommon product = new ProductCommon(pr, null, null);
                    product.setIsNewProduct(productDAO.checkNewProduct(pr.getProductId()));
                    pro12Sell.add(product);
                }
            }
            //get four product new
            if (fourNewProduct.size() > 0) {
                for (Products pro : fourNewProduct) {
                    ProductCommon product = new ProductCommon(pro, null, null);
                    product.setIsNewProduct(productDAO.checkNewProduct(pro.getProductId()));
                    fourProNew.add(product);
                }
            }
            if (product12foryou.size() > 0) {
                for (Products pro : product12foryou) {
                    ProductCommon product = new ProductCommon(pro, null, null);
                    product.setIsNewProduct(productDAO.checkNewProduct(pro.getProductId()));
                    pro12FouYou.add(product);
                }
            }
        } else {
            List<Wishlists> wishlistses = wishlistDAO.getWishlistByCustomerId(customers.getCustomerId());

            if (productTopOneSale != null) {
                ProductCommon product = new ProductCommon(productTopOneSale, customers, wishlistses);
                product.setIsNewProduct(productDAO.checkNewProduct(product.getProduct().getProductId()));
                productSale = product;
            }
            //get 1 product have the highest sell
            if (productTopOneSell != null) {
                ProductCommon product = new ProductCommon(productTopOneSell, customers, wishlistses);
                product.setIsNewProduct(productDAO.checkNewProduct(product.getProduct().getProductId()));
                productSell = product;
            }

            //get 12 product have the highest % discount
            if (product12Sale.size() > 0) {
                for (Products pr : product12Sale) {
                    ProductCommon product = new ProductCommon(pr, customers, wishlistses);
                    product.setIsNewProduct(productDAO.checkNewProduct(pr.getProductId()));
                    pro12Sale.add(product);
                }
            }
            //get 12 product have the highest sellQty
            if (product12SellQuantity.size() > 0) {
                for (Products pr : product12SellQuantity) {
                    ProductCommon product = new ProductCommon(pr, customers, wishlistses);
                    product.setIsNewProduct(productDAO.checkNewProduct(pr.getProductId()));
                    pro12Sell.add(product);
                }
            }
            //get four product new
            if (fourNewProduct.size() > 0) {
                for (Products pro : fourNewProduct) {
                    ProductCommon product = new ProductCommon(pro, customers, wishlistses);
                    product.setIsNewProduct(productDAO.checkNewProduct(pro.getProductId()));
                    fourProNew.add(product);
                }
            }
            //get four product new
            if (product12foryou.size() > 0) {
                for (Products pro : product12foryou) {
                    ProductCommon product = new ProductCommon(pro, customers, wishlistses);
                    product.setIsNewProduct(productDAO.checkNewProduct(pro.getProductId()));
                    pro12FouYou.add(product);
                }
            }
        }
        Contacts contact = contactDAO.getContactByStatus();
        if (contact != null) {
            model.addAttribute("contact", contact);
        }
        
        Logo logo = logoDAO.getLogoByStatus();
        if (logo != null) {
            model.addAttribute("logo", logo);
        }
        
        model.addAttribute("productTopOneSale", productSale);

        if (productSell != null) {
            model.addAttribute("productTopOneSell", productSell);
        }
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }
        if (pro12Sale.size() > 0) {
            model.addAttribute("product12Sale", pro12Sale);
        }
        if (pro12Sell.size() > 0) {
            model.addAttribute("product12SellQuantity", pro12Sell);
        }
        if (fourProNew.size() > 0) {
            model.addAttribute("fourNewProduct", fourProNew);
        }
        if (product12foryou.size() > 0) {
            model.addAttribute("product12foryou", pro12FouYou);
        }
        model.addAttribute("banner", banner);
        model.addAttribute("brand", brand);
        return "client/home";
    }

    @RequestMapping(value = "tim-kiem", method = RequestMethod.POST)
    public String search(HttpSession session, String productName) {
        session.setAttribute("productNameClient", productName);
        return "redirect:san-pham.htm";
    }
    @RequestMapping(value = "xoa-loc")
    public String remove(HttpSession session) {
        if (session.getAttribute("productNameClient") != null) {
            session.removeAttribute("productNameClient");
        }
        return "redirect:san-pham.htm";
    }

    @RequestMapping(value = "san-pham", method = RequestMethod.GET)
    public String product(HttpSession session, Model model, String categoryId, String brandId, String view, Integer sort, Integer pageSize, Integer page) {
        Customers customers = (Customers) session.getAttribute("InfoCustomer");

        Brands brands = null;
        Categories categoryses = null;

        String keyword = "";

        if (session.getAttribute("productNameClient") != null) {
            keyword = (String) session.getAttribute("productNameClient");
        }

        if (brandId != null && !brandId.equals("")) {
            brands = brandDAO.getBrandId(RegexValid.convertStringToInt(brandId, 0));
        }
        if (categoryId != null && !categoryId.equals("")) {
            categoryses = categoryDAO.getId(RegexValid.convertStringToInt(categoryId, 0));
        }
        if (page == null || page <= 0) {
            page = 1;
        }

        if (pageSize == null || (pageSize != 9 && pageSize != 24 && pageSize != 60)) {
            pageSize = 9;
        }

        if (sort == null || sort != 1 && sort != 2 && sort != 3 && sort != 4 && sort != 5 && sort != 6 && sort != 7) {
            sort = 1;
        }

        if (view == null || (!view.equals("grid") && !view.equals("list"))) {
            view = "grid";
        }
        List<Brands> brandes;
        List<Categories> categories;
        List<Products> productses;
        String pagingHtml = "";
        Paging paging;
        ProductFilter filters;
        String firstLink = "/Sufee_Store/san-pham.htm?brandId=" + brandId + "&view=" + view + "&sort=" + sort + "&pageSize=" + pageSize + "&keyword=" + keyword;
        String currentLink = "/Sufee_Store/san-pham.htm{p}&brandId=" + brandId + "&view=" + view + "&sort=" + sort + "&pageSize=" + pageSize + "&keyword=" + keyword;
        List<ProductCommon> proByCategory = new ArrayList<>();
        List<ProductCommon> proByBrand = new ArrayList<>();

        //product by category
        List<Products> productBycategory = productDAO.getProductByCategoryShowForClient(RegexValid.convertStringToInt(categoryId, 0));
        //product by brand
//        List<Products> productByBrand = productDAO.getProductByBrandShowForClient(brandId);
        //get brand sort product 
        List<Brands> brand = brandDAO.getAllBrandShowForClient();

        List<Categories> listCate = categoryDAO.getAllCategoriesShowClient();
        //navbar categories product
        String navHtml = categoryDAO.generateNavbarHtml();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();

        int totalRecords = productDAO.countProductFilterForPaging(brands, categoryses, keyword);
        if (totalRecords > pageSize) {
            paging = new Paging(page, totalRecords, pageSize, currentLink, firstLink);
            filters = productDAO.filterProductsFrontEnd(paging.startRecord, pageSize, brands, categoryses, keyword, sort);
            productBycategory = filters.products;
            brandes = filters.brands;
            categories = filters.categories;
            pagingHtml = paging.generateHtmlFrontend();
        } else {
            filters = productDAO.filterProductsFrontEnd(0, totalRecords, brands, categoryses, keyword, sort);
            productBycategory = filters.products;
            brandes = filters.brands;
            categories = filters.categories;
        }

        if (pagingHtml.length() > 0) {
            model.addAttribute("paging", pagingHtml);
        }

        if (customers == null) {
            if (productBycategory.size() > 0) {
                for (Products pro : productBycategory) {
                    ProductCommon product = new ProductCommon(pro, null, null);
                    product.setIsNewProduct(productDAO.checkNewProduct(pro.getProductId()));
                    proByCategory.add(product);
                }
            }
        } else {
            if (productBycategory.size() > 0) {
                for (Products pro : productBycategory) {
                    ProductCommon product = new ProductCommon(pro, null, null);
                    product.setIsNewProduct(productDAO.checkNewProduct(pro.getProductId()));
                    proByCategory.add(product);
                }
            }
        }

        if (proByCategory.size() > 0) {
            model.addAttribute("productBycategory", proByCategory);
        }

        if (brand.size() > 0) {
            model.addAttribute("brand", brand);
        }
        if (listCate.size() > 0) {
            model.addAttribute("listCate", listCate);
        }
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }
        if (brands != null) {
            model.addAttribute("brandId", brands.getBrandId());
        }
        if (categories.size() > 0) {
            model.addAttribute("parentCategories", categories);
        }

        if (brandes.size() > 0) {
            model.addAttribute("brands", brandes);
        }

        if (categoryses != null) {
            model.addAttribute("categoryId", categoryses.getCategoryId());
        }
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("brandId", brandId);
        model.addAttribute("view", view);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sort", sort);
        model.addAttribute("keyword", keyword);
        return "client/product-list";
    }

    @RequestMapping(value = "san-pham/chi-tiet", method = RequestMethod.GET)
    public String productDetail(HttpSession session, Model model, int productId) {
        //get session Customer
        Customers customers = (Customers) session.getAttribute("InfoCustomer");
        //get category show product detail
        List<Categories> listCategory = categoryDAO.fullCategories();
        //get brand show product detail
        List<Brands> listBrand = brandDAO.getAllBrandShowForClient();
        //detail product
        Products products = productDAO.getProductId(productId);
        //list 4 related products
        List<Products> fourRelatedProduct = productDAO.getFourRelatedProductShowForClient(products.getCategories().getCategoryId(), productId);
        //count comment products
        int countProductCmt = productDAO.countProductComment(productId);
        //navbar categories product
        String navHtml = categoryDAO.generateNavbarHtml();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();
        //list productComment
        List<ProductComments> productComments = productDAO.getAllCommentByProductId(productId);
        //list customer
        List<Customers> customerses = customerDAO.getAllCustomer();
        if (products == null) {
            return "client/home";
        }

        ProductCommon productDetail = null;
        List<ProductCommon> fourProRelated = new ArrayList<>();
        if (customers == null) {
            if (fourRelatedProduct != null) {
                ProductCommon pro = new ProductCommon(products, null, null);
                pro.setIsNewProduct(productDAO.checkNewProduct(pro.getProduct().getProductId()));
                productDetail = pro;
            }
            if (fourRelatedProduct.size() > 0) {
                for (Products product : fourRelatedProduct) {
                    ProductCommon pro = new ProductCommon(product, null, null);
                    pro.setIsNewProduct(productDAO.checkNewProduct(pro.getProduct().getProductId()));
                    fourProRelated.add(pro);
                }
            }
        } else {
            List<Wishlists> wishlistses = wishlistDAO.getWishlistByCustomerId(customers.getCustomerId());
            if (fourRelatedProduct != null) {
                ProductCommon pro = new ProductCommon(products, customers, wishlistses);
                pro.setIsNewProduct(productDAO.checkNewProduct(pro.getProduct().getProductId()));
                productDetail = pro;
            }
            if (fourRelatedProduct.size() > 0) {
                for (Products product : fourRelatedProduct) {
                    ProductCommon pro = new ProductCommon(product, customers, wishlistses);
                    pro.setIsNewProduct(productDAO.checkNewProduct(pro.getProduct().getProductId()));
                    fourProRelated.add(pro);
                }
            }
        }

        if (productDetail != null) {
            List<String> images = new ArrayList<>();
            String[] imgs = productDetail.getProduct().getImages().split(";");
            images.addAll(Arrays.asList(imgs));
            model.addAttribute("productImages", images);
            model.addAttribute("productDetail", productDetail);
        }
        if (fourProRelated.size() > 0) {
            model.addAttribute("fourRelatedProduct", fourProRelated);
        }

        if (countProductCmt > 0) {
            model.addAttribute("countProductCmt", countProductCmt);
        }
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }
        if (productComments.size() > 0) {
            model.addAttribute("productComments", productComments);
        }
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("listBrand", listBrand);
        model.addAttribute("customerses", customerses);
        return "client/product-detail";
    }

    @RequestMapping(value = "news-list-catalog", method = RequestMethod.GET)
    public String getAllNewsByCatalogId(HttpSession session, String catalogId, Model model, Integer page, Integer pageSize,RedirectAttributes attributes) {
        String firstLink = "/Sufee_Store/news-list-catalog.htm";
        String currentLink = "/Sufee_Store/news-list-catalog.htm";
        int totalRecords;
        Paging paging;
        News news = newsDAO.getNewsByCatalogId(RegexValid.convertStringToInt(catalogId, 0));
        List<Admins> listAdmin = adminDAO.getAllAdmin();
        List<News> listNewsHot = newsDAO.getAllNewsHotByCatalogId(RegexValid.convertStringToInt(catalogId, 0));
        List<News> sortByCountView = newsDAO.sortByCountView();
        List<News> newses;
        String pagingHtml = "";
        String navHtml = categoryDAO.generateNavbarHtml();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }

        if (page == null || page <= 0) {
            page = 1;
        }

        if (pageSize == null || (pageSize != 5 && pageSize != 20 && pageSize != 50)) {
            pageSize = 5;
        }
        if (catalogId == null || catalogId.equals("")) {
            totalRecords = newsDAO.countAllNewsFrontEnd();
            if (totalRecords > pageSize) {
                firstLink += "?pageSize=" + pageSize;
                currentLink += "{p}&pageSize=" + pageSize;
                paging = new Paging(page, totalRecords, pageSize, currentLink, firstLink);
                newses = newsDAO.getAllNews(paging.startRecord, pageSize);
                pagingHtml = paging.generateHtmlFrontend();
            } else {
                newses = newsDAO.getAllNews(0, totalRecords);
            }
        }else{
            Catalogs catalogs = newsDAO.getCatalogId(RegexValid.convertStringToInt(catalogId, 0));
            if (catalogs == null) {
                attributes.addFlashAttribute("error", "Mã danh mục tin tức không tồn tại!");
                return "redirect:news-list-catalog.htm";
            }else{
                totalRecords = newsDAO.countAllNewsByCatalogId(catalogs.getCatalogId());
                if (totalRecords > pageSize) {
                    firstLink += "?catalogId=" + catalogId + "&pageSize=" + pageSize;
                    currentLink += "{p}&catalogId=" + catalogId + "&pageSize=" + pageSize;
                    paging = new Paging(page, totalRecords, pageSize, currentLink, firstLink);
                    newses = newsDAO.getAllNewsByCatalogId(catalogs.getCatalogId(), paging.startRecord, pageSize);
                    pagingHtml = paging.generateHtmlFrontend();
                } else {
                    newses = newsDAO.getAllNewsByCatalogId(catalogs.getCatalogId(), 0, totalRecords);
                }

                model.addAttribute("catalogs", catalogs);
                model.addAttribute("catalogId", catalogId);
            }
        }
        
        if (newses.size()>0) {
            model.addAttribute("newses", newses);
        }
        if (pagingHtml.length() > 0) {
            model.addAttribute("paging", pagingHtml);
        }
        model.addAttribute("news", news);
        model.addAttribute("listNewsHot", listNewsHot);
        model.addAttribute("listAdmin", listAdmin);
        model.addAttribute("sortByCountView", sortByCountView);
        model.addAttribute("pageSize", pageSize);
        return "client/new-list";
    }

    @RequestMapping(value = "news-detail", method = RequestMethod.GET)
    public String detailNews(HttpSession session, int newsId, Model model) {
        boolean check = newsDAO.viewNews(newsId);
        News news = newsDAO.getNewsId(newsId);
        List<Admins> listAdmin = adminDAO.getAllAdmin();
        List<News> listNews = newsDAO.getAllNewsDetail();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();
        String navHtml = categoryDAO.generateNavbarHtml();
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }
        model.addAttribute("news", news);
        model.addAttribute("listNews", listNews);
        model.addAttribute("listAdmin", listAdmin);
        if (check) {
            return "client/new-detail";
        } else {
            return "client/new-detail";
        }
    }

    @RequestMapping(value = "binh-luan", method = RequestMethod.POST)
    public String commentProduct(HttpSession session, Model model, RedirectAttributes attributes, int productId, ProductComments productComments) {
        if (session.getAttribute("InfoCustomer") == null) {
            attributes.addFlashAttribute("error", "Vui lòng đăng nhập để tiếp tục");
            return "redirect:dang-nhap.htm";
        }
        Customers customers = (Customers) session.getAttribute("InfoCustomer");

        Products products = productDAO.getProductId(productId);
        productComments.setCustomers(customers);
        productComments.setProducts(products);
        productComments.setCommentTime(new Date());
        boolean check = productDAO.commentProduct(productComments);

        if (check) {
            return "redirect:san-pham/chi-tiet.htm?productId=" + productId;
        } else {
            return "redirect:san-pham/chi-tiet.htm?productId=" + productId;
        }
    }

    @RequestMapping(value = "tat-ca-san-pham", method = RequestMethod.GET)
    public String allProduct(HttpSession session, Model model) {
        Customers customers = (Customers) session.getAttribute("InfoCustomer");

        String keyword = "";

        if (session.getAttribute("productNameClient") != null) {
            keyword = (String) session.getAttribute("productNameClient");
        }
        //product by category
        List<Products> productAll = productDAO.getAllProduct();
        //product by brand
//        List<Products> productByBrand = productDAO.getProductByBrandShowForClient(brandId);
        //get brand sort product 
        List<Brands> brand = brandDAO.getAllBrandShowForClient();
        //navbar categories product
        String navHtml = categoryDAO.generateNavbarHtml();
        //navbar categories news
        String newsHtml = newsDAO.generateNewsHtml();

        List<ProductCommon> allProduct = new ArrayList<>();
        List<ProductCommon> proByBrand = new ArrayList<>();
        if (customers == null) {
            if (productAll.size() > 0) {
                for (Products pro : productAll) {
                    ProductCommon product = new ProductCommon(pro, null, null);
                    product.setIsNewProduct(productDAO.checkNewProduct(pro.getProductId()));
                    allProduct.add(product);
                }
            }
        } else {
            if (productAll.size() > 0) {
                for (Products pro : productAll) {
                    ProductCommon product = new ProductCommon(pro, null, null);
                    product.setIsNewProduct(productDAO.checkNewProduct(pro.getProductId()));
                    allProduct.add(product);
                }
            }
        }
        if (allProduct.size() > 0) {
            model.addAttribute("productAll", allProduct);
        }
        if (brand.size() > 0) {
            model.addAttribute("brand", brand);
        }
        if (navHtml.length() > 0) {
            model.addAttribute("navHtml", navHtml);
        }
        if (newsHtml.length() > 0) {
            model.addAttribute("newsHtml", newsHtml);
        }
        if (keyword.length() > 0) {
            model.addAttribute("keyword", keyword);
        }
        model.addAttribute("keyword", keyword);
        return "client/product-all";
    }
    
    @RequestMapping(value = "faqs", method = RequestMethod.GET)
    public String showFaqs(HttpSession session, RedirectAttributes attributes, Model model) {
        List<Faqs> listFaq = faqDAO.getAllFaqsShowForClient();
        model.addAttribute("listFaq", listFaq);
        return "client/faqs";
    }
    
    @RequestMapping(value = "introductions", method = RequestMethod.GET)
    public String showIntro(HttpSession session, RedirectAttributes attributes, Model model) {
        Introductions intro = introDAO.getIntroByStatus();
        model.addAttribute("intro", intro);
        return "client/about-us";
    }
}
