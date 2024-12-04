package org.acumen.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.acumen.training.codes.UnivConfiguration;
import org.acumen.training.codes.dao.CourseDao;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCourseDao {
	private UnivConfiguration config;

	@BeforeEach
	public void setup() {
		config = new UnivConfiguration();
	}

	@AfterEach
	public void teardown() {
		config = null;
	}

	@Test
	public void testgetTotalStudentsPerCourse() {
		config.createConfiguration();
		SessionFactory factory = config.getSessionFactory();
		CourseDao courseDao = new CourseDao(factory);
		List<Object[]> totalStudPerCourse = courseDao.getTotalStudentsEnrolledPerCourse();
		assertNotNull(totalStudPerCourse);

		totalStudPerCourse.forEach((rec) -> {
			System.out.println(Arrays.toString(rec));
		});
	}

}
