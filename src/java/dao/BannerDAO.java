/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Banners;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Kai
 */
public class BannerDAO {

    /**
     * This is used to get the data for administrators
     *
     * @return
     */
    //Get-all Banner
    public List<Banners> getAllBanner() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Banners> listBanner = session.createQuery("from Banners where bannerStatus = 1 or bannerStatus = 0").list();
            if (listBanner == null) {
                return null;
            }
            session.getTransaction().commit();
            return listBanner;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //Get Banner by ID
    public Banners getBannerById(int bannerId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Banners banner = null;
        try {
            Query query = session.createQuery("from Banners where bannerStatus = 1 and bannerId = :bannerId");
            query.setParameter("bannerId", bannerId);
            banner = (Banners) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return banner;
    }

    //Create Banner
    public boolean createBanner(Banners banner) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        long count = 0;
        try {
            List<Banners> bannerses = getAllBanner();
            count = (long) bannerses.size();
            banner.setBannerPiority((int) (count + 1));
            session.save(banner);
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

    //Update Banner
    public boolean updateBanner(Banners banner, int bannerPiority) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            banner.setBannerPiority(bannerPiority);
            session.update(banner);
            long count = bannerPiority;
            List<Banners> bannerses = getAllBanner();
            for (Banners banners : bannerses) {
                if (banners.getBannerPiority() >= bannerPiority && banner.getBannerId() != banners.getBannerId()) {
                    banners.setBannerPiority((int) ++count);
                    session.update(banners);
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

    //Delete Banner
    public boolean deleteBanner(int bannerId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        long count = 0;
        try {
            Banners banner = getBannerById(bannerId);
            banner.setBannerStatus(-1);
            session.update(banner);
            session.getTransaction().commit();
            session.beginTransaction();
            List<Banners> bannerses = getAllBanner();
            for (Banners banners : bannerses) {
                banners.setBannerPiority((int) ++count);
                session.update(banners);
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
     * This is used to get the data for Client
     *
     * @return
     */
    //Get 3 banner show for client
    public List<Banners> getAllBannerShowForClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Banners> listBanner = session.createQuery("from Banners where bannerStatus = 1 order by bannerPiority").setMaxResults(4).list();
            if (listBanner == null) {
                return null;
            }
            session.getTransaction().commit();
            return listBanner;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }
}
