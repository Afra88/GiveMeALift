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
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="USER")
@DiscriminatorColumn(
    name = "USER_TYPE",
    discriminatorType = DiscriminatorType.STRING
)
public abstract class User extends DomainObject {
	
	@Column (name="EMAIL",unique=true,nullable=false)
	private String email;
	
	@Column (name="PASSWORD")
	private String password;
	
	@Column (name="NAME", length = 30) //, nullable = false,
	private String name;
	
	@Column (name="SURNAME", length = 30) //, nullable = false, 
	private String surname;
	
	@Column (name = "GENDER", length = 1)
	private String gender;
	
	@Column (name="BIRTH_YEAR",nullable=true) //nullable = false
	private Integer birthYear;
	
	@Column (name="PHONE", length = 15)
	private String phone;
	
	@Column (name="MOBILE_PHONE", length = 15)
	private String mobilePhone;

	@Embedded
	@Column (name="ADDRESS")
	private Address address;

	private List<SocialNetworkProfile> listSocialNetworkProfiles;

	public User() {
		listSocialNetworkProfiles=new LinkedList<SocialNetworkProfile>();
	}

	public User(String email, String psw, String name, 
			String surname, String gender, Integer birthYear, 
			String phone, String mPhone, Address address){
		this.email = email;
		this.password = psw;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthYear = birthYear;
		this.phone = phone;
		this.mobilePhone = mPhone;
		this.address = address;
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
	
	public Integer getBirthYear() {
		return birthYear;
	}
	
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
		if(u.birthYear != null)
			this.birthYear=u.birthYear;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((birthYear == null) ? 0 : birthYear.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime
				* result
				+ ((listSocialNetworkProfiles == null) ? 0
						: listSocialNetworkProfiles.hashCode());
		result = prime * result
				+ ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthYear == null) {
			if (other.birthYear != null)
				return false;
		} else if (!birthYear.equals(other.birthYear))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (listSocialNetworkProfiles == null) {
			if (other.listSocialNetworkProfiles != null)
				return false;
		} else if (!listSocialNetworkProfiles
				.equals(other.listSocialNetworkProfiles))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	

}
