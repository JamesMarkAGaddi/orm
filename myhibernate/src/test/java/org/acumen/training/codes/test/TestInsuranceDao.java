package org.acumen.training.codes.test;

import org.acumen.training.codes.HRMSConfiguration;
import org.acumen.training.codes.dao.InsuranceDao;
import org.acumen.training.codes.model.Insurance;
import org.acumen.training.codes.model.InsuranceId;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestInsuranceDao {
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
		public void testInsert() {
			config.createServiceRegistry();
			SessionFactory sf = config.getSessionFactory();
			InsuranceId ids = new InsuranceId();
			ids.setId(2);
			ids.setPolicyid(4);
			
			Insurance ins = new Insurance();
			ins.setInsuranceId(ids);
			
			InsuranceDao dao = new InsuranceDao(sf);
			dao.insert(ins);
			// all the setters
			
			
			System.out.println("inserted 1 record");
		}
}
