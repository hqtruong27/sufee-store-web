/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Icons;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Kai
 */
public class IconDAO {
    public List<Icons> getAllIcons(){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Icons> listIcon = session.createQuery("from Icons where iconStatus = 0 or iconStatus = 1").list();
            if (listIcon == null) {
                return null;
            }
            session.getTransaction().commit();
            return listIcon;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        }finally{
            session.close();
        }
    }
    
    public Icons getIconById(int iconId){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Icons icon = null;
        try {
            Query query = session.createQuery("from Icons where iconId = :iconId");
            query.setParameter("iconId", iconId);
            icon = (Icons) query.uniqueResult();
            session.getTransaction().commit();
            return icon;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        }finally{
            session.close();
        }
    }
    
    public boolean createIcon(Icons icon){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(icon);
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
    
    public boolean updateIcon(Icons icon){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(icon);
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
    
    public boolean deleteIcon(int iconId){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Icons icon = getIconById(iconId);
            icon.setIconStatus(-1);
            session.update(icon);
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
}
