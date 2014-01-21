package it.unical.mat.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class DBTest {
	
	private static RegisteredUser user = new RegisteredUser(); 
	private static RegisteredUserMapper rm = new RegisteredUserMapper();
	private static Lift l = new Lift();
	private static LiftMapper lm = new LiftMapper();
	private static LiftPointMapper lpm = new LiftPointMapper();
	private static LiftDetourMapper ldm = new LiftDetourMapper();
	
	@BeforeClass
	public static void prepareDB(){
		insertUser(user);
		insertLift(l);				
	}
	

	private static void insertUser(RegisteredUser user) {
					
		user.setName("John");
		user.setSurname("Bon Jovi");
		user.setEmail("user@test.it");
		user.setGender("M");
		user.setBirthYear(1975); 
		user.setPhone("0984000001");
		Address a = new Address("Viale Mancini", "Cosenza", "Italia");
		user.setAddress(a);
		user.setCountAlert(1);
		rm.insert(user);
		
	}

		
	private static void insertLift(Lift l) {
		LiftPoint pickUpPoint = new LiftPoint("Roma");
		LiftPoint p0 = new LiftPoint("Firenze");	//detour0
		LiftPoint p1 = new LiftPoint("Napoli");		//detour1
		LiftPoint dropOffPoint = new LiftPoint("Cosenza");
		
		lpm.insert(pickUpPoint);
		lpm.insert(p0);
		lpm.insert(p1);
		lpm.insert(dropOffPoint);
		
		l.setPickUpPoint(pickUpPoint);
		l.setDropOffPoint(dropOffPoint);
		
		l.setCost(25);
		l.setnSeat(3);
		
		LiftDetour det0 = new LiftDetour();
		LiftDetour det1 = new LiftDetour();
		LiftDetour det2 = new LiftDetour();
		det0.setPickUpPoint(pickUpPoint);
		det0.setDropOffPoint(p0);
		det1.setPickUpPoint(p0);
		det1.setDropOffPoint(p1);
		det2.setPickUpPoint(p1);
		det2.setDropOffPoint(dropOffPoint);
		
//		List<LiftDetour> detours = new LinkedList<LiftDetour>();
//		detours.add(det0);
//		detours.add(det1);
//		detours.add(det2);
//		l.setDetours(detours);
		
		ldm.insert(det0);
		ldm.insert(det1);
		ldm.insert(det2);
		
		
		l.setPossibleDetour(true);
		l.setUserOffering(user);
		
		LiftPreference pref = new LiftPreference();
		pref.setLuggageSize(1);
		pref.setPinkTrip(true);
		pref.setRoadType("Autostrada");
//		pref.setTimesForThisRoute(1);
		
		LiftPreferenceMapper lpref = new LiftPreferenceMapper();
		lpref.insert(pref);
		l.setLiftPreferences(pref);
		
		try {
			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse("20/01/2014");
			java.sql.Date dd=new java.sql.Date(d.getTime());
			l.setDepartureDate(dd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lm.insert(l);
	}
	
	@AfterClass
	public static void closeSession(){
		HibernateUtil.shutdown();
	}
	
	@Test
	public void UsersBornIn(){
		assertTrue(rm.getUserBornInYear(1975).size() > 1);
	}
	
	@Test
	public void UsersOfGender(){
		assertTrue(rm.getMaleUsers("M").size() >= 0);
		assertTrue(rm.getMaleUsers("F").size() == 0);
	}
		
	
	@Test
	public void searchLiftFromAndTo(){
		assertTrue(lm.findLiftByFromAndTo("Roma", "Cosenza", "20/01/2014").size() > 0);
		assertTrue(lpm.findLiftPointByLocation("Roma").size()> 0);
		assertTrue(lpm.findLiftPointByLocation("Cosenza").size()> 0);
		assertTrue(lpm.findLiftPointByLocation("Firenze").size()> 0);
		assertTrue(lpm.findLiftPointByLocation("Napoli").size()> 0);
		assertTrue(ldm.findDetourFromPickUpAndDropOffPoints("Cosenza", "Firenze").size()> 0);
	}

}
