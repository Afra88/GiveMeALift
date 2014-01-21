package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LIFT_PREFERENCE")
public class LiftPreference extends DomainObject {

	@Column(name = "ROAD_TYPE")
	private String roadType;
	@Column(name = "LUGGAGE_SIZE")
	private Integer luggageSize;
	@Column(name = "SCHEDULE_FLEXIBILITY")
	private String scheduleFlexibility;
	@Column(name = "PINK_TRIP")
	private Boolean pinkTrip;
	
	public LiftPreference() {
	}
	
	
	public LiftPreference(String roadType, Integer luggageSize, String scheduleFlexibility,
			Boolean pinkTrip) {
		this.roadType = roadType;
		this.luggageSize = luggageSize;
		this.scheduleFlexibility = scheduleFlexibility;
		this.pinkTrip = pinkTrip;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LIFT_PREFERENCE_ID")
	public long getId() {
		return super.getId();
	}



	public String getRoadType() {
		return roadType;
	}



	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}


	public String getScheduleFlexibility() {
		return scheduleFlexibility;
	}



	public void setScheduleFlexibility(String scheduleFlexibility) {
		this.scheduleFlexibility = scheduleFlexibility;
	}



	public Boolean getPinkTrip() {
		return pinkTrip;
	}



	public void setPinkTrip(Boolean pinkTrip) {
		this.pinkTrip = pinkTrip;
	}



	@Override
	public void copy(DomainObject object2) {
		LiftPreference lp = (LiftPreference) object2;
		if(lp.roadType != null)
			this.roadType = lp.roadType;
		if(lp.luggageSize != null)
			this.luggageSize = lp.luggageSize;
		if(lp.scheduleFlexibility != null)
			this.scheduleFlexibility = lp.scheduleFlexibility;
		if(lp.pinkTrip != null)
			this.pinkTrip = lp.pinkTrip;
	}


	public void setLuggageSize(Integer luggageSize) {
		this.luggageSize = luggageSize;
	}
	
	public Integer getLuggageSize() {
		return luggageSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((luggageSize == null) ? 0 : luggageSize.hashCode());
		result = prime * result
				+ ((pinkTrip == null) ? 0 : pinkTrip.hashCode());
		result = prime * result
				+ ((roadType == null) ? 0 : roadType.hashCode());
		result = prime
				* result
				+ ((scheduleFlexibility == null) ? 0 : scheduleFlexibility
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LiftPreference other = (LiftPreference) obj;
		if (luggageSize == null) {
			if (other.luggageSize != null)
				return false;
		} else if (!luggageSize.equals(other.luggageSize))
			return false;
		if (pinkTrip == null) {
			if (other.pinkTrip != null)
				return false;
		} else if (!pinkTrip.equals(other.pinkTrip))
			return false;
		if (roadType == null) {
			if (other.roadType != null)
				return false;
		} else if (!roadType.equals(other.roadType))
			return false;
		if (scheduleFlexibility == null) {
			if (other.scheduleFlexibility != null)
				return false;
		} else if (!scheduleFlexibility.equals(other.scheduleFlexibility))
			return false;
		return true;
	}

	
	
}
