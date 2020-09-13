package dao;

import entity.Customers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 *
 * @author ASUS
 */
public class CustomerDAO {

    public Customers login(String email, String passwords) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customers customer = new Customers();
        try {
            customer = (Customers) session.createQuery("from Customers where email = :email").setParameter("email", email).uniqueResult();
            if (customer != null && customer.getPasswords().equals(passwords)) {
                session.getTransaction().commit();
                return customer;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
        return null;
    }

    public boolean register(Customers c) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(c);
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

    public boolean checkEmailExists(String email) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        Customers customer = new Customers();
        try {
            customer = (Customers) session.createQuery("from Customers where email = :email").setParameter("email", email).uniqueResult();
            session.getTransaction().commit();
            if (customer != null) {
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

    public boolean checkPhoneExists(String phone) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        try {
            Customers customer = (Customers) session.createQuery("from Customers where phone = :phone").setParameter("phone", phone).uniqueResult();
            session.getTransaction().commit();
            if (customer != null) {
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

    public List<Customers> getAllCustomer() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Customers> list = session.createQuery("from Customers where (customerStatus = 1 or customerStatus = 0)").list();
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

    public boolean updateCustomer(Customers c) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(c);
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

    public Customers getCustomerId(int customerId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            Customers customer = (Customers) session.createQuery("from Customers where (customerStatus = 1 or  customerStatus = 0 or customerStatus = -1) and customerId = :customerId").setParameter("customerId", customerId).uniqueResult();
            if (customer == null) {
                return null;
            }
            session.getTransaction().commit();
            return customer;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Customers getCustomerByEmail(String email) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            Customers customer = (Customers) session.createQuery("from Customers where email = :email").setParameter("email", email).uniqueResult();
            if (customer == null) {
                return null;
            }
            session.getTransaction().commit();
            return customer;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean changeInfoCustomer(int customerId, String fullname, String phone, String birthday, String customerAddress, String avatar) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        try {
            Customers customer = getCustomerId(customerId);
            customer.setFullname(fullname);
            customer.setPhone(phone);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(birthday);
            customer.setBirthday(date);
            customer.setCustomerAddress(customerAddress);
            customer.setAvatar(avatar);
            check = updateCustomer(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return check;
    }

    public boolean changePassword(int customerId, String passwords) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        try {
            Customers customer = getCustomerId(customerId);
            customer.setPasswords(passwords);
            check = updateCustomer(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return check;
    }

    public boolean changeAvatar(int customerId, String avatar) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        try {
            Customers customer = getCustomerId(customerId);
            customer.setAvatar(avatar);
            check = updateCustomer(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return check;
    }
    
    public int countCustomer() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int countCustomer = 0;
        try {
            List list = session.createQuery("from Customers where customerStatus = 1").list();
            countCustomer = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        }finally{
            session.close();
        }
        return countCustomer;
    }
}
