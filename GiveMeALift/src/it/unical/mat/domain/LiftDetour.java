package it.unical.mat.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LIFT_DETOUR")
public class LiftDetour extends DomainObject {
	
	private LiftPoint pickUpPoint;
	
	private LiftPoint dropOffPoint;
	
	private List<Lift> liftList;
	
	public LiftDetour(){
		pickUpPoint=null;
		dropOffPoint=null;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LIFT_DETOUR_ID")
	public long getId() {return super.getId();};
	
	
	@OneToMany(fetch=FetchType.LAZY) //cascade=CascadeType.ALL
	@JoinColumn(name="PICK_UP")
	public LiftPoint getPickUpPoint() {
		return pickUpPoint;
	}


	public void setPickUpPoint(LiftPoint pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}



	@OneToMany(fetch=FetchType.LAZY) //cascade=CascadeType.ALL
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
	
	@ManyToMany(fetch=FetchType.LAZY) //cascade=CascadeType.ALL
	@JoinTable(name = "LIFT_DETOURS_JOIN",
				joinColumns = { @JoinColumn (name = "DETOUR_ID") },
				inverseJoinColumns = { @JoinColumn(name = "LIFT_ID") })
	public List<Lift> getLiftList() {
		return liftList;
	}

	public void setLiftList(List<Lift> liftList) {
		this.liftList = liftList;
	}

}
