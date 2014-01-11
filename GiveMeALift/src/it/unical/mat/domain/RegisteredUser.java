package it.unical.mat.domain;

import java.util.LinkedList;
import java.util.List;

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

@Entity
@DiscriminatorValue("RE")
public class RegisteredUser extends User {
	
	@Column(name = "COUNT_ALERT", nullable=true)
	private Integer countAlert;
	
	@Column (name = "ONLY_PASSENGER", nullable=true)
	private Boolean onlyPassenger;
	
	@Column(name="PHOTO", nullable=true)
	private String carPhoto;
	

	private UserActivity userActivity;
	
	private PersonalPreference personalPreference;

	private List<Message> messagesSent;
	
	private List<Message> messagesReceived;
	
	private List<Feedback> receivedFeedback;
	
	public RegisteredUser() {
		super();
		messagesSent=new LinkedList<Message>();
		messagesReceived=new LinkedList<Message>();
		receivedFeedback=new LinkedList<Feedback>();
	}
	
	public String getCarPhoto() {
		return carPhoto;
	}
	
	public void setCarPhoto(String carPhoto) {
		this.carPhoto = carPhoto;
	}

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
		if(u.onlyPassenger!=null)
			this.onlyPassenger=u.onlyPassenger;
		if(u.countAlert!=null)
			this.onlyPassenger=u.onlyPassenger;
		if(u.countAlert!=null)
			this.countAlert=u.countAlert;
		if(u.messagesReceived!=null)
			this.messagesReceived=u.messagesReceived;
		if(u.messagesSent!=null)
			this.messagesSent=u.messagesSent;
		if(u.personalPreference!=null)
			this.personalPreference=u.personalPreference;
		if(u.receivedFeedback!=null)
			this.receivedFeedback=u.receivedFeedback;
		if(u.userActivity!=null)
			this.userActivity=u.userActivity;	
		if(u.carPhoto!=null)
			this.carPhoto=u.carPhoto;
	}
	
	@OneToOne (fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public UserActivity getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(UserActivity userActivity) {
		this.userActivity = userActivity;
	}

	@OneToMany(fetch=FetchType.LAZY) //cambiato aa
	@JoinTable(name = "MESSAGE_SENDER_JOIN",
				joinColumns = { @JoinColumn (name = "USER_ID") },
				inverseJoinColumns = { @JoinColumn(name = "MESSAGE_ID") })
	public List<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(List<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	@OneToMany(fetch=FetchType.LAZY) //cambiato aa
	@JoinTable(name = "MESSAGE_RECEIVER_JOIN",
				joinColumns = { @JoinColumn (name = "USER_ID") },
				inverseJoinColumns = { @JoinColumn(name = "MESSAGE_ID") })
	public List<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

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
	
	
}
