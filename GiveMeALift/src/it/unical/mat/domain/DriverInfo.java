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
@Table(name="DRIVER_INFO")
public class DriverInfo extends DomainObject {

	@Column(name="DRIVING_LICENCE",unique=true,nullable=false)
	private String driving_licence;
	@Column(name="RELISING_DATE")
	private Date relisingDate;
	@Column(name="COLOR")
	private String color;
	@Column(name="COMFORT")
	private Integer comfort;
	@Column(name="INSURANCE",unique=true,nullable=false)
	private String insurance;
	@Column(name="CAR_PHOTO")
	private String car_photo;
	
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;
	
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Car car;
	
	
	public DriverInfo(){}
	
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="DRIVER_INFO_ID")
	public long getId() {return super.getId();};
	
	
	
	
	public String getDriving_licence() {
		return driving_licence;
	}




	public void setDriving_licence(String driving_licence) {
		this.driving_licence = driving_licence;
	}




	public Date getRelisingDate() {
		return relisingDate;
	}




	public void setRelisingDate(Date relisingDate) {
		this.relisingDate = relisingDate;
	}




	public String getColor() {
		return color;
	}




	public void setColor(String color) {
		this.color = color;
	}




	public int isComfort() {
		return comfort;
	}




	public void setComfort(int comfort) {
		this.comfort = comfort;
	}




	public String getInsurance() {
		return insurance;
	}




	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}




	public String getCar_photo() {
		return car_photo;
	}




	public void setCar_photo(String car_photo) {
		this.car_photo = car_photo;
	}




	@Override
	public void copy(DomainObject object2) {
		DriverInfo d=(DriverInfo) object2;
		if(this.car_photo!=null)
			this.car_photo=d.car_photo;
		if(this.comfort!=null)
			this.comfort=d.comfort;
		if(this.color!=null)
			this.color=d.color;
		if(this.driving_licence!=null)
			this.driving_licence=d.driving_licence;
		if(this.insurance!=null)
			this.insurance=d.insurance;
		if(this.relisingDate!=null)
			this.relisingDate=d.relisingDate;
		if(this.car!=null)
			this.car=d.car;
		if(this.user!=null)
			this.user=d.user;
	}

}