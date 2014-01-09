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

@Entity
public class MemberActivity extends DomainObject {

	@Column(name = "RIDES_OFFERED")
	private int ridesOffered;
	@Column(name = "MEMBER_SINCE")
	private Date memberSince;
	@Column(name = "LAST_ONLINE")
	private Date lastOnline;
	@Column(name = "ANSWERS_PERCENTAGE") 
	private int answersPercentage;
	
	//relazione 1a1 con User in base alla chiave primaria
	@OneToOne (fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;
	

	public MemberActivity() {
		this.ridesOffered = 0;
		this.memberSince = null;
		this.lastOnline = null;
		this.answersPercentage = 0;
	}
	
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
		MemberActivity m = (MemberActivity) object2;
		if(this.ridesOffered != 0)
			this.ridesOffered = m.ridesOffered;
		if(this.memberSince != null)
			this.memberSince = m.memberSince;
		if(this.lastOnline != null)
			this.lastOnline = m.lastOnline;
		if(this.answersPercentage != 0)
			this.answersPercentage = m.answersPercentage;
		}
}