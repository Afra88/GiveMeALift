package it.unical.mat.datamapper;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;
import it.unical.mat.util.HibernateUtil;

import java.util.Date;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LiftMapper extends AbstractMapper {
	
//	public List<Lift> findFromTo(String CityFrom, String CityTo){
//		List<Lift> result = new LinkedList<Lift>();
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction = null;
//		try {
//			transaction = session.beginTransaction();
//			if(CityFrom!="" && CityFrom!=null && CityTo!="" && CityTo!=null){	
//				@SuppressWarnings("unchecked")
//				Query query= session.createQuery("from Lift"
////						+ " where "
////						+ "(pickUpPoint.city like :par1 "
////						+ "or pickUpPoint.street like :par1 "
////						+ "or pickUpPoint.state like :par1 )"
////						+ "and (dropOffPoint.city like :par2 "
////						+ "or dropOffPoint.street like :par2 "
////						+ "or dropOffPoint.state like :par2 )"
//						);
////				query.setString("par1", CityFrom);
////				query.setString("par2", CityTo);
//				@SuppressWarnings("unchecked")
//				Collection<DomainObject> objects=query.list();
//				transaction.commit();
//				for (DomainObject object : objects) {
//					result.add((Lift) object);
//				}
//				return result;
//			}
//		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
//			e.printStackTrace();
//			transaction.rollback();
//		} finally {
//			session.close();
//		}
//		return null;
//			
//		
//	}

//	"from Lift l, Lift_Point l1, Lift_Point l2,"
//	+ "where l.pick_up = 1 and l.drop_off=2 and l1.city=cityFrom and l2.city=cityTo"
	
	public List<Lift> findLiftByFromAndTo(String cityFrom, String cityTo, String date){
		List<Lift> result = new LinkedList<Lift>();
		String findStatement= "from Lift"
							+ " where "
							+ "(pickUpPoint.city like :par1 "
							+ "or pickUpPoint.street like :par1 "
							+ "or pickUpPoint.state like :par1 "
							+ "or pickUpPoint.province like :par1 "
							+ "or pickUpPoint.region like :par1) "
							+ "and (dropOffPoint.city like :par2 "
							+ "or dropOffPoint.street like :par2 "
							+ "or dropOffPoint.state like :par2 "
							+ "or dropOffPoint.province like :par2 "
							+ "or dropOffPoint.region like :par2)"
							;
//							+ "departureDate=:par3";
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", cityFrom);
		parameters.put("par2", cityTo);
//		try {
//			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
//			parameters.put("par3", d);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		Collection<DomainObject> objects=find(findStatement, parameters);
		for (DomainObject object : objects) {
			result.add((Lift) object);
		}
		return result;
	}
	
	public List<Lift> findLiftByFromAndToAndCostAndTimeAndDate(String cityFrom, 
			String cityTo, String date, String cost, Integer timeTo, Integer timeFrom){
		List<Lift> result=null;
		Map<String, Object> parameters=new HashMap<String, Object>();
		String findStatement= "from Lift"
							+ " where "
							+ "(pickUpPoint.city like :par1 "
							+ "or pickUpPoint.street like :par1 "
							+ "or pickUpPoint.state like :par1 "
							+ "or pickUpPoint.province like :par1 "
							+ "or pickUpPoint.region like :par1) "
							+ "and (dropOffPoint.city like :par2 "
							+ "or dropOffPoint.street like :par2 "
							+ "or dropOffPoint.state like :par2 "
							+ "or dropOffPoint.province like :par2 "
							+ "or dropOffPoint.region like :par2)"
							;
//							+ "departureDate=:par3 ";
		if(cost!=null){		
			if(cost.equals("1")){
				findStatement+= "and cost<avg(cost) ";
			}
			if(cost.equals("2")){
				findStatement+= "and cost>=avg(cost) and cost<=max(cost) ";
			}
			if(cost.equals("3")){
				findStatement+= "and cost>=avg(cost) ";
			}
		}
		if(timeTo!=null && timeFrom!=null){
			findStatement+= "and departureTime<=:par4 and departureTime>=:par5 ";
			parameters.put("par5", timeFrom);
			parameters.put("par4", timeTo);
		}
		parameters.put("par1", cityFrom);
		parameters.put("par2", cityTo);
//		try {
//			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
//			parameters.put("par3", d);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		Collection<DomainObject> objects=find(findStatement, parameters);
		if(!objects.isEmpty()){		
			result = new LinkedList<Lift>();
			for (DomainObject object : objects) {
				result.add((Lift) object);
			}
		}
		
		return result;
	}

	public Lift findById(String lift) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
				Query query= session.createQuery("from Lift where id=:par1");
				query.setLong("par1", Long.parseLong(lift));
				@SuppressWarnings("unchecked")
				List<Lift> objects=query.list();
				Lift l=(Lift) objects.get(0);
				Hibernate.initialize(l.getLiftPreferences());
				Hibernate.initialize(l.getDetours());
				transaction.commit();
				return l;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	
	public List<Lift> findDetourByFromAndToAndCostAndTimeAndDate(String cityFrom, 
			String cityTo, String date, String cost, Integer timeTo, Integer timeFrom){
		List<Lift> result=null;
		Map<String, Object> parameters=new HashMap<String, Object>();
		String findStatement= "from Lift"
							+ " where "
							+ "(detours.pickUpPoint.city like :par1 "
							+ "or detours.pickUpPoint.street like :par1 "
							+ "or detours.pickUpPoint.state like :par1 "
							+ "or detours.pickUpPoint.province like :par1 "
							+ "or detours.pickUpPoint.region like :par1) "
							+ "and (detours.dropOffPoint.city like :par2 "
							+ "or detours.dropOffPoint.street like :par2 "
							+ "or detours.dropOffPoint.state like :par2 "
							+ "or detours.dropOffPoint.province like :par2 "
							+ "or detours.dropOffPoint.region like :par2)"
							;
//							+ "departureDate=:par3 ";
		if(cost!=null){		
			if(cost.equals("1")){
				findStatement+= "and cost<avg(cost) ";
			}
			if(cost.equals("2")){
				findStatement+= "and cost>=avg(cost) and cost<=max(cost) ";
			}
			if(cost.equals("3")){
				findStatement+= "and cost>=avg(cost) ";
			}
		}
		if(timeTo!=null && timeFrom!=null){
			findStatement+= "and departureTime<=:par4 and departureTime>=:par5 ";
			parameters.put("par5", timeFrom);
			parameters.put("par4", timeTo);
		}
		parameters.put("par1", cityFrom);
		parameters.put("par2", cityTo);
//		try {
//			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
//			parameters.put("par3", d);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		Collection<DomainObject> objects=find(findStatement, parameters);
		if(!objects.isEmpty()){		
			result = new LinkedList<Lift>();
			for (DomainObject object : objects) {
				result.add((Lift) object);
			}
		}
		
		return result;
	}
}
