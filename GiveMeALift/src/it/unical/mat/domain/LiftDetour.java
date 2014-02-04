package it.unical.mat.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="LIFT_DETOUR")
public class LiftDetour extends DomainObject {
	
	private LiftPoint pickUpPoint;
	
	private LiftPoint dropOffPoint;
	
	@Column(name="DETOUR_COST")
	private Integer cost;
	
	private Lift lift;
	
	public LiftDetour(LiftPoint pickUpPoint, LiftPoint dropOffPoint) {
		super();
		this.pickUpPoint = pickUpPoint;
		this.dropOffPoint = dropOffPoint;
	}

	public LiftDetour(){
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LIFT_DETOUR_ID")
	public long getId() {return super.getId();};
	
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@ManyToOne(fetch=FetchType.LAZY) //cascade=CascadeType.ALL //cambiato
	@JoinColumn(name="PICK_UP")
	public LiftPoint getPickUpPoint() {
		return pickUpPoint;
	}


	public void setPickUpPoint(LiftPoint pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}


	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@ManyToOne(fetch=FetchType.LAZY ) //cascade=CascadeType.ALL //cambiato
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
		if(ld.cost!=null)
			this.cost=ld.cost;
		if(ld.lift!=null)
			this.lift=ld.lift;
	}
	
//	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL) //cascade=CascadeType.ALL
	@JoinColumn(name="lift_id")
//	@JoinTable(name = "LIFT_DETOURS_JOIN",
//				joinColumns = { @JoinColumn (name = "LIFT_DETOUR_ID") },
//				inverseJoinColumns = { @JoinColumn(name = "LIFT_ID") })
	public Lift getLift() {
		return lift;
	}

	public void setLift(Lift lift) {
		this.lift = lift;
	}
	
	public Integer getCost() {
		return cost;
	}
	
	public void setCost(Integer cost) {
		this.cost = cost;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result
				+ ((dropOffPoint == null) ? 0 : dropOffPoint.hashCode());
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
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (dropOffPoint == null) {
			if (other.dropOffPoint != null)
				return false;
		} else if (!dropOffPoint.equals(other.dropOffPoint))
			return false;
		if (pickUpPoint == null) {
			if (other.pickUpPoint != null)
				return false;
		} else if (!pickUpPoint.equals(other.pickUpPoint))
			return false;
		return true;
	}

	

	
}
