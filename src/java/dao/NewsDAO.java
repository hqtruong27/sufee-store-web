package dao;

import entity.Catalogs;
import entity.News;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Hoang Truong
 */
public class NewsDAO {

    /**
     * This is used to get the data for administrators
     *
     * @return
     */
    //GET all Catalogs
    public List<Catalogs> getAllCatalogs() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Catalogs> catalogses = session.createQuery("from Catalogs where catalogStatus = 1 or catalogStatus = 0").list();
            if (catalogses == null) {
                return null;
            }
            session.getTransaction().commit();
            return catalogses;
        } catch (Exception e) {
            e.getMessage();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    //GET all Catalogs with parentId = 0
    public List<Catalogs> getAllCatalogsNoParent() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Catalogs> catalogses = session.createQuery("from Catalogs where (catalogStatus = 1 or catalogStatus = 0) and parentId = 0").list();
            if (catalogses == null) {
                return null;
            }
            session.getTransaction().commit();
            return catalogses;
        } catch (Exception e) {
            e.getMessage();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    //GET all Catalogs with parentId != 0
    public List<Catalogs> getAllParentCatalogs() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Catalogs> catalogses = session.createQuery("from Catalogs where (catalogStatus = 1 or catalogStatus = 0) and parentId != 0").list();
            if (catalogses == null) {
                return null;
            }
            session.getTransaction().commit();
            return catalogses;
        } catch (Exception e) {
            e.getMessage();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    //DAO/List Catalogs but not it self
    public List<Catalogs> getCatalogHasParentButNotSelf(int catalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Catalogs> list = session.createQuery("from Catalogs where (catalogStatus = 1 or catalogStatus = 0) and parentId = 0 and catalogId != :catalogId").setParameter("catalogId", catalogId).list();
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

    //DAO/count Catalog if Has Parent
    public long findCatalogHasParent(int catalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        long count = 0;
        try {
            Query query = session.createQuery("select count(*) from Catalogs where (catalogStatus = 1 or catalogStatus = 0) and parentId IN :catalogId");
            query.setParameter("catalogId", catalogId);
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

    //DAO/List Catalogs but not it self
    public Catalogs getCatalogId(int catalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Catalogs catalogs = (Catalogs) session.createQuery("from Catalogs where (catalogStatus = 1 or catalogStatus = 0) and catalogId = :catalogId ").setParameter("catalogId", catalogId).uniqueResult();
            if (catalogs == null) {
                return null;
            }
            session.getTransaction().commit();
            return catalogs;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean createCatalogs(Catalogs c) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Catalogs> catalogses = getAllCatalogsNoParent();
            List<Catalogs> parentCatalog = getAllParentCatalogs();
            int count = catalogses.size();
            int countParent = parentCatalog.size();
            if (c.getParentId() == 0) {
                c.setPiority(count + 1);
            }
            if (c.getParentId() != 0) {
                c.setPiority(countParent + 1);
            }
            session.persist(c);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    //DAO: Update/Categories
    public boolean updateCatalog(Catalogs c, int piority) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            c.setPiority(piority);
            session.update(c);
            List<Catalogs> listParent = getAllParentCatalogs();
            // disable subcategories if u disable category
            if (c.getCatalogStatus() == 0) {
                if (c.getParentId() == 0) {
                    for (Catalogs catalogs : listParent) {
                        if (catalogs.getParentId() == c.getCatalogId()) {
                            catalogs.setCatalogStatus(0);
                            session.merge(catalogs);
                        }
                    }
                }
            }
            session.getTransaction().commit();
            List<Catalogs> listCatalogNoParent = getAllCatalogsNoParent();
            int sortCate = 0;
            int count = c.getPiority();
            session.beginTransaction();
            if (c.getParentId() == 0) {
                for (Catalogs categories : listCatalogNoParent) {
                    if (categories.getPiority() >= piority && c.getCatalogId() != categories.getCatalogId()) {
                        categories.setPiority(++count);
                    }
                    session.merge(categories);
                }
                for (Catalogs catehasParent : listParent) {
                    catehasParent.setPiority(++sortCate);
                    session.merge(catehasParent);
                }
                session.getTransaction().commit();
            } else {
                for (Catalogs categories : listCatalogNoParent) {
                    categories.setPiority(++sortCate);
                    session.merge(categories);
                }
                for (Catalogs catehasParent : listParent) {
                    if (catehasParent.getPiority() >= piority && c.getCatalogId() != catehasParent.getCatalogId()) {
                        catehasParent.setPiority(++count);
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
    public boolean deleteCatalog(int catalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Catalogs c = getCatalogId(catalogId);
            c.setCatalogStatus(-1);
            session.update(c);
            session.getTransaction().commit();
            List<Catalogs> listCatalogNoParent = getAllCatalogsNoParent();
            int sortCate = 0;
            int sortParent = 0;
            session.beginTransaction();
            
            List<Catalogs> listParent = getAllParentCatalogs();
            // delete subcategories if u delete category
            if (c.getParentId() == 0) {
                for (Catalogs catalogs : listParent) {
                    if (catalogs.getParentId() == c.getCatalogId()) {
                        catalogs.setCatalogStatus(-1);
                        session.merge(catalogs);
                    }
                }
            }
            //sort categories
            for (Catalogs categories : listCatalogNoParent) {
                categories.setPiority(++sortCate);
                session.merge(categories);
            }
            for (Catalogs catehasParent : listParent) {
                catehasParent.setPiority(++sortParent);
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

    public List<News> getAllNews(Integer start, Integer limit) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<News> listNew = session.createQuery("from News where (newStatus = 1 or newStatus = 0 ) order by newsId desc").setFirstResult(start).setMaxResults(limit).list();
            if (listNew == null) {
                return null;
            }
            session.getTransaction().commit();
            return listNew;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public List<News> getAllNewsAdmin() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<News> listNew = session.createQuery("from News where (newStatus = 1 or newStatus = 0 ) order by newsId desc").list();
            if (listNew == null) {
                return null;
            }
            session.getTransaction().commit();
            return listNew;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public News getNewsId(int newsId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            News news = (News) session.createQuery("from News where (newStatus = 1 or newStatus = 0) and newsId =:newsId").setParameter("newsId", newsId).uniqueResult();
            if (news == null) {
                return null;
            }
            session.getTransaction().commit();
            return news;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean createNews(News news) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Date date = Calendar.getInstance().getTime();
            news.setCreateDate(date);
            session.save(news);
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
    //GET all Catalogs
    public List<Catalogs> getAllCatalogsShowClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Catalogs> catalogses = session.createQuery("from Catalogs where catalogStatus = 1  and parentId = 0 order by piority").list();
            if (catalogses == null) {
                return null;
            }
            session.getTransaction().commit();
            return catalogses;
        } catch (Exception e) {
            e.getMessage();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Catalogs> getCatalogParentShowClient(int catalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Catalogs> catalogses = session.createQuery("from Catalogs where catalogStatus = 1 and parentId != 0 and parentId = :catalogId").setParameter("catalogId", catalogId).list();
            if (catalogses == null) {
                return null;
            }
            session.getTransaction().commit();
            return catalogses;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public List<News> getAllChildrenCatalogsByParentIdFrontEnd(Integer catalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<News> news = new ArrayList<>();
        try {
            Query query = session.createQuery("from News where newStatus = 1 and catalogId = :catalogId");
            query.setParameter("catalogId", catalogId);
            news = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }

        return news;
    }

    public List<News> getAllNewsByCatalogId(int catalogId, Integer start, Integer limit) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<News> news = new ArrayList<>();
        List<News> collections;
        try {
            Query query = session.createQuery("from News where newStatus = 1 and catalogId = :catalogId");
            query.setParameter("catalogId", catalogId);
            collections = query.list();
//            query = session.createQuery("from News where newStatus = 1 and catalogId = :catalogId");
//            query.setParameter("catalogId", catalogId);
            Stream<News> stream = collections.stream();
            news = stream.skip(start).limit(limit).collect(Collectors.toList());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return news;
    }

    public News getNewsByCatalogId(int catalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            News news = (News) session.createQuery("from News where newStatus = 1 and catalogId = :catalogId or parentId = :catalogId").setParameter("catalogId", catalogId).uniqueResult();
            if (news == null) {
                return null;
            }
            session.getTransaction().commit();
            return news;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public List<News> getAllNewsDetail() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<News> listNew = session.createQuery("from News  where newStatus = 1  order by newsId desc").setMaxResults(3).list();
            if (listNew == null) {
                return null;
            }
            session.getTransaction().commit();
            return listNew;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public List<News> getAllNewsHotByCatalogId(int catalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<News> listNews = session.createQuery("from News where newStatus = 1 and catalogId = :catalogId order by newsId Desc").setParameter("catalogId", catalogId).setMaxResults(1).list();
            if (listNews == null) {
                return null;
            }
            session.getTransaction().commit();
            return listNews;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean viewNews(int newsId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            News news = getNewsId(newsId);
            int countView = news.getCountView();
            news.setCountView(++countView);
            session.update(news);
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

    public Integer countAllNewsByCatalogId(Integer catalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Integer count = 0;

        try {
            Query query = session.createQuery("from News where newStatus = 1 and catalogId = :catalogId");
            Catalogs c = getCatalogId(catalogId);
            query.setParameter("catalogId", c);
            List list = query.list();
            count = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }

        return count;
    }

    public Integer countAllNewsFrontEnd() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Integer count = 0;

        try {
            List list = session.createQuery("from News where newStatus = 1").list();
            count = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }

        return count;
    }

//    public Integer countAllNewsByCatalogIdFrontEnd(Integer catalogId) {
//        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        Integer count = 0;
//
//        try {
//            Query query = session.createQuery("from News where newStatus = 1 and catalogId = :catalogId");
//            Catalogs c = getCatalogId(catalogId);
//            query.setParameter("catalogId", c);
//            List<News> news = query.list();
//            List<Catalogs> catalogs = getAllChildrenCatalogsByParentIdFrontEnd(catalogId);
//
//            for (Catalogs ct : catalogs) {
//                query = session.createQuery("from News where newStatus = 1 and catalogId = :catalogId");
//                query.setParameter("catalogId", ct);
//                news.addAll(query.list());
//            }
//
//            count = news.size();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//            e.getMessage();
//        } finally {
//            session.close();
//        }
//
//         return count;
//    }

    public List<News> sortByCountView() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<News> listNew = session.createQuery("from News  where newStatus = 1  order by countView desc").setMaxResults(4).list();
            if (listNew == null) {
                return null;
            }
            session.getTransaction().commit();
            return listNew;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * Display catalog cate for client
     *
     * @return
     */
    public String generateNewsHtml() {
        String html = "";
        List<Catalogs> catalogs = getAllCatalogsShowClient();

        for (Catalogs c : catalogs) {
            html += "<div class='col-md-4'>";
            html += "<ul class='list-links'>";
            html += "<li>";
            html += "<h3 class='list-links-title'><a href='/Sufee_Store/news-list-catalog.htm?catalogId=" + c.getCatalogId() + "'>" + c.getCatalogName() + "</a></h3>";
            html += "</li>";

            List<Catalogs> children = getCatalogParentShowClient(c.getCatalogId());
            for (Catalogs ct : children) {
                html += "<li><a href='/Sufee_Store/news-list-catalog.htm?catalogId=" + ct.getCatalogId() + "'>" + ct.getCatalogName() + "</a></li>";
            }
            html += "</ul>";
            html += "<hr class='hidden-md hidden-lg'>";
            html += "</div>";
        }
        return html;
    }
}
