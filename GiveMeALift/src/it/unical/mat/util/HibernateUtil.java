package it.unical.mat.util;

import it.unical.mat.domain.Address;
import it.unical.mat.domain.Administrator;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.domain.LiftPoint;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration()
								.configure(ClassLoader.getSystemResource("resource/hibernate.cfg.xml"))
								.addPackage("it.unical.mat.domain") 
								.addAnnotatedClass(User.class)
								.addAnnotatedClass(RegisteredUser.class)
								.addAnnotatedClass(Administrator.class)
								.addAnnotatedClass(Address.class)
//								.addAnnotatedClass(DomainObject.class)
								.addAnnotatedClass(LiftPoint.class)
								.addAnnotatedClass(LiftDetour.class)
								.addAnnotatedClass(Lift.class)
								.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown(){
		sessionFactory.close();
	}
}
