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
	@Column(name = "TIMES_FOR_THIS_ROUTE")
	private Integer timesForThisRoute;
	@Column(name = "LUGGAGE_SIZE")
	private Integer luggageSize;
	@Column(name = "SCHEDULE_FLEXIBILITY")
	private Boolean scheduleFlexibility;
	@Column(name = "PINK_TRIP")
	private Boolean pinkTrip;
	
	public LiftPreference() {
		this.roadType = null;
		this.timesForThisRoute = 0;
		this.luggageSize = 0;
		this.scheduleFlexibility = false;
		this.pinkTrip = false;
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



	public int getTimesForThisRoute() {
		return timesForThisRoute;
	}



	public void setTimesForThisRoute(int timesForThisRoute) {
		this.timesForThisRoute = timesForThisRoute;
	}



	public int getLuggageSize() {
		return luggageSize;
	}



	public void setLuggageSize(int luggageSize) {
		this.luggageSize = luggageSize;
	}



	public Boolean getScheduleFlexibility() {
		return scheduleFlexibility;
	}



	public void setScheduleFlexibility(Boolean scheduleFlexibility) {
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
		if(lp.timesForThisRoute != null)
			this.timesForThisRoute = lp.timesForThisRoute;
		if(lp.luggageSize != null)
			this.luggageSize = lp.luggageSize;
		if(lp.scheduleFlexibility != false)
			this.scheduleFlexibility = lp.scheduleFlexibility;
		if(lp.pinkTrip != false)
			this.pinkTrip = lp.pinkTrip;
	}

}
