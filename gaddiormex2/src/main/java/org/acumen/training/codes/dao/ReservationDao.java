package org.acumen.training.codes.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import org.acumen.training.codes.model.tables.Boat;
import org.acumen.training.codes.model.tables.Reservation;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Result;

public class ReservationDao {
	private static final Logger LOGGER = Logger.getLogger(ReservationDao.class.getName());
	DSLContext ctx;

	public ReservationDao(DSLContext ctx) {
		this.ctx = ctx;
	}
//	c) Insert, and delete reservation record.
	public boolean insertReservation(Integer rid, Integer sid, Integer bid, LocalDate day) {
		LOGGER.info("Start");
		try {
			int rows = ctx.transactionResult((cfg) -> {
				int count = cfg
						.dsl().insertInto(Reservation.RESERVATION)
						.set(Reservation.RESERVATION.RID, rid)
						.set(Reservation.RESERVATION.SID, sid)
						.set(Reservation.RESERVATION.BID, bid)
						.set(Reservation.RESERVATION.DAY, day)
						.execute();
				return count;
			});
			LOGGER.info("No. of rows inserted: %d".formatted(rows));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteReservation(Integer rid) {
		LOGGER.info("Start");
		try {
			int rows = ctx.transactionResult((cfg) -> {
				int count = cfg.dsl().deleteFrom(Reservation.RESERVATION)
						.where(Reservation.RESERVATION.RID.equal(rid))
						.execute();
				return count;
			});
			LOGGER.info("No. of rows deleted: %d".formatted(rows));
			return true;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
//	h) All reservation dates of boat1 must be moved to February 8, 2008.
    public List<Record3<Integer, Integer, LocalDate>> setReservationDateTo(LocalDate newDate, Integer boat1) {
        LOGGER.info("setReservationDateTo - Start");
        try {
            ctx.update(Reservation.RESERVATION)
                .set(Reservation.RESERVATION.DAY, newDate)
                .where(Reservation.RESERVATION.BID.eq(boat1))
                .execute();

            Result<Record3<Integer, Integer, LocalDate>> rec = ctx
                .select(Boat.BOAT.ID, Reservation.RESERVATION.BID, Reservation.RESERVATION.DAY)
                .from(Reservation.RESERVATION)
                .join(Boat.BOAT)
                    .on(Boat.BOAT.ID.eq(Reservation.RESERVATION.BID))
                .where(Reservation.RESERVATION.BID.eq(boat1))
                .fetch();

            LOGGER.info("Reservation dates updated for boat ID %d".formatted(boat1));
            LOGGER.info("setReservationDateTo - End");
            return rec;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in setReservationDateTo: %s".formatted(e.getMessage()));
        }
        LOGGER.info("setReservationDateTo - End");
        return List.of();
    }

}
