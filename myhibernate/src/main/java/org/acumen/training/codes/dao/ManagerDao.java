package org.acumen.training.codes.dao;

import java.util.ArrayList;
import java.util.List;

import org.acumen.training.codes.model.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

public class ManagerDao {
	
	private SessionFactory sf;
	private static final Logger LOGGER = Logger.getLogger(ManagerDao.class);
	
	public ManagerDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Transactional
	public boolean insert(Manager mgr) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			sess.persist(mgr);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			try {
				sess.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	public boolean updateManagerFNameAge(String firstname, Integer age, Integer id) {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			MutationQuery query = sess.createNamedMutationQuery("updateManagerFNameAge");
			query.setParameter("fname", firstname);
			query.setParameter("age", age);
			query.setParameter("id", id);
			int rows = query.executeUpdate();
			LOGGER.infof("No. of rows: %d", rows);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			try {
				sess.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	@Transactional
	public List<Manager> selectAllManager(){
		Session sess = sf.openSession();
		List<Manager> recs = new ArrayList<>();
		try {
			Query<Manager> query = sess.createNamedQuery("selectAllManager", 
					Manager.class);
			recs = query.getResultList();
			return recs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				sess.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return recs;
	}
	
	public List<Object[]> selectSelfJoin(){
		Session sess = sf.openSession();
		List<Object[]> recs = new ArrayList<>();
		try {
						
			Query<Object[]> query = 
					sess.createNamedQuery("selectSelfJoin", Object[].class); // Both HQL and Native Query (with SQL limits)
			recs = query.getResultList();
			return recs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				sess.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return recs;
	}
	// Group by
	
	public List<Object[]> selectGroupByGenderStats(){
		Session sess = sf.openSession();
		List<Object[]> stats = new ArrayList<>();
		try {
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Object[]> sql = builder.createQuery(Object[].class);
			Root<Manager> from = sql.from(Manager.class);
			sql.multiselect(from.get("gender"), 
					builder.count(from.get("gender")).alias("total count"),
					builder.avg(from.get("age")).alias("avg age"), 
					builder.avg(from.get("salary")).alias("avg salary"))
			.groupBy(from.get("gender"))
			.having(builder.greaterThanOrEqualTo(from.get("avg salary"), 38000.00))
			.orderBy(builder.desc(from.get("gender")));
			
			Query<Object[]> query = sess.createQuery(sql);
			stats = query.getResultList();
			return stats;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				sess.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return stats;
	}

}
