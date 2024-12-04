package org.acumen.training.codes.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Cacheable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "hrms2", name = "insurance")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Insurance {
	
	private InsuranceId insuranceId;
	// instances

	@EmbeddedId
	public InsuranceId getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(InsuranceId insuranceId) {
		this.insuranceId = insuranceId;
	}
	
	

}
