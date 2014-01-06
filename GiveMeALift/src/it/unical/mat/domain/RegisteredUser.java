package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RE")
public class RegisteredUser extends User {
	
	@Column(name = "COUNT_ALERT", nullable=true)
	private int countAlert;
	
	@Column (name = "IS_ONLY_PASSENGER", nullable=true)
	//@Type (type = "BOOLEAN")
	private boolean isOnlyPassenger;
	
	public RegisteredUser() {
		super();
		countAlert = 0;
		isOnlyPassenger = true;
	}
	
	public int getCountAlert() {
		return countAlert;
	}
	
	public void setCountAlert(int countAlert) {
		this.countAlert = countAlert;
	}
	
	public boolean isOnlyPassenger() {
		return isOnlyPassenger;
	}
	
	public void setOnlyPassenger(boolean isOnlyPassenger) {
		this.isOnlyPassenger = isOnlyPassenger;
	}

	@Override
	public void copy(DomainObject object2) {
		super.copy(object2);
		RegisteredUser u=(RegisteredUser)(object2);
		this.countAlert=u.countAlert;
		this.isOnlyPassenger=u.isOnlyPassenger;
	}
}
