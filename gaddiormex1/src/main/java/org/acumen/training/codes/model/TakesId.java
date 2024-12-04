package org.acumen.training.codes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TakesId {
	private String id;
	private String courseId;
	private String secId;
	private String semester;
	private Integer year;

	@Column(name = "id", unique = true, length = 5, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "course_id", unique = true, length = 8, nullable = false)
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Column(name = "sec_id", unique = true, length = 8, nullable = false)
	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	@Column(name = "semester", unique = true, length = 6, nullable = false)
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TakesId))
			return false;
		TakesId castOther = (TakesId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getCourseId() == castOther.getCourseId()) || (this.getCourseId() != null
						&& castOther.getCourseId() != null && this.getCourseId().equals(castOther.getCourseId())))
				&& ((this.getSecId() == castOther.getSecId()) || (this.getSecId() != null
						&& castOther.getSecId() != null && this.getSecId().equals(castOther.getSecId())))
				&& ((this.getSemester() == castOther.getSemester()) || (this.getSemester() != null
						&& castOther.getSemester() != null && this.getSemester().equals(castOther.getSemester())))
				&& (this.getYear() == castOther.getYear());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getCourseId() == null ? 0 : this.getCourseId().hashCode());
		result = 37 * result + (getSecId() == null ? 0 : this.getSecId().hashCode());
		result = 37 * result + (getSemester() == null ? 0 : this.getSemester().hashCode());
		result = 37 * result + this.getYear();
		return result;
	}

}
