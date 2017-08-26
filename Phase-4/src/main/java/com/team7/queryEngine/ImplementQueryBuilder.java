package com.team7.queryEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.team7.interfaces.QueryBuilder;
import com.team7.parsing.ImplementSchemaDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Class ImplementQueryBuilder.
 * This class represents the query engine
 */ 
public class ImplementQueryBuilder implements QueryBuilder{

	/** The where clause for paper author. */
	String whereClauseForPaperAuthor = "";
	
	/** The where clause for committee. */
	String whereClauseForCommittee = "";
	
	/** The where clause for article. */
	String whereClauseForArticle = "";

	/** The group by clause paper author. */
	String groupByClausePaperAuthor = "";
	
	/** The group by clause article. */
	String groupByClauseArticle = "";
	
	/** The query paper author. */
	String queryPaperAuthor = null; 
	
	/** The query Committee */
	String queryCommitte = null; 
	
	/** The query article. */
	String queryArticle = null;

	/** The Author table. */
	private static String AuthorTable = "Author";
	
	/** The Paper table. */
	private static String PaperTable = "Paper";
	
	/** The Article table. */
	private static String ArticleTable = "Article";

	/** The Author table alias. */
	private static String AuthorTableAlias = "a";
	
	/** The Paper table alias. */
	private static String PaperTableAlias = "p";
	
	/** The Committee table alias. */
	private static String CommitteeTableAlias = "c";
	
	/** The Article table alias. */
	private static String ArticleTableAlias = "ar";
	
	/** The count. */
	private static int count;
	
	/** The final join. */
	private static HashMap<String, String> finalJoin;
	
	/** The query order. */
	String[] queryOrder = new String[5];

	/** The queries. */
	List<String> queries = new ArrayList<String>();
	
	
	/**
	 * Instantiates a new implement query builder.
	 */
	public ImplementQueryBuilder(){
		finalJoin = new HashMap<String, String>();
		count =0;
	}
	
	// creates queries for all the incoming search parameters and their values from the UI 
	/* (non-Javadoc)
	 * @see com.team7.interfaces.QueryBuilder#createQuery(java.util.List)
	 */
	public List<String> createQuery(List<SearchParameter> searchParam) {
		if(validateQuery(searchParam)){
			
			// if there is only one condition of grouping by the number of papers
			if (searchParam.size() == 1 && searchParam.get(0).getSearchFilter() == "CountNoOfPapers") {
				
				return getGroupByPaperAuthorQuery(searchParam.get(0));
			}
			
			// if there is only one condition of grouping by the number of articles
			else if (searchParam.size() == 1 && searchParam.get(0).getSearchFilter() == "CountNoOfArticles") {		
				
				return getGroupByArticleQuery(searchParam.get(0));
			}
			
			else  {
				formQueryParams(searchParam);

				getPaperAuthorQuery();

				getCommitteeQuery();	

				getArticleQuery();
			}
		}	
		queries.add(0, queryPaperAuthor);
		queries.add(1, queryCommitte);
		queries.add(2, queryArticle);
		return queries;
	}

