package com.team7;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

// This class gives the information about details of the author (name and their home page)
@SuppressWarnings("restriction")
public class AuthorDetails {

	private List<String> author;
	private String url;

	public AuthorDetails() {
		// TODO Auto-generated constructor stub
	}

	public 	List<String> getAuthor() {
		return 	author;
	}

	@XmlElement
	public void setAuthor(List<String> name) {
		this.author = name;
	}

	public String getUrl() {
		return url;
	}

	@XmlElement
	public void setUrl(String url) {
		this.url = url;
	}
}
