package com.team7.queryTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.team7.queryEngine.ImplementQueryBuilder;
import com.team7.queryEngine.SearchParameter;

import junit.framework.TestCase;

/**
 * The Class ImplementQueryBuilderTest.
 */
public class ImplementQueryBuilderTest extends TestCase {

	// Testing queries of different types
	 
  	/**
	 * Test valid author name.
	 */
	@Test
	public void testValidAuthorName(){ 	
  		List<String> query1 = new ArrayList<String>();
  		query1.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE a.Name='Michael Ley' ");
  		query1.add(1, null);
  		query1.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("Name", "=" ,"Michael Ley", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);	
  		ImplementQueryBuilder obj1 = new ImplementQueryBuilder();
		List<String> queryFormed = obj1.createQuery(searchCriteria);
		assertEquals(query1, queryFormed);
	}
  	
	/**
	 * Test valid years active.
	 */
	@Test
	public void testValidYearsActive(){	
		
		List<String> query2 = new ArrayList<String>();
  		query2.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.Year>2 ");
  		query2.add(1,null);	
  		query2.add(2,null);	

		SearchParameter s= new SearchParameter("Year", ">" ,"2", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);
  		ImplementQueryBuilder obj2 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj2.createQuery(searchCriteria);
		assertEquals(query2, queryFormed);
	}

