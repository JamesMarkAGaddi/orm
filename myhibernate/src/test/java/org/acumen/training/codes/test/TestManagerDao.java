package org.acumen.training.codes.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.acumen.training.codes.HRMSConfiguration;
import org.acumen.training.codes.dao.ManagerDao;
import org.acumen.training.codes.model.Manager;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestManagerDao {
	private HRMSConfiguration config;

	@BeforeEach
	public void setup() {
		config = new HRMSConfiguration();
	}

	@AfterEach
	public void teardown() {
		config = null;
	}
	
	@Disabled
	@Test
	public void testInsert() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		Manager mgr = new Manager();
		mgr.setFirstname("Pedro");
		mgr.setAge(30);
		mgr.setSalary(60000.00);
		mgr.setBirthday(LocalDate.of(1999, 10, 20));
		mgr.setGender('M');
		mgr.setSupervisor(104);
		
		ManagerDao dao = new ManagerDao(sf);
		dao.insert(mgr);
		
		System.out.println("inserted 1 record");
	}
	
	@Disabled
	@Test
	public void testSelectGroupByGenderWithStats() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
				
		ManagerDao dao = new ManagerDao(sf);
		List<Object[]> stats = dao.selectGroupByGenderStats();
		stats.forEach((rec) -> {
			System.out.println(Arrays.toString(rec));
		});
	}
	
	@Test
	public void testUpdateManagerFNameAge() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
				
		ManagerDao dao = new ManagerDao(sf);
		dao.updateManagerFNameAge("Peter", 40, 2);
	}
	
	@Test
	public void testselectAllManager() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
				
		ManagerDao dao = new ManagerDao(sf);
		List<Manager> recs = dao.selectAllManager();
		System.out.println(recs);
	}
	
	@Test
	public void testSelectSelfJoin() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
				
		ManagerDao dao = new ManagerDao(sf);
		List<Object[]> recs = dao.selectSelfJoin();
		recs.stream().forEach((rec) -> {
			System.out.println(Arrays.toString(rec));
		});
	}
	
}
