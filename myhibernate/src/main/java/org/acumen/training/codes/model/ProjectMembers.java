package org.acumen.training.codes.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "hrms2", name = "project_members")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectMembers {
	private Integer id;
	private String firstname;
	private String lastname;
	private Integer projid;
	
	private Project project;
	
	@Id
	@Column(name = "id", nullable = false, unique = true) // PK
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "firstname", nullable = false, length = 60) 
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column(name = "lastname", nullable = false, length = 60) 
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Column(name = "projid", nullable = true) // PK
	public Integer getProjid() {
		return projid;
	}
	public void setProjid(Integer projid) {
		this.projid = projid;
	}
	
	@ManyToOne
	@JoinColumn(name = "projid", insertable = false, updatable = false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Override
	public String toString() {
		return "%d %s %s %d".formatted(id, firstname, lastname, projid);
	}

}
