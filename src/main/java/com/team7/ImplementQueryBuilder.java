package com.team7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// This class represents the query engine
public class ImplementQueryBuilder implements QueryBuilder{

	String whereClauseForPaperAuthor = "";
	String whereClauseForCommittee = "";
	String whereClauseForArticle = "";

	String groupByClause = "";
	String queryPaperAuthor = null; 
	String queryCommitte = null; 
	String queryArticle = null;

	private static String AuthorTable = "Author";
	private static String PaperTable = "Paper";

	private static String AuthorTableAlias = "a";
	private static String PaperTableAlias = "p";
	private static String CommitteeTableAlias = "c";
	private static String ArticleTableAlias = "ar";

	List<String> queries = new ArrayList<String>();
	
	// creates queries for all the incoming search parameters and their values 
	// from the UI
	public List<String> createQuery(List<SearchParameter> searchParam) {
		if(validateQuery(searchParam)){
			
			// if there is only one condition of grouping by the number of papers
			if (searchParam.size() == 1 && searchParam.get(0).getSearchFilter() == "CountNoOfPapers") {
				
				return getGroupByQuery(searchParam.get(0));
			}
			else  {
				formQueryParams(searchParam);

				getPaperAuthorQuery();

				getCommitteQuery();	

				getArticleQuery();
			}
		}	
		queries.add(0, queryPaperAuthor);
		queries.add(1, queryCommitte);
		queries.add(2, queryArticle);
		return queries;
	}

	// validates whether the search parameters are valid values or not
	public boolean validateQuery(List<SearchParameter> searchParam) {        	
		boolean result = false;
		for(SearchParameter s : searchParam){

			if(s.getSearchFilter() == "Name"){	
				if(checkValidityOfSearchParameters(s.getSearchValue())){					
					return false;
				}
				else
					result = true;
			}

			if(s.getSearchFilter() == "ConfName"){
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}

			if(s.getSearchFilter() == "Year" && Integer.parseInt(s.getSearchValue()) > 0){
					result = true;
				}
			
			if(s.getSearchFilter() == "Keyword"){	
				// no validation needed for Keyword
				// all characters are accepted
				result = true;
			}

			if(s.getSearchFilter() == "CountNoOfPapers"){            	
				if(Integer.parseInt(s.getSearchValue()) > 0){
					return true;
				}
			}    

			if(s.getSearchFilter() == "Committee.Year" && Integer.parseInt(s.getSearchValue()) > 0){					
					return true;
				}

			if(s.getSearchFilter() == "Committee.ConfName"){
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}

			if(s.getSearchFilter() == "JournalName"){
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}
		}
		return result;
	}

	private boolean checkValidityOfSearchParameters(String searchParameter){

		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(searchParameter);
		return m.find();
	}

	// forming the where clause of the query from the parameters obtained.       
	private void formQueryParams(List<SearchParameter> searchParam){

		for(SearchParameter s : searchParam){	
			if(s.getSearchFilter().contains("Committee")){
				formCommitteeWhereClause(s);
			}

			else if(s.getSearchFilter() == "JournalName"){
				whereClauseForArticle += ArticleTableAlias + ".journal" + s.getSearchComparator()+ " '"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";    			  
			}

			else{
				formPaperAuthorWhereClause(s);
			}
		}	

		if(whereClauseForPaperAuthor.length()>0){
			whereClauseForPaperAuthor = whereClauseForPaperAuthor.substring(0, whereClauseForPaperAuthor.length()-5);
		}   				

		if(groupByClause.length()>0){
			whereClauseForPaperAuthor += groupByClause;
		}

		if(whereClauseForCommittee.length()>0){
			whereClauseForCommittee = whereClauseForCommittee.substring(0, whereClauseForCommittee.length()-5);   

		}

		if(whereClauseForArticle.length()>0){
			whereClauseForArticle = whereClauseForArticle.substring(0, whereClauseForArticle.length()-5);   

		}
	}

	// Getting the final result of the query.
	public List<String> getResultForDisplay(List<String> searchQuery) throws SQLException, IOException{

		List<String> finalResult = new ArrayList<String>();
		List<String> intermediate = new ArrayList<String>();
		List<String> paperAuthorResult = new ArrayList<String>();
		List<String> committeeResult = new ArrayList<String>();
		List<String> articleResult = new ArrayList<String>();

		// If there is query data about paper and author
		if (searchQuery.get(0) != null) {
			ResultSet paperAuthorResultSet = sendQuery(searchQuery.get(0));
			while (paperAuthorResultSet.next()) {
				paperAuthorResult.add(paperAuthorResultSet.getString("Author"));
			}			
		}

		// If there is query data about committee
		if(searchQuery.get(1) != null){
			ResultSet committeeResultSet = sendQuery(searchQuery.get(1));

			while (committeeResultSet.next()) {
				committeeResult.add(committeeResultSet.getString("Author"));
			}  
		}

		// If there is query data about articles
		if(searchQuery.get(2) != null){
			ResultSet articleResultSet = sendQuery(searchQuery.get(2));


			while (articleResultSet.next()) {
				articleResult.add(articleResultSet.getString("Author"));
			}
		} 


		// The result sets which are not empty are intersected together
		if (paperAuthorResult.size() != 0 && 
				committeeResult.size() != 0 && 
				articleResult.size() != 0 ) {
			paperAuthorResult.retainAll(committeeResult);
			paperAuthorResult.retainAll(articleResult);
			intermediate = paperAuthorResult;
		}
		else if (paperAuthorResult.size() != 0 && 
				committeeResult.size() != 0) {
			paperAuthorResult.retainAll(committeeResult);
			intermediate = paperAuthorResult;
		}
		else if (paperAuthorResult.size() != 0 && 
				articleResult.size() != 0) {
			paperAuthorResult.retainAll(articleResult);
			intermediate = paperAuthorResult;
		}
		else if (committeeResult.size() != 0 && 
				articleResult.size() != 0 ) {
			committeeResult.retainAll(articleResult);
			intermediate = committeeResult;
		} 
		else if (paperAuthorResult.size() != 0) {
			intermediate = paperAuthorResult;
		}
		else if (committeeResult.size() != 0) {
			intermediate = committeeResult;
		}
		else if (articleResult.size() != 0) {
			intermediate = articleResult;
		}

		finalResult.addAll(new HashSet<String>(intermediate));
		return finalResult;     		
	}

