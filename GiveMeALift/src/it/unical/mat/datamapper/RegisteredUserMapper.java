package it.unical.mat.datamapper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;
import it.unical.mat.util.HibernateUtil;

public class RegisteredUserMapper extends AbstractMapper {
	
	public List<RegisteredUser> findAll() {
		List<RegisteredUser> result = new LinkedList<RegisteredUser>();
		Collection<DomainObject> objects = super.find("from RegisteredUser", null);
		for (DomainObject object : objects) {
			result.add((RegisteredUser) object);
		}
		return result;
	}

}
