/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.OrderDetails;
import entity.Orders;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import util.SufeeHibernateUtil;

/**
 *
 * @author ASUS
 */
public class OrdersDAO {

    public List<Orders> getAllOrders() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            List<Orders> listOrders = session.createQuery("from Orders where orderStatus = 1 or orderStatus = 0 or orderStatus = -1").list();
            if (listOrders == null) {
                return null;
            }
            session.getTransaction().commit();
            return listOrders;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public Orders getOrderId(int orderId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            Orders orders = (Orders) session.createQuery("from Orders where (orderStatus = 1 or orderStatus = 0 or orderStatus = -1) and orderId = :orderId").setParameter("orderId", orderId).uniqueResult();
            if (orders == null) {
                return null;
            }
            session.getTransaction().commit();
            return orders;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Orders> getOrderByCustomerId(int customerId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            List<Orders> orders = session.createQuery("from Orders where (orderStatus = 1 or orderStatus = 0 or orderStatus = -1) and customerId = :customerId").setParameter("customerId", customerId).list();
            if (orders == null) {
                return null;
            }
            session.getTransaction().commit();
            return orders;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public List<OrderDetails> getOrderDetailByOrderId(int orderId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            List<OrderDetails> listOrderDeatil = session.createQuery("from OrderDetails where (orderDetailStatus = 1 or orderDetailStatus = 0 or orderDetailStatus = -1) and orderId = :orderId").setParameter("orderId", orderId).list();
            if (listOrderDeatil == null) {
                return null;
            }
            session.getTransaction().commit();
            return listOrderDeatil;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {

        }

    }

    public boolean createOrders(Orders orders, String email) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Date date = Calendar.getInstance().getTime();
            orders.setCreateDate(date);
            orders.setEmail(email);
            session.save(orders);
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

    public boolean createOrdersDetail(OrderDetails orderDetails) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Date date = Calendar.getInstance().getTime();
            orderDetails.setCreateDate(date);
            session.save(orderDetails);
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

    public List<OrderDetails> getAllOrdersDetail() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            List<OrderDetails> listOrdersDetail = session.createQuery("from OrderDetails where orderDetailStatus = 1 or orderDetailStatus = 0 or orderDetailStatus = -1").list();
            if (listOrdersDetail == null) {
                return null;
            }
            session.getTransaction().commit();
            return listOrdersDetail;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean acceptOrder(int orderId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Orders orders = getOrderId(orderId);
            orders.setOrderStatus(1);
            session.update(orders);
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

    public boolean cannelOrder(int orderId) {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Orders orders = getOrderId(orderId);
            if (orders.getTimeExpired().getTime() < new Date().getTime()) {
                return false;
            }
            orders.setOrderStatus(-1);
            session.update(orders);
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

    public int countOrder() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int countOrder = 0;
        try {
            List list = session.createQuery("from Orders where orderStatus = 1").list();
            countOrder = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return countOrder;
    }


    public double totalOrder() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query sumOrder = session.createQuery("select SUM(orderTotalAmount) from Orders where orderStatus = 1");
            if (sumOrder.list().get(0) == null) {
                return 0;
            }
            session.getTransaction().commit();
            return (double) sumOrder.list().get(0);
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return 0;
        }
    }
    
    public List<Orders> notifyOrder() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Orders> listOrder = session.createQuery("from Orders  where ( orderStatus = 0 ) order by orderId desc").setMaxResults(4).list();
            if (listOrder == null) {
                return null;
            }
            session.getTransaction().commit();
            return listOrder;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
            return null;
        } finally {
            session.close();
        }
    }

    public int countNotifyOrder() {
        Session session = SufeeHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int countOrder = 0;
        try {
            List list = session.createQuery("from Orders where orderStatus = 0").list();
            countOrder = list.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.getMessage();
        } finally {
            session.close();
        }
        return countOrder;
    }
}
