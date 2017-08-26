package com.team7;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
// This class gives information about the article (authors, title, date of publishing)
public class Article {

	private List<String> author; 
	private String key; 
	private String title; 
	private int year;
	private String month;
	private String ee;

	public Article () {

	}

	// Getter and setters
	public String getKey() {
		return key;
	}

	@XmlAttribute
	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getAuthor() {
		return author;
	}

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

	public int getYear() {
		return year;
	}

	@XmlElement
	public void setYear(int year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	@XmlElement
	public void setMonth(String month) {
		this.month = month;
	}

	public String getEe() {
		return ee;
	}

	@XmlElement
	public void setEe(String ee) {
		this.ee = ee;
	}



}
