/*
 * This file is generated by jOOQ.
 */
package org.acumen.training.codes.model.tables.records;


import org.acumen.training.codes.model.tables.Allowance;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class AllowanceRecord extends UpdatableRecordImpl<AllowanceRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.allowance.allowanceid</code>.
     */
    public void setAllowanceid(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.allowance.allowanceid</code>.
     */
    public Integer getAllowanceid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.allowance.id</code>.
     */
    public void setId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.allowance.id</code>.
     */
    public Integer getId() {
        return (Integer) get(1);
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
     * Create a detached AllowanceRecord
     */
    public AllowanceRecord() {
        super(Allowance.ALLOWANCE);
    }

    /**
     * Create a detached, initialised AllowanceRecord
     */
    public AllowanceRecord(Integer allowanceid, Integer id) {
        super(Allowance.ALLOWANCE);

        setAllowanceid(allowanceid);
        setId(id);
        resetChangedOnNotNull();
    }
}
