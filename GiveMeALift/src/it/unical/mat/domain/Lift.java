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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="LIFT")
public class Lift extends DomainObject {

	@Column(name="COST")
	private int cost;
	@Column(name="N_SEAT")
	private int nSeat;
	@Column(name="POSSIBLE_DETOUR")
	private boolean possibleDetour;

	private LiftPoint pickUpPoint;
	private LiftPoint dropOffPoint;
	private List<LiftDetour> detours=new LinkedList<LiftDetour>();
	private LiftPreferences lift_Preferences;
	private List<User> usersBookingList=new LinkedList<User>();
	private List<User> usersOfferingList=new LinkedList<User>();
	

	public Lift(){
		cost=0;
		pickUpPoint=null;
		dropOffPoint=null;
		nSeat=0;
		possibleDetour=false;
	}		
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LIFT_ID")
	public long getId() {return super.getId();};
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "LIFT_DETOURS_JOIN",
				joinColumns = { @JoinColumn (name = "LIFT_ID") },
				inverseJoinColumns = { @JoinColumn(name = "DETOUR_ID") })
	public List<LiftDetour> getDetours() {
		return detours;
	}
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "LIFT_USER_BOOKING",
				joinColumns = { @JoinColumn (name = "LIFT_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public List<User> getUsersBookingList() {
		return usersBookingList;
	}

	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "LIFT_USER_OFFERING",
				joinColumns = { @JoinColumn (name = "LIFT_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public List<User> getUsersOfferingList() {
		return usersOfferingList;
	}
	
	public void setUsersBookingList(List<User> usersBookingList) {
		this.usersOfferingList = usersBookingList;
	}


	public void setDetours(List<LiftDetour> detours) {
		this.detours = detours;
	}




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




	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public LiftPoint getPickUpPoint() {
		return pickUpPoint;
	}




	public void setPickUpPoint(LiftPoint pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}




	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public LiftPoint getDropOffPoint() {
		return dropOffPoint;
	}

	public void setDropOffPoint(LiftPoint dropOffPoint) {
		this.dropOffPoint = dropOffPoint;
	}

	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "PREFERENCES_FOR_A_LIFT", 
				joinColumns = { @JoinColumn(name = "LIFT_ID") }, 
				inverseJoinColumns = { @JoinColumn(name = "LIFT_PREFERENCES_ID")}
			   )
	public LiftPreferences getLift_Preferences() {
		return lift_Preferences;
	}

	public void setLift_Preferences(LiftPreferences lift_Preferences) {
		this.lift_Preferences = lift_Preferences;
	}


	@Override
	public void copy(DomainObject object2) {
		Lift l=(Lift) object2;
		if(this.cost!=0)
			this.cost=l.cost;
		this.dropOffPoint=l.dropOffPoint;
		this.pickUpPoint=l.pickUpPoint;
		this.nSeat=l.nSeat;
		this.possibleDetour=l.possibleDetour;
	}

}
