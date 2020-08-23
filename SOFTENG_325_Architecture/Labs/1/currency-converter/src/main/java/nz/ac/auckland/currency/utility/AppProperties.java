package nz.ac.auckland.currency.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton class to manage access to application properties.
 * 
 * As a Singleton, only one AppProperties is created and the same instance is
 * always returned when requested (via class method instance()). AppProperties
 * provides a convenient way to read property values, abstracting over reading
 * from a data file and, for efficiency, ensuring that the file is read only
 * once - when initially creating the AppProperties instance.
 * 
 * Properties are listed in a properties file that is expected to be named
 * "app.properties" and which is located at the root of CLASSPATH.
 * 
 * @author Ian Warren
 *
 */
public class AppProperties {
	/*
	 * ======================== Property key names START
	 */

	// Persistence keys.
	public static final String PERSISTENCE_EXCHANGE_RATES_FILE = "persistence.exchange-rates";

	// Error message keys.
	public static final String MESSAGES_ERROR_READING_DATA_FILE = "messages.error.reading-data-file";
	public static final String MESSAGES_ERROR_INSTANTIATING_EXCHANGE_RATE_SOURCE = "messages.error.instantiating-exchange-rate-source";
			

	// Debug message keys.
	public static final String MESSAGES_DEBUG_LOADED_EXCHANGE_RATES = "messages.debug.loaded-exchange-rates";
	public static final String MESSAGES_DEBUG_ERROR_CLOSING_INPUT_STREAM = "messages.debug.error-closing-input-stream";

	public static final String EXCHANGE_RATES_SOURCE_CLASS = "exchange-rates.source.class";
	
	
	/*
	 * Property key names END ======================
	 */

	// Debug messages, used internally as part of this class' implementation.
	private static final String SUCCESS_LOADING_PROPERTIES = "Successfully loaded app properties";
	private static final String ERROR_LOADING_PROPERTIES = "Unable to load application properties";

	// Singleton instance.
	private static AppProperties _instance = null;

	// Properties object, storing the property key/value pairs.
	private Properties _properties;

	
	/**
	 * Returns the Singleton instance, creating it if necessary.
	 */
	public static AppProperties instance() {
		if (_instance == null) {
			_instance = new AppProperties();
		}
		return _instance;
	}

	/**
	 * Returns the name of the requested property, or null if the property is
	 * unknown.
	 * 
	 * @param key
	 *            the name of the property whose value is requested.
	 */
	public String getProperty(String key) {
		return _properties.getProperty(key);
	}

	/**
	 * Instantiates AppProperties, reading properties from a data file.
	 */
	protected AppProperties() {
		_properties = new Properties();
		InputStream input = null;

		try {
			input = getClass().getResourceAsStream("/app.properties");
			_properties.load(input);
			System.out.println(SUCCESS_LOADING_PROPERTIES);
		} catch (IOException e) {
			System.out.println(ERROR_LOADING_PROPERTIES);
		}
	}
}
