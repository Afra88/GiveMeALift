package it.unical.mat.datamapper;

import it.unical.mat.domain.DomainObject;
import it.unical.mat.domain.Lift;
import it.unical.mat.util.HibernateUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	
	public List<Lift> findLiftByFromAndTo(String cityFrom, String cityTo){
		List<Lift> result = new LinkedList<Lift>();
		String findStatement= "from Lift"
							+ " where "
							+ "(pickUpPoint.city like :par1 "
							+ "or pickUpPoint.street like :par1 "
							+ "or pickUpPoint.state like :par1 )"
							+ "and (dropOffPoint.city like :par2 "
							+ "or dropOffPoint.street like :par2 "
							+ "or dropOffPoint.state like :par2 )"
							+ "and cost>:par3";
		
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("par1", cityFrom);
		parameters.put("par2", cityTo);
		parameters.put("par3", 1);
		Collection<DomainObject> objects=find(findStatement, parameters);
		for (DomainObject object : objects) {
			result.add((Lift) object);
		}
		return result;
	}
}
