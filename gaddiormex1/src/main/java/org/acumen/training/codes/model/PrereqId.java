package org.acumen.training.codes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PrereqId {
	private String course_id;
	private String prereq_id;
	
	@Column(name = "course_id", unique = true, length = 8, nullable = false)
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	
	@Column(name = "prereq_id", unique = true, length = 8, nullable = false)
	public String getPrereq_id() {
		return prereq_id;
	}
	public void setPrereq_id(String prereq_id) {
		this.prereq_id = prereq_id;
	}
	
	@Override
    public String toString() {
        return "%s %s ".formatted(course_id, prereq_id);
    }
	
	
}
