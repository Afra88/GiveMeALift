package it.unical.mat.datamapper;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.mat.domain.Conversation;
import it.unical.mat.domain.DomainObject;
import it.unical.mat.util.HibernateUtil;

public class MessageMapper extends AbstractMapper {
	
//	public int findCountNotReadMessagesOfConversation(long id) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction = null;
//		try {			
//			String statement="select count(m.message_id) as j "
//					+ "from Conversation c "
//					+ "left join c.messages as m "
//					+ "where c.conversation_id=:par1 "
//					+ "and isRead=false ";
//			
//			transaction = session.beginTransaction();
//			Query query = session.createSQLQuery(statement).addScalar("j", IntegerType.INSTANCE);
//			query.setLong("par1", id);
//			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			Integer count=(Integer) query.uniqueResult();
//			transaction.commit();
//			return count;
//		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
//			e.printStackTrace();
//			transaction.rollback();
//		} finally {
//			session.close();
//		}
//		return 0;
//	}
	  
	//TODO i messaggi di una conversazione sono in ordine di data?
	
	@SuppressWarnings("unchecked")
	public List<Conversation> findUserConversations(long id) {
		List<Conversation> conversations=new LinkedList<Conversation>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {	
			String statement="select c from Conversation as c "
					+ "left join c.messages as m where (m.receiver.id=:par1 or m.sender.id=:par1) and isArchiviated=false ORDER BY m.dateSending,m.timeSending DESC";
			transaction = session.beginTransaction();
			Query query = session.createQuery(statement);
			query.setLong("par1", id);
			List<DomainObject> l=query.list();
			for (DomainObject domainObject : l) {
				conversations.add((Conversation) domainObject);
			}
			for (Conversation conversation : conversations) {				
				Hibernate.initialize(conversation.getMessages());
			}
			transaction.commit();
			return conversations;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public Conversation findConversationById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {	
			String statement="select c from Conversation as c where c.id=:par1";
			transaction = session.beginTransaction();
			Query query = session.createQuery(statement);
			query.setLong("par1", id);
			@SuppressWarnings("unchecked")
			List<DomainObject> l=query.list();
			if(l.size()>1)
				return null;
			Conversation c=(Conversation) l.get(0);
			Hibernate.initialize(c.getMessages());
			transaction.commit();
			return c;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public List<Conversation> findUserConversationsArchiviated(long id) {
		List<Conversation> conversations=new LinkedList<Conversation>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {	
			String statement="select c from Conversation as c "
					+ "left join c.messages as m where (m.receiver.id=:par1 or m.sender.id=:par1) and isArchiviated=true ORDER BY m.dateSending,m.timeSending DESC";
			transaction = session.beginTransaction();
			Query query = session.createQuery(statement);
			query.setLong("par1", id);
			@SuppressWarnings("unchecked")
			List<DomainObject> l=query.list();
			for (DomainObject domainObject : l) {
				conversations.add((Conversation) domainObject);
			}
			for (Conversation conversation : conversations) {				
				Hibernate.initialize(conversation.getMessages());
			}
			transaction.commit();
			return conversations;
		} catch (HibernateException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	
//	public List<Conversation> findConversationsSended(long id) {
//		List<Conversation> sended=new LinkedList<Conversation>();
//		String statement="from Conversation as c "
//				+ "left join c.messages as m where m.sender=:par1";
//		Map<String, Object> parameters=new HashMap<String, Object>();
//		parameters.put("par1", id);
//		Collection<DomainObject> res=find(statement, parameters, false);
//		for (DomainObject object : res) {
//			sended.add((Conversation) object);
//		}
//		return sended;
//	}
	
//	public List<Conversation> findConversationsArchiviated(long id) {
//		List<Conversation> sended=new LinkedList<Conversation>();
//		String statement="from Conversation as c "
//				+ "left join c.messages as m "
//				+ "where (m.receiver=:par1 or m.sender=:par1) "
//				+ " and isArchiviated=true ";
//		Map<String, Object> parameters=new HashMap<String, Object>();
//		parameters.put("par1", id);
//		Collection<DomainObject> res=find(statement, parameters, false);
//		for (DomainObject object : res) {
//			sended.add((Conversation) object);
//		}
//		return sended;
//	}
//	
//	public Message findLastMessageReceivedOfConversation(long id) {
//		String statement="from Conversation c "
//				+ "left join c.messages as m "
//				+ "where c.conversation_id=:par1 "
//				+ "and m.message_id=max(m.message_id) ";
//		Map<String, Object> parameters=new HashMap<String, Object>();
//		parameters.put("par1", id);
//		List<DomainObject> res=find(statement, parameters, false);
//		if(res.size()>0)
//			return (Message) res.get(0);
//		return null;
//			
//	}
	
	

}
