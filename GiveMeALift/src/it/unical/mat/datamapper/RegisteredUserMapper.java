package it.unical.mat.datamapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.type.StringType;

import it.unical.mat.domain.Car;
import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Feedback;
import it.unical.mat.domain.PersonalPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;
import it.unical.mat.service.ParseDate;
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
	
	public List<RegisteredUser> findUserOfGender(String g){
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
				Hibernate.initialize(l.getListSocialNetworkProfiles());
//				List<Feedback> f = l.getReceivedFeedback();
//				Hibernate.initialize(f);
//				for (Feedback fb : f) 
//					Hibernate.initialize(fb);
					
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
	
	public HashMap<String,Integer> findMemberByMonth() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {			
			String findStatement = "select month(User_Activity.MEMBERSINCE) as M, count(User_id) as C from User,User_Activity "
					+ " where User.User_ID=User_Activity.MEMBER_ACTIVITY_ID"
					+ " group by month(User_Activity.MEMBERSINCE) ";
			
			transaction = session.beginTransaction();
			Query query = session.createSQLQuery(findStatement).addScalar("M", StringType.INSTANCE).addScalar("C", StringType.INSTANCE);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String,String>> aliasToValueMapList=query.list();
			HashMap<String, Integer> result=new HashMap<String, Integer>();
			for (Map<String, String> map : aliasToValueMapList) {
				Set<String> keys=map.keySet();
				String month = null;
				for (String string : keys) {
					if(string.equals("M"))
						month=map.get(string);
					if(string.equals("C"))
						result.put(ParseDate.months[Integer.parseInt(month)-1], Integer.parseInt(map.get(string)));
				}
			}
			for (Entry<String, Integer> res : result.entrySet()) {
				System.out.println(res.getKey()+" "+res.getValue());
			}
			transaction.commit();
			return result;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	
	public PersonalPreference loadPersonalPreference(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
				RegisteredUser u=(RegisteredUser) session.get(RegisteredUser.class, id);
				Hibernate.initialize(u.getPersonalPreference());
				transaction.commit();
				return u.getPersonalPreference();
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	
	public boolean deleteUser(RegisteredUser user){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(user);		
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
}