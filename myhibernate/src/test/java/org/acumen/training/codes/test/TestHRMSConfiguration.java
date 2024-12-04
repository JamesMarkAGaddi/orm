package org.acumen.training.codes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.acumen.training.codes.HRMSConfiguration;
import org.hibernate.cache.ehcache.internal.EhcacheRegionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TestHRMSConfiguration {
	private HRMSConfiguration config;
	
	@BeforeEach
	public void setup() {
		
		config = new HRMSConfiguration();
		
	}
	
	@AfterEach
	public void teardown() {
		config = null;
	}
	
	@Test
	public void testDbConnectivity() {
	
		assertEquals(true, config.createServiceRegistry());
		assertNotNull(config.getSessionFactory());
		System.out.println("database connection OK");
	}
	

}
