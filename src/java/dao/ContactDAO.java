/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Contacts;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author Kai
 */
public class ContactDAO {

    public List<Contacts> getAllContact() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Contacts> listContact = session.createQuery("from Contacts where contactStatus = 1 or contactStatus = 0").list();
            if (listContact == null) {
                return null;
            }
            session.getTransaction().commit();
            return listContact;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public Contacts getContactById(int contactId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Contacts contact = null;
        try {
            Query query = session.createQuery("from Contacts where (contactStatus = 1 or contactStatus = 0) and contactId = :contactId");
            query.setParameter("contactId", contactId);
            contact = (Contacts) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return contact;
    }

    public boolean createContact(Contacts contact) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        try {
            List<Contacts> listContacts = getAllContact();
            if (listContacts != null) {
                session.beginTransaction();
                for (Contacts listContact : listContacts) {
                    if (listContact.getContactStatus() == 1) {
                        listContact.setContactStatus(0);
                    }
                    session.merge(listContact);
                }
                session.getTransaction().commit();
            }
            session.beginTransaction();
            session.save(contact);
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

    public boolean updateContact(Contacts contact) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(contact);
            session.getTransaction().commit();
            //
            List<Contacts> listContact = getAllContact();
            session.beginTransaction();
            for (Contacts contacts : listContact) {
                if (contacts.getContactStatus() == 1 && contact.getContactId() != contacts.getContactId()) {
                    contacts.setContactStatus(0);
                }
                session.merge(contacts);
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

    public boolean deleteContact(int contactId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Contacts contact = getContactById(contactId);
            contact.setContactStatus(-1);
            session.update(contact);
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

    public Contacts getContactByStatus() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Contacts contact = (Contacts) session.createQuery("from Contacts where contactStatus = 1").uniqueResult();
            session.getTransaction().commit();
            return contact;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }
}
