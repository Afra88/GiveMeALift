import it.unical.mat.dao.impl.UserDaoImpl;


public class Main {

	public static void main(String[] args) {
		UserDaoImpl user = new UserDaoImpl();
		String e = user.saveRegisteredUser(0, true, "gh", "jshdks", "A", "B", "F", 1900, "0984", "333", null);
		
		System.out.println(e);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
