package com.team7.queryEngine;

// 
/**
 * The Class SearchParameter.
 * This class contains the search criteria, values, comparator and join conditions
 * The object of this class is sent to the query engine to create the query.
 */ 
public class SearchParameter {
	
	/** The search filter. */
	private String searchFilter = null;
	
	/** The search comparator. */
	private String searchComparator = null;
	
	/** The search value. */
	private String searchValue = null;
	
	/** The join filter. */
	private String joinFilter = null;
	
	/**
	 * Instantiates a new search parameter.
	 */
	public SearchParameter(){
		
	}
	
	/**
	 * Instantiates a new search parameter.
	 *
	 * @param searchFilter the search filter
	 * @param searchComparator the search comparator
	 * @param searchValue the search value
	 * @param joinFilter the join filter
	 */
	public SearchParameter(String searchFilter, String searchComparator, String searchValue, String joinFilter){
		this.searchFilter =searchFilter;
		this.searchComparator = searchComparator;
		this.searchValue = searchValue;	
		if(joinFilter != null){
				this.joinFilter = joinFilter;
			}
		}
	
	/**
	 * Gets the search filter.
	 *
	 * @return the search filter
	 */
	// getters and setters
	public String getSearchFilter(){
		return this.searchFilter;
	}
	
	/**
	 * Gets the search comparator.
	 *
	 * @return the search comparator
	 */
	public String getSearchComparator(){
		return this.searchComparator;
	}
	
	/**
	 * Gets the search value.
	 *
	 * @return the search value
	 */
	public String getSearchValue(){
		return this.searchValue;
	}
	
	/**
	 * Gets the join filter.
	 *
	 * @return the join filter
	 */
	public String getjoinFilter(){
		return this.joinFilter;
	}
	
	/**
	 * Sets the search filter.
	 *
	 * @param newSearchFilter the new search filter
	 * @return the string
	 */
	public String setSearchFilter(String newSearchFilter){
		this.searchFilter = newSearchFilter;
		return this.searchFilter;
	}
	
	/**
	 * Sets the search comparator.
	 *
	 * @param newSearchComparator the new search comparator
	 * @return the string
	 */
	public String setSearchComparator(String newSearchComparator){
		this.searchComparator = newSearchComparator;
		return this.searchComparator;
	}
	
	/**
	 * Sets the search value.
	 *
	 * @param newSearchValue the new search value
	 * @return the string
	 */
	public String setSearchValue(String newSearchValue){
		this.searchValue = newSearchValue;
		return this.searchValue;
	}
	
	/**
	 * Sets the join filter.
	 *
	 * @param newJoinFilter the new join filter
	 * @return the string
	 */
	public String setJoinFilter(String newJoinFilter ){
		this.joinFilter = newJoinFilter;
		return this.joinFilter;
	}

}
