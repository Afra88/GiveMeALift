package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FEEDBACK")
public class Feedback extends DomainObject {

	private User feedbackSender;
	private User feedbackReceiver;
	@Column(name="TEXT")
	private String text;
	@Column(name="RATING")
	private int rating;
	@Column(name="NUM_ALERT_SEGNALATION")
	private int numAlertSegnalation;
	
	public Feedback() {}
	
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="FEEDBACK_ID")
	public long getId() {return super.getId();};
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "MESSAGE_FEEDBACK_SENDER_JOIN",
				joinColumns = { @JoinColumn (name = "USER_ID") },
				inverseJoinColumns = { @JoinColumn(name = "FEEDBACK_ID") })
	public User getSender() {
		return feedbackSender;
	}



	public void setSender(User sender) {
		this.feedbackSender = sender;
	}


	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "MESSAGE_FEEDBACK_RECEIVER_JOIN",
				joinColumns = { @JoinColumn (name = "USER_ID") },
				inverseJoinColumns = { @JoinColumn(name = "FEEDBACK_ID") })
	public User getReceiver() {
		return feedbackReceiver;
	}



	public void setReceiver(User receiver) {
		this.feedbackReceiver = receiver;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}


	@Override
	public void copy(DomainObject object2) {
		Feedback f=(Feedback)object2;
		if(f.feedbackSender!=null)
			this.feedbackSender=f.feedbackSender;
		if(f.feedbackReceiver!=null)
			this.feedbackReceiver=f.feedbackReceiver;
		if(f.text!=null)
			this.text=f.text;
		if(f.numAlertSegnalation!=0)
			this.numAlertSegnalation=f.numAlertSegnalation;
		if(f.rating!=0){
			this.rating=f.rating;
		}

	}

}
