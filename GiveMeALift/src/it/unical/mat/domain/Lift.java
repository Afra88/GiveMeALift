package it.unical.mat.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="LIFT")
public class Lift extends DomainObject {

	@Column(name="COST")
	private Integer cost;
//	@Column(name="DETOURS_COST")
//	private String detoursCost;
	@Column(name="N_SEAT")
	private Integer nSeat;
//	@Column(name="POSSIBLE_DETOUR") 
//	private Boolean possibleDetour;
	@Column(name="DEPARTURE_TIME")
	private Time departureTime;
	@Column(name="DEPARTURE_DATE")
	private Date departureDate;
	@Column(name="DESCRIPTION") 
	private String description;
	@Column(name="DEVIATION")
	private String deviation;
	@Column(name="IS_RETURN")
	private Boolean isReturn;
	
	
	private LiftPoint pickUpPoint;
	private LiftPoint dropOffPoint;
	private List<LiftDetour> detours;
	private LiftPreference liftPreferences;
	private List<User> usersBookingList;
	private User userOffering;
	private Lift returnLift;
	

	public Lift(){
		detours=new LinkedList<LiftDetour>();
		usersBookingList=new LinkedList<User>();
	}	
	
	public Lift(Integer cost, Integer nSeat, Boolean possibleDetour,
			Time departureTime, Date departureDate, LiftPoint pickUpPoint,
			LiftPoint dropOffPoint){
		this.cost = cost;
		this.nSeat = nSeat;
//		this.possibleDetour = possibleDetour;
		this.departureTime = departureTime;
		this.departureDate = departureDate;
		this.pickUpPoint = pickUpPoint;
		this.dropOffPoint = dropOffPoint;
	
	}
	
	
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LIFT_ID")
	public long getId() {return super.getId();};
	
	
//	public String getDetoursCost() {
//		return detoursCost;
//	}
//	
//	public void setDetoursCost(String detoursCost) {
//		this.detoursCost = detoursCost;
//	}

//	public Boolean getPossibleDetour() {
//		return possibleDetour;
//	}

//	public void setPossibleDetour(Boolean possibleDetour) {
//		this.possibleDetour = possibleDetour;
//	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public void setnSeat(Integer nSeat) {
		this.nSeat = nSeat;
	}

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL) //FIXME quando è fatto detour cost
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

	@ManyToOne(fetch=FetchType.LAZY) //ok
	@JoinTable(name = "LIFT_USER_OFFERING",
				joinColumns = { @JoinColumn (name = "LIFT_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public User getUserOffering() {
		return userOffering;
	}
	
	public void setUsersBookingList(List<User> usersBookingList) {
		this.usersBookingList = usersBookingList;
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




//	public boolean isPossibleDetour() {
//		if(possibleDetour != null)
//			return true;
//		return false;
//	}


	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name = "LIFT_PICK_UP_POINT_JOIN",
				joinColumns = { @JoinColumn (name = "LIFT_ID") },
				inverseJoinColumns = { @JoinColumn(name = "LIFT_POINT_ID") })
	public LiftPoint getPickUpPoint() {
		return pickUpPoint;
	}




	public void setPickUpPoint(LiftPoint pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}



	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name = "LIFT_DROP_OFF_POINT_JOIN",
				joinColumns = { @JoinColumn (name = "LIFT_ID") },
				inverseJoinColumns = { @JoinColumn(name = "LIFT_POINT_ID") })
	public LiftPoint getDropOffPoint() {
		return dropOffPoint;
	}

	public void setDropOffPoint(LiftPoint dropOffPoint) {
		this.dropOffPoint = dropOffPoint;
	}

	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
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

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}	

	public String getDeviation() {
		return deviation;
	}
	
	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}
	
	@Override
	public void copy(DomainObject object2) {
		Lift l=(Lift) object2;
//		if(l.possibleDetour!=null)
//			this.possibleDetour=l.possibleDetour;
		if(l.cost!=null)
			this.cost=l.cost;
		if(l.departureDate!=null)
			this.departureDate=l.departureDate;
		if(l.departureTime!=null)
			this.departureTime=l.departureTime;
//		if(l.detoursCost!=null) //FIXME
//			this.detoursCost=l.detoursCost;
		if(l.userOffering!=null)
			this.userOffering=l.userOffering;
		if(l.dropOffPoint!=null)
			this.dropOffPoint=l.dropOffPoint;
		if(l.pickUpPoint!=null)
			this.pickUpPoint=l.pickUpPoint;
		if(l.nSeat!=null)
			this.nSeat=l.nSeat;
		if(l.detours!=null)
			this.detours=l.detours;
		if(l.liftPreferences!=null)
			this.liftPreferences=l.liftPreferences;
		if(l.usersBookingList!=null)
			this.usersBookingList=l.usersBookingList;
		if(l.returnLift!=null)
			this.returnLift=l.returnLift;
		if(l.description!=null)
			this.description=l.description;
		if(l.deviation!=null)
			this.deviation=l.deviation;
		if(l.isReturn!=null)
			this.isReturn=l.isReturn;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
	public Time getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public void setUserOffering(User userOffering) {
		this.userOffering = userOffering;
	}
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="RETURN_LIFT_ID",nullable=true)
	public Lift getReturnLift() {
		return returnLift;
	}
	
	public void setReturnLift(Lift returnLift) {
		this.returnLift = returnLift;
	}
	
	public Boolean getIsReturn() {
		return isReturn;
	}
	
	public void setIsReturn(Boolean isReturn) {
		this.isReturn = isReturn;
	}
	

	public List<String> computeRoute(){
		List<String> route=new ArrayList<String>();
		String city="";
		for (LiftDetour ld : this.detours) {
			city=ld.getPickUpPoint().getCity(); //FIXME only city is used
			if(!route.contains(city))
				route.add(city);
		}
		city=this.dropOffPoint.getCity();
		if(!route.contains(city))
			route.add(city);
		return route;
		
	}
	
	public List<String> computeRouteOnlyDetours(){
		List<String> route=new ArrayList<String>();
		String city="";
		for (LiftDetour ld : this.detours) {
			city=ld.getPickUpPoint().getCity(); //FIXME only city is used
			if(!route.contains(city) && !city.equals(pickUpPoint.getCity()) && !city.equals(dropOffPoint.getCity()))
				route.add(city);
		}
		return route;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result
				+ ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result
				+ ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((detours == null) ? 0 : detours.hashCode());
		result = prime * result
				+ ((deviation == null) ? 0 : deviation.hashCode());
		result = prime * result
				+ ((dropOffPoint == null) ? 0 : dropOffPoint.hashCode());
		result = prime * result
				+ ((isReturn == null) ? 0 : isReturn.hashCode());
		result = prime * result
				+ ((liftPreferences == null) ? 0 : liftPreferences.hashCode());
		result = prime * result + ((nSeat == null) ? 0 : nSeat.hashCode());
		result = prime * result
				+ ((pickUpPoint == null) ? 0 : pickUpPoint.hashCode());
		result = prime * result
				+ ((returnLift == null) ? 0 : returnLift.hashCode());
		result = prime * result
				+ ((userOffering == null) ? 0 : userOffering.hashCode());
		result = prime
				* result
				+ ((usersBookingList == null) ? 0 : usersBookingList.hashCode());
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
		Lift other = (Lift) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (detours == null) {
			if (other.detours != null)
				return false;
		} else if (!detours.equals(other.detours))
			return false;
		if (deviation == null) {
			if (other.deviation != null)
				return false;
		} else if (!deviation.equals(other.deviation))
			return false;
		if (dropOffPoint == null) {
			if (other.dropOffPoint != null)
				return false;
		} else if (!dropOffPoint.equals(other.dropOffPoint))
			return false;
		if (isReturn == null) {
			if (other.isReturn != null)
				return false;
		} else if (!isReturn.equals(other.isReturn))
			return false;
		if (liftPreferences == null) {
			if (other.liftPreferences != null)
				return false;
		} else if (!liftPreferences.equals(other.liftPreferences))
			return false;
		if (nSeat == null) {
			if (other.nSeat != null)
				return false;
		} else if (!nSeat.equals(other.nSeat))
			return false;
		if (pickUpPoint == null) {
			if (other.pickUpPoint != null)
				return false;
		} else if (!pickUpPoint.equals(other.pickUpPoint))
			return false;
		if (returnLift == null) {
			if (other.returnLift != null)
				return false;
		} else if (!returnLift.equals(other.returnLift))
			return false;
		if (userOffering == null) {
			if (other.userOffering != null)
				return false;
		} else if (!userOffering.equals(other.userOffering))
			return false;
		if (usersBookingList == null) {
			if (other.usersBookingList != null)
				return false;
		} else if (!usersBookingList.equals(other.usersBookingList))
			return false;
		return true;
	}
	
	
	
}
