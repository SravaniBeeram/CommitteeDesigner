package com.team7.interfaces;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.team7.queryEngine.SearchParameter;

/**
 * The Interface SearchDisplay.
 */
public interface SearchDisplay {
	
	/**
	 * Search.
	 * sends these parameters to the query builder
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */ 
	public List<String> search(List<SearchParameter> searchFilter) throws SQLException,IOException;
	
	//  , 
	/**
	 * Send email.
	 * gets a list of authors and sends email ID's to all users 
	 * with the list of the final committee
	 *
	 * @param authors the authors
	 * @param userName the user name
	 * @return the string
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */ 
	public String sendEmail(Set<String> authors, String userName) throws SQLException,IOException;

	/**
	 * Candidate details.
	 * gives the publication details of the
	 *
	 * @param authors the authors
	 * @return the result set
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ResultSet candidateDetails(Set<String> authors) throws SQLException,IOException;
	
	/**
	 * Similar author.
	 *
	 * @param author the author
	 * @return the sets the
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Set<String> similarAuthor(String author) throws SQLException, IOException;
	
}
