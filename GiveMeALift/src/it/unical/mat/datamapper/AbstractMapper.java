package it.unical.mat.datamapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
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

	protected Collection<DomainObject> find(String findStatement){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<DomainObject> objects = session.createQuery(findStatement).list();
			transaction.commit();
			return objects;
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	
}

