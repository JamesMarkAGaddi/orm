package org.acumen.training.codes.model;

import java.time.LocalDate;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


@Entity
@NamedQueries(value = {
		@NamedQuery(name = "updateManagerFNameAge", 
				    query = "update Manager m set m.firstname = :fname, m.age = :age "
				    		+ "where m.id = :id"),
		@NamedQuery(name = "selectAllManager", query = "from Manager m")
		
     }
)

@NamedNativeQueries(
	@NamedNativeQuery(name = "selectSelfJoin", 
	    query = "select * from manager mgr inner join manager child "
				+ "on child.supervisor = mgr.id")
	)
@Table(catalog = "hrms2", name = "manager")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Manager {
	
	private Integer id;
	private String firstname;
	private Character gender;
	private Integer age;
	private Double salary;
	private LocalDate birthday;
	private Integer supervisor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "firstname", nullable = false, length = 100)
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column(name = "gender", nullable = false)
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	@Column(name = "age", nullable = false)
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Column(name = "salary", nullable = false, precision = 3)
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	@Column(name = "birthday", nullable = false)
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "supervisor", nullable = false)
	public Integer getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Integer supervisor) {
		this.supervisor = supervisor;
	}
	
	@Override
	public String toString() {
		
		return "%d %s %d %f".formatted(id, firstname, age, salary);
	}
	

}
