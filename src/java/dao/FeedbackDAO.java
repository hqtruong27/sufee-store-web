package dao;

import entity.FeedbackCatalogs;
import entity.Feedbacks;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author ASUS
 */
public class FeedbackDAO {

    //All FeedbackCatalog Client
    public List<FeedbackCatalogs> getAllFeedbackCataLog() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<FeedbackCatalogs> listFb = session.createQuery("from FeedbackCatalogs where feedbackCatalogStatus = 1").list();
            if (listFb == null) {
                return null;
            }
            session.getTransaction().commit();
            return listFb;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    //All FeedbackCatalog Admin
    public List<FeedbackCatalogs> getALlFeedbackCataLogAdmin() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<FeedbackCatalogs> listFb = session.createQuery("from FeedbackCatalogs where feedbackCatalogStatus = 1 or feedbackCatalogStatus = 0 ").list();
            if (listFb == null) {
                return null;
            }
            session.getTransaction().commit();
            return listFb;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public FeedbackCatalogs getFeedbackCatalogId(int feedbackCatalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            FeedbackCatalogs feedbackCatalogs = (FeedbackCatalogs) session.createQuery("from FeedbackCatalogs where(feedbackCatalogStatus = 0 or feedbackCatalogStatus = 1 ) and feedbackCatalogId = :feedbackCatalogId").setParameter("feedbackCatalogId", feedbackCatalogId).uniqueResult();
            if (feedbackCatalogs == null) {
                return null;
            }
            session.getTransaction().commit();
            return feedbackCatalogs;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean createFeedbackCatalogas(FeedbackCatalogs feedbackCatalogs) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(feedbackCatalogs);
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

    public boolean createFeedback(Feedbacks feedbacks) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Date date = Calendar.getInstance().getTime();
            feedbacks.setFeedbacksTime(date);
            session.save(feedbacks);
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

    public boolean deleteFeedbackCatalogs(int feedbackCatalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            FeedbackCatalogs feedbacks = getFeedbackCatalogId(feedbackCatalogId);
            if (feedbacks != null) {
                feedbacks.setFeedbackCatalogStatus(-1);
                session.update(feedbacks);
                session.getTransaction().commit();
                return true;
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

    public List<Feedbacks> getAllFeedback() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Feedbacks> listF = session.createQuery("from Feedbacks where feedbackStatus = 1 or feedbackStatus = 0").list();
            if (listF == null) {
                return null;
            }
            session.getTransaction().commit();
            return listF;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public Feedbacks getFeedbackId(int feedbackId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Feedbacks feedbacks = (Feedbacks) session.createQuery("from Feedbacks where(feedbackStatus = 0 or feedbackStatus = 1) and feedbackId = :feedbackId").setParameter("feedbackId", feedbackId).uniqueResult();
            if (feedbacks == null) {
                return null;
            }
            session.getTransaction().commit();
            return feedbacks;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Feedbacks> getAllFeedbackHasFeedbackCatalogId(int feedbackCatalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Feedbacks> listF = session.createQuery("from Feedbacks where (feedbackStatus = 1 or feedbackStatus = 0) and feedbackCatalogId = :feedbackCatalogId ").setParameter("feedbackCatalogId", feedbackCatalogId).list();
            if (listF == null) {
                return null;
            }
            session.getTransaction().commit();
            return listF;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public Feedbacks getFeedbackHasFeedbackCatalogId(int feedbackCatalogId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Feedbacks feedbacks = (Feedbacks) session.createQuery("from Feedbacks where(feedbackStatus = 0 or feedbackStatus = 1) and feedbackCatalogId = :feedbackCatalogId").setParameter("feedbackCatalogId", feedbackCatalogId).uniqueResult();
            if (feedbacks == null) {
                return null;
            }
            session.getTransaction().commit();
            return feedbacks;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean checkFeedbackCatalogExists(String feedbackCatalogName) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        FeedbackCatalogs feedbackCatalogs = new FeedbackCatalogs();
        try {
            feedbackCatalogs = (FeedbackCatalogs) session.createQuery("from FeedbackCatalogs where (feedbackCatalogStatus = 1 or feedbackCatalogStatus = 0) and feedbackCatalogName = :feedbackCatalogName").setParameter("feedbackCatalogName", feedbackCatalogName).uniqueResult();
            session.getTransaction().commit();
            if (feedbackCatalogs != null) {
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

    public List<Feedbacks> notifyFeedback() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Feedbacks> listFeedback = session.createQuery("from Feedbacks  where ( feedbackStatus = 0 ) order by feedbackId desc").setMaxResults(4).list();
            if (listFeedback == null) {
                return null;
            }
            session.getTransaction().commit();
            return listFeedback;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public int countNotifyFeedback() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int countFeedback = 0;
        try {
            List list = session.createQuery("from Feedbacks where feedbackStatus = 0").list();
            countFeedback = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return countFeedback;
    }
}
