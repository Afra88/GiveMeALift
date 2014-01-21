package it.unical.mat.domain;

import java.sql.Date;

import javax.persistence.CascadeType;
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
@Table(name="MESSAGE")
public class Message extends DomainObject {

	private User sender;
	private User receiver;
	@Column(name="TEXT")
	private String text;
	@Column(name="DATE_SENDING")
	private Date dateSending;
	
	public Message() {}
	
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MESSAGE_ID")
	public long getId() {return super.getId();};
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL) //cambiato
	@JoinTable(name = "MESSAGE_SENDER_JOIN",
				joinColumns = { @JoinColumn (name = "MESSAGE_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public User getSender() {
		return sender;
	}



	public void setSender(User sender) {
		this.sender = sender;
	}


	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL) //cambiato
	@JoinTable(name = "MESSAGE_RECEIVER_JOIN",
				joinColumns = { @JoinColumn (name = "MESSAGE_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public User getReceiver() {
		return receiver;
	}



	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public Date getDateSending() {
		return dateSending;
	}



	public void setDateSending(Date dateSending) {
		this.dateSending = dateSending;
	}



	@Override
	public void copy(DomainObject object2) {
		Message m=(Message)object2;
		if(m.sender!=null)
			this.sender=m.sender;
		if(m.receiver!=null)
			this.receiver=m.receiver;
		if(m.text!=null)
			this.text=m.text;
		if(m.dateSending!=null)
			this.dateSending=m.dateSending;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateSending == null) ? 0 : dateSending.hashCode());
		result = prime * result
				+ ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
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
		Message other = (Message) obj;
		if (dateSending == null) {
			if (other.dateSending != null)
				return false;
		} else if (!dateSending.equals(other.dateSending))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	

}
