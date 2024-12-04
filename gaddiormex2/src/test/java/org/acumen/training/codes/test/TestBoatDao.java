package org.acumen.training.codes.test;

import org.acumen.training.codes.SailorConfiguration;
import org.acumen.training.codes.dao.BoatDao;
import org.jooq.DSLContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBoatDao {
	private SailorConfiguration config;
	private String url = "jdbc:postgresql://localhost:5432/sailor";
	private String user = "postgres";
	private String password = "admin2255";

	@BeforeEach
	public void setup() {
		config = new SailorConfiguration();
	}

	@AfterEach
	public void teardown() {
		config.closeConnection();
		config = null;
	}

	@Test
	public void testInsertBoat() {
		DSLContext ctx = config.createDSL(url, user, password);
		BoatDao dao = new BoatDao(ctx);
		dao.insertBoat(105, "SSMatthew", "black");
	}

	@Test
	public void testDeleteBoat() {
		DSLContext ctx = config.createDSL(url, user, password);
		BoatDao dao = new BoatDao(ctx);
		dao.deleteBoat(105);
	}
	
	@Test
	public void testUpdateInterlake() {
		DSLContext ctx = config.createDSL(url, user, password);
		BoatDao dao = new BoatDao(ctx);
		dao.updateInterlake("Interbarangay");
	}

}
