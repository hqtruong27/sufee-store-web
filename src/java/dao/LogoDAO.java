/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Logo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Kai
 */
public class LogoDAO {

    public List<Logo> getAllLogo() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Logo> listLogo = session.createQuery("from Logo where logoStatus = 1 or logoStatus = 0").list();
            if (listLogo == null) {
                return null;
            }
            session.getTransaction().commit();
            return listLogo;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Logo getLogoById(int logoId){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Logo logo = null;
        try {
            Query query = session.createQuery("from Logo where logoId= :logoId");
            query.setParameter("logoId", logoId);
            logo = (Logo) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        }finally{
            session.close();
        }
        return logo;
    }
    
    public boolean createLogo(Logo logo){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        try {
            List<Logo> listLogos = getAllLogo();
            if (listLogos!= null) {
                session.beginTransaction();
                for (Logo listLogo : listLogos) {
                    if (listLogo.getLogoStatus() == 1) {
                        listLogo.setLogoStatus(0);
                    }
                    session.merge(listLogo);
                }
                session.getTransaction().commit();
            }
            session.beginTransaction();
            session.save(logo);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        }finally{
            session.close();
        }
    }
    
    public boolean updateLogo(Logo logo){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(logo);
            session.getTransaction().commit();
            //
            List<Logo> listContact = getAllLogo();
            session.beginTransaction();
            for (Logo logos : listContact) {
                if (logos.getLogoStatus() == 1 && logo.getLogoId() != logos.getLogoId()) {
                    logos.setLogoStatus(0);
                }
                session.merge(logos);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        }finally{
            session.close();
        }
    }
    
    public boolean deleteLogo(int logoId){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Logo logo = getLogoById(logoId);
            logo.setLogoStatus(-1);
            session.update(logo);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        }finally{
            session.close();
        }
    }
    
    public Logo getLogoByStatus() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Logo logo = null;
        try {
            Query query = session.createQuery("from Logo where logoStatus = 1");
            logo = (Logo) query.uniqueResult();
            session.getTransaction().commit();
            return logo;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }
}
