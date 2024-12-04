package org.acumen.training.codes.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.acumen.training.codes.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CustomerDao {

    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class.getName());
    private SessionFactory sf;

    public CustomerDao(SessionFactory sf) {
        this.sf = sf;
    }

    public boolean insert(Customer customer) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			sess.persist(customer); 
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				sess.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

    public boolean updateCustomerGrade(Long id, Integer newGrade) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			Customer customer = sess.get(Customer.class, id);
			customer.setGrade(newGrade);
			sess.merge(customer);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				sess.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
    
    public boolean deleteCustomerById(Long id) {
        Transaction transaction = null;
        Customer customer = new Customer();
        try (Session sess = sf.openSession()) {
            transaction = sess.beginTransaction();

            CriteriaBuilder builder = sess.getCriteriaBuilder();
            CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
            Root<Customer> from = criteria.from(Customer.class);
            criteria.select(from).where(builder.equal(from.get("customerId"), id));

            Query<Customer> query = sess.createQuery(criteria);
            customer = query.getSingleResult();

            if (customer != null) {
                sess.remove(customer);
                transaction.commit();
                LOGGER.infof("Removed customer with ID {}", id);
            } else {
                LOGGER.infof("Customer with ID {} not found", id);
            }
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
		return false;
    }

    public List<Customer> selectAllCustomers() {
        List<Customer> records = new ArrayList<>();
        try (Session sess = sf.openSession()) {

            CriteriaBuilder builder = sess.getCriteriaBuilder();
            CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
            Root<Customer> root = criteria.from(Customer.class);
            criteria.select(root);

            Query<Customer> query = sess.createQuery(criteria);
            records = query.getResultList();
            LOGGER.infof("Number of customers: {}", records.size());
    
			return Collections.unmodifiableList(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}
