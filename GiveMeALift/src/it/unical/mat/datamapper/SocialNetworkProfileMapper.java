package it.unical.mat.datamapper;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.SocialNetworkProfile;
import it.unical.mat.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SocialNetworkProfileMapper extends AbstractMapper {
	
	public List<SocialNetworkProfile> findSocialNetwork(User u){
		List<SocialNetworkProfile> profiles = new LinkedList<SocialNetworkProfile>();
				
		String findStatement = "Select * from SocialNetworkProfile"
				+ " where " 			
				+ " user = :par1"
				;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", u);
		
		Collection<DomainObject> objects = find(findStatement, parameters,true);
		for (DomainObject object : objects) {
			profiles.add((SocialNetworkProfile) object);
		}		

		return profiles;
		
	}
	

}
