package org.acumen.training.codes.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "university", name = "classroom")
public class Classroom {

	private ClassroomId classroomId;
	private Short capacity;

	private Set<Section> section = new HashSet<>();

	public Classroom() {
	}

	public Classroom(ClassroomId classroomId) {
		this.classroomId = classroomId;
	}

	public Classroom(ClassroomId classroomId, Short capacity, Set<Section> section) {
		this.classroomId = classroomId;
		this.capacity = capacity;
		this.section = section;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "building", column = @Column(name = "building", nullable = false, length = 15)),
			@AttributeOverride(name = "roomNumber", column = @Column(name = "room_number", nullable = false, length = 7)) })
	public ClassroomId getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(ClassroomId classroomId) {
		this.classroomId = classroomId;
	}

	@Column(name = "capacity")
	public Short getCapacity() {
		return capacity;
	}

	public void setCapacity(Short capacity) {
		this.capacity = capacity;
	}

	@OneToMany(mappedBy = "classroom", fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST }, orphanRemoval = false)
	public Set<Section> getSection() {
		return section;
	}

	public void setSection(Set<Section> section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "%s %.2f".formatted(classroomId, capacity);
	}

	@PreRemove
	public void setParentNull() {
		section.forEach((c) -> {
			c.setClassroom(null);
		});

	}

}
