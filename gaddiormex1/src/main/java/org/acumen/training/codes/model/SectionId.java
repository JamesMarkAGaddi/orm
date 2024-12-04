package org.acumen.training.codes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SectionId {
	private String course_id;
	private String sec_id;
	private String semester;
	private Integer year;

	@Column(name = "course_id", unique = true, nullable = false, length = 8)
	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	@Column(name = "sec_id", unique = true, nullable = false, length = 8)
	public String getSec_id() {
		return sec_id;
	}

	public void setSec_id(String sec_id) {
		this.sec_id = sec_id;
	}

	@Column(name = "semester", unique = true, nullable = false, length = 6)
	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	@Column(name = "year", unique = true, nullable = false)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "%s %s %s %d".formatted(course_id, sec_id, semester, year);
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SectionId))
			return false;
		SectionId castOther = (SectionId) other;

		return ((this.getCourse_id() == castOther.getCourse_id()) || (this.getCourse_id() != null
				&& castOther.getCourse_id() != null && this.getCourse_id().equals(castOther.getCourse_id())))
				&& ((this.getSec_id() == castOther.getSec_id()) || (this.getSec_id() != null
						&& castOther.getSec_id() != null && this.getSec_id().equals(castOther.getSec_id())))
				&& ((this.getSemester() == castOther.getSemester()) || (this.getSemester() != null
						&& castOther.getSemester() != null && this.getSemester().equals(castOther.getSemester())))
				&& (this.getYear() == castOther.getYear());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCourse_id() == null ? 0 : this.getCourse_id().hashCode());
		result = 37 * result + (getSec_id() == null ? 0 : this.getSec_id().hashCode());
		result = 37 * result + (getSemester() == null ? 0 : this.getSemester().hashCode());
		result = 37 * result + this.getYear();
		return result;
	}

}
