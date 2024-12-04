package org.acumen.training.codes.test;

import java.time.LocalDate;
import java.util.List;

import org.acumen.training.codes.SailorConfiguration;
import org.acumen.training.codes.dao.ReservationDao;
import org.acumen.training.codes.model.tables.Boat;
import org.acumen.training.codes.model.tables.Reservation;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestReservationDao {
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
	public void testInsertReservation() {
		DSLContext ctx = config.createDSL(url, user, password);
		ReservationDao dao = new ReservationDao(ctx);
		dao.insertReservation(9, 32, 101, LocalDate.of(2001, 9, 8));
	}

	@Test
	public void testDeleteReservation() {
		DSLContext ctx = config.createDSL(url, user, password);
		ReservationDao dao = new ReservationDao(ctx);
		dao.deleteReservation(9);
	}

	@Test
	public void testSetReservationDateTo() {
		DSLContext ctx = config.createDSL(url, user, password);
		ReservationDao dao = new ReservationDao(ctx);
		
		List<Record3<Integer, Integer, LocalDate>> updatedReservations = dao.setReservationDateTo(LocalDate.of(2008, 2, 8), 101);
		updatedReservations.forEach(reservation -> 
		    System.out.println("Boat ID: %d Reservation Boat ID: %d Date: %s"
		    		.formatted(reservation.get(Boat.BOAT.ID), 
		    				   reservation.get(Reservation.RESERVATION.BID), 
		    				   reservation.get(Reservation.RESERVATION.DAY))));
		
	}
}
