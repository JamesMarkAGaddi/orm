package org.acumen.training.codes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ClassroomId {

	private String building;
	private String roomNumber;

	public ClassroomId() {
	}

	public ClassroomId(String building, String roomNumber) {
		this.building = building;
		this.roomNumber = roomNumber;
	}

	@Column(name = "building", unique = true, nullable = false, length = 15)
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	@Column(name = "room_number", unique = true, nullable = false, length = 7)
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public String toString() {
		return "%d %d".formatted(building, roomNumber);
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ClassroomId))
			return false;
		ClassroomId castOther = (ClassroomId) other;

		return ((this.getBuilding() == castOther.getBuilding()) || (this.getBuilding() != null
				&& castOther.getBuilding() != null && this.getBuilding().equals(castOther.getBuilding())))
				&& ((this.getRoomNumber() == castOther.getRoomNumber())
						|| (this.getRoomNumber() != null && castOther.getRoomNumber() != null
								&& this.getRoomNumber().equals(castOther.getRoomNumber())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBuilding() == null ? 0 : this.getBuilding().hashCode());
		result = 37 * result + (getRoomNumber() == null ? 0 : this.getRoomNumber().hashCode());
		return result;
	}

}
