package it.unical.mat.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="LIFT_DETOUR")
public class LiftDetour extends LiftComponent {
	
//	private LiftPoint pickUpPoint;
//	
//	private LiftPoint dropOffPoint;
//	
//	@Column(name="DETOUR_COST")
//	private Integer cost;
	
	private Lift lift;
	
	public LiftDetour(LiftPoint pickUpPoint, LiftPoint dropOffPoint, Double cost) {
		super(pickUpPoint, dropOffPoint, cost);
	}

	public LiftDetour(){
		super();
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
		return super.pickUpPoint;
	}


	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@ManyToOne(fetch=FetchType.LAZY ) //cascade=CascadeType.ALL //cambiato
	@JoinColumn(name="DROP_OFF")
	public LiftPoint getDropOffPoint() {
		return super.dropOffPoint;
	}


	@Override
	public void copy(DomainObject object2) {
		super.copy(object2);
		LiftDetour ld=(LiftDetour)object2;
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
	
	@Column(name="DETOUR_COST")
	public Double getCost() {
		return cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lift == null) ? 0 : lift.hashCode());
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
		if (lift == null) {
			if (other.lift != null)
				return false;
		} else if (!lift.equals(other.lift))
			return false;
		return true;
	}
	
	
	

	
}
