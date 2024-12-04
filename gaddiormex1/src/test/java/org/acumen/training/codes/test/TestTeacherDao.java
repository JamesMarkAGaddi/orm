package org.acumen.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.acumen.training.codes.UnivConfiguration;
import org.acumen.training.codes.dao.TeacherDao;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTeacherDao {
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
	public void testupdateAllInstructorSalaries() {
		config.createConfiguration();
		SessionFactory factory = config.getSessionFactory();
		TeacherDao teacherDao = new TeacherDao(factory);
		assertTrue(teacherDao.updateAllInstructorSalaries(), "Instructors salaries should be increased successfully.");
	}

	@Test
	public void testaddBonusForBiologyTeachers() {
		config.createConfiguration();
		SessionFactory factory = config.getSessionFactory();
		TeacherDao teacherDao = new TeacherDao(factory);
		assertTrue(teacherDao.addBonusForBiologyTeachers(),
				"Biology instructors salaries should be increased successfully.");
	}

	@Test
	public void testgiveRaiseToNonBiologyTeachers() {
		config.createConfiguration();
		SessionFactory factory = config.getSessionFactory();
		TeacherDao teacherDao = new TeacherDao(factory);
		assertTrue(teacherDao.giveRaiseToNonBiologyTeachers(),
				"Nonbiology instructors salaries should be increased successfully.");
	}

	@Test
	public void testgetMaxSalaryOfTeachersPerDepartment() {
		config.createConfiguration();
		SessionFactory factory = config.getSessionFactory();
		TeacherDao teacherDao = new TeacherDao(factory);
		List<Object[]> total = teacherDao.getMaxSalaryOfTeachersPerDepartment();
		assertNotNull(total);

		total.forEach((rec) -> {
			System.out.println(Arrays.toString(rec));
		});
	}

	@Test
	public void testgetTotalStudentsUnderEachTeacherAdvisor() {
		config.createConfiguration();
		SessionFactory factory = config.getSessionFactory();
		TeacherDao teacherDao = new TeacherDao(factory);
		List<Object[]> total = teacherDao.getTotalStudentsUnderEachTeacherAdvisor();
		assertNotNull(total);

		total.forEach((rec) -> {
			System.out.println(Arrays.toString(rec));
		});
	}

	@Test
	public void testgetTotalStudentsUnderEachTeacherInstructor() {
		config.createConfiguration();
		SessionFactory factory = config.getSessionFactory();
		TeacherDao teacherDao = new TeacherDao(factory);
		List<Object[]> total = teacherDao.getTotalStudentsUnderEachTeacherInstructor();
		assertNotNull(total);

		total.forEach((rec) -> {
			System.out.println(Arrays.toString(rec));
		});
	}

}
