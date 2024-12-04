package org.acumen.training.codes.test;

import java.util.List;

import org.acumen.training.codes.SailorConfiguration;
import org.acumen.training.codes.dao.SailorDao;
import org.acumen.training.codes.model.tables.Sailor;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSailorDao {
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
	public void testInsertSailor() {
		DSLContext ctx = config.createDSL(url, user, password);
		SailorDao dao = new SailorDao(ctx);
		dao.insertSailor(32, "Matthew", 10, 85);
	}

	@Test
	public void testDeleteSailor() {
		DSLContext ctx = config.createDSL(url, user, password);
		SailorDao dao = new SailorDao(ctx);
		dao.deleteSailor(32);
	}

	@Test
	public void testfindSailorsByRatingAndAge() {
		DSLContext ctx = config.createDSL(url, user, password);
		SailorDao dao = new SailorDao(ctx);

		List<Record1<String>> sailors = dao.findSailorsByRatingAndAge(7, 35, 66);
		sailors.forEach(sailor -> System.out.println("Sailor name: %s".formatted(sailor.value1())));

	}

	@Test
	public void testfindSailorsByBoat() {
		DSLContext ctx = config.createDSL(url, user, password);
		SailorDao dao = new SailorDao(ctx);

		List<Record2<Integer, Integer>> sailors = dao.findSailorsByBoat(103);
		sailors.forEach(sailor -> System.out.println("Rating: %d Age: %d".formatted(sailor.value1(), sailor.value2())));

	}

	@Test
	public void testfindSailorsByBoatIds() {
		DSLContext ctx = config.createDSL(url, user, password);
		SailorDao dao = new SailorDao(ctx);

		List<Integer> sailorIds = dao.findSailorsByBoatIds(103, 104);
		sailorIds.forEach(id -> System.out.println("Sailor ID: " + id));

	}

	@Test
	public void testsailorsWhoReservedAllRedBoats() {
		DSLContext ctx = config.createDSL(url, user, password);
		SailorDao dao = new SailorDao(ctx);

		List<Record2<Integer, String>> sailors = dao.sailorsWhoReservedAllRedBoats();
		sailors.forEach(sailor -> System.out
				.println("Sailor ID: %d Name: %s".formatted(sailor.get(Sailor.SAILOR.ID), sailor.get(Sailor.SAILOR.NAME))));
	}
}
