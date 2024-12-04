package org.acumen.training.codes.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.acumen.training.codes.model.Project;
import org.acumen.training.codes.model.ProjectMembers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;


public class ProjectDao {
	private static final Logger LOGGER = Logger.getLogger(ProjectDao.class);
	
	private SessionFactory sf;
	
	public ProjectDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	public boolean insert(Project proj) {
	    Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();
		try{
			sess.persist(proj); // INSERT INTO
			tx.commit();
			return true;
		} catch(Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
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

	public boolean updateProjname(Short id, String newProjname) {
		Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();
		try{
			Project proj = sess.get(Project.class, id); // PK only
			proj.setProjname(newProjname);
			sess.merge(proj);
			tx.commit();
			return true;
		} catch(Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
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
	
	public boolean updateProjnameNewDate(String keyword, LocalDate newProjdate) {
		Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();
		try{
		    // Hibernate Query Language
			String sql = "update Project p set p.projdate = ?1 "
					+ "where p.projname like ?2";
			MutationQuery query = sess.createMutationQuery(sql);
			query.setParameter(1, newProjdate);
			query.setParameter(2, keyword);
		    int row = query.executeUpdate();
		    LOGGER.infof("Number of rows: %d", row);
			tx.commit();
			return true;
		} catch(Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
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
	
	public boolean deleteById(Short id) {
		Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();
		try{
			Project proj = sess.get(Project.class, id); // PK only
			sess.remove(proj);		
			tx.commit();
			return true;
		} catch(Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
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
	
	public boolean deleteByProjname(String keyword) {
		Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();
		try{
		    // Hibernate Query Language
			String sql = "delete from Project p "
					+ "where p.projname like :projname";
			MutationQuery query = sess.createMutationQuery(sql);
			query.setParameter("projname", keyword);
			
		    int row = query.executeUpdate();
		    LOGGER.infof("Number of rows: %d", row);
			tx.commit();
			return true;
		} catch(Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
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
	
	// Query Transaction
	
	public List<Project> selectAllProject(){
		List<Project> records = new ArrayList<>();
		try (Session sess = sf.openSession();){
		    // Hibernate Query Language
			String sql = "from Project p"; // SELECTION in HQL
			Query<Project> query = sess.createQuery(sql, Project.class);
			records = query.getResultList();
		    LOGGER.infof("Number of rows: %d", records.size());
			
			return Collections.unmodifiableList(records);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Project> selectAllProjectCriteria(){
		List<Project> records = new ArrayList<>();
		try (Session sess = sf.openSession();){
		    // Hibernate Query Language
			
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Project> sql = builder.createQuery(Project.class);
			Root<Project> from = sql.from(Project.class);
			sql.select(from);
			
			Query<Project> query = sess.createQuery(sql);
			records = query.getResultList();
		    LOGGER.infof("Number of rows: %d", records.size());
			
			return Collections.unmodifiableList(records);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public Project selectSingleProject(Short id){
		Project rec = new Project();
		try (Session sess = sf.openSession();){
		    // Hibernate Query Language
			
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Project> sql = builder.createQuery(Project.class);
			Root<Project> from = sql.from(Project.class);
			sql.select(from).where(builder.equal(from.get("projid"), id));
			
			Query<Project> query = sess.createQuery(sql);
			rec = query.getSingleResult();
		    LOGGER.infof("Number of rows: %d", 1);
			return rec;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rec;
	}
	
	public List<Object[]> projectSomeProj(String keyword){
		List<Object[]> records = new ArrayList<>();
		try (Session sess = sf.openSession();){
		    // Hibernate Query Language
			
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Object[]> sql = builder.createQuery(Object[].class);
			Root<Project> from = sql.from(Project.class);
			sql.multiselect(from.get("projname"), from.get("projdate"))
			   .where(builder.like(from.get("projname"), keyword))
			   .orderBy(builder.desc(from.get("projname")));
			
			Query<Object[]> query = sess.createQuery(sql);
			records = query.getResultList();
		    LOGGER.infof("Number of rows: %d", records.size());
			
			return Collections.unmodifiableList(records);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return records;
	}
	
	// HQL join
	public List<Project> joinMembersPerProj(){
		List<Project> records = new ArrayList<>();
		try (Session sess = sf.openSession();){
		    // Hibernate Query Language
			String sql = "from Project p inner join p.projMembers pm";
			Query<Project> query = sess.createQuery(sql, Project.class);
			records = query.getResultList();
		    LOGGER.infof("Number of rows: %d", records.size());
			
			return Collections.unmodifiableList(records);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Project> joinMembersPerProjCriteria(){
		List<Project> records = new ArrayList<>();
		try (Session sess = sf.openSession();){
		    // Hibernate Query Language
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Project> sql = builder.createQuery(Project.class);
			Root<Project> from = sql.from(Project.class);
			Join<Project, ProjectMembers> join = from.join("projMembers", JoinType.INNER);
		    sql.select(from);
		    
			Query<Project> query = sess.createQuery(sql);
			records = query.getResultList();
		    LOGGER.infof("Number of rows: %d", records.size());
			
			return Collections.unmodifiableList(records);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return records;
	}

}




