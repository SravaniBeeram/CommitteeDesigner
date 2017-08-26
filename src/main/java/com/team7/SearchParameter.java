package com.team7;

// This class contains the search criteria, values, comparator and join conditions
// The object of this class is sent to the query engine to create the query.
public class SearchParameter {
	
	private String searchFilter = null;
	private String searchComparator = null;
	private String searchValue = null;
	private String joinFilter = null;
	
	SearchParameter(){
		
	}
	
	SearchParameter(String searchFilter, String searchComparator, String searchValue, String joinFilter){
		this.searchFilter =searchFilter;
		this.searchComparator = searchComparator;
		this.searchValue = searchValue;	
		if(joinFilter != null){
				this.joinFilter = joinFilter;
			}
		}
	
	// getters and setters
	public String getSearchFilter(){
		return this.searchFilter;
	}
	
	public String getSearchComparator(){
		return this.searchComparator;
	}
	
	public String getSearchValue(){
		return this.searchValue;
	}
	
	public String getjoinFilter(){
		return this.joinFilter;
	}
	
	public String setSearchFilter(String newSearchFilter){
		this.searchFilter = newSearchFilter;
		return this.searchFilter;
	}
	
	public String setSearchComparator(String newSearchComparator){
		this.searchComparator = newSearchComparator;
		return this.searchComparator;
	}
	
	public String setSearchValue(String newSearchValue){
		this.searchValue = newSearchValue;
		return this.searchValue;
	}
	
	public String setJoinFilter(String newJoinFilter ){
		this.joinFilter = newJoinFilter;
		return this.joinFilter;
	}

}
