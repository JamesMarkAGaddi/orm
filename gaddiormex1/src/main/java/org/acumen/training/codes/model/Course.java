package org.acumen.training.codes.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	private String courseId;
	private String title;
	private String deptName;
	private Integer credits;

	private Department department;

	private Set<Section> section = new HashSet<>();
	private Set<Prereq> prereq = new HashSet<>();

	public Course() {
	}

	public Course(String courseId) {
		this.courseId = courseId;
	}

	public Course(String courseId, Department department, String title, Integer credits) {
		this.courseId = courseId;
		this.department = department;
		this.title = title;
		this.credits = credits;
	}

	@Id
	@Column(name = "course_id", nullable = false, unique = true, length = 8)
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String course_id) {
		this.courseId = course_id;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "dept_name", length = 20, nullable = true)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "credits")
	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	@ManyToOne
	@JoinColumn(name = "dept_name", insertable = false, updatable = false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, orphanRemoval = false)
	public Set<Section> getSection() {
		return section;
	}

	public void setSection(Set<Section> section) {
		this.section = section;
	}

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }, orphanRemoval = false)
	public Set<Prereq> getPrereq() {
		return prereq;
	}

	public void setPrereq(Set<Prereq> prereq) {
		this.prereq = prereq;
	}

	@Override
	public String toString() {
		return "%s %s %s %.2f".formatted(courseId, deptName, title, credits);
	}

	@PreRemove
	public void setParentNull() {
		section.forEach((c) -> {
			c.setCourse(null);
		});
		prereq.forEach((c) -> {
			c.setCourse(null);
		});
	}
}
