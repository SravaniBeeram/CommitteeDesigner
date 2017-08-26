package com.team7.interfaces;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.team7.queryEngine.SearchParameter;

/**
 * The Interface QueryBuilder.
 */
public interface QueryBuilder {

	
	/**
	 * Creates the query.
	 * createQuery method receives a List for search parameters
	 * Returns a List of queries which will be joined for the final result
	 *
	 * @param searchParam the search param
	 * @return the list
	 */ 
	public List<String> createQuery(List<SearchParameter> searchParam);

	/**
	 * Validate query.
	 * validate the query and look for evil inputs for enhanced security
	 *
	 * @param searchParam the search param
	 * @return true, if successful
	 */
	public boolean validateQuery(List<SearchParameter> searchParam);

	/**
	 * Send query.
	 * sends the query to Database and fetches results
	 *
	 * @param searchQuery the search query
	 * @return the result set
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ResultSet sendQuery(String searchQuery) throws SQLException,IOException;
}
