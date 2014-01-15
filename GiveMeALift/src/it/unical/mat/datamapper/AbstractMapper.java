package it.unical.mat.datamapper;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.util.HibernateUtil;

public abstract class AbstractMapper {
	
	public long insert(DomainObject o){
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

	public boolean delete(Long id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			DomainObject object= (DomainObject) session.get(DomainObject.class, id);
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
	
	public boolean update(DomainObject object2){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			DomainObject object= (DomainObject) session.get(DomainObject.class, (Serializable) object2.getId());
			object.copy(object2);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();		
		}
		return false;	
	}

	protected Collection<DomainObject> find(String findStatement,Map<String,Object> parameters){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
				Query query= session.createQuery(findStatement);
				if(parameters!=null){	
					for (String key : parameters.keySet()) {
						String objectType=parameters.get(key).getClass().getSimpleName();
						String methodeToInvoke="set"+objectType;
						if(objectType.equals("Integer")){
							Method m=query.getClass().getMethod(methodeToInvoke, key.getClass(), int.class);
							m.invoke(query, key, parameters.get(key));
						}
						else if(objectType.equals("Boolean")){
							Method m=query.getClass().getMethod(methodeToInvoke, key.getClass(), boolean.class);
							m.invoke(query, key, parameters.get(key));
						}
						else{
							Method m=query.getClass().getMethod(methodeToInvoke, key.getClass(), parameters.get(key).getClass());
							m.invoke(query, key, parameters.get(key));
						}
					}
				}
				@SuppressWarnings("unchecked")
				Collection<DomainObject> objects=query.list();
//				for (DomainObject domainObject : objects) 
//					
//					
//				}
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

