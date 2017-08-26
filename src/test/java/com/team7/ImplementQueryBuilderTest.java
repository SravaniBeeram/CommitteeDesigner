package com.team7;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class ImplementQueryBuilderTest extends TestCase {

	// Testing queries of different types
	 
  	@Test
	public void testValidAuthorName(){ 	
  		List<String> query1 = new ArrayList<String>();
  		query1.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE a.Name='Michael Ley' ");
  		query1.add(1, null);
  		query1.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("Name", "=" ,"Michael Ley", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query1, queryFormed);
	}

	@Test 
	public void testInvalidAuthorNameWithSpecialCharacters(){	
		
		List<String> expected = new ArrayList<String>();
		expected.add(0,null);
		expected.add(1,null);	
		expected.add(2,null);	
  		
		SearchParameter s= new SearchParameter("Name", "=" ,"#Michael Ley", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}

	@Test
	public void testInvalidAuthorNameWithNumbers(){		
		List<String> expected = new ArrayList<String>();
		expected.add(0,null);
		expected.add(1,null);	
		expected.add(2,null);	
  		
		SearchParameter s= new SearchParameter("Name", "=" ,"123 Michael Ley", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals( expected, queryFormed);
	}

	@Test
	public void testValidYearsActive(){	
		
		List<String> query2 = new ArrayList<String>();
  		query2.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.Year>2 ");
  		query2.add(1,null);	
  		query2.add(2,null);	

		SearchParameter s= new SearchParameter("Year", ">" ,"2", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query2, queryFormed);
	}

	@Test
	public void testInvalidYearsActive(){	
		List<String> expected = new ArrayList<String>();
		expected.add(0,null);
		expected.add(1,null);	
		expected.add(2,null);	

		SearchParameter s= new SearchParameter("Year", ">" ,"-1", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();	
	
  		searchCriteria.add(0,s);	
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}

	@Test
	public void testValidKeyword(){	
		
		List<String> query3 = new ArrayList<String>();
  		query3.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.title LIKE '%Chemistry%' ");
  		query3.add(1,null);	
  		query3.add(2,null);	

		SearchParameter s= new SearchParameter("Keyword", "LIKE" ,"Chemistry", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query3, queryFormed);
	}
	
	@Test
	public void testDiffFilter(){
		
		List<String> query4 = new ArrayList<String>();
  		query4.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE a.Name='ABC' AND p.title LIKE '%Biology%' ");
  		query4.add(1,null);	
  		query4.add(2,null);	
  		
  		SearchParameter s1= new SearchParameter("Name", "=" ,"ABC","AND");
		SearchParameter s2= new SearchParameter("Keyword", "LIKE" ,"Biology", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
		searchCriteria.add(0,s1);
		searchCriteria.add(1, s2);
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query4, queryFormed);
	}
	
	@Test
	public void testValidMultipleKeyword(){
		
		List<String> query5 = new ArrayList<String>();
  		query5.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.title LIKE '%Chemistry%' OR p.title LIKE '%Biology%' ");
  		query5.add(1, null);	
  		query5.add(2,null);	

		SearchParameter s1= new SearchParameter("Keyword", "LIKE" ,"Chemistry","OR");
		SearchParameter s2= new SearchParameter("Keyword", "LIKE" ,"Biology", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
		searchCriteria.add(0,s1);
		searchCriteria.add(1,s2);
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query5, queryFormed);
	}
	
	@Test
	public void testSendQuery() throws SQLException, IOException{	
		SearchParameter s1= new SearchParameter("Name", "=" ,"Petra Ludewig","OR");
		SearchParameter s2= new SearchParameter("Keyword", "LIKE" ,"Extension", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
  		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);			
  		assertEquals("Yusuke Nishiguchi", result.get(0));
	}
	
	@Test
	public void testMultipleAuthorSearch() throws SQLException, IOException{
		SearchParameter s1= new SearchParameter("Name", "=" ,"Shahar Maoz","OR");
		SearchParameter s2= new SearchParameter("Name", "=" ,"Jan Oliver Ringert", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals("Shahar Maoz", result.get(0));
	}
	
	@Test
	public void testMultipleAuthorInvalidSearch() throws SQLException, IOException{
		SearchParameter s1= new SearchParameter("Name", "=" ,"ABC","AND");
		SearchParameter s2= new SearchParameter("Name", "=" ,"Michael Ley", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals(0, result.size());
	}
	
	@Test
	public void testFirstUseCase() throws SQLException{    //add another test fetching result from DB
		
		List<String> query6 = new ArrayList<String>();
  		query6.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.ConfName='OOPSLA' AND p.Year=2010 GROUP BY a.name HAVING COUNT(*) >1");
  		query6.add(1,null);	
  		query6.add(2,null);	

		SearchParameter s1= new SearchParameter("ConfName", "=" ,"OOPSLA","AND");
		SearchParameter s2= new SearchParameter("Year", "=" ,"2010", "AND");
		SearchParameter s3= new SearchParameter("CountNoOfPapers", ">" ,"1", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		searchCriteria.add(2,s3);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query6, queryFormed);
	}
	
	@Test
	public void testSecondUseCase() throws SQLException{    //add another test fetching result from DB
		List<String> query7 = new ArrayList<String>();
  		query7.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.title LIKE '%pointer%' OR p.title LIKE '%analysis%' GROUP BY a.name HAVING COUNT(*) >=1");
  		query7.add(1,null);
  		query7.add(2,null);
  		
		SearchParameter s1= new SearchParameter("Keyword", "LIKE" ,"pointer","OR");
		SearchParameter s2= new SearchParameter("Keyword", "LIKE" ,"analysis", "AND");
		SearchParameter s3= new SearchParameter("CountNoOfPapers", ">=" ,"1", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		searchCriteria.add(2,s3);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query7, queryFormed);
	}
	
	@Test
	public void testThirdUseCase() throws SQLException, IOException{    //add another test fetching result from DB
		SearchParameter s1= new SearchParameter("Keyword", "LIKE" ,"pointer","AND");
		SearchParameter s2= new SearchParameter("Committee.Year", "=" ,"2006", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
  		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals("Simon Marlow",result.get(0));  
	}
	
	@Test
	public void testNoDuplicateAuthorName() throws SQLException, IOException{
  		 
  		SearchParameter s= new SearchParameter("Keyword", "LIKE" ,"test", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		
		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals(1, Collections.frequency(result, "William Pugh"));  
	}
	
	@Test
	public void testLikeClauseAuthorName() throws SQLException, IOException{ 	
  		List<String> query9= new ArrayList<String>();
  		query9.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE a.Name LIKE '%Anurag%' ");
  		query9.add(1,null);	
  		query9.add(2,null);
  		 
  		SearchParameter s= new SearchParameter("Name", "LIKE" ,"Anurag", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query9, queryFormed);
		
		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals("Anurag Mendhekar",result.get(0));   
	}
	
	@Test
	public void testCountOfPapers() throws SQLException, IOException{ 
  		SearchParameter s1= new SearchParameter("CountNoOfPapers", ">=" ,"2",null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		
		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);
		assertEquals("Steven Lucco",result.get(0)); 
	}
	
	@Test
	public void testQueryWithJournal() throws SQLException, IOException{ 	
  		SearchParameter s1= new SearchParameter("Name", "=" ,"Brian Demsky","AND");
  		SearchParameter s2= new SearchParameter("JournalName", "=" ,"tse", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);
		assertEquals("Brian Demsky",result.get(0)); 
	}

	@Test
	public void testInValidConfName(){ 	
  		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("ConfName", "=" ,"CONF123", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}
	
	@Test
	public void testInValidYear(){ 	
  		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("Year", "=" ,"-8", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}
	
	@Test
	public void testInValidCountNoOfPapers(){ 	
  		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("CountNoOfPapers", "=" ,"-2", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}
	
	@Test
	public void testInValidCommitteeYear(){ 	
  		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("Committee.Year", "=" ,"-2", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}
	
	@Test
	public void testInValidJournalName(){ 	
  		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("JournalName", "=" ,"JournalName123", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}
	
	@Test
	public void testQueryWithJustJournal() throws SQLException, IOException{ 	
  	  	SearchParameter s1= new SearchParameter("JournalName", "=" ,"tse", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);
		assertEquals(5338 ,result.size()); 
	}

	@Test
	public void testCommitteeName() throws SQLException, IOException{   
		SearchParameter s1= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		//searchCriteria.add(1,s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
  		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals(377,result.size());  
	}
	
	@Test
	public void testInValidCommitteName() throws SQLException{    
		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
		SearchParameter s1= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA123", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected,queryFormed);  
	}
	
	@Test
	public void testAllThreeTablesUseCase() throws SQLException, IOException{   
		SearchParameter s1= new SearchParameter("Keyword", "LIKE" ,"pointer","AND");
		SearchParameter s2= new SearchParameter("Committee.Year", "=" ,"2006", null);
		SearchParameter s3= new SearchParameter("JournalName", "=" ,"tse", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		searchCriteria.add(1,s3);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
  		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals("Amer Diwan",result.get(0));  
	}
	
	@Test
	public void testCommitteeAndArticleUseCase() throws SQLException, IOException{   
		SearchParameter s1= new SearchParameter("Committee.Year", "=" ,"2006", null);
		SearchParameter s2= new SearchParameter("JournalName", "=" ,"tse", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		searchCriteria.add(1,s2);
  		//searchCriteria.add(1,s3);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
  		List<String> result = new ImplementQueryBuilder().getResultForDisplay(queryFormed);	
		assertEquals("Luca Cardelli",result.get(0));  
	}
	
	@Test
	public void testInvalidSearchParemeter() throws SQLException{   
		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
		SearchParameter s1= new SearchParameter("XYZ", "=" ,"2006", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected,queryFormed);  
	}
	
	@Test
	public void testInvalidCommitteeSearchParemeter() throws SQLException{   
		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
		SearchParameter s1= new SearchParameter("Committee.XYZ", "=" ,"2006", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected,queryFormed);  
	}
	
	
	@Test
	public void testFirstUseCase1() throws SQLException{    //add another test fetching result from DB
		
		List<String> query6 = new ArrayList<String>();
  		query6.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey GROUP BY a.name HAVING COUNT(*) >1");
  		query6.add(1,null);	
  		query6.add(2,null);	

		SearchParameter s1= new SearchParameter("CountNoOfPapers", ">" ,"1", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(0,s1);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(query6, queryFormed);
	}


}