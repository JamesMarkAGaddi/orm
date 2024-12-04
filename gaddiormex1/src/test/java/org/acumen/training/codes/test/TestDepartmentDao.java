package org.acumen.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.acumen.training.codes.UnivConfiguration;
import org.acumen.training.codes.dao.DepartmentDao;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDepartmentDao {

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
	public void testgetTotalTeachersAndAvgSalaryPerDepartment() {
		config.createConfiguration();
		SessionFactory factory = config.getSessionFactory();
		DepartmentDao departmentDao = new DepartmentDao(factory);
		List<Object[]> total = departmentDao.getTotalTeachersAndAvgSalaryPerDepartment();
		assertNotNull(total);

		total.forEach((rec) -> {
			System.out.println(Arrays.toString(rec));
		});
	}

	@Test
	public void testgetTotalTeachersUnderEachDepartment() {
		config.createConfiguration();
		SessionFactory factory = config.getSessionFactory();
		DepartmentDao departmentDao = new DepartmentDao(factory);
		List<Object[]> total = departmentDao.getTotalTeachersUnderEachDepartment();
		assertNotNull(total);

		total.forEach((rec) -> {
			System.out.println(Arrays.toString(rec));
		});
	}

}
