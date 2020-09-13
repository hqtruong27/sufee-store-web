package dao;

import common.ProductFilter;
import entity.Brands;
import entity.Categories;
import entity.ProductComments;
import entity.Products;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Hoang Truong
 */
public class ProductDAO {

    public final BrandDAO brandDAO = new BrandDAO();
    public final CategoryDAO categoryDAO = new CategoryDAO();

    /**
     * This is used to get the data for administrators
     *
     * @return
     */
    //Get all Product with Status == 1 or status == 0
    public List<Products> getAllProduct() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Products> list = session.createQuery("from Products where (productStatus = 0 or productStatus = 1 or productStatus = 2) order by productId desc").list();
            if (list == null) {
                return null;
            }
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Get Product id with Status == 1 or status == 0
    public Products getProductId(int productId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Products products = (Products) session.createQuery("from Products where (productStatus = 0 or productStatus = 1 or productStatus = 2) and productId =:productId").setParameter("productId", productId).uniqueResult();
            if (products == null) {
                return null;
            }
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //DAO/Create Product
    public boolean createProduct(Products p) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.persist(p);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * This is used to get the data for Client
     *
     * @return
     */
    //Get 4 Product news  with Status == 1 show product new
    public List<Products> getFourNewProductShowForClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Products> list = session.createQuery("from Products where productStatus = 1 or productStatus = 2 order by productId desc").setMaxResults(4).list();
            if (list == null) {
                return null;
            }
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Get Product by Category  with Status = 1&2
    public List<Products> getProductByCategoryShowForClient(int categoryId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Products> list = session.createQuery("from Products where (productStatus = 1 or productStatus = 2) and categoryId = :categoryId")
                    .setParameter("categoryId", categoryId).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Get Product by brand  with Status = 1&2
    public List<Products> getProductByBrandShowForClient(int brandId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Products> list = session.createQuery("from Products where (productStatus = 1 or productStatus = 2) and brandId = :brandId")
                    .setParameter("brandId", brandId).list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Get 4 Product news  with Status == 1 show product new
    public List<Products> getFourRelatedProductShowForClient(int categoryId, int productId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Products> list = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId")
                    .setParameter("categoryId", categoryId).setMaxResults(4).list();
            Products product = getProductId(productId);
            for (Products pro : list) {
                if (product.getProductId() == pro.getProductId()) {
                    list.remove(pro);
                    break;
                }
            }
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Select 50 product new where id
    public boolean checkNewProduct(int productId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean bl = false;
        try {
            List<Products> list = session.createQuery("from Products where productStatus = 1 or productStatus = 2 order by productId desc").setMaxResults(20).list();
            Products products = getProductId(productId);
            for (Products pro : list) {
                if (Objects.equals(products.getProductId(), pro.getProductId())) {
                    bl = true;
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        } finally {
            session.close();
        }
        return bl;
    }

    //Get top 1 Product sale price with Status == 1
    public Products getBestSaleProduct() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Products product = (Products) session.createQuery("from Products where productStatus = 1 or productStatus = 2 order by productSale desc").setMaxResults(1).list().get(0);
            session.getTransaction().commit();
            return product;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Get top 1 Product best sell with Status == 1
    public Products getBestSellProduct() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Products product = (Products) session.createQuery("from Products where productStatus = 1 or productStatus = 2 and saleQuantity > 0 order by saleQuantity desc").setMaxResults(1).list().get(0);
            session.getTransaction().commit();
            return product;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Get 12 Product sell high quantity  with Status == 1 show client 
    public List<Products> get12SellQuantityProductShowForClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Products> products = session.createQuery("from Products where productStatus = 1 or productStatus = 2 and saleQuantity > 0 order by saleQuantity desc").setMaxResults(13).list();
            Products pro = getBestSellProduct();
            for (Products p : products) {
                if (Objects.equals(p.getProductId(), pro.getProductId())) {
                    products.remove(p);
                    break;
                }
            }
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Get 12 Product sale price with Status == 1 show client 
    public List<Products> get12SalePriceProductShowForClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Products> products = session.createQuery("from Products where productStatus = 1 or productStatus = 2 order by productSale desc").setMaxResults(13).list();
            Products pro = getBestSaleProduct();
            for (Products p : products) {
                if (Objects.equals(p.getProductId(), pro.getProductId())) {
                    products.remove(p);
                    break;
                }
            }
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Get 12 Product sale price with Status == 1 show client 
    public List<Products> get12ProductForYouShowForClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Products> products = session.createQuery("from Products where productStatus = 1 or productStatus = 2 order by productId desc").setMaxResults(10).list();
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Product Comment
    public boolean commentProduct(ProductComments productComments) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(productComments);
            session.getTransaction().commit();
            //
            session.beginTransaction();
            Products product = getProductId(productComments.getProducts().getProductId());
            float stars = (float) (product.getStarAvg() + productComments.getCommentRate())/2;
            product.setStarAvg(stars);
            session.update(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        } finally {
            session.close();
        }
    }

    public List<ProductComments> getAllCommentByProductId(int productId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<ProductComments> productComments = session.createQuery("from ProductComments where productId = :productId").setParameter("productId", productId).list();
            if (productComments == null) {
                return null;
            }
            session.getTransaction().commit();
            return productComments;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public ProductFilter filterProductsFrontEnd(Integer start, Integer limit, Brands brandId, Categories categoryId, String productName, Integer sort) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProductFilter products = null;
        Query query;
        List<Products> collections;

        if (sort == null || sort <= 0 || sort > 7) {
            sort = 1;
        }

        try {
            if (brandId == null && categoryId == null && "".equals(productName)) {
                query = session.createQuery("from Products where productStatus = 1");
                collections = query.list();
            } else if (brandId != null && categoryId == null && "".equals(productName)) {
                query = session.createQuery("from Products where productStatus = 1 and brandId = :brandId");
                query.setParameter("brandId", brandId);
                collections = query.list();
            } else if (brandId == null && categoryId != null && "".equals(productName)) {
                List<Categories> cas = session.createQuery("from Categories where parentId = :categoryId and categoryStatus = 1").setParameter("categoryId", categoryId.getCategoryId()).list();
                query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                query.setParameter("categoryId", categoryId);
                collections = query.list();

                for (Categories c : cas) {
                    query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                    query.setParameter("categoryId", c);
                    collections.addAll(query.list());
                }
            } else if (brandId == null && categoryId == null && !"".equals(productName)) {
                query = session.createQuery("from Products where productStatus = 1 and productName like :productName");
                query.setParameter("productName", "%" + productName + "%");
                collections = query.list();
            } else if (brandId != null && categoryId != null && "".equals(productName)) {
                List<Categories> cas = session.createQuery("from Categories where parentId = :categoryId and categoryStatus = 1").setParameter("categoryId", categoryId.getCategoryId()).list();
                query = session.createQuery("from Products where productStatus = 1 and brandId = :brandId and categoryId = :categoryId");
                query.setParameter("brandId", brandId);
                query.setParameter("categoryId", categoryId);
                collections = query.list();

                for (Categories c : cas) {
                    query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                    query.setParameter("categoryId", c);
                    collections.addAll(query.list());
                }
            } else if (brandId == null && categoryId != null && !"".equals(productName)) {
                List<Categories> cas = session.createQuery("from Categories where parentId = :categoryId and categoryStatus = 1").setParameter("categoryId", categoryId.getCategoryId()).list();
                query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId and productName like :productName");
                query.setParameter("categoryId", categoryId);
                query.setParameter("productName", "%" + productName + "%");
                collections = query.list();

                for (Categories c : cas) {
                    query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                    query.setParameter("categoryId", c);
                    collections.addAll(query.list());
                }
            } else if (brandId != null && categoryId == null && !"".equals(productName)) {
                query = session.createQuery("from Products where productStatus = 1 and brandId = :brandId and productName like :productName");
                query.setParameter("brandId", brandId);
                query.setParameter("productName", "%" + productName + "%");
                collections = query.list();
            } else {
                List<Categories> cas = session.createQuery("from Categories where parentId = :categoryId and categoryStatus = 1").setParameter("categoryId", categoryId.getCategoryId()).list();
                query = session.createQuery("from Products where productStatus = 1 and brandId = :brandId and categoryId = :categoryId and productName like :productName");
                query.setParameter("brandId", brandId);
                query.setParameter("categoryId", categoryId);
                query.setParameter("productName", "%" + productName + "%");
                collections = query.list();

                for (Categories c : cas) {
                    query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                    query.setParameter("categoryId", c);
                    collections.addAll(query.list());
                }
            }

            Stream<Products> stream = collections.stream();
            List<Products> productListOnly;
            switch (sort) {
                case 2:
                    productListOnly = stream.sorted(Comparator.comparing(Products::getPrice).reversed()).collect(Collectors.toList());
                    break;
                case 3:
                    productListOnly = stream.sorted(Comparator.comparing(Products::getStarAvg)).collect(Collectors.toList());
                    break;
                case 4:
                    productListOnly = stream.sorted(Comparator.comparing(Products::getStarAvg).reversed()).collect(Collectors.toList());
                    break;
                case 5:
                    productListOnly = stream.sorted(Comparator.comparing(Products::getSaleQuantity)).collect(Collectors.toList());
                    break;
                case 6:
                    productListOnly = stream.sorted(Comparator.comparing(Products::getSaleQuantity).reversed()).collect(Collectors.toList());
                    break;
                case 7:
                    productListOnly = stream.sorted(Comparator.comparing(Products::getProductId).reversed()).collect(Collectors.toList());
                    break;
                default:
                    productListOnly = stream.sorted(Comparator.comparing(Products::getPrice)).collect(Collectors.toList());
                    break;
            }

            List<Brands> brands = new ArrayList<>();
            List<Categories> categories = new ArrayList<>();

            if (brandId == null && categoryId == null) {
                brands = brandDAO.getAllBrandShowForClient();
                categories = categoryDAO.getAllParentCategoriesShowClient();
            } else if (brandId != null && categoryId == null) {
                brands.add(brandId);

                for (Products p : collections) {
                    if (p.getCategories().getParentId() == 0) {
                        categories.add(p.getCategories());
                    }

                    if (p.getCategories().getParentId() != 0) {
                        query = session.createQuery("from Categories where categoryId = :categoryId");
                        query.setParameter("categoryId", p.getCategories().getParentId());
                        Categories cat = (Categories) query.uniqueResult();

                        if (cat.getParentId() == 0 && !Objects.equals(p.getCategories().getCategoryId(), cat.getCategoryId())) {
                            categories.add(cat);
                        }
                    }
                }
            } else if (brandId == null && categoryId != null) {
                categories.add(categoryId);
                categories.addAll(categoryDAO.getCategoriesParentShowClient(categoryId.getCategoryId()));

                for (Products p : collections) {
                    brands.add(p.getBrands());
                }
            } else {
                brands.add(brandId);
                categories.add(categoryId);
                categories.addAll(categoryDAO.getCategoriesParentShowClient(categoryId.getCategoryId()));
            }

            categories = categories.stream().distinct().collect(Collectors.toList());
            brands = brands.stream().distinct().collect(Collectors.toList());
            productListOnly = productListOnly.stream().skip(start).limit(limit).collect(Collectors.toList());
            products = new ProductFilter(productListOnly, brands, categories);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("dao.impl.ProductDAOImpl.filterProductsFrontEnd()");
            e.getMessage();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return products;
    }

    public Integer countProductFilterForPaging(Brands brandId, Categories categoryId, String productName) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Integer count = 0;
        Query query;
        List<Products> collections;
        try {
            if (brandId == null && categoryId == null && "".equals(productName)) {
                query = session.createQuery("from Products where productStatus = 1");
                collections = query.list();
            } else if (brandId != null && categoryId == null && "".equals(productName)) {
                query = session.createQuery("from Products where productStatus = 1 and brandId = :brandId");
                query.setParameter("brandId", brandId);
                collections = query.list();
            } else if (brandId == null && categoryId != null && "".equals(productName)) {
                List<Categories> cas = session.createQuery("from Categories where parentId = :categoryId").setParameter("categoryId", categoryId.getCategoryId()).list();
                query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                query.setParameter("categoryId", categoryId);
                collections = query.list();

                for (Categories c : cas) {
                    query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                    query.setParameter("categoryId", c);
                    collections.addAll(query.list());
                }
            } else if (brandId == null && categoryId == null && !"".equals(productName)) {
                query = session.createQuery("from Products where productStatus = 1 and productName like :productName");
                query.setParameter("productName", "%" + productName + "%");
                collections = query.list();
            } else if (brandId != null && categoryId != null && "".equals(productName)) {
                List<Categories> cas = session.createQuery("from Categories where parentId = :categoryId").setParameter("categoryId", categoryId.getCategoryId()).list();
                query = session.createQuery("from Products where productStatus = 1 and brandId = :brandId and categoryId = :categoryId");
                query.setParameter("brandId", brandId);
                query.setParameter("categoryId", categoryId);
                collections = query.list();

                for (Categories c : cas) {
                    query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                    query.setParameter("categoryId", c);
                    collections.addAll(query.list());
                }
            } else if (brandId == null && categoryId != null && !"".equals(productName)) {
                List<Categories> cas = session.createQuery("from Categories where parentId = :categoryId").setParameter("categoryId", categoryId.getCategoryId()).list();
                query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId and productName like :productName");
                query.setParameter("categoryId", categoryId);
                query.setParameter("productName", "%" + productName + "%");
                collections = query.list();

                for (Categories c : cas) {
                    query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                    query.setParameter("categoryId", c);
                    collections.addAll(query.list());
                }
            } else if (brandId != null && categoryId == null && !"".equals(productName)) {
                query = session.createQuery("from Products where productStatus = 1 and brandId = :brandId and productName like :productName");
                query.setParameter("brandId", brandId);
                query.setParameter("productName", "%" + productName + "%");
                collections = query.list();
            } else {
                List<Categories> cas = session.createQuery("from Categories where parentId = :categoryId").setParameter("categoryId", categoryId.getCategoryId()).list();
                query = session.createQuery("from Products where productStatus = 1 and brandId = :brandId and categoryId = :categoryId and productName like :productName");
                query.setParameter("brandId", brandId);
                query.setParameter("categoryId", categoryId);
                query.setParameter("productName", "%" + productName + "%");
                collections = query.list();

                for (Categories c : cas) {
                    query = session.createQuery("from Products where productStatus = 1 and categoryId = :categoryId");
                    query.setParameter("categoryId", c);
                    collections.addAll(query.list());
                }
            }

            count = collections.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return count;
    }

    public int countProduct() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int countProduct = 0;
        try {
            List list = session.createQuery("from Products where productStatus = 1 or productStatus = 0").list();
            countProduct = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return countProduct;
    }
    public int countProductComment(int productId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int countProductCmt = 0;
        try {
            List list = session.createQuery("from ProductComments where productId = :productId").setParameter("productId", productId).list();
            countProductCmt = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return countProductCmt;
    }
    
    public boolean checkProductCodeExists(String productCode) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        try {
            Products products = (Products) session.createQuery("from Products where productCode = :productCode").setParameter("productCode", productCode).uniqueResult();
            session.getTransaction().commit();
            if (products != null) {
                check = true;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return check;
    }
}
