package it.unical.mat.domain;

import javax.persistence.Entity;

@Entity
public class LiftPreferences extends DomainObject {

	private String roadType;
	private int timesForThisRoute;
	private int luggageSize;
	private Boolean scheduleFlexibility;
	private Boolean pinkTrip;
	
	public LiftPreferences() {
		this.roadType = null;
		this.timesForThisRoute = 0;
		this.luggageSize = 0;
		this.scheduleFlexibility = false;
		this.pinkTrip = false;
	}
	
	

	@Override
	public void copy(DomainObject object2) {
		// TODO Auto-generated method stub

	}

}
