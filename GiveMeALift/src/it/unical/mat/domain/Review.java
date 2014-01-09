package it.unical.mat.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Review extends DomainObject {

	@Column(name = "LINK_TO_TEXT")
	private String linkToText;
	@Column(name = "DATE")
	private Date date;
	
	private User user;
	
	public Review() {
		this.linkToText = null;
		this.date = null;
	}	

	
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
		if(this.linkToText != null)
			this.linkToText = r.linkToText;
		if(this.date != null)
			this.date = r.date;
	}

}
