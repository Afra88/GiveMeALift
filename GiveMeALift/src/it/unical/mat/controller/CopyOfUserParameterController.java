package it.unical.mat.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.datamapper.CarMapper;
import it.unical.mat.datamapper.RegisteredUserMapper;
import it.unical.mat.domain.Address;
import it.unical.mat.domain.Car;
import it.unical.mat.domain.PersonalPreference;
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
public class CopyOfUserParameterController {
	
	
    @Autowired
    private ServletContext servletContext;
     
	
	@RequestMapping(value = "/ShowUserProfile")
	public String showUserProfile(Model model, HttpSession session) {
		User u = (User)session.getAttribute("user");
		
		if(u!=null){
			
			return "showUserProfile";
		}
		
		else
			return "error";
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
//			@RequestParam("chatness") String chatnessLevel,
//			@RequestParam("music") String musicOnBoard,
//			@RequestParam("smoking") String smokingOnBoard,
//			@RequestParam("pets") String petsOnBoard,
			Model model, HttpSession session) {
		
		
		User u = (User)session.getAttribute("user");
		
		if(u!=null){
			
			RegisteredUser ru = new RegisteredUser();
		
//			PersonalPreference pref = new PersonalPreference(); //ru.getPersonalPreference();
//					
//			int level = 0;
//			if(chatnessLevel.equals("1"))
//				level = 1;
//			else if(chatnessLevel.equals("2"))
//				level = 2;
//			else if(chatnessLevel.equals("3"))
//				level = 3;
//			
//			Boolean musicOn = false;
//			if(musicOnBoard.equals("noMus"))
//				musicOn = false;
//			else
//				musicOn = true;
//			Boolean smokingOn = false;
//			if(smokingOnBoard.equals("noSmok"))
//				smokingOn = false;
//			else
//				smokingOn = true;
//			Boolean petsOn = false;
//			if(petsOnBoard.equals("noPets"))
//				petsOn = false;
//			else
//				petsOn = true;
//			
//			pref.setChatnessLevel(level);
//			pref.setMusic(musicOn);
//			pref.setSmoking(smokingOn);
//			pref.setPetsOnBoard(petsOn);	
			
			Address a = new Address();
			a = new Address();
			a.setStreet(street);
			a.setCity(city);
			a.setState(state);	
			
			ru.setName(name);
			ru.setSurname(surname);
			ru.setPhone(phone);
			ru.setMobilePhone(mobilePhone);
			ru.setAddress(a);
//			ru.setPersonalPreference(pref);
			
			
			RegisteredUserMapper rm = new RegisteredUserMapper();
							
			boolean modified = rm.update(ru, u.getId());
			System.out.println("Userupdate: "+ modified);
			
			
			if(modified)
				model.addAttribute("modified", true);
			else
				model.addAttribute("modified", false);
	

			
			//--------------- FOTO ---------------
			
			String filename = u.getId()+".jpg";	
			
			uploadPhoto(uploadForm, u, filename); 
			
	        //--------------- FOTO ---------------
		     
			RegisteredUser ruNew= rm.findRegisteredUserById(u.getId());
		    session.setAttribute("user", ruNew);
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
			
			RegisteredUser ru = new RegisteredUser(); //(RegisteredUser) u;
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
			
			boolean modified = rm.update(ru, u.getId());
			
			
			System.out.println("In submitCar:"+
								"---update user per new car: "+ modified);
			
			//session.setAttribute("user", ru);
			
			
			//--------------- FOTO ---------------
			
			String filename = u.getId()+"_car.jpg";	
			
			uploadPhoto(uploadForm, u, filename);	       
			 
		    //--------------- FOTO ---------------
		         
			if(modified)
				model.addAttribute("modified", true);
			else
				model.addAttribute("modified", true);	//è false -- metto true solo per prova
									
		        
			return "submitCar";
		}
		else
			return "home";
	}

	/**
	 * @param uploadForm
	 * @param u
	 */
	private void uploadPhoto(FileUploadForm uploadForm, User u, String filename) {
		List<MultipartFile> files = uploadForm.getFiles();
		     
		    if(files != null && files.size() > 0) {

//	                String filename = multipartFile.getOriginalFilename();
		    	
		        	//String filename = u.getId()+"_car.jpg";	//lo passo come parametro	                	                		                
		      
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
			RegisteredUser user = (RegisteredUser) u;
			RegisteredUser ru = new RegisteredUser(); //(RegisteredUser) u;			
			
					
			Car car = user.getCar();
			Integer c = null;
			
			System.out.println("In MOdifyUserCar:");
			System.out.println(car);
			
			if(car!=null){
//			if(ru.getDriverInfo() == null){
				
				System.out.println("car!=null");

				if(confort.equals("base"))
					c=1;
				else if(confort.equals("normale"))
					c=2;
				else if(confort.equals("confortevole"))
					c=3;
				else if(confort.equals("lusso"))
					c=4;
				
				car.setConfort(c);
				car.setBrand(brand);
				car.setColor(color);
				car.setModel(model);

				//	car.setCarPhoto(photoCar);


//			}
			
			CarMapper carMapper=new CarMapper();
			boolean carUpdated = carMapper.update(car,user.getCar().getId());
			boolean modified = false;
					
			System.out.println("carUpdated---"+ carUpdated);
			
			if(carUpdated){
				m.addAttribute("carUpdated", true);
				ru.setCar(car);
				modified = rm.update(ru, u.getId());
			}
			else
				m.addAttribute("carUpdated", true);		//è false -- metto true solo per prova
			
//			boolean modified = rm.update(ru, u.getId());
			if(modified)
				m.addAttribute("modified", true);
			else
				m.addAttribute("modified", true);	//è false -- metto true solo per prova
			
			System.out.println("userUp:"+ modified);
			
			} // end if car	
			

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
			if(deleted)
				model.addAttribute("error",false);
			else
				model.addAttribute("error",true);
			
			return "showDeleteMsg";
			
		}
		
		return "home";
		
	}
}

