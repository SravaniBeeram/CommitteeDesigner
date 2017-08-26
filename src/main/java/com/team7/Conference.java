package com.team7;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

// This class contains information about the conferences ( title, extra information about it)
@SuppressWarnings("restriction")
public class Conference {

	private String booktitle; // name of the conference
	private String title; // extra info about the conference
	private String key;

	public String getBooktitle() {
		return booktitle;
	}

	// getter and setters
	@XmlElement
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	@XmlAttribute
	public void setKey(String key) {
		this.key = key;
	}


}
