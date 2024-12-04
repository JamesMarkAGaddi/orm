package org.acumen.training.codes.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import org.acumen.training.codes.model.tables.Project;
import org.acumen.training.codes.model.tables.ProjectMembers;
import org.acumen.training.codes.model.tables.records.ProjectRecord;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Record5;
import org.jooq.Result;

public class ProjectDao {
	private DSLContext ctx;
	private static final Logger LOGGER = Logger.getLogger(ProjectDao.class.getName());
	
	public ProjectDao(DSLContext ctx) {
		this.ctx = ctx;
	}
	
	public boolean insertQuery(Integer projid, String projname, LocalDate projdate) {
		
		try {
			int rows = ctx.transactionResult((cfg) -> {
				// automatic commit, automatic rollback
				int count = cfg.dsl().insertInto(Project.PROJECT)
						.set(Project.PROJECT.PROJID, projid.shortValue())
						.set(Project.PROJECT.PROJNAME, projname)
						.set(Project.PROJECT.PROJDATE, projdate)
		                .execute();
				return count;
			});
			
			LOGGER.info("No. of rows: %d".formatted(rows));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertDomain(Integer projid, String projname, LocalDate projdate) {
		try {
			int rows = ctx.transactionResult((cfg) -> {
				// automatic commit, automatic rollback
				
				ProjectRecord rec = new ProjectRecord();
				rec.setProjid(projid.shortValue());
				rec.setProjname(projname);
				rec.setProjdate(projdate);
				rec.attach(cfg);
				int count = rec.insert();
				return count;
			});
			
			LOGGER.info("No. of rows: %d".formatted(rows));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean updateProjNameQuery(String newProjname, Integer id) {
		try {
			int rows = ctx.transactionResult((cfg) -> {
				// automatic commit, automatic rollback
				int count = cfg.dsl().update(Project.PROJECT)
						.set(Project.PROJECT.PROJNAME, newProjname)
						.where(Project.PROJECT.PROJID.equal(id.shortValue()))
		                .execute();
				return count;
			});
			
			LOGGER.info("No. of rows: %d".formatted(rows));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateDomain(String newProjname, Integer id) {
		try {
			int rows = ctx.transactionResult((cfg) -> {
				// automatic commit, automatic rollback
				
				ProjectRecord rec = new ProjectRecord();
				rec.setProjid(id.shortValue()); // PK
				rec.setProjname(newProjname);
				
				rec.attach(cfg);
				int count = rec.update();
				return count;
			});
			
			LOGGER.info("No. of rows: %d".formatted(rows));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteByIdQuery(Integer id) {
		try {
			int rows = ctx.transactionResult((cfg) -> {
				// automatic commit, automatic rollback
				int count = cfg.dsl().deleteFrom(Project.PROJECT)
						.where(Project.PROJECT.PROJID.equal(id.shortValue()))
		                .execute();
				return count;
			});
			
			LOGGER.info("No. of rows: %d".formatted(rows));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean deleteByIdDomain(Integer id) {
		try {
			int rows = ctx.transactionResult((cfg) -> {
				// automatic commit, automatic rollback
				ProjectRecord rec = new ProjectRecord();
				rec.setProjid(id.shortValue()); // PK
				
				rec.attach(cfg);
				int count = rec.delete();
				return count;
			
			});
			
			LOGGER.info("No. of rows: %d".formatted(rows));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<ProjectRecord> selectAllProjectQuery(){
	    Result<ProjectRecord> recs = ctx.selectFrom(Project.PROJECT).fetch();
		return recs.into(ProjectRecord.class);
	}
	
	public List<Record2<String, LocalDate>> projectProjNameDate(){
		List<Record2<String, LocalDate>> recs = 
				ctx.select(Project.PROJECT.PROJNAME, Project.PROJECT.PROJDATE)
				.from(Project.PROJECT)
		        .fetch();
		return recs;
	}
	
	public ProjectRecord selectSingleProject(Integer id) {
		ProjectRecord rec = ctx.selectFrom(Project.PROJECT)
				               .where(Project.PROJECT.PROJID.equal(id.shortValue()))
		                       .fetchSingle();
		return rec;
	}
	
	public List<Record5<Short, String, Integer, String, String>> joinProjectMembers(){
		List<Record5<Short, String, Integer, String, String>> recs =
				ctx.select(Project.PROJECT.PROJID, Project.PROJECT.PROJNAME, 
				   ProjectMembers.PROJECT_MEMBERS.ID, 
				   ProjectMembers.PROJECT_MEMBERS.FIRSTNAME,
				   ProjectMembers.PROJECT_MEMBERS.LASTNAME)
		            .from(Project.PROJECT).rightJoin(ProjectMembers.PROJECT_MEMBERS)
		            .on(Project.PROJECT.PROJID.cast(Integer.class)
		            		.eq(ProjectMembers.PROJECT_MEMBERS
		            		.PROJID))
		            .fetch();
		return recs;
	}
	

}
