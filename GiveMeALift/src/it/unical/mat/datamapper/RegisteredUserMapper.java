package it.unical.mat.datamapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.RegisteredUser;


public class RegisteredUserMapper extends AbstractMapper {
	
	public List<RegisteredUser> findAll() {
		List<RegisteredUser> result = new LinkedList<RegisteredUser>();
		Collection<DomainObject> objects = super.find("from RegisteredUser", null,false);
		for (DomainObject object : objects) {
			result.add((RegisteredUser) object);
		}
		return result;
	}
	
	public List<RegisteredUser> getMaleUsers(String g){
		List<RegisteredUser> users = new LinkedList<RegisteredUser>();
				
		String findStatement = "from RegisteredUser"
				+ " where " 			
				+ " gender = :par1"
				;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", g);
		
		Collection<DomainObject> objects = find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			users.add((RegisteredUser) object);
		}		

		return users;
		
	}
	
	
	public List<RegisteredUser> getUserBornInYear(Integer year){
		List<RegisteredUser> users = new LinkedList<RegisteredUser>();
		
		String findStatement = "from RegisteredUser"
				+ " where " 			
				+ " birthYear = :par1"
				;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", year);
		
		Collection<DomainObject> objects = find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			users.add((RegisteredUser) object);
		}		

		return users;
		
	}

	public RegisteredUser findUserByEmailAndPassword(String email, String psw) {
		List<RegisteredUser> users = new LinkedList<RegisteredUser>();
		
		String findStatement = "from RegisteredUser"
				+ " where " 			
				+ " email = :par1"
				+ " and password = :par2"
				;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", email);
		parameters.put("par2", psw);
		
		Collection<DomainObject> objects = find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			users.add((RegisteredUser) object);
		}		

		if(users.size()==1)
			return users.get(0);
		return null;
	}

}