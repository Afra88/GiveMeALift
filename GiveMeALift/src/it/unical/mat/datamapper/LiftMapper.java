package it.unical.mat.datamapper;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
			String cityTo, String date, String cost, String timeTo, String timeFrom){
		List<Lift> result = new LinkedList<Lift>();
		Map<String, Object> parameters=new HashMap<String, Object>();
		String findStatement= "from Lift"
							+ " where "
							+ "(pickUpPoint.city like :par1 "
							+ "or pickUpPoint.street like :par1 "
							+ "or pickUpPoint.state like :par1 "
							+ "or pickUpPoint.province like :par1"
							+ "or pickUpPoint.region like :par1)"
							+ "and (dropOffPoint.city like :par2 "
							+ "or dropOffPoint.street like :par2 "
							+ "or dropOffPoint.state like :par2 "
							+ "or dropOffPoint.province like :par2 "
							+ "or dropOffPoint.region like :par2) "
							+ "departureDate=:par3 ";
		if(cost.equals("1")){
			findStatement+= "and cost<avg(cost) ";
		}
		if(cost.equals("2")){
			findStatement+= "and cost>=avg(cost) and cost<=max(cost) ";
		}
		if(cost.equals("3")){
			findStatement+= "and cost>=avg(cost) ";
		}
		if(!timeTo.equals("") && timeTo!=null && !timeFrom.equals("") && timeFrom!=null){
			findStatement+= "and departureTime<=:par4 and departureTime>=:par4 ";
			parameters.put("par4", timeTo);
			parameters.put("par4", timeFrom);
		}
		parameters.put("par1", cityFrom);
		parameters.put("par2", cityTo);
		try {
			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
			parameters.put("par3", d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Collection<DomainObject> objects=find(findStatement, parameters);
		for (DomainObject object : objects) {
			result.add((Lift) object);
		}
		return result;
	}

	public List<Lift> findLiftByFromAndToAndCostAndTimeAndDate(String input1,
			String input2, String input3, String radio, int timeTo, int timeFrom) {
		// TODO Auto-generated method stub
		return null;
	}

}
