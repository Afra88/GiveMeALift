package it.unical.mat.datamapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;
import it.unical.mat.util.HibernateUtil;


public class RegisteredUserMapper extends AbstractMapper {
	
	public List<RegisteredUser> findAll() {
		List<RegisteredUser> result = new LinkedList<RegisteredUser>();
		Collection<DomainObject> objects = super.find("from RegisteredUser", null,false);
		for (DomainObject object : objects) {
			result.add((RegisteredUser) object);
		}
		return result;
	}
	
	public List<RegisteredUser> findMale(String g){
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
	
	
	public List<RegisteredUser> findByBirthYear(Integer year){
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

	public RegisteredUser findRegisteredUserById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
				Query query= session.createQuery("from RegisteredUser where id=:par1");
				query.setLong("par1", id);
				@SuppressWarnings("unchecked")
				List<User> objects=query.list();
				RegisteredUser l=(RegisteredUser) objects.get(0);
				Hibernate.initialize(l.getCar());
				transaction.commit();
				return l;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public List<RegisteredUser> findUsersFromCity(String city) {
		List<RegisteredUser> users = new LinkedList<RegisteredUser>();
		
		String findStatement = "from User"
				+ " where " 			
				+ " city = :par1"
				;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", city);
		
		Collection<DomainObject> objects = find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			users.add((RegisteredUser) object);
		}		

		
			return users;
	}
	
	public RegisteredUser findRegisteredUserByTelephone(String telephone) {
		List<RegisteredUser> users = new LinkedList<RegisteredUser>();				
				String findStatement = "from RegisteredUser"
						+ " where " 			
						+ " mobilePhone= :par1"
						;
				
				Map<String, Object> parameters=new HashMap<String, Object>();
				parameters.put("par1", telephone);
				
				Collection<DomainObject> objects = find(findStatement, parameters,false);
				for (DomainObject object : objects) {
					users.add((RegisteredUser) object);
				}		
		
				if(users.size()==1)
					return users.get(0);
				return null;
	}
	
	public List<?> findMemberByMonth() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {			
			String findStatement = "select month(User_Activity.MEMBERSINCE),count(User_id) from User,User_Activity "
					+ " where User.User_ID=User_Activity.MEMBER_ACTIVITY_ID"
					+ " group by month(User_Activity.MEMBERSINCE) ";
		
			transaction = session.beginTransaction();
			Query query = session.createSQLQuery(findStatement);
			List<?> res=query.list();
			transaction.commit();
			return res;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}


}