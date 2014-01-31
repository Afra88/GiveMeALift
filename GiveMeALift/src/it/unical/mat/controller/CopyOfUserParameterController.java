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
		RegisteredUser u = (RegisteredUser)session.getAttribute("user");
		
		if(u!=null){
//			PersonalPreference pref = u.getPersonalPreference();
//			
//			System.out.println("__"+pref);
//			
//			if (pref == null) {
//				pref = new PersonalPreference();
//				pref.setChatnessLevel(1);
//				pref.setMusic(false);
//				pref.setSmoking(false);
//				pref.setPetsOnBoard(false);
//				
//
//				u.setPersonalPreference(pref);				
//				session.setAttribute("user", u);	
//			}
//			
//			
//			System.out.println(pref.getChatnessLevel());
//			System.out.println(pref.getMusic());
//			System.out.println(pref.getPetsOnBoard());
//			System.out.println(pref.getSmoking());
			
			
			
			
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
			@RequestParam(value="chatness", required=false) String chatnessLevel,
			@RequestParam(value="music", required=false) String musicOnBoard,
			@RequestParam(value="smoking", required=false) String smokingOnBoard,
			@RequestParam(value="pets", required=false) String petsOnBoard,
			@RequestParam("note") String description,
			Model model, HttpSession session) {
		
		
		RegisteredUser u = (RegisteredUser)session.getAttribute("user");
		
		if(u!=null){
			
			RegisteredUser ru = new RegisteredUser();
			
			RegisteredUser user= u;
			RegisteredUserMapper rm = new RegisteredUserMapper();
			
			//PersonalPreference pref = rm.loadPersonalPreference(user.getId());//user.getPersonalPreference(); //new PersonalPreference(); //ru.getPersonalPreference();
			
			PersonalPreference pref = new PersonalPreference();

		System.out.println("lev"+pref.getChatnessLevel());
		System.out.println("lev"+chatnessLevel);
			
			if(chatnessLevel.equals("1"))
				pref.setChatnessLevel(1);
			else if(chatnessLevel.equals("2"))
				pref.setChatnessLevel(2);
			else if(chatnessLevel.equals("3"))
				pref.setChatnessLevel(3);
			
			
			if(musicOnBoard.equals("noMus"))
				pref.setMusic(false);
			else
				pref.setMusic(true);
			
			if(smokingOnBoard.equals("noSmok"))
				pref.setSmoking(false);
			else
				pref.setSmoking(true);
			
			if(petsOnBoard.equals("noPets"))
				pref.setPetsOnBoard(false);
			else
				pref.setPetsOnBoard(true);
			
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
			ru.setPersonalPreference(pref);
			
			System.out.println("lev"+pref.getChatnessLevel());
			
			System.out.println("descr"+ description);
			
			
			if(ru.getDescription()== null || ( !description.equals("Es: \"Studio a Milano, ma sono originario di Bologna e viaggio spesso per"
					+ "		andare a trovare la mia famiglia.\"")))
				ru.setDescription(description); 
			
			//--------------- FOTO ---------------
			
			String filename = u.getId()+".jpg";	
			
			uploadPhoto(uploadForm, u, filename); 
			
	        //--------------- FOTO ---------------
			
			ru.setProfilePhoto(filename);
			
									
			boolean modified = rm.update(ru, u.getId());
			System.out.println("Userupdate: "+ modified);
			
			System.out.println("_ModifyUserProfile_"+pref);
//			System.out.println(pref.getChatnessLevel());
//			System.out.println(pref.getMusic());
//			System.out.println(pref.getPetsOnBoard());
//			System.out.println(pref.getSmoking());
//			
			
			if(modified){
				model.addAttribute("modified", true);
				RegisteredUser ruNew= rm.findRegisteredUserById(u.getId());
				
//				PersonalPreference p1 = ruNew.getPersonalPreference();
//
//				System.out.println("_ModifyUserProfile_runew_"+p1);
//				System.out.println(p1.getChatnessLevel());
//				System.out.println(p1.getMusic());
//				System.out.println(p1.getPetsOnBoard());
//				System.out.println(p1.getSmoking());
				
				session.setAttribute("user", ruNew);
			}
			else
				model.addAttribute("modified", false);
	

			return "showUserProfile";
//			return "showMsgError";
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
		
			
			//--------------- FOTO ---------------
			
			String filename = u.getId()+"_car.jpg";	
			
			uploadPhoto(uploadForm, u, filename);	       
			 
		    //--------------- FOTO ---------------
		         
			car.setCarPhoto(filename);			
			ru.setCar(car);

			boolean modified = rm.update(ru, u.getId());
			
			
			System.out.println("In submitCar:"+
								"---update user per new car: "+ modified);
			

			if(modified){
				model.addAttribute("modified", true);
				RegisteredUser ruNew= rm.findRegisteredUserById(u.getId());
			    session.setAttribute("user", ruNew);
			}
			else
				model.addAttribute("modified", false);	//� false -- metto true solo per prova
		
		        
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
	
	
	@RequestMapping(value = "/ModifyUserCar", method = RequestMethod.POST) 	
	public String modifyUserCar(
			@ModelAttribute("uploadForm") FileUploadForm uploadForm,
			@RequestParam("brandCar") String brand, 
			@RequestParam("modelCar") String model, 
			@RequestParam("colorCar") String color,
			@RequestParam("confortCar") String confort,
			//@RequestParam("photoCar") String photoCar,
			Model m, HttpSession session) {
		
		User u = (User)session.getAttribute("user");
		if(u!=null){
			
			RegisteredUserMapper rm = new RegisteredUserMapper();
			RegisteredUser ru = new RegisteredUser(); 	
			
					
//			Car car = user.getCar();
			Car car = new Car();
			Integer c = null;
			
			System.out.println("In MOdifyUserCar:");
			System.out.println(car);
			
//			if(car!=null){
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

				

				//--------------- FOTO ---------------
				
				String filename = u.getId()+"_car.jpg";					
				uploadPhoto(uploadForm, u, filename); 
				
		        //--------------- FOTO ---------------
				
				car.setCarPhoto(filename);


//			}
			
//			CarMapper carMapper=new CarMapper();
//			boolean carUpdated = carMapper.update(car,user.getCar().getId());
			boolean modified = false;
					
//			System.out.println("carUpdated---"+ carUpdated);
			
//			if(carUpdated){
//				m.addAttribute("carUpdated", true);
				ru.setCar(car);
				modified = rm.update(ru, u.getId());
//			}
//			else
//				m.addAttribute("carUpdated", true);		//� false -- metto true solo per prova
			
//			boolean modified = rm.update(ru, u.getId());
			if(modified){
				m.addAttribute("modified", true);
				RegisteredUser ruNew= rm.findRegisteredUserById(u.getId());
			    session.setAttribute("user", ruNew);
			}
			else
				m.addAttribute("modified", false);	//� false -- metto true solo per prova
			
			System.out.println("userUp:"+ modified);
			
//			} // end if car	
		
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
			
			//--------------- FOTO ---------------
			String filename = ru.getId()+"_car.jpg";					
			String fullPath = servletContext.getRealPath("/WEB-INF/resources/avatars/"+filename);			
			File f = new File(fullPath);
			
			if(f.exists())
				f.delete();
			//--------------- FOTO ---------------
			
			boolean deleted = cm.deleteCar(car, ru);
			if(deleted)
				model.addAttribute("error",false);
			else
				model.addAttribute("error",true);
			
			return "showDeleteMsg";
			
		}
		
		return "home";
		
	}
	
	
	@RequestMapping(value = "/DeleteRegUser" )
	public String deleteRegisteredUser(Model model, HttpSession session) {
		
		User u = (User)session.getAttribute("user");
		if(u!=null){
			RegisteredUser ru = (RegisteredUser) u;
			RegisteredUserMapper rm = new RegisteredUserMapper();
			
			//--------------- FOTO ---------------
			String filename = ru.getId()+"_car.jpg";					
			String fullPath = servletContext.getRealPath("/WEB-INF/resources/avatars/"+filename);			
			File f = new File(fullPath);
			
			if(f.exists())
				f.delete();
			//--------------- FOTO ---------------
			
			boolean deleted = rm.deleteUser(ru);
			if(deleted)
				model.addAttribute("error",false);
			else
				model.addAttribute("error",true);
			
			return "showDeleteMsg";
			
		}
		
		return "home";
		
	}
	
	
}
