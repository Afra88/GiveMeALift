package it.unical.mat.domain;

import java.util.LinkedList;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LIFT_DETOUR")
public class LiftDetour extends DomainObject {
	
	private LiftPoint pickUpPoint;
	
	private LiftPoint dropOffPoint;
	
	private List<Lift> liftList;
	
	public LiftDetour(LiftPoint pickUpPoint, LiftPoint dropOffPoint) {
		super();
		this.pickUpPoint = pickUpPoint;
		this.dropOffPoint = dropOffPoint;
	}

	public LiftDetour(){
		liftList=new LinkedList<Lift>();
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LIFT_DETOUR_ID")
	public long getId() {return super.getId();};
	
	
	@ManyToOne(fetch=FetchType.LAZY) //cascade=CascadeType.ALL //cambiato
	@JoinColumn(name="PICK_UP")
	public LiftPoint getPickUpPoint() {
		return pickUpPoint;
	}


	public void setPickUpPoint(LiftPoint pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}



	@ManyToOne(fetch=FetchType.LAZY) //cascade=CascadeType.ALL //cambiato
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
		if(ld.dropOffPoint!=null)
			this.dropOffPoint=ld.dropOffPoint;
		if(ld.pickUpPoint!=null)
			this.pickUpPoint=ld.pickUpPoint;
		if(ld.liftList!=null)
			this.liftList=ld.liftList;
	}
	
	@ManyToMany(fetch=FetchType.LAZY) //cascade=CascadeType.ALL
	@JoinTable(name = "LIFT_DETOURS_JOIN",
				joinColumns = { @JoinColumn (name = "LIFT_DETOUR_ID") },
				inverseJoinColumns = { @JoinColumn(name = "LIFT_ID") })
	public List<Lift> getLiftList() {
		return liftList;
	}

	public void setLiftList(List<Lift> liftList) {
		this.liftList = liftList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dropOffPoint == null) ? 0 : dropOffPoint.hashCode());
		result = prime * result
				+ ((liftList == null) ? 0 : liftList.hashCode());
		result = prime * result
				+ ((pickUpPoint == null) ? 0 : pickUpPoint.hashCode());
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
		LiftDetour other = (LiftDetour) obj;
		if (dropOffPoint == null) {
			if (other.dropOffPoint != null)
				return false;
		} else if (!dropOffPoint.equals(other.dropOffPoint))
			return false;
		if (liftList == null) {
			if (other.liftList != null)
				return false;
		} else if (!liftList.equals(other.liftList))
			return false;
		if (pickUpPoint == null) {
			if (other.pickUpPoint != null)
				return false;
		} else if (!pickUpPoint.equals(other.pickUpPoint))
			return false;
		return true;
	}
	
}
