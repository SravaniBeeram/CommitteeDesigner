package com.team7;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

// Contains information about the paper (authors, title , pages etc.)
@SuppressWarnings("restriction")
public class Paper {

	private List<String> author;
	private String title;
	private String pages;
	private int year;
	private String key; 
	
	public List<String> getAuthor() {
		return author;
	}

	// Getters and setters
	@XmlElement
	public void setAuthor(List<String> author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPages() {
		return pages;
	}

	@XmlElement
	public void setPages(String pages) {
		this.pages = pages;
	}

	public int getYear() {
		return year;
	}

	@XmlElement
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getKey() {
		return key;
	}

	@XmlAttribute
	public void setKey(String key) {		
		 this.key = key;
       	}

}
