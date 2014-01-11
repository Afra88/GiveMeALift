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
	
	private User user;
	
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
		if(d.car_photo!=null)
			this.car_photo=d.car_photo;
		if(d.comfort!=null)
			this.comfort=d.comfort;
		if(d.color!=null)
			this.color=d.color;
		if(d.driving_licence!=null)
			this.driving_licence=d.driving_licence;
		if(d.insurance!=null)
			this.insurance=d.insurance;
		if(d.relisingDate!=null)
			this.relisingDate=d.relisingDate;
		if(d.car!=null)
			this.car=d.car;
		if(d.user!=null)
			this.user=d.user;
	}


	public Integer getComfort() {
		return comfort;
	}


	public void setComfort(Integer comfort) {
		this.comfort = comfort;
	}


	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}

	
}
