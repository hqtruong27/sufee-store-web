package dao;

import entity.Categories;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Hoang Truong
 */
public class CategoryDAO {

    /**
     * This is used to get the data for administrators
     *
     * @return
     */
    //DAO/List Categories 
    public List<Categories> fullCategories() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Categories> list = session.createQuery("from Categories where (categoryStatus = 1 or categoryStatus = 0)").list();
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

    //DAO/List Categories with status = 1 and 0 and parentId = 0
    public List<Categories> getAllCategories() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Categories> list = session.createQuery("from Categories where (categoryStatus = 1 or categoryStatus = 0) and parentId = 0 order by categoryId desc").list();
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

    //DAO/List Categories get categories has parent and categories no parent
    public List<Categories> getCategoriesParent(int categoryId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Categories> list = session.createQuery("from Categories where categoryStatus = 1 and parentId != 0 and parentId = :categoryId").setParameter("categoryId", categoryId).list();
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

    //DAO/List Get All Parent Categories
    public List<Categories> getAllParentCategories() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Categories> categoriesHasParent = session.createQuery("from Categories where (categoryStatus = 1 or categoryStatus = 0) and parentId != 0 order by categoryPiority").list();
            if (categoriesHasParent == null) {
                return null;
            }
            session.getTransaction().commit();
            return categoriesHasParent;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //DAO/List Categories but not it self
    public List<Categories> getParentIdNotSelf(int categoryId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Categories> list = session.createQuery("from Categories where (categoryStatus = 1 or categoryStatus = 0) and parentId = 0 and categoryId != :categoryId").setParameter("categoryId", categoryId).list();
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

    //DAO/Find Categories Has Parent
    public long findCateHasParent(int categoryId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        long count = 0;
        try {
            Query query = session.createQuery("select count(*) from Categories where (categoryStatus = 1 or categoryStatus = 0) and parentId IN :categoryId");
            query.setParameter("categoryId", categoryId);
            count = (long) query.uniqueResult();
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return count;
    }

    //DAO/Get id Categories
    public Categories getId(int categoryId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Categories categories = (Categories) session.createQuery("from Categories where (categoryStatus = 1 or categoryStatus = 0) and categoryId =:categoryId").setParameter("categoryId", categoryId).uniqueResult();
            if (categories == null) {
                return null;
            }
            session.getTransaction().commit();
            return categories;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //DAO/Create Categories
    public boolean createCategories(Categories c) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        long count = 0;
        long countHasParent = 0;
        try {
            if (c.getParentId() == 0) {
                Query query = session.createQuery("select count(categoryId) from Categories where (categoryStatus = 1 or categoryStatus = 0)and parentId = 0");
                count = (long) query.uniqueResult();
                c.setCategoryPiority((int) (count + 1));
            } else {
                Query queryHasParent = session.createQuery("select count(categoryId) from Categories where (categoryStatus = 1 or categoryStatus = 0) and parentId != 0");
                countHasParent = (long) queryHasParent.uniqueResult();
                c.setCategoryPiority((int) (countHasParent + 1));
                List<Categories> getParentName = getAllCategories();
                for (Categories categories : getParentName) {
                    if (c.getParentId() == categories.getCategoryId()) {
                        c.setParentName(categories.getCategoryName());
                    }
                }
            }
            session.save(c);
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

    //DAO: Update/Categories
    public boolean updateCategories(Categories c, int categoryPiority) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Categories> listCategories = getAllCategories();
             if (c.getParentId() != 0) {
                for (Categories categories : listCategories) {
                    if (c.getParentId() == categories.getCategoryId()) {
                        c.setParentName(categories.getCategoryName());
                    }
                }
            }
            c.setCategoryPiority(categoryPiority);
            session.update(c);
            session.getTransaction().commit();
            List<Categories> listParent = getAllParentCategories();
            // delete subcategories if u delete category
            if (c.getCategoryStatus() == 0) {
                if (c.getParentId() == 0) {
                    for (Categories categories : listParent) {
                        if (categories.getParentId() == c.getCategoryId()) {
                            categories.setCategoryStatus(0);
                            session.merge(categories);
                            session.getTransaction().commit();
                        }
                    }
                }
            }
            List<Categories> listCate = getAllCategories();
            session.beginTransaction();
            int sortCate = 0;
            int count = c.getCategoryPiority();
            if (c.getParentId() == 0) {
                for (Categories categories : listCate) {
                    if (categories.getCategoryPiority() >= categoryPiority && c.getCategoryId() != categories.getCategoryId()) {
                        categories.setCategoryPiority(++count);
                    }
                    session.merge(categories);
                }
                for (Categories catehasParent : listParent) {
                    catehasParent.setCategoryPiority(++sortCate);
                    session.merge(catehasParent);
                }
                session.getTransaction().commit();
            } else {
                for (Categories categories : listCate) {
                    categories.setCategoryPiority(++sortCate);
                    session.merge(categories);
                }
                for (Categories catehasParent : listParent) {
                    if (catehasParent.getCategoryPiority() >= categoryPiority && c.getCategoryId() != catehasParent.getCategoryId()) {
                        catehasParent.setCategoryPiority(++count);
                    }
                    session.merge(catehasParent);
                }
                session.getTransaction().commit();
            }
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        } finally {
            session.close();
        }
    }

    //Delete Categories with change status = -1
    public boolean deleteCategories(int categoryId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Categories c = getId(categoryId);
            c.setCategoryStatus(-1);
            session.update(c);
            session.getTransaction().commit();
            List<Categories> listCate = getAllCategories();
            int sortCate = 0;
            int sortParent = 0;
            
            session.beginTransaction();
            List<Categories> listCateHasParent = getAllParentCategories();
            // delete subcategories if u delete category
            if (c.getParentId() == 0) {
                for (Categories categories : listCateHasParent) {
                    if (categories.getParentId() == c.getCategoryId()) {
                        categories.setCategoryStatus(-1);
                        session.merge(categories);
                    }
                }
            }
            //sort categories
            for (Categories categories : listCate) {
                categories.setCategoryPiority(++sortCate);
                session.merge(categories);
            }
            for (Categories catehasParent : listCateHasParent) {
                catehasParent.setCategoryPiority(++sortParent);
                session.merge(catehasParent);
            }
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
     * This is used to get the data For Client
     *
     * @return The RSS data
     */
    //DAO/List Categories with status = 1 and 0 and parentId = 0
    public List<Categories> getAllCategoriesShowClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Categories> list = session.createQuery("from Categories where categoryStatus = 1 and parentId = 0 order by categoryPiority").list();
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

    //DAO/List Get All Parent Categories
    public List<Categories> getAllParentCategoriesShowClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Categories> categoriesHasParent = session.createQuery("from Categories where categoryStatus = 1 and parentId != 0 order by categoryPiority").list();
            if (categoriesHasParent == null) {
                return null;
            }
            session.getTransaction().commit();
            return categoriesHasParent;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //DAO/List Categories get categories has parent and categories no parent
    public List<Categories> getCategoriesParentShowClient(int categoryId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Categories> list = session.createQuery("from Categories where categoryStatus = 1 and parentId != 0 and parentId = :categoryId").setParameter("categoryId", categoryId).list();
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

    //ParentCategory show client
    public Boolean isParentCategoryShowClient(int categoryId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Boolean result = false;
        try {
            List<Categories> categories = session.createQuery("from Categories where categoryStatus = 1 and parentId = :categoryId").setParameter("categoryId", categoryId).list();
            session.getTransaction().commit();
            if (categories.size() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.getMessage();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return result;
    }

    /**
     * Display html Category for client
     *
     * @return
     */
    public String generateNavbarHtml() {
        String html = "";
        List<Categories> categories = getAllCategoriesShowClient();
        for (Categories c : categories) {
            if (isParentCategoryShowClient(c.getCategoryId())) {
                html += "<li class='dropdown side-dropdown'>";
                html += "<a class='dropdown-toggle' data-toggle='dropdown' aria-expanded='true'>" + c.getCategoryName() + "<i class='fa fa-angle-right'></i></a>";
                html += "<div class='custom-menu'>";
                html += "<div class='row'>";
                List<Categories> children = getCategoriesParentShowClient(c.getCategoryId());
                for (Categories ct : children) {
                    html += "<div class='col-md-4'>";
                    html += "<ul class='list-links'>";
                    html += "<li>";
                    html += "<h3 class='list-links-title'><a href='/Sufee_Store/san-pham.htm?categoryId=" + ct.getCategoryId() + "'>" + ct.getCategoryName() + "</a></h3>";
                    html += "</li>";
                    html += "</ul>";
                    html += "<hr>";
                    html += "<hr class='hidden-md hidden-lg'>";
                    html += "</div>";
                }
                html += "</div>";
                html += "</div>";
                html += "</li>";
            } else {
                html += "<li><a href='/Sufee_Store/san-pham.htm?categoryId=" + c.getCategoryId() + "'>" + c.getCategoryName() + "</a></li>";
            }
        }
        return html;
    }
}
