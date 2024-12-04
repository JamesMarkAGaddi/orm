package org.acumen.training.codes.model;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "section")
public class Section {
	private SectionId sectionId;

	private String building;
	private String roomNumber;
	private String timeSlotId;
	
    private Classroom classroom;
    private Course course;
	
    @EmbeddedId
	public SectionId getSectionId() {
		return sectionId;
	}
	
	public void setSectionId(SectionId sectionId) {
		this.sectionId = sectionId;
	}
	
    @Column(name = "building", length = 15, nullable = true)
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
    @Column(name = "room_number", length = 7, nullable = true)
	public String getroomNumber() {
		return roomNumber;
	}
	
	public void setroomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
    @Column(name = "time_slot_id", length = 4)
	public String gettimeSlotId() {
		return timeSlotId;
	}
	
	public void settimeSlotId(String timeSlotId) {
		this.timeSlotId = timeSlotId;
	}
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "building", referencedColumnName = "building", insertable = false, updatable = false),
		@JoinColumn(name = "room_number", referencedColumnName = "room_number", insertable = false, updatable = false)
	})
	public Classroom getClassroom() {
		return classroom;
	}
	
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
