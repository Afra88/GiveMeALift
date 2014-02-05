package it.unical.mat.datamapper;

import it.unical.mat.controller.Compare;
import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.service.ParseDate;
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
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

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
	
	
	public List<Lift> findLiftByFromAndTo(String cityFrom, String cityTo, String date, String sort){
		List<Lift> result = new LinkedList<Lift>();
		String findStatement= "from Lift"
							+ " where "
							+ "(difference(pickUpPoint.city,:par1) >=2 "
							+ "or difference(pickUpPoint.street,:par1) >=1 "
							+ "or difference(pickUpPoint.state, :par1) >=1 "
							+ "or difference(pickUpPoint.province, :par1) >=1 "
							+ "or difference(pickUpPoint.region, :par1) >=1 )"
							+ "and (difference(dropOffPoint.city, :par2)>=2 "
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
			String cityTo, String date, String cost, Integer timeTo, Integer timeFrom, String flexibleDate, SortOption sort){
		List<Lift> result=null;
		Map<String, Object> parameters=new HashMap<String, Object>();
		String findStatement= "from Lift"
							+ " where "
							+ "(difference(pickUpPoint.city,:par1) >=2 "
							+ "or difference(pickUpPoint.street,:par1) >=1 "
							+ "or difference(pickUpPoint.state, :par1) >=1 "
							+ "or difference(pickUpPoint.province, :par1) >=1 "
							+ "or difference(pickUpPoint.region, :par1) >=1 )"
							+ "and (difference(dropOffPoint.city, :par2)>=2 "
							+ "or difference(dropOffPoint.street, :par2) >=1 "
							+ "or difference(dropOffPoint.state, :par2) >=1 "
							+ "or difference(dropOffPoint.province, :par2) >=1 "
							+ "or difference(dropOffPoint.region, :par2)>=1 )"
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
			findStatement+= "and departureTime<=:par3 and departureTime>=:par4 ";
			parameters.put("par4", timeFrom);
			parameters.put("par3", timeTo);
		}
		parameters.put("par1", cityFrom);
		parameters.put("par2", cityTo);
		try {
			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
			if(flexibleDate!=null && !flexibleDate.equals("") && flexibleDate.matches("[0-9]+") && Integer.parseInt(flexibleDate)<=60){
				findStatement+= "and DATEDIFF(departureDate, :d) <=:par5 and DATEDIFF(:d, departureDate)<=:par5 "; //and DATEDIFF(:d, departureDate)<=:par5 or DATEDIFF(departureDate, :d)<=:par5 
				parameters.put("par5", flexibleDate);
				parameters.put("d",d);
			}
			else{
				findStatement+= " and departureDate=:par5 ";
				parameters.put("par5", d);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(sort==SortOption.DATE){
			findStatement+=" order by departureDate";
		}
		if(sort==SortOption.COST){
			findStatement+=" order by cost";
		}
		Collection<DomainObject> objects=find(findStatement, parameters,false);
		result = new LinkedList<Lift>();
		if(!objects.isEmpty()){		
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
	
	public HashMap<String,Integer> findLiftStatsByYear(int year) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {			
			String findStatement = "select month(Lift.departureDate) as M, count(Lift_id) as C from Lift "
					+ " where year(Lift.departureDate)=:par1"
					+ " group by month(Lift.departureDate) ";
			
			transaction = session.beginTransaction();
			Query query = session.createSQLQuery(findStatement).addScalar("M", StringType.INSTANCE).addScalar("C", StringType.INSTANCE);
			query.setInteger("par1", year);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String,String>> aliasToValueMapList=query.list();
			HashMap<String, Integer> result=new HashMap<String, Integer>();
			for (Map<String, String> map : aliasToValueMapList) {
				Set<String> keys=map.keySet();
				String month = null;
				for (String string : keys) {
					if(string.equals("M"))
						month=map.get(string);
					if(string.equals("C"))
						result.put(ParseDate.months[Integer.parseInt(month)-1], Integer.parseInt(map.get(string)));
				}
			}
			for (Entry<String, Integer> res : result.entrySet()) {
				System.out.println(res.getKey()+" "+res.getValue());
			}
			transaction.commit();
			return result;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	
	
	public HashMap<String,Integer> findLiftStatsByMonthAndYear(int year,int month) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {		
			String findStatement = "select DAYOFMONTH(Lift.departureDate) as M, count(Lift_id) as C from Lift "
					+ " where year(Lift.departureDate)=:par1 and month(Lift.departureDate)=:par2 " 
					+ " group by DAYOFMONTH(Lift.departureDate) ";			
			transaction = session.beginTransaction();
			Query query = session.createSQLQuery(findStatement).addScalar("M", StringType.INSTANCE).addScalar("C", StringType.INSTANCE);
			query.setInteger("par1", year);
			query.setInteger("par2", month);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String,String>> aliasToValueMapList=query.list();
			HashMap<String, Integer> result=new HashMap<String, Integer>();
			for (Map<String, String> map : aliasToValueMapList) {
				Set<String> keys=map.keySet();
				String day = null;
				for (String string : keys) {
					if(string.equals("M"))
						day=map.get(string);
					if(string.equals("C"))
						result.put(day, Integer.parseInt(map.get(string)));
				}
			}
			for (Entry<String, Integer> res : result.entrySet()) {
				System.out.println(res.getKey()+" "+res.getValue());
			}
			transaction.commit();
			return result;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}


	public int findCountSeatOldLift(int i, Compare c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {		
			String findStatement="";
			if(c==Compare.EQ)
				findStatement= "select count(Lift_id) as C from Lift "
					+ " where nSeat=:par1 and departureDate<=CURRENT_DATE ";			
			else if(c==Compare.GQ)
				findStatement = "select count(Lift_id) as C from Lift "
						+ " where nSeat>=:par1 and departureDate<=CURRENT_DATE ";
			else 
				return 0;
			transaction = session.beginTransaction();
			Query query = session.createSQLQuery(findStatement).addScalar("C", IntegerType.INSTANCE);
			query.setInteger("par1", i);
			int result=(Integer) query.uniqueResult();
			transaction.commit();
			return result;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return 0;
	}
}

