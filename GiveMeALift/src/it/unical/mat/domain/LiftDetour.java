package it.unical.mat.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LIFT_DETOUR")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class LiftDetour extends DomainObject {
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PICK_UP")
	private LiftPoint pickUpPoint;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="DROP_OFF")
	private LiftPoint dropOffPoint;
	
	public LiftDetour(){
		pickUpPoint=null;
		dropOffPoint=null;
	}
	
	public LiftPoint getPickUpPoint() {
		return pickUpPoint;
	}



	public void setPickUpPoint(LiftPoint pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}



	public LiftPoint getDropOffPoint() {
		return dropOffPoint;
	}



	public void setDropOffPoint(LiftPoint dropOffPoint) {
		this.dropOffPoint = dropOffPoint;
	}



	@Override
	public void copy(DomainObject object2) {
		LiftDetour ld=(LiftDetour)object2;
		this.dropOffPoint=ld.dropOffPoint;
		this.pickUpPoint=ld.pickUpPoint;

	}

}
