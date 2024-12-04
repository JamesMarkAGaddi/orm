package org.acumen.training.codes.dao;

import java.util.logging.Logger;

import org.acumen.training.codes.model.tables.Boat;
import org.jooq.DSLContext;

public class BoatDao {

    private static final Logger LOGGER = Logger.getLogger(BoatDao.class.getName());
    DSLContext ctx;

    public BoatDao(DSLContext ctx) {
        this.ctx = ctx;
    }

// Insert, and delete boat record.
    public boolean insertBoat(Integer id, String name, String colour) {
        LOGGER.info("insertBoat - Start");
        try {
            int rows = ctx.transactionResult((cfg) -> {
                int count = cfg.dsl().insertInto(Boat.BOAT)
                        .set(Boat.BOAT.ID, id)
                        .set(Boat.BOAT.NAME, name)
                        .set(Boat.BOAT.COLOUR, colour)
                        .execute();
                return count;
            });
            LOGGER.info("No. of rows inserted: %d".formatted(rows));
            LOGGER.info("insertBoat - End");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in insertBoat: %s".formatted(e.getMessage()));
        }
        LOGGER.info("insertBoat - End");
        return false;
    }

    public boolean deleteBoat(Integer id) {
        LOGGER.info("deleteBoat - Start");
        try {
            int rows = ctx.transactionResult((cfg) -> {
                int count = cfg.dsl().deleteFrom(Boat.BOAT)
                        .where(Boat.BOAT.ID.equal(id))
                        .execute();
                return count;
            });
            LOGGER.info("No. of rows deleted: %d".formatted(rows));
            LOGGER.info("deleteBoat - End");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in deleteBoat: %s".formatted(e.getMessage()));
        }
        LOGGER.info("deleteBoat - End");
        return false;
    }

// Boat Interlake must be renamed to newName.
    public boolean updateInterlake(String newName) {
        LOGGER.info("updateInterlake - Start");
        try {
            int rows = ctx.transactionResult((cfg) -> {
                int count = cfg.dsl().update(Boat.BOAT)
                        .set(Boat.BOAT.NAME, newName)
                        .where(Boat.BOAT.NAME.eq("Interlake"))
                        .execute();
                return count;
            });
            LOGGER.info("No. of rows updated: %d".formatted(rows));
            LOGGER.info("updateInterlake - End");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("Exception in updateInterlake: %s".formatted(e.getMessage()));
        }
        LOGGER.info("updateInterlake - End");
        return false;
    }
}
