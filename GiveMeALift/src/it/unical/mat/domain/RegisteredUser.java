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
	private int countAlert;
	
	@Column (name = "IS_ONLY_PASSENGER", nullable=true)
	private boolean isOnlyPassenger;
	
	private PersonalPreference personalPreference;
	
	private List<Message> messagesSent=new LinkedList<Message>();
	
	private List<Message> messagesReceived=new LinkedList<Message>();

	private UserActivity userActivity;
	
	private List<Feedback> receivedFeedback=new LinkedList<Feedback>();

	public RegisteredUser() {
		super();
		countAlert = 0;
		isOnlyPassenger = true;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PERSONAL_PREFERENCES_USER_JOIN",
				joinColumns = { @JoinColumn (name = "PERSONAL_PREFERENCES_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public PersonalPreference getPersonalPreference() {
		return personalPreference;
	}

	public void setPersonalPreference(PersonalPreference personalPreference) {
		this.personalPreference = personalPreference;
	}
	
	public int getCountAlert() {
		return countAlert;
	}
	
	public void setCountAlert(int countAlert) {
		this.countAlert = countAlert;
	}
	
	public boolean isOnlyPassenger() {
		return isOnlyPassenger;
	}
	
	public void setOnlyPassenger(boolean isOnlyPassenger) {
		this.isOnlyPassenger = isOnlyPassenger;
	}

	@Override
	public void copy(DomainObject object2) {
		super.copy(object2);
		RegisteredUser u=(RegisteredUser)(object2);
		this.countAlert=u.countAlert;
		this.isOnlyPassenger=u.isOnlyPassenger;
	}
	
	@OneToOne (fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public UserActivity getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(UserActivity userActivity) {
		this.userActivity = userActivity;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name = "MESSAGE_SENDER_JOIN",
				joinColumns = { @JoinColumn (name = "USER_ID") },
				inverseJoinColumns = { @JoinColumn(name = "MESSAGE_ID") })
	public List<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(List<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name = "MESSAGE_RECEIVER_JOIN",
				joinColumns = { @JoinColumn (name = "USER_ID") },
				inverseJoinColumns = { @JoinColumn(name = "MESSAGE_ID") })
	public List<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	@ManyToOne(fetch=FetchType.LAZY)
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