	// making a connection with the DB
	public ResultSet sendQuery(String searchQuery) throws SQLException, IOException {
		ImplementSchemaDB implementSchemaObj = new ImplementSchemaDB();
		Connection conn = implementSchemaObj.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet result =stmt.executeQuery(searchQuery);	
		return result;
	}

	private void formGroupClause(SearchParameter search){ 	
		groupByClause = " GROUP BY " + AuthorTableAlias +".name HAVING COUNT(*) " + search.getSearchComparator() +search.getSearchValue();
	}

	private void formPaperAuthorWhereClause(SearchParameter s){ 	

		if(s.getSearchFilter() == "Keyword"){
			whereClauseForPaperAuthor += PaperTableAlias+ ".title " + s.getSearchComparator()+ " '%"+ s.getSearchValue()+ "%' " + s.getjoinFilter() + " ";   	
			System.out.println(whereClauseForPaperAuthor);
		}

		else if(s.getSearchFilter() == "Year"){
			whereClauseForPaperAuthor += PaperTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ s.getSearchValue()+ " " + s.getjoinFilter() + " ";
		}

		else if(s.getSearchFilter() == "Name"){
			if(s.getSearchComparator() == "="){
				whereClauseForPaperAuthor += AuthorTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";	
			}
			else{
				whereClauseForPaperAuthor += AuthorTableAlias + "."+ s.getSearchFilter() + " "+ s.getSearchComparator()+ " '%"+ s.getSearchValue()+ "%' " + s.getjoinFilter() + " ";   					
			}
		} 

		else if(s.getSearchFilter() == "ConfName"){
			whereClauseForPaperAuthor += PaperTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";	
		} 

		else if(s.getSearchFilter() == "CountNoOfPapers"){
			formGroupClause(s);
		}

	}

	private String formJoinCondition(){

		return AuthorTable+ " " + AuthorTableAlias+ " INNER JOIN " +
				PaperTable+ " " + PaperTableAlias+ " ON " +
				AuthorTableAlias+ ".paperKey = " + PaperTableAlias + ".paperKey";		
	}


	private void formCommitteeWhereClause(SearchParameter s){  	

		if(s.getSearchFilter() == "Committee.ConfName"){
			whereClauseForCommittee += CommitteeTableAlias + "."+ s.getSearchFilter().split("\\.")[1] + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";
		} 

		else if(s.getSearchFilter() == "Committee.Year"){
			whereClauseForCommittee += CommitteeTableAlias + "."+ s.getSearchFilter().split("\\.")[1] + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";	

		} 
	}

	private void getCommitteQuery(){

		if(whereClauseForCommittee.length()>0){
			queryCommitte = "SELECT c.AuthorName AS Author FROM  Committee c WHERE ";  
			queryCommitte += whereClauseForCommittee;
		}
	}

	private void getPaperAuthorQuery(){ 

		if (whereClauseForPaperAuthor.length() > 0) {

			String joinCondition = formJoinCondition();
			queryPaperAuthor = "SELECT a.name AS Author FROM " + joinCondition + " WHERE ";  
			queryPaperAuthor += whereClauseForPaperAuthor;
		}
	}


	private void getArticleQuery(){

		if(whereClauseForArticle.length()>0){
			queryArticle = "SELECT ar.Author AS Author FROM  Article ar WHERE ";  
			queryArticle += whereClauseForArticle;
		}
	}

	// Gets all details for authors
	public String createQueryForAuthorDetails(Set<String> authors){

		String query = "SELECT a.name AS Author, p.title As PaperTitle, p.confName AS Conference,"
				+ "p.year as Year FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey where a.name IN (";   	
		for(String author : authors){
			query +="'" + author +"',";	
		}     	
		query = query.substring(0, query.length()-1) + ")"; 	
		return query;   	
	}
	
	private List<String> getGroupByQuery(SearchParameter groupByParameter){

	formGroupClause(groupByParameter);
	
	queryPaperAuthor = "SELECT a.name AS Author FROM " + formJoinCondition() +
			groupByClause;
	
	queries.add(0, queryPaperAuthor);
	queries.add(1, queryCommitte);
	queries.add(2, queryArticle);
	
	return queries;
	}
}

