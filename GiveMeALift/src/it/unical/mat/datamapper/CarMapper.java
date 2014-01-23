package it.unical.mat.datamapper;

import it.unical.mat.domain.Car;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CarMapper extends AbstractMapper {

	public boolean deleteCar(Car car, RegisteredUser user){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			user.setCar(null);
			session.update(user);
			session.delete(car);		
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
