package org.acumen.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.acumen.training.codes.SailorConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSailorConfiguration {
	private SailorConfiguration config;
	private	String url = "jdbc:postgresql://localhost:5432/hrms";
	private	String user = "postgres";
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
	public void testDbConnectivity() {
		assertNotNull(config.createDSL(url, user, password));
	}
}
