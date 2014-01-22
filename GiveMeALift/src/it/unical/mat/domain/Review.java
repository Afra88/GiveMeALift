package it.unical.mat.domain;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REVIEUW")
public class Review extends DomainObject {

	@Column(name = "LINK_TO_TEXT")
	private String linkToText;
	@Column(name = "DATE")
	private Date date;
	
	private User user;
	
	public Review() {}	

	
	@Override
	@Id
	@Column(name = "REVIEW_ID")
	public long getId() {
		return super.getId();
	}


	public String getLinkToText() {
		return linkToText;
	}


	public void setLinkToText(String linkToText) {
		this.linkToText = linkToText;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public void copy(DomainObject object2) {
		Review r = (Review) object2;
		if(r.linkToText != null)
			this.linkToText = r.linkToText;
		if(r.date != null)
			this.date = r.date;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL) //cambiato
	@JoinTable(name = "REVIEUW_USER_JOIN",
				joinColumns = { @JoinColumn (name = "REVIEUW_ID") },
				inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((linkToText == null) ? 0 : linkToText.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Review other = (Review) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (linkToText == null) {
			if (other.linkToText != null)
				return false;
		} else if (!linkToText.equals(other.linkToText))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	

	
}
