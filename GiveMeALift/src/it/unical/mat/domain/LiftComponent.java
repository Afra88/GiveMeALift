package it.unical.mat.domain;

public abstract class LiftComponent extends DomainObject {

	protected LiftPoint pickUpPoint;	
	protected LiftPoint dropOffPoint;
	protected Double cost;
	
	public LiftComponent(LiftPoint pickUpPoint, LiftPoint dropOffPoint,
			Double cost) {
		super();
		this.pickUpPoint = pickUpPoint;
		this.dropOffPoint = dropOffPoint;
		this.cost = cost;
	}
	public LiftComponent() {
		super();
	}
	
	@Override
	public void copy(DomainObject object2) {
		LiftComponent l=(LiftComponent) object2;
		if(l.dropOffPoint!=null)
			this.dropOffPoint=l.dropOffPoint;
		if(l.pickUpPoint!=null)
			this.pickUpPoint=l.pickUpPoint;
		if(l.cost!=null)
			this.cost=l.cost;
	}
	
//	public boolean add(LiftComponent c){ 
//		if((this instanceof LiftDetour))
//			return false;
//		return true;
//	}
//	public void remove(LiftComponent c);
//	public LiftComponent getLiftComponent(int i);
	
	
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
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
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
		LiftComponent other = (LiftComponent) obj;
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
