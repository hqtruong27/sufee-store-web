/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Introductions;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Kai
 */
public class IntroductionDAO {

    public List<Introductions> getAllIntro() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Introductions> listIntro = session.createQuery("from Introductions where introductionStatus = 1 or introductionStatus = 0").list();
            if (listIntro == null) {
                return null;
            }
            session.getTransaction().commit();
            return listIntro;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public Introductions getIntroById(int introductionId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Introductions intro = null;
        try {
            Query query = session.createQuery("from Introductions where introductionId = :introductionId");
            query.setParameter("introductionId", introductionId);
            intro = (Introductions) query.uniqueResult();
            session.getTransaction().commit();
            return intro;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean createIntro(Introductions intro) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        try {
            List<Introductions> listIntroductionses = getAllIntro();
            if (listIntroductionses != null) {
                session.beginTransaction();
                listIntroductionses.stream().map((Introductions introdution) -> {
                    if (introdution.getIntroductionStatus() == 1) {
                        introdution.setIntroductionStatus(0);
                    }
                    return introdution;
                }).forEachOrdered((introdution) -> {
                    session.merge(introdution);
                });
                session.getTransaction().commit();
            }
            session.beginTransaction();
            session.save(intro);
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

    public boolean updateIntro(Introductions intro) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(intro);
            session.getTransaction().commit();
            //      
            List<Introductions> listIntroductionses = getAllIntro();
            session.beginTransaction();
            for (Introductions introdution : listIntroductionses) {
                if (introdution.getIntroductionStatus() == 1 && intro.getIntroductionId() != introdution.getIntroductionId()) {
                    introdution.setIntroductionStatus(0);
                }
                session.merge(introdution);
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

    public boolean deleteIntro(int introductionId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Introductions intro = getIntroById(introductionId);
            intro.setIntroductionStatus(-1);
            session.update(intro);
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

    public Introductions getIntroByStatus() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Introductions intro = null;
        try {
            Query query = session.createQuery("from Introductions where introductionStatus = 1");
            intro = (Introductions) query.uniqueResult();
            session.getTransaction().commit();
            return intro;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }
}
