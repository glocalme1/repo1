package com.glocalme.controller;

import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glocalme.constants.ReportConstants;
import com.glocalme.service.PrintService;
import com.glocalme.service.dto.IdentityScanDto;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

@Controller
@Scope("request")
@RequestMapping("/retailer")
public class RetailerController {

	
	@Autowired
	private PrintService printService;
	
    @RequestMapping(value = "/print", method = RequestMethod.POST)
    public void print(@RequestBody IdentityScanDto identityScanDto, 
    		HttpServletRequest servletRequest,
    		HttpServletResponse servletResponse ) {
    	
    	System.out.println("RetailerController - Print");
    	System.out.println(identityScanDto.getFullName());
    	System.out.println(identityScanDto.getIdNumber());
    	
    	
    	String jasperFileName = servletRequest.getServletContext().getRealPath("/") + ReportConstants.JASPER_FOLDER + "/" + ReportConstants.RETAILER_BUYDEVICE + ".jasper";
    	String reportFileName = servletRequest.getServletContext().getRealPath("/") + ReportConstants.REPORT_FOLDER + "/" + ReportConstants.RETAILER_BUYDEVICE + ".jrxml";
    	JasperReport jasperReport = null;
    	try {
    		jasperReport = printService.compileReport( reportFileName , jasperFileName );
    		byte[] bytes = JasperRunManager.runReportToPdf(jasperReport , new HashMap<String,String>());
        	servletResponse.reset();
            servletResponse.resetBuffer();
            servletResponse.setContentType("application/pdf");
            servletResponse.setContentLength(bytes.length);
            ServletOutputStream ouputStream = servletResponse.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();    		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    @RequestMapping(value = "/scanid", method = RequestMethod.POST)
    public @ResponseBody IdentityScanDto scan() {
    	System.out.println("RetailerController - Scan");
    	IdentityScanDto identityScanDto = new IdentityScanDto();
    	identityScanDto.setFullName("FullNameTest");
    	identityScanDto.setIdNumber("S123555");
    	return identityScanDto;
    }

    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save() {
    	System.out.println("RetailerController - Save");    	
    }
    
}
