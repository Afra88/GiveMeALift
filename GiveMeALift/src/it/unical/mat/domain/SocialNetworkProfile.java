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
	@Column(name = "TYPE")
	private String type;
	@Embedded
	@Column(name = "SOCIAL_NETWORK_TYPE_USER_ID", unique = true)
	private SocialNetworkUserID key;
		
		
	public SocialNetworkProfile() {}

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


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((socialNetworkType == null) ? 0 : socialNetworkType
						.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocialNetworkUserID other = (SocialNetworkUserID) obj;
		if (socialNetworkType == null) {
			if (other.socialNetworkType != null)
				return false;
		} else if (!socialNetworkType.equals(other.socialNetworkType))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}