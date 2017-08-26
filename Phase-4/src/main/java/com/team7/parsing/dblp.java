package com.team7.parsing;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class dblp.
 * This class represents the root element of the xml file.
 * Contains objects of all the tags whose information is required.
 * Information of all these objects will be extracted by JAXB parser.
 */
 
@XmlRootElement
public class dblp {

	/** The inproceedings. */
	private List<Paper> inproceedings;
	
	/** The proceedings. */
	private List<Conference> proceedings;
	
	/** The article. */
	private List<Article> article;

	/**
	 * Gets the inproceedings.
	 *
	 * @return the inproceedings
	 */
	// getters and setters
	public List<Paper> getInproceedings() {
		return inproceedings;
	}

	/**
	 * Sets the inproceedings.
	 *
	 * @param inproceedings the new inproceedings
	 */
	@XmlElement
	public void setInproceedings(List<Paper> inproceedings) {
		this.inproceedings = inproceedings;
	}

	/**
	 * Gets the proceedings.
	 *
	 * @return the proceedings
	 */
	public List<Conference> getProceedings() {
		return proceedings;
	}

	/**
	 * Sets the proceedings.
	 *
	 * @param proceedings the new proceedings
	 */
	@XmlElement
	public void setProceedings(List<Conference> proceedings) {
		this.proceedings = proceedings;
	}

	/**
	 * Gets the article.
	 *
	 * @return the article
	 */
	public List<Article> getArticle() {
		return article;
	}

	/**
	 * Sets the article.
	 *
	 * @param article the new article
	 */
	@XmlElement
	public void setArticle(List<Article> article) {
		this.article = article;
	}

}

