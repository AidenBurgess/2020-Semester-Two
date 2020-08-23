package nz.ac.auckland.currency;

import nz.ac.auckland.currency.utility.ExchangeRateSource;
import nz.ac.auckland.currency.utility.ExchangeRateSourceFactory;


/**
 * Class that converts NZ dollars into specified foreign currencies.
 * 
 * @author Ian Warren
 *
 */
public class CurrencyConverter {

	// Data structure to store exchange rates with foreign currencies.
	private ExchangeRateSource _exchangeRates;

	/**
	 * Creates a CurrencyConverter instance, populating it with a set of
	 * exchange rates.
	 */
	public CurrencyConverter() {
		_exchangeRates = ExchangeRateSourceFactory.getExchangeRateSource();
	}

	/**
	 * Converts a specified amount in NZ dollars to a specified foreign
	 * currency.
	 * 
	 * @param amountInNZDollars
	 *            amount, in NZ dollars, to exchange
	 * @param targetCurrency
	 *            target foreign currency
	 * @return the amount in the specified currency corresponding to the NZ
	 *         dollar value
	 */
	public double convert(double amountInNZDollars, Currency targetCurrency) {
		return _exchangeRates.getExchangeRate(targetCurrency) * amountInNZDollars;
	}
}
