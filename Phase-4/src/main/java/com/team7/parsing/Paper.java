package com.team7.parsing;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class Paper.
 * Contains information about the paper (authors, title , pages etc.)
 */ 
public class Paper {

	/** The author. */
	private List<String> author;
	
	/** The title. */
	private String title;
	
	/** The pages. */
	private String pages;
	
	/** The year. */
	private int year;
	
	/** The key. */
	private String key; 
	
	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public List<String> getAuthor() {
		return author;
	}

	/**
	 * Sets the author.
	 *
	 * @param author the new author
	 */
	// Getters and setters
	@XmlElement
	public void setAuthor(List<String> author) {
		this.author = author;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the pages.
	 *
	 * @return the pages
	 */
	public String getPages() {
		return pages;
	}

	/**
	 * Sets the pages.
	 *
	 * @param pages the new pages
	 */
	@XmlElement
	public void setPages(String pages) {
		this.pages = pages;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	@XmlElement
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	@XmlAttribute
	public void setKey(String key) {		
		 this.key = key;
       	}

}
