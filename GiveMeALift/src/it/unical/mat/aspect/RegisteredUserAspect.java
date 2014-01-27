package it.unical.mat.aspect;

import java.sql.Date;

import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.UserActivity;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RegisteredUserAspect {
	
	@Pointcut("execution(* it.unical.mat.controller.UserController.userSignUp(..,javax.servlet.http.HttpSession)) && args(..,session)")
	public void userRegistration(HttpSession session){}
	
	@Pointcut("execution(* it.unical.mat.controller.UserController.userLogIn(..,javax.servlet.http.HttpSession)) && args(..,session)")
	public void userLogIn(HttpSession session){}
	
	@After(value="userRegistration(session)")
	public void setUserRegistrationDate(HttpSession session){
		System.out.println("In setUser");
		RegisteredUser u=(RegisteredUser) session.getAttribute("user");
		if(u!=null){
			UserActivity userActivity=u.getUserActivity();
			if(userActivity==null)
				userActivity=new UserActivity();
			java.util.Date today=new java.util.Date();
			userActivity.setMemberSince(new Date(today.getTime()));
			RegisteredUserMapper rm=new RegisteredUserMapper();
			System.out.println(today);
			System.out.println(userActivity.getMemberSince());
			u.setUserActivity(userActivity);
			rm.update(u, u.getId());
			System.out.println(u.getUserActivity().getMemberSince());
		}
	}

	@After(value="userLogIn(session)")
	public void setUserLastOnlineDate(HttpSession session){
		
		System.out.println("In setUser");
		if(session.getAttribute("user") instanceof RegisteredUser){				
			RegisteredUser u=(RegisteredUser) session.getAttribute("user");
			if(u!=null){
				UserActivity userActivity=u.getUserActivity();
				if(userActivity==null)
					userActivity=new UserActivity();
				java.util.Date today=new java.util.Date();
				userActivity.setLastOnline(new Date(today.getTime()));
				RegisteredUserMapper rm=new RegisteredUserMapper();
				System.out.println(today);
				System.out.println(userActivity.getLastOnline());
				u.setUserActivity(userActivity);
//				rm.update(u, u.getId());
				System.out.println(u.getUserActivity().getLastOnline());
			}
		}
	}
	
}
