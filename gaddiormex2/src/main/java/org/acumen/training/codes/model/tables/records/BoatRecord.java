/*
 * This file is generated by jOOQ.
 */
package org.acumen.training.codes.model.tables.records;


import org.acumen.training.codes.model.tables.Boat;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BoatRecord extends UpdatableRecordImpl<BoatRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.boat.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.boat.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.boat.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.boat.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.boat.colour</code>.
     */
    public void setColour(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.boat.colour</code>.
     */
    public String getColour() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BoatRecord
     */
    public BoatRecord() {
        super(Boat.BOAT);
    }

    /**
     * Create a detached, initialised BoatRecord
     */
    public BoatRecord(Integer id, String name, String colour) {
        super(Boat.BOAT);

        setId(id);
        setName(name);
        setColour(colour);
        resetChangedOnNotNull();
    }
}