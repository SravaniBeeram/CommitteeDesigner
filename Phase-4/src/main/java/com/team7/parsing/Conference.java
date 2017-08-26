package com.team7.parsing;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * The Class Conference.
 * This class contains information about the conferences ( title, extra information about it)
 */ 
public class Conference {

	/** The booktitle. */
	private String booktitle; // name of the conference
	
	/** The title. */
	private String title; // extra info about the conference
	
	/** The key. */
	private String key;

	/**
	 * Gets the booktitle.
	 *
	 * @return the booktitle
	 */
	public String getBooktitle() {
		return booktitle;
	}

	/**
	 * Sets the booktitle.
	 *
	 * @param booktitle the new booktitle
	 */
	// getter and setters
	@XmlElement
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
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
