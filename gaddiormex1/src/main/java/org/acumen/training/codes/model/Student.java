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
@Table(name = "student")
public class Student {
	private String id;
	private String name;
	private String deptName;
	private Integer totCred;

	private Department department;

	private Set<Advisor> advisor = new HashSet<>();
	private Set<Takes> takes = new HashSet<>();

	public Student() {
	}

	public Student(String id, String name) {
		this.id = id;
		this.name = name;
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

	@Column(name = "tot_cred")
	public Integer getTotCred() {
		return totCred;
	}

	public void setTotCred(Integer totCred) {
		this.totCred = totCred;
	}

	@ManyToOne
	@JoinColumn(name = "dept_name", insertable = false, updatable = false)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST }, orphanRemoval = false)
	public Set<Advisor> getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Set<Advisor> advisor) {
		this.advisor = advisor;
	}

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST }, orphanRemoval = false)
	public Set<Takes> getTakes() {
		return takes;
	}

	public void setTakes(Set<Takes> takes) {
		this.takes = takes;
	}

	@Override
	public String toString() {
		return "%s %s %s %d".formatted(id, name, deptName, totCred);
	}

	@PreRemove
	public void setParentNull() {
		advisor.forEach((c) -> {
			c.setStudent(null);
		});
		takes.forEach((c) -> {
			c.setStudent(null);
		});

	}
}
