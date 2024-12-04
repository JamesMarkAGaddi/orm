package org.acumen.training.codes.dao;

import java.util.List;
import java.util.logging.Logger;
import org.acumen.training.codes.model.tables.Boat;
import org.acumen.training.codes.model.tables.Reservation;
import org.acumen.training.codes.model.tables.Sailor;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Result;

public class SailorDao {

    private static final Logger LOGGER = Logger.getLogger(SailorDao.class.getName());
    DSLContext ctx;

    public SailorDao(DSLContext ctx) {
        this.ctx = ctx;
    }

    // Insert, and delete sailor record.
    public boolean insertSailor(Integer id, String name, Integer rating, Integer age) {
        LOGGER.info("insertSailor - Start");
        try {
            int rows = ctx.transactionResult((cfg) -> {
                int count = cfg.dsl().insertInto(Sailor.SAILOR)
                        .set(Sailor.SAILOR.ID, id)
                        .set(Sailor.SAILOR.NAME, name)
                        .set(Sailor.SAILOR.RATING, rating)
                        .set(Sailor.SAILOR.AGE, age)
                        .execute();
                return count;
            });
            LOGGER.info("No. of rows inserted: %d".formatted(rows));
            LOGGER.info("insertSailor - End");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in insertSailor: %s".formatted(e.getMessage()));
        }
        LOGGER.info("insertSailor - End");
        return false;
    }

    public boolean deleteSailor(Integer id) {
        LOGGER.info("deleteSailor - Start");
        try {
            int rows = ctx.transactionResult((cfg) -> {
                int count = cfg.dsl().deleteFrom(Sailor.SAILOR)
                        .where(Sailor.SAILOR.ID.equal(id))
                        .execute();
                return count;
            });
            LOGGER.info("No. of rows deleted: %d".formatted(rows));
            LOGGER.info("deleteSailor - End");
            return true;    
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in deleteSailor: %s".formatted(e.getMessage()));
        }
        LOGGER.info("deleteSailor - End");
        return false;
    }

    // Find the names of sailors with a rating greater than rate and an age between age1 and age2.
    public List<Record1<String>> findSailorsByRatingAndAge(Integer rate, Integer age1, Integer age2) {        
        LOGGER.info("findSailorsByRatingAndAge - Start");
        try {
            List<Record1<String>> result = ctx
                .select(Sailor.SAILOR.NAME)
                .from(Sailor.SAILOR)
                .where(Sailor.SAILOR.RATING.gt(rate))
                .and(Sailor.SAILOR.AGE.between(age1, age2))
                .fetch();
            LOGGER.info("Result found: %s".formatted(result));
            LOGGER.info("findSailorsByRatingAndAge - End");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in findSailorsByRatingAndAge: %s".formatted(e.getMessage()));
        }
        LOGGER.info("findSailorsByRatingAndAge - End");
        return List.of();
    }

    // Find the rating and the age of the sailors who reserved a boat.
    public List<Record2<Integer, Integer>> findSailorsByBoat(Integer x) {
        LOGGER.info("findSailorsByBoat - Start");
        try {
            Result<Record2<Integer, Integer>> result = ctx
                .select(Sailor.SAILOR.RATING, Sailor.SAILOR.AGE)
                .from(Sailor.SAILOR)
                .join(Reservation.RESERVATION)
                .on(Sailor.SAILOR.ID.eq(Reservation.RESERVATION.SID))
                .where(Reservation.RESERVATION.BID.eq(x))
                .fetch();
            LOGGER.info("Result found: %s".formatted(result));
            LOGGER.info("findSailorsByBoat - End");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in findSailorsByBoat: %s".formatted(e.getMessage()));
        }
        LOGGER.info("findSailorsByBoat - End");
        return List.of();
    }

    // Find the IDs of those sailors who either reserved the boat1 or who reserved the boat2.
    public List<Integer> findSailorsByBoatIds(Integer boat1, Integer boat2) {
        LOGGER.info("findSailorsByBoatIds - Start");
        try {
            Result<Record1<Integer>> result = ctx
                .selectDistinct(Reservation.RESERVATION.SID)
                .from(Reservation.RESERVATION)
                .where(Reservation.RESERVATION.BID.eq(boat1)
                       .or(Reservation.RESERVATION.BID.eq(boat2)))
                .fetch();
            LOGGER.info("Result found: %s".formatted(result.getValues(Reservation.RESERVATION.SID)));
            LOGGER.info("findSailorsByBoatIds - End");
            return result.getValues(Reservation.RESERVATION.SID);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in findSailorsByBoatIds: %s".formatted(e.getMessage()));
        }
        LOGGER.info("findSailorsByBoatIds - End");
        return List.of();
    }

    // List those sailors who have reserved every red boat (list the id and the name only).
    public List<Record2<Integer, String>> sailorsWhoReservedAllRedBoats() {
        LOGGER.info("sailorsWhoReservedAllRedBoats - Start");
        try {
            Result<Record2<Integer, String>> result = ctx
                .select(Sailor.SAILOR.ID, Sailor.SAILOR.NAME)
                .from(Sailor.SAILOR)
                .join(Reservation.RESERVATION)
                .on(Sailor.SAILOR.ID.eq(Reservation.RESERVATION.SID))
                .join(Boat.BOAT)
                .on(Reservation.RESERVATION.BID.eq(Boat.BOAT.ID))
                .where(Boat.BOAT.COLOUR.eq("red"))
                .groupBy(Sailor.SAILOR.ID, Sailor.SAILOR.NAME)
                .fetch();
            LOGGER.info("Result found: %s".formatted(result));
            LOGGER.info("sailorsWhoReservedAllRedBoats - End");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in sailorsWhoReservedAllRedBoats: %s".formatted(e.getMessage()));
        }
        LOGGER.info("sailorsWhoReservedAllRedBoats - End");
        return List.of();
    }
}
