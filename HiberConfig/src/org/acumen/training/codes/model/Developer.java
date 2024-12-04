package org.acumen.training.codes.model;
// Generated 24 Oct 2024, 1:17:56 pm by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Developer generated by hbm2java
 */
@Entity
@Table(name = "developer", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "employeeid"))
public class Developer implements java.io.Serializable {

	private int id;
	private Manager manager;
	private short employeeid;

	public Developer() {
	}

	public Developer(int id, Manager manager, short employeeid) {
		this.id = id;
		this.manager = manager;
		this.employeeid = employeeid;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "devid", nullable = false)
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Column(name = "employeeid", unique = true, nullable = false)
	public short getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(short employeeid) {
		this.employeeid = employeeid;
	}

}
