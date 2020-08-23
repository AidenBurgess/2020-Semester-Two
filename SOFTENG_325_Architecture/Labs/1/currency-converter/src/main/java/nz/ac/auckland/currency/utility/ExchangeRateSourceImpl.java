package nz.ac.auckland.currency.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import nz.ac.auckland.currency.Currency;


/**
 * Class to represent a data source for exchange rates. ExchangeRateSourceImpl
 * defines a single method to return of Map of exchange rates.
 * 
 * This implementation reads exchange rates from a data file.
 *
 */
public class ExchangeRateSourceImpl implements ExchangeRateSource {
	
	private Map<Currency, Double> _exchangeRates;
	
	public ExchangeRateSourceImpl() {
		_exchangeRates = new HashMap<Currency, Double>();

		BufferedReader in = null;

		try {
			// Open the file containing exchange rates. In a Maven project,
			// resources should be located in src/main/resources. At build time,
			// Maven copies any such resources to target/classes. Any such
			// resource will be accessible on the test CLASSPATH.
			//
			// The getResource() and getResourceAsStream() methods of Java's
			// Class class can be used to access CLASSPATH resources.
			
			String exchangeRateFileName = AppProperties.instance().getProperty(
					AppProperties.PERSISTENCE_EXCHANGE_RATES_FILE);

			InputStream is = ExchangeRateSourceImpl.class.getResourceAsStream("/"
					+ exchangeRateFileName);
			InputStreamReader isreader = new InputStreamReader(is);
			in = new BufferedReader(isreader);

			String inputLine = null;
			String[] tokens = null;
			while ((inputLine = in.readLine()) != null) {
				tokens = inputLine.split("\\s");
				if (tokens != null && tokens.length == 2) {
					_exchangeRates.put(Currency.valueOf(tokens[0]),
							Double.valueOf(tokens[1]));
				}
			}
		} catch (Exception e) {
			System.out.println(
					AppProperties.instance().getProperty(
							AppProperties.MESSAGES_ERROR_READING_DATA_FILE));
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// Ignore any exception when attempting to close the input
				// stream, but log for debugging purposes.
				System.out.println(
						AppProperties.MESSAGES_DEBUG_ERROR_CLOSING_INPUT_STREAM);
			}
		}
	}
	
	/** 
	 * Returns the exchange rate for a given foreign currency.
	 */
	@Override
	public double getExchangeRate(Currency currency) {
		return _exchangeRates.get(currency);
	}
	
	/**
	 * Returns a Map containing exchange rates for foreign currencies. The map
	 * key is a foreign currency, and the value is the exchange rate for NZ$1.
	 * 
	 */
	public Map<Currency, Double> getExchangeRates() {
		return _exchangeRates;
	}
}
