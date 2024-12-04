package org.acumen.training.codes.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.acumen.training.codes.HRMSConfiguration;
import org.acumen.training.codes.dao.ProjectDao;
import org.acumen.training.codes.model.Project;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestProjectDao {
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
		ProjectDao dao = new ProjectDao(sf);
		Project proj = new Project();
		proj.setProjid((short) 8);
		proj.setProjname("Feedback Sys");
		proj.setProjdate(LocalDate.of(2025, 12, 10));
		dao.insert(proj);
		System.out.println("inserted 1 record");
	}
	
	@Disabled
	@Test
	public void testUpdateProjname() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		dao.updateProjname((short) 8, "Compolaint Mgt. Sys");
		System.out.println("updated 1 record");
	}
	
	@Test
	public void testDeleteById() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		dao.deleteById((short) 3);
		System.out.println("deleted 1 record");
	}
	@Test
	public void testUpdateProjnameNewdate() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		dao.updateProjnameNewDate("%system%", LocalDate.of(2027, 3, 30));
	}
	
	@Disabled
	@Test
	public void testDeleteByProjname() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		dao.deleteByProjname("%system%");
	}
	
	@Disabled
	@Test
	public void testSelectAllProject() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		List<Project> recs = dao.selectAllProject();
		System.out.println(recs);
	}
	
	@Disabled
	@Test
	public void testSelectAllProjectCriteria() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		List<Project> recs = dao.selectAllProjectCriteria();
		System.out.println(recs);
	}
	
	
	@Test
	public void testSelectSingleProject() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		Project rec = dao.selectSingleProject((short) 3);
		System.out.println(rec);
	}
	
	
	@Test
	public void testprojectProjnames() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		List<Object[]> rec = dao.projectSomeProj("%System%");
		rec.forEach((r) -> {
			System.out.println(Arrays.toString(r));
		});
		
	}
	
	
	@Test
	public void testJoinMembersPerProj() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		List<Project> recs = dao.joinMembersPerProj();
		recs.stream().forEach((r)->{
			System.out.print(r.getProjname() + " ");
			System.out.println(r.getProjMembers());
		});
	}
	
	@Test
	public void testJoinMembersPerProjCriteria() {
		config.createServiceRegistry();
		SessionFactory sf = config.getSessionFactory();
		ProjectDao dao = new ProjectDao(sf);
		List<Project> recs = dao.joinMembersPerProjCriteria();
		recs.stream().forEach((r)->{
			System.out.print(r.getProjname() + " ");
			System.out.println(r.getProjMembers());
		});
	}
	

}





