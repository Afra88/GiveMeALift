package it.unical.mat.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "USER_TYPE",
    discriminatorType = DiscriminatorType.STRING
)
public abstract class User extends DomainObject {
	
	@Column (name="EMAIL",unique=true,nullable=false)
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
	
	@Column(name="PHOTO")
	private String car_photo;

	@Embedded
	@Column (name="ADDRESS")
	private Address address;

	private List<SocialNetworkProfile> listSocialNetworkProfiles;

	public User() {
		name = "";
		surname = "";
		yearOfBirth = 0;
		email = "";
		phone = "";
		mobilePhone = "";
		address = new Address();
		car_photo = null;
		gender = null;
		password = null;
		List<SocialNetworkProfile> listSocialNetworkProfiles=new LinkedList<SocialNetworkProfile>();

	}
	
	public String getCar_photo() {
		return car_photo;
	}
	
	public void setCar_photo(String car_photo) {
		this.car_photo = car_photo;
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
		if(u.address != null )
			this.address=u.address;
		if(u.email != null)
			this.email=u.email;
		if(u.gender != null)
			this.gender=u.gender;
		if(u.mobilePhone != null)
			this.mobilePhone=u.mobilePhone;
		if(u.name != null)
			this.name=u.name;
		if(u.password != null)
			this.password=u.password;
		if(u.phone != null)
			this.phone=u.phone;
		if(u.surname != null)
			this.surname=u.surname;
		if(u.yearOfBirth != 0)
			this.yearOfBirth=u.yearOfBirth;
		if(u.car_photo != null)
			this.car_photo = u.car_photo;
		if(u.listSocialNetworkProfiles != null)
			this.listSocialNetworkProfiles = u.listSocialNetworkProfiles;
	}

	@OneToMany(fetch=FetchType.LAZY) //ok
	@JoinTable(name = "SOCIAL_NETWORK_USER_JOIN",
				joinColumns = { @JoinColumn (name = "USER_ID") },
				inverseJoinColumns = { @JoinColumn(name = "SOCIAL_NETWORK_PROFILE_ID") })
	public List<SocialNetworkProfile> getListSocialNetworkProfiles() {
		return listSocialNetworkProfiles;
	}

	public void setListSocialNetworkProfiles(
			List<SocialNetworkProfile> listSocialNetworkProfiles) {
		this.listSocialNetworkProfiles = listSocialNetworkProfiles;
	}

}
