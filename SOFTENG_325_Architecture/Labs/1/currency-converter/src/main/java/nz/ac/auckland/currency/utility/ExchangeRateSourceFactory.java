package nz.ac.auckland.currency.utility;

import nz.ac.auckland.currency.CurrencyConverter;

/**
 * Factory class to create an implementation of the ExchangeRateSource 
 * interface. 
 * 
 * The class to instantiate is named as a property of this application.
 *
 */
public class ExchangeRateSourceFactory {
	private static ExchangeRateSource _exchangeRateSource = null;
	
	public static ExchangeRateSource getExchangeRateSource() {
		if(_exchangeRateSource != null) {
			return _exchangeRateSource;
		}
		
		// Lookup the name of the ExchangeRateSource class to instantiate.
		AppProperties props = AppProperties.instance();
		String className = props.getProperty(AppProperties.EXCHANGE_RATES_SOURCE_CLASS);
		
		try {	
			// Instantiate ExchangeRateSource class.
			System.out.println("Class name: " + className);
			_exchangeRateSource = (ExchangeRateSource)Class.forName(className).newInstance();
		} catch(Exception e) {
			System.out.println(props.getProperty(AppProperties.MESSAGES_ERROR_INSTANTIATING_EXCHANGE_RATE_SOURCE));
		}
		
		return _exchangeRateSource;
	}
}
