package it.unical.mat.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.datamapper.CarMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.Car;
import it.unical.mat.domain.RegisteredUser;
import it.unical.mat.domain.User;
import it.unical.mat.service.FileUploadForm;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserParameterController {
	
	
    @Autowired
    private ServletContext servletContext;
     
	
	@RequestMapping(value = "/ShowUserProfile")
	public String showUserProfile(Model model, HttpSession session) {
		User u = (User)session.getAttribute("user");
		
		if(u!=null){
			
			//RegisteredUser ru = (RegisteredUser)u;
			//session.setAttribute("user", ru);
			
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
 
	@RequestMapping(value = "/ModifyUserProfile", method = RequestMethod.POST)
	public String modifyUserProfile(
			@ModelAttribute("uploadForm") FileUploadForm uploadForm,
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
			
			
			System.out.println("cell:" + mobilePhone);
			System.out.println("tel:" + phone);
	
			RegisteredUser ru = (RegisteredUser) u;
			RegisteredUserMapper rm = new RegisteredUserMapper();
						
			if(!ru.getName().equals(name))
				ru.setName(name);			
			if(!ru.getSurname().equals(surname))
				ru.setSurname(surname);
			if(ru.getMobilePhone()== null || !ru.getMobilePhone().equals(mobilePhone))
				ru.setMobilePhone(mobilePhone);		
			if(ru.getPhone()==null || !ru.getPhone().equals(phone))
				ru.setPhone(phone);

		
			Address a = new Address();
			if(ru.getAddress()==null){
					a.setStreet(street);
					a.setCity(city);
					a.setState(state);				
			}
			else{
				if(ru.getAddress().getStreet()== null || !ru.getAddress().getStreet().equals(street))
					a.setStreet(street);
				if(ru.getAddress().getCity()== null || !ru.getAddress().getCity().equals(city))
					a.setCity(city);
				if(ru.getAddress().getState()== null || !ru.getAddress().getState().equals(state))
					a.setState(state);				
			}
			
			ru.setAddress(a);
			//FIXME org.hibernate.NonUniqueObjectException: a different object with the same identifier value was already associated with the session: [it.unical.mat.domain.Car#1]
			// non fa fare l'update di user profile
			
			rm.update(ru,ru.getId());

			
			//--------------- FOTO ---------------
			
			 List<MultipartFile> files = uploadForm.getFiles();
		         
		        if(files != null && files.size() > 0) {

//	                String filename = multipartFile.getOriginalFilename();
		        	
		            	String filename = u.getId()+".jpg";		                	                		                
		          
		    			String fullPath = servletContext.getRealPath("/WEB-INF/resources/avatars/"+filename);
		    			    			
		    			File f = new File(fullPath);
		        			
		    			System.out.println("/resources/avatars/"+filename);
		    			System.out.println(f.exists());
		    			System.out.println(f.getAbsolutePath());
		    			System.out.println(f.getPath());

			    	try {
			    			if (files.get(0).getBytes().length >0) {
											    			
			    				if(f.exists())      
			    					f.delete();					    			
			    				
								files.get(0).transferTo(f); // prendo solo il primo della lista
								
			    			}
						} catch (IllegalStateException | IOException e) {e.printStackTrace();} 
		        }	        
		        //--------------- FOTO ---------------
		         
			
			
			
			

		//	session.setAttribute("user", rm.findRegisteredUserById(ru.getId()));
			
			// ha senso se si salva su DB, fare controllo(user sessione ,user DB)!!
	//		session.setAttribute("user", ru);

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
		//session.setAttribute("user", ru);
		 

		 
		
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
	
	
	
	
	
	@RequestMapping(value = "/SubmitCar", method = RequestMethod.POST)
	public String submitCar(	
			@RequestParam("car-years") String year,
			@RequestParam("car-makes") String brandAuto,
			@RequestParam("car-models") String modelAuto,
			@RequestParam("colorAuto") String colorAuto,
			@RequestParam("confortAuto") String confortAuto,
            @ModelAttribute("uploadForm") FileUploadForm uploadForm,
//			@RequestParam("photoCar") String photoCar,
			Model model, HttpSession session){
	
	model.addAttribute("year", year); // <-- da aggiungere in Car
		
		User u = (User)session.getAttribute("user");
	
		if(u!=null){
			
			RegisteredUser ru = (RegisteredUser) u;
			RegisteredUserMapper rm = new RegisteredUserMapper();
			 
			Car car = new Car();
			car.setBrand(brandAuto);
			car.setModel(modelAuto);
			car.setColor(colorAuto);
			 
			Integer c = null;
			if(confortAuto.equals("base"))
				c=1;
			else if(confortAuto.equals("normale"))
				c=2;
			else if(confortAuto.equals("confortevole"))
				c=3;
			else if(confortAuto.equals("lusso"))
				c=4;
			 
			car.setConfort(c);
//			car.setCarPhoto(photoCar);
			
			
							 
			 
			ru.setCar(car);
			rm.update(ru, ru.getId());
			
			//session.setAttribute("user", ru);
			
			
			//--------------- FOTO ---------------
			
			 List<MultipartFile> files = uploadForm.getFiles();
		         
		        if(files != null && files.size() > 0) {

//	                String filename = multipartFile.getOriginalFilename();
		        	
		            	String filename = u.getId()+"_car.jpg";		                	                		                
		          
		    			String fullPath = servletContext.getRealPath("/WEB-INF/resources/avatars/"+filename);
		    			    			
		    			File f = new File(fullPath);
		        			
		    			System.out.println("/resources/avatars/"+filename);
		    			System.out.println(f.exists());
		    			System.out.println(f.getAbsolutePath());
		    			System.out.println(f.getPath());

		    			try {
			    			if (files.get(0).getBytes().length >0) {
											    			
			    				if(f.exists())      
			    					f.delete();					    			
			    				
								files.get(0).transferTo(f); // prendo solo il primo della lista
								
			    			}
						} catch (IllegalStateException | IOException e) {e.printStackTrace();} 
		        }	        
		        //--------------- FOTO ---------------
		         
			
									
		        
			return "submitCar";
		}
		else
			return "home";
	}
	
	
	@RequestMapping(value = "/ModifyUserCar" )
	public String modifyUserCar(
			@RequestParam("brandCar") String brand, //miss
			@RequestParam("modelCar") String model, //miss
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
			
			
			if(car!=null){
//			if(ru.getDriverInfo() == null){
				
			//	d.setCar_photo(photoCar);
				car.setColor(color);
			//	car.setCarPhoto(photoCar);
				
				if(confort.equals("base"))
					c=1;
				else if(confort.equals("normale"))
					c=2;
				else if(confort.equals("confortevole"))
					c=3;
				else if(confort.equals("lusso"))
					c=4;
				
				car.setBrand(brand);
				car.setColor(color);
				car.setConfort(c);
				car.setModel(model);
				car.setComfort(c);	
			
//			}else{
				
				if(!car.getBrand().equals(brand))
					car.setBrand(brand);
				if(!car.getColor().equals(color))
					car.setColor(color);
	
				if(confort.equals("base"))
					c=1;
				else if(confort.equals("normale"))
					c=2;
				else if(confort.equals("confortevole"))
					c=3;
				else if(confort.equals("lusso"))
					c=4;
						
				if(!ru.getCar().getConfort().equals(c))
					car.setConfort(c);
				if(!ru.getCar().getModel().equals(model))
					car.setModel(model);
//				if(!ru.getCar().getCarPhoto().equals(photoCar))
//					car.setCarPhoto(photoCar);

//			}
			ru.setCar(car);
			CarMapper carMapper=new CarMapper();
			carMapper.update(car,ru.getCar().getId());
			rm.update(ru, ru.getId());
			} // end if 	
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
			CarMapper cm = new CarMapper();
						
			boolean deleted = cm.deleteCar(car, ru);
			if(deleted){
				model.addAttribute("error",false);
				return "showDeleteMsg";	
			}
			else{
				model.addAttribute("error",true);
				return "showDeleteMsg";
			}
		}
		
		return "home";
		
	}
}

