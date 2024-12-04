package org.acumen.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.acumen.training.codes.HRMSConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestHRMSConfiguration {
	
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
	public void testConnection() {
		String url = "jdbc:postgresql://localhost:5432/hrms2";
		String username = "postgres";
		String password = "admin2255";
		assertNotNull(cfg.createDSLContext(url, username, password));
		cfg.closeConnection();
		System.out.println("database connected....");
	}

}
