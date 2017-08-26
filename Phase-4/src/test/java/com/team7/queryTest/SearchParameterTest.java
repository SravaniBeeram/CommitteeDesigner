package com.team7.queryTest;

import static org.junit.Assert.*;
import org.junit.Test;

import com.team7.queryEngine.SearchParameter;

/**
 * The Class SearchParameterTest.
 */
public class SearchParameterTest {
	
	/** The test. */
	SearchParameter test = new SearchParameter();
	
	/** The test filter. */
	String testFilter = "filter";
	
	/** The test comparator. */
	String testComparator = "comparator";
	
	/** The test value. */
	String testValue = "value";
	
	/** The join filter. */
	String joinFilter = "joinFilterValue";
	
	/**
	 * Test set search filter.
	 */
	@Test
	public void testSetSearchFilter() {
		String searchFilter = test.setSearchFilter(testFilter);
		assertEquals("filter", searchFilter);
		assertNotNull(searchFilter);
	}
	
	/**
	 * Test search comparator.
	 */
	@Test
	public void testSearchComparator() {
		String searchComparator = test.setSearchComparator(testComparator);
		assertEquals("comparator", searchComparator);
		assertNotNull(searchComparator);
	}
	
	/**
	 * Test set search value.
	 */
	@Test
	public void testSetSearchValue() {
		String searchValue = test.setSearchValue(testValue);
		assertEquals("value", searchValue);
		assertNotNull(searchValue);
	}
	
	/**
	 * Test set join filter.
	 */
	@Test
	public void testSetJoinFilter() {
		String searchJoinFilter = test.setJoinFilter(joinFilter);
		assertEquals("joinFilterValue", searchJoinFilter);
		assertNotNull(searchJoinFilter);
	}
}
