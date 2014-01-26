package it.unical.mat.util.io;


import it.unical.mat.domain.User;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.Conversions;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class FileUpload extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
//	private String connectionId;
	
	File f = null;
    String configName = null;
	
    @SuppressWarnings("resource")
	public void doPost(final HttpServletRequest request,final HttpServletResponse response)  throws ServletException, IOException {
    	
    	User u = ((User)request.getSession().getAttribute("user"));;
    	String filename = "dummy.jpg";
    	if(u!=null)
    		filename = u.getId()+".jpg";
    	    	
    	final ServletFileUpload upload = new ServletFileUpload();
    	try {
    		final FileItemIterator iter = upload.getItemIterator(request);
    		
    		while (iter.hasNext()) {// potrebbe comprendere più file
    			System.out.println("DOING FILE");
    			FileItemStream item = iter.next();
    			String name = item.getFieldName();
    			    			
//    			connectionId = (name.split("_")[0]);
    			
    			
    			ServletContext context = this.getServletContext();
    			String fullPath = context.getRealPath("/WEB-INF/resources/avatars/"+filename);
    			    			
    			f = new File(fullPath);
        			
    			System.out.println("/resources/avatars/"+filename);
    			System.out.println(f.exists());
    			System.out.println(f.getAbsolutePath());
    			System.out.println(f.getPath());
//    			
    			if(f.exists())         	f.delete();
//    			if(!dir.exists())         	dir.mkdirs();
////    			else   for (File f : dir.listFiles())	f.delete();		
//    			
    			InputStream stream = item.openStream();
//    			FileOutputStream fileOuputStream = new FileOutputStream(dir.getPath()+"/output.zip");
    			FileOutputStream fileOuputStream = new FileOutputStream(f.getPath());
    			// Process the input stream
    			ByteArrayOutputStream out = new ByteArrayOutputStream();                
    			int len;
    			
    			byte[] buffer = new byte[request.getContentLength()];            
    			while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
//                	System.out.println("len:" +len);
    				out.write(buffer,0, len);
    				fileOuputStream.write(buffer,0,len);
    				
    			}
    			int maxFileSize = 10*(1024*1024); //10 megs max 
    			if (out.size() > maxFileSize) {
    				throw new RuntimeException("File is > than " + maxFileSize); 
    			}
    			fileOuputStream.close();   
    		}//WHILE    		
    		
    		
//    		Thread t = new Thread(new Runnable(){@Override public void run() {fileLoad(dir,configName);}	});
//    		t.start();     
    		
    	} catch (FileUploadException e) {
    		e.printStackTrace();
    		writeResponse(response, "UPLOAD FAILED");
    	}      	
    	
    	String referer = request.getHeader("Referer");
    	response.sendRedirect(referer);
//    	writeResponse(response, "/ShowUserProfile");          
    }//doPost
	
	private static final void copyInputStream(InputStream in, OutputStream out)	throws IOException
	{
		byte[] buffer = new byte[1024];
		int len;
		while((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);
		in.close();
		out.close();
	}

	private void writeResponse(HttpServletResponse response, String responseString)	throws IOException {
		response.setContentLength(responseString.length());
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8" ); 
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(responseString);
		response.getWriter().flush();
	}

}
