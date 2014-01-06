package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="LIFT_POINT")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class LiftPoint extends DomainObject {

	@Column(name="STREET")
	private String street;
	@Column(name="PROVINCE")
	private String province;
	@Column(name="CITY")
	private String city;
	@Column(name="REGION")
	private String region;
	@Column(name="STATE")
	private String state;
	
	public LiftPoint() {
		this.street="";
		this.province="";
		this.city="";
		this.region="";
		this.state="";
	}
	
	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	@Override
	public void copy(DomainObject object2) {
		LiftPoint lp= (LiftPoint) object2;
		this.street=lp.street;
		this.province=lp.province;
		this.city=lp.city;
		this.region=lp.region;
		this.state=lp.state;
	}

}
