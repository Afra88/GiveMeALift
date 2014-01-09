package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LIFT_POINT")
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
	
	public LiftPoint() {}


	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LIFT_POINT_ID")
	public long getId() {return super.getId();};
	
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
		if(lp.street!=null)
			this.street=lp.street;
		if(lp.province!=null)
			this.province=lp.province;
		if(lp.city!=null)
			this.city=lp.city;
		if(lp.region!=null)
			this.region=lp.region;
		if(lp.state!=null)
			this.state=lp.state;
	}

}
