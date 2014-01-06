package it.unical.mat.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LIFT_DETOUR")
public class LiftDetour extends DomainObject {
	
	private LiftPoint pickUpPoint;
	
	private LiftPoint dropOffPoint;
	
	public LiftDetour(){
		pickUpPoint=null;
		dropOffPoint=null;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LIFT_DETOUR_ID")
	public long getId() {return super.getId();};
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PICK_UP")
	public LiftPoint getPickUpPoint() {
		return pickUpPoint;
	}



	public void setPickUpPoint(LiftPoint pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}



	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="DROP_OFF")
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
