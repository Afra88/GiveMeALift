package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {
	@Id
	@Column (name="EMAIL")
	private String email;
	@Column (name="PASSWORD")
	private String password;
	@Column (name="NAME", nullable = false, length = 30)
	private String name;
	@Column (name="SURNAME", nullable = false, length = 30)
	private String surname;
	@Column (name = "GENDER", length = 1)
	private String gender;
	@Column (name="BIRTH_YEAR", nullable = false)
	private int yearOfBirth;
	@Column (name="PHONE", length = 15)
	private String phone;
	@Column (name="MOBILE_PHONE", length = 15)
	private String mobilePhone;
	@Embedded
	@Column (name="ADDRESS")
	private Address address;

	public User() {
		name = "";
		surname = "";
		yearOfBirth = 0;
		email = "";
		phone = "";
		mobilePhone = "";
		address = new Address();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public int getYearOfBirth() {
		return yearOfBirth;
	}


	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMobilePhone() {
		return mobilePhone;
	}


	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}	

}
