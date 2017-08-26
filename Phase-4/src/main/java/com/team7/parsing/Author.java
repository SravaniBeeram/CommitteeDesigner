package com.team7.parsing;

/**
 * The Class Author.
 * This class gives information about the Author (name and the papers written)
 */
public class Author {

	/** The name. */
	private String name;
	
	/** The paper key. */
	private String paperKey;

	/**
	 * Instantiates a new author.
	 *
	 * @param author the author
	 * @param key the key
	 */
	public Author(String author, String key) {
		this.name = author;
		this.paperKey = key;
	} 

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	// Getters and setters
	public String getName() {
		return name;
	}

	/**
	 * Gets the paper key.
	 *
	 * @return the paper key
	 */
	public String getPaperKey() {
		return paperKey;
	}

}
