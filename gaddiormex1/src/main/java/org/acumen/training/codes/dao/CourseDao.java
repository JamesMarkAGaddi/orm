package org.acumen.training.codes.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.acumen.training.codes.model.Takes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CourseDao {
	private static final Logger LOGGER = Logger.getLogger(CourseDao.class.getName());
	private SessionFactory sf;

	public CourseDao(SessionFactory sf) {
		this.sf = sf;
	}

	// Get total count of students enrolled per course
	public List<Object[]> getTotalStudentsEnrolledPerCourse() {
		LOGGER.infof("Start");
		List<Object[]> stats = new ArrayList<>();
		try (Session sess = sf.openSession()) {
			CriteriaBuilder builder = sess.getCriteriaBuilder();
			CriteriaQuery<Object[]> sql = builder.createQuery(Object[].class);
			Root<Takes> from = sql.from(Takes.class);
			sql.multiselect(from.get("id").get("courseId"), builder.count(from.get("id").get("id")))
					.groupBy(from.get("id").get("courseId")).orderBy(builder.asc(from.get("id").get("courseId")));

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
