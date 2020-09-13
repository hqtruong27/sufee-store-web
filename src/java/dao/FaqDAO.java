/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Faqs;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Kai
 */
public class FaqDAO {

    public List<Faqs> getAllFaqs() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Faqs> listFaq = session.createQuery("from Faqs where faqstatus = 1 or faqstatus = 0").list();
            if (listFaq == null) {
                return null;
            }
            session.getTransaction().commit();
            return listFaq;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public Faqs getFaqById(int faqid) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Faqs faq = null;
        try {
            Query query = session.createQuery("from Faqs where faqid = :faqid");
            query.setParameter("faqid", faqid);
            faq = (Faqs) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return faq;
    }
    
    public boolean createFaq(Faqs faq){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(faq);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        }finally {
            session.close();
        }
    }
    
    public boolean updateFaq(Faqs faq){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(faq);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        }finally {
            session.close();
        }
    }
    
    public boolean deleteFaq(int faqid){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Faqs faq = getFaqById(faqid);
            faq.setFaqstatus(-1);
            session.update(faq);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return false;
        }finally {
            session.close();
        }
    }
    
    public List<Faqs> getAllFaqsShowForClient() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Faqs> listFaq = session.createQuery("from Faqs where faqstatus = 1").list();
            if (listFaq == null) {
                return null;
            }
            session.getTransaction().commit();
            return listFaq;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }
}
