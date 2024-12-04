package org.acumen.training.codes;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HRMSConfiguration {

	// Level 1 caching
	// private Configuration cfg;
	private ServiceRegistry sr;

	// Step 1: Setup the Configuration instance
	/*
	 public boolean createConfiguration() { 
	   try { 
	      cfg = new Configuration().configure();
	      return true; 
	   } catch(Exception e) { e.printStackTrace(); } return false; }
	 */
	
	public boolean createServiceRegistry() {
		try {
			//Properties props = new Properties();
			//props.put("connection.driver_class", Driver.class);
			sr = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					//.applySettings(props)
					.build();
		
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Step 2: Setup the SessionFactory
	public SessionFactory getSessionFactory() {
		try {
			Metadata metadata = new MetadataSources(sr).getMetadataBuilder().build();
			SessionFactory sf = metadata.buildSessionFactory();
			return sf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
