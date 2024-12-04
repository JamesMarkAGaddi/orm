/*
 * This file is generated by jOOQ.
 */
package org.acumen.training.codes.model.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.acumen.training.codes.model.Keys;
import org.acumen.training.codes.model.Public;
import org.acumen.training.codes.model.tables.records.AllowanceRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Allowance extends TableImpl<AllowanceRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.allowance</code>
     */
    public static final Allowance ALLOWANCE = new Allowance();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AllowanceRecord> getRecordType() {
        return AllowanceRecord.class;
    }

    /**
     * The column <code>public.allowance.allowanceid</code>.
     */
    public final TableField<AllowanceRecord, Integer> ALLOWANCEID = createField(DSL.name("allowanceid"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.allowance.id</code>.
     */
    public final TableField<AllowanceRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    private Allowance(Name alias, Table<AllowanceRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Allowance(Name alias, Table<AllowanceRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.allowance</code> table reference
     */
    public Allowance(String alias) {
        this(DSL.name(alias), ALLOWANCE);
    }

    /**
     * Create an aliased <code>public.allowance</code> table reference
     */
    public Allowance(Name alias) {
        this(alias, ALLOWANCE);
    }

    /**
     * Create a <code>public.allowance</code> table reference
     */
    public Allowance() {
        this(DSL.name("allowance"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<AllowanceRecord, Integer> getIdentity() {
        return (Identity<AllowanceRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<AllowanceRecord> getPrimaryKey() {
        return Keys.ALLOWANCE_PKEY;
    }

    @Override
    public List<UniqueKey<AllowanceRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.ALLOWANCE_ID_KEY);
    }

    @Override
    public Allowance as(String alias) {
        return new Allowance(DSL.name(alias), this);
    }

    @Override
    public Allowance as(Name alias) {
        return new Allowance(alias, this);
    }

    @Override
    public Allowance as(Table<?> alias) {
        return new Allowance(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Allowance rename(String name) {
        return new Allowance(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Allowance rename(Name name) {
        return new Allowance(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Allowance rename(Table<?> name) {
        return new Allowance(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Allowance where(Condition condition) {
        return new Allowance(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Allowance where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Allowance where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Allowance where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Allowance where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Allowance where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Allowance where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Allowance where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Allowance whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Allowance whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
