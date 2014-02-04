package it.unical.mat.datamapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.domain.LiftDetour;
import it.unical.mat.util.HibernateUtil;

public class LiftDetourMapper extends AbstractMapper{
	

	public List<LiftDetour> findDetourByFromAndToAndCostAndTimeAndDate(String cityFrom, 
			String cityTo, String date, String cost, Integer timeTo, Integer timeFrom, String flexibleDate, SortOption sort, Boolean music, Boolean smok, Boolean pets, Integer chatnessLevel){
		List<LiftDetour> result=null;
		String findStatement= "select d from LiftDetour as d "
						+ "join d.pickUpPoint as p1 "
						+ "join d.dropOffPoint as p2 "
						+ "join d.lift as l "
						+ "join l.userOffering as u "
						+ "join u.personalPreference as pref "
							+ " where "
							+ "((LOCATE(LOWER(p1.city),LOWER(:par1))) > 0  or (LOCATE(LOWER(:par1),LOWER(p1.city))) > 0  )"
//							+ "or difference(p1.street,:par1) >=1 "
//							+ "or difference(p1.state, :par1) >=1 "
//							+ "or difference(p1.province, :par1) >=1 "
//							+ "or difference(p1.region, :par1) >=1 )"
							+ "and ((LOCATE(LOWER(p2.city),LOWER(:par2))) > 0 or (LOCATE(LOWER(:par2),LOWER(p2.city))) > 0 ) "
//							+ "or difference(p2.street, :par2) >=1 "
//							+ "or difference(p2.state, :par2) >=1 "
//							+ "or difference(p2.province, :par2) >=1 "
//							+ "or difference(p2.region, :par2)>=1 )"
							;
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = null;
			try {
				transaction = session.beginTransaction();
			if(cost!=null){		
				if(cost.equals("1"))
					findStatement+= "and cost<avg(cost) ";
				if(cost.equals("2"))
					findStatement+= "and cost>=avg(cost) and cost<=max(cost) ";
				if(cost.equals("3"))
					findStatement+= "and cost>=avg(cost) ";
			}
			if(timeTo!=null && timeFrom!=null)
				findStatement+= "and hour(departureTime)>=:par3 and hour(departureTime)<=:par4 ";
			if(flexibleDate!=null && !flexibleDate.equals("") && flexibleDate.matches("[0-9]+") && Integer.parseInt(flexibleDate)<=60)
				findStatement+= "and DATEDIFF(departureDate, :d) <=:par5 and DATEDIFF(:d, departureDate)<=:par5 ";
			else
				findStatement+= " and departureDate=:par5 ";
			if(music!=null){
				findStatement+= " and pref.music=:music ";
			}
			if(pets!=null){
				findStatement+= " and pref.petsOnBoard=:pets ";
			}
			if(chatnessLevel!=null){
				findStatement+= " and pref.chatnessLevel=:chatness ";
			}
			if(smok!=null){
				findStatement+= " and pref.smoking=:smoking ";
			}
			if(sort==SortOption.DATE)
				findStatement+=" order by departureDate";
			if(sort==SortOption.COST)
				findStatement+=" order by cost";
			Query q=session.createQuery(findStatement);
			q.setString("par1", cityFrom);
			q.setString("par2", cityTo);
			if(timeTo!=null && timeFrom!=null){
				q.setInteger("par3", timeFrom);
				q.setInteger("par4", timeTo);
			}
			try {
				Date d=DateFormat.getDateInstance(DateFormat.SHORT,Locale.ITALIAN).parse(date);
				if(flexibleDate!=null && !flexibleDate.equals("") && flexibleDate.matches("[0-9]+") && Integer.parseInt(flexibleDate)<=60){
					q.setInteger("par5", Integer.parseInt(flexibleDate));
					q.setDate("d",d);
				}
				else
					q.setDate("par5", d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(music!=null){
				q.setBoolean("music", music);
			}
			if(pets!=null){
				q.setBoolean("pets", pets);
			}
			if(chatnessLevel!=null){
				q.setInteger("chatness", chatnessLevel);
			}
			if(smok!=null){
				q.setBoolean("smoking", smok);	
			}
			result=q.list();
			transaction.commit();
			if(result!=null){		
				for (LiftDetour l : result) {
					Hibernate.initialize(l.getLift());
					Hibernate.initialize(l.getLift().getUserOffering());
					Hibernate.initialize(l.getPickUpPoint());
					Hibernate.initialize(l.getDropOffPoint());
				}
			}
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	
}
