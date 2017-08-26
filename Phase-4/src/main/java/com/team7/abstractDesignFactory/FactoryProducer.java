package com.team7.abstractDesignFactory;


/**
 * The Class FactoryProducer.
 */
public class FactoryProducer {
	
	/**
	 * Gets the factory.
	 *
	 * @param choice the choice
	 * @return the factory
	 */
	public static AbstractParseFactory getFactory(String choice) {
		
		if (choice.equalsIgnoreCase("XML")) {
			return new XmlFactory();
		}
		else if (choice.equalsIgnoreCase("CSV")) {
			return new CsvFactory();
		}
		
		else if (choice.equalsIgnoreCase("TXT")) {
			return new TextFactory();
		}
		return null;
		
	}

}
