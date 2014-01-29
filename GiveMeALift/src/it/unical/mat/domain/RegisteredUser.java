package it.unical.mat.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;

@Entity
@DiscriminatorValue("RE")
public class RegisteredUser extends User {
	
	@Column(name = "COUNT_ALERT", nullable=true)
	private Integer countAlert;
	
	@Column (name = "ONLY_PASSENGER", nullable=true)
	private Boolean onlyPassenger;
	
	@Column(name="PROFILE_PHOTO")
	private String profilePhoto;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="SHOW_CONTACTS")
	private Boolean showContacts;

	private UserActivity userActivity;
	
	private PersonalPreference personalPreference;

//	private List<Message> messagesSent;
//	
//	private List<Message> messagesReceived;
	
	private List<Feedback> receivedFeedback;

	private Car car;
	
	
	public RegisteredUser() {
		super();
//		messagesSent=new LinkedList<Message>();
//		messagesReceived=new LinkedList<Message>();
//		receivedFeedback=new LinkedList<Feedback>();
	}			

	public RegisteredUser(String email, String psw, String name,
			String surname, String gender, Integer birthYear, String phone,
			String mPhone, Address address) {
		super(email, psw, name, surname, gender, birthYear, phone, mPhone, address);
	}
	

	public RegisteredUser(String email, String psw, String name, String surname, String gender, Integer birthYear) {
		super(email,psw,name,surname,gender,birthYear);
	}

	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@ManyToOne(fetch = FetchType.LAZY) //cambiato
	@JoinTable(name = "PERSONAL_PREFERENCES_USER_JOIN",
				joinColumns = { @JoinColumn (name = "USER_ID") },
				inverseJoinColumns = { @JoinColumn(name = "PERSONAL_PREFERENCES_ID") })
	public PersonalPreference getPersonalPreference() {
		return personalPreference;
	}

	public void setPersonalPreference(PersonalPreference personalPreference) {
		this.personalPreference = personalPreference;
	}
	
	public Integer getCountAlert() {
		return countAlert;
	}
	
	public void setCountAlert(Integer countAlert) {
		this.countAlert = countAlert;
	}

	public Boolean getOnlyPassenger() {
		return onlyPassenger;
	}

	public void setOnlyPassenger(Boolean onlyPassenger) {
		this.onlyPassenger = onlyPassenger;
	}

	@Override
	public void copy(DomainObject object2) {
		RegisteredUser u=(RegisteredUser)(object2);
		super.copy(object2);
		if(u.onlyPassenger!=null)
			this.onlyPassenger=u.onlyPassenger;
		if(u.countAlert!=null)
			this.onlyPassenger=u.onlyPassenger;
		if(u.countAlert!=null)
			this.countAlert=u.countAlert;
//		if(u.messagesReceived!=null)
//			this.messagesReceived=u.messagesReceived;
//		if(u.messagesSent!=null)
//			this.messagesSent=u.messagesSent;
		if(u.personalPreference!=null)
			this.personalPreference=u.personalPreference;
		if(u.receivedFeedback!=null)
			this.receivedFeedback=u.receivedFeedback;
		if(u.userActivity!=null)
			this.userActivity=u.userActivity;
		if(u.description!=null)
			this.description=u.description;
		if(u.showContacts!=null)
			this.showContacts=u.showContacts;
		if(u.car!=null)
			this.car=u.car;
	}
	
	@OneToOne (fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public UserActivity getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(UserActivity userActivity) {
		this.userActivity = userActivity;
	}

//	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//	@OneToMany(fetch=FetchType.LAZY) //cambiato aa
//	@JoinTable(name = "MESSAGE_SENDER_JOIN",
//				joinColumns = { @JoinColumn (name = "USER_ID") },
//				inverseJoinColumns = { @JoinColumn(name = "MESSAGE_ID") })
//	public List<Message> getMessagesSent() {
//		return messagesSent;
//	}
//
//	public void setMessagesSent(List<Message> messagesSent) {
//		this.messagesSent = messagesSent;
//	}

//	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL) //cambiato aa
//	@JoinTable(name = "MESSAGE_RECEIVED",
//				joinColumns = { @JoinColumn (name = "USER_ID") },
//				inverseJoinColumns = { @JoinColumn(name = "MESSAGE_ID") })
//	public List<Message> getMessagesReceived() {
//		return messagesReceived;
//	}
//
//	public void setMessagesReceived(List<Message> messagesReceived) {
//		this.messagesReceived = messagesReceived;
//	}
//
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@OneToMany(fetch=FetchType.LAZY) //cambiato aa
	@JoinTable(name = "FEEDBACK_RECEIVER_JOIN",
				joinColumns = { @JoinColumn (name = "USER_ID") },
				inverseJoinColumns = { @JoinColumn(name = "FEEDBACK_ID") })
	public List<Feedback> getReceivedFeedback() {
		return receivedFeedback;
	}

	public void setReceivedFeedback(List<Feedback> receivedFeedback) {
		this.receivedFeedback = receivedFeedback;
	}
	
	public String getProfilePhoto() {
		return profilePhoto;
	}
	
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getShowContacts() {
		return showContacts;
	}
	
	public void setShowContacts(Boolean showContacts) {
		this.showContacts = showContacts;
	}
	
//	public Integer computeAvgRating(){
//		Integer sum = 0;
//		int tot = receivedFeedback.size();
//		for (Feedback f : receivedFeedback) {
//			sum += f.getRating();
//		}
//		
//		return sum/tot;
//	}
//	
//	public Integer giveAvgRating(){
//		return computeAvgRating();
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result
				+ ((countAlert == null) ? 0 : countAlert.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
//		result = prime
//				* result
//				+ ((messagesReceived == null) ? 0 : messagesReceived.hashCode());
//		result = prime * result
//				+ ((messagesSent == null) ? 0 : messagesSent.hashCode());
		result = prime * result
				+ ((onlyPassenger == null) ? 0 : onlyPassenger.hashCode());
		result = prime
				* result
				+ ((personalPreference == null) ? 0 : personalPreference
						.hashCode());
		result = prime * result
				+ ((profilePhoto == null) ? 0 : profilePhoto.hashCode());
		result = prime
				* result
				+ ((receivedFeedback == null) ? 0 : receivedFeedback.hashCode());
		result = prime * result
				+ ((showContacts == null) ? 0 : showContacts.hashCode());
		result = prime * result
				+ ((userActivity == null) ? 0 : userActivity.hashCode());
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
		RegisteredUser other = (RegisteredUser) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (countAlert == null) {
			if (other.countAlert != null)
				return false;
		} else if (!countAlert.equals(other.countAlert))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
//		if (messagesReceived == null) {
//			if (other.messagesReceived != null)
//				return false;
//		} else if (!messagesReceived.equals(other.messagesReceived))
//			return false;
//		if (messagesSent == null) {
//			if (other.messagesSent != null)
//				return false;
//		} else if (!messagesSent.equals(other.messagesSent))
//			return false;
		if (onlyPassenger == null) {
			if (other.onlyPassenger != null)
				return false;
		} else if (!onlyPassenger.equals(other.onlyPassenger))
			return false;
		if (personalPreference == null) {
			if (other.personalPreference != null)
				return false;
		} else if (!personalPreference.equals(other.personalPreference))
			return false;
		if (profilePhoto == null) {
			if (other.profilePhoto != null)
				return false;
		} else if (!profilePhoto.equals(other.profilePhoto))
			return false;
		if (receivedFeedback == null) {
			if (other.receivedFeedback != null)
				return false;
		} else if (!receivedFeedback.equals(other.receivedFeedback))
			return false;
		if (showContacts == null) {
			if (other.showContacts != null)
				return false;
		} else if (!showContacts.equals(other.showContacts))
			return false;
		if (userActivity == null) {
			if (other.userActivity != null)
				return false;
		} else if (!userActivity.equals(other.userActivity))
			return false;
		return true;
	}
	
	
	
	
}
