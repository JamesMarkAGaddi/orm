package org.acumen.training.codes.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "takes")
public class Takes {
	private TakesId id;
	private String grade;
	private Student student;

	@EmbeddedId
	public TakesId getIds() {
		return id;
	}

	public void setIds(TakesId id) {
		this.id = id;
	}

	@Column(name = "grade", length = 2)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}