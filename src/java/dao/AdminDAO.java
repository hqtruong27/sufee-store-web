package dao;

import entity.Admins;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.SufeeHibernateUtil;

/**
 * This is used to get the data for administrators
 *
 * @author Hoang Truong
 */
public class AdminDAO {

    ///Login admin
    public Admins login(String email, String passwords) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Admins admin = new Admins();
        try {
            admin = (Admins) session.createQuery("from Admins where email = :email").setParameter("email", email).uniqueResult();
            if (admin != null && admin.getPasswords().equals(passwords)) {
                session.getTransaction().commit();
                return admin;
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

    //DAO/List Admins 
    public List<Admins> getAllAdmin() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Admins> list = session.createQuery("from Admins where (Status = 1 or Status = 0)").list();
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

    //Create Admin(employee)
    public boolean createAdmins(Admins admin) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
//            SimpleDateFormat format = new  SimpleDateFormat("dd/MM/yyyy");
//            Date date = format.parse(birthday);
//            admin.setBirthday(date);
            session.save(admin);
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

    //Update Admin(e)
    public boolean updateAdmins(Admins a) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(a);
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

    //Get Admin by Id
    //Lần sau đặt tên biến rõ rang vào nhé.
    //Không đặt a - b không ai hiểu.
    public Admins getAdminById(int adminId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Admins a = null;
        try {
            //get Admins by id
            Query query = session.createQuery("from Admins where adminId=:aId");
            //set value tham so truyen vao aId
            query.setParameter("aId", adminId);
            //get Admin
            a = (Admins) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return a;
    }

    //Delete Admin(employee)
    public boolean deleteAdmins(int adminId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Admins a = getAdminById(adminId);
            a.setStatus(-1);
            session.update(a);
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

    //Change-admin-info
    public boolean changeAdminInfo(int adminId, String fullname, String email, String birthday, String idCard, int gender, String adminAddress, String phone, String avatar) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Boolean result = false;

        try {
            Admins admins = getAdminById(adminId);
            admins.setFullname(fullname);
            admins.setEmail(email);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(birthday);
            admins.setBirthday(date);
            admins.setIdCard(idCard);
            admins.setGender(gender);
            admins.setAdminAddress(adminAddress);
            admins.setPhone(phone);
            admins.setAvatar(avatar);
            session.merge(admins);
            session.getTransaction().commit();
            return result = true;
        } catch (Exception e) {
            e.getMessage();
            session.getTransaction().rollback();
            return result;
        } finally {
            session.close();
        }
    }

    //Change-admin-avatar
    public boolean changeAdminAvatar(int adminId, String avatar) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        try {
            Admins admin = getAdminById(adminId);
            admin.setAvatar(avatar);
            check = updateAdmins(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return check;
    }

    //Change-password
    public boolean changeAdminPassword(int adminId, String passwords) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        try {
            Admins admin = getAdminById(adminId);
            admin.setPasswords(passwords);
            check = updateAdmins(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return check;
    }

    public boolean checkEmailExists(String email) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        Admins admin = new Admins();
        try {
            admin = (Admins) session.createQuery("from Admins where email = :email").setParameter("email", email).uniqueResult();
            session.getTransaction().commit();
            if (admin != null) {
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
        Admins admin = new Admins();
        try {
            admin = (Admins) session.createQuery("from Admins where phone = :phone").setParameter("phone", phone).uniqueResult();
            session.getTransaction().commit();
            if (admin != null) {
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
    
    public boolean checkIDCardExists(String idCard){
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        boolean check = false;
        Admins admin = new Admins();
        try {
            admin = (Admins) session.createQuery("from Admins where idCard = :idCard").setParameter("idCard", idCard).uniqueResult();
            session.getTransaction().commit();
            if (admin != null) {
               check = true; 
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        }finally{
            session.close();
        }
        return check;
    }
    
    public int countAdmin() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int countAdmin = 0;
        try {
            List list = session.createQuery("from Admins where status = 1 or status = 0").list();
            countAdmin = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        }finally{
            session.close();
        }
        return countAdmin;
    }
}
