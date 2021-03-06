package it.unical.mat.datamapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.util.HibernateUtil;

public abstract class AbstractMapper {
	
	public final long insert(DomainObject o){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		long objectId = 0;
		try {
			transaction = session.beginTransaction();
			objectId = (Long) session.save(o);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return objectId;	
	}

	public final boolean delete(Long id, Class<? extends DomainObject> objectClass){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			DomainObject object= (DomainObject) session.get(objectClass, id);
			session.delete(object);
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
	
	public final DomainObject update(DomainObject object2, long id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		RegisteredUser ob=null;
		try {
			transaction = session.beginTransaction();
			DomainObject object= (DomainObject) session.get(object2.getClass(), id);
			object.copy(object2);
//			if(object instanceof RegisteredUser){
//				ob=(RegisteredUser) object;
//				System.out.println(((RegisteredUser) object).getCar().getBrand());
//				System.out.println(((RegisteredUser) object2).getCar().getBrand());
//			}
			session.saveOrUpdate(object);
			transaction.commit();
			return object;
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.flush();
			session.close();
//			if(object2 instanceof RegisteredUser){
//				System.out.println(((RegisteredUser) ob).getCar().getBrand());
//				System.out.println(((RegisteredUser) object2).getCar().getBrand());
//			}
		}
		return null;	
	}

	protected final List<DomainObject> find(String findStatement,Map<String,Object> parameters, boolean isSql){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query;
			if(isSql){
				query = session.createSQLQuery(findStatement);
			}
			else
				query = session.createQuery(findStatement);
				if(parameters!=null){	
					for (String key : parameters.keySet()) {
						String objectType=parameters.get(key).getClass().getSimpleName();
						String methodeToInvoke="set"+objectType;
						Method m=null;
						if(parameters.get(key).getClass().getPackage().equals("it.unical.mat.domain")){
							//getSuperclass().getSimpleName().equals("User") || parameters.get(key).getClass().getSuperclass().getSimpleName().equals("DomainObject")){						
							methodeToInvoke="setEntity";
							m=query.getClass().getMethod(methodeToInvoke, key.getClass(), Object.class);
						}
						else if(objectType.equals("Integer")){
							m=query.getClass().getMethod(methodeToInvoke, key.getClass(), int.class);
						}
						else if(objectType.equals("Boolean")){
							m=query.getClass().getMethod(methodeToInvoke, key.getClass(), boolean.class);
						}
						else if(objectType.equals("Long")){
							m=query.getClass().getMethod(methodeToInvoke, key.getClass(), long.class);
						}
						else{
							m=query.getClass().getMethod(methodeToInvoke, key.getClass(), parameters.get(key).getClass());
						}
						m.invoke(query, key, parameters.get(key));
					}
				}
				@SuppressWarnings("unchecked")
				List<DomainObject> objects=query.list();
				transaction.commit();
				return objects;
		} catch (HibernateException | NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	
}

