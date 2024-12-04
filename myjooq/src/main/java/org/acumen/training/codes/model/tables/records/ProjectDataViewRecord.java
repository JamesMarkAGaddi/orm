/*
 * This file is generated by jOOQ.
 */
package org.acumen.training.codes.model.tables.records;


import java.time.LocalDate;

import org.acumen.training.codes.model.tables.ProjectDataView;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ProjectDataViewRecord extends TableRecordImpl<ProjectDataViewRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.project_data_view.projid</code>.
     */
    public void setProjid(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.project_data_view.projid</code>.
     */
    public Integer getProjid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.project_data_view.projname</code>.
     */
    public void setProjname(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.project_data_view.projname</code>.
     */
    public String getProjname() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.project_data_view.projdate</code>.
     */
    public void setProjdate(LocalDate value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.project_data_view.projdate</code>.
     */
    public LocalDate getProjdate() {
        return (LocalDate) get(2);
    }

    /**
     * Setter for <code>public.project_data_view.id</code>.
     */
    public void setId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.project_data_view.id</code>.
     */
    public Integer getId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.project_data_view.firstname</code>.
     */
    public void setFirstname(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.project_data_view.firstname</code>.
     */
    public String getFirstname() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.project_data_view.lastname</code>.
     */
    public void setLastname(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.project_data_view.lastname</code>.
     */
    public String getLastname() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProjectDataViewRecord
     */
    public ProjectDataViewRecord() {
        super(ProjectDataView.PROJECT_DATA_VIEW);
    }

    /**
     * Create a detached, initialised ProjectDataViewRecord
     */
    public ProjectDataViewRecord(Integer projid, String projname, LocalDate projdate, Integer id, String firstname, String lastname) {
        super(ProjectDataView.PROJECT_DATA_VIEW);

        setProjid(projid);
        setProjname(projname);
        setProjdate(projdate);
        setId(id);
        setFirstname(firstname);
        setLastname(lastname);
        resetChangedOnNotNull();
    }
}