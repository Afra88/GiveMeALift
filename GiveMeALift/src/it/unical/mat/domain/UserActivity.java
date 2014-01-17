package it.unical.mat.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="USER_ACTIVITY")
public class UserActivity extends DomainObject {

	@Column(name = "RIDES_OFFERED")
	private Integer ridesOffered;
	@Column(name = "MEMBER_SINCE")
	private Date memberSince;
	@Column(name = "LAST_ONLINE")
	private Date lastOnline;
	@Column(name = "ANSWERS_PERCENTAGE") 
	private Integer answersPercentage;
	
	//relazione 1a1 con User in base alla chiave primaria
	@OneToOne (fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;
	

	public UserActivity() {}
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="MEMBER_ACTIVITY_ID")
	public long getId() {return super.getId();};
	
	public int getRidesOffered() {
		return ridesOffered;
	}

	public void setRidesOffered(int ridesOffered) {
		this.ridesOffered = ridesOffered;
	}

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public Date getLastOnline() {
		return lastOnline;
	}

	public void setLastOnline(Date lastOnline) {
		this.lastOnline = lastOnline;
	}

	public int getAnswersPercentage() {
		return answersPercentage;
	}

	public void setAnswersPercentage(int answersPercentage) {
		this.answersPercentage = answersPercentage;
	}

	@Override
	public void copy(DomainObject object2) {
		UserActivity m = (UserActivity) object2;
		if(m.ridesOffered != null)
			this.ridesOffered = m.ridesOffered;
		if(m.memberSince != null)
			this.memberSince = m.memberSince;
		if(m.lastOnline != null)
			this.lastOnline = m.lastOnline;
		if(m.answersPercentage != null)
			this.answersPercentage = m.answersPercentage;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((answersPercentage == null) ? 0 : answersPercentage
						.hashCode());
		result = prime * result
				+ ((lastOnline == null) ? 0 : lastOnline.hashCode());
		result = prime * result
				+ ((memberSince == null) ? 0 : memberSince.hashCode());
		result = prime * result
				+ ((ridesOffered == null) ? 0 : ridesOffered.hashCode());
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
		UserActivity other = (UserActivity) obj;
		if (answersPercentage == null) {
			if (other.answersPercentage != null)
				return false;
		} else if (!answersPercentage.equals(other.answersPercentage))
			return false;
		if (lastOnline == null) {
			if (other.lastOnline != null)
				return false;
		} else if (!lastOnline.equals(other.lastOnline))
			return false;
		if (memberSince == null) {
			if (other.memberSince != null)
				return false;
		} else if (!memberSince.equals(other.memberSince))
			return false;
		if (ridesOffered == null) {
			if (other.ridesOffered != null)
				return false;
		} else if (!ridesOffered.equals(other.ridesOffered))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}