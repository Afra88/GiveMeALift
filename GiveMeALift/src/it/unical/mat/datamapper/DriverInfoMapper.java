package it.unical.mat.datamapper;

import it.unical.mat.domain.Car;
import it.unical.mat.domain.DriverInfo;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DriverInfoMapper extends AbstractMapper {



public boolean deleteCar(DriverInfo d, Car car, RegisteredUser user){
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction transaction = null;
	try {
		transaction = session.beginTransaction();
		d.setCar(null);
		d.setCar_photo(null);
		
		/*TODO 
		 * controllare i campi color e confort di DriverInfo se hanno senso
		 * 
		 * */
		
		session.update(d);
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