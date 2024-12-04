package org.acumen.training.codes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "advisor")
public class Advisor {
	private String sId;
	private String iId;
	private Student student;
	private Instructor instructor;

	@Id
	@Column(name = "s_id", nullable = false, unique = true, length = 5)
	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	@Column(name = "i_id", length = 5)
	public String getiId() {
		return iId;
	}

	public void setiId(String iId) {
		this.iId = iId;
	}

	@ManyToOne
	@JoinColumn(name = "s_id", insertable = false, updatable = false)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@ManyToOne
	@JoinColumn(name = "i_id", insertable = false, updatable = false)
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

}