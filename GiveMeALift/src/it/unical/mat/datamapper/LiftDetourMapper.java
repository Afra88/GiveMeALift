package it.unical.mat.datamapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;

public class LiftDetourMapper extends AbstractMapper{

	public List<LiftDetour> findDetourFromPickUpAndDropOffPoints(String pPoint, String dPoint){
		
		List<LiftDetour> res = new LinkedList<LiftDetour>();
		
		String findStatement = "from LiftDetour"
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
								+ "or difference(dropOffPoint.region, :par2)>=1 )";
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", pPoint);
		parameters.put("par2", dPoint);
		
		Collection<DomainObject> objects=find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			res.add((LiftDetour) object);
		}		

		return res;	
	}
	
//	public List<Lift> findDetourByFromAndToAndCost(String cityFrom, 
//			String cityTo, String cost){
//		List<Lift> result=null;
//		Map<String, Object> parameters=new HashMap<String, Object>();
//		String findStatement= "select d.* from Lift as l, Lift_Detour as d, LIFT_DETOURS_JOIN as j, Lift_point p1, lift_point p2 "
//							+ "where "
//							+ "l.lift_id=j.lift_id "
//							+ "and j.DETOUR_ID=d.LIFT_DETOUR_ID "
//							+ "and p1.lift_point_id=d.pick_up and p2.lift_point_id=drop_off "
//							+ "and( "
//							+ "p1.city like :par1 "
//							+ "or p1.street like :par1 "
//							+ "or p1.state like :par1 "
//							+ "or p1.province like :par1 "
//							+ "or p1.region like :par1) "
//							+ "and (p2.city like :par2 "
//							+ "or p2.street like :par2 "
//							+ "or p2.state like :par2 "
//							+ "or p2.province like :par2 "
//							+ "or p2.region like :par2)"
//							;
//		
//		parameters.put("par1", cityFrom);
//		parameters.put("par2", cityTo);
////		try {
////			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
////			parameters.put("par3", d);
////		} catch (ParseException e) {
////			e.printStackTrace();
////		}
//		Collection<DomainObject> objects=find(findStatement, parameters,true);
//		if(!objects.isEmpty()){		
//			result = new LinkedList<Lift>();
//			for (DomainObject object : objects) {
//				result.add((Lift) object);
//			}
//		}
//		
//		return result;
//	
//}
//	
	
	public List<Lift> findDetourByFromAndToAndCostAndTimeAndDate(String cityFrom, 
			String cityTo, String date, String cost, Integer timeTo, Integer timeFrom, String flexibleDate, SortOption sort){
		List<Lift> result=null;
		Map<String, Object> parameters=new HashMap<String, Object>();
		String findStatement= "select l from Lift as l "
						+ "join l.detours as d "
						+ "join d.pickUpPoint as p1 "
						+ "join d.dropOffPoint as p2 "
							+ " where "
							+ "(difference(p1.city,:par1) >=2 "
							+ "or difference(p1.street,:par1) >=1 "
							+ "or difference(p1.state, :par1) >=1 "
							+ "or difference(p1.province, :par1) >=1 "
							+ "or difference(p1.region, :par1) >=1 )"
							+ "and (difference(p2.city, :par2)>=2 "
							+ "or difference(p2.street, :par2) >=1 "
							+ "or difference(p2.state, :par2) >=1 "
							+ "or difference(p2.province, :par2) >=1 "
							+ "or difference(p2.region, :par2)>=1 )"
							;
//		if(cost!=null){		
//			if(cost.equals("1")){
//				findStatement+= "and cost<avg(cost) ";
//			}
//			if(cost.equals("2")){
//				findStatement+= "and cost>=avg(cost) and cost<=max(cost) ";
//			}
//			if(cost.equals("3")){
//				findStatement+= "and cost>=avg(cost) ";
//			}
//		}
//		if(timeTo!=null && timeFrom!=null){
//			findStatement+= "and departureTime<=:par3 and departureTime>=:par4 ";
//			parameters.put("par4", timeFrom);
//			parameters.put("par3", timeTo);
//		}
		parameters.put("par1", cityFrom);
		parameters.put("par2", cityTo);
//		try {
//			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
//			if(flexibleDate!=null && flexibleDate!="" && flexibleDate.matches("[0-9]+") && Integer.parseInt(flexibleDate)<=60){
//				findStatement+= "and DATEDIFF(departureDate, :d) <=:par5 and DATEDIFF(:d, departureDate)<=:par5 "; //and DATEDIFF(:d, departureDate)<=:par5 or DATEDIFF(departureDate, :d)<=:par5 
//				parameters.put("par5", flexibleDate);
//				parameters.put("d",d);
//			}
//			else{
//				findStatement+= " and departureDate=:par5 ";
//				parameters.put("par5", d);
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		if(sort==SortOption.DATE){
//			findStatement+=" order by departureDate";
//		}
//		if(sort==SortOption.COST){
//			findStatement+=" order by cost";
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
	
	
//	public List<Lift> findDetourByFromAndToAndCostAndTimeAndDate(String cityFrom, 
//			String cityTo, String date, String cost, Integer timeTo, Integer timeFrom, String flexibleDate, SortOption sort){
//		List<Lift> result=null;
//		Map<String, Object> parameters=new HashMap<String, Object>();
//		String findStatement= "from Lift";
////							+ "left join LIFT_DETOURS_JOIN as j on l.lift_id=j.lift_id "
////							+ "left join Lift_Detour as d on j.DETOUR_ID=d.LIFT_DETOUR_ID "
////							+ "left join Lift_point p1 on p1.lift_point_id=d.pick_up "
////							+ "left join lift_point p2 on p2.lift_point_id=drop_off "
////							+ "where"
////							+ "( "
////							+ "p1.city like :par1 "
////							+ "or p1.street like :par1 "
////							+ "or p1.state like :par1 "
////							+ "or p1.province like :par1 "
////							+ "or p1.region like :par1) "
////							+ "and (p2.city like :par2 "
////							+ "or p2.street like :par2 "
////							+ "or p2.state like :par2 "
////							+ "or p2.province like :par2 "
////							+ "or p2.region like :par2)"
//							;
////							+ "departureDate=:par3 ";
////		if(cost!=null){		
////			if(cost.equals("1")){
////				findStatement+= "and cost<avg(cost) ";
////			}
////			if(cost.equals("2")){
////				findStatement+= "and cost>=avg(cost) and cost<=max(cost) ";
////			}
////			if(cost.equals("3")){
////				findStatement+= "and cost>=avg(cost) ";
////			}
////		}
////		if(timeTo!=null && timeFrom!=null){
////			findStatement+= "and departureTime<=:par4 and departureTime>=:par5 ";
////			parameters.put("par5", timeFrom);
////			parameters.put("par4", timeTo);
////		}
////		parameters.put("par1", cityFrom);
////		parameters.put("par2", cityTo);
////		try {
////			Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
////			if(flexibleDate!=null && flexibleDate!="" && flexibleDate.matches("[0-9]+") && Integer.parseInt(flexibleDate)<=60){
////				findStatement+= "and DATEDIFF(departureDate, :d) <=:par5 and DATEDIFF(:d, departureDate)<=:par5 "; //and DATEDIFF(:d, departureDate)<=:par5 or DATEDIFF(departureDate, :d)<=:par5 
////				parameters.put("par5", flexibleDate);
////				parameters.put("d",d);
////			}
////			else{
////				findStatement+= " and departureDate=:par5 ";
////				parameters.put("par5", d);
////			}
////		} catch (ParseException e) {
////			e.printStackTrace();
////		}
////		if(sort==SortOption.DATE){
////			findStatement+=" order by departureDate";
////		}
////		if(sort==SortOption.COST){
////			findStatement+=" order by d.cost";
////		}
//		result = new LinkedList<Lift>();
//		Collection<DomainObject> objects=find(findStatement, parameters, false);
//		if(!objects.isEmpty()){		
//			for (DomainObject object : objects) {
//				System.out.println(object.toString());
//				result.add((Lift) object);
//			}
//		}
//		
//		return result;
//	}
}
