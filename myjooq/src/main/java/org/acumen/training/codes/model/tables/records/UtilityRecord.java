/*
 * This file is generated by jOOQ.
 */
package org.acumen.training.codes.model.tables.records;


import org.acumen.training.codes.model.tables.Utility;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class UtilityRecord extends UpdatableRecordImpl<UtilityRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.utility.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.utility.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.utility.fullname</code>.
     */
    public void setFullname(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.utility.fullname</code>.
     */
    public String getFullname() {
        return (String) get(1);
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
     * Create a detached UtilityRecord
     */
    public UtilityRecord() {
        super(Utility.UTILITY);
    }

    /**
     * Create a detached, initialised UtilityRecord
     */
    public UtilityRecord(Integer id, String fullname) {
        super(Utility.UTILITY);

        setId(id);
        setFullname(fullname);
        resetChangedOnNotNull();
    }
}
