package com.glocalme.service;

import com.glocalme.exception.GlocalMeException;

import net.sf.jasperreports.engine.JasperReport;

public interface PrintService {
	
	public JasperReport compileReport( String reportName , String jasperFileName) throws GlocalMeException;

}
