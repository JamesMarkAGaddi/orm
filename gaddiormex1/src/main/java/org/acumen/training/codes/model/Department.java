package org.acumen.training.codes.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@NamedQueries({
    @NamedQuery(name = "totalteachersandavgsalaryperdepartment", query = "select d.deptName, count(i.id), avg(i.salary) from Department d join d.instructor i group by d.deptName"),
    @NamedQuery(name = "totalteachersperdepartment", query = "select d.deptName, count(i.id) from Department d join d.instructor i group by d.deptName")
})

@Entity
@Table(catalog = "university", name = "Department")
public class Department {
	private String deptName;
	private String building;
	private Double budget;

	private Set<Course> course = new HashSet<>();
	private Set<Instructor> instructor = new HashSet<>();
	private Set<Student> student = new HashSet<>();

	public Department() {
	}

	public Department(String deptName) {
		this.deptName = deptName;
	}

	public Department(String deptName, String building, Double budget) {
		this.deptName = deptName;
		this.building = building;
		this.budget = budget;
	}

	@Id
	@Column(name = "dept_name", nullable = false, unique = true, length = 20)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "building", length = 20)
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	@Column(name = "budget")
	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST }, orphanRemoval = false)
	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST }, orphanRemoval = false)
	public Set<Instructor> getInstructor() {
		return instructor;
	}

	public void setInstructor(Set<Instructor> instructor) {
		this.instructor = instructor;
	}

	@OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST }, orphanRemoval = false)
	public Set<Student> getStudent() {
		return student;
	}

	public void setStudent(Set<Student> student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "%s %s %.2f".formatted(deptName, building, budget);
	}

	@PreRemove
	public void setParentNull() {
		course.forEach((c) -> {
			c.setDepartment(null);
		});
		instructor.forEach((c) -> {
			c.setDepartment(null);
		});
		student.forEach((c) -> {
			c.setDepartment(null);
		});
	}
}
