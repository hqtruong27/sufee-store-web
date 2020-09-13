/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Customers;
import entity.Products;
import entity.Wishlists;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author ASUS
 */
public class WishlistDAO {

    public List<Wishlists> getAllWishlist() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Wishlists> wishlistses = session.createQuery("from Wishlists where wishlistStatus = 0 or wishlistStatus = 1").list();
            if (wishlistses == null) {
                return null;
            }
            session.getTransaction().commit();
            return wishlistses;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean createWishlist(Wishlists wishlists) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(wishlists);
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

    public List<Wishlists> getWishlistByCustomerId(int customerId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Wishlists> wishlistses = session.createQuery("from Wishlists where (wishlistStatus = 0 or wishlistStatus = 1) and customerId = :customerId order by wishlistId desc").setParameter("customerId", customerId).list();
            if (wishlistses == null) {
                return null;
            }
            session.getTransaction().commit();
            return wishlistses;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean removeWishlist(Customers customers, Products products) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean result = false;
        try {
            Query query = session.createQuery("delete from Wishlists where customerId = :customerId and productId = :productId");
            query.setParameter("customerId", customers);
            query.setParameter("productId", products);
            int  i = query.executeUpdate();
            
            if (i > 0) {
                result = true;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return  result;
    }

}
