package it.unical.mat.controller;

import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.Car;
import it.unical.mat.domain.DriverInfo;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;

import javax.servlet.http.HttpSession;

import org.apache.catalina.ant.FindLeaksTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserParameterController {
	
	@RequestMapping(value = "/ShowUserProfile")
	public String showUserProfile(Model model, HttpSession session) {
		User u = (User)session.getAttribute("user");
		if(u!=null){
			String email = u.getEmail();
			String psw = u.getPassword();
		
			if(email!="" && email!=null && psw!="" && psw!=null) {
			 
				RegisteredUserMapper rm=new RegisteredUserMapper();
				RegisteredUser ru = rm.findUserByEmailAndPassword(email, psw);
			 	
				 if(ru!= null){
			 		session.setAttribute("user", ru);
			 		
			 		char firstLetter = u.getSurname().charAt(0);
			 		model.addAttribute("firstLetter", firstLetter);
			 		
			 		return "showUserProfile";	
			 	}
			 	else{
			 		return "home";
			 	}
			 }
			 else{
			 		return "home";
			 	}
		}
		else
			return "home";
	}
 
	@RequestMapping(value = "/ModifyUserProfile" )
	public String modifyUserProfile(
			@RequestParam("profileName") String name,
			@RequestParam("profileSurname") String surname,
			@RequestParam("profileCell") String mobilePhone,
			@RequestParam("profilePhone") String phone,
			@RequestParam("address") String street,
			@RequestParam("cityAddress") String city,
			@RequestParam("stateAddress") String state,
			Model model, HttpSession session) {
		
		
		User u = (User)session.getAttribute("user");
		if(u!=null){
	//		System.out.println(u.getName());
	//		System.out.println(u.getSurname());
	//		System.out.println(u.getEmail());
	//		System.out.println(u.getPassword());
	//		System.out.println(u.getGender());
	//		System.out.println(u.getPhone());
	//		System.out.println(u.getMobilePhone());
	//		System.out.println(u.getAddress());
	//		System.out.println(u.getAddress().getCity() + "," + u.getAddress().getCity());
			
			String email = u.getEmail();	
			String psw = u.getPassword();	
			//CONTROLLO U == NULL
			if(email!=null && email!="" && psw!=null && psw!="")
			{
				RegisteredUser ru = new RegisteredUser();			
				RegisteredUserMapper rm = new RegisteredUserMapper();
				ru = rm.findUserByEmailAndPassword(email, psw);
				
				System.out.println(ru.getId());
				
				if(ru.getName()!= name)
					ru.setName(name);			
				if(ru.getSurname()!=surname)
					ru.setSurname(surname);
				if(ru.getMobilePhone()!=mobilePhone)
					ru.setMobilePhone(mobilePhone);
				if(ru.getPhone()!=phone)
					ru.setPhone(phone);
				
				Address a = new Address();
				if(ru.getAddress()==null){
						a.setStreet(street);
						a.setCity(city);
						a.setState(state);				
				}
				else{
					if(ru.getAddress().getStreet()!= street)
						a.setStreet(street);
					if(ru.getAddress().getCity()!=city)
						a.setCity(city);
					if(ru.getAddress().getState()!=state)
						a.setState(state);				
				}
				
				ru.setAddress(a);
				
				rm.update(ru);
	
				return "showUserProfile";
			}
			else
				return "home";
		}
		else
			return "home";
	}
	 
	@RequestMapping(value = "/ShowUserCar")
	public String showUserCar(Model model, HttpSession session){
		User u = (User)session.getAttribute("user");
		
		if(u!=null){
			String email = u.getEmail();
			String psw = u.getPassword();
		
			if(email!="" && email!=null && psw!="" && psw!=null) {
				
				RegisteredUserMapper rm=new RegisteredUserMapper();
				RegisteredUser ru = rm.findUserByEmailAndPassword(email, psw);
		 	
				if(ru!= null){
				 	session.setAttribute("user", ru);
				 	return "showUserCar";	
				}
				else
					return "home";
			}
			else
				return "home";
		}
		else
			return "home";
	}
	
	
/*	@RequestMapping(value = "/ModifyUserCar" )
	public String modifyUserCar(
			@RequestParam("brandAuto") String brand,
			@RequestParam("modelAuto") String model,
			@RequestParam("colorAuto") String color,
			@RequestParam("confortAuto") String confort,
			@RequestParam("photoCar") String photoCar,
			Model m, HttpSession session) {
		
		User u = (User)session.getAttribute("user");
		if(u!=null){
			String email = u.getEmail();
			String psw = u.getPassword();
		
		if(email!=null && email!="" && psw!=null && psw!="")
		{
			RegisteredUserMapper rm = new RegisteredUserMapper();
			RegisteredUser ru = rm.findUserByEmailAndPassword(email, psw);
			
			Car car = new Car();
			DriverInfo d = new DriverInfo();
			
			if(ru.getDriverInfo() == null){
				
				d.setCar_photo(photoCar);
				d.setColor(color);
				Integer c = null;
				if(confort == "base")
					c=1;
				else if(confort == "normale")
					c=2;
				else if(confort == "confortevole")
					c=3;
				else if(confort == "lusso")
					c=4;
				
				car.setBrand(brand);
				car.setColor(color);
				car.setConfort(c);
				car.setModel(model);
				d.setCar(car);
				d.setComfort(c);	//CHECK this ATTRIBUTE
			
			}else{
				
				if(ru.getDriverInfo().getCar().getBrand() != brand)
				{	car.setBrand(brand);
					
				}
				
		}
			
//			if(u.getName()!= name)
//				u.setName(name);			
//			if(u.getSurname()!=surname)
//				u.setSurname(surname);
//			if(u.getMobilePhone()!=mobilePhone)
//				u.setMobilePhone(mobilePhone);
//			if(u.getPhone()!=phone)
//				u.setPhone(phone);
//			
//			Address a = new Address();
//			if(u.getAddress().getStreet()!= street)
//				a.setStreet(street);
//			if(u.getAddress().getCity()!=city)
//				a.setCity(city);
//			if(u.getAddress().getState()!=state)
//				a.setState(state);
//			
//			u.setAddress(a);
//			
//			rm.update(u);

			return "showUserProfile";
		}
		else
			return "home";
	}
	
	}
		*/
}
