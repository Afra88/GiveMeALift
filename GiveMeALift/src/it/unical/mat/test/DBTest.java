package it.unical.mat.test;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unical.mat.datamapper.LiftDetourMapper;
import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.datamapper.LiftPointMapper;
import it.unical.mat.datamapper.LiftPreferenceMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.domain.LiftPoint;
import it.unical.mat.domain.LiftPreference;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.util.HibernateUtil;

import org.junit.BeforeClass;
import org.junit.Test;


public class DBTest {
	
	private static RegisteredUser user = new RegisteredUser(); 
	private static RegisteredUserMapper rm = new RegisteredUserMapper();
	private static Lift l = new Lift();
	private static LiftMapper lm = new LiftMapper();
	
	@BeforeClass
	public static void prepareDB(){
		
		insertUser(user, rm);
		insertLift(l,lm);
		
					
	}
	

	private static void insertUser(RegisteredUser user,
			RegisteredUserMapper rm) {
					
		user.setName("John");
		user.setSurname("Bon Jovi");
		user.setEmail("user@test.it");
		user.setGender("M");
		user.setBirthYear(1975); 
		user.setPhone("0984000001");
		Address a = new Address("Viale Mancini", "Cosenza", "Italia");
		user.setAddress(a);
		user.setCountAlert(1);
	//	user.setDriverInfo(new DriverInfo().se);
		long id = rm.insert(user);
		System.out.println(id);
		
	}

	private static void insertLiftPoint(LiftPoint lp){
		LiftPointMapper lpm = new LiftPointMapper();
		lpm.insert(lp);
	}
		
	private static void insertLift(Lift l, LiftMapper lm) {
		LiftPoint pickUpPoint = new LiftPoint("Roma");
		LiftPoint p0 = new LiftPoint("Firenze");	//detour0
		LiftPoint p1 = new LiftPoint("Napoli");		//detour1
		LiftPoint dropOffPoint = new LiftPoint("Cosenza");
		
		insertLiftPoint(pickUpPoint);
		insertLiftPoint(p0);
		insertLiftPoint(p1);
		insertLiftPoint(dropOffPoint);
		
		l.setPickUpPoint(pickUpPoint);
		l.setDropOffPoint(dropOffPoint);
		
		l.setCost(25);
		l.setnSeat(3);
		
		LiftDetour det0 = new LiftDetour();
		LiftDetour det1 = new LiftDetour();
		det0.setPickUpPoint(pickUpPoint);
		det0.setDropOffPoint(p0);
		det1.setPickUpPoint(p1);
		det1.setDropOffPoint(dropOffPoint);
		
		LiftDetourMapper ld = new LiftDetourMapper();
		ld.insert(det0);
		ld.insert(det1);
		
		
		List<LiftDetour> detours = new ArrayList<LiftDetour>();
		detours.add(det0);
		detours.add(det1);
		l.setDetours(detours);
		
		l.setPossibleDetour(true);
		l.setUserOffering(user);
		
		LiftPreference pref = new LiftPreference();
		pref.setLuggageSize(1);
		pref.setPinkTrip(true);
		pref.setRoadType("Autostrada");
		pref.setTimesForThisRoute(1);
		
		LiftPreferenceMapper lpref = new LiftPreferenceMapper();
		lpref.insert(pref);
		l.setLiftPreferences(pref);
		
		lm.insert(l);
	}
	
	
	@Test
	public void UsersBornIn(){
		assertTrue(rm.getUserBornInYear(1975).size() > 1);
		HibernateUtil.shutdown();
	}
	
	@Test
	public void UsersOfGender(){
		assertTrue(rm.getMaleUsers("M").size() >= 0);
		assertTrue(rm.getMaleUsers("F").size() >= 0);
		HibernateUtil.shutdown();
	}
	
	/* 
	 * TO CHECK 
	 * 
	 */
	
	@Test
	public void searchLiftFromAndTo(){
		assertTrue(lm.findDetourByFromAndToAndCost("Roma", "Cosenza", "25").size() >= 0);
		HibernateUtil.shutdown();
	}

}
