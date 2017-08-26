package com.team7.parsing;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class Article.
 * This class gives information about the article (authors, title, date of publishing)
 */

public class Article {

	/** The author. */
	private List<String> author; 
	
	/** The key. */
	private String key; 
	
	/** The title. */
	private String title; 
	
	/** The year. */
	private int year;
	
	/** The ee. */
	private String ee;

	/**
	 * Instantiates a new article.
	 */
	public Article () {

	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	// Getter and setters
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
	 * Gets the ee.
	 *
	 * @return the ee
	 */
	public String getEe() {
		return ee;
	}

	/**
	 * Sets the ee.
	 *
	 * @param ee the new ee
	 */
	@XmlElement
	public void setEe(String ee) {
		this.ee = ee;
	}
}
