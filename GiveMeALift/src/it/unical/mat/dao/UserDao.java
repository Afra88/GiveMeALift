package it.unical.mat.dao;

import it.unical.mat.domain.Address;

@Deprecated
public interface UserDao {

	public String saveRegisteredUser(int countAlert, boolean isOnlyPassenger
													,String email
													,String password
													,String name
													,String surname
													,String gender
													,int yearOfBirth
													,String phone
													,String mobilePhone
													,Address address);

	public void saveAdministrator(String email, String password, String name, 
								  String surname, String gender, int yearOfBirth,
								  String phone, String mobilePhone, Address address);
	
	//public String getUser
	
}
