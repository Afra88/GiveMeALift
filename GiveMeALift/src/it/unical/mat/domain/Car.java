package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Car")
public class Car extends DomainObject {

	@Column(name="BRAND")
	private String brand;
	@Column(name="MODEL")
	private String model;
	@Column(name="COLOR")
	private String color;
	@Column(name="CONFORT")
	private Integer confort;
	@Column(name="CAR_PHOTO")
	private String carPhoto;
	
	private RegisteredUser registeredUser;
	
	public Car(){}
	
	
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CAR_ID")
	public long getId() {return super.getId();};
	
	@Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}
	
	
	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	public String getCarPhoto() {
		return carPhoto;
	}
	
	public Integer getConfort() {
		return confort;
	}
	
	public void setCarPhoto(String carPhoto) {
		this.carPhoto = carPhoto;
	}
	
	public void setConfort(Integer confort) {
		this.confort = confort;
	}

	@Override
	public void copy(DomainObject object2) {
		Car c=(Car)object2;
		if(c.color!=null)
			this.color=c.color;
		if(c.brand!=null)
			this.brand=c.brand;
		if(c.model!=null)
			this.model=c.model;
		if(c.confort!=null)
			this.confort=c.confort;
		if(c.carPhoto!=null)
			this.carPhoto=c.carPhoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result
				+ ((carPhoto == null) ? 0 : carPhoto.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((confort == null) ? 0 : confort.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
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
		Car other = (Car) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (carPhoto == null) {
			if (other.carPhoto != null)
				return false;
		} else if (!carPhoto.equals(other.carPhoto))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (confort == null) {
			if (other.confort != null)
				return false;
		} else if (!confort.equals(other.confort))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

	
	
	
}
