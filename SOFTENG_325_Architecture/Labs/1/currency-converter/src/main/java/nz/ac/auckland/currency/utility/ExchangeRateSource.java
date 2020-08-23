package nz.ac.auckland.currency.utility;

import java.util.Map;

import nz.ac.auckland.currency.Currency;

/**
 * Interface to represent a data source for exchange-rates.
 * 
 * @author Ian Warren
 *
 */
public interface ExchangeRateSource {
	/** 
	 * Returns the exchange rate for a given foreign currency.
	 */
	double getExchangeRate(Currency currency); 
	
	/**
	 * Returns a Map containing exchange rates for foreign currencies. The map
	 * key is a foreign currency, and the value is the exchange rate for NZ$1.
	 * 
	 */
	Map<Currency, Double> getExchangeRates();
}
