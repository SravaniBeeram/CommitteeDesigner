package com.team7.interfaces;

import java.io.IOException;
import java.sql.SQLException;

 
/**
 * The Interface ParseCsvFiles.
 * An interface for the parsing of the csv file containing information
 * about the university of author, their home page and the authors affiliated universities.
 */ 
public interface ParseCsvFiles {
	
	/**
	 * Parses the csv.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	public String parseCsv() throws IOException, SQLException;

}
