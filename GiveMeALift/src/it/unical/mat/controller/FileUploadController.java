package it.unical.mat.controller;

import it.unical.mat.domain.User;
import it.unical.mat.service.FileUploadForm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

 
/**
 * @author kekka
 * 
 * @method displayForm: show input form to user, forwarding to the page file_upload_form.jsp
 *  
 * @method save: Fetches the form using @ModelAttribute annotation and get the File content from it. 
 * It creates a list of filenames of files being uploaded and pass this list to success page.
 *
 */
@Controller
public class FileUploadController {

    @Autowired
    private ServletContext servletContext;
     
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String displayForm() {
        return "file_upload_form";
    }
     
    
    
    @RequestMapping(value = "/Save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("uploadForm") FileUploadForm uploadForm,
                    HttpSession session,Model map) {
         
        List<MultipartFile> files = uploadForm.getFiles();
 
        List<String> fileNames = new ArrayList<String>();
         
        if(files != null && files.size() > 0) {
//            for (MultipartFile multipartFile : files) {
 
//                String filename = multipartFile.getOriginalFilename();
//                fileNames.add(filename);
                
                //Handle file content - multipartFile.getInputStream()
                
            	File f = null;

             	User u = ((User)session.getAttribute("user"));;
            	String filename = "dummy.jpg";
            	if(u!=null)
            		filename = u.getId()+".jpg";
                
                fileNames.add(filename);// serve solo per l'esempio
                
                
//                multipartFile.getInputStream();
                
                
          
    			String fullPath = servletContext.getRealPath("/WEB-INF/resources/avatars/"+filename);
    			    			
    			f = new File(fullPath);
        			
    			System.out.println("/resources/avatars/"+filename);
    			System.out.println(f.exists());
    			System.out.println(f.getAbsolutePath());
    			System.out.println(f.getPath());
//    			
    			if(f.exists())         	f.delete();
//    			if(!dir.exists())         	dir.mkdirs();
////    			else   for (File f : dir.listFiles())	f.delete();		
    			
    			try {
    				
					files.get(0).transferTo(f); // prendo solo il primo della lista
					
				} catch (IllegalStateException | IOException e) {e.printStackTrace();} 
//            }//FOREACH
        }
         
        map.addAttribute("files", fileNames);
        return "file_upload_success";
    }
} 