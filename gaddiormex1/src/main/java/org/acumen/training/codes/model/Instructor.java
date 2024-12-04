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
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@NamedQueries({ @NamedQuery(name = "updateAllSalaries", query = "update Instructor i set i.salary = :newSalary"),
		@NamedQuery(name = "increaseSalaryForBiologyTeachers", query = "update Instructor i set i.salary = i.salary + 5000 where i.deptName = :courseTitle"),
		@NamedQuery(name = "raiseForNonBiologyTeachers", query = "update Instructor i set i.salary = i.salary * 1.10 where i.deptName <> :courseTitle") })

@NamedNativeQuery(name = "getTotalStudentsUnderEachTeacherInstructor", query = """
		select i.id, i.name, count(distinct tks.id) as total_count
		from instructor i
		join teaches t on i.id = t.id
		join takes tks on t.course_id = tks.course_id
		    and t.sec_id = tks.sec_id
		    and t.semester = tks.semester
		    and t.year = tks.year
		group by i.id, i.name
		   """)

@Entity
@Table(name = "instructor")
public class Instructor {
	private String id;
	private String name;
	private String deptName;
	private Double salary;

	private Set<Teaches> teaches = new HashSet<>();
	private Department department;

	public Instructor() {
	}

	public Instructor(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Instructor(String id, Department department, String name, Double salary) {
		this.id = id;
		this.department = department;
		this.name = name;
		this.salary = salary;
	}

	@Id
	@Column(name = "id", nullable = false, unique = true, length = 5)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", length = 20, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "dept_name", length = 20, nullable = true)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "salary")
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double updatedSalary) {
		this.salary = updatedSalary;
	}

	@ManyToOne
	@JoinColumn(name = "dept_name", insertable = false, updatable = false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST }, orphanRemoval = false)
	public Set<Teaches> getTeaches() {
		return teaches;
	}

	public void setTeaches(Set<Teaches> teaches) {
		this.teaches = teaches;
	}

	@Override
	public String toString() {
		return "%s %s %s %.2f".formatted(id, name, deptName, salary);
	}
}
