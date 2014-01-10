package it.unical.mat.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SocialNetworkProfile extends DomainObject {
	
	
	@Column(name = "LINK")
	private String link;
	@Embedded
	@Column(name = "SOCIAL_NETWORK_TYPE_USER_ID", unique = true)
	private SocialNetworkUserID key;
		
		
	public SocialNetworkProfile() {
		this.link = null;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOCIAL_NETWORK_PROFILE_ID")
	public long getId() {
		return super.getId();
	}
	

	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	@Override
	public void copy(DomainObject object2) {
		SocialNetworkProfile s = (SocialNetworkProfile) object2;
		if(s.link != null)
			this.link = s.link;
	}
}

/*
 * Inner Class for the SocialNetworkProfile's foreign key TYPE+USER
 * */

@Embeddable
class SocialNetworkUserID implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SOCIAL_NETWORK_TYPE")
	private String socialNetworkType;
	@Column(name = "USER")
	private User user;
	
	public String getSocialNetworkType() {
		return socialNetworkType;
	}
	
	public void setSocialNetworkType(String socialNetworkType) {
		this.socialNetworkType = socialNetworkType;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}