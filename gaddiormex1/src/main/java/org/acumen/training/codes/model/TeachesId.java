package org.acumen.training.codes.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TeachesId implements Serializable {
	private static final long serialVersionUID = 1L;
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

	@Override
	public String toString() {
		return "%s %s %s %s %d".formatted(id, courseId, secId, semester, year);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof TeachesId))
			return false;
		TeachesId that = (TeachesId) o;
		return Objects.equals(id, that.id) && Objects.equals(courseId, that.courseId)
				&& Objects.equals(secId, that.secId) && Objects.equals(semester, that.semester)
				&& Objects.equals(year, that.year);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, courseId, secId, semester, year);
	}
}
