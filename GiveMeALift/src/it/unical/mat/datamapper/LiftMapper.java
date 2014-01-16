package it.unical.mat.datamapper;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;
import it.unical.mat.util.HibernateUtil;

import java.util.Date;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
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
		Collection<DomainObject> objects=find(findStatement, parameters,false);
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
		Collection<DomainObject> objects=find(findStatement, parameters,false);
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
		String findStatement= "select d.* from Lift as l, Lift_Detour as d, LIFT_DETOURS_JOIN as j, Lift_point p1, lift_point p2 "
							+ "where "
							+ "l.lift_id=j.lift_id "
							+ "and j.lift_detour_id=d.lift_detour_id "
							+ "and p1.lift_point_id=d.pick_up and p2.lift_point_id=drop_off "
							+ "and( "
							+ "p1.city like :par1 "
							+ "or p1.street like :par1 "
							+ "or p1.state like :par1 "
							+ "or p1.province like :par1 "
							+ "or p1.region like :par1) "
							+ "and (p2.city like :par2 "
							+ "or p2.street like :par2 "
							+ "or p2.state like :par2 "
							+ "or p2.province like :par2 "
							+ "or p2.region like :par2)"
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
		Collection<DomainObject> objects=find(findStatement, parameters,true);
		if(!objects.isEmpty()){		
			result = new LinkedList<Lift>();
			for (DomainObject object : objects) {
				result.add((Lift) object);
			}
		}
		
		return result;
	}
}
