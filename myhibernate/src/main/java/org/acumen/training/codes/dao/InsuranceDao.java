package org.acumen.training.codes.dao;

import org.acumen.training.codes.model.Insurance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InsuranceDao {
	private SessionFactory sf;
	
	public InsuranceDao(SessionFactory sf) {
		this.sf = sf;
	}
	
	public boolean insert(Insurance ins) {
	    Session sess = sf.openSession();
	    Transaction tx = sess.beginTransaction();
		try{
			sess.persist(ins); // INSERT INTO
			tx.commit();
			return true;
		} catch(Exception e) {
			try {
				tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				sess.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}

}
