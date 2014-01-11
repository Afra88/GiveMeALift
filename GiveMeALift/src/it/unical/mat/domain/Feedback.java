package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FEEDBACK")
public class Feedback extends DomainObject {

	@Column(name="TEXT")
	private String text;
	@Column(name="RATING")
	private Integer rating;
	@Column(name="NUM_ALERT_SEGNALATION")
	private Integer numAlertSegnalation;
	
	private RegisteredUser feedbackReceiver;
	private RegisteredUser feedbackSender;

	public Feedback() {}
	
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="FEEDBACK_ID")
	public long getId() {return super.getId();};
	
	@ManyToOne(fetch=FetchType.LAZY) //ok
	@JoinTable(name = "FEEDBACK_SENDER_JOIN",
				joinColumns = { @JoinColumn (name = "FEEDBACK_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public User getSender() {
		return feedbackSender;
	}



	public void setSender(RegisteredUser sender) {
		this.feedbackSender = sender;
	}


	@ManyToOne(fetch=FetchType.LAZY) //ok
	@JoinTable(name = "FEEDBACK_RECEIVER_JOIN",
				joinColumns = { @JoinColumn (name = "FEEDBACK_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public RegisteredUser getReceiver() {
		return feedbackReceiver;
	}



	public void setReceiver(RegisteredUser receiver) {
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
		if(f.numAlertSegnalation!=null)
			this.numAlertSegnalation=f.numAlertSegnalation;
		if(f.rating!=null){
			this.rating=f.rating;
		}

	}


	public Integer getRating() {
		return rating;
	}


	public void setRating(Integer rating) {
		this.rating = rating;
	}


	public int getNumAlertSegnalation() {
		return numAlertSegnalation;
	}


	public void setNumAlertSegnalation(int numAlertSegnalation) {
		this.numAlertSegnalation = numAlertSegnalation;
	}
	
	

}
