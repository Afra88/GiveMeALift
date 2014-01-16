package it.unical.mat.datamapper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

}
