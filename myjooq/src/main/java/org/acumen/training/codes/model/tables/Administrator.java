/*
 * This file is generated by jOOQ.
 */
package org.acumen.training.codes.model.tables;


import java.util.Collection;

import org.acumen.training.codes.model.Keys;
import org.acumen.training.codes.model.Public;
import org.acumen.training.codes.model.tables.records.AdministratorRecord;
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
public class Administrator extends TableImpl<AdministratorRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.administrator</code>
     */
    public static final Administrator ADMINISTRATOR = new Administrator();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AdministratorRecord> getRecordType() {
        return AdministratorRecord.class;
    }

    /**
     * The column <code>public.administrator.id</code>.
     */
    public final TableField<AdministratorRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.administrator.fullname</code>.
     */
    public final TableField<AdministratorRecord, String> FULLNAME = createField(DSL.name("fullname"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.administrator.role</code>.
     */
    public final TableField<AdministratorRecord, String> ROLE = createField(DSL.name("role"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    private Administrator(Name alias, Table<AdministratorRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Administrator(Name alias, Table<AdministratorRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.administrator</code> table reference
     */
    public Administrator(String alias) {
        this(DSL.name(alias), ADMINISTRATOR);
    }

    /**
     * Create an aliased <code>public.administrator</code> table reference
     */
    public Administrator(Name alias) {
        this(alias, ADMINISTRATOR);
    }

    /**
     * Create a <code>public.administrator</code> table reference
     */
    public Administrator() {
        this(DSL.name("administrator"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<AdministratorRecord, Integer> getIdentity() {
        return (Identity<AdministratorRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<AdministratorRecord> getPrimaryKey() {
        return Keys.ADMINISTRATOR_PKEY;
    }

    @Override
    public Administrator as(String alias) {
        return new Administrator(DSL.name(alias), this);
    }

    @Override
    public Administrator as(Name alias) {
        return new Administrator(alias, this);
    }

    @Override
    public Administrator as(Table<?> alias) {
        return new Administrator(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Administrator rename(String name) {
        return new Administrator(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Administrator rename(Name name) {
        return new Administrator(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Administrator rename(Table<?> name) {
        return new Administrator(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Administrator where(Condition condition) {
        return new Administrator(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Administrator where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Administrator where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Administrator where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Administrator where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Administrator where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Administrator where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Administrator where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Administrator whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Administrator whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
