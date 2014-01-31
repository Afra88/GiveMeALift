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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	
	@Cascade(value=CascadeType.SAVE_UPDATE)
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

	@Cascade(value=CascadeType.SAVE_UPDATE)
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((feedbackReceiver == null) ? 0 : feedbackReceiver.hashCode());
		result = prime * result
				+ ((feedbackSender == null) ? 0 : feedbackSender.hashCode());
		result = prime
				* result
				+ ((numAlertSegnalation == null) ? 0 : numAlertSegnalation
						.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Feedback other = (Feedback) obj;
		if (feedbackReceiver == null) {
			if (other.feedbackReceiver != null)
				return false;
		} else if (!feedbackReceiver.equals(other.feedbackReceiver))
			return false;
		if (feedbackSender == null) {
			if (other.feedbackSender != null)
				return false;
		} else if (!feedbackSender.equals(other.feedbackSender))
			return false;
		if (numAlertSegnalation == null) {
			if (other.numAlertSegnalation != null)
				return false;
		} else if (!numAlertSegnalation.equals(other.numAlertSegnalation))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	

	
}
