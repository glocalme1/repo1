package com.glocalme.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("/partner")
public class PartnerController {

	/*
    @RequestMapping(value = "/fileupload", method = RequestMethod.POST)
    public void save() {
    	System.out.println("PersonalInfoController - Save");
    	System.out.println("TODO: Save Personal Information");
    }
    */
    
    
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

    	System.out.println( " File Upload is Called " );
    	//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	
    	MultipartHttpServletRequest multipartRequest = new DefaultMultipartHttpServletRequest(request);
    	
    	Iterator<String> fileNames =  multipartRequest.getFileNames();
    
    	while( fileNames.hasNext() ){
    		System.out.println(fileNames.next());
    	}
    
    	MultipartFile multipartFile = multipartRequest.getFile("file");    	
    	System.out.println(multipartFile);
    	ByteArrayInputStream stream = null;
    	String fileContents = null;
    	try {
    		stream = new   ByteArrayInputStream(multipartFile.getBytes());
    		fileContents = IOUtils.toString(stream, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.println(fileContents);
    	return null;
    	
    	/*
    	try {
	    	Map fileMap = multipartRequest.getFileMap();
	
	    	Iterator fileIt = fileMap.keySet().iterator();
	    	BufferedReader bufReader = null;
	    	while (fileIt.hasNext()) {
	    	   String fileKey = (String) fileIt.next();
	    	   MultipartFile file = (MultipartFile) fileMap.get(fileKey);
	    	   if (file != null) {
	    	      bufReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	    	      String str = null;
		       	   while ((str = bufReader.readLine()) != null) {
		       		   System.out.println(str);
		     	   }
	    	   } else {
	    	      System.out.println("Invalid file");
	    	   }
	    	} 
    	}catch(Exception e) {
    	
    	}
    	return null;
    	*/
    }
    
    /*
    public void upload(MultipartHttpServletRequest request, HttpServletResponse response) {                 
  
    	System.out.println("UPload is called");
      //0. notice, we have used MultipartHttpServletRequest
  
      //1. get the files from the request object
      Iterator<String> itr =  request.getFileNames();
      
      while( itr.hasNext() ) {
    	  System.out.println(itr.next());
      }
  
      MultipartFile mpf = request.getFile(itr.next());
      System.out.println(mpf.getOriginalFilename() +" uploaded!");
  
      UploadedFile ufile = new UploadedFile();
      try {
                 //just temporary save file info into ufile
         ufile.length = mpf.getBytes().length;
         ufile.bytes= mpf.getBytes();
         ufile.type = mpf.getContentType();
         ufile.name = mpf.getOriginalFilename();
  
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
   }
   */
    
    /*
    @ResponseBody 
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)    
    public String uploadFile(@RequestParam("file") MultipartFile file) { 
     		String str = null; 
     		StringBuffer sf = new StringBuffer(); 
    		try { 
     			sf.append( "File Details: " + file.getName() + ", " + file.getOriginalFilename() + "<br>"); 
     			sf.append("And Content is: <hr><hr>"); 
    			sf.append("<pre>"); 
     			InputStream is = file.getInputStream(); 
     			BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
     			while ( (str = br.readLine()) != null) { 
     				sf.append(str + "<br>"); 
     			} 
     			br.close(); 
     			sf.append("</pre>"); 
     		} catch (IOException e) { 
     			e.printStackTrace(); 
     		} 
     		return sf.toString(); 
    } 
   */
    

    
}
