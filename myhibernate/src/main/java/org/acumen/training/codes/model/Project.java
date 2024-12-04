package org.acumen.training.codes.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

// JAXB parsing
@Entity
@Table(catalog = "hrms2", name = "project")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project {
	
	private Short projid;
	private String projname;
	private LocalDate projdate;
	
	private Set<ProjectMembers> projMembers = new HashSet<>();
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projid", nullable = false, unique = true)
	public Short getProjid() {
		return projid;
	}
	public void setProjid(Short projid) {
		this.projid = projid;
	}
	
	@Column(name = "projname", nullable = false, length = 200)
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}
	
	//@Temporal(TemporalType.DATE) // java.sql.Date
	@Column(name = "projdate", nullable = false)
	public LocalDate getProjdate() {
		return projdate;
	}
	public void setProjdate(LocalDate projdate) {
		this.projdate = projdate;
	}
	
	// Eager -> one-to-one, one-to-many, parent with many strict relationships
	// Lazy -> many-to-one many-to-many, relationship may not be in PK-FK rel (cross-join)
	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, 
			cascade = { CascadeType.PERSIST }, orphanRemoval = false)
	public Set<ProjectMembers> getProjMembers() {
		return projMembers;
	}
	public void setProjMembers(Set<ProjectMembers> projMembers) {
		this.projMembers = projMembers;
	}
	
	@Override
	public String toString() {
		return "%d %s %s".formatted(projid, projname, projdate);
	}
	
	@PreRemove
	public void setParentNull() {
		projMembers.forEach((c) -> {
			c.setProject(null);
		});
	}
	

}
