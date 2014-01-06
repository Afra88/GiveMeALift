package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "USER_TYPE",
    discriminatorType = DiscriminatorType.STRING
)
public abstract class User extends DomainObject {
	
	@Column (name="EMAIL",unique=true)
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

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USER_ID")
	public long getId() {return super.getId();};

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
	
	@Override
	public void copy(DomainObject object2) {
		User u= (User) object2;
		this.address=u.address;
		this.email=u.email;
		this.gender=u.gender;
		this.mobilePhone=u.mobilePhone;
		this.name=u.name;
		this.password=u.password;
		this.phone=u.phone;
		this.surname=u.surname;
		this.yearOfBirth=u.yearOfBirth;
	}
}