	/* (non-Javadoc)
	 * @see com.team7.interfaces.QueryBuilder#validateQuery(java.util.List)
	 */
	// validates whether the search parameters are valid values or not
	public boolean validateQuery(List<SearchParameter> searchParam) {        	
		boolean result = false;
		for(SearchParameter s : searchParam){

			if(s.getSearchFilter() == "Name"){	
				// no validation needed for Keyword
				// all characters are accepted
				result = true;
				
				if(finalJoin.get("Author")!=null){
					String join = s.getjoinFilter();
					finalJoin.put("Author", join + ":"+count);
					count++;
				}
				else{
				String join= s.getjoinFilter();
				finalJoin.put("Author", join + ":" + count);
				count ++;
			}
		}

			if(s.getSearchFilter() == "ConfName"){
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
				
				if(finalJoin.get("Author")!=null){
					String join = s.getjoinFilter();
					finalJoin.put("Author", join + ":"+ count);
					count++;
				}
				else{
				String join= s.getjoinFilter();
				finalJoin.put("Author", join + ":" + count);
				count ++;
			}
		}

			if(s.getSearchFilter() == "Year" && Integer.parseInt(s.getSearchValue()) > 0){
					result = true;
					
					if(finalJoin.get("Author")!=null){
						String join = s.getjoinFilter();
						finalJoin.put("Author", join +":"+ count);
						count++;
					}
					else{
					String join= s.getjoinFilter();
					finalJoin.put("Author", join + ":" + count);
					count ++;
				}
			}
			
			if(s.getSearchFilter() == "Keyword"){	
				// no validation needed for Keyword
				// all characters are accepted
				result = true;
				
				if(finalJoin.get("Author")!=null){
					String join = s.getjoinFilter();
					finalJoin.put("Author", join +":"+ count);
					count++;
				}
				else{
				String join= s.getjoinFilter();
				finalJoin.put("Author", join + ":" + count);
				count ++;
			}
		}

			if(s.getSearchFilter() == "CountNoOfPapers"){            	
				// no validation needed for Keyword
				// all characters are accepted
				result = true;
				
				if(finalJoin.get("Author")!=null){
					String join = s.getjoinFilter();
					finalJoin.put("Author", join +":"+ count);
					count++;
				}
				else{
				String join= s.getjoinFilter();
				finalJoin.put("Author", join + ":" + count);
				count ++;
			}
			}  
			
			if(s.getSearchFilter() == "CountNoOfArticles"){            	
				// no validation needed for Keyword
				// all characters are accepted
				result = true;
				
				if(finalJoin.get("Article")!=null){
					String join = s.getjoinFilter();
					finalJoin.put("Article", join +":"+ count);
					count++;
				}
				else{
				String join= s.getjoinFilter();
				finalJoin.put("Article", join + ":" + count);
				count ++;
			}
			}    

			if(s.getSearchFilter() == "Committee.Year" && Integer.parseInt(s.getSearchValue()) > 0){					
					result = true;
					if(finalJoin.get("Committee")!=null){
						String join = s.getjoinFilter();
						finalJoin.put("Committee", join + ":"+ count);
						count++;
					}
					else{
					String join= s.getjoinFilter();
					finalJoin.put("Committee", join + ":" + count);
					count ++;
				}		
			}

			if(s.getSearchFilter() == "Committee.ConfName"){
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
				if(finalJoin.get("Committee")!=null){
					String join = s.getjoinFilter();
					finalJoin.put("Committee", join + ":"+count);
					count++;
				}
				else{
				String join= s.getjoinFilter();
				finalJoin.put("Committee", join + ":" + count);
				count ++;
			}
		}

			if(s.getSearchFilter() == "JournalName"){
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
				if(finalJoin.get("Article")!=null){
					String join = s.getjoinFilter();
					finalJoin.put("Article", join + ":"+ count);
					count++;
				}
				else{
				String join= s.getjoinFilter();
				finalJoin.put("Article", join + ":" + count);
				count ++;
			}
				
		 }
		}
		return result;
	}

	/**
	 * Check validity of search parameters.
	 *
	 * @param searchParameter the search parameter
	 * @return true, if successful
	 */
	private boolean checkValidityOfSearchParameters(String searchParameter){

		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(searchParameter);
		return m.find();
	}

	/**
	 * Form query params.
	 *
	 * @param searchParam the search param
	 */
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

		if(groupByClausePaperAuthor.length()>0){
			whereClauseForPaperAuthor += groupByClausePaperAuthor;
		}

		if(whereClauseForCommittee.length()>0){
			whereClauseForCommittee = whereClauseForCommittee.substring(0, whereClauseForCommittee.length()-5);   

		}

		if(whereClauseForArticle.length()>0){
			whereClauseForArticle = whereClauseForArticle.substring(0, whereClauseForArticle.length()-5);   
		}
		
