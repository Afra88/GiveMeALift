package it.unical.mat.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.dao.UserDao;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;
import it.unical.mat.util.HibernateUtil;

@Deprecated
public class UserDaoImpl implements UserDao {

	@Override
	public String saveRegisteredUser(int countAlert, boolean isOnlyPassenger,
			String email, String password, String name, String surname,
			String gender, int yearOfBirth, String phone, String mobilePhone,
			Address address)
	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String email_id = null;
		try {
			transaction = session.beginTransaction();
			User user = new RegisteredUser();
			user.setName(name);
			user.setSurname(surname);
			user.setEmail(email);
			email_id = (String) session.save(user);			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return email_id;

	}

	@Override
	public void saveAdministrator(String email, String password, String name,
			String surname, String gender, int yearOfBirth, String phone,
			String mobilePhone, Address address) {
		// TODO Auto-generated method stub

	}

}
