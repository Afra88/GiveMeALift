package it.unical.mat.test;

import static org.junit.Assert.assertTrue;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.DriverInfo;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.util.HibernateUtil;

import org.junit.BeforeClass;
import org.junit.Test;


public class DBTest {
	
	private static RegisteredUser user = new RegisteredUser();
	private static RegisteredUserMapper rm = new RegisteredUserMapper();
	
	@BeforeClass
	public static void prepareDB(){
			
		user.setName("John");
		user.setSurname("Bon Jovi");
		user.setEmail("user@test.it");
		user.setGender("M");
		user.setBirthYear(1975);
		user.setPhone("0984000001");
		Address a = new Address("Viale Mancini", "Cosenza", "Italia");
		user.setAddress(a);
		user.setCountAlert(1);
	//	user.setDriverInfo(new DriverInfo().se);
		long id = rm.insert(user);

		System.out.println(id);
		
		HibernateUtil.shutdown();
						
	}
//	
//	@Test
//	public void UsersBornIn(){
//		assertTrue(rm.getUserBornInYear(1975).size() >= 1);
//	}
//	
	@Test
	public void UsersOfGender(){
//		assertTrue(rm.getMaleUsers("M").size() >= 0);
		assertTrue(rm.getMaleUsers("F").size() >= 0);
	}
}
