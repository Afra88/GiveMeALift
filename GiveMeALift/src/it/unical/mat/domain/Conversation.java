package it.unical.mat.domain;

import it.unical.mat.service.ParseDate;

import java.util.List;

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

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="CONVERSATION")
public class Conversation extends DomainObject {

	@Column(name="CAPTION")
	private String caption;
	@Column(name="ARCHIVIATED")
	private Boolean isArchiviated;
	private List<Message> messages;
	
	public Conversation() {}
	
	public Conversation(String caption, Boolean isArchiviated,
			List<Message> messages) {
		super();
		this.caption = caption;
		this.isArchiviated = isArchiviated;
		this.messages = messages;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CONVERSATION_ID")
	public long getId() {
		return super.getId();
	}
	
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "MESSAGE_CONVERSATION_JOIN",
				joinColumns = { @JoinColumn (name = "CONVERSATION_ID") },
				inverseJoinColumns = { @JoinColumn(name = "MESSAGE_ID") })
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	public String getCaption() {
		return caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Boolean getIsArchiviated() {
		return isArchiviated;
	}
	
	public void setIsArchiviated(Boolean isArchiviated) {
		this.isArchiviated = isArchiviated;
	}

	@Override
	public void copy(DomainObject object2) {
		Conversation c=(Conversation)object2;
		if(c.messages!=null)
			this.messages=c.messages;
		if(c.isArchiviated!=null)
			this.isArchiviated=c.isArchiviated;
		if(c.caption!=null)
			this.caption=c.caption;		
	}
	
	public RegisteredUser computeOtherUser(long id){
		if(messages.size()>0){
			Message m=messages.get(0);
			if(m.getSender().getId()==id)
				return m.getReceiver();
			else if(m.getReceiver().getId()==id)
				return m.getSender();
		}
		return null;
		
	}
	
	public String computeLastMessageDate(){
		if(messages.size()>0){
			Message message=messages.get(messages.size()-1);
			return ParseDate.getItalianFormat(message.getDateSending().toString());
		}
		return null;
	}
	
	public String computeLastMessageTime(){
		if(messages.size()>0){
			Message message=messages.get(messages.size()-1);
			return ParseDate.getTimeHM(message.getTimeSending());
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result
				+ ((isArchiviated == null) ? 0 : isArchiviated.hashCode());
		result = prime * result
				+ ((messages == null) ? 0 : messages.hashCode());
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
		Conversation other = (Conversation) obj;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
		if (isArchiviated == null) {
			if (other.isArchiviated != null)
				return false;
		} else if (!isArchiviated.equals(other.isArchiviated))
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		return true;
	}

	public void addMessage(Message m) {
		if(m!=null)
			messages.add(m);		
	}

	
}
