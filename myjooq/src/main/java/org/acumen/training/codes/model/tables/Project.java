/*
 * This file is generated by jOOQ.
 */
package org.acumen.training.codes.model.tables;


import java.time.LocalDate;
import java.util.Collection;

import org.acumen.training.codes.model.Keys;
import org.acumen.training.codes.model.Public;
import org.acumen.training.codes.model.tables.ProjectMembers.ProjectMembersPath;
import org.acumen.training.codes.model.tables.records.ProjectRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
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
public class Project extends TableImpl<ProjectRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.project</code>
     */
    public static final Project PROJECT = new Project();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProjectRecord> getRecordType() {
        return ProjectRecord.class;
    }

    /**
     * The column <code>public.project.projid</code>.
     */
    public final TableField<ProjectRecord, Short> PROJID = createField(DSL.name("projid"), SQLDataType.SMALLINT.nullable(false), this, "");

    /**
     * The column <code>public.project.projname</code>.
     */
    public final TableField<ProjectRecord, String> PROJNAME = createField(DSL.name("projname"), SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>public.project.projdate</code>.
     */
    public final TableField<ProjectRecord, LocalDate> PROJDATE = createField(DSL.name("projdate"), SQLDataType.LOCALDATE.nullable(false), this, "");

    private Project(Name alias, Table<ProjectRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Project(Name alias, Table<ProjectRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.project</code> table reference
     */
    public Project(String alias) {
        this(DSL.name(alias), PROJECT);
    }

    /**
     * Create an aliased <code>public.project</code> table reference
     */
    public Project(Name alias) {
        this(alias, PROJECT);
    }

    /**
     * Create a <code>public.project</code> table reference
     */
    public Project() {
        this(DSL.name("project"), null);
    }

    public <O extends Record> Project(Table<O> path, ForeignKey<O, ProjectRecord> childPath, InverseForeignKey<O, ProjectRecord> parentPath) {
        super(path, childPath, parentPath, PROJECT);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class ProjectPath extends Project implements Path<ProjectRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> ProjectPath(Table<O> path, ForeignKey<O, ProjectRecord> childPath, InverseForeignKey<O, ProjectRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private ProjectPath(Name alias, Table<ProjectRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public ProjectPath as(String alias) {
            return new ProjectPath(DSL.name(alias), this);
        }

        @Override
        public ProjectPath as(Name alias) {
            return new ProjectPath(alias, this);
        }

        @Override
        public ProjectPath as(Table<?> alias) {
            return new ProjectPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<ProjectRecord> getPrimaryKey() {
        return Keys.PROJECT_PKEY;
    }

    private transient ProjectMembersPath _projectMembers;

    /**
     * Get the implicit to-many join path to the
     * <code>public.project_members</code> table
     */
    public ProjectMembersPath projectMembers() {
        if (_projectMembers == null)
            _projectMembers = new ProjectMembersPath(this, null, Keys.PROJECT_MEMBERS__PROJECT_MEMBERS_PROJID_FKEY.getInverseKey());

        return _projectMembers;
    }

    @Override
    public Project as(String alias) {
        return new Project(DSL.name(alias), this);
    }

    @Override
    public Project as(Name alias) {
        return new Project(alias, this);
    }

    @Override
    public Project as(Table<?> alias) {
        return new Project(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Project rename(String name) {
        return new Project(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Project rename(Name name) {
        return new Project(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Project rename(Table<?> name) {
        return new Project(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Project where(Condition condition) {
        return new Project(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Project where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Project where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Project where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Project where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Project where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Project where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Project where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Project whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Project whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
