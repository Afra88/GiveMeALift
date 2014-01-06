package it.unical.mat.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LIFT")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Lift extends DomainObject {

	@Column(name="COST")
	private int cost;
	@Column(name="N_SEAT")
	private int nSeat;
	@Column(name="POSSIBLE_DETOUR")
	private boolean possibleDetour;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PICK_UP")
	private LiftPoint pickUpPoint;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="DROP_OFF")
	private LiftPoint dropOffPoint;

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "LIFT_DETOURS_JOIN",
//				joinColumns = { @JoinColumn (name = "ID") },
//				inverseJoinColumns = { @JoinColumn(name = "LIFT_DETOUR_ID") })
//	private List<LiftDetour> detours=new LinkedList<LiftDetour>();
	
	
	public Lift(){
		cost=0;
		pickUpPoint=null;
		dropOffPoint=null;
		nSeat=0;
		possibleDetour=false;
	}		

//	public List<LiftDetour> getDetours() {
//		return detours;
//	}
//
//
//
//
//	public void setDetours(List<LiftDetour> detours) {
//		this.detours = detours;
//	}




	public int getCost() {
		return cost;
	}




	public void setCost(int cost) {
		this.cost = cost;
	}




	public int getnSeat() {
		return nSeat;
	}




	public void setnSeat(int nSeat) {
		this.nSeat = nSeat;
	}




	public boolean isPossibleDetour() {
		return possibleDetour;
	}




	public void setPossibleDetour(boolean possibleDetour) {
		this.possibleDetour = possibleDetour;
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
		Lift l=(Lift) object2;
		this.cost=l.cost;
		this.dropOffPoint=l.dropOffPoint;
		this.pickUpPoint=l.pickUpPoint;
		this.nSeat=l.nSeat;
		this.possibleDetour=l.possibleDetour;
	}

}
