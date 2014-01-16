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

	public LiftPoint(String city){
		this.city = city;
		this.street = "nd";
		this.province = "nd";
		this.region = "nd";
		this.state = "nd";
	}
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		LiftPoint other = (LiftPoint) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	
	
}
