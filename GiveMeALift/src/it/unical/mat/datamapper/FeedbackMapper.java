package it.unical.mat.datamapper;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Feedback;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.service.ParseDate;
import it.unical.mat.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;

public class FeedbackMapper extends AbstractMapper{
	
	public List<Feedback> findReceivedFeedback(Long id){
		List<Feedback> l = new ArrayList<Feedback>();
		
		String findStatement = "from Feedback "
				+ "where "
				+ "receiver =:par1"
				;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", id);
		
		Collection<DomainObject> objects = find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			l.add((Feedback) object);
		}		

		return l;
	}
	
	
	public List<Feedback> findGivenFeedback(Long id){
		List<Feedback> l = new ArrayList<Feedback>();
		
		String findStatement = "from Feedback "
				+ "where "
				+ "sender =:par1"
				;
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", id);
		
		Collection<DomainObject> objects = find(findStatement, parameters,false);
		for (DomainObject object : objects) {
			l.add((Feedback) object);
		}		

		return l;
	}
	
	public Double computeAvgRating(Long id){	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {			
			String findStatement = "select AVG(rating) as AVERAGE from Feedback "
					+ "where "
					+ "receiver =:par1"
					;
			
			transaction = session.beginTransaction();
			Query query = session.createQuery(findStatement).setLong("par1",id);
		//	addScalar("AVERAGE", DoubleType.INSTANCE).
			Double avg = 0.0;
			avg = (Double) query.uniqueResult();
				
			transaction.commit();
			if(avg != null)
				return avg;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return 0.0;
	}
	
}
