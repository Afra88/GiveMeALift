package it.unical.mat.domain;

import java.sql.Date;
import java.sql.Time;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="MESSAGE")
public class Message extends DomainObject {

	private RegisteredUser sender;
	private RegisteredUser receiver;
	@Column(name="TEXT")
	private String text;
	@Column(name="DATE_SENDING")
	private Date dateSending;
	@Column(name="TIME_SENDING")
	private Time timeSending;
	@Column(name="READ")
	private Boolean isRead;
	
	private Message nextMessage;
	
	public Message() {}

	public Message(RegisteredUser sender, RegisteredUser receiver, String text,
			Date dateSending, Time timeSending, Boolean isRead,
			Message nextMessage) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.text = text;
		this.dateSending = dateSending;
		this.timeSending = timeSending;
		this.isRead = isRead;
		this.nextMessage = nextMessage;
	}


	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE) //cambiato
	@JoinTable(name = "MESSAGE_SENDER_JOIN",
				joinColumns = { @JoinColumn (name = "MESSAGE_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public RegisteredUser getSender() {
		return sender;
	}
	
	public void setSender(RegisteredUser sender) {
		this.sender = sender;
	}


	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE) //cambiato
	@JoinTable(name = "MESSAGE_RECEIVER_JOIN",
				joinColumns = { @JoinColumn (name = "MESSAGE_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public RegisteredUser getReceiver() {
		return receiver;
	}
	
	public void setReceiver(RegisteredUser receiver) {
		this.receiver = receiver;
	}
	



	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MESSAGE_ID")
	public long getId() {return super.getId();};
	

	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NEXT_MESSAGE_ID",nullable=true)
	public Message getNextMessage() {
		return nextMessage;
	}
	
	public void setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
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
	
	
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	
	public Boolean getIsRead() {
		return isRead;
	}
	


	@Override
	public void copy(DomainObject object2) {
		Message m=(Message)object2;
		if(m.text!=null)
			this.text=m.text;
		if(m.dateSending!=null)
			this.dateSending=m.dateSending;
		if(m.isRead!=null)
			this.isRead=m.isRead;
		if(m.nextMessage!=null)
			this.nextMessage=m.nextMessage;
		if(m.timeSending!=null)
			this.timeSending=m.timeSending;
		if(m.receiver!=null)
			this.receiver=m.receiver;
		if(m.sender!=null)
			this.sender=m.sender;
	}

	public Time getTimeSending() {
		return timeSending;
	}
	
	public void setTimeSending(Time timeSending) {
		this.timeSending = timeSending;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateSending == null) ? 0 : dateSending.hashCode());
		result = prime * result + ((isRead == null) ? 0 : isRead.hashCode());
		result = prime * result
				+ ((nextMessage == null) ? 0 : nextMessage.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result
				+ ((timeSending == null) ? 0 : timeSending.hashCode());
		result = prime * result
				+ ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
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
		if (isRead == null) {
			if (other.isRead != null)
				return false;
		} else if (!isRead.equals(other.isRead))
			return false;
		if (nextMessage == null) {
			if (other.nextMessage != null)
				return false;
		} else if (!nextMessage.equals(other.nextMessage))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (timeSending == null) {
			if (other.timeSending != null)
				return false;
		} else if (!timeSending.equals(other.timeSending))
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
		return true;
	}
	

}
