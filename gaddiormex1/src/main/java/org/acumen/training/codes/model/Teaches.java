package org.acumen.training.codes.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "teaches")
public class Teaches {
	private TeachesId ids;
	private Instructor instructor;

	@EmbeddedId
	public TeachesId getIds() {
		return ids;
	}

	public void setIds(TeachesId ids) {
		this.ids = ids;
	}

	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false)
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

}