		if(groupByClauseArticle.length()>0){
			whereClauseForArticle += groupByClauseArticle;
		}
	}

	/**
	 * Gets the result for display.
	 *
	 * @param searchQuery the search query
	 * @return the result for display
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Getting the final result of the query.
	public List<String> getResultForDisplay(List<String> searchQuery) throws SQLException, IOException{

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

		return getSequenceOfResults(paperAuthorResult,committeeResult,articleResult);
	}

	/* (non-Javadoc)
	 * @see com.team7.interfaces.QueryBuilder#sendQuery(java.lang.String)
	 */
	// making a connection with the DB
	public ResultSet sendQuery(String searchQuery) throws SQLException, IOException {
		ImplementSchemaDB implementSchemaObj = new ImplementSchemaDB();
		Connection conn = implementSchemaObj.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet result =stmt.executeQuery(searchQuery);	
		return result;
	}

	/**
	 * Form group by clause paper author.
	 *
	 * @param search the search
	 */
	private void formGroupByClausePaperAuthor(SearchParameter search){ 	
		groupByClausePaperAuthor = " GROUP BY " + AuthorTableAlias +".name HAVING COUNT(*) " + search.getSearchComparator() +search.getSearchValue();
	}
	
	/**
	 * Form group by clause article.
	 *
	 * @param search the search
	 */
	private void formGroupByClauseArticle(SearchParameter search){ 	
		groupByClauseArticle = " GROUP BY " + AuthorTableAlias +".name HAVING COUNT(*) " + search.getSearchComparator() +search.getSearchValue();
	}

	/**
	 * Form paper author where clause.
	 *
	 * @param s the s
	 */
	private void formPaperAuthorWhereClause(SearchParameter s){ 	

		if(s.getSearchFilter() == "Keyword"){
			whereClauseForPaperAuthor += PaperTableAlias+ ".title " + s.getSearchComparator()+ " '%"+ s.getSearchValue()+ "%' " + s.getjoinFilter() + " ";   					
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
			formGroupByClausePaperAuthor(s);
		}
		
		else if(s.getSearchFilter() == "CountNoOfArticles"){
			formGroupByClauseArticle(s);
		}

	}

	/**
	 * Form join condition.
	 *
	 * @return the string
	 */
	private String formJoinCondition(){

		return AuthorTable+ " " + AuthorTableAlias+ " INNER JOIN " +
				PaperTable+ " " + PaperTableAlias+ " ON " +
				AuthorTableAlias+ ".paperKey = " + PaperTableAlias + ".paperKey";		
	}

	/**
	 * Form join condition article.
	 *
	 * @return the string
	 */
	private String formJoinConditionArticle(){

		return AuthorTable+ " " + AuthorTableAlias+ " INNER JOIN " +
				ArticleTable+ " " + ArticleTableAlias+ " ON " +
				AuthorTableAlias+ ".articleKey = " + ArticleTableAlias + ".articleKey";		
	}

	
	/**
	 * Form committee where clause.
	 *
	 * @param s the s
	 */
	private void formCommitteeWhereClause(SearchParameter s){  	

		if(s.getSearchFilter() == "Committee.ConfName"){
			whereClauseForCommittee += CommitteeTableAlias + "."+ s.getSearchFilter().split("\\.")[1] + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";
		} 

		else if(s.getSearchFilter() == "Committee.Year"){
			whereClauseForCommittee += CommitteeTableAlias + "."+ s.getSearchFilter().split("\\.")[1] + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";	

		} 
	}

	/**
	 * Gets the committee query.
	 *
	 * @return the committee query
	 */
	private void getCommitteeQuery(){

		if(whereClauseForCommittee.length()>0){
			queryCommitte = "SELECT c.AuthorName AS Author FROM  Committee c WHERE ";  
			queryCommitte += whereClauseForCommittee;
		}
	}
	
	/**
	 * Gets the paper author query.
	 *
	 * @return the paper author query
	 */
	private void getPaperAuthorQuery(){ 

		if (whereClauseForPaperAuthor.length() > 0) {

			String joinCondition = formJoinCondition();
			queryPaperAuthor = "SELECT a.name AS Author FROM " + joinCondition;
			
			if(whereClauseForPaperAuthor.startsWith(" GROUP")){
			queryPaperAuthor+= whereClauseForPaperAuthor;
			}
			else{	
			queryPaperAuthor += " WHERE " + whereClauseForPaperAuthor;
			}
		}
	}

	/**
	 * Gets the article query.
	 *
	 * @return the article query
	 */
	private void getArticleQuery(){

		if(whereClauseForArticle.length()>0){
			String joinCondition = formJoinConditionArticle();
			queryArticle = "SELECT a.name AS Author FROM " + joinCondition;
			
			if(whereClauseForArticle.startsWith(" GROUP")){
				queryArticle+= whereClauseForArticle;
				}
				else{	
					queryArticle += " WHERE " + whereClauseForArticle;
				}
		}
	}

	/**
	 * Creates the query for author details.
	 * Gets all the publication details for authors
	 *
	 * @param authors the authors
	 * @return the string
	 */
	public String createQueryForAuthorDetails(Set<String> authors){

		String query = "SELECT * from (SELECT a.name AS Author, p.title AS PaperTitle, null AS ArticleTitle, a.url AS Url"
				+ " FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE a.name IN (";   	
		for(String author : authors){
			query +="'" + author +"',";	
		}     	
		query = query.substring(0, query.length()-1) + ")" + " UNION SELECT a.name AS Author, null AS PaperTitle, ar.title AS ArticleTitle,"
				+ " a.url AS Url FROM Author a INNER JOIN "
				+ "Article ar ON a.articleKey=ar.articleKey WHERE a.name IN (";
		for(String author : authors){
			query +="'" + author +"',";	
		}
		query = query.substring(0, query.length()-1) + ")) as Author ORDER BY Author";
		
		return query;  
		
	}
	
	/**
	 * Gets the group by paper author query.
	 *
	 * @param groupByParameter the group by parameter
	 * @return the group by paper author query
	 */
	private List<String> getGroupByPaperAuthorQuery(SearchParameter groupByParameter){

	formGroupByClausePaperAuthor(groupByParameter);
	
	queryPaperAuthor = "SELECT a.name AS Author FROM " + formJoinCondition() +
			groupByClausePaperAuthor;
	
	queries.add(0, queryPaperAuthor);
	queries.add(1, queryCommitte);
	queries.add(2, queryArticle);
	
	return queries;
	}
	
	/**
	 * Gets the group by article query.
	 *
	 * @param groupByParameter the group by parameter
	 * @return the group by article query
	 */
	private List<String> getGroupByArticleQuery(SearchParameter groupByParameter){

		formGroupByClauseArticle(groupByParameter);
		
		queryArticle = "SELECT a.name AS Author FROM " + formJoinConditionArticle() +
				groupByClauseArticle;
		
		queries.add(0, queryPaperAuthor);
		queries.add(1, queryCommitte);
		queries.add(2, queryArticle);
		
		return queries;
		}

	/**
	 * Creates the query for similar authors.
	 * getting the similar authors according to the same university
	 *
	 * @param author the author
	 * @return the string
	 */ 
	public String createQueryForSimilarAuthors(String author) {
		
		String uniQuery = "select university from Author where name='"+author+"'";
		ResultSet rs1;
		String uni = new String();
		String authQuery = new String();
		try {
			rs1 = sendQuery(uniQuery);
			
			while (rs1.next()) {
				uni = rs1.getString(1);
			}
			
			if (uni == null) {
				authQuery = "select name from Author where university is NULL";
			}
			else {
				authQuery = "select name from Author where university='"+uni+"'";
			}
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return authQuery;
	}
	
    /**
     * Gets the sequence of results.
     *
     * @param paperAuthor the paper author
     * @param committee the committee
     * @param article the article
     * @return the sequence of results
     */
    private List<String> getSequenceOfResults(List<String> paperAuthor, List<String> committee, List<String> article){
		
		for(Entry<String, String> value :finalJoin.entrySet()){
			String key = (String) value.getKey();
			int val = Integer.parseInt(value.getValue().toString().split(":")[1]);
			
			queryOrder[val] = key + ":" + value.getValue().toString().split(":")[0];	
		}
		
		List<String> inter = new ArrayList<String>();
		String joinParameter = "";
		
		for(String query : queryOrder){
			if(query != null){			
				if(query.split(":")[0].equals("Author")){
					if(joinParameter.length() == 0){
						inter = paperAuthor;
						joinParameter = query.split(":")[1]; 
					}
					else{
						if(joinParameter.equals("AND")){
							inter.retainAll(paperAuthor);
							joinParameter = query.split(":")[1]; 
						}
						else{
							inter.addAll(paperAuthor);
							joinParameter = query.split(":")[1]; 
						}
					}
				}
				
				else if(query.split(":")[0].equals("Article")){
					if(joinParameter.length() == 0){
						inter = article;
						joinParameter = query.split(":")[1]; 
					}
					else{
						if(joinParameter.equals("AND")){
							inter.retainAll(article);
							joinParameter = query.split(":")[1]; 
						}
						else{
							
							inter.addAll(article);
							joinParameter = query.split(":")[1]; 
						}
					}
				}
				
				
				else if(query.split(":")[0].equals("Committee")){
					if(joinParameter.length() == 0){
						inter = committee;
						joinParameter = query.split(":")[1]; 
					}
					else{
						if(joinParameter.equals("AND")){
							inter.retainAll(committee);
							joinParameter = query.split(":")[1]; 
						}
						else{
							inter.addAll(committee);
							joinParameter = query.split(":")[1]; 
						}
					}
				}
				
			}
		}
		
		Set<String> setWithUniqueValues = new TreeSet<>(inter);
		List<String> listWithUniqueValues = new ArrayList<>(setWithUniqueValues);
		return listWithUniqueValues;
		
	}

    /**
     * Creates the query for fav list.
     * getting the favorites of the user
     *
     * @param attName the att name
     * @param attValue the att value
     * @return the string
     */ 
	public String createQueryForFavList(String attName, String attValue) {
		String favQuery = "SELECT selectedAuthor FROM Favorite_list WHERE " + attName + "='"+attValue+"'" + " ORDER by selectedAuthor";
		return favQuery;
	}

	/**
	 * Creates the query for committee list.
	 * getting the final candidate list
	 *
	 * @param conference the conference
	 * @return the string
	 */
	public String createQueryForCommitteeList(String conference) {
		String commQuery = "SELECT selectedAuthor from Candidate_list WHERE confName=" + "'"+conference + "'" + " ORDER by selectedAuthor";
		return commQuery;
	}

}

