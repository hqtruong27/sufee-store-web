package dao;

import entity.Brands;
import java.util.List;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Kai
 */
public class BrandDAO {

    /**
     * This is used to get the data for administrators
     *
     * @return
     */
    //Get all Brand with Status == 1 or status == 0
    public List<Brands> getAllBrands() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Brands> list = session.createQuery("from Brands where (brandStatus = 1 or brandStatus = 0) order by brandPiority").list();
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

    //Get id Brand with Status == 1 or status == 0
    public Brands getBrandId(int brandId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Brands brands = (Brands) session.createQuery("from Brands where (brandStatus = 1 or brandStatus = 0) and brandId =:brandId").setParameter("brandId", brandId).uniqueResult();
            if (brands == null) {
                return null;
            }
            session.getTransaction().commit();
            return brands;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean createBrands(Brands brand) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        long count = 0;
        try {
            List<Brands> brands = getAllBrands();
            count = brands.size();
            brand.setBrandPiority((int) (count + 1));
            session.save(brand);
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

    public boolean updateBrand(Brands brand, int brandPiority) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            brand.setBrandPiority(brandPiority);
            session.update(brand);
            List<Brands> brands = getAllBrands();
            long count = brandPiority;
            for (Brands br : brands) {
                if (br.getBrandPiority() >= brandPiority && brand.getBrandId() != br.getBrandId()) {
                    br.setBrandPiority((int) ++count);
                    session.update(br);
                }
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

    public boolean deleteBrand(int brandId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Brands brand = getBrandId(brandId);
            brand.setBrandStatus(-1);
            session.update(brand);
            session.getTransaction().commit();
            List<Brands> brands = getAllBrands();
            long count = 0;
            for (Brands br : brands) {
                br.setBrandPiority((int) ++count);
                session.update(br);
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

    /**
     * This is used to get the data for Client
     *
     * @return
     */
    //Get 10 Brand Show for Client
    public List<Brands> getAllBrandShowForClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Brands> list = session.createQuery("from Brands where brandStatus = 1 order by brandPiority").setMaxResults(10).list();
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
    
    public int countBrand() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int countBrand = 0;
        try {
            List list = session.createQuery("from Brands where brandStatus = 1").list();
            countBrand = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return countBrand;
    }
    
    public boolean checkBrandNameExists(String brandName) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        try {
            Brands brands = (Brands) session.createQuery("from Brands where brandName = :brandName").setParameter("brandName", brandName).uniqueResult();
            session.getTransaction().commit();
            if (brands != null) {
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
    public boolean checkBrandIdExists(int brandId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        try {
            Brands brands = (Brands) session.createQuery("from Brands where brandId != :brandId").setParameter("brandId", brandId).uniqueResult();
            session.getTransaction().commit();
            if (brands != null) {
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
