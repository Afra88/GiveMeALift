package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Car extends DomainObject {

	private String brand;
	private String model;
	private String color;
	private Integer confort;
	
	public Car(){}
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CAR_ID")
	public long getId() {return super.getId();};
	
	
	
	
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

	public Integer getConfort() {
		return confort;
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
		if(c.confort!=null)
			this.confort=c.confort;
		if(c.model!=null)
			this.model=c.model;
	}
}
