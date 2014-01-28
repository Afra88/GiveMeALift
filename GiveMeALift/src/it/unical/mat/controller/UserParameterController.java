//package it.unical.mat.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import it.unical.mat.datamapper.CarMapper;
//import it.unical.mat.datamapper.RegisteredUserMapper;
//import it.unical.mat.domain.Address;
//import it.unical.mat.domain.Car;
//import it.unical.mat.domain.PersonalPreference;
//import it.unical.mat.domain.RegisteredUser;
//import it.unical.mat.domain.User;
//import it.unical.mat.service.FileUploadForm;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//public class UserParameterController {
//	
//	
//    @Autowired
//    private ServletContext servletContext;
//     
//	
//	@RequestMapping(value = "/ShowUserProfile")
//	public String showUserProfile(Model model, HttpSession session) {
//		User u = (User)session.getAttribute("user");
//		
//		if(u!=null){
//			
//			RegisteredUser ru = (RegisteredUser)u;
//			RegisteredUserMapper rm = new RegisteredUserMapper();
//			//session.setAttribute("user", ru);
//			
//			
//			PersonalPreference pref = ru.getPersonalPreference(); 
//			if(pref == null){
//				pref = new PersonalPreference();
//				pref.setChatnessLevel(1);
//				pref.setMusic(false);
//				pref.setSmoking(false);
//				pref.setPetsOnBoard(false);	
//				
//				ru.setPersonalPreference(pref);
//				rm.update(ru, ru.getId());
//				
//				System.out.println("in ShowUserProfile update:"+rm.update(ru, ru.getId()));
//				
//			}
//			
//			
//			return "showUserProfile";
//		}
//		
//		else
//			return "error";
//		
////		if(u!=null){
////			String email = u.getEmail();
////			String psw = u.getPassword();
////		
////			if(email!="" && email!=null && psw!="" && psw!=null) {
////			 
////				RegisteredUserMapper rm=new RegisteredUserMapper();
////				RegisteredUser ru = rm.findUserByEmailAndPassword(email, psw);
////			 	
////				 if(ru!= null){
////			 		session.setAttribute("user", ru);
////			 		return "showUserProfile";	
////			 	}
////			 	else{
////			 		return "home";
////			 	}
////			 }
////			 else{
////			 		return "home";
////			 	}
////		}
////		else
////			return "home";
//	}
// 
//	@RequestMapping(value = "/ModifyUserProfile", method = RequestMethod.POST)
//	public String modifyUserProfile(
//			@ModelAttribute("uploadForm") FileUploadForm uploadForm,
//			@RequestParam("profileName") String name,
//			@RequestParam("profileSurname") String surname,
//			@RequestParam("profileCell") String mobilePhone,
//			@RequestParam("profilePhone") String phone,
//			@RequestParam("address") String street,
//			@RequestParam("cityAddress") String city,
//			@RequestParam("stateAddress") String state,
//			@RequestParam("chatness") String chatnessLevel,
//			@RequestParam("music") String musicOnBoard,
//			@RequestParam("smoking") String smokingOnBoard,
//			@RequestParam("pets") String petsOnBoard,
//			Model model, HttpSession session) {
//		
//		
//		User u = (User)session.getAttribute("user");
//		
//		if(u!=null){
//			
//			System.out.println("In modifyUserProfile:");
//			System.out.println("cell:" + mobilePhone);
//			System.out.println("tel:" + phone);
//			
//	
//			RegisteredUser ru = (RegisteredUser) u;
//			RegisteredUserMapper rm = new RegisteredUserMapper();
//			
//			System.out.println("nomePrima:"+ru.getName());
//			System.out.println("CognomePrima:"+ru.getSurname());
//			
//					
//			if(!ru.getName().equals(name))
//				ru.setName(name);			
//
//			if(!ru.getSurname().equals(surname))
//				ru.setSurname(surname);
//
//			if(ru.getMobilePhone()== null || !ru.getMobilePhone().equals(mobilePhone))
//				ru.setMobilePhone(mobilePhone);		
//
//			if(ru.getPhone()==null || !ru.getPhone().equals(phone))
//				ru.setPhone(phone);
//		
//			Address a = ru.getAddress(); //new Address();
//			if(a==null){
//					a = new Address();
//					a.setStreet(street);
//					a.setCity(city);
//					a.setState(state);				
//			}
//			else{
//				if(a.getStreet()== null || !a.getStreet().equals(street))
//					a.setStreet(street);
//
//				if(a.getCity()== null || !a.getCity().equals(city))
//					a.setCity(city);
//
//				if(a.getState()== null || !a.getState().equals(state))
//					a.setState(state);			
//			}
//			
//			ru.setAddress(a);
//			
//			PersonalPreference pref = ru.getPersonalPreference(); 
////			if(pref == null){
////				pref = new PersonalPreference();
////				pref.setChatnessLevel(1);
////				pref.setMusic(false);
////				pref.setSmoking(false);
////				pref.setPetsOnBoard(false);	
////			}
////			else{
//			
////			System.out.println("-------lev-"+chatnessLevel);
////			System.out.println("-------music-"+ musicOnBoard);
////			System.out.println("-------smok-"+smokingOnBoard);
////			System.out.println("-------pets-"+petsOnBoard);
//				
//				int level = 0;
//				if(chatnessLevel.equals("1"))
//					level = 1;
//				else if(chatnessLevel.equals("2"))
//					level = 2;
//				else if(chatnessLevel.equals("3"))
//					level = 3;
//		
//				if(pref.getChatnessLevel()==0 || pref.getChatnessLevel()!=level)
//					pref.setChatnessLevel(level);
//				
//				Boolean musicOn = false;
//				if(musicOnBoard.equals("noMus"))
//					musicOn = false;
//				else
//					musicOn = true;
//				if(!pref.getMusic().equals(musicOnBoard))
//					pref.setMusic(musicOn);
//				
//				Boolean smokingOn = false;
//				if(smokingOnBoard.equals("noSmok"))
//					smokingOn = false;
//				else
//					smokingOn = true;
//				if(!pref.getSmoking().equals(smokingOnBoard))
//					pref.setSmoking(smokingOn);
//				
//				Boolean petsOn = false;
//				if(petsOnBoard.equals("noPets"))
//					petsOn = false;
//				else
//					petsOn = true;
//				if(!pref.getPetsOnBoard().equals(petsOnBoard))
//					pref.setPetsOnBoard(petsOn);	
////			}
//			ru.setPersonalPreference(pref);
//			
//			//FIXME org.hibernate.NonUniqueObjectException: a different object with the same identifier value was already associated with the session: [it.unical.mat.domain.Car#1]
//			// non fa fare l'update di user profile
//			
//			
////			System.out.println("\n\nafter----lev-"+ru.getPersonalPreference().getChatnessLevel());
////			System.out.println("after---music-"+ ru.getPersonalPreference().getMusic());
////			System.out.println("after---smok-"+ru.getPersonalPreference().getSmoking());
////			System.out.println("after---pets-"+ru.getPersonalPreference().getPetsOnBoard());
//			
//			boolean modified = rm.update(ru,ru.getId());
//			System.out.println("Userupdate: "+ modified);
//			
//			System.out.println("name:"+ru.getName());
//			
//			ru.setName("Ciccio");
//			ru.setSurname("Pasticcio");
//			
//			rm.update(ru,ru.getId());
//			
//			System.out.println("dopo:" +ru.getName());
//			System.out.println("dopo:" +ru.getSurname());
//			
//			
//			if(modified)
//				model.addAttribute("modified", true);
//			else
//				model.addAttribute("modified", false);
//	
//
//			
//			//--------------- FOTO ---------------
//			
//			 List<MultipartFile> files = uploadForm.getFiles();
//		         
//		        if(files != null && files.size() > 0) {
//
////	                String filename = multipartFile.getOriginalFilename();
//		        	
//		            	String filename = u.getId()+".jpg";		                	                		                
//		          
//		    			String fullPath = servletContext.getRealPath("/WEB-INF/resources/avatars/"+filename);
//		    			    			
//		    			File f = new File(fullPath);
//		        			
//		    			System.out.println("/resources/avatars/"+filename);
//		    			System.out.println(f.exists());
//		    			System.out.println(f.getAbsolutePath());
//		    			System.out.println(f.getPath());
//
//			    	try {
//			    			if (files.get(0).getBytes().length >0) {
//											    			
//			    				if(f.exists())      
//			    					f.delete();					    			
//			    				
//								files.get(0).transferTo(f); // prendo solo il primo della lista
//								
//			    			}
//						} catch (IllegalStateException | IOException e) {e.printStackTrace();} 
//		        }	        
//		        //--------------- FOTO ---------------
//		         
//			
//		//	session.setAttribute("user", rm.findRegisteredUserById(ru.getId()));
//			
//			// ha senso se si salva su DB, fare controllo(user sessione ,user DB)!!
//	//		session.setAttribute("user", ru);
//		        
//		    
//			return "showUserProfile";
//		}
//		else
//			return "home";
//	}
//	 
//	
//	
//	@RequestMapping(value = "/ShowUserCar")
//	public String showUserCar(Model model, HttpSession session){
//		User u = (User)session.getAttribute("user");
//		
//		if(u!=null){
//		 
//			RegisteredUser ru = (RegisteredUser) u;
//			//session.setAttribute("user", ru);
//			
//			if(ru.getCar() == null	)
//			 {			 
//				 return "add";
//			 } 
//			else
//				 return "showUserCar";	
//		}
//		
//		else
//			return "home";
//		
//	}
//	
//	
//	@RequestMapping(value = "/AddCarDetails")
//	public String addCarDetails(Model model, HttpSession session){
//		User u = (User)session.getAttribute("user");
//		
//		if(u!=null){ 
//			 return "addCarDetails";	
//		}else
//			return "home";
//		
//	}
//	
//	
//	
//	
//	
//	@RequestMapping(value = "/SubmitCar", method = RequestMethod.POST)
//	public String submitCar(	
//			@RequestParam("car-years") String year,
//			@RequestParam("car-makes") String brandAuto,
//			@RequestParam("car-models") String modelAuto,
//			@RequestParam("colorAuto") String colorAuto,
//			@RequestParam("confortAuto") String confortAuto,
//            @ModelAttribute("uploadForm") FileUploadForm uploadForm,
////			@RequestParam("photoCar") String photoCar,
//			Model model, HttpSession session){
//	
//	model.addAttribute("year", year); // <-- da aggiungere in Car
//		
//		User u = (User)session.getAttribute("user");
//	
//		if(u!=null){
//			
//			RegisteredUser ru = (RegisteredUser) u;
//			RegisteredUserMapper rm = new RegisteredUserMapper();
//			 
//			Car car = new Car();
//			car.setBrand(brandAuto);
//			car.setModel(modelAuto);
//			car.setColor(colorAuto);
//			 
//			Integer c = null;
//			if(confortAuto.equals("base"))
//				c=1;
//			else if(confortAuto.equals("normale"))
//				c=2;
//			else if(confortAuto.equals("confortevole"))
//				c=3;
//			else if(confortAuto.equals("lusso"))
//				c=4;
//			 
//			car.setConfort(c);
////			car.setCarPhoto(photoCar);
//			
//			ru.setCar(car);
//			
//			boolean modified = rm.update(ru, ru.getId());
//			
//			
//			System.out.println("In submitCar:"+
//								"---update user per new car: "+ modified);
//			
//			//session.setAttribute("user", ru);
//			
//			
//			//--------------- FOTO ---------------
//			
//			 List<MultipartFile> files = uploadForm.getFiles();
//		         
//		        if(files != null && files.size() > 0) {
//
////	                String filename = multipartFile.getOriginalFilename();
//		        	
//		            	String filename = u.getId()+"_car.jpg";		                	                		                
//		          
//		    			String fullPath = servletContext.getRealPath("/WEB-INF/resources/avatars/"+filename);
//		    			    			
//		    			File f = new File(fullPath);
//		        			
//		    			System.out.println("/resources/avatars/"+filename);
//		    			System.out.println(f.exists());
//		    			System.out.println(f.getAbsolutePath());
//		    			System.out.println(f.getPath());
//
//		    			try {
//			    			if (files.get(0).getBytes().length >0) {
//											    			
//			    				if(f.exists())      
//			    					f.delete();					    			
//			    				
//								files.get(0).transferTo(f); // prendo solo il primo della lista
//								
//			    			}
//						} catch (IllegalStateException | IOException e) {e.printStackTrace();} 
//		        }	        
//		        //--------------- FOTO ---------------
//		         
//			if(modified)
//				model.addAttribute("modified", true);
//			else
//				model.addAttribute("modified", false);
//									
//		        
//			return "submitCar";
//		}
//		else
//			return "home";
//	}
//	
//	
//	@RequestMapping(value = "/ModifyUserCar" )
//	public String modifyUserCar(
//			@RequestParam("brandCar") String brand, 
//			@RequestParam("modelCar") String model, 
//			@RequestParam("colorCar") String color,
//			@RequestParam("confortCar") String confort,
//			//@RequestParam("photoCar") String photoCar,
//			Model m, HttpSession session) {
//		
//		User u = (User)session.getAttribute("user");
//		if(u!=null){
//			
//			RegisteredUserMapper rm = new RegisteredUserMapper();
//			RegisteredUser ru = (RegisteredUser) u;			
//			
//			Car car = ru.getCar();
//			Integer c = null;
//			
//			System.out.println("In MOdifyUserCar:");
//			System.out.println(car);
//			
//			if(car!=null){
////			if(ru.getDriverInfo() == null){
//				
//				System.out.println("car!=null");
//			//	car.setCarPhoto(photoCar);
//				
//				if(confort.equals("base"))
//					c=1;
//				else if(confort.equals("normale"))
//					c=2;
//				else if(confort.equals("confortevole"))
//					c=3;
//				else if(confort.equals("lusso"))
//					c=4;
//				
////				car.setBrand(brand);
////				car.setColor(color);
////				car.setConfort(c);
////				car.setModel(model);
////				car.setComfort(c);	
//			
////			}else{
//				
//				if(!car.getBrand().equals(brand))
//					car.setBrand(brand);
//				if(!car.getColor().equals(color))
//					car.setColor(color);
//	
//				if(confort.equals("base"))
//					c=1;
//				else if(confort.equals("normale"))
//					c=2;
//				else if(confort.equals("confortevole"))
//					c=3;
//				else if(confort.equals("lusso"))
//					c=4;
//						
//				if(!ru.getCar().getConfort().equals(c))
//					car.setConfort(c);
//				if(!ru.getCar().getModel().equals(model))
//					car.setModel(model);
////				if(!ru.getCar().getCarPhoto().equals(photoCar))
////					car.setCarPhoto(photoCar);
//
////			}
//			
//			CarMapper carMapper=new CarMapper();
//			boolean carUpdated = carMapper.update(car,ru.getCar().getId());
//			boolean modified = false;
//			
//		
//			System.out.println("carUpdated---"+ carUpdated);
//			
//			if(carUpdated){
//				m.addAttribute("carUpdated", true);
//				ru.setCar(car);
//				modified = rm.update(ru, ru.getId());
//			}
//			else
//				m.addAttribute("carUpdated", false);
//			
////			boolean modified = rm.update(ru, ru.getId());
//			if(modified)
//				m.addAttribute("modified", true);
//			else
//				m.addAttribute("modified", false);
//			
//			System.out.println("userUp:"+ modified);
//			
//			} // end if car	
//			
////			session.setAttribute("user", ru);
//			return "showUserCar";
//			
//		}
//			
//		else
//			return "home";
//	}
//	
//	@RequestMapping(value = "/DeleteUserCar" )
//	public String deleteUserCar(Model model, HttpSession session) {
//		
//		User u = (User)session.getAttribute("user");
//		if(u!=null){
//			RegisteredUser ru = (RegisteredUser) u;
//			Car car = ru.getCar();
//			CarMapper cm = new CarMapper();
//						
//			boolean deleted = cm.deleteCar(car, ru);
//			if(deleted)
//				model.addAttribute("error",false);
//			else
//				model.addAttribute("error",true);
//			
//			return "showDeleteMsg";
//			
//		}
//		
//		return "home";
//		
//	}
//}
//
