package org.acumen.training.codes;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UnivConfiguration {

	private Configuration cfg;

	// Step1 : setup the configuration instance
	public boolean createConfiguration() {
		try {
			cfg = new Configuration().configure();
			System.out.println("Configuration done");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Step2 : setup the session factory
	public SessionFactory getSessionFactory() {
		try {
			SessionFactory sf = cfg.buildSessionFactory();
			System.out.println("Session built done");
			return sf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
