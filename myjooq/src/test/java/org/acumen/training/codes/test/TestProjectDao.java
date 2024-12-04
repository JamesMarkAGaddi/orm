package org.acumen.training.codes.test;

import java.time.LocalDate;
import java.util.List;

import org.acumen.training.codes.HRMSConfiguration;
import org.acumen.training.codes.dao.ProjectDao;
import org.acumen.training.codes.model.tables.Project;
import org.acumen.training.codes.model.tables.ProjectMembers;
import org.acumen.training.codes.model.tables.records.ProjectRecord;
import org.jooq.DSLContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestProjectDao {
private HRMSConfiguration cfg;
	
	@BeforeEach
	public void setup() {
		cfg = new HRMSConfiguration();
	}
	
	@AfterEach
	public void teardown() {
		cfg = null;
	}
	
	@Test
	public void testInsertQuery() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		dao.insertQuery(11, "Proj Mgt System", LocalDate.of(2025, 11, 10));
		cfg.closeConnection();
		
	}
	
	@Test
	public void testInsertDomain() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		dao.insertDomain(12, "Kaban Mgt System", LocalDate.of(2025, 12, 12));
		cfg.closeConnection();
	}
	
	@Test
	public void testUpdateProjNameQuery() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		dao.updateProjNameQuery("KMS", 12);
		cfg.closeConnection();
	}
	
	@Test
	public void testUpdateProjNameDomain() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		dao.updateDomain("Kaban Mgt System", 12);
		cfg.closeConnection();
	}
	
	@Test
	public void testDeleteByIdQuery() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		dao.deleteByIdQuery(12);
		cfg.closeConnection();
	}
	
	@Test
	public void testDeleteByIdDomain() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		dao.deleteByIdDomain(11);
		cfg.closeConnection();
	}
	
	@Test
	public void testSelectAllProject() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		List<ProjectRecord> recs = dao.selectAllProjectQuery();
		System.out.println(recs);
		cfg.closeConnection();
	}
	
	@Test
	public void testProjectNameDate() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		dao.projectProjNameDate().forEach((rec) ->{
			System.out.println("%s %s".formatted(rec.get(Project.PROJECT.PROJNAME),
					rec.get(Project.PROJECT.PROJDATE)));
		});
		
		cfg.closeConnection();
	}
	
	@Test
	public void testSelectSingleProject() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		ProjectRecord rec = dao.selectSingleProject(5);
		System.out.println(rec);
		
		cfg.closeConnection();
	}
	
	@Test
	public void testJoinProjectMembers() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		DSLContext ctx = cfg.createDSLContext(url, username, password);
		ProjectDao dao = new ProjectDao(ctx);
		dao.joinProjectMembers().forEach((rec) ->{
			System.out.println("%d %s %d %s %s".formatted(rec.get(Project.PROJECT.PROJID),
					rec.get(Project.PROJECT.PROJNAME),
					rec.get(ProjectMembers.PROJECT_MEMBERS.ID),
					rec.get(ProjectMembers.PROJECT_MEMBERS.FIRSTNAME),
					rec.get(ProjectMembers.PROJECT_MEMBERS.LASTNAME)));
		});
		
		cfg.closeConnection();
	}
	
	

}