	/**
	 * Test invalid years active.
	 */
	@Test
	public void testInvalidYearsActive(){	
		List<String> expected = new ArrayList<String>();
		expected.add(0,null);
		expected.add(1,null);	
		expected.add(2,null);	

		SearchParameter s= new SearchParameter("Year", ">" ,"-1", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();	
	
  		searchCriteria.add(s);
  		ImplementQueryBuilder obj3 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj3.createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}

	/**
	 * Test valid keyword.
	 */
	@Test
	public void testValidKeyword(){	
		
		List<String> query3 = new ArrayList<String>();
  		query3.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.title LIKE '%Chemistry%' ");
  		query3.add(1,null);	
  		query3.add(2,null);	

		SearchParameter s= new SearchParameter("Keyword", "LIKE" ,"Chemistry", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);
  		ImplementQueryBuilder obj4 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj4.createQuery(searchCriteria);
		assertEquals(query3, queryFormed);
	}
	
	/**
	 * Test diff filter.
	 */
	@Test
	public void testDiffFilter(){
		
		List<String> query4 = new ArrayList<String>();
  		query4.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE a.Name='ABC' AND p.title LIKE '%Biology%' ");
  		query4.add(1,null);	
  		query4.add(2,null);	
  		
  		SearchParameter s1= new SearchParameter("Name", "=" ,"ABC","AND");
		SearchParameter s2= new SearchParameter("Keyword", "LIKE" ,"Biology", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
		searchCriteria.add(s1);
		searchCriteria.add(s2);
		ImplementQueryBuilder obj5 = new ImplementQueryBuilder();
		List<String> queryFormed = obj5.createQuery(searchCriteria);
		assertEquals(query4, queryFormed);
	}
	
	/**
	 * Test valid multiple keyword.
	 */
	@Test
	public void testValidMultipleKeyword(){
		
		List<String> query5 = new ArrayList<String>();
  		query5.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.title LIKE '%Chemistry%' OR  p.title LIKE '%Biology%' ");
  		query5.add(1, null);	
  		query5.add(2,null);	

		SearchParameter s1= new SearchParameter("Keyword", "LIKE" ,"Chemistry","OR ");
		SearchParameter s2= new SearchParameter("Keyword", "LIKE" ,"Biology", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
		searchCriteria.add(s1);
		searchCriteria.add(s2);
		ImplementQueryBuilder obj6 = new ImplementQueryBuilder();
		List<String> queryFormed = obj6.createQuery(searchCriteria);
		assertEquals(query5, queryFormed);
	}
	
	/**
	 * Test send query.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testSendQuery() throws SQLException, IOException{	
		SearchParameter s1= new SearchParameter("Name", "=" ,"Petra Ludewig","OR ");
		SearchParameter s2= new SearchParameter("Keyword", "LIKE" ,"Extension", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj7 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj7.createQuery(searchCriteria);
  		List<String> result = obj7.getResultForDisplay(queryFormed);			
  		assertTrue(result.contains("Yusuke Nishiguchi"));
	}
	
	/**
	 * Test multiple author search.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testMultipleAuthorSearch() throws SQLException, IOException{
		SearchParameter s1= new SearchParameter("Name", "=" ,"Shahar Maoz","OR ");
		SearchParameter s2= new SearchParameter("Name", "=" ,"Jan Oliver Ringert", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj8 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj8.createQuery(searchCriteria);
		List<String> result = obj8.getResultForDisplay(queryFormed);	
		assertEquals(2, result.size());
	}
	
	/**
	 * Test multiple author invalid search.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testMultipleAuthorInvalidSearch() throws SQLException, IOException{
		SearchParameter s1= new SearchParameter("Name", "=" ,"ABC","AND");
		SearchParameter s2= new SearchParameter("Name", "=" ,"Michael Ley", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj9 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj9.createQuery(searchCriteria);
		List<String> result = obj9.getResultForDisplay(queryFormed);	
		assertEquals(0, result.size());
	}
	
	/**
	 * Test first use case.
	 *
	 * @throws SQLException the SQL exception
	 */
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
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		searchCriteria.add(s3);
  		ImplementQueryBuilder obj10 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj10.createQuery(searchCriteria);
		assertEquals(query6, queryFormed);
	}
	
	/**
	 * Test second use case.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testSecondUseCase() throws SQLException{ 
		List<String> query7 = new ArrayList<String>();
  		query7.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE p.title LIKE '%pointer%' OR  p.title LIKE '%analysis%' GROUP BY a.name HAVING COUNT(*) >=1");
  		query7.add(1,null);
  		query7.add(2,null);
  		
		SearchParameter s1= new SearchParameter("Keyword", "LIKE" ,"pointer","OR ");
		SearchParameter s2= new SearchParameter("Keyword", "LIKE" ,"analysis", "AND");
		SearchParameter s3= new SearchParameter("CountNoOfPapers", ">=" ,"1", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		searchCriteria.add(s3);
  		ImplementQueryBuilder obj11 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj11.createQuery(searchCriteria);
		assertEquals(query7, queryFormed);
	}
	
	/**
	 * Test third use case.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testThirdUseCase() throws SQLException, IOException{   
		SearchParameter s1= new SearchParameter("Keyword", "LIKE" ,"pointer","AND");
		SearchParameter s2= new SearchParameter("Committee.Year", "=" ,"2006", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj12 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj12.createQuery(searchCriteria);
  		List<String> result = obj12.getResultForDisplay(queryFormed);	
		assertEquals(4,result.size());  
	}
	
	/**
	 * Test no duplicate author name.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testNoDuplicateAuthorName() throws SQLException, IOException{
  		 
  		SearchParameter s= new SearchParameter("Keyword", "LIKE" ,"test", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);	
  		ImplementQueryBuilder obj25 = new ImplementQueryBuilder();
		List<String> queryFormed = obj25.createQuery(searchCriteria);
		
		List<String> result = obj25.getResultForDisplay(queryFormed);	
		assertEquals(1, Collections.frequency(result, "William Pugh"));  
	}
	
	/**
	 * Test like clause author name.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testLikeClauseAuthorName() throws SQLException, IOException{ 	
  		List<String> query9= new ArrayList<String>();
  		query9.add(0,"SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey WHERE a.Name LIKE '%Anurag%' ");
  		query9.add(1,null);	
  		query9.add(2,null);
  		 
  		SearchParameter s= new SearchParameter("Name", "LIKE" ,"Anurag", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);	
  		ImplementQueryBuilder obj20 = new ImplementQueryBuilder();
		List<String> queryFormed = obj20.createQuery(searchCriteria);
		assertEquals(query9, queryFormed);
		
		List<String> result = obj20.getResultForDisplay(queryFormed);	
		assertTrue(result.contains("Anurag Mendhekar"));   
	}
	
	/**
	 * Test count of papers.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testCountOfPapers() throws SQLException, IOException{ 
  		SearchParameter s1= new SearchParameter("CountNoOfPapers", ">=" ,"2",null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		ImplementQueryBuilder obj29 = new ImplementQueryBuilder();
		List<String> queryFormed = obj29.createQuery(searchCriteria);
		List<String> result = obj29.getResultForDisplay(queryFormed);
		assertTrue(result.contains("Steven Lucco")); 
	}
	
	/**
	 * Test query with journal.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testQueryWithJournal() throws SQLException, IOException{ 	
  		SearchParameter s1= new SearchParameter("Name", "=" ,"Brian Demsky","AND");
  		SearchParameter s2= new SearchParameter("JournalName", "=" ,"tse", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj21 = new ImplementQueryBuilder();
		List<String> queryFormed = obj21.createQuery(searchCriteria);
		List<String> result = obj21.getResultForDisplay(queryFormed);
		assertTrue(result.contains("Brian Demsky")); 
	}

	/**
	 * Test in valid conf name.
	 */
	@Test
	public void testInValidConfName(){ 	
  		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("ConfName", "=" ,"CONF123", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}
	
	/**
	 * Test in valid year.
	 */
	@Test
	public void testInValidYear(){ 	
  		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("Year", "=" ,"-8", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}
	
	/**
	 * Test in valid committee year.
	 */
	@Test
	public void testInValidCommitteeYear(){ 	
  		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("Committee.Year", "=" ,"-2", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}
	
	/**
	 * Test in valid journal name.
	 */
	@Test
	public void testInValidJournalName(){ 	
  		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
  		 
  		SearchParameter s= new SearchParameter("JournalName", "=" ,"JournalName123", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);	
		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected, queryFormed);
	}
	
	/**
	 * Test query with just journal.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testQueryWithJustJournal() throws SQLException, IOException{ 	
  	  	SearchParameter s1= new SearchParameter("JournalName", "=" ,"tse", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		ImplementQueryBuilder obj28 = new ImplementQueryBuilder();
		List<String> queryFormed = obj28.createQuery(searchCriteria);
		List<String> result = obj28.getResultForDisplay(queryFormed);
		assertEquals(5337,result.size()); 
	}

	/**
	 * Test committee name.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testCommitteeName() throws SQLException, IOException{   
		SearchParameter s1= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		ImplementQueryBuilder obj31 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj31.createQuery(searchCriteria);
  		List<String> result = obj31.getResultForDisplay(queryFormed);	
		assertEquals(377,result.size());  
	}
	
	/**
	 * Test in valid committe name.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testInValidCommitteName() throws SQLException{    
		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
		SearchParameter s1= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA123", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected,queryFormed);  
	}
	
	/**
	 * Test all three tables use case.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testAllThreeTablesUseCase() throws SQLException, IOException{   
		SearchParameter s1= new SearchParameter("Keyword", "LIKE" ,"pointer","AND");
		SearchParameter s2= new SearchParameter("Committee.Year", "=" ,"2006", null);
		SearchParameter s3= new SearchParameter("JournalName", "=" ,"tse", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		searchCriteria.add(s3);
  		ImplementQueryBuilder obj31 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj31.createQuery(searchCriteria);
  		List<String> result = obj31.getResultForDisplay(queryFormed);	
		assertTrue(result.contains("Christopher J. Hetmanski"));  
	}
	
	/**
	 * Test committee and article use case.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testCommitteeAndArticleUseCase() throws SQLException, IOException{   
		SearchParameter s1= new SearchParameter("Committee.Year", "=" ,"2006", null);
		SearchParameter s2= new SearchParameter("JournalName", "=" ,"tse", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj30 = new ImplementQueryBuilder();
  		List<String> queryFormed = obj30.createQuery(searchCriteria);
  		List<String> result = obj30.getResultForDisplay(queryFormed);	
  		
		assertEquals(5415,result.size());  
	}
	
	/**
	 * Test invalid search paremeter.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testInvalidSearchParemeter() throws SQLException{   
		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
		SearchParameter s1= new SearchParameter("XYZ", "=" ,"2006", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected,queryFormed);  
	}
	
	/**
	 * Test invalid committee search paremeter.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testInvalidCommitteeSearchParemeter() throws SQLException{   
		List<String> expected = new ArrayList<String>();
  		expected.add(0, null);
  		expected.add(1, null);
  		expected.add(2, null);
		SearchParameter s1= new SearchParameter("Committee.XYZ", "=" ,"2006", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected,queryFormed);  
	}
	
	/**
	 * Test proper join formation.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	public void testProperJoinFormation() throws SQLException{   
		List<String> expected = new ArrayList<String>();
  		expected.add(0, "SELECT a.name AS Author FROM Author a INNER JOIN Paper p ON a.paperKey = p.paperKey GROUP BY a.name HAVING COUNT(*) >5");
  		expected.add(1, null);
  		expected.add(2, "SELECT a.name AS Author FROM Author a INNER JOIN Article ar ON a.articleKey = ar.articleKey WHERE ar.journal= 'TSE'");
		SearchParameter s1= new SearchParameter("JournalName", "=" ,"TSE", "AND");
		SearchParameter s2= new SearchParameter("CountNoOfPapers", ">" ,"5",null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		List<String> queryFormed = new ImplementQueryBuilder().createQuery(searchCriteria);
		assertEquals(expected,queryFormed);  
	}
	
	/**
	 * Test new.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testNew() throws SQLException, IOException{   

  		SearchParameter s1= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA", "OR ");
  		SearchParameter s3= new SearchParameter("Year", ">" ,"2017", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s3);
  		ImplementQueryBuilder obj24 =new ImplementQueryBuilder();
  		List<String> queryFormed = obj24.createQuery(searchCriteria);
  		List<String> result = obj24.getResultForDisplay(queryFormed);	
		assertEquals(377,result.size()); 
	}
	
	/**
	 * Test new 2.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testNew2() throws SQLException, IOException{   

  		SearchParameter s1= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA", "AND");
  		SearchParameter s2= new SearchParameter("Year", ">" ,"2017", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(0,result.size()); 
	}
	
	/**
	 * Test new 3.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testNew3() throws SQLException, IOException{   
		
  		SearchParameter s1= new SearchParameter("Year", ">" ,"2017", "AND");
  		SearchParameter s2= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(0,result.size()); 
	}
	
	/**
	 * Test new 4.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testNew4() throws SQLException, IOException{   

  		SearchParameter s1= new SearchParameter("Year", ">" ,"2017", "OR ");
  		SearchParameter s2= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);		
		assertEquals(377,result.size()); 
	} 
	
	/**
	 * Test four search parameter.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testFourSearchParameter() throws SQLException, IOException{   

  		SearchParameter s1= new SearchParameter("Year", ">" ,"2017", "OR ");
  		SearchParameter s2= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA", "AND");
  		SearchParameter s3= new SearchParameter("Name", "LIKE" ,"Frank", "AND");
  		SearchParameter s4= new SearchParameter("JournalName", "=" ,"tse", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		searchCriteria.add(s3);
  		searchCriteria.add(s4);
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(1,result.size()); 
	} 

	/**
	 * Test three OR search parameter.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testThreeORSearchParameter() throws SQLException, IOException{   

  		SearchParameter s1= new SearchParameter("Year", ">" ,"1990", "OR ");
  		SearchParameter s2= new SearchParameter("Committee.Year", ">" ,"1990", "OR ");
  		SearchParameter s3= new SearchParameter("Name", "LIKE" ,"Frank", "OR ");
  		SearchParameter s4= new SearchParameter("JournalName", "=" ,"tse", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		searchCriteria.add(s3);
  		searchCriteria.add(s4);
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(12798,result.size()); 
	} 
	
	/**
	 * Test to cover all filters.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToCoverAllFilters() throws SQLException, IOException{ 	
  		 
		SearchParameter s= new SearchParameter("Name","LIKE" ,"Frank","OR ");
  		SearchParameter s1= new SearchParameter("ConfName", "=" ,"OOPSLA", "OR ");
		SearchParameter s2= new SearchParameter("Committee.Year", ">" ,"1990", "OR ");
		SearchParameter s3= new SearchParameter("Committee.Year", "<" ,"2000", null);
	
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);
  		searchCriteria.add(s1);	
  		searchCriteria.add(s2);	
  		searchCriteria.add(s3);		
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(5156,result.size()); 
	}
	
	/**
	 * Test to cover all filters 2.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToCoverAllFilters2() throws SQLException, IOException{ 	

  		SearchParameter s= new SearchParameter("JournalName", "=" ,"tse", "OR ");
  		SearchParameter s1= new SearchParameter("JournalName", "=" ,"pointer", "OR ");
  		SearchParameter s2= new SearchParameter("Committee.ConfName", "=" ,"OOPSLA", " OR");
  		SearchParameter s3= new SearchParameter("Committee.ConfName", "=" ,"TSE", null);
  		
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);
  		searchCriteria.add(s1);	
  		searchCriteria.add(s2);	
  		searchCriteria.add(s3);		
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(5629,result.size()); 
	}
	
	/**
	 * Test to count no of articles valid.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToCountNoOfArticlesValid() throws SQLException, IOException{ 	

  		SearchParameter s= new SearchParameter("CountNoOfArticles", ">" ,"0", null);  	
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(6603,result.size()); 
	}
	
	/**
	 * Test to count no of articles journal.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToCountNoOfArticlesJournal() throws SQLException, IOException{ 	

		SearchParameter s1= new SearchParameter("JournalName", "=" ,"TSE", "OR "); 
  		SearchParameter s2= new SearchParameter("CountNoOfArticles", ">" ,"0", null);  	
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(5336,result.size()); 
	}
	
	/**
	 * Test to count no of articles.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToCountNoOfArticles() throws SQLException, IOException{ 	

  		SearchParameter s= new SearchParameter("CountNoOfArticles", ">" ,"3", "AND");
  		SearchParameter s1= new SearchParameter("JournalName", "=" ,"pointer", "OR ");
  		SearchParameter s2= new SearchParameter("Name","LIKE" ,"Frank", null);
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s);
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(32,result.size()); 
	}
	
	/**
	 * Test to count no of articles 3.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToCountNoOfArticles3() throws SQLException, IOException{ 	

  		SearchParameter s1= new SearchParameter("JournalName", "=" ,"pointer", "AND");
  		SearchParameter s2= new SearchParameter("Name","LIKE" ,"Frank", "OR ");
  		SearchParameter s3= new SearchParameter("CountNoOfArticles", ">" ,"3", "AND");
  		SearchParameter s4= new SearchParameter("Year", ">" ,"2000", "AND");
  		SearchParameter s5= new SearchParameter("CountNoOfPapers", ">" ,"3", null);
  		
  		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		searchCriteria.add(s3);
  		searchCriteria.add(s4);
  		searchCriteria.add(s5);
  		
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(0,result.size()); 
	}
	
	/**
	 * Test count articles only.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testCountArticlesOnly() throws SQLException, IOException {
		
		SearchParameter s1= new SearchParameter("CountNoOfArticles", ">" ,"3", null);
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(452,result.size()); 
	}
	
	/**
	 * Test count articles with author name.
	 *
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testCountArticlesWithAuthorName() throws SQLException, IOException {
		
		SearchParameter s1= new SearchParameter("CountNoOfArticles", ">" ,"3", null);
		SearchParameter s2= new SearchParameter("Name","LIKE" ,"Frank", "OR ");
		List<SearchParameter> searchCriteria = new ArrayList<SearchParameter>();
  		searchCriteria.add(s1);
  		searchCriteria.add(s2);
  		ImplementQueryBuilder obj = new ImplementQueryBuilder();
  		List<String> queryFormed = obj.createQuery(searchCriteria);
  		List<String> result = obj.getResultForDisplay(queryFormed);	
		assertEquals(482,result.size()); 
	}
}