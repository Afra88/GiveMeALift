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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="LIFT")
public class Lift extends DomainObject {

	@Column(name="COST")
	private Integer cost;
	@Column(name="N_SEAT")
	private Integer nSeat;
	@Column(name="POSSIBLE_DETOUR")
	private Boolean possibleDetour;

	private LiftPoint pickUpPoint;
	private LiftPoint dropOffPoint;
	private List<LiftDetour> detours;
	private LiftPreference liftPreferences;
	private List<User> usersBookingList;
	private List<User> usersOfferingList;
	

	public Lift(){
		detours=new LinkedList<LiftDetour>();
		usersBookingList=new LinkedList<User>();
		usersOfferingList=new LinkedList<User>();
	}		
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LIFT_ID")
	public long getId() {return super.getId();};
	
	
	
	public Boolean getPossibleDetour() {
		return possibleDetour;
	}

	public void setPossibleDetour(Boolean possibleDetour) {
		this.possibleDetour = possibleDetour;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public void setnSeat(Integer nSeat) {
		this.nSeat = nSeat;
	}

	public void setUsersOfferingList(List<User> usersOfferingList) {
		this.usersOfferingList = usersOfferingList;
	}

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

	@OneToMany(fetch=FetchType.LAZY) //ok
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

	@ManyToOne(fetch=FetchType.LAZY) //cambiato
	@JoinTable(name = "PREFERENCE_LIFT_JOIN", 
				joinColumns = { @JoinColumn(name = "LIFT_ID") }, 
				inverseJoinColumns = { @JoinColumn(name = "LIFT_PREFERENCE_ID")}
			   )
	public LiftPreference getLiftPreferences() {
		return liftPreferences;
	}

	public void setLiftPreferences(LiftPreference liftPreferences) {
		this.liftPreferences = liftPreferences;
	}


	@Override
	public void copy(DomainObject object2) {
		Lift l=(Lift) object2;
		if(l.cost!=null)
			this.cost=l.cost;
		if(l.dropOffPoint!=null)
			this.dropOffPoint=l.dropOffPoint;
		if(l.pickUpPoint!=null)
			this.pickUpPoint=l.pickUpPoint;
		if(l.nSeat!=null)
			this.nSeat=l.nSeat;
		if(this.possibleDetour!=null)
			this.possibleDetour=l.possibleDetour;
		if(this.detours!=null)
			this.detours=l.detours;
		if(this.liftPreferences!=null)
			this.liftPreferences=l.liftPreferences;
		if(this.usersBookingList!=null)
			this.usersBookingList=l.usersBookingList;
		if(this.usersOfferingList!=null)
			this.usersOfferingList=l.usersOfferingList;
	}

}
