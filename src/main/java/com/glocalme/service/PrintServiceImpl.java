package com.glocalme.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.glocalme.exception.GlocalMeException;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class PrintServiceImpl implements PrintService {

	@Override
	public JasperReport compileReport( String reportFileName , String jasperFileName ) throws GlocalMeException {
		JasperReport jasperReport = null;
		System.out.println("Report File Name: " + reportFileName );
		System.out.println("Jasper File Name: " + jasperFileName );		
		try {
			File reportFile = new File( jasperFileName );
	
			if (!reportFile.exists()) {
				System.out.println( "Jasper Doesnot exist, Compiling" );
				JasperCompileManager.compileReportToFile( reportFileName, jasperFileName );
				System.out.println( "Compilation Done" );
			}
			jasperReport = (JasperReport) JRLoader.loadObject( reportFile );
		} catch(Exception e) {
			throw new GlocalMeException();
		}
		return jasperReport;
 		
	}

}
