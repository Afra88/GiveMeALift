package it.unical.mat.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="LIFT")
public class Lift extends LiftComponent {

	
	@Column(name="N_SEAT")
	private Integer nSeat;
	@Column(name="DEPARTURE_TIME")
	private Time departureTime;
	@Column(name="DEPARTURE_DATE")
	private Date departureDate;
	@Column(name="DESCRIPTION") 
	private String description;
	@Column(name="IS_RETURN")
	private Boolean isReturn;
	
	@Version
	@Column(name="OPT_LOCK")
	private int version;
	private List<LiftDetour> detours;
	private LiftPreference liftPreferences;
//	private List<User> usersBookingList;
	private RegisteredUser userOffering;
	private Lift returnLift;
	
	public Lift(){
		super();
//		detours=new LinkedList<LiftDetour>();
//		usersBookingList=new LinkedList<User>();
	}	
	
	public Lift(Double cost, Integer nSeat, Boolean possibleDetour,
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
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
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

	public void setnSeat(Integer nSeat) {
		this.nSeat = nSeat;
	}
	
	public Integer getnSeat() {
		return nSeat;
	}
	
	

//	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@OneToMany(fetch=FetchType.LAZY, mappedBy="lift",cascade=CascadeType.ALL)
//	@JoinTable(name = "LIFT_DETOURS_JOIN",
//				joinColumns = { @JoinColumn (name = "LIFT_ID") },
//				inverseJoinColumns = { @JoinColumn(name = "DETOUR_ID") })
	public List<LiftDetour> getDetours() {
		return detours;
	}
	
	
//	@ManyToMany(fetch=FetchType.LAZY)
//	@JoinTable(name = "LIFT_USER_BOOKING",
//				joinColumns = { @JoinColumn (name = "LIFT_ID") },
//				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
//	public List<User> getUsersBookingList() {
//		return usersBookingList;
//	}

	@ManyToOne(fetch=FetchType.LAZY) //ok
	@JoinTable(name = "LIFT_USER_OFFERING",
				joinColumns = { @JoinColumn (name = "LIFT_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public RegisteredUser getUserOffering() {
		return userOffering;
	}
	
//	public void setUsersBookingList(List<User> usersBookingList) {
//		this.usersBookingList = usersBookingList;
//	}


	public void setDetours(List<LiftDetour> detours) {
		this.detours = detours;
	}

	@Column(name="COST")
	public Double getCost() {
		return super.cost;
	}

	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name = "LIFT_PICK_UP_POINT_JOIN",
				joinColumns = { @JoinColumn (name = "LIFT_ID") },
				inverseJoinColumns = { @JoinColumn(name = "LIFT_POINT_ID") })
	public LiftPoint getPickUpPoint() {
		return super.pickUpPoint;
	}



	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name = "LIFT_DROP_OFF_POINT_JOIN",
				joinColumns = { @JoinColumn (name = "LIFT_ID") },
				inverseJoinColumns = { @JoinColumn(name = "LIFT_POINT_ID") })
	public LiftPoint getDropOffPoint() {
		return super.dropOffPoint;
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
	
	@Override
	public void copy(DomainObject object2) {
		super.copy(object2);
		Lift l=(Lift) object2;
		if(l.departureDate!=null)
			this.departureDate=l.departureDate;
		if(l.departureTime!=null)
			this.departureTime=l.departureTime;
		if(l.userOffering!=null)
			this.userOffering=l.userOffering;
		if(l.nSeat!=null)
			this.nSeat=l.nSeat;
		if(l.detours!=null)
			this.detours=l.detours;
		if(l.liftPreferences!=null)
			this.liftPreferences=l.liftPreferences;
//		if(l.usersBookingList!=null)
//			this.usersBookingList=l.usersBookingList;
		if(l.returnLift!=null)
			this.returnLift=l.returnLift;
		if(l.description!=null)
			this.description=l.description;
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

	public void setUserOffering(RegisteredUser userOffering) {
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
		result = prime * result
				+ ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result
				+ ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((detours == null) ? 0 : detours.hashCode());
		result = prime * result
				+ ((isReturn == null) ? 0 : isReturn.hashCode());
		result = prime * result
				+ ((liftPreferences == null) ? 0 : liftPreferences.hashCode());
		result = prime * result + ((nSeat == null) ? 0 : nSeat.hashCode());
		result = prime * result
				+ ((returnLift == null) ? 0 : returnLift.hashCode());
		result = prime * result
				+ ((userOffering == null) ? 0 : userOffering.hashCode());
		result = prime * result + version;
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
		if (version != other.version)
			return false;
		return true;
	}

	public String pathToString(){
		List<String> path=computeRoute();
		String pathString="";
		pathString+=path.get(0);
		for (String string : path) {
			pathString+=" - "+string;
		}
		return pathString;
		
	}
	
	
	
	
}
