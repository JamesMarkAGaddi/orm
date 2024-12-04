package org.acumen.training.codes;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class HRMSConfiguration {
	private DSLContext ctx;
	private Connection conn;
    
	// Step 1 and 2 together...
	public DSLContext createDSLContext(String url, String username, String password) {
		try {
			conn = DriverManager.getConnection(url, username, password);
			ctx = DSL.using(conn, SQLDialect.POSTGRES);
			return ctx;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean closeConnection() {
		try {
			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
