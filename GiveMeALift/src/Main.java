import it.unical.mat.datamapper.LiftMapper;
import it.unical.mat.datamapper.LiftPointMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Lift;
import it.unical.mat.domain.LiftDetour;
import it.unical.mat.domain.LiftPoint;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;
import it.unical.mat.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		
		RegisteredUserMapper user=new RegisteredUserMapper();
		RegisteredUser u=new RegisteredUser();
		u.setEmail("kdsàc");
		u.setName("dsdsd");;
		Object e = user.insert(u);
		user.findAll();
		System.out.println(e.toString());
		for (User uu  : user.findAll()) {
			System.out.println(uu.getEmail());
		}
		
		Lift l=new Lift();
		LiftPoint lp=new LiftPoint();
		lp.setCity("Roma");
		LiftPoint lp2=new LiftPoint();
		lp2.setCity("Milano");
		LiftPointMapper lpm=new LiftPointMapper();
		long idLp=lpm.insert(lp);
		long idLp2=lpm.insert(lp2);
		l.setPickUpPoint(lp);
		l.setDropOffPoint(lp2);
		LiftMapper lm=new LiftMapper();
		LiftDetour ld=new LiftDetour();
		ld.setPickUpPoint(lp);
		ld.setDropOffPoint(lp2);
		lm.insert(l);
		System.out.println(idLp);
		System.out.println(idLp2);
		HibernateUtil.shutdown();
	}

}
