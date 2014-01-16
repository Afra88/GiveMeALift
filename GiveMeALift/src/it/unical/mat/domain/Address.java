package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	@Column(name="STREET")
    private String street; 
	@Column(name="CITY")
	private String city; 
	@Column(name="STATE")
	private String state;
    
	public Address(){}
	
	public Address(String street, String city, String state){
		this.street = street;
		this.city = city;
		this.state = state;
	}
	
    public String toString() {
        return "street: " + getStreet() +
               ", city: " + getCity() +
               ", state: " + getState();
    }

    public String getStreet() {
        return street;
    }
    
    public void setStreet(String address) {
        this.street = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
