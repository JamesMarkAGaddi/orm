package org.acumen.training.codes.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prereq")
public class Prereq {
	
	private PrereqId prereqId;
    private Course course;

	@EmbeddedId
	public PrereqId getPrereqId() {
		return prereqId;
	}

	public void setPrereqId(PrereqId prereqId) {
		this.prereqId = prereqId;
	}

	@ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
