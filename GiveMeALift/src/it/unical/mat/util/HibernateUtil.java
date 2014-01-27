package it.unical.mat.util;

import it.unical.mat.domain.Address;
import it.unical.mat.domain.Administrator;
import it.unical.mat.domain.Car;
import it.unical.mat.domain.Conversation;
import it.unical.mat.domain.Feedback;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.domain.LiftPoint;
import it.unical.mat.domain.LiftPreference;
import it.unical.mat.domain.Message;
import it.unical.mat.domain.PersonalPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.Review;
import it.unical.mat.domain.SocialNetworkProfile;
import it.unical.mat.domain.User;
import it.unical.mat.domain.UserActivity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration()
								.configure("resource/hibernate.cfg.xml")
								.addPackage("it.unical.mat.domain") 
								.addAnnotatedClass(User.class)
								.addAnnotatedClass(RegisteredUser.class)
								.addAnnotatedClass(Administrator.class)
								.addAnnotatedClass(Address.class)
//								.addAnnotatedClass(DomainObject.class)
								.addAnnotatedClass(Conversation.class)
								.addAnnotatedClass(LiftPoint.class)
								.addAnnotatedClass(LiftDetour.class)
								.addAnnotatedClass(Lift.class)
								.addAnnotatedClass(Car.class)
								.addAnnotatedClass(Feedback.class)
								.addAnnotatedClass(Message.class)
								.addAnnotatedClass(PersonalPreference.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(SocialNetworkProfile.class)
								.addAnnotatedClass(UserActivity.class)
								.addAnnotatedClass(LiftPreference.class)
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
