package it.unical.mat.controller;

import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.Car;
import it.unical.mat.domain.DriverInfo;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;

import javax.servlet.http.HttpSession;

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
			
			RegisteredUser ru = (RegisteredUser)u;
			
			session.setAttribute("user", ru);
			return "showUserProfile";
		}
		
		else
			return "error";
		
//		if(u!=null){
//			String email = u.getEmail();
//			String psw = u.getPassword();
//		
//			if(email!="" && email!=null && psw!="" && psw!=null) {
//			 
//				RegisteredUserMapper rm=new RegisteredUserMapper();
//				RegisteredUser ru = rm.findUserByEmailAndPassword(email, psw);
//			 	
//				 if(ru!= null){
//			 		session.setAttribute("user", ru);
//			 		return "showUserProfile";	
//			 	}
//			 	else{
//			 		return "home";
//			 	}
//			 }
//			 else{
//			 		return "home";
//			 	}
//		}
//		else
//			return "home";
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
	
			RegisteredUser ru = (RegisteredUser) u;
			RegisteredUserMapper rm = new RegisteredUserMapper();
						
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
			rm.update(ru,ru.getId());
			

		//	session.setAttribute("user", rm.findRegisteredUserById(ru.getId()));
			
			// ha senso se si salva su DB, fare controllo(user sessione ,user DB)!!
			session.setAttribute("user", ru);

			return "showUserProfile";
		}
		else
			return "home";
	}
	 
	@RequestMapping(value = "/ShowUserCar")
	public String showUserCar(Model model, HttpSession session){
		User u = (User)session.getAttribute("user");
		
		if(u!=null){
		 
		RegisteredUser ru = (RegisteredUser) u;
		session.setAttribute("user", ru);
		 
		System.out.println("di:"+ru.getDriverInfo());
//		 System.out.println("car:"+ru.getDriverInfo().getCar());
		 
		
		if(	ru.getDriverInfo() == null||ru.getDriverInfo().getCar() == null	)
		 {			 
			 return "add";
		 } 
		else
			 return "showUserCar";	
		}
		
		else
			return "home";
		
	}
	
	
	@RequestMapping(value = "/AddCarDetails")
	public String addCarDetails(Model model, HttpSession session){
		User u = (User)session.getAttribute("user");
		
		if(u!=null){ 
			 return "addCarDetails";	
		}else
			return "home";
		
	}
	
	
	
	
	
	@RequestMapping(value = "/SubmitCar")
	public String submitCar(		
//			@RequestParam("brandAuto") String brandAuto,
//			@RequestParam("modelAuto") String modelAuto,
			@RequestParam("colorAuto") String colorAuto,
			@RequestParam("confortAuto") String confortAuto,
//			@RequestParam("photoCar") String photoCar,
			Model model, HttpSession session){
		
		User u = (User)session.getAttribute("user");
	
		if(u!=null){
			
			 RegisteredUser ru = (RegisteredUser) u;
			 Car car = new Car();
			 DriverInfo d = new DriverInfo();
//			 car.setBrand(brandAuto);
			 car.setColor(colorAuto);
			 
			Integer c = null;
			if(confortAuto == "base")
				c=1;
			else if(confortAuto == "normale")
				c=2;
			else if(confortAuto == "confortevole")
				c=3;
			else if(confortAuto == "lusso")
				c=4;
			 
			 car.setConfort(c);
//			 car.setModel(modelAuto);
//			 d.setCar_photo(photoCar);

			 
			 d.setCar(car);				 
			 
			 ru.setDriverInfo(d);
									
			return "submitCar";
		}
		else
			return "home";
	}
	
	
	@RequestMapping(value = "/ModifyUserCar" )
	public String modifyUserCar(
			@RequestParam("brandAuto") String brand,
			@RequestParam("modelAuto") String model,
			@RequestParam("colorAuto") String color,
			@RequestParam("confortAuto") String confort,
			@RequestParam("photoCar") String photoCar,
			Model m, HttpSession session) {
		
		User u = (User)session.getAttribute("user");
		if(u!=null){
			
			RegisteredUserMapper rm = new RegisteredUserMapper();
			RegisteredUser ru = (RegisteredUser) u;			
			
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

			return "showUserCar";
		}
		else
			return "home";

	}
		
}
