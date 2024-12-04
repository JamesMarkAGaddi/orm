package org.acumen.training.codes.model;
// Generated 24 Oct 2024, 1:17:56 pm by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Administrator generated by hbm2java
 */
@Entity
@Table(name = "administrator", schema = "public")
public class Administrator implements java.io.Serializable {

	private int id;
	private String fullname;
	private String role;

	public Administrator() {
	}

	public Administrator(int id, String fullname, String role) {
		this.id = id;
		this.fullname = fullname;
		this.role = role;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "fullname", nullable = false, length = 100)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "role", nullable = false, length = 100)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}