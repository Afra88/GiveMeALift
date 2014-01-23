package it.unical.mat.controller;

import it.unical.mat.datamapper.CarMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.Car;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
		 
//		 System.out.println("car:"+ru.getDriverInfo().getCar());
		 
		
		if(ru.getCar() == null	)
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
			@RequestParam("car-makes") String brandAuto,
			@RequestParam("car-models") String modelAuto,
			@RequestParam("colorAuto") String colorAuto,
			@RequestParam("confortAuto") String confortAuto,
			//@RequestParam("photoCar") String photoCar,
			Model model, HttpSession session){
		
		User u = (User)session.getAttribute("user");
	
		if(u!=null){
			
			RegisteredUser ru = (RegisteredUser) u;
			RegisteredUserMapper rm = new RegisteredUserMapper();
			 
			Car car = new Car();
			car.setBrand(brandAuto);
			car.setModel(modelAuto);
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
			
			//			 d.setCar_photo(photoCar);
			ru.setCar(car);				 
			 
			rm.update(ru, ru.getId());
			
			session.setAttribute("user", ru);
									
			return "submitCar";
		}
		else
			return "home";
	}
	
	
	@RequestMapping(value = "/ModifyUserCar" )
	public String modifyUserCar(
			@RequestParam("brandCar") String brand,
			@RequestParam("modelCar") String model,
			@RequestParam("colorCar") String color,
			@RequestParam("confortCar") String confort,
			//@RequestParam("photoCar") String photoCar,
			Model m, HttpSession session) {
		
		User u = (User)session.getAttribute("user");
		if(u!=null){
			
			RegisteredUserMapper rm = new RegisteredUserMapper();
			RegisteredUser ru = (RegisteredUser) u;			
			
			Car car = ru.getCar();
			Integer c = null;
			
			if(car != null){
				
			//	d.setCar_photo(photoCar);
				car.setColor(color);
				
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
				car.setComfort(c);	
				//CHECK this ATTRIBUTE

				if(car.getBrand() != brand)
					car.setBrand(brand);
				if(car.getColor() != color)
					car.setColor(color);
	
				if(confort.equals("base"))
					c=1;
				else if(confort.equals("normale"))
					c=2;
				else if(confort.equals("confortevole"))
					c=3;
				else if(confort.equals("lusso"))
					c=4;
						
				if(ru.getCar().getConfort() != c)
					car.setConfort(c);
				if(ru.getCar().getModel() != model)
					car.setModel(model);
//				if(ru.getDriverInfo().getCar_photo() != photoCar)
//				{
//					d.setCar_photo(photoCar);
//					ru.setDriverInfo(d);
//				}
				ru.setCar(car);
				CarMapper carMapper=new CarMapper();
				carMapper.update(car, ru.getCar().getId());
				rm.update(ru, ru.getId());
			}
//			session.setAttribute("user", ru);
			return "showUserCar";
			
		}
			
		else
			return "home";
	}
	
	@RequestMapping(value = "/DeleteUserCar" )
	public String deleteUserCar(Model model, HttpSession session) {
		
		User u = (User)session.getAttribute("user");
		if(u!=null){
			RegisteredUser ru = (RegisteredUser) u;
			Car car = ru.getCar();
			CarMapper dm = new CarMapper();
						
			boolean deleted = dm.deleteCar(car, ru);
			if(deleted){
				model.addAttribute("error",false);
				return "showDeleteMsg";	
			}
			else{
				model.addAttribute("error",false);
				return "showDeleteMsg";
			}
		}
		
		return "home";
		
	}
}
