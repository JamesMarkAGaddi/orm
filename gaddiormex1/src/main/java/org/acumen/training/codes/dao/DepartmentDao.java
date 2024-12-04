package org.acumen.training.codes.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.acumen.training.codes.model.Department;
import org.acumen.training.codes.model.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public class DepartmentDao {

	private static final Logger LOGGER = Logger.getLogger(DepartmentDao.class.getName());
	private SessionFactory sf;

	public DepartmentDao(SessionFactory sf) {
		this.sf = sf;
	}

	// Get total count of teachers and average salary per department
	public List<Object[]> getTotalTeachersAndAvgSalaryPerDepartment() {
		LOGGER.infof("Start");
		List<Object[]> stats = new ArrayList<>();
		try (Session sess = sf.openSession()) {
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Object[]> sql = builder.createQuery(Object[].class);
			Root<Instructor> from = sql.from(Instructor.class);
			sql.multiselect(from.get("deptName"), 
					builder.count(from.get("id")), 
					builder.avg(from.get("salary")))
					.groupBy(from.get("deptName"));

			Query<Object[]> query = sess.createQuery(sql);
			stats = query.getResultList();
			LOGGER.infof("Number of rows: %d", stats.size());
		} catch (Exception e) {
			LOGGER.fatal("[Exception] Error while fetching");
			e.printStackTrace();
		}
		LOGGER.infof("End");
		return Collections.unmodifiableList(stats);
	}

	// Get total number of teachers under each department
	public List<Object[]> getTotalTeachersUnderEachDepartment() {
		LOGGER.infof("Start");
		List<Object[]> stats = new ArrayList<>();
		try (Session sess = sf.openSession()) {
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Object[]> sql = builder.createQuery(Object[].class);

			Root<Department> from = sql.from(Department.class);
			Join<Department, Instructor> join = from.join("instructor", JoinType.LEFT);

			sql.multiselect(from.get("deptName"), 
					builder.coalesce(builder.count(join), 0))
					.groupBy(from.get("deptName"))
					.orderBy(builder.desc(builder.count(join.get("deptName"))));

			Query<Object[]> query = sess.createQuery(sql);
			stats = query.getResultList();
			LOGGER.infof("Number of rows: %d", stats.size());
		} catch (Exception e) {
			LOGGER.fatal("[Exception] Error while fetching");
			e.printStackTrace();
		}
		LOGGER.infof("End");
		return Collections.unmodifiableList(stats);
	}

}
