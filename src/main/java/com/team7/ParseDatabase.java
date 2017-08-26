package com.team7;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

@SuppressWarnings("restriction")
public interface ParseDatabase {
	
	//Parses XML to human readable format 
	public String parseXml(File xmlDataFile) throws JAXBException, SQLException,IOException;
}
