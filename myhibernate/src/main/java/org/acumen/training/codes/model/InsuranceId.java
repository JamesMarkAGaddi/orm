package org.acumen.training.codes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

// embeddable

@Embeddable
public class InsuranceId {

	private Integer id;
	private Integer policyid;
	
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "policyid", unique = true, nullable = false)
	public Integer getPolicyid() {
		return policyid;
	}
	public void setPolicyid(Integer policyid) {
		this.policyid = policyid;
	}
	
	@Override
	public String toString() {
		return "%d %d".formatted(id, policyid);
	}
	
	
}
