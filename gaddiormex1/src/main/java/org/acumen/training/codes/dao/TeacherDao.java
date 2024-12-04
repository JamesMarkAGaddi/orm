package org.acumen.training.codes.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.acumen.training.codes.model.Advisor;
import org.acumen.training.codes.model.Instructor;
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

public class TeacherDao {
	private static final Logger LOGGER = Logger.getLogger(TeacherDao.class.getName());
	private SessionFactory sf;

	public TeacherDao(SessionFactory sf) {
		this.sf = sf;
	}

	// Update all instructor salaries to $45,000
	public boolean updateAllInstructorSalaries() {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			MutationQuery query = sess.createNamedMutationQuery("updateAllSalaries");
			query.setParameter("newSalary", 45000);
			int rows = query.executeUpdate();
			tx.commit();
			LOGGER.infof("No. of rows updated to $45,000: %d", rows);
			return true;
		} catch (Exception e) {
			tx.rollback();
			LOGGER.error("[Exception] Error updating all instructor salaries", e);
		} finally {
			sess.close();
		}
		return false;
	}

	// Add $5,000 more to teachers teaching Biology
	public boolean addBonusForBiologyTeachers() {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			MutationQuery query = sess.createNamedMutationQuery("increaseSalaryForBiologyTeachers");
			query.setParameter("courseTitle", "Biology");
			int rows = query.executeUpdate();
			tx.commit();
			LOGGER.infof("No. of instructors teaching Biology received 5,000 increase: %d", rows);
			return true;
		} catch (Exception e) {
			tx.rollback();
			LOGGER.error("[Exception] Error updating Biology instructors' salary", e);
		} finally {
			sess.close();
		}
		return false;
	}

	// Give a 10% raise to all instructors not teaching Biology
	public boolean giveRaiseToNonBiologyTeachers() {
		Session sess = sf.openSession();
		Transaction tx = sess.beginTransaction();
		try {
			MutationQuery query = sess.createNamedMutationQuery("raiseForNonBiologyTeachers");
			query.setParameter("courseTitle", "Biology");
			int rows = query.executeUpdate();
			tx.commit();
			LOGGER.infof("No. of instructors not teaching Biology received a 10% raise: %d", rows);
			return true;
		} catch (Exception e) {
			tx.rollback();
			LOGGER.error("[Exception] Error giving raise to non-Biology instructors", e);
		} finally {
			sess.close();
		}
		return false;
	}

	// Get maximum salary of a teacher per department where individual salary >=
	// 9000
	public List<Object[]> getMaxSalaryOfTeachersPerDepartment() {
		LOGGER.infof("Start");
		List<Object[]> stats = new ArrayList<>();
		try (Session sess = sf.openSession()) {
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Object[]> sql = builder.createQuery(Object[].class);
			Root<Instructor> from = sql.from(Instructor.class);
			sql.multiselect(from.get("deptName"), builder.max(from.get("salary")))
					.where(builder.ge(from.get("salary"), 9000)).groupBy(from.get("deptName"));

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

	// Get the total number of students under each teacher
	public List<Object[]> getTotalStudentsUnderEachTeacherAdvisor() {
		LOGGER.infof("Start");
		List<Object[]> stats = new ArrayList<>();
		try (Session sess = sf.openSession()) {
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Object[]> sql = builder.createQuery(Object[].class);
			Root<Advisor> advisorRoot = sql.from(Advisor.class);
			Join<Advisor, Instructor> instructorJoin = advisorRoot.join("instructor", JoinType.LEFT);
			sql.multiselect(instructorJoin.get("name"), builder.count(advisorRoot.get("sId")))
					.groupBy(instructorJoin.get("name")).orderBy(builder.asc(builder.count(advisorRoot.get("sId"))));

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

	public List<Object[]> getTotalStudentsUnderEachTeacherInstructor() {
		List<Object[]> recs = new ArrayList<>();
		Session sess = sf.openSession();
		try {
			Query<Object[]> query = sess.createNamedQuery("getTotalStudentsUnderEachTeacherInstructor", Object[].class);
			recs = query.getResultList();

			LOGGER.info("Successfully retrieved total students by instructors");
		} catch (Exception e) {
			LOGGER.error("Error occurred while retrieving total students by instructor: " + e.getMessage(), e);
		} finally {
			try {
				sess.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return recs;
	}
}
