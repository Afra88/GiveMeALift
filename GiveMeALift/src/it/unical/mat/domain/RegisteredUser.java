package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="REGISTERED_USER")
public class RegisteredUser extends User {
	
	@Column(name = "COUNT_ALERT")
	private int countAlert;
	
	@Column (name = "IS_ONLY_PASSENGER")
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



}
