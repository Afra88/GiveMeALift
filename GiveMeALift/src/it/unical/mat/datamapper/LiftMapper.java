package it.unical.mat.datamapper;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.util.HibernateUtil;

import java.util.Date;
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
	
//	public long persist(Lift l, List<LiftDetour> detours, LiftPoint p, LiftPoint d, LiftPreference prefs, RegisteredUser user){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction = null;
//		long objectId = 0;
//		try {
//			transaction = session.beginTransaction();
//			l.setDetours(detours);
//			l.setLiftPreferences(prefs);
//			l.setPickUpPoint(p);
//			l.setDropOffPoint(d);
//			l.setUserOffering(user);
//			l.setUserOffering(user);
//			session.persist(l);
//			session.flush();
//			transaction.commit();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			transaction.rollback();
//		} finally {
//			session.close();
//		}
//		return objectId;	
//	}
	
	
	public List<Lift> findLiftByFromAndTo(String cityFrom, String cityTo, String date){
		List<Lift> result = new LinkedList<Lift>();
		String findStatement= "from Lift"
							+ " where "
							+ "(difference(pickUpPoint.city,:par1) >=1 "
							+ "or difference(pickUpPoint.street,:par1) >=1 "
							+ "or difference(pickUpPoint.state, :par1) >=1 "
							+ "or difference(pickUpPoint.province, :par1) >=1 "
							+ "or difference(pickUpPoint.region, :par1) >=1 )"
							+ "and (difference(dropOffPoint.city, :par2)>=1 "
							+ "or difference(dropOffPoint.street, :par2) >=1 "
							+ "or difference(dropOffPoint.state, :par2) >=1 "
							+ "or difference(dropOffPoint.province, :par2) >=1 "
							+ "or difference(dropOffPoint.region, :par2)>=1 )"
							+ " and departureDate=:par3 ";
							;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", cityFrom);
		parameters.put("par2", cityTo);
		try {
			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
			parameters.put("par3", d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
							+ "(difference(pickUpPoint.city,:par1) >=1 "
							+ "or difference(pickUpPoint.street,:par1) >=1 "
							+ "or difference(pickUpPoint.state, :par1) >=1 "
							+ "or difference(pickUpPoint.province, :par1) >=1 "
							+ "or difference(pickUpPoint.region, :par1) >=1 )"
							+ "and (difference(dropOffPoint.city, :par2)>=1 "
							+ "or difference(dropOffPoint.street, :par2) >=1 "
							+ "or difference(dropOffPoint.state, :par2) >=1 "
							+ "or difference(dropOffPoint.province, :par2) >=1 "
							+ "or difference(dropOffPoint.region, :par2)>=1 )"
							+ " and departureDate=:par3 ";
							;
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
		try {
			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
			parameters.put("par3", d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
//				System.out.println(objects.get(0).getClass());
				@SuppressWarnings("unchecked")
				List<Lift> objects=query.list();
				Lift l=(Lift) objects.get(0);
				Hibernate.initialize(l.getLiftPreferences());
				Hibernate.initialize(l.getUserOffering());
				Hibernate.initialize(l.getDetours());
				for (LiftDetour ld : l.getDetours()) {					
					Hibernate.initialize(ld.getPickUpPoint());
				}
				if(l.getReturnLift()!=null)
					Hibernate.initialize(l.getReturnLift());
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


	public List<Lift> findLiftOfferedByUser(long id) {
		List<Lift> result=new LinkedList<Lift>();
		String findStatement="from Lift where userOffering.id=:par1 and DATEDIFF(departureDate,CURDATE())>=0 ";
		Map<String,Object> parameters =new HashMap<String, Object>();
		parameters.put("par1", id);
		Collection<DomainObject> objects=find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			result.add((Lift) object);
		}	
		return result;
	}

	public List<Lift> findLiftBookedByUser(long id) {
		List<Lift> result=new LinkedList<Lift>();
		String findStatement="select * from Lift, LIFT_USER_BOOKING where lift.lift_id=LIFT_USER_BOOKING.lift_id and LIFT_USER_BOOKING.user_id=:par1 and DATEDIFF(departureDate,CURDATE())>=0 ";
		Map<String,Object> parameters =new HashMap<String, Object>();
		parameters.put("par1", id);
		Collection<DomainObject> objects=find(findStatement, parameters,true);
		
		if(objects!=null){
			for (DomainObject object : objects) {
				result.add((Lift) object);
			}				
		
		}
		return result;
	}

	public Lift findLiftByLiftReturn(long id) {
		List<Lift> result=new LinkedList<Lift>();
		String findStatement="from Lift where returnLift=:par1";
		Map<String,Object> parameters =new HashMap<String, Object>();
		parameters.put("par1", id);
		Collection<DomainObject> objects=find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			result.add((Lift) object);
		}	
		return result.get(0);
	}
	
	public boolean deleteReturnLift(Lift l,Lift lr){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			l.setReturnLift(null);
			session.update(l);
			session.delete(lr);
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

